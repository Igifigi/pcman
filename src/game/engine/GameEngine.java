package game.engine;

import game.entities.Player;
import game.entities.Enemy;
import game.ui.GameFrame;
import game.ui.GamePanel;
import game.utils.Constants;
import game.utils.KeyboardManager;
import game.world.sprites.EnemySprite;
import java.util.ArrayList;

public class GameEngine implements Runnable {
    private static final int FPS = Constants.FPS;
    private static final int TPS = Constants.TPS;
    private static final int refreshRate = 1000 / FPS;

    private Thread thread;
    private boolean running = false;
    private GamePanel panel;
    private Player player;
    private int tick = 0;
    private ArrayList<Enemy> enemies = new ArrayList<>();

    public GameEngine(GameFrame frame) {
        panel = new GamePanel(this);
        frame.add(panel);
        frame.setVisible(true);

        player = Player.getInstance();
        enemies.add(new Enemy(EnemySprite.ARCH, Constants.ARCH_STARTING_POSITION));
        enemies.add(new Enemy(EnemySprite.UBUNTU, Constants.UBUNTU_STARTING_POSITION));
        enemies.add(new Enemy(EnemySprite.GENTOO, Constants.GENTOO_STARTING_POSITION));
        enemies.add(new Enemy(EnemySprite.MINT, Constants.GENTOO_STARTING_POSITION));

        panel.addKeyListener(new KeyboardManager());
        panel.setFocusable(true);
        panel.requestFocusInWindow();
    }

    public void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    @Override
    public void run() {
        while (running) {
            if (tick % TPS == 0) {
                player.updateLocation();
                enemies.forEach(Enemy::update);
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
