package game.logic;

public class Vertex implements Comparable<Vertex> {
    public final int row;
    public final int col;

    public Vertex(int row, int col) {
        this.row = row;
        this.col = col;
    }

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

    @Override
    public int hashCode() {
        return 31 * Integer.hashCode(row) + Integer.hashCode(col);
    }

    @Override
    public String toString() {
        return "(" + this.row + " " + this.col + ")";
    }
}
