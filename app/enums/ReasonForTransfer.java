package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ReasonForTransfer {
    Undefined(99),
    Availability_of_Jobs(1),
    Higher_wage(2),
    Presence_of_schools_or_Universities(3),
    Presence_of_relatives_and_friends_in_other_place(4),
    Housing(5),
    Others(6);

    private static final Map<Integer, ReasonForTransfer> map;

    static {
        map = Arrays
                .stream(ReasonForTransfer.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, Others);
    }

    public static ReasonForTransfer getByCode(Integer code) {
        if (code == null) return null;
        return map.get(code);
    }


    private final int code;

    ReasonForTransfer(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}