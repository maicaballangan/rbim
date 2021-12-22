package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum YesOrNo {
    Yes(1),
    No(2),
    Undefined(99);

    private static final Map<Integer, YesOrNo> map;

    static {
        map = Arrays
                .stream(YesOrNo.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
    }

    public static YesOrNo getByCode(Integer code) {
        return map.get(code);
    }

    private final int code;

    YesOrNo(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}