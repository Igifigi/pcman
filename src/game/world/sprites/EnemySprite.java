package game.world.sprites;

import java.awt.image.BufferedImage;

import game.utils.EnemyType;

/**
 * Class that provides access to enemy sprites.
 *
 * <p>
 * Use {@link #getSpriteByType(EnemyType)} to obtain the sprite associated with
 * a particular {@link EnemyType}.
 *
 * @see EnemyType
 * @see SpriteSheet
 */
public final class EnemySprite {

    public static final BufferedImage ARCH = SpriteSheet.getSprite(7, 3);
    public static final BufferedImage GENTOO = SpriteSheet.getSprite(8, 3);
    public static final BufferedImage LINUX = SpriteSheet.getSprite(9, 3);
    public static final BufferedImage MINT = SpriteSheet.getSprite(10, 3);
    public static final BufferedImage ERROR = SpriteSheet.getSprite(11, 3);
    public static final BufferedImage UBUNTU = SpriteSheet.getSprite(12, 3);

    public static BufferedImage getSpriteByType(EnemyType type) {
        switch (type) {
            case EnemyType.ARCH:
                return EnemySprite.ARCH;
            case EnemyType.GENTOO:
                return EnemySprite.GENTOO;
            case EnemyType.MINT:
                return EnemySprite.MINT;
            case EnemyType.UBUNTU:
                return EnemySprite.UBUNTU;
            default:
                return null;
        }
    }
}
