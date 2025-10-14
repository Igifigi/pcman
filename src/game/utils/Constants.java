package game.utils;

import java.awt.image.BufferedImage;

public final class Constants {
    public static final Size DEFAULT_WINDOW_SIZE = new Size(1000, 800);

    /*-------------------SPRITES-------------------- */
    public static final int TILE_SIZE = 32;
    public static final String SPRITE_SHEET_FILE = "src/resources/textures/sprites.png";

    public static final int ANIMATION_SPEED = 10;

    /*---------------ANIMATION FRAME SETS----------------- */
    public static final BufferedImage[] FULL_HP_RIGHT = {Sprite.getSprite(10, 0), 
        Sprite.getSprite(13, 0), Sprite.getSprite(1, 1)};
    public static final BufferedImage[] FULL_HP_LEFT = {Sprite.getSprite(8, 0), 
        Sprite.getSprite(15, 0), Sprite.getSprite(2, 1)};
    public static final BufferedImage[] FULL_HP_DOWN = {Sprite.getSprite(11, 0), 
        Sprite.getSprite(14, 0), Sprite.getSprite(3, 1)};
    public static final BufferedImage[] FULL_HP_UP = {Sprite.getSprite(9, 0), 
        Sprite.getSprite(12, 0), Sprite.getSprite(0, 1)};

    public static final BufferedImage[] HALF_HP_RIGHT = {Sprite.getSprite(4, 1), 
        Sprite.getSprite(8, 1), Sprite.getSprite(12, 1)};
    public static final BufferedImage[] HALF_HP_LEFT = {Sprite.getSprite(6, 1), 
        Sprite.getSprite(10, 1), Sprite.getSprite(14, 1)};
    public static final BufferedImage[] HALF_HP_UP = {Sprite.getSprite(7, 1), 
        Sprite.getSprite(11, 1), Sprite.getSprite(15, 1)};
    public static final BufferedImage[] HALF_HP_DOWN = {Sprite.getSprite(5, 1), 
        Sprite.getSprite(9, 1), Sprite.getSprite(13, 1)};
    
    public static final BufferedImage[] LOW_HP_RIGHT = {Sprite.getSprite(0, 2), 
        Sprite.getSprite(0, 0), Sprite.getSprite(4, 0)};
    public static final BufferedImage[] LOW_HP_LEFT = {Sprite.getSprite(2, 2), 
        Sprite.getSprite(2, 0), Sprite.getSprite(6, 0)};
    public static final BufferedImage[] LOW_HP_UP = {Sprite.getSprite(3, 2), 
        Sprite.getSprite(3, 0), Sprite.getSprite(7, 0)};
    public static final BufferedImage[] LOW_HP_DOWN = {Sprite.getSprite(1, 2), 
        Sprite.getSprite(1, 0), Sprite.getSprite(5, 0)};
    
}
