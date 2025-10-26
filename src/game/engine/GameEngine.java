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

/**
 * Manages the main game logic, including the game loop, character updates,
 * collision detection, and switching between game states (playing, win, loss).
 *
 * <p>
 * This class implements {@link Runnable} to run the main game loop in a
 * separate thread. It controls the flow of the game, updates the player
 * and enemies, checks for win/loss conditions, and handles pauses or
 * restarts after collisions.
 * </p>
 */
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

    /**
     * Creates a new GameEngine.
     *
     * <p>
     * This sets up the game environment. It creates the main {@link GamePanel},
     * adds it to the window ({@link GameFrame}), creates the player,
     * and adds all the enemies to the game.
     * </p>
     *
     * @param frame The main game window (GameFrame) that will hold the GamePanel.
     */
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

    /**
     * Starts the main game loop.
     * <p>
     * Resets the player's state, marks the game as 'running',
     * and starts a new {@link Thread} to run the {@link #run()} method.
     * </p>
     */
    public void start() {
        Player.reset();
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Handles the logic for restarting a round after the player is hit.
     *
     * <p>
     * This method pauses the game, makes the player take damage (lose health),
     * moves the player and all enemies back to their starting positions,
     * waits for 3 seconds, and then un-pauses the game.
     * </p>
     */
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

    /**
     * Checks if the player is colliding with any enemy and handles the outcome.
     *
     * <p>
     * It loops through all enemies.
     * <ul>
     * <li>If the player is NOT powered-up and touches an enemy, it returns
     * {@code true}
     * (signaling that the player was hit).</li>
     * <li>If the player IS powered-up and touches an enemy, it sends that enemy
     * back to its starting position and returns {@code false} (player is
     * safe).</li>
     * </ul>
     * </p>
     *
     * @return {@code true} if the player was hit by an enemy, {@code false}
     *         otherwise.
     */
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

    /**
     * Gets the list of all enemies currently in the game.
     *
     * @return An {@link ArrayList} containing all {@link Enemy} objects.
     */
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Stops the game and shows the "Win" screen.
     *
     * <p>
     * This stops the game loop (sets {@code running} to false) and
     * safely updates the game window to show the {@link WinPanel}.
     * </p>
     */
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

    /**
     * Stops the game and shows the "Loss" screen.
     *
     * <p>
     * This stops the game loop (sets {@code running} to false) and
     * safely updates the game window to show the {@link LossPanel}.
     * </p>
     */
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

    /**
     * The main game loop, which runs on a separate thread.
     *
     * <p>
     * This method runs as long as the {@code running} flag is true.
     * In each loop, it:
     * <ul>
     * <li>skips updates if the game is {@code paused},</li>
     * <li>updates the player and enemies based on their update speed (TPS),</li>
     * <li>checks for collisions (and calls {@link #restart()} if needed),</li>
     * <li>checks if the player won (no orbs left),</li>
     * <li>checks if the player lost (no health left),</li>
     * <li>increments the game tick,</li>
     * <li>tells the {@link GamePanel} to redraw itself,</li>
     * <li>pauses briefly to maintain the target frames per second (FPS).</li>
     * </ul>
     * </p>
     */
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
