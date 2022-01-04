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
    Undefined("98"),
    Agbannawag("AGBANNAWAG"),
    Appas("APPAS"),
    Balong("BALONG"),
    Cabaruan("CABARUAN"),
    Casigayan("CASIGAYAN"),
    Dagupan_Centro("DAGUPAN CENTRO"),
    Dagupan_Weste("DAGUPAN WESTE"),
    Dilag("DILAG"),
    Ipil("IPIL"),
    Laya_East("LAYA EAST"),
    Laya_West("LAYA WEST"),
    Magsaysay("MAGSAYSAY");

    private static final Map<String, Barangay> map;

    static {
        map = Arrays.stream(Barangay
                .values())
                .collect(Collectors.toMap(e -> e.description, Function.identity()));

        map.put("99", Undefined);
        map.put("DC", Barangay.Dagupan_Centro);
    }

    public static Barangay getByDescription(String description) {
        if (description == null) return null;
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