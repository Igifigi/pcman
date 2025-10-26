package game.entities;

import game.world.Board;
import game.world.sprites.PlayerSprite;

import java.awt.Graphics;
import java.util.List;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.engine.GameEngine;
import game.utils.Animation;
import game.utils.Constants;
import game.utils.Direction;
import game.utils.Tuple;
import game.utils.Utils;

/**
 * Represents the player character, managed as a singleton.
 *
 * <p>
 * This class handles player movement, input (via {@code desiredMovement}),
 * health, orb collection, power-ups, and animations. It extends
 * {@link Entity}.
 * </p>
 */
public class Player extends Entity {
    // eager init due to multithreading
    private static Player instance = new Player();
    static final Tuple size = new Tuple(32, 32);
    private Timer powerUpTimer;

    private static final int ANIMATION_SPEED = Constants.ANIMATION_SPEED;
    Animation goLeft;
    Animation goRight;
    Animation goUp;
    Animation goDown;
    Animation animation;

    private int playerHealth;
    private int remainingOrbs;
    public boolean poweredUp = false;

    /**
     * Private constructor for the singleton pattern.
     * Initializes health, orbs, and animations.
     */
    private Player() {
        super(null, Constants.PLAYER_STARTING_POSITION);
        playerHealth = Constants.INITIAL_PLAYER_HEALTH;
        this.pickAnimationSet();
        animation = goRight;
        remainingOrbs = Constants.INITIAL_ORBS;
    }

    /**
     * Gets the single, shared instance of the Player.
     *
     * @return The singleton {@link Player} instance.
     */
    public static Player getInstance() {
        return instance;
    }

    /**
     * Gets the player's sprite width.
     *
     * @return The width in pixels.
     */
    public int getWidth() {
        return size.first;
    }

    /**
     * Gets the player's sprite height (sprites are squares).
     *
     * @return The size in pixels.
     */
    public int getHeight() {
        return size.second;
    }

    /**
     * Sets the player's next desired movement direction (from keyboard input).
     *
     * @param direction The {@link Direction} the player wants to move.
     */
    public void setDesiredMovement(Direction direction) {
        desiredMovement = direction;
    }

    /**
     * Gets the player's current health.
     *
     * @return The number of lives remaining.
     */
    public int getHealth() {
        return playerHealth;
    }

    /**
     * Gets the number of orbs left to collect.
     *
     * @return The remaining orb count.
     */
    public int getOrbs() {
        return remainingOrbs;
    }

    /**
     * Checks if the player is currently powered up.
     *
     * @return {@code true} if the power-up is active, {@code false} otherwise.
     */
    public boolean isPoweredUp() {
        return poweredUp;
    }

    /**
     * Updates the player's state. Called once per tick by the
     * {@link GameEngine}.
     * 
     * <p>
     * This method handles:
     * <ul>
     * <li>teleporting through the side tunnels,</li>
     * <li>changing direction based on player input ({@code desiredMovement}),</li>
     * <li>moving the player one tile if the path is clear,</li>
     * <li>collecting orbs on the new tile.</li>
     * </ul>
     * </p>
     * 
     * @param engine - the main game engine
     */
    @Override
    public void update(GameEngine engine) {
        // teleport
        if (boardPosition.second == 14) {
            if (boardPosition.first <= 0 && desiredMovement.equals(Direction.LEFT)) {
                boardPosition.first = 27;
                if (Board.getOrbType(boardPosition.second, boardPosition.first) == 2) {
                    Board.setOrbType(boardPosition.second, boardPosition.first, 1);
                    remainingOrbs--;
                }
            } else if (boardPosition.first >= 27 && desiredMovement.equals(Direction.RIGHT)) {
                boardPosition.first = 0;
            }
            collectOrbIfPresent(engine);
        }

        // change of movement
        if (canGoThere(desiredMovement.dx(), desiredMovement.dy())) {
            movement.first = desiredMovement.dx();
            movement.second = desiredMovement.dy();
            currentMovement = desiredMovement;
            collectOrbIfPresent(engine);
        }

        // movement, if possible
        if (this.canGoThere(movement.first, movement.second)) {
            boardPosition.first += movement.first;
            boardPosition.second += movement.second;
            collectOrbIfPresent(engine);
        }
    }

