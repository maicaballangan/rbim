package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Sex {
    Male(1),
    Female(2);

    private static final Map<Integer, Sex> map;

    static {
        map = Arrays
                .stream(Sex.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
    }

    public static Sex getByCode(Integer code) {
        return map.get(code);
    };

    private final int code;

    Sex(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}