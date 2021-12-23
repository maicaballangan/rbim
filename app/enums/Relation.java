package enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Relation {
    Head(1),
    Spouse(2),
    Son(3),
    Daughter(4),
    Stepson(5),
    Stepdaughter(6),
    Son_In_Law(7),
    Daughter_In_Law(8),
    Grandson(9),
    Granddaughter(10),
    Father(11),
    Mother(12),
    Brother(13),
    Sister(14),
    Uncle(15),
    Aunt(16),
    Nephew(17),
    Niece(18),
    Other_Relative(19),
    NonRelative(20),
    Boarder(21),
    Domestic_Helper(22);

    private static final Map<Integer, Relation> map;

    static {
        map = Arrays
                .stream(Relation.values())
                .collect(Collectors.toMap(e -> e.code, Function.identity()));
    }

    public static Relation getByCode(int code) {
        return map.get(code);
    }

    private final int code;

    Relation(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}