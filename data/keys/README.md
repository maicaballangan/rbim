To generate a jks keystore, use the following openssl and keytool commands:

`openssl req -out CSR.csr -new -newkey rsa:2048 -nodes -keyout private.key`
`openssl req -x509 -sha256 -nodes -days 365 -newkey rsa:2048 -keyout private.key -out certificate.crt`
`openssl pkcs12 -export -out certificate.p12 -inkey private.key -in certificate.crt`
`keytool -importkeystore -destkeystore keystore.jks -srckeystore certificate.p12 -srcstoretype PKCS12 -alias 1`
