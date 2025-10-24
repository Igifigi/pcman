package game.entities;

import java.awt.image.BufferedImage;
import java.util.List;
import java.awt.Graphics;

import game.logic.BFS;
import game.logic.Graph;
import game.logic.Vertex;
import game.utils.Constants;
import game.utils.Direction;
import game.utils.EnemyType;
import game.utils.Tuple;
import game.utils.Utils;
import game.world.sprites.EnemySprite;

public class Enemy extends Entity {
    public final EnemyType type;
    private final BufferedImage scaredSprite = EnemySprite.ERROR;
    public Direction desiredMovement = Direction.NONE;
    private boolean scared = false; // to change sprite to error/scared

    private Enemy ubuntuReference;

    public Enemy(EnemyType type, Tuple startingPosition) {
        super(EnemySprite.getSpriteByType(type), startingPosition);
        this.type = type;
    }

    public void setUbuntuReference(Enemy ubuntu) {
        this.ubuntuReference = ubuntu;
    }

    // random movements to demonstrate functionality
    @Override
    public void update() {
        Tuple target = getTargetField();
        Graph graph = Graph.getInstance();
        // TODO: fix XY mess-up
        // Vertex start = new Vertex(boardPosition.first, boardPosition.second);
        // Vertex goal = new Vertex(target.first, target.second);
        Vertex start = new Vertex(boardPosition.second, boardPosition.first);
        Vertex goal = new Vertex(target.second, target.first);

        List<Vertex> path = BFS.getPath(graph, start, goal);

        if (path.size() > 1) {
            Vertex next = path.get(1);

            int dx = next.col - start.col;
            int dy = next.row - start.row;
            desiredMovement = Direction.fromDelta(dx, dy);
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

    private Tuple getUbuntuTarget() {
        return Player.getInstance().boardPosition;
    }

    private Tuple getArchTarget() {
        if (ubuntuReference == null) {
            return Player.getInstance().boardPosition;
        }
        Player player = Player.getInstance();
        int vx = player.getBoardX() - ubuntuReference.getBoardX();
        int vy = player.getBoardY() - ubuntuReference.getBoardY();

        int targetX = player.getBoardX() + vx;
        int targetY = player.getBoardY() + vy;

        return Utils.trimAndAvoidWall(targetX, targetY);
    }

    private Tuple getGentooTarget() {
        Player player = Player.getInstance();
        int distance = Utils.calculateBoardDifference(player.getBoardPosition(), this.boardPosition);

        if (distance > 8) {
            return player.getBoardPosition();
        } else {
            return Constants.GENTOO_SECOND_TARGET;
        }
    }

    private Tuple getMintTarget() {
        Player player = Player.getInstance();

        int targetX = player.getBoardX() + Constants.MINT_DISTANCE * player.desiredMovement.dx();
        int targetY = player.getBoardY() + Constants.MINT_DISTANCE * player.desiredMovement.dy();

        return Utils.trimAndAvoidWall(targetX, targetY);
    }

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
