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

public enum WaterSource {
    LAKE_RIVER_RAIN_OTHERS(1),
    DUG_WELL(2),
    UNPROTECTED_SPRING(3),
    PROTECTED_SPRING(4),
    PEDDLER(5),
    TUBED_OR_PIPED_SHALLOW_WELL(6),
    SHARED_TUBED_OR_PIPED_DEEP_WELL(7),
    OWN_USE_TUBED_OR_PIPED_DEEP_WELL(8),
    SHARED_USE_FAUCET_COMMUNITY_WATER_SYSTEM(9),
    OWN_USE_FAUCET_COMMUNITY_WATER_SYSTEM(10),
    BOTTLED_WATER(11),
    OTHERS(12);

    private static final Map<Integer, WaterSource> map;

    static {
        map = Arrays
                .stream(WaterSource.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, OTHERS);
    }

    public static WaterSource getByCode(Integer code) {
        if (code == null || code == 99) return null;
        return map.get(code);
    }

    private final Integer code;

    WaterSource(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
    return code;
}

    @Override
    public String toString() {
        return name().replaceAll("_", " ");
    }
}