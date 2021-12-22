package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum CookingFuel {
    None(0),
    Wood(1),
    Charcoal(2),
    LPG(3),
    Kerosene(4),
    Electricity(5),
    Others(6);
    private static final Map<Integer, CookingFuel> map;

    static {
        map = Arrays
                .stream(CookingFuel.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
    }

    public static CookingFuel getByCode(Integer code) {
        return map.get(code);
    };

    private final int code;

    CookingFuel(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}