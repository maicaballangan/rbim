package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum SourceOfFP {
    Undefined(99),
    Government_Hospital(1),
    RHU_or_Health_Center(2),
    Brgy_Health_Station(3),
    Private_Hospital(4),
    Pharmacy(5),
    Others(6);

    private static final Map<Integer, SourceOfFP> map;

    static {
        map = Arrays
                .stream(SourceOfFP.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, Others);
    }

    public static SourceOfFP getByCode(Integer code) {
        if (code == null) return null;
        return map.get(code);
    }

    private final int code;

    SourceOfFP(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}