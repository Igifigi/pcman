package game.ui;

import game.utils.Constants;
import javax.swing.JFrame;

/**
 * The main window for the game.
 *
 * <p>
 * This class extends {@link JFrame} and sets up the basic window
 * properties, such as the title, size, and close operation. It acts as
 * the main container for all other UI panels.
 * </p>
 */
public class GameFrame extends JFrame {

    /**
     * Creates a new GameFrame.
     *
     * <p>
     * Initializes the window with a fixed size from {@link Constants},
     * sets the title to "PCMAN", centers it on the screen,
     * and makes it visible.
     * </p>
     */
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
