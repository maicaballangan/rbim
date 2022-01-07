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

public enum Facility {
    GOVERNMENT_HOSPITAL(1),
    RHU_OR_HEALTH_CENTER(2),
    BRGY_HEALTH_STATION(3),
    PRIVATE_HOSPITAL(4),
    PRIVATE_CLINIC(5),
    PHARMACY(6),
    HILOT_OR_HERBALIST(7);

    private static final Map<Integer, Facility> map;

    static {
        map = Arrays
                .stream(Facility.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, null);
    }

    public static Facility getByCode(Integer code) {
        if (code == null || code == 99) return null;
        return map.get(code);
    }

    private final Integer code;

    Facility(Integer code) {
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