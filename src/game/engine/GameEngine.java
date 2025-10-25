package game.engine;

import game.entities.Player;
import game.entities.Enemy;
import game.ui.GameFrame;
import game.ui.GamePanel;
import game.ui.LossPanel;
import game.ui.WinPanel;
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
    private boolean paused = false;
    private GamePanel panel;
    private Player player;
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
        Player.reset();
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void restart() {
        paused = true;
        player.takeDamage();

        player.goToStart();
        for (Enemy enemy : enemies) {
            enemy.goToStart();
            enemy.update(this);
        }

        try {
            // maybe play a sound to indicate HP loss?
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        paused = false;
    }

    private boolean detectCollision() {
        for (Enemy enemy : enemies) {
            if (!player.isPoweredUp() && player.isCollidingWith(enemy)) {
                return true;
            }
            if (player.isCollidingWith(enemy) && player.isPoweredUp()) {
                enemy.goToStart();
                return false;
            }
        }
        return false;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void performWinScreen() {
        running = false;
        javax.swing.SwingUtilities.invokeLater(() -> {
            java.awt.Window window = javax.swing.SwingUtilities.getWindowAncestor(panel);
            if (window instanceof javax.swing.JFrame frame) {
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new WinPanel(frame));
                frame.revalidate();
                frame.repaint();
            }
        });
    }

    public void performLossScreen() {
        running = false;
        javax.swing.SwingUtilities.invokeLater(() -> {
            java.awt.Window window = javax.swing.SwingUtilities.getWindowAncestor(panel);
            if (window instanceof javax.swing.JFrame frame) {
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new LossPanel(frame));
                frame.revalidate();
                frame.repaint();
            }
        });
    }

    @Override
    public void run() {
        while (running) {

            if (paused) {
                continue;
            }

            if (tick % Constants.PLAYER_TPS == 0) {
                player.update(this);
            }

            if (tick % Constants.ENEMY_TPS == 0) {
                enemies.forEach((enemy) -> {
                    enemy.update(this);
                });
            }

            if (detectCollision()) {
                restart();
            }

            if (player.getOrbs() == 0) {
                performWinScreen();
            }
            if (player.getHealth() == 0) {
                performLossScreen();
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
