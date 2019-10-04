package io.polyhx.lhgames.data;

public class Player {
    private final Team fTeam;
    private final Point fPosition;
    private final int fTailSize;
    private final int fBodySize;

    public Player(Team team, Point pos, int tail, int body) {
        fTeam = team;
        fPosition = pos;
        fTailSize = tail;
        fBodySize = body;
    }

    public Team getTeam() {
        return fTeam;
    }

    public Point getPosition() {
        return fPosition;
    }

    public int getTailSize() {
        return fTailSize;
    }

    public int getBodySize() {
        return fBodySize;
    }
}