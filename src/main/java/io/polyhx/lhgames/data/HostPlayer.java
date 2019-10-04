package io.polyhx.lhgames.data;

public class HostPlayer extends Player {
    private final int fMaxMove;
    private final int fMoveLeft;
    private final Direction fLastMove;

    public HostPlayer(Team team, Point pos, int tail, int body, int max, int left, Direction last) {
        super(team, pos, tail, body);

        fMaxMove = max;
        fMoveLeft = left;
        fLastMove = last;
    }

    public int getMaxMove() {
        return fMaxMove;
    }

    public int getMoveLeft() {
        return fMoveLeft;
    }

    public Direction getLastMove() {
        return fLastMove;
    }
}
