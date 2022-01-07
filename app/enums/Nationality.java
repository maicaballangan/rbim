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

public enum Nationality {
    Filipino(1),
    Non_Filipino(2);

    private static final Map<Integer, Nationality> map;

    static {
        map = Arrays
                .stream(Nationality.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
    }

    public static Nationality getByCode(Integer code) {
        if (code == null) return null;
        return map.get(code);
    }

    private final int code;

    Nationality(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}