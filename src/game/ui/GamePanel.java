package game.ui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import game.entities.Player;
import game.world.Board;
import game.world.sprites.WallSprite;

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

        // draw board
        for (int row = 0; row < Board.getHeight(); row++) {
            for (int col = 0; col < Board.getWidth(); col++) {
                int tileX = boardX + 32 * col;
                int tileY = boardY + 32 * row;
                if (Board.map[row][col] == 1) {
                    // wall
                    g.drawImage(WallSprite.WALLS[1], tileX, tileY, null);
                    // temporary -> change to desired wall (!!!)
                } else {
                    // not wall
                    g.drawImage(WallSprite.EMPTY_TILE, tileX, tileY, null);

                }
            }
        }

        // draw player
        g.setColor(Color.RED);
        g.fillRect(this.getPlayerX(), this.getPlayerY(), player.getWidth(), player.getHeight());
    }

    public GamePanel() {
        this.setBackground(Color.BLACK);
        player = Player.getInstance();
    }

}
