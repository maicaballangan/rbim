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

import utils.ExcelUtils;

public enum Skill {
    REFRIGERATION_AND_AIRCONDITIONING(1),
    AUTOMOTIVE_OR_HEAVY_EQUIPMENT_SERVICING(2),
    METAL_WORKER(3),
    BUILDING_WIRING_INSTALLATION(4),
    HEAVY_EQUIPMENT_OPERATION(5),
    PLUMBING(6),
    WELDING(7),
    CARPENTRY(8),
    BAKING(9),
    DRESSMAKING(10),
    LINGUIST(11),
    COMPUTER_GRAPHICS(12),
    PAINTING(13),
    BEAUTY_CARE(14),
    COMMERCIAL_COOKING(15),
    HOUSEKEEPING(16),
    MASSAGE_THERAPY(17),
    OTHERS(18);

    private static final Map<Integer, Skill> map;
    private static final Map<String, Skill> mapDesc;

    static {
        map = Arrays
                .stream(Skill.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, OTHERS);

        mapDesc = Arrays
                .stream(Skill.values())
                .collect(Collectors.toMap(e -> e.toString(), Function.identity()));
    }

    public static Skill getByCode(Integer code) {
        if (code == null || code == 99) return null;
        return map.get(code);
    }

    private final Integer code;

    Skill(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
            return code;
        }

    public static Skill getByCodeOrDescription(String value) {
        if (value == null || "99".equals(value) || "98".equals(value)) return null;
        if (mapDesc.containsKey(value)) {
            return mapDesc.get(value);
        } else if ("COMP. GRAPHICS".equals(value)) {
            return COMPUTER_GRAPHICS;
        } else {
            return getByCode(ExcelUtils.parseInt(value));
        }
    }

    @Override
    public String toString() {
        return name().replaceAll("_", " ");
    }
}