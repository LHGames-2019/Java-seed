package io.polyhx.lhgames.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public enum Team {
    TEAM_1(1),
    TEAM_2(2),
    TEAM_3(3),
    TEAM_4(4);

    private static final Map<Integer, Team> INTEGER_MAP = new HashMap<Integer, Team>() {{
        put(1, Team.TEAM_1);
        put(2, Team.TEAM_2);
        put(3, Team.TEAM_3);
        put(4, Team.TEAM_4);
    }};

    private static final Map<String, Team> TAIL_STRING_MAP = new HashMap<String, Team>() {{
        put("a", Team.TEAM_1);
        put("b", Team.TEAM_2);
        put("c", Team.TEAM_3);
        put("d", Team.TEAM_4);
    }};

    private static final Map<String, Team> BODY_STRING_MAP = new HashMap<String, Team>() {{
        put("A", Team.TEAM_1);
        put("B", Team.TEAM_2);
        put("C", Team.TEAM_3);
        put("D", Team.TEAM_4);
    }};

    private static final Map<String, Team> FROM_STRING_MAP = new HashMap<String, Team>() {{
        put("1", Team.TEAM_1);
        put("2", Team.TEAM_2);
        put("3", Team.TEAM_3);
        put("4", Team.TEAM_4);
    }};

    private final int fValue;

    Team(int value) {
        fValue = value;
    }

    public Set<Team> getOtherTeams() {
        Set<Team> teams = new HashSet<>();
        teams.add(Team.TEAM_1);
        teams.add(Team.TEAM_2);
        teams.add(Team.TEAM_3);
        teams.add(Team.TEAM_4);
        teams.remove(this);
        return teams;
    }

    public static Team fromInteger(int integer) {
        return INTEGER_MAP.get(integer);
    }

    public static Team fromTailString(String string) {
        return TAIL_STRING_MAP.get(string);
    }

    public static Team fromBodyString(String string) {
        return BODY_STRING_MAP.get(string);
    }

    public static Team fromString(String string) {
        return FROM_STRING_MAP.get(string);
    }

    @Override
    public String toString() {
        return Integer.toString(fValue);
    }
}
