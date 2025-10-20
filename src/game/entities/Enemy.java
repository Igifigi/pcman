package game.entities;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

import game.utils.Direction;
import game.utils.Tuple;
import game.world.Board;
import game.world.sprites.EnemySprite;

public class Enemy {
    private final BufferedImage scaredSprite = EnemySprite.ERROR;
    private final BufferedImage enemySprite;
    private Direction desiredMovement = Direction.NONE;
    private Tuple movement = new Tuple(0,0);
    private Tuple boardPosition;
    private boolean scared = false; //to change sprite to error/scared

    public Enemy(BufferedImage enemySprite, Tuple position) {
        this.enemySprite = enemySprite;
        this.boardPosition = position;
    }

    private boolean canGoThere(int dx, int dy) {
        if (boardPosition.first + dx > 27 || boardPosition.second + dy > 29 
            || boardPosition.first + dx < 0 || boardPosition.second + dy < 0) {
                return false;
            }
        return Board.map[boardPosition.second + dy][boardPosition.first + dx] == 0;
    }

    //random movements to demonstrate functionality
    public void update() {
        double rand = Math.random();
        if (rand < 0.25) {
            desiredMovement = Direction.UP;
        } else if (rand < 0.5) {
            desiredMovement = Direction.DOWN;
            //scared = false;
        } else if (rand < 0.75) {
            desiredMovement = Direction.LEFT;
        } else if (rand < 1) {
            desiredMovement = Direction.RIGHT;
            //scared = true;
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

    public void draw(Graphics g, int tileSize, int boardX, int boardY) {
        if (!scared) {
            g.drawImage(enemySprite, boardPosition.first * tileSize + boardX, boardPosition.second * tileSize + boardY, null);
        } else {
            g.drawImage(scaredSprite, boardPosition.first * tileSize + boardX, boardPosition.second * tileSize + boardY, null);
        }
        
    }

}
