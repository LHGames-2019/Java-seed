package io.polyhx.lhgames.data;

public class Point {
    private final int fX;
    private final int fY;

    public Point(int x, int y) {
        fX = x;
        fY = y;
    }

    public int getX() {
        return fX;
    }

    public int getY() {
        return fY;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Point) {
            Point other = (Point) obj;

            if(fX != other.getX()) {
                return false;
            }

            if(fY != other.getY()) {
                return false;
            }

            return true;
        }

        return false;
    }
}