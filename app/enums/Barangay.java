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

/**
 * @author Maica Ballangan
 * @since v1
 */
public enum Barangay {
    AGBANNAWAG("AGBANNAWAG"),
    APPAS("APPAS"),
    BALONG("BALONG"),
    CABARUAN("CABARUAN"),
    CASIGAYAN("CASIGAYAN"),
    DAGUPAN_CENTRO("DAGUPAN CENTRO"),
    DAGUPAN_WESTE("DAGUPAN WESTE"),
    DILAG("DILAG"),
    IPIL("IPIL"),
    LAYA_EAST("LAYA EAST"),
    LAYA_WEST("LAYA WEST"),
    MAGSAYSAY("MAGSAYSAY");;

    private static final Map<String, Barangay> map;

    static {
        map = Arrays.stream(Barangay
                .values())
                .collect(Collectors.toMap(e -> e.description, Function.identity()));
        map.put("DC", Barangay.DAGUPAN_CENTRO);
    }

    public static Barangay getByDescription(String description) {
        if (description == null || "99".equals(description) || "98".equals(description)) return null;
        return map.get(description);
    }

    private String description;

    Barangay(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}