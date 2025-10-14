package game.entities;

import game.utils.Animation;
import game.utils.Constants;

public class Player {
    public static final int ANIMATION_SPEED = Constants.ANIMATION_SPEED;
    Animation goLeft;
    Animation goRight;
    Animation goUp;
    Animation goDown;

    public int playerHealth;

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
