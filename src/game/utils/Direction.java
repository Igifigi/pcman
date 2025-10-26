package game.utils;

/**
 * Enumeration defining movement directions as Tuples.
 * <p>
 * Entities can move in 4 cardinal directions on the map. 
 * This enum defines those directions as Tuples that can be interpreted into movement or facing direction.
 * @see game.entities.Entity
 * @see game.entities.Player
 * @see game.entities.Enemy
 */
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
