package game.utils;

public final class Constants {
    public static final Tuple DEFAULT_WINDOW_SIZE = new Tuple(1500, 1090);
    public static final int FPS = 60;
    public static final int TPS = (int) (60 / 10);

    /*-------------------SPRITES-------------------- */
    public static final int TILE_SIZE = 32;
    public static final String SPRITE_SHEET_FILENAME = "src/resources/textures/spritesheet.png";

    public static final int ANIMATION_SPEED = 5;

    public static final Tuple ARCH_STARTING_POSITION = new Tuple(12, 15);
    public static final Tuple UBUNTU_STARTING_POSITION = new Tuple(13, 15);
    public static final Tuple GENTOO_STARTING_POSITION = new Tuple(14, 15);
    public static final Tuple MINT_STARTING_POSITION = new Tuple(15, 15);
    public static final Tuple PLAYER_STARTING_POSITION = new Tuple(1, 1);

    public static final int MINT_DISTANCE = 4;
    public static final Tuple GENTOO_SECOND_TARGET = new Tuple(1, 29);

    public static final Tuple NULL_MOVEMENT = new Tuple(0, 0);

    public static final int INITIAL_ORBS = 300;
    public static final int POWERUP_DURATION = 5000;
}
