package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum YesOrNo {
    Undefined(99),
    Yes(1),
    No(2);

    private static final Map<Integer, YesOrNo> map;

    static {
        map = Arrays
                .stream(YesOrNo.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, Undefined);
    }

    public static YesOrNo getByCode(Integer code) {
        if (code == null) return null;
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