package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum BuildingMaterial {
    NoWalls(0, "No walls"),
    MakeshiftSalvagedImprovised(1, "Makeshift/Salvaged/Improvised"),
    Glass(2, "Glass"),
    Asbestos(3, "Asbestos"),
    BambooSawaliCogonNipa(4, "Bamboo/Sawali/Cogon/Nipa"),
    GalvanizedIronAluminum(5, "Galvanized Iron/Aluminum"),
    HalfConcreteBrickStoneHalfWood(6, "Half Concrete/Brick/Stone and Half Wood"),
    Wood(7, "Wood"),
    ConcreteBrickStone(8, "Concrete/Brick/Stone"),
    Other(9, "Other");

    private static final Map<Integer, BuildingMaterial> map;

    static {
        map = Arrays
                .stream(BuildingMaterial.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
    }

    public static BuildingMaterial getByCode(Integer code) {
        return map.get(code);
    };

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