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

/**
 * The main drawing area for the game.
 *
 * <p>
 * This class extends {@link JPanel} and is responsible for rendering
 * everything the player sees during gameplay. It draws the game board,
 * the player, all enemies, and the score.
 * </p>
 *
 * <p>
 * The {@link GameEngine} calls {@code repaint()} on this panel in its
 * game loop, which in turn calls the {@link #paintComponent(Graphics)}
 * method.
 * </p>
 */
public class GamePanel extends JPanel {
    private final Player player;
    private final GameEngine engine;

    private int panelWidth;
    private int panelHeight;

    private int boardOffsetX;
    private int boardOffsetY;

    /**
     * Creates a new GamePanel.
     *
     * @param gameEngine - a reference to the main {@link GameEngine} (it is
     *                   used to get the list of enemies to draw)
     */
    public GamePanel(GameEngine gameEngine) {
        this.setBackground(Color.BLACK);
        player = Player.getInstance();
        this.engine = gameEngine;
    }

    /**
     * Draws the entire game state onto the panel.
     *
     * <p>
     * This method is called automatically by Swing when the panel needs
     * to be redrawn. It handles:
     * <ul>
     * <li>clearing the screen and drawing the black background,</li>
     * <li>calculating the correct offsets to center the game board,</li>
     * <li>drawing the score,</li>
     * <li>drawing every tile of the game board (walls and orbs),</li>
     * <li>drawing the player's animated sprite,</li>
     * <li>drawing all enemy sprites.</li>
     * </ul>
     * </p>
     *
     * @param g - the {@link Graphics} object provided by Swing for drawing
     */
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
        g.drawString(Constants.INITIAL_ORBS - player.getOrbs() + "", scoreX + 25, scoreY + 50);

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
