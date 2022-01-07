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
    Feeding_to_animals(1),
    Burying(2),
    Composting(3),
    Burning(4),
    Dumping_individual_pit(5),
    Picked_up_by_garbage_truck(6),
    Others(7);

    private static final Map<Integer, GarbageDisposal> map;

    static {
        map = Arrays
                .stream(GarbageDisposal.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, Others);
    }

    public static GarbageDisposal getByCode(Integer code) {
        if (code == null) return null;
        return map.get(code);
    };

    private final int code;

    GarbageDisposal(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}