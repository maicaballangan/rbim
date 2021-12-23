package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Attendant {
    Doctor(1),
    Nurse(2),
    Midwife(3),
    Hilot(4),
    Undefined(99);

    private static final Map<Integer, Attendant> map;

    static {
        map = Arrays
                .stream(Attendant.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
    }

    public static Attendant getByCode(int code) {
        return map.get(code);
    }

    private final int code;

    Attendant(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}