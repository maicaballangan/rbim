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
    No_Walls(0, "No walls"),
    Makeshift_Salvaged_Improvised(1, "Makeshift/Salvaged/Improvised"),
    Glass(2, "Glass"),
    Asbestos(3, "Asbestos"),
    Bamboo_Sawali_Cogon_Nipa(4, "Bamboo/Sawali/Cogon/Nipa"),
    GalvanizedIron_Aluminum(5, "Galvanized Iron/Aluminum"),
    HalfConcrete_Brick_StoneAndHalfWood(6, "Half Concrete/Brick/Stone and Half Wood"),
    Wood(7, "Wood"),
    Concrete_Brick_Stone(8, "Concrete/Brick/Stone"),
    Others(9, "Other");

    private static final Map<Integer, BuildingMaterial> map;

    static {
        map = Arrays
                .stream(BuildingMaterial.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, Others);
    }

    public static BuildingMaterial getByCode(Integer code) {
        if (code == null) return null;
        return map.get(code);
    }

    private final int code;
    private final String description;

    BuildingMaterial(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return description;
    }
}