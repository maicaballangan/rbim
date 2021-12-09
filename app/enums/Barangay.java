package enums;

/**
 * @author Maica Ballangan
 * @since v1
 */
public enum Barangay {
    LayaEast("Laya East");

    private String description;

    Barangay(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
