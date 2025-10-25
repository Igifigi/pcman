package game.utils;

import java.util.Random;

import game.entities.Player;
import game.logic.BFS;
import game.logic.Graph;
import game.logic.Vertex;
import game.world.Board;

public class Utils {
    public static Tuple calculateOnScreenPosition(int boardX, int boardY, int tileSize, int boardOffsetX,
            int boardOffsetY) {
        return new Tuple(boardX * tileSize + boardOffsetX, boardY * tileSize + boardOffsetY);
    }

    public static int calculateBoardDifference(Tuple position1, Tuple position2) {
        int diffX = position1.first - position2.first;
        int diffY = position1.second - position1.second;
        return (int) Math.round(Math.sqrt(1.0 * diffX * diffX + 1.0 * diffY * diffY));
    }

    public static Tuple trimAndAvoidWall(int boardX, int boardY) {
        int trimmedX = Math.max(0, Math.min(boardX, Board.getWidth() - 1));
        int trimmedY = Math.max(0, Math.min(boardY, Board.getHeight() - 1));

        if (!Board.isWall(trimmedX, trimmedY)) {
            return new Tuple(trimmedX, trimmedY);
        }

        Vertex nearest = BFS.getNearestVertex(Graph.getInstance(), boardX, boardY);
        if (nearest != null) {
            return new Tuple(nearest.row, nearest.col);
        }
        return Player.getInstance().getBoardPosition();
    }

    public static String getRandomWinMessage() {
        Random random = new Random();
        return Constants.WIN_MESSAGES[random.nextInt(Constants.WIN_MESSAGES.length)];
    }

    public static String getRandomLossMessage() {
        Random random = new Random();
        return Constants.LOSS_MESSAGES[random.nextInt(Constants.LOSS_MESSAGES.length)];
    }
}
