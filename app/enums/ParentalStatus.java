package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ParentalStatus {
    Registered_Solo_Parent(1),
    Non_Solo_Parent(2),
    Unregistered_Solo_Parent(3),
    Undefined(99);

    private static final Map<Integer, ParentalStatus> map;

    static {
        map = Arrays
                .stream(ParentalStatus.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
    }

    public static ParentalStatus getByCode(int code) {
        return map.get(code);
    }

    private final int code;

    ParentalStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}