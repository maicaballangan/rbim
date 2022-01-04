package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ParentalStatus {
    Undefined(99),
    Registered_Solo_Parent(1),
    Non_Solo_Parent(2),
    Unregistered_Solo_Parent(3);

    private static final Map<Integer, ParentalStatus> map;

    static {
        map = Arrays
                .stream(ParentalStatus.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, Undefined);
    }

    public static ParentalStatus getByCode(Integer code) {
        if (code == null) return null;
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