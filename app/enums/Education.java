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

public enum Education {
    NONE(00),
    PRESCHOOL(1),
    ELEMENTARY(2),
    ELEMENTARY_GRAD(3),
    HIGHSCHOOL(4),
    HIGHSCHOOL_GRAD(5),
    JUNIOR_HS(6),
    JUNIOR_HS_GRAD(7),
    SENIOR_HS(8),
    SENIOR_HS_GRAD(9),
    VOCATIONAL(10),
    COLLEGE(11),
    COLLEGE_GRAD(12),
    POST_GRAD(13);

    private static final Map<Integer, Education> map;

    static {
        map = Arrays
                .stream(Education.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, null);
    }

    public static Education getByCode(Integer code) {
        if (code == null || code == 99) return null;
        return map.get(code);
    }

    private final Integer code;

    Education(Integer code) {
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
