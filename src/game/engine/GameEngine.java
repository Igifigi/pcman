package game.engine;

import game.entities.Player;
import game.entities.Enemy;
import game.entities.Entity;
import game.ui.GameFrame;
import game.ui.GamePanel;
import game.utils.Constants;
import game.utils.EnemyType;
import game.utils.KeyboardManager;

import java.util.ArrayList;

public class GameEngine implements Runnable {
    private static final int FPS = Constants.FPS;
    // private static final int TPS = Constants.TPS;
    private static final int refreshRate = 1000 / FPS;

    private Thread thread;
    private boolean running = false;
    private GamePanel panel;
    private Entity player;
    private int tick = 0;
    private ArrayList<Enemy> enemies = new ArrayList<>();

    public GameEngine(GameFrame frame) {
        panel = new GamePanel(this);
        frame.add(panel);
        frame.setVisible(true);

        player = Player.getInstance();
        Enemy ubuntu = new Enemy(EnemyType.UBUNTU, Constants.UBUNTU_STARTING_POSITION);
        Enemy arch = new Enemy(EnemyType.ARCH, Constants.ARCH_STARTING_POSITION);
        arch.setUbuntuReference(ubuntu);
        enemies.add(ubuntu);
        enemies.add(arch);
        enemies.add(new Enemy(EnemyType.GENTOO, Constants.GENTOO_STARTING_POSITION));
        enemies.add(new Enemy(EnemyType.MINT, Constants.MINT_STARTING_POSITION));

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
            if (tick % Constants.PLAYER_TPS == 0) {
                player.update(this);
            }
            if (tick % Constants.ENEMY_TPS == 0) {
                enemies.forEach((enemy) -> {
                    enemy.update(this);
                });
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
