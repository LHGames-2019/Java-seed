package io.polyhx.lhgames.data;

import java.util.HashMap;
import java.util.Map;

public enum Direction {
    UP(0),
    RIGHT(1),
    DOWN(2),
    LEFT(3),
    UNKNOWN(4);

    private static final Map<Integer, Direction> INTEGER_MAP = new HashMap<Integer, Direction>() {{
        put(1, Direction.UP);
        put(2, Direction.RIGHT);
        put(3, Direction.DOWN);
        put(4, Direction.LEFT);
    }};

    private static final Map<Direction, String> STRING_MAP = new HashMap<Direction, String>() {{
        put(Direction.UP, "up");
        put(Direction.RIGHT, "right");
        put(Direction.DOWN, "down");
        put(Direction.LEFT, "left");
    }};

    private final int fValue;

    Direction(int value) {
        fValue = value;
    }

    public int getValue() {
        return fValue;
    }

    public static Direction fromInteger(int integer) {
        Direction direction = INTEGER_MAP.get(integer);
        if(direction != null) {
            return direction;
        }

        return UNKNOWN;
    }

    public String toString() {
        String string = STRING_MAP.get(this);
        if(string == null) {
            return "unknown";
        }

        return string;
    }
}
