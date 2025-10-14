package game.entities;

import game.utils.Tuple;
import game.world.Board;
import game.utils.Animation;
import game.utils.Constants;

public class Player {
    // eager init due to multithreading

    private static Player instance = new Player();
    private Tuple boardPosition = new Tuple(1, 1);
    private Tuple movement = new Tuple(0, 0);
    private Tuple desiredMovement = new Tuple(0, 0);
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

    public void setDesiredMovement(int dx, int dy) {
        desiredMovement.first = dx;
        desiredMovement.second = dy;
    }

    public void updateLocation() {
        if (this.canGoThere(desiredMovement.first, desiredMovement.second)) {
            movement.first = desiredMovement.first;
            movement.second = desiredMovement.second;
        }

        if (this.canGoThere(movement.first, movement.second)) {
            boardPosition.first += movement.first;
            boardPosition.second += movement.second;
        }
    }

    public void pickAnimationSet() {
        switch (playerHealth) {
            case 3:
                goLeft = new Animation(Constants.FULL_HP_LEFT, ANIMATION_SPEED);
                goRight = new Animation(Constants.FULL_HP_RIGHT, ANIMATION_SPEED);
                goUp = new Animation(Constants.FULL_HP_UP, ANIMATION_SPEED);
                goDown = new Animation(Constants.FULL_HP_DOWN, ANIMATION_SPEED);
                break;
            case 2:
                goLeft = new Animation(Constants.HALF_HP_LEFT, ANIMATION_SPEED);
                goRight = new Animation(Constants.HALF_HP_RIGHT, ANIMATION_SPEED);
                goUp = new Animation(Constants.HALF_HP_UP, ANIMATION_SPEED);
                goDown = new Animation(Constants.HALF_HP_DOWN, ANIMATION_SPEED);
                break;
            case 1:
                goLeft = new Animation(Constants.LOW_HP_LEFT, ANIMATION_SPEED);
                goRight = new Animation(Constants.LOW_HP_RIGHT, ANIMATION_SPEED);
                goUp = new Animation(Constants.LOW_HP_UP, ANIMATION_SPEED);
                goDown = new Animation(Constants.LOW_HP_DOWN, ANIMATION_SPEED);
                break;
            default:
                break;
        }
    }

}
