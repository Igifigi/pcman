package game.engine;

import game.entities.Player;
import game.ui.GameFrame;
import game.ui.GamePanel;
import game.utils.Constants;
import game.utils.KeyboardManager;

public class GameEngine implements Runnable {
    private static final int FPS = Constants.FPS;
    private static final int TPS = Constants.TPS;
    private static final int refreshRate = 1000 / FPS;

    private Thread thread;
    private boolean running = false;
    private GamePanel panel;
    private Player player;
    private int tick = 0;

    public GameEngine(GameFrame frame) {
        panel = new GamePanel();
        frame.add(panel);
        frame.setVisible(true);

        player = Player.getInstance();

        panel.addKeyListener(new KeyboardManager());
        panel.setFocusable(true);
        panel.requestFocusInWindow();
    }

    public void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (running) {
            if (tick % TPS == 0) {
                player.updateLocation();
            }
            tick++;
            panel.repaint();

            try {
                Thread.sleep(refreshRate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
