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
    Undefined(99),
    Sick_or_Injured(1),
    Prenatal_or_Postnatal(2),
    Gave_Birth(3),
    Dental(4),
    Medical_Checkup(5),
    Medical_Requirement(6),
    NHTS_CCT_4Ps_Requirement(7),
    Others(8);

    private static final Map<Integer, ReasonOfVisit> map;

    static {
        map = Arrays
                .stream(ReasonOfVisit.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, Others);
    }

    public static ReasonOfVisit getByCode(Integer code) {
        if (code == null) return null;
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