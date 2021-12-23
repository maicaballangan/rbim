package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Skill {
    Refrigeration_and_Airconditioning(1),
    Automotive_or_Heavy_Equipment_Servicing(2),
    Metal_Worker(3),
    Building_Wiring_Installation(4),
    Heavy_Equipment_Operation(5),
    Plumbing(6),
    Welding(7),
    Carpentry(8),
    Baking(9),
    Dressmaking(10),
    Linguist(11),
    Computer_Graphics(12),
    Painting(13),
    Beauty_Care(14),
    Commercial_Cooking(15),
    Housekeeping(16),
    Massage_Therapy(17),
    Others(18),
    Undefined(99);

    private static final Map<Integer, Skill> map;

    static {
        map = Arrays
                .stream(Skill.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
    }

    public static Skill getByCode(int code) {
        return map.get(code);
    };

    private final int code;

    Skill(int code) {
        this.code = code;
    }

    public int getCode() {
            return code;
        }
}