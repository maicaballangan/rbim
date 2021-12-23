
package utils;

import exceptions.EncryptionException;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cms.*;
import org.bouncycastle.cms.jcajce.*;
import org.bouncycastle.jcajce.provider.digest.Keccak;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.OutputEncryptor;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.bouncycastle.util.Store;
import play.Play;
import play.vfs.VirtualFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * This Singleton class is used to manage the encryption of transactions. It's used to
 * sign a transaction using a private key and verify a signed transaction
 * using the corresponding public key. It can also be used to encrypt and decrypt data, although symmetric strong
 * encryption (AES256) is preferred since the f*cking NSA can probably break ECDH-based ciphers.
 * <p>
 * To generate a jks keystore, use the following openssl and keytool commands...
 * <br>
 * <pre>>openssl req -out CSR.csr -new -newkey rsa:2048 -nodes -keyout private.key</pre>
 * <br>
 * To self-sign the CSR and generate a certificate:
 * <br>
 * <pre>>openssl req -x509 -sha256 -nodes -days 365 -newkey rsa:2048 -keyout private.key -out certificate.crt</pre>
 * <br>
 * To convert the certificate and private key into PKCS#12 format (native keystore format):
 * <br>
 * <pre>>openssl pkcs12 -export -out certificate.p12 -inkey private.key -in certificate.crt</pre>
 * <br>
 * To convert the PKCS#12 file into a JKS file:
 * <br>
 * <pre>>keytool -importkeystore -destkeystore keystore.jks -srckeystore certificate.p12 -srcstoretype PKCS12 -alias 1</pre>
 * <br>
 * If the previous command states you need to convert to PKCS12 again, just copy and paste the command provided.
 * You should now have a keystore.jks keystore file. You can delete the keystore.jks.old one if present.
 * </p>
 *
 * @since v1
 */
public class EncryptionUtils {

    private static final String KEYSTORE_EXTENSION = "JKS";
    private static final String KEYSTORE_FILE = Play.configuration.getProperty("keystore.file");
    private static final String KEYSTORE_PASSWORD = Play.configuration.getProperty("keystore.password");
    private static final String KEYSTORE_ALIAS = Play.configuration.getProperty("keystore.alias");
    private static final String PROVIDER = "BC";
    private static final String ALGORITHM = "SHA256withRSA";
    private static final KeyStore keyStore = readKeyStore(VirtualFile.fromRelativePath(KEYSTORE_FILE).getRealFile(),
            KEYSTORE_PASSWORD);

    private static final X509Certificate publicKey = readPublicKey();
    private static final PrivateKey privateKey = readPrivateKey();

    /**
     * Private constructor - should use static methods only
     */
    private EncryptionUtils() {
        // private utility class constructor
        throw new IllegalStateException("Utility class");
    }

    /**
     * Reads a Java keystore from a file.
     *
     * @param keystoreFile keystore file to read
     * @param password     password for the keystore file
     * @return the keystore object
     * @throws KeyStoreException         if the buildingType of KeyStore could not be created
     * @throws IOException               if the keystore could not be loaded
     * @throws NoSuchAlgorithmException  if the algorithm used to check the integrity of the keystore
     *                                   cannot be found
     * @throws UnrecoverableKeyException if the private key cannot be retrieved from keystore
     */
    private static KeyStore readKeyStore(final File keystoreFile, final String password) {
        try {
            final KeyStore keystore = KeyStore.getInstance(KEYSTORE_EXTENSION);
            try (final FileInputStream fis = new FileInputStream(keystoreFile)) {
                keystore.load(fis, password.toCharArray());
                return keystore;
            }
        } catch (IOException | CertificateException | KeyStoreException | NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Keystore cannot be initialized", e);
        }
    }

    private static X509Certificate readPublicKey() {
        try {
            return (X509Certificate) keyStore.getCertificate(KEYSTORE_ALIAS);
        } catch (KeyStoreException e) {
            throw new IllegalArgumentException("Public Key not found...", e);
        }
    }

