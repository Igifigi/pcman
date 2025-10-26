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

    private Player() {
        super(null, Constants.PLAYER_STARTING_POSITION);
        playerHealth = Constants.INITIAL_PLAYER_HEALTH;
        this.pickAnimationSet();
        animation = goRight;
        remainingOrbs = Constants.INITIAL_ORBS;
    }

    public static Player getInstance() {
        return instance;
    }

    public int getWidth() {
        return size.first;
    }

    public int getHeight() {
        return size.second;
    }

    public void setDesiredMovement(Direction direction) {
        desiredMovement = direction;
    }

    public int getHealth() {
        return playerHealth;
    }

    public int getOrbs() {
        return remainingOrbs;
    }

    public boolean isPoweredUp() {
        return poweredUp;
    }

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

    public void takeDamage() {
        this.playerHealth--;
        this.pickAnimationSet();
    }

    public void updateAnimation() {
        this.pickAnimationDirection();
        this.animation.update();
    }

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

    @Override
    public void draw(Graphics g, int tileSize, int boardOffsetX, int boardOffsetY) {
        Tuple onScreenPosition = Utils.calculateOnScreenPosition(this.boardPosition.first, this.boardPosition.second,
                tileSize, boardOffsetX, boardOffsetY);
        g.drawImage(animation.getSprite(), onScreenPosition.first, onScreenPosition.second, null);
    }

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
