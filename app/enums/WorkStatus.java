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
    Undefined(99),
    Permanent(1),
    Casual(2),
    Contractual(3),
    Individually_Owned_Business(4),
    Shared_or_Partnership_Business(5),
    Corporate_Business(6);

    private static final Map<Integer, WorkStatus> map;

    static {
        map = Arrays
                .stream(WorkStatus.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
        map.put(-1, Undefined);
    }

    public static WorkStatus getByCode(Integer code) {
        if (code == null) return null;
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