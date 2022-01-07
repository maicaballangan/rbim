/* Copyright (C) 2022 Jamaica Ballangan - All Rights Reserved
 * Clients may use and modify this code under the
 * terms and agreement only. Selling or distribution is prohibited
 * without the consent of the author
 */
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
    UNDEFINED(null),
    BALBALAN("Balbalan"),
    PASIL("Pasil"),
    LUBUAGAN("Lubuagan"),
    PINUKPUK("Pinukpuk"),
    RIZAL("Rizal"),
    TABUK_CITY("Tabuk City"),
    TANUDAN("Tanudan"),
    TINGLAYAN("Tinglayan");

    private static final Map<String, Municipality> map;

    static {
        map = Arrays.stream(Municipality
                        .values())
                .collect(Collectors.toMap(e -> e.description, Function.identity()));
    }

    public static Municipality getByDescription(String description) {
        if (description == null) return null;
        if ("99".equals(description)) return UNDEFINED;
        return Municipality.valueOf(description);
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