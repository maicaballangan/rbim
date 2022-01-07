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

public enum ToiletFacility {
    NONE(0),
    OPEN_PIT(1),
    CLOSE_PIT(2),
    WATER_SEALED_SHARED_DEPOSITORY(3),
    WATER_SEALED_EXCLUSIVE_DEPOSITORY(4),
    WATER_SEALED_SHARED_SEWER_SEPTIC_TANK(5),
    WATER_SEALED_EXCLUSIVE_SEWER_SEPTIC_TANK(6),
    OTHERS(7);

    private static final Map<Integer, ToiletFacility> map;

    static {
        map = Arrays
                .stream(ToiletFacility.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, OTHERS);
    }

    public static ToiletFacility getByCode(Integer code) {
        if (code == null || code == 99) return null;
        return map.get(code);
    }

    private final Integer code;

    ToiletFacility(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + " - " + name().replaceAll("_", " ");
    }
}