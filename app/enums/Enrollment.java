package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Enrollment {
    PUBLIC(1),
    PRIVATE(2),
    UNENROLLED(99); // TODO ask why 3 here but excel is 99

    private static final Map<Integer, Enrollment> map;

    static {
        map = Arrays
                .stream(Enrollment.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
    }

    public static Enrollment getByCode(Integer code) {
        return map.get(code);
    }

    private final int code;

    Enrollment(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}