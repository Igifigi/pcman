package game.entities;

import game.world.sprites.PlayerSprite;

import java.awt.Color;
import java.awt.Graphics;

import game.utils.Animation;
import game.utils.Constants;
import game.utils.Direction;
import game.utils.Tuple;
import game.utils.Utils;

public class Player extends Entity {
    // eager init due to multithreading
    private static Player instance = new Player();
    static final Tuple size = new Tuple(32, 32);

    private static final int ANIMATION_SPEED = Constants.ANIMATION_SPEED;
    Animation goLeft;
    Animation goRight;
    Animation goUp;
    Animation goDown;

    public int playerHealth;

    private Player() {
        super(null, Constants.PLAYER_STARTING_POSITION);
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

    @Override
    public void update() {
        // teleport L -> R
        if (this.boardPosition.first <= 0 && this.boardPosition.second == 14
                && desiredMovement.equals(Direction.LEFT)) {
            this.boardPosition.first = 27;
        }

        // teleport R -> L
        if (this.boardPosition.first >= 27 && this.boardPosition.second == 14
                && desiredMovement.equals(Direction.RIGHT)) {
            this.boardPosition.first = 0;
        }

        if (this.canGoThere(desiredMovement.dx(), desiredMovement.dy())) {
            movement.first = desiredMovement.dx();
            movement.second = desiredMovement.dy();
        }

        if (this.canGoThere(movement.first, movement.second)) {
            boardPosition.first += movement.first;
            boardPosition.second += movement.second;
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
        g.setColor(Color.RED);
        Tuple onScreenPosition = Utils.calculateOnScreenPosition(this.boardPosition.first, this.boardPosition.second,
                tileSize, boardOffsetX, boardOffsetY);
        g.fillRect(onScreenPosition.first, onScreenPosition.second, tileSize, tileSize);
    }

}
