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

public enum DeliveryPlace {
    PUBLIC_HOSPITAL(1),
    PRIVATE_HOSPITAL(2),
    LYING_IN_CLINIC(3),
    HOME(4),
    OTHERS(5);

    private static final Map<Integer, DeliveryPlace> map;

    static {
        map = Arrays
                .stream(DeliveryPlace.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, OTHERS);
    }

    public static DeliveryPlace getByCode(Integer code) {
        if (code == null || code == 99) return null;
        return map.get(code);
    }

    private final Integer code;

    DeliveryPlace(Integer code) {
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