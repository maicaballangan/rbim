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
    NONE(0),
    WOOD(1),
    CHARCOAL(2),
    LPG(3),
    KEROSENE(4),
    ELECTRICITY(5),
    OTHERS(6);

    private static final Map<Integer, CookingFuel> map;

    static {
        map = Arrays
                .stream(CookingFuel.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, OTHERS);
    }

    public static CookingFuel getByCode(Integer code) {
        if (code == null || code == 99) return null;
        return map.get(code);
    }

    private final Integer code;

    CookingFuel(Integer code) {
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