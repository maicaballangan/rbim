package exceptions;

import enums.APIErrorCode;
import interfaces.ErrorCode;
import interfaces.Field;

/**
 * ValidationException
 *
 * @author Maica Ballangan
 * @since v1
 */

public class ValidationException extends RuntimeException {
    private final HttpException httpException;

    /**
     * ValidationException with message
     *
     * @param message The message pattern
     */
    public ValidationException(final String message) {
        super(message);
        httpException = new HttpException(APIErrorCode.VALIDATION_FAILED, message);
    }

    /**
     * ValidationException with message
     *
     * @param e Throwable exception
     */
    public ValidationException(final Throwable e) {
        super(e);
        httpException = new HttpException(APIErrorCode.VALIDATION_FAILED, e);
    }

    /**
     * ValidationException with message
     *
     * @param code The Error code
     */
    public ValidationException(final ErrorCode code) {
        super(code.message());
        this.httpException = new HttpException(code);
    }

    /**
     * ValidationException with message
     *
     * @param field The field required
     */
    public ValidationException(final Field field) {
        super(field.message());
        this.httpException = new HttpException(field);
    }

    /**
     * ValidationException with message
     *
     * @param code    The Error code
     * @param message The message pattern
     */
    public ValidationException(final ErrorCode code, final String message) {
        super(message);
        this.httpException = new HttpException(code, message);
    }

    /**
     * ValidationException with message
     *
     * @param code  The Error code
     * @param cause The throwable cause
     */
    public ValidationException(final ErrorCode code, final Throwable cause) {
        super(cause.getMessage());
        this.httpException = new HttpException(code, cause);
    }

    public HttpException httpException() {
        return httpException;
    }
}
