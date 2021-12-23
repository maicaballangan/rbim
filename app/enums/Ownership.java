package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Ownership {
    Rent_free_without_consent_of_owner(1),
    Rent_free_with_consent_of_owner(2),
    Rented(3),
    Owned_or_being_Amortized(4);

    private static final Map<Integer, Ownership> map;

    static {
        map = Arrays
                .stream(Ownership.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
    }

    public static Ownership getByCode(int code) {
        return map.get(code);
    };

    private final int code;

    Ownership(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}