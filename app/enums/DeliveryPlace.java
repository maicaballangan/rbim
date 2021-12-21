package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum DeliveryPlace {
    Public_Hospital(1),
    Private_Hospital(2),
    Lying_in_Clinic(3),
    Home(4),
    Undefined(99);

    private static final Map<Integer, DeliveryPlace> map;

    static {
        map = Arrays
                .stream(DeliveryPlace.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
    }

    public static DeliveryPlace getByCode(int code) {
        return map.get(code);
    };

    private final int code;

    DeliveryPlace(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}