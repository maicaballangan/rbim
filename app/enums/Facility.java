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

public enum Facility {
    Undefined(99),
    Government_Hospital(1),
    RHU_or_Health_Center(2),
    Brgy_Health_Station(3),
    Private_Hospital(4),
    Private_Clinic(5),
    Pharmacy(6),
    Hilot_or_Herbalist(7);

    private static final Map<Integer, Facility> map;

    static {
        map = Arrays
                .stream(Facility.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, Undefined);
    }

    public static Facility getByCode(Integer code) {
        if (code == null) return null;
        return map.get(code);
    }

    private final int code;

    Facility(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}