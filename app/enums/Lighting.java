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

public enum Lighting {
    None(0),
    Oil_from_animals_or_vegetables (1),
    LPG(2),
    Kerosene(3),
    Electricity(4),
    Others(5);

    private static final Map<Integer, Lighting> map;

    static {
        map = Arrays
                .stream(Lighting.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, Others);
    }

    public static Lighting getByCode(Integer code) {
        if (code == null) return null;
        return map.get(code);
};

    private final int code;

    Lighting(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}