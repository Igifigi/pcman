package game.world.sprites;

import java.awt.image.BufferedImage;

/**
 * Class that provides access to orb-related sprites.
 * <p>
 * Sprites are obtained from {@code SpriteSheet.getSprite(...)} at class load time.
 * @see SpriteSheet
 */
public final class OrbSprite {

    public static final BufferedImage EMPTY_TILE = WallSprite.EMPTY_TILE;
    public static final BufferedImage ORB = SpriteSheet.getSprite(14, 2);
    public static final BufferedImage POWER_ORB = SpriteSheet.getSprite(0, 3);

    public static BufferedImage getOrbByType(int type) {
        switch (type) {
            case 1:
                return OrbSprite.EMPTY_TILE;
            case 2:
                return OrbSprite.ORB;
            case 3:
                return OrbSprite.POWER_ORB;
        }
        return null;
    }

}
