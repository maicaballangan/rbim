package exceptions;

/**
 * EncryptionException
 *
 * @author Maica Ballangan
 * @since v1
 */

public class EncryptionException extends Exception {

    public EncryptionException() {
        super();
    }

    public EncryptionException(final String message) {
        super(message);
    }

    public EncryptionException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public EncryptionException(final Throwable cause) {
        super(cause);
    }
}
