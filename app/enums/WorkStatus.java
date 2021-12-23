package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum WorkStatus {
    Permanent(1),
    Casual(2),
    Contractual(3),
    Individually_Owned_Business(4),
    Shared_or_Partnership_Business(5),
    Corporate_Business(6),
    Undefined(99);

    private static final Map<Integer, WorkStatus> map;

    static {
        map = Arrays
                .stream(WorkStatus.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
    }

    public static WorkStatus getByCode(int code) {
        return map.get(code);
    }

    private final int code;

    WorkStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}