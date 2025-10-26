package game.world.sprites;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.utils.Constants;

/**
 * Class to load a shared sprite sheet and return {@link #TILE_SIZE} tiles.
 * Loads the image from {@link #SPRITE_SHEET_FILENAME}.
 * <p>
 * Use getSprite(col, row) with 0-based tile coordinates; returns a
 * {@link #TILE_SIZE}×{@link #TILE_SIZE} subimage.
 * If the sheet fails to load, throw an exception.
 * </p>
 * <b>Remark: </b> non-public = visible only in game.world.sprites package
 */
final class SpriteSheet {
    private static BufferedImage spriteSheet;
    private static final int TILE_SIZE = Constants.TILE_SIZE;

    private static BufferedImage loadImage(String filename) {
        BufferedImage sheet = null;
        try {
            sheet = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sheet;
    }

    /**
     * Returns a subimage of the {@code SpriteSheet}.
     * 
     * @param posX starting at 0, column number of tile on the {@code SpriteSheet}
     *             counting from the <b>left</b>
     * @param posY starting at 0, row number of tile on the {@code SpriteSheet}
     *             counting from the <b>top</b>
     * @return a {@code BufferedImage} of the rectangle defined by {@link #posX},
     *         {@link #posY}, {@link #TILE_SIZE}
     * 
     * @see game.utils.Constants.TILE_SIZE
     */
    public static BufferedImage getSprite(int posX, int posY) {
        if (spriteSheet == null) {
            spriteSheet = loadImage(Constants.SPRITE_SHEET_FILENAME);
        }
        return spriteSheet.getSubimage(posX * TILE_SIZE, posY * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

}
