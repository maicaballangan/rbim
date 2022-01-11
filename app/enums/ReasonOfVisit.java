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

public enum ReasonOfVisit {
    SICK_OR_INJURED(1),
    PRENATAL_OR_POSTNATAL(2),
    GAVE_BIRTH(3),
    DENTAL(4),
    MEDICAL_CHECKUP(5),
    MEDICAL_REQUIREMENT(6),
    NHTS_CCT_4PS_REQUIREMENT(7),
    OTHERS(8);

    private static final Map<Integer, ReasonOfVisit> map;

    static {
        map = Arrays
                .stream(ReasonOfVisit.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, OTHERS);
    }

    public static ReasonOfVisit getByCode(Integer code) {
        if (code == null || code == 99) return null;
        return map.get(code);
    }

    private final Integer code;

    ReasonOfVisit(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String toString() {
        return name().replaceAll("_", " ");
    }
}