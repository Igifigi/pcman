package game.engine;

import game.ui.GameFrame;
import game.ui.GamePanel;

public class GameEngine implements Runnable {
    private static final int FPS = Constants.FPS;
    private static final int TPS = Constants.TPS;
    private static final int refreshRate = 1000 / FPS;

    private Thread thread;
    private boolean running = false;
    private GamePanel panel;

    public GameEngine(GameFrame frame) {
        panel = new GamePanel();
        frame.add(panel);
        frame.setVisible(true);
    }

    public void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (running) {
            panel.repaint();
            try {
                Thread.sleep(refreshRate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
