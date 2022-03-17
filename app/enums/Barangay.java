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
    AMLAO("AMLAO"),
    APPAS("APPAS"),
    BADO_DANGWA("BADO DANGWA"),
    BAGUMBAYAN("BAGUMBAYAN"),
    BALAWAG("BALAWAG"),
    BALONG("BALONG"),
    BANTAY("BANTAY"),
    BULANAO_CENTRO("BULANAO CENTRO"),
    BULANAO_NORTE("BULANAO NORTE"),
    BULO("BULO"),
    CABARITAN("CABARITAN"),
    CABARUAN("CABARUAN"),
    CALACCAD("CALACCAD"),
    CALANAN("CALANAN"),
    CASIGAYAN("CASIGAYAN"),
    CUDAL("CUDAL"),
    DAGUPAN_CENTRO("DAGUPAN CENTRO"),
    DILAG("DILAG"),
    DUPAG("DUPAG"),
    GOBGOB("GOBGOB"),
    GUILAYON("GUILAYON"),
    IPIL("IPIL"),
    LACNOG("LACNOG"),
    LACNOG_WEST("LACNOG WEST"),
    LANNA("LANNA"),
    LAYA_EAST("LAYA EAST"),
    LAYA_WEST("LAYA WEST"),
    LUCOG("LUCOG"),
    MAGNAO("MAGNAO"),
    MAGSAYSAY("MAGSAYSAY"),
    MALALAO("MALALAO"),
    MALINAWA("MALIN-AWA"),
    MASABLANG("MASABLANG"),
    NAMBARAN("NAMBARAN"),
    NAMBUCAYAN("NAMBUCAYAN"),
    NANENG("NANENG"),
    NEW_TANGLAG("NEW TANGLAG"),
    SAN_JUAN("SAN JUAN"),
    SAN_JULIAN("SAN JULIAN"),
    SUYANG("SUYANG"),
    TUGA("TUGA"),
    OTHER("OTHER");

    private static final Map<String, Barangay> map;

    static {
        map = Arrays.stream(Barangay
                .values())
                .collect(Collectors.toMap(e -> e.description, Function.identity()));
        map.put("DC", Barangay.DAGUPAN_CENTRO);
    }

    public static Barangay getByDescription(String description) {
        if (description == null || "99".equals(description) || "98".equals(description)) return null;
        return map.containsKey(description) ? map.get(description) : OTHER;
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