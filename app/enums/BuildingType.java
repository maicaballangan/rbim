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

public enum BuildingType {
    SINGLE(1, "SINGLE"),
    DUPLEX(2, "DUPLEX"),
    MULTIUNIT_RESIDENTIAL(3, "MULTI-UNIT RESIDENTIAL"),
    COMMERCIAL_INDUSTRIAL_AGRICULTURAL(4, "COMMERCIAL/UNIT/AGRICULTURAL"),
    INSTITUTIONAL_LIVING_QUARTER(5, "INSTITUTIONAL LIVING QUARTER (HOTEL, HOSPITAL)"),
    OTHERS(6, "OTHER HOUSING UNITS (BOAT, CAVE, OTHERS)");

    private static final Map<Integer, BuildingType> map;

    static {
        map = Arrays
                .stream(BuildingType.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, OTHERS);
    }

    public static BuildingType getByCode(Integer code) {
        if (code == null || code == 99) return null;
        return map.get(code);
    }

    private final Integer code;
    private final String description;

    BuildingType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + " - " + description;
    }
}