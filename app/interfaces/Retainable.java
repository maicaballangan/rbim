package interfaces;

public interface Retainable extends play.db.Model {

    /**
     * Get status of the record
     *
     * @return status
     */
    Enum<?> getStatus();

    /**
     * Get status of the record
     */
    void setStatus(final Enum<?> status);

    /**
     * Get remove status of the record
     *
     * @return status
     */
    Enum<?> getRemoveStatus();

    default void _delete() {
        setRemoveStatus();
        _save();
    }

    /**
     * Sets the status to removed for this instance
     */
    default void setRemoveStatus() {
        setStatus(getRemoveStatus());
    }
}
