package game.world.sprites;

import game.utils.Tuple;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public final class WallSprite {

    private static final Tuple[] coordinates = {
            new Tuple(4, 2),    //turn LU 0
            new Tuple(5, 2),    //turn UR 1
            new Tuple(6, 2),    //dwall L 2
            new Tuple(7, 2),    //dWall U 3
            new Tuple(8, 2),    //dWall R 4
            new Tuple(9, 2),    //dWall D 5
            new Tuple(10, 2),   //sWall L 6
            new Tuple(11, 2),   //sWall U 7
            new Tuple(12, 2),   //sWall R 8
            new Tuple(13, 2),   //sWall D 9
            new Tuple(1, 3),    //dTurn DR 10
            new Tuple(2, 3),    //dTurn LD 11
            new Tuple(3, 3),    //dTurn LU 12
            new Tuple(4, 3),    //dTurn UR 13
            new Tuple(5, 3),    //turn DR 14
            new Tuple(6, 3),    //turn LD 15
            new Tuple(0,4),     //inner turn 16
            new Tuple(1,4),     //inner turn 17
            new Tuple(2,4),     //inner turn 18
            new Tuple(3,4),     //inner turn 19
            new Tuple(4,4),     //inner dTurn 20
            new Tuple(5,4),     //inner dTurn 21
            new Tuple(6,4),     //inner dTurn 22
            new Tuple(7,4)      //inner dTurn 23
    };

    private static BufferedImage[] getWalls() {
        ArrayList<BufferedImage> walls = new ArrayList<>();
        for (Tuple t : coordinates) {
            walls.add(SpriteSheet.getSprite(t.first, t.second));
        }
        return walls.toArray(new BufferedImage[0]);
    }

    private static final BufferedImage[] WALLS = getWalls();
    public static final BufferedImage EMPTY_TILE = SpriteSheet.getSprite(15, 2);

    public static BufferedImage getWallTile(int wallType) {
        return (wallType < 0) ? EMPTY_TILE : WALLS[wallType];
        // if (wallType < 0) {
        //     return EMPTY_TILE;
        // }
        // return WALLS[wallType];
    }

    public static void testShit() {
        // for (int i = 0; i < WALLS.length; i++) {
        //     System.out.println("idx: " + i + "; WALL: " + WALLS[i]);
        // }

        // int max = -100000;
        // int min = 100;
        // for (int i = 0; i < Board.walls.length; i++) {
        //     for (int j = 0; j < Board.walls[i].length; j++) {
        //         max = Math.max(max, Board.walls[i][j]);
        //         min = Math.min(min, Board.walls[i][j]);
        //     }
        // }

        // System.out.println(max + " " + min);
    }

}
