package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Education {
    None(00),
    PreSchool(1),
    Elementary(2),
    Elementary_Graduate(3),
    HighSchool(4),
    HighSchool_Graduate(5),
    Junior_HS(6),
    Junior_HS_graduate(7),
    Senior_HS(8),
    Senior_HS_graduate(9),
    Vocational_or_Tech(10),
    College(11),
    College_Graduate(12),
    Post_Graduate(13);

    private static final Map<Integer, Education> map;

    static {
    map = Arrays
            .stream(Education.values())
            .collect(Collectors.toMap(e -> e.code, Function.identity()));
    }

    public static Education getByCode(int code) {
    return map.get(code);
    }

    private final int code;

    Education(int code) {
    this.code = code;
    }

    public int getCode() {
            return code;
        }
}