    /**
     * Activates the player's power-up.
     * <p>
     * This sets all enemies to the "scared" state and starts a timer.
     * If a power-up is already active, it resets the timer.
     *
     * @param enemies The list of all enemies in the game.
     */
    public void activatePowerUp(List<Enemy> enemies) {
        poweredUp = true;

        for (Enemy e : enemies) {
            e.setScared(true);
        }

        if (powerUpTimer != null && powerUpTimer.isRunning()) {
            powerUpTimer.stop();
        }

        powerUpTimer = new Timer(Constants.POWERUP_DURATION, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                poweredUp = false;
                for (Enemy enemy : enemies) {
                    enemy.setScared(false);
                }
                powerUpTimer.stop();
            }
        });

        powerUpTimer.setRepeats(false);
        powerUpTimer.start();
    }

    /**
     * Reduces the player's health by one.
     * Also updates the player's animation set to reflect the new health.
     */
    public void takeDamage() {
        this.playerHealth--;
        this.pickAnimationSet();
    }

    /**
     * Advances the player's animation to the next frame.
     */
    public void updateAnimation() {
        this.pickAnimationDirection();
        this.animation.update();
    }

    /**
     * Selects the correct animation based on
     * the player's {@code currentMovement}.
     */
    private void pickAnimationDirection() {
        switch (currentMovement) {
            case Direction.UP:
                animation = goUp;
                break;
            case Direction.DOWN:
                animation = goDown;
                break;
            case Direction.LEFT:
                animation = goLeft;
                break;
            case Direction.RIGHT:
                animation = goRight;
                break;
            default:
                break;
        }
    }

    /**
     * Loads the correct set of animation sprites based on the player's
     * current health.
     */
    public void pickAnimationSet() {
        switch (playerHealth) {
            case 3:
                goLeft = new Animation(PlayerSprite.FULL_HP_LEFT, ANIMATION_SPEED);
                goRight = new Animation(PlayerSprite.FULL_HP_RIGHT, ANIMATION_SPEED);
                goUp = new Animation(PlayerSprite.FULL_HP_UP, ANIMATION_SPEED);
                goDown = new Animation(PlayerSprite.FULL_HP_DOWN, ANIMATION_SPEED);
                break;
            case 2:
                goLeft = new Animation(PlayerSprite.HALF_HP_LEFT, ANIMATION_SPEED);
                goRight = new Animation(PlayerSprite.HALF_HP_RIGHT, ANIMATION_SPEED);
                goUp = new Animation(PlayerSprite.HALF_HP_UP, ANIMATION_SPEED);
                goDown = new Animation(PlayerSprite.HALF_HP_DOWN, ANIMATION_SPEED);
                break;
            case 1:
                goLeft = new Animation(PlayerSprite.LOW_HP_LEFT, ANIMATION_SPEED);
                goRight = new Animation(PlayerSprite.LOW_HP_RIGHT, ANIMATION_SPEED);
                goUp = new Animation(PlayerSprite.LOW_HP_UP, ANIMATION_SPEED);
                goDown = new Animation(PlayerSprite.LOW_HP_DOWN, ANIMATION_SPEED);
                break;
            default:
                break;
        }
    }

    /**
     * Draws the player's current animation frame on the screen.
     *
     * @param g            - the {@link Graphics} context to draw with
     * @param tileSize     - the size of one tile in pixels
     * @param boardOffsetX - the X-pixel offset of the game board on the screen
     * @param boardOffsetY - the Y-pixel offset of the game board on the screen
     */
    @Override
    public void draw(Graphics g, int tileSize, int boardOffsetX, int boardOffsetY) {
        Tuple onScreenPosition = Utils.calculateOnScreenPosition(this.boardPosition.first, this.boardPosition.second,
                tileSize, boardOffsetX, boardOffsetY);
        g.drawImage(animation.getSprite(), onScreenPosition.first, onScreenPosition.second, null);
    }

    /**
     * Checks the player's current tile for an orb.
     * <p>
     * If an orb is found, it is "collected" (its type is changed on the
     * board), the {@code remainingOrbs} count is decreased, and if it's
     * a power orb (type 3), the power-up is activated.
     *
     * @param engine - the main {@link GameEngine}, needed to get the enemy list
     *               for {@link #activatePowerUp(List)}
     */
    private void collectOrbIfPresent(GameEngine engine) {
        int type = Board.getOrbType(boardPosition.second, boardPosition.first);
        if (type == 2 || type == 3) {
            Board.setOrbType(boardPosition.second, boardPosition.first, 1);
            remainingOrbs--;
            if (type == 3) {
                activatePowerUp(engine.getEnemies());
            }
        }
    }

}
