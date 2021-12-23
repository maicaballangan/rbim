package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ReasonOfVisit {
    Sick_or_Injured(1),
    Prenatal_or_Postnatal(2),
    Gave_Birth(3),
    Dental(4),
    Medical_Checkup(5),
    Medical_Requirement(6),
    NHTS_CCT_4Ps_Requirement(7),
    Undefined(99);

    private static final Map<Integer, ReasonOfVisit> map;

    static {
        map = Arrays
                .stream(ReasonOfVisit.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
    }

    public static ReasonOfVisit getByCode(int code) {
        return map.get(code);
    }

    private final int code;

    ReasonOfVisit(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}