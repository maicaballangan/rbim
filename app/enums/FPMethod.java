package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum FPMethod {
    Undefined(99),
    None(00),
    Female_Sterilization_or_Ligation(1),
    Male_Sterilization_or_Vasectomy(2),
    IUD(3),
    InjectAbles(4),
    Implants(5),
    Pill(6),
    Condom(7),
    Modern_Natural_FP(8),
    Lactational_Amenorrhea_Method(9),
    Traditional(10),
    Others(11);

    private static final Map<Integer, FPMethod> map;

    static {
        map = Arrays
                .stream(FPMethod.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, Others);
    }

    public static FPMethod getByCode(Integer code) {
        if (code == null) return null;
        return map.get(code);
    }

    private final int code;

    FPMethod(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}