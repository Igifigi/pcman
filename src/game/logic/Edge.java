package game.logic;

public class Edge {
    public final Vertex a;
    public final Vertex b;

    public Edge(Vertex a, Vertex b) {
        if (a.compareTo(b) <= 0) {
            this.a = a;
            this.b = b;
        } else {
            this.a = b;
            this.b = a;
        }
    }

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

    @Override
    public int hashCode() {
        return 31 * a.hashCode() + b.hashCode();
    }
}
