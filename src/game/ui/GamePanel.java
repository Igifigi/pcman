package game.ui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        int rectWidth = 224;
        int rectHeight = 248;

        if (panelWidth >= rectWidth * 2 && panelHeight >= rectHeight * 2) {
            rectWidth *= 2;
            rectHeight *= 2;
        }

        int x = (panelWidth - rectWidth) / 2;
        int y = (panelHeight - rectHeight) / 2;

        g.setColor(Color.BLUE);
        g.fillRect(x, y, rectWidth, rectHeight);
    }

    public GamePanel() {
        this.setBackground(Color.BLACK);
    }

}
