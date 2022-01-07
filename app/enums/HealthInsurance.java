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

public enum HealthInsurance {
    Undefined(99),
    Philhealth_Paying_Member(1),
    Philhealth_Dependent_of_Paying_Member(2),
    Philhealth_Indigent_Member(3),
    Philhealth_Dependent_of_Indigent_Member(4),
    GSIS(5),
    SSS(6),
    Private_or_HMO(7),
    Others(8);

    private static final Map<Integer, HealthInsurance> map;

    static {
        map = Arrays
                .stream(HealthInsurance.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, Others);
    }

    public static HealthInsurance getByCode(Integer code) {
        if (code == null) return null;
        return map.get(code);
    };

    private final int code;

    HealthInsurance(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}