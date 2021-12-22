package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum HealthInsurance {
    Philhealth_Paying_Member(1),
    Philhealth_Dependent_of_Paying_Member(2),
    Philhealth_Indigent_Member(3),
    Philhealth_Dependent_of_Indigent_Member(4),
    GSIS(5),
    SSS(6),
    Private_or_HMO(7),
    Undefined(99);

    private static final Map<Integer, HealthInsurance> map;

    static {
        map = Arrays
                .stream(HealthInsurance.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
    }

    public static HealthInsurance getByCode(Integer code) {
        return map.get(code);
    };

    private final int code;

    HealthInsurance(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}