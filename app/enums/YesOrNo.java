/* Copyright (C) 2022 Jamaica Ballangan - All Rights Reserved
 * Clients may use and modify this code under the
 * terms and agreement only. Selling or distribution is prohibited
 * without the consent of the author
 */
package enums;

import utils.ExcelUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum YesOrNo {
    YES(1),
    NO(2);

    private static final Map<Integer, YesOrNo> map;
    private static final Map<String, YesOrNo> mapDesc;

    static {
        map = Arrays
                .stream(YesOrNo.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, null);

        mapDesc = Arrays
                .stream(YesOrNo.values())
                .collect(Collectors.toMap(e -> e.toString(), Function.identity()));
    }

    public static YesOrNo getByCode(Integer code) {
        if (code == null || code == 99) return null;
        return map.get(code);
    }

    private final Integer code;

    YesOrNo(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static YesOrNo getByCodeOrDescription(String value) {
        if (value == null || "99".equals(value) || "98".equals(value)) return null;
        if (mapDesc.containsKey(value)) {
            return YesOrNo.valueOf(value);
        } else {
            return getByCode(ExcelUtils.parseInt(value));
        }
    }

    @Override
    public String toString() {
        return name().replaceAll("_", " ");
    }
}