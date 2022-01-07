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

public enum GarbageDisposal {
    FEEDING_TO_ANIMALS(1),
    BURYING(2),
    COMPOSTING(3),
    BURNING(4),
    DUMPING_INDIVIDUAL_PIT(5),
    PICKED_UP_BY_GARBAGE_TRUCK(6),
    OTHERS(7);

    private static final Map<Integer, GarbageDisposal> map;

    static {
        map = Arrays
                .stream(GarbageDisposal.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, OTHERS);
    }

    public static GarbageDisposal getByCode(Integer code) {
        if (code == null || code == 99) return null;
        return map.get(code);
    }

    private final Integer code;

    GarbageDisposal(Integer code) {
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