package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum FPMethod {
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
    None(00),
    Undefined(99);

    private static final Map<Integer, FPMethod> map;

    static {
        map = Arrays
                .stream(FPMethod.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
    }

    public static FPMethod getByCode(int code) {
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