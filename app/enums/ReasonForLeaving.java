package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ReasonForLeaving {
    Lack_of_Employment(1),
    Perception_of_better_income_in_other_place(2),
    Schooling(3),
    Presence_of_relatives_and_friends_in_other_place(4),
    Employment_or_Job_Relocation(5),
    Disaster_related_Relocation(6),
    Retirement(7),
    To_live_with_parents(8),
    To_live_with_children(9),
    Marriage(10),
    Annulment_Divorce_Separation(11),
    Commuting_related_Reasons(12),
    Health_related_Reasons(13),
    Peacde_and_Security(14),
    Others(15),
    Undefined(99);

    private static final Map<Integer, ReasonForLeaving> map;

    static {
        map = Arrays
                .stream(ReasonForLeaving.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
    }

    public static ReasonForLeaving getByCode(Integer code) {
        return map.get(code);
    }

    private final int code;

    ReasonForLeaving(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}