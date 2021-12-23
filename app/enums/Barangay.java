package enums;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Maica Ballangan
 * @since v1
 */
public enum Barangay {
    Undefined98("98"),
    Agbannawag("Agbannawag"),
    Appas("Appas"),
    Balong("Balong"),
    Cabaruan("Cabaruan"),
    Casigayan("Casigayan"),
    Dagupan_Centro("Dagupan Centro"),
    Dagupan_Weste("Dagupan Weste"),
    Dilag("Dilag"),
    Ipil("Ipil"),
    Laya_East("Laya East"),
    Laya_West("Laya West"),
    Magsaysay("Magsaysay"),
    Undefined("99");

    private static final Map<String, Barangay> map;

    static {
        map = Arrays.stream(Barangay
                .values())
                .collect(Collectors.toMap(e -> e.description, Function.identity()));
    }

    public static Barangay getByDescription(String description) {
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
