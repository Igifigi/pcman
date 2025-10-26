package game.logic;

/**
 * Represents an undirected edge (a connection) between two {@link Vertex}
 * objects.
 *
 * <p>
 * This class ensures that an edge is always stored in a canonical
 * (standard) way, where vertex {@code a} is always "less than" or equal
 * to vertex {@code b}. This makes it easy to check if two edges are
 * equal, regardless of the order their vertices were provided in.
 * </p>
 */
public class Edge {
    public final Vertex a;
    public final Vertex b;

    /**
     * Creates a new edge connecting two vertices.
     *
     * <p>
     * The vertices are automatically sorted into a standard order
     * ({@code a} and {@code b}) to ensure that {@code new Edge(v1, v2)}
     * is always equal to {@code new Edge(v2, v1)}.
     * </p>
     *
     * @param a - the first vertex
     * @param b - the second vertex
     */
    public Edge(Vertex a, Vertex b) {
        if (a.compareTo(b) <= 0) {
            this.a = a;
            this.b = b;
        } else {
            this.a = b;
            this.b = a;
        }
    }

    /**
     * Checks if this edge is equal to another object (edge). Useful when working
     * with HashMaps.
     *
     * @param obj - the object to compare with
     * @return {@code true} if the other object is an {@code Edge}
     *         and connects the exact same two vertices, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Edge)) {
            return false;
        }
        Edge edge = (Edge) obj;
        return a.equals(edge.a) && b.equals(edge.b);
    }

    /**
     * Generates a hash code for the edge. Useful when working
     * with HashMaps.
     *
     * @return A hash code based on the two vertices it connects.
     */
    @Override
    public int hashCode() {
        return 31 * a.hashCode() + b.hashCode();
    }
}
