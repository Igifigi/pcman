package game.world.sprites;

import game.utils.Tuple;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Class that maps wall types to sprite-sheet images.
 *
 * <p>Usage:
 * Wall types correspond to the order of entries in the coordinates array.
 * Call {@code getWallTile(wallType)} to obtain the {@link BufferedImage} for a given wall type. If {@link #wallType} is
 *   negative, {@link #EMPTY_TILE} is returned.
 */
public final class WallSprite {

    /*
     * dTurn = double turn
     * LU, UR = LeftUp, UpRight etc.
     */
    private static final Tuple[] coordinates = {
            new Tuple(4, 2), // turn LU 0
            new Tuple(5, 2), // turn UR 1
            new Tuple(6, 2), // dwall L 2
            new Tuple(7, 2), // dWall U 3
            new Tuple(8, 2), // dWall R 4
            new Tuple(9, 2), // dWall D 5
            new Tuple(10, 2), // sWall L 6
            new Tuple(11, 2), // sWall U 7
            new Tuple(12, 2), // sWall R 8
            new Tuple(13, 2), // sWall D 9
            new Tuple(1, 3), // dTurn DR 10
            new Tuple(2, 3), // dTurn LD 11
            new Tuple(3, 3), // dTurn LU 12
            new Tuple(4, 3), // dTurn UR 13
            new Tuple(5, 3), // turn DR 14
            new Tuple(6, 3), // turn LD 15
            new Tuple(0, 4), // inner turn 16
            new Tuple(1, 4), // inner turn 17
            new Tuple(2, 4), // inner turn 18
            new Tuple(3, 4), // inner turn 19
            new Tuple(4, 4), // inner dTurn 20
            new Tuple(5, 4), // inner dTurn 21
            new Tuple(6, 4), // inner dTurn 22
            new Tuple(7, 4) // inner dTurn 23
    };

    /**
     * Builds and returns an array of wall sprites based on the coordinate list.
     *
     * <p>For each Tuple in {@code coordinates} this method calls
     * {@code SpriteSheet.getSprite(t.first, t.second)} and collects the resulting
     * {@code BufferedImage} into a new array.</p>
     *
     * @return a {@code BufferedImage[]} containing the wall sprites in the same
     *         order as {@code coordinates}.
     */
    private static BufferedImage[] getWalls() {
        ArrayList<BufferedImage> walls = new ArrayList<>();
        for (Tuple t : coordinates) {
            walls.add(SpriteSheet.getSprite(t.first, t.second));
        }
        return walls.toArray(new BufferedImage[0]);
    }

    //List of wall sprites and the empty tile
    private static final BufferedImage[] WALLS = getWalls();
    public static final BufferedImage EMPTY_TILE = SpriteSheet.getSprite(15, 2);

    /**
     * Returns a {@code BufferedImage} sprite of the given {@code wallType}. For negative {@code wallType} return {@link #EMPTY_TILE}.
     * @param wallType type of wall (0-23)
     * @return {@code BufferedImage} sprite of type {@code wallType} or {@link #EMPTY_TILE}
     */
    public static BufferedImage getWallTile(int wallType) {
        return (wallType < 0) ? EMPTY_TILE : WALLS[wallType];
    }

}