    private static PrivateKey readPrivateKey() {
        try {
            return (PrivateKey) keyStore.getKey(KEYSTORE_ALIAS, KEYSTORE_PASSWORD.toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new IllegalArgumentException("Private Key not found...", e);
        }
    }

    /**
     * Signs the given data and returns the signature as a {@link java.security.Signature Signature}
     *
     * @param data the data to be signed.
     * @return the signature for the given data.
     * @throws EncryptionException if the data could not be signed.
     */
    public static Signature sign(final String data) throws EncryptionException {
        try {
            Signature signature = Signature.getInstance(ALGORITHM, PROVIDER);
            signature.initSign(privateKey);
            signature.update(data.getBytes());
            return signature;
        } catch (SignatureException | InvalidKeyException | NoSuchProviderException | NoSuchAlgorithmException e) {
            throw new EncryptionException(e);
        }
    }

    /**
     * Verifies the signature and returns true or false if it is valid
     *
     * @param signature The signature to verify
     * @return True if valid or false otherwise
     * @throws EncryptionException if the signature could not be verified
     */
    public static boolean verify(final Signature signature)
            throws EncryptionException {

        try {
            // Build CMS
            List certList = new ArrayList();
            CMSTypedData data = new CMSProcessableByteArray(signature.sign());
            certList.add(publicKey);
            Store certs = new JcaCertStore(certList);
            CMSSignedDataGenerator gen = new CMSSignedDataGenerator();
            ContentSigner sha1Signer = new JcaContentSignerBuilder(ALGORITHM).setProvider(PROVIDER).build(privateKey);
            gen.addSignerInfoGenerator(new JcaSignerInfoGeneratorBuilder(new JcaDigestCalculatorProviderBuilder().setProvider(PROVIDER).build()).build(sha1Signer, publicKey));
            gen.addCertificates(certs);
            CMSSignedData signedData = gen.generate(data, true);
            Store store = signedData.getCertificates();
            SignerInformationStore signers = signedData.getSignerInfos();
            Collection c = signers.getSigners();
            Iterator it = c.iterator();
            while (it.hasNext()) {
                SignerInformation signer = (SignerInformation) it.next();
                Collection certCollection = store.getMatches(signer.getSID());
                Iterator certIt = certCollection.iterator();
                X509CertificateHolder certHolder = (X509CertificateHolder) certIt.next();
                X509Certificate certFromSignedData = new JcaX509CertificateConverter().setProvider(PROVIDER).getCertificate(certHolder);
                return signer.verify(new JcaSimpleSignerInfoVerifierBuilder().setProvider(PROVIDER).build(certFromSignedData));
            }
            return false;
        } catch (CMSException | OperatorCreationException | CertificateException | SignatureException e) {
            throw new EncryptionException(e);
        }
    }

    /**
     * Encrypts the data and returns a byte array representing the encrypted String
     *
     * @param data the data to encrypt
     * @return The encrypted String
     * @throws EncryptionException
     */
    public static byte[] encrypt(final String data)
            throws EncryptionException {
        try {
            byte[] encryptedData = null;
            if (null != data && null != publicKey) {
                CMSEnvelopedDataGenerator cmsEnvelopedDataGenerator = new CMSEnvelopedDataGenerator();
                JceKeyTransRecipientInfoGenerator jceKey = new JceKeyTransRecipientInfoGenerator(publicKey);
                cmsEnvelopedDataGenerator.addRecipientInfoGenerator(jceKey);
                CMSTypedData msg = new CMSProcessableByteArray(data.getBytes());
                OutputEncryptor encryptor = new JceCMSContentEncryptorBuilder(CMSAlgorithm.AES256_GCM).setProvider(PROVIDER)
                        .build();
                CMSEnvelopedData cmsEnvelopedData = cmsEnvelopedDataGenerator.generate(msg, encryptor);
                encryptedData = cmsEnvelopedData.getEncoded();
            }
            return encryptedData;
        } catch (CertificateEncodingException | CMSException | IOException e) {
            throw new EncryptionException(e);
        }
    }

    /**
     * Decrypts the encrypted byte array back into a String
     *
     * @param encryptedData The encrypted String in a byte array
     * @return The decrypted String
     * @throws EncryptionException
     */
    public static String decrypt(final byte[] encryptedData) throws EncryptionException {
        try {
            byte[] decryptedData = null;
            if (null != encryptedData && null != privateKey) {
                CMSEnvelopedData envelopedData = new CMSEnvelopedData(encryptedData);
                Collection<RecipientInformation> recip = envelopedData.getRecipientInfos().getRecipients();
                KeyTransRecipientInformation recipientInfo = (KeyTransRecipientInformation) recip.iterator().next();
                JceKeyTransRecipient recipient = new JceKeyTransEnvelopedRecipient(privateKey);
                decryptedData = recipientInfo.getContent(recipient);
            }
            return new String(decryptedData);
        } catch (CMSException e) {
            throw new EncryptionException(e);
        }
    }

    /**
     * Hashes data by using Keccak-256, a popular SHA3-256 hashing algorithm.
     *
     * @param data the string to hash in UTF-8
     * @return the hashed string in Base64 format
     */
    public static String hash(final String data) {
        Keccak.Digest256 digest256 = new Keccak.Digest256();
        byte[] hashbytes = digest256.digest(
                data.getBytes(StandardCharsets.UTF_8));
        return new String(Base64.encodeBase64(hashbytes));
    }
}
