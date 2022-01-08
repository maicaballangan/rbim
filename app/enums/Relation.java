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

public enum Relation {
    HEAD(1),
    SPOUSE(2),
    SON(3),
    DAUGHTER(4),
    STEPSON(5),
    STEPDAUGHTER(6),
    SON_IN_LAW(7),
    DAUGHTER_IN_LAW(8),
    GRANDSON(9),
    GRANDDAUGHTER(10),
    FATHER(11),
    MOTHER(12),
    BROTHER(13),
    SISTER(14),
    UNCLE(15),
    AUNT(16),
    NEPHEW(17),
    NIECE(18),
    OTHER_RELATIVE(19),
    NONRELATIVE(20),
    BOARDER(21),
    DOMESTIC_HELPER(22);

    private static final Map<Integer, Relation> map;

    static {
        map = Arrays
                .stream(Relation.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
    }

    public static Relation getByCode(Integer code) {
        if (code == null || code == 99) return null;
        return map.get(code);
    }

    private final Integer code;

    Relation(Integer code) {
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