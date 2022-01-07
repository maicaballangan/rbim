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

public enum ResidentType {
    NonMigrant(1),
    Migrant(2),
    Transient(3);

    private static final Map<Integer, ResidentType> map;

    static {
        map = Arrays
                .stream(ResidentType.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
    }

    public static ResidentType getByCode(Integer code) {
        if (code == null) return null;
        return map.get(code);
    }

    private final int code;

    ResidentType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}