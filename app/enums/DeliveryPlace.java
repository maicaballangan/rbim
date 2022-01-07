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
    Undefined(99),
    Public_Hospital(1),
    Private_Hospital(2),
    Lying_in_Clinic(3),
    Home(4),
    Others(5);

    private static final Map<Integer, DeliveryPlace> map;

    static {
        map = Arrays
                .stream(DeliveryPlace.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, Others);
    }

    public static DeliveryPlace getByCode(Integer code) {
        if (code == null) return null;
        return map.get(code);
    }

    private final int code;

    DeliveryPlace(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}