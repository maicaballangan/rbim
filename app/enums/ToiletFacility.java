package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ToiletFacility {
    None(0),
    Open_pit(1),
    Close_pit(2),
    Water_sealed_shared_depository(3),
    Water_sealed_exclusive_depository(4),
    Water_sealed_shared_sewer_septic_tank(5),
    Water_sealed_exclusive_sewer_septic_tank(6),
    Others(7);

    private static final Map<Integer, ToiletFacility> map;

    static {
        map = Arrays
                .stream(ToiletFacility.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
    }

    public static ToiletFacility getByCode(Integer code) {
        return map.get(code);
    };

    private final int code;

    ToiletFacility(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}