package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Enrollment {
    PUBLIC(1),
    PRIVATE(2),
    NONE(3);

    private static final Map<Integer, Enrollment> map;

    static {
        map = Arrays
                .stream(Enrollment.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, null);
    }

    public static Enrollment getByCode(Integer code) {
        if (code == null || code == 99) return null;
        return map.get(code);
    }

    private final Integer code;

    Enrollment(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String toString() {
        return name().replaceAll("_", " ").replaceAll("_", " ");
    }
}