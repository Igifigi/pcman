package game.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import game.utils.Direction;
import game.world.Board;

public class Graph {
    private final ArrayList<Vertex> vertices = new ArrayList<>();
    private final Set<Edge> edges = new HashSet<>();
    private final Map<Vertex, List<Vertex>> adjacencyList;
    private final Direction[] directions = {
            Direction.UP,
            Direction.DOWN,
            Direction.LEFT,
            Direction.RIGHT
    };

    public Graph() {
        for (int row = 0; row < Board.getHeight(); row++) {
            for (int col = 0; col < Board.getWidth(); col++) {
                if (Board.map[row][col] != 0) {
                    continue;
                }
                vertices.add(new Vertex(row, col));
            }
        }

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
    }

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

}
