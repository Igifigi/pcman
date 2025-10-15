package game.entities;

import game.world.Board;
import game.world.sprites.PlayerSprite;
import game.utils.Animation;
import game.utils.Constants;
import game.utils.Direction;
import game.utils.Tuple;

public class Player {
    // eager init due to multithreading

    private static Player instance = new Player();
    private Tuple boardPosition = new Tuple(1, 1);
    private Tuple movement = new Tuple(0, 0);
    private Direction desiredMovement = Direction.NONE;
    static final Tuple size = new Tuple(32, 32);

    private static final int ANIMATION_SPEED = Constants.ANIMATION_SPEED;
    Animation goLeft;
    Animation goRight;
    Animation goUp;
    Animation goDown;

    public int playerHealth;

    private Player() {
    }

    public static Player getInstance() {
        return instance;
    }

    public int getBoardX() {
        return boardPosition.first;
    }

    public int getBoardY() {
        return boardPosition.second;
    }

    public int getWidth() {
        return size.first;
    }

    public int getHeight() {
        return size.second;
    }

    private boolean canGoThere(int dx, int dy) {
        return Board.map[boardPosition.second + dy][boardPosition.first + dx] == 0;
    }

    public void setDesiredMovement(Direction direction) {
        desiredMovement = direction;
    }

    public void updateLocation() {
        // teleport L -> R
        if (this.boardPosition.first <= 0 && this.boardPosition.second == 14 && desiredMovement.equals(Direction.LEFT)) {
            this.boardPosition.first = 27;
        }

        // teleport R -> L
        if (this.boardPosition.first >= 27 && this.boardPosition.second == 14 && desiredMovement.equals(Direction.RIGHT)) {
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

}
