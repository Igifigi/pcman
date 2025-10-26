package game.logic;

/**
 * Represents a single tile (or node) on the game board.
 *
 * <p>
 * A Vertex is defined by its {@code row} (y-coordinate) and
 * {@code col} (x-coordinate). This class is immutable.
 *
 * <p>
 * It implements {@link Comparable} so that vertices can be
 * sorted, which is useful for creating canonical (standard)
 * {@link Edge} objects.
 * </p>
 */
public class Vertex implements Comparable<Vertex> {
    public final int row;
    public final int col;

    /**
     * Creates a new Vertex at the specified location.
     *
     * @param row - the row (Y-coordinate)
     * @param col - the column (X-coordinate)
     */
    public Vertex(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Compares this vertex to another vertex.
     *
     * <p>
     * It first compares by {@code row}. If the rows are the same,
     * it then compares by {@code col}.
     * </p>
     *
     * @param ver - the other vertex to compare to
     * @return A negative number if this vertex comes "before" the other,
     *         a positive number if it comes "after", and 0 if they are
     *         in the same position.
     */
    @Override
    public int compareTo(Vertex ver) {
        if (ver == null) {
            return 1;
        }
        int rowDiff = Integer.compare(this.row, ver.row);
        if (rowDiff != 0) {
            return rowDiff;
        }
        return Integer.compare(this.col, ver.col);
    }

    /**
     * Checks if this vertex is at the same location as another object (vertex).
     *
     * @param obj - the object to compare with.
     * @return {@code true} if the other object is a {@code Vertex}
     *         with the same {@code row} and {@code col}, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Vertex)) {
            return false;
        }
        Vertex ver = (Vertex) obj;
        return ver.row == this.row && ver.col == this.col;
    }

    /**
     * Generates a hash code for the vertex.
     *
     * @return A hash code based on the vertex's {@code row} and {@code col}.
     */
    @Override
    public int hashCode() {
        return 31 * Integer.hashCode(row) + Integer.hashCode(col);
    }

    /**
     * Returns a string representation of the vertex.
     *
     * @return A string in the format "(row col)", e.g. "(14 22)".
     */
    @Override
    public String toString() {
        return "(" + this.row + " " + this.col + ")";
    }
}
