package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum BuildingType {
    Single(1, "Single"),
    Duplex(2, "Duplex"),
    MultiUnit_Residential(3, "Multi-unit Residential"),
    Commercial_Industrial_Agricultural(4, "Commercial/Unit/Agricultural"),
    Institutional_Living_Quarter(5, "Institutional Living Quarter (Hotel, Hospital)"),
    Others(6, "Other housing units (boat, cave, others)");

    private static final Map<Integer, BuildingType> map;

    static {
        map = Arrays
                .stream(BuildingType.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, Others);
    }

    public static BuildingType getByCode(Integer code) {
        if (code == null) return null;
        return map.get(code);
    }

    private final int code;
    private final String description;

    BuildingType(int code, String description) {
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