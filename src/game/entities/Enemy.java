package game.entities;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

import game.utils.Direction;
import game.utils.Tuple;
import game.utils.Utils;
import game.world.sprites.EnemySprite;

public class Enemy extends Entity {
    private final BufferedImage scaredSprite = EnemySprite.ERROR;
    private Direction desiredMovement = Direction.NONE;
    private boolean scared = false; // to change sprite to error/scared

    public Enemy(BufferedImage sprite, Tuple startingPosition) {
        super(sprite, startingPosition);
    }

    // random movements to demonstrate functionality
    @Override
    public void update() {
        double rand = Math.random();
        if (rand < 0.25) {
            desiredMovement = Direction.UP;
        } else if (rand < 0.5) {
            desiredMovement = Direction.DOWN;
            // scared = false;
        } else if (rand < 0.75) {
            desiredMovement = Direction.LEFT;
        } else if (rand < 1) {
            desiredMovement = Direction.RIGHT;
            // scared = true;
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

    public void draw(Graphics g, int tileSize, int boardOffsetX, int boardOffsetY) {
        BufferedImage toDraw = (!scared) ? sprite : scaredSprite;
        Tuple onScreenPosition = Utils.calculateOnScreenPosition(this.boardPosition.first, this.boardPosition.second,
                tileSize, boardOffsetX, boardOffsetY);
        g.drawImage(toDraw, onScreenPosition.first, onScreenPosition.second, null);
    }

}
