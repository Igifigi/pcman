package game.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import game.utils.Direction;
import game.world.Board;

/**
 * Represents the game's walkable paths as a graph data structure.
 *
 * <p>
 * This class is a singleton, meaning only one instance of it exists.
 * When created, it scans the {@link Board#map} and builds a graph where
 * each non-wall tile is a {@link Vertex} and each connection between
 * adjacent non-wall tiles is an {@link Edge}.
 * </p>
 *
 * <p>
 * This is used by pathfinding algorithms like {@link BFS}.
 * </p>
 */
public class Graph {
    private static Graph instance = new Graph();

    private final ArrayList<Vertex> vertices = new ArrayList<>();
    private final Set<Edge> edges = new HashSet<>();
    private final Map<Vertex, List<Vertex>> adjacencyList;
    private final Direction[] directions = {
            Direction.UP,
            Direction.DOWN,
            Direction.LEFT,
            Direction.RIGHT
    };

    /**
     * Private constructor to create the singleton instance.
     *
     * <p>
     * It scans the entire {@link Board#map}, adds every tile that is NOT a wall
     * (value 0) as a {@link Vertex}, and it then re-scans all newly found vertices
     * and creates an {@link Edge} between any two that are adjacent (up, down,
     * left, or right) and also not walls.
     * </p>
     */
    private Graph() {
        // find all vertices (non-wall tiles)
        for (int row = 0; row < Board.getHeight(); row++) {
            for (int col = 0; col < Board.getWidth(); col++) {
                if (Board.map[row][col] != 0) {
                    continue;
                }
                vertices.add(new Vertex(row, col));
            }
        }

        // find all edges (connections between vertices)
        for (Vertex ver : vertices) {
            int row = ver.row;
            int col = ver.col;

            for (Direction dir : directions) {
                int newRow = row + dir.dy();
                int newCol = col + dir.dx();

                if (newRow < 0 || newCol < 0 || newRow >= Board.getHeight() || newCol >= Board.getWidth()) {
                    continue;
                }
                if (Board.map[newRow][newCol] != 0) {
                    continue;
                }

                Vertex neighbor = new Vertex(newRow, newCol);
                edges.add(new Edge(ver, neighbor));
            }
        }

        adjacencyList = buildAdjacency();
        // this.printGraph();
    }

    /**
     * Gets the single, shared instance of the Graph.
     *
     * @return The singleton {@code Graph} instance.
     */
    public static Graph getInstance() {
        return instance;
    }

    /**
     * A helper method to build the {@link #adjacencyList} map.
     * <p>
     * It converts the {@link #edges} set into a map where each
     * vertex is a key, and its value is a list of all its neighbors.
     * </p>
     *
     * @return A {@link Map} representing the graph's adjacency list.
     */
    private Map<Vertex, List<Vertex>> buildAdjacency() {
        Map<Vertex, List<Vertex>> map = new HashMap<>();

        for (Vertex ver : vertices) {
            map.put(ver, new ArrayList<>());
        }

        for (Edge edge : edges) {
            map.get(edge.a).add(edge.b);
            map.get(edge.b).add(edge.a);
        }

        return map;
    }

    /**
     * Gets the list of all neighbors for a given vertex.
     *
     * @param vertex - the vertex to find neighbors for
     * @return A {@link List} of all adjacent vertices. Returns an
     *         empty list if the vertex has no neighbors or is not in the graph.
     */
    public List<Vertex> getNeighbors(Vertex vertex) {
        return adjacencyList.getOrDefault(vertex, Collections.emptyList());
    }

}
