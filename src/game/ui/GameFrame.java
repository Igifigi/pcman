package game.ui;

import game.utils.Constants;
import javax.swing.JFrame;

public class GameFrame extends JFrame {
    
    public GameFrame() {
        int frameX = Constants.DEFAULT_WINDOW_SIZE.first;
        int frameY = Constants.DEFAULT_WINDOW_SIZE.second;

        setTitle("PCMAN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(frameX, frameY);

        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
