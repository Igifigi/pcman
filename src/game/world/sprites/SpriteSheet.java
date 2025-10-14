package game.world.sprites;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.utils.Constants;

// non-public = visible only in game.world.sprites package
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

    public static BufferedImage getSprite(int posX, int posY) {
        if (spriteSheet == null) {
            spriteSheet = loadImage(Constants.SPRITE_SHEET_FILENAME);
        }
        return spriteSheet.getSubimage(posX * TILE_SIZE, posY * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

}
