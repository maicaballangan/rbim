package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum CivilStatus {
    Single(1),
    Married(2),
    LivingIn(3),
    Widowed(4),
    Separated(5),
    Divorced(6),
    Undefined(7);

    private static final Map<Integer, CivilStatus> map;

    static {
        map = Arrays
                .stream(CivilStatus.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
    }

    public static CivilStatus getByCode(Integer code) {
        return map.get(code);
    }

    private final int code;

    CivilStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}