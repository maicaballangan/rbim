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

public enum SchoolLevel {
    PRE_SCHOOL(0),
    ELEMENTARY(1),
    JUNIOR_HS(2),
    SENIOR_HS(3),
    VOCATIONAL_OR_TECHNICAL(4),
    COLLEGE_OR_UNIVERSITY(5);

    private static final Map<Integer, SchoolLevel> map;

    static {
        map = Arrays
                .stream(SchoolLevel.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
    }

    public static SchoolLevel getByCode(Integer code) {
        if (code == null || code == 99) return null;
        return map.get(code);
    }

    private final Integer code;

    SchoolLevel(Integer code) {
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