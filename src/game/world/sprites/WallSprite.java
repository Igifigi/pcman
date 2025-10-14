package game.world.sprites;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.utils.Tuple;

public final class WallSprite {

    private static final Tuple[] coordinates = {
            new Tuple(4, 2),
            new Tuple(5, 2),
            new Tuple(6, 2),
            new Tuple(7, 2),
            new Tuple(8, 2),
            new Tuple(8, 2),
            new Tuple(9, 2),
            new Tuple(10, 2),
            new Tuple(11, 2),
            new Tuple(12, 2),
            new Tuple(13, 2),
            new Tuple(1, 3),
            new Tuple(2, 3),
            new Tuple(3, 3),
            new Tuple(4, 3),
            new Tuple(5, 3),
            new Tuple(6, 3)
    };

    private static BufferedImage[] getWalls() {
        ArrayList<BufferedImage> walls = new ArrayList<>();
        for (Tuple t : coordinates) {
            walls.add(SpriteSheet.getSprite(t.first, t.second));
        }
        return walls.toArray(new BufferedImage[0]);
    }

    public static final BufferedImage[] WALLS = getWalls();

}
