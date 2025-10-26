package game.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.engine.GameEngine;
import game.utils.Constants;
import game.utils.Direction;
import game.utils.Tuple;
import game.world.Board;

/**
 * An abstract base class for all movable objects ('entities') in the game.
 *
 * <p>
 * This class manages common properties like position, movement, and
 * collision. Subclasses {@link Player} and {@link Enemy} must
 * implement the {@link #update(GameEngine)} method to define their
 * specific behavior.
 * </p>
 */
public abstract class Entity {
    protected Tuple boardPosition;
    protected Tuple startingPosition;
    protected Tuple movement = Constants.NULL_MOVEMENT.clone();
    protected Direction desiredMovement = Direction.NONE;
    protected Direction currentMovement = Direction.NONE;
    protected BufferedImage sprite;

    /**
     * Creates a new Entity.
     *
     * @param sprite           - the image to use for this entity
     * @param startingPosition - the tile coordinates (x, y) where this entity
     *                         spawns
     */
    public Entity(BufferedImage sprite, Tuple startingPosition) {
        this.sprite = sprite;
        this.boardPosition = startingPosition.clone();
        this.startingPosition = startingPosition.clone();
    }

    /**
     * Gets the entity's current board position.
     *
     * @return A {@link Tuple} containing the (x, y) coordinates.
     */
    public Tuple getBoardPosition() {
        return boardPosition;
    }

    /**
     * Gets the entity's current X-coordinate (column).
     *
     * @return The X board position.
     */
    public int getBoardX() {
        return boardPosition.first;
    }

    /**
     * Gets the entity's current Y-coordinate (row).
     *
     * @return The Y board position.
     */
    public int getBoardY() {
        return boardPosition.second;
    }

    /**
     * Teleports the entity back to its starting spawn position and stops
     * its movement.
     */
    public void goToStart() {
        boardPosition = startingPosition.clone();
        movement = Constants.NULL_MOVEMENT.clone();
    }

    /**
     * Sets the direction the entity wishes to move next.
     *
     * @param direction - the desired {@link Direction}
     */
    public void setDesiredMovement(Direction direction) {
        this.desiredMovement = direction;
    }

    /**
     * Gets the entity's current sprite.
     *
     * @return The {@link BufferedImage} for this entity.
     */
    public BufferedImage getSprite() {
        return sprite;
    }

    /**
     * Checks if the entity can move to an adjacent tile.
     *
     * @param dx - the change in X (e.g., -1 for left, 1 for right)
     * @param dy - the change in Y (e.g., -1 for up, 1 for down)
     * @return {@code true} if the target tile is within bounds and is not a
     *         wall, {@code false} otherwise.
     */
    protected boolean canGoThere(int dx, int dy) {
        int newX = boardPosition.first + dx;
        int newY = boardPosition.second + dy;

        if (newX < 0 || newY < 0 || newX >= Board.map[0].length || newY >= Board.map.length) {
            return false;
        }

        return Board.map[newY][newX] == 0;
    }

    /**
     * Checks if this entity is on the same tile as another entity.
     *
     * @param e - the other {@link Entity} to check against
     * @return {@code true} if they are on the same (x, y) tile,
     *         {@code false} otherwise.
     */
    public boolean isCollidingWith(Entity e) {
        return this.boardPosition.first == e.boardPosition.first && this.boardPosition.second == e.boardPosition.second;
    }

    /**
     * Updates the entity's state. This is called once per game tick.
     * <p>
     * Subclasses must implement this to define their movement and logic.
     *
     * @param engine - the main {@link GameEngine} instance
     */
    public abstract void update(GameEngine engine);

    /**
     * Draws the entity on the screen at its current position.
     *
     * @param g        - the {@link Graphics} context to draw with
     * @param tileSize - the size of one tile in pixels
     * @param boardX   - the X coordinate on the board
     * @param boardY   - the Y coordinate on the board
     */
    public void draw(Graphics g, int tileSize, int boardX, int boardY) {
        if (sprite == null) {
            return;
        }
        g.drawImage(sprite, boardPosition.first * tileSize + boardX, boardPosition.second * tileSize + boardY, null);
    }
}
