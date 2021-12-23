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
    }

    public static WaterSource getByCode(Integer code) {
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