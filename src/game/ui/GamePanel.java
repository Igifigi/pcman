package game.ui;

import game.entities.Player;
import game.utils.Constants;
import game.entities.Enemy;
import game.world.Board;
import game.world.sprites.OrbSprite;
import game.world.sprites.WallSprite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import game.engine.GameEngine;

public class GamePanel extends JPanel {
    private final Player player;
    private final GameEngine engine;

    private int panelWidth;
    private int panelHeight;

    private int boardOffsetX;
    private int boardOffsetY;

    public GamePanel(GameEngine gameEngine) {
        this.setBackground(Color.BLACK);
        player = Player.getInstance();
        this.engine = gameEngine;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        panelWidth = getWidth();
        panelHeight = getHeight();

        // fill background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, panelWidth, panelHeight);

        // int rectWidth = 224;
        int rectWidth = 896;
        // int rectHeight = 248;
        int rectHeight = 992;

        if (panelWidth >= rectWidth * 2 && panelHeight >= rectHeight * 2) {
            rectWidth *= 2;
            rectHeight *= 2;
        }

        boardOffsetX = (panelWidth - rectWidth) / 2;
        boardOffsetY = (panelHeight - rectHeight) / 2;

        // draw pre-board (background)
        g.setColor(Color.BLUE);
        g.fillRect(boardOffsetX, boardOffsetY, rectWidth, rectHeight);

        // draw score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Noto Sans", Font.BOLD, 32));

        int scoreX = boardOffsetX - 150;
        int scoreY = boardOffsetX + 50;

        g.drawString("SCORE", scoreX, scoreY);
        g.drawString(Constants.INITIAL_ORBS - player.getRemainingOrbs() + "", scoreX + 25, scoreY + 50);

        // draw board
        for (int row = 0; row < Board.getHeight(); row++) {
            for (int col = 0; col < Board.getWidth(); col++) {
                int tileX = boardOffsetX + 32 * col;
                int tileY = boardOffsetY + 32 * row;
                if (Board.map[row][col] == 1) {
                    // wall
                    g.drawImage(WallSprite.getWallTile(Board.getWallType(row, col)), tileX, tileY, null);
                } else {
                    // not wall
                    int pathType = Board.getOrbType(row, col);
                    g.drawImage(OrbSprite.getOrbByType(pathType), tileX, tileY, null);
                }
            }
        }

        // draw player
        player.updateAnimation();
        player.draw(g, Constants.TILE_SIZE, boardOffsetX, boardOffsetY);

        // draw enemies
        for (Enemy e : engine.getEnemies()) {
            e.draw(g, Constants.TILE_SIZE, boardOffsetX, boardOffsetY);
        }
    }

}
