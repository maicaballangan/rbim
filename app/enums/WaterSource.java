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
    Lake_river_rain_others(1),
    Dug_well(2),
    Unprotected_spring(3),
    Protected_Spring(4),
    Peddler(5),
    Tubed_or_Piped_Shallow_Well(6),
    Shared_Tubed_or_Piped_Deep_Well(7),
    Own_Use_Tubed_or_Piped_Deep_Well(8),
    Shared_Use_Faucet_Community_Water_System(9),
    Own_Use_Faucet_Community_Water_System(10),
    Bottled_Water(11),
    Others(12);

    private static final Map<Integer, WaterSource> map;

    static {
        map = Arrays
                .stream(WaterSource.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, Others);
    }

    public static WaterSource getByCode(Integer code) {
        if (code == null) return null;
        return map.get(code);
    };

    private final int code;

    WaterSource(int code) {
        this.code = code;
    }

    public int getCode() {
    return code;
}
}