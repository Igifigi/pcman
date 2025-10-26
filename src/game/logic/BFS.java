package game.logic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import game.world.Board;

/**
 * A utility class that provides static methods for finding paths
 * using Breadth-First Search (BFS).
 *
 * <p>
 * This class is not meant to be instantiated. It is used for
 * pathfinding calculations within a {@link Graph}.
 * </p>
 */
public class BFS {
    /**
     * Finds the shortest path between a {@code start} vertex and a {@code goal}
     * vertex
     * in the given {@code Graph}.
     *
     * <p>
     * This method uses the Breadth-First Search algorithm, which guarantees
     * that the found path is the shortest in terms of the number of steps (edges)
     * taken.
     * </p>
     *
     * @param graph - the {@link Graph} to search within
     * @param start - the {@link Vertex} where the path begins
     * @param goal  - the {@link Vertex} where the path should end
     * @return A {@link List} of vertices representing the shortest path from
     *         {@code start} to {@code goal}. If no path exists, an empty
     *         list is returned.
     */
    public static List<Vertex> getPath(Graph graph, Vertex start, Vertex goal) {
        if (start.equals(goal)) {
            return List.of(start);
        }

        Queue<Vertex> queue = new ArrayDeque<>();
        Map<Vertex, Vertex> parents = new HashMap<>();
        Set<Vertex> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);
        parents.put(start, null);

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();

            if (current.equals(goal)) {
                break;
            }

            for (Vertex neighbor : graph.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parents.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        // no path
        if (!parents.containsKey(goal)) {
            return Collections.emptyList();
        }

        // retrieve path
        // (this is very neat use of the for-loop)
        List<Vertex> path = new ArrayList<>();
        for (Vertex vertex = goal; vertex != null; vertex = parents.get(vertex)) {
            path.add(vertex);
        }
        Collections.reverse(path);

        return path;
    }

    /**
     * Finds the nearest valid (non-wall) vertex in the graph, starting from
     * a specific (x, y) board coordinate.
     *
     * <p>
     * This is useful for finding a valid spot on the game grid while searching for
     * a target field for an entity. The search expands outward (BFS) until
     * it hits the first tile that is not a wall, according to
     * {@link Board#isWall(int, int)}.
     * </p>
     *
     * @param graph  - the {@link Graph} to search within
     * @param boardX - the starting X-coordinate (column) to search from
     * @param boardY - the starting Y-coordinate (row) to search from
     * @return The closest {@link Vertex} that is not a wall. Returns {@code null}
     *         if no valid vertex is found (e.g., if the search area is
     *         completely surrounded by walls and contains no valid tiles).
     */
    public static Vertex getNearestVertex(Graph graph, int boardX, int boardY) {
        Vertex start = new Vertex(boardY, boardX);
        Vertex nearest = null;

        Queue<Vertex> queue = new ArrayDeque<>();
        Set<Vertex> visited = new HashSet<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();

            if (!Board.isWall(current.row, current.col)) {
                nearest = current;
                break;
            }

            for (Vertex neighbor : graph.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        if (nearest != null) {
            return nearest;
        }
        return null;
    }
}
