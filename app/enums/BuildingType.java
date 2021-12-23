package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum BuildingType {
    Single(1, "Single"),
    Duplex(2, "Duplex"),
    MultiUnitResidential(3, "Multi-unit Residential"),
    CommercialIndustrialAgricultural(4, "Commercial/Unit/Agricultural"),
    InstitutionalLivingQuarter(5, "Institutional Living Quarter (Hotel, Hospital)"),
    Other(6, "Other housing units (boat, cave, others)");

    private static final Map<Integer, BuildingType> map;

    static {
        map = Arrays
                .stream(BuildingType.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
    }

    public static BuildingType getByCode(Integer code) {
        return map.get(code);
    };

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