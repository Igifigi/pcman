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

public class BFS {
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
