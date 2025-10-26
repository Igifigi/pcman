package game.entities;

import java.awt.image.BufferedImage;
import java.util.List;
import java.awt.Graphics;

import game.engine.GameEngine;
import game.logic.BFS;
import game.logic.Graph;
import game.logic.Vertex;
import game.utils.Constants;
import game.utils.Direction;
import game.utils.EnemyType;
import game.utils.Tuple;
import game.utils.Utils;
import game.world.sprites.EnemySprite;

/**
 * Represents an AI-controlled enemy character.
 *
 * <p>
 * This class extends {@link Entity} and implements logic for
 * pathfinding. Each enemy has a specific {@link EnemyType} which
 * determines its targeting behavior (its "personality").
 * </p>
 */
public class Enemy extends Entity {
    public final EnemyType type;
    private final BufferedImage scaredSprite = EnemySprite.ERROR;
    public Direction desiredMovement = Direction.NONE;
    private boolean scared = false;

    private Enemy ubuntuReference;

    /**
     * Creates a new enemy with a specific type and starting position.
     *
     * @param type             - the {@link EnemyType}, which defines its behavior
     * @param startingPosition - the tile coordinates (x, y) where this enemy spawns
     */
    public Enemy(EnemyType type, Tuple startingPosition) {
        super(EnemySprite.getSpriteByType(type), startingPosition);
        this.type = type;
    }

    /**
     * Sets the reference to the UBUNTU enemy.
     * <p>
     * This is specifically required for the ARCH enemy's AI.
     *
     * @param ubuntu - the {@link Enemy} instance with the type UBUNTU
     */
    public void setUbuntuReference(Enemy ubuntu) {
        this.ubuntuReference = ubuntu;
    }

    /**
     * Updates the enemy's position. Called once per tick by the
     * {@link GameEngine}.
     *
     * <p>
     * <ul>
     * <li>gets a target tile using {@link #getTargetField()},</li>
     * <li>finds the shortest path to that target using {@link BFS},</li>
     * <li>sets its {@code desiredMovement} to the first step on that path,</li>
     * <li>moves in that direction if possible.</li>
     * 
     * @param engine - the main game engine
     */
    @Override
    public void update(GameEngine engine) {
        // 1. get the target tile based on AI personality
        Tuple target = getTargetField();
        Graph graph = Graph.getInstance();

        // Note: Vertex uses (row, col) which is (y, x)
        Vertex start = new Vertex(boardPosition.second, boardPosition.first);
        Vertex goal = new Vertex(target.second, target.first);

        // 2. find the shortest path to the target
        List<Vertex> path = BFS.getPath(graph, start, goal);

        // 3. determine the next move from the path
        if (path.size() > 1) {
            Vertex next = path.get(1);

            int dx = next.col - start.col;
            int dy = next.row - start.row;
            desiredMovement = Direction.fromDelta(dx, dy);
        }

        // 4. try to move in the new desired direction
        if (this.canGoThere(desiredMovement.dx(), desiredMovement.dy())) {
            movement.first = desiredMovement.dx();
            movement.second = desiredMovement.dy();
        }

        // 5. keep moving in the current direction if the new one is blocked
        if (this.canGoThere(movement.first, movement.second)) {
            boardPosition.first += movement.first;
            boardPosition.second += movement.second;
        }
    }

    /**
     * Sets the enemy's "scared" state.
     *
     * @param scared {@code true} to make the enemy scared,
     *               {@code false} to return to normal
     */
    public void setScared(boolean scared) {
        this.scared = scared;
    }

    /**
     * Draws the enemy on the screen.
     * <p>
     * It will draw the {@link #scaredSprite} if the enemy is scared,
     * otherwise it draws its normal {@link #sprite}.
     *
     * @param g            - the {@link Graphics} context to draw with,
     * @param tileSize     - the size of one tile in pixels,
     * @param boardOffsetX - the X-pixel offset of the game board on the screen,
     * @param boardOffsetY - the Y-pixel offset of the game board on the screen,
     */
    public void draw(Graphics g, int tileSize, int boardOffsetX, int boardOffsetY) {
        BufferedImage toDraw = (!scared) ? sprite : scaredSprite;
        Tuple onScreenPosition = Utils.calculateOnScreenPosition(this.boardPosition.first, this.boardPosition.second,
                tileSize, boardOffsetX, boardOffsetY);
        g.drawImage(toDraw, onScreenPosition.first, onScreenPosition.second, null);
    }

    /**
     * AI Target: UBUNTU
     * <p>
     * Directly targets the player's current tile.
     *
     * @return The player's position.
     */
    private Tuple getUbuntuTarget() {
        return Player.getInstance().boardPosition;
    }

    /**
     * AI Target: ARCH
     * <p>
     * Tries to get to the player by targeting a tile in front of the player.
     * It uses the UBUNTU enemy's position as a reference point.
     *
     * @return The calculated ambush tile.
     */
    private Tuple getArchTarget() {
        if (ubuntuReference == null) {
            return Player.getInstance().boardPosition;
        }
        Player player = Player.getInstance();
        // get vector from Ubuntu to Player
        int vx = player.getBoardX() - ubuntuReference.getBoardX();
        int vy = player.getBoardY() - ubuntuReference.getBoardY();

        // target the tile that is the same vector past the player
        int targetX = player.getBoardX() + vx;
        int targetY = player.getBoardY() + vy;

        return Utils.trimAndAvoidWall(targetX, targetY);
    }

    /**
     * AI Target: GENTOO
     * <p>
     * If the player is far away, it targets the player directly.
     * If the player is close (within 8 tiles), it retreats to a corner.
     *
     * @return The player's tile or the corner tile.
     */
    private Tuple getGentooTarget() {
        Player player = Player.getInstance();
        int distance = Utils.calculateBoardDifference(player.getBoardPosition(), this.boardPosition);

        if (distance > 8) {
            return player.getBoardPosition();
        } else {
            return Constants.GENTOO_SECOND_TARGET;
        }
    }

    /**
     * AI Target: MINT
     * <p>
     * Targets a few tiles in front of the player's current movement
     * direction.
     *
     * @return The calculated intercept tile.
     */
    private Tuple getMintTarget() {
        Player player = Player.getInstance();

        int targetX = player.getBoardX() + Constants.MINT_DISTANCE * player.desiredMovement.dx();
        int targetY = player.getBoardY() + Constants.MINT_DISTANCE * player.desiredMovement.dy();

        return Utils.trimAndAvoidWall(targetX, targetY);
    }

    /**
     * Determines the target tile for the AI based on the enemy's
     * {@link #type}.
     *
     * <p>
     * Each enemy type has a different "personality" and targeting
     * strategy. If scared, all enemies will target a "safe" corner
     * (this logic is not yet implemented in this method).
     *
     * @return The (x, y) coordinates of the target tile as a {@link Tuple}.
     */
    public Tuple getTargetField() {
        switch (type) {
            case ARCH -> {
                return getArchTarget();
            }
            case GENTOO -> {
                return getGentooTarget();
            }
            case MINT -> {
                return getMintTarget();
            }
            case UBUNTU -> {
                return getUbuntuTarget();
            }
            default -> {
                return Player.getInstance().boardPosition;
            }
        }
    }

}
