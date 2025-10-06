package game.ui;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
    
    public GameFrame() {
        setTitle("PCMAN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);

        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
