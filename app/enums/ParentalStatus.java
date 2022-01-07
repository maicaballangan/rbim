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

public enum ParentalStatus {
    REGISTERED_SOLO_PARENT(1),
    NON_SOLO_PARENT(2),
    UNREGISTERED_SOLO_PARENT(3);

    private static final Map<Integer, ParentalStatus> map;

    static {
        map = Arrays
                .stream(ParentalStatus.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, null);
    }

    public static ParentalStatus getByCode(Integer code) {
        if (code == null || code == 99) return null;
        return map.get(code);
    }

    private final Integer code;

    ParentalStatus(Integer code) {
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