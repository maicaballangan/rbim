/* Copyright (C) 2022 Jamaica Ballangan - All Rights Reserved
 * Clients may use and modify this code under the
 * terms and agreement only. Selling or distribution is prohibited
 * without the consent of the author
 */
package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ReasonForLeaving {
    LACK_OF_EMPLOYMENT(1),
    PERCEPTION_OF_BETTER_INCOME_IN_OTHER_PLACE(2),
    SCHOOLING(3),
    PRESENCE_OF_RELATIVES_AND_FRIENDS_IN_OTHER_PLACE(4),
    EMPLOYMENT_OR_JOB_RELOCATION(5),
    DISASTER_RELATED_RELOCATION(6),
    RETIREMENT(7),
    TO_LIVE_WITH_PARENTS(8),
    TO_LIVE_WITH_CHILDREN(9),
    MARRIAGE(10),
    ANNULMENT_DIVORCE_SEPARATION(11),
    COMMUTING_RELATED_REASONS(12),
    HEALTH_RELATED_REASONS(13),
    PEACE_AND_SECURITY(14),
    OTHERS(15);

    private static final Map<Integer, ReasonForLeaving> map;

    static {
        map = Arrays
                .stream(ReasonForLeaving.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, OTHERS);
    }

    public static ReasonForLeaving getByCode(Integer code) {
        if (code == null || code == 99) return null;
        return map.get(code);
    }

    private final Integer code;

    ReasonForLeaving(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + " - " + name().replaceAll("_", " ");
    }
}