package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ReasonForTransfer {
    Availability_of_Jobs(1),
    Higher_wage(2),
    Presence_of_schools_or_Universities(3),
    Presence_of_relatives_and_friends_in_other_place(4),
    Housing(5),
    Undefined(99);

    private static final Map<Integer, ReasonForTransfer> map;

    static {
        map = Arrays
                .stream(ReasonForTransfer.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
    }

    public static ReasonForTransfer getByCode(Integer code) {
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