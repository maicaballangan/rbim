package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Facility {
    Government_Hospital(1),
    RHU_or_Health_Center(2),
    Brgy_Health_Station(3),
    Private_Hospital(4),
    Private_Clinic(5),
    Pharmacy(6),
    Hilot_or_Herbalist(7),
    Undefined(99);

    private static final Map<Integer, Facility> map;

    static {
        map = Arrays
                .stream(Facility.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
    }

    public static Facility getByCode(int code) {
        return map.get(code);
    }

    private final int code;

    Facility(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}