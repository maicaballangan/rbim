package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Sex {
    Male(1),
    Female(2),
    Others(3);

    private static final Map<Integer, Sex> map;

    static {
        map = Arrays
                .stream(Sex.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, Others);
    }

    public static Sex getByCode(Integer code) {
        if (code == null) return null;
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