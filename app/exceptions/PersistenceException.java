package exceptions;

/**
 * PersistenceException
 *
 * @author Maica Ballangan
 * @since v1
 */

public class PersistenceException extends Exception {

    private final String index;
    private final String id;

    /**
     * PersistenceException with throwable cause
     *
     * @param message The message pattern
     * @param cause   the exception cause
     * @param index   index of record that failed to save
     * @param id      id of record that failed to save
     */
    public PersistenceException(final String message, final Throwable cause, final String index, final String id) {
        super(message, cause);
        this.index = index;
        this.id = id;
    }

    /**
     * PersistenceException with throwable cause
     *
     * @param message The message pattern
     * @param cause   the exception cause
     * @param index   index of record that failed to save
     */
    public PersistenceException(final String message, final Throwable cause, final String index) {
        super(message, cause);
        this.index = index;
        this.id = null;
    }

    /**
     * PersistenceException with message
     *
     * @param message The message pattern
     */
    public PersistenceException(final String message) {
        super(message);
        this.index = null;
        this.id = null;
    }

    /**
     * PersistenceException with message, index and id
     *
     * @param message the message pattern
     * @param index   index of record that failed to save
     * @param id      id of record that failed to save
     */
    public PersistenceException(final String message, final String index, final String id) {
        super(message);
        this.index = index;
        this.id = id;
    }

    /**
     * PersistenceException with cause
     *
     * @param cause The cause
     */
    public PersistenceException(final Throwable cause) {
        super(cause);
        this.index = null;
        this.id = null;
    }

    public final String getIndex() {
        return index;
    }

    public final String getId() {
        return id;
    }

}
