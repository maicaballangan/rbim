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

public enum CookingFuel {
    None(0),
    Wood(1),
    Charcoal(2),
    LPG(3),
    Kerosene(4),
    Electricity(5),
    Others(6);

    private static final Map<Integer, CookingFuel> map;

    static {
        map = Arrays
                .stream(CookingFuel.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, Others);
    }

    public static CookingFuel getByCode(Integer code) {
        if (code == null) return null;
        return map.get(code);
    }

    private final int code;

    CookingFuel(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}