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

public enum CivilStatus {
    SINGLE(1),
    MARRIED(2),
    LIVING_IN(3),
    WIDOWED(4),
    SEPARATED(5),
    DIVORCED(6);

    private static final Map<Integer, CivilStatus> map;

    static {
        map = Arrays
                .stream(CivilStatus.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, null);
    }

    public static CivilStatus getByCode(Integer code) {
        if (code == null || code == 99) return null;
        return map.get(code);
    }

    private final Integer code;

    CivilStatus(Integer code) {
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