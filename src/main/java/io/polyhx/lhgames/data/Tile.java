package io.polyhx.lhgames.data;

import com.sun.tools.javac.main.Option;

public class Tile {
    private final Point fPosition;
    private final Team fOwner;
    private final Team fTail;
    private final Team fHead;

    public Tile(Point pos, Team owner, Team tail, Team head) {
        fPosition = pos;
        fOwner = owner;
        fTail = tail;
        fHead = head;
    }

    public Point getPosition() {
        return fPosition;
    }

    public Team getTeamOwner() {
        return fOwner;
    }

    public Team getTailOwner() {
        return fTail;
    }

    public Team getHeadOwner() {
        return fHead;
    }

    public boolean isEmpty() {
        if(fOwner != null) {
            return false;
        }

        if(fTail != null) {
            return false;
        }

        if(fHead != null) {
            return false;
        }

        return true;
    }

    public static Tile fromString(Point pos, String string) throws Exception {
        if(string.length() == 0) {
            return new Tile(pos, null, null, null);
        }

        if(string.length() != 3) {
            throw new Exception("Invalid string format: " + string);
        }

        Team owner = null;
        if(string.charAt(0) != '-') {
            owner = Team.fromBodyString("" + string.charAt(0));
        }

        Team tail = null;
        if(string.charAt(1) != '-') {
            tail = Team.fromTailString("" + string.charAt(1));
        }

        Team head = null;
        if(string.charAt(2) != '-') {
            head = Team.fromString("" + string.charAt(2));
        }

        return new Tile(pos, owner, tail, head);
    }

    @Override
    public String toString() {
        if(fHead != null) {
            return String.format("[%s]", fHead.toString());
        }

        if(fTail != null) {
            return String.format("(%s)", fTail.toString());
        }

        if(fOwner != null) {
            return String.format(" %s ", fOwner.toString());
        }

        return "   ";
    }
}