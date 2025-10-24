package game.utils;

public enum Direction {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    NONE(0, 0);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int dx() {
        return dx;
    }

    public int dy() {
        return dy;
    }

    public static Direction fromDelta(int dx, int dy) {
    for (Direction dir : Direction.values()) {
        if (dir.dx() == dx && dir.dy() == dy) {
            return dir;
        }
    }
    return Direction.NONE;
}
}
