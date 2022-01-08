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

public enum FPMethod {
    NONE(00),
    FEMALE_STERILIZATION_OR_LIGATION(1),
    MALE_STERILIZATION_OR_VASECTOMY(2),
    IUD(3),
    INJECTABLES(4),
    IMPLANTS(5),
    PILL(6),
    CONDOM(7),
    MODERN_NATURAL_FP(8),
    LACTATIONAL_AMENORRHEA_METHOD(9),
    TRADITIONAL(10),
    OTHERS(11);

    private static final Map<Integer, FPMethod> map;

    static {
        map = Arrays
                .stream(FPMethod.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, OTHERS);
    }

    public static FPMethod getByCode(Integer code) {
        if (code == null || code == 99) return null;
        return map.get(code);
    }

    private final Integer code;

    FPMethod(Integer code) {
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