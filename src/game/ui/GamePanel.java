package game.ui;

import game.entities.Player;
import game.utils.Constants;
import game.entities.Enemy;
import game.world.Board;
import game.world.sprites.OrbSprite;
import game.world.sprites.WallSprite;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import game.engine.GameEngine;

public class GamePanel extends JPanel {
    private final Player player;

    private int panelWidth;
    private int panelHeight;

    private int boardX;
    private int boardY;

    private int getPlayerX() {
        return player.getBoardX() * 32 + boardX;
    }

    private int getPlayerY() {
        return player.getBoardY() * 32 + boardY;
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

        boardX = (panelWidth - rectWidth) / 2;
        boardY = (panelHeight - rectHeight) / 2;

        // draw pre-board (background)
        g.setColor(Color.BLUE);
        g.fillRect(boardX, boardY, rectWidth, rectHeight);

        for (int row = 0; row < Board.getHeight(); row++) {
            for (int col = 0; col < Board.getWidth(); col++) {
                int tileX = boardX + 32 * col;
                int tileY = boardY + 32 * row;
                if (Board.map[row][col] == 1) {
                    // wall
                    g.drawImage(WallSprite.getWallTile(Board.getWallType(row, col)), tileX, tileY, null);
                } else {
                    // not wall
                    int pathType = Board.getOrbType(row, col);
                    switch (pathType) {
                        case 1:
                            g.drawImage(OrbSprite.EMPTY_TILE, tileX, tileY, null);
                            break;
                        case 2:
                            g.drawImage(OrbSprite.ORB, tileX, tileY, null);
                            break;
                        case 3:
                            g.drawImage(OrbSprite.POWER_ORB, tileX, tileY, null);
                        default:
                            break;
                    }
                }
            }
        }

        // draw player
        g.setColor(Color.RED);
        g.fillRect(this.getPlayerX(), this.getPlayerY(), player.getWidth(), player.getHeight());

        //draw enemies
        for (Enemy e : GameEngine.getEnemies()) {
            e.draw(g, Constants.TILE_SIZE, boardX, boardY);
        }
    }

    public GamePanel() {
        this.setBackground(Color.BLACK);
        player = Player.getInstance();
    }

}
