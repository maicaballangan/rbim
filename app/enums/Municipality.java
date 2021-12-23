package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Maica Ballangan
 * @since v1
 */
public enum Municipality {
    Balbalan("Balbalan"),
    Pasil("Pasil"),
    Lubugan("Lubuagan"),
    Pinukpuk("Pinukpuk"),
    Rizal("Rizal"),
    Tabuk("Tabuk"),
    Tanudan("Tanudan"),
    Tinglayan("Tinglayan"),
    Undefined("99");

    private static final Map<String, Municipality> map;

    static {
        map = Arrays.stream(Municipality
                        .values())
                .collect(Collectors.toMap(e -> e.description, Function.identity()));
    }

    public static Municipality getByDescription(String description) {
        return map.get(description);
    }

    private String description;

    Municipality(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}