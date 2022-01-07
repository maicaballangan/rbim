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

public enum Ownership {
    RENT_FREE_WITHOUT_CONSENT_OF_OWNER(1),
    RENT_FREE_WITH_CONSENT_OF_OWNER(2),
    RENTED(3),
    OWNED_OR_BEING_AMORTIZED(4);

    private static final Map<Integer, Ownership> map;

    static {
        map = Arrays
                .stream(Ownership.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, null);
    }

    public static Ownership getByCode(Integer code) {
        if (code == null || code == 99) return null;
        return map.get(code);
    }

    private final Integer code;

    Ownership(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + " - " + name().replaceAll("_", " ");
    }
}