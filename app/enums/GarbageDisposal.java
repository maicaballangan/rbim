package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum GarbageDisposal {
    Feeding_to_animals(1),
    Burying(2),
    Composting(3),
    Burning(4),
    Dumping_individual_pit(5),
    Picked_up_by_garbage_truck(6);
    private static final Map<Integer, GarbageDisposal> map;

    static {
        map = Arrays
                .stream(GarbageDisposal.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
    }

    public static GarbageDisposal getByCode(int code) {
        return map.get(code);
    };

    private final int code;

    GarbageDisposal(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}