package game.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.engine.GameEngine;
import game.utils.Constants;
import game.utils.Direction;
import game.utils.Tuple;
import game.world.Board;

public abstract class Entity {
    protected Tuple boardPosition;
    protected Tuple startingPosition;
    protected Tuple movement = Constants.NULL_MOVEMENT.clone();
    protected Direction desiredMovement = Direction.NONE;
    protected Direction currentMovement = Direction.NONE;
    protected BufferedImage sprite;

    public Entity(BufferedImage sprite, Tuple startingPosition) {
        this.sprite = sprite;
        this.boardPosition = startingPosition.clone();
        this.startingPosition = startingPosition.clone();
    }

    public Tuple getBoardPosition() {
        return boardPosition;
    }

    public int getBoardX() {
        return boardPosition.first;
    }

    public int getBoardY() {
        return boardPosition.second;
    }

    /**Teleport entity to start position declared at init. */
    public void goToStart() {
        boardPosition = startingPosition.clone();
        movement = Constants.NULL_MOVEMENT.clone();
    }

    public void setDesiredMovement(Direction direction) {
        this.desiredMovement = direction;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    protected boolean canGoThere(int dx, int dy) {
        int newX = boardPosition.first + dx;
        int newY = boardPosition.second + dy;

        if (newX < 0 || newY < 0 || newX >= Board.map[0].length || newY >= Board.map.length) {
            return false;
        }

        return Board.map[newY][newX] == 0;
    }

    public boolean isCollidingWith(Entity e) {
        if (this.boardPosition.first == e.boardPosition.first && this.boardPosition.second == e.boardPosition.second) {
            return true;
        } else {
            return false;
        }
    }

    public abstract void update(GameEngine engine);

    public void draw(Graphics g, int tileSize, int boardX, int boardY) {
        if (sprite == null) {
            return;
        }
        g.drawImage(sprite, boardPosition.first * tileSize + boardX, boardPosition.second * tileSize + boardY, null);
    }
}
