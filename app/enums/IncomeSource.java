package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum IncomeSource {
    Undefined(99),
    Employment(1),
    Business(2),
    Remittance(3),
    Investments(4),
    Others(5);

    private static final Map<Integer, IncomeSource> map;

    static {
        map = Arrays
                .stream(IncomeSource.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, Others);
    }

    public static IncomeSource getByCode(Integer code) {
        if (code == null) return null;
        return map.get(code);
    }

    private final int code;

    IncomeSource(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}