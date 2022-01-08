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
    PHILHEALTH_PAYING_MEMBER(1),
    PHILHEALTH_DEPENDENT_OF_PAYING_MEMBER(2),
    PHILHEALTH_INDIGENT_MEMBER(3),
    PHILHEALTH_DEPENDENT_OF_INDIGENT_MEMBER(4),
    GSIS(5),
    SSS(6),
    PRIVATE_OR_HMO(7),
    OTHERS(8);

    private static final Map<Integer, HealthInsurance> map;

    static {
        map = Arrays
                .stream(HealthInsurance.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, OTHERS);
    }

    public static HealthInsurance getByCode(Integer code) {
        if (code == null || code == 99) return null;
        return map.get(code);
    };

    private final Integer code;

    HealthInsurance(Integer code) {
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