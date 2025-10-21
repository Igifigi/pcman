package game.utils;

public class Utils {
    public static Tuple calculateOnScreenPosition(int boardX, int boardY, int tileSize, int boardOffsetX,
            int boardOffsetY) {
        return new Tuple(boardX * tileSize + boardOffsetX, boardY * tileSize + boardOffsetY);
    }
}
