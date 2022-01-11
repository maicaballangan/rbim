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

public enum ReasonForTransfer {
    AVAILABILITY_OF_JOBS(1),
    HIGHER_WAGE(2),
    PRESENCE_OF_SCHOOLS_OR_UNIVERSITIES(3),
    PRESENCE_OF_RELATIVES_AND_FRIENDS_IN_OTHER_PLACE(4),
    HOUSING(5),
    OTHERS(6);

    private static final Map<Integer, ReasonForTransfer> map;

    static {
        map = Arrays
                .stream(ReasonForTransfer.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, OTHERS);
    }

    public static ReasonForTransfer getByCode(Integer code) {
        if (code == null || code == 99) return null;
        return map.get(code);
    }

    private final Integer code;

    ReasonForTransfer(Integer code) {
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