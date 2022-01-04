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

public enum Attendant {
    Undefined(99),
    Doctor(1),
    Nurse(2),
    Midwife(3),
    Hilot(4);

    private static final Map<Integer, Attendant> map;

    static {
        map = Arrays
                .stream(Attendant.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, Undefined);
    }

    public static Attendant getByCode(Integer code) {
        if (code == null) return null;
        return map.get(code);
    }

    private final int code;

    Attendant(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}