package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum SchoolLevel {
    Undefined(99),
    Pre_School(0),
    Elementary(1),
    Junior_HS(2),
    Senior_HS(3),
    Vocational_or_Technical(4),
    College_or_University(5);

    private static final Map<Integer, SchoolLevel> map;

    static {
        map = Arrays
                .stream(SchoolLevel.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
    }

    public static SchoolLevel getByCode(Integer code) {
        if (code == null) return null;
        return map.get(code);
    }

    private final int code;

    SchoolLevel(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}