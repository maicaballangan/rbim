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

public enum BuildingMaterial {
    NO_WALLS(0, "NO WALLS"),
    MAKESHIFT_SALVAGED_IMPROVISED(1, "MAKESHIFT/SALVAGED/IMPROVISED"),
    GLASS(2, "GLASS"),
    ASBESTOS(3, "ASBESTOS"),
    BAMBOO_SAWALI_COGON_NIPA(4, "BAMBOO/SAWALI/COGON/NIPA"),
    GALVANIZEDIRON_ALUMINUM(5, "GALVANIZED IRON/ALUMINUM"),
    HALFCONCRETE_BRICK_STONEANDHALFWOOD(6, "HALF CONCRETE/BRICK/STONE AND HALF WOOD"),
    WOOD(7, "WOOD"),
    CONCRETE_BRICK_STONE(8, "CONCRETE/BRICK/STONE"),
    OTHERS(9, "OTHER");;

    private static final Map<Integer, BuildingMaterial> map;

    static {
        map = Arrays
                .stream(BuildingMaterial.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, OTHERS);
    }

    public static BuildingMaterial getByCode(Integer code) {
        if (code == null || code == 99) return null;
        return map.get(code);
    }

    private final Integer code;
    private final String description;

    BuildingMaterial(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + " - " + description;
    }
}