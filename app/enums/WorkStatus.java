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

public enum WorkStatus {
    PERMANENT(1),
    CASUAL(2),
    CONTRACTUAL(3),
    INDIVIDUALLY_OWNED_BUSINESS(4),
    SHARED_OR_PARTNERSHIP_BUSINESS(5),
    CORPORATE_BUSINESS(6);

    private static final Map<Integer, WorkStatus> map;

    static {
        map = Arrays
                .stream(WorkStatus.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, null);
    }

    public static WorkStatus getByCode(Integer code) {
        if (code == null || code == 99) return null;
        return map.get(code);
    }

    private final Integer code;

    WorkStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String toString() {
        return name().replaceAll("_", " ");
    }
}