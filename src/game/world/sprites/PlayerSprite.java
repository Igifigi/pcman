package game.world.sprites;

import java.awt.image.BufferedImage;

public final class PlayerSprite {

    // ==== FULL HP SPRITES ==== //
    public static final BufferedImage[] FULL_HP_RIGHT = {
            SpriteSheet.getSprite(10, 0),
            SpriteSheet.getSprite(13, 0),
            SpriteSheet.getSprite(1, 1)
    };
    public static final BufferedImage[] FULL_HP_LEFT = {
            SpriteSheet.getSprite(8, 0),
            SpriteSheet.getSprite(15, 0),
            SpriteSheet.getSprite(2, 1)
    };
    public static final BufferedImage[] FULL_HP_DOWN = {
            SpriteSheet.getSprite(11, 0),
            SpriteSheet.getSprite(14, 0),
            SpriteSheet.getSprite(3, 1)
    };
    public static final BufferedImage[] FULL_HP_UP = {
            SpriteSheet.getSprite(9, 0),
            SpriteSheet.getSprite(12, 0),
            SpriteSheet.getSprite(0, 1)
    };

    // ==== HALF HP SPRITES ==== //
    public static final BufferedImage[] HALF_HP_RIGHT = {
            SpriteSheet.getSprite(4, 1),
            SpriteSheet.getSprite(8, 1),
            SpriteSheet.getSprite(12, 1)
    };
    public static final BufferedImage[] HALF_HP_LEFT = {
            SpriteSheet.getSprite(6, 1),
            SpriteSheet.getSprite(10, 1),
            SpriteSheet.getSprite(14, 1)
    };
    public static final BufferedImage[] HALF_HP_UP = {
            SpriteSheet.getSprite(7, 1),
            SpriteSheet.getSprite(11, 1),
            SpriteSheet.getSprite(15, 1)
    };
    public static final BufferedImage[] HALF_HP_DOWN = {
            SpriteSheet.getSprite(5, 1),
            SpriteSheet.getSprite(9, 1),
            SpriteSheet.getSprite(13, 1)
    };

    // ==== LOW HP SPRITES ==== //
    public static final BufferedImage[] LOW_HP_RIGHT = {
            SpriteSheet.getSprite(0, 2),
            SpriteSheet.getSprite(0, 0),
            SpriteSheet.getSprite(4, 0)
    };
    public static final BufferedImage[] LOW_HP_LEFT = {
            SpriteSheet.getSprite(2, 2),
            SpriteSheet.getSprite(2, 0),
            SpriteSheet.getSprite(6, 0)
    };
    public static final BufferedImage[] LOW_HP_UP = {
            SpriteSheet.getSprite(3, 2),
            SpriteSheet.getSprite(3, 0),
            SpriteSheet.getSprite(7, 0)
    };
    public static final BufferedImage[] LOW_HP_DOWN = {
            SpriteSheet.getSprite(1, 2),
            SpriteSheet.getSprite(1, 0),
            SpriteSheet.getSprite(5, 0)
    };

}
