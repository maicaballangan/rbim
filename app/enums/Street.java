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
public enum Street {
    Centro_West("Centro West", "App Centro", Barangay.APPAS),
    Centro_East("Centro East", "App Centro", Barangay.APPAS),
    Sur("Sur", "App Sur", Barangay.APPAS),
    Anabel("Anabel", "App Anabel", Barangay.APPAS),
    Nasgueban_1("Nasgueban BLCK-1", "App Nas.BLCK 1", Barangay.APPAS),
    Nasgueban_2("Nasgueban BLCK-2", "App Nas.BLCK 2", Barangay.APPAS),
    Nasgueban_3("Nasgueban BLCK-3", "App Nas.BLCK 3", Barangay.APPAS),
    Norte("Norte", "App Norte", Barangay.APPAS);

    private static final Map<String, Street> map;

    static {
        map = Arrays.stream(Street
                .values())
                .collect(Collectors.toMap(e -> e.description, Function.identity()));
    }

    private String description;
    private String popcomNo;
    private Barangay barangay;

    Street(String description, String popcomNo, Barangay barangay) {
        this.description = description;
        this.popcomNo = popcomNo;
        this.barangay = barangay;
    }

    @Override
    public String toString() {
        return description;
    }

    public static Street getByDescription(String description) {
        if (description == null) return null;
        return map.get(description);
    }
}
