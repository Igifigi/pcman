package game.utils;

public final class Constants {
    public static final Tuple DEFAULT_WINDOW_SIZE = new Tuple(1500, 1090);
    public static final int FPS = 60;
    public static final int PLAYER_TPS = (int) (60 / 10);
    public static final int ENEMY_TPS = (int) (60 / 6);

    /*-------------------SPRITES-------------------- */
    public static final int TILE_SIZE = 32;
    public static final String SPRITE_SHEET_FILENAME = "src/resources/textures/spritesheet.png";

    public static final int ANIMATION_SPEED = 5;

    public static final Tuple ARCH_STARTING_POSITION = new Tuple(12, 15);
    public static final Tuple UBUNTU_STARTING_POSITION = new Tuple(13, 15);
    public static final Tuple GENTOO_STARTING_POSITION = new Tuple(14, 15);
    public static final Tuple MINT_STARTING_POSITION = new Tuple(15, 15);
    public static final Tuple PLAYER_STARTING_POSITION = new Tuple(13, 17);

    public static final int MINT_DISTANCE = 4;
    public static final Tuple GENTOO_SECOND_TARGET = new Tuple(1, 29);

    public static final Tuple NULL_MOVEMENT = new Tuple(0, 0);

    public static final int INITIAL_PLAYER_HEALTH = 3;
    public static final int INITIAL_ORBS = 300;
    public static final int POWERUP_DURATION = 5000;

    public static final String[] WIN_MESSAGES = {
            "You survived the penguin invasion! Windows prevails!",
            "Congrats! No Linux was harmed… yet.",
            "All updates installed. PC Man leveled up!",
            "You collected all the packets! Telemetry says: B)",
            "Windows XP, Vista, 7, 10, 11… ALL salute you!",
            "The sudo rm -rf trick worked… everyone ran away!",
            "Penguins 0 — PC Man 1. Game over, penguins!",
            "Freedom achieved! Arch is crying somewhere.",
            "Gentoo compiled… and lost.",
            "Mint tried to control your desktop… you resisted!",
            "Blue screens avoided. Victory secured!",
            "Power-up victory dance activated!",
            "Your Windows license is safe. For now.",
            "PC Man installed the update without errors — impressive!",
            "i use scala btw",
            "Even Ubuntu is clapping. Kinda.",
            "You dodged Arch like a true GUI wizard.",
            "All orbs collected. Windows updates complete!",
            "The penguin rebellion failed miserably.",
            "Your Ctrl+Alt+Del reflexes saved the day.",
            "Victory! Now go update your antivirus.",
    };

    public static final String[] LOSS_MESSAGES = {
            "You got caught! The penguins are victorious.",
            "Gentoo optimized your defeat… painfully.",
            "Arch says: 'I told you so.'",
            "Mint got you… it's just like Windows, but meaner.",
            "PC Man crashed. Windows XP is shaking its head.",
            "Blue screen incoming… oh wait, it's just Linux.",
            "You lost a health point. Time to cry in safe mode.",
            "Penguins: 1 — You: 0.",
            "Ctrl+Alt+Del won't save you now.",
            "Ubuntu smiles. You don't.",
            "Your desktop will never be the same…",
            "i use scala btw",
            "Power-up wasted. Linux laughs at you.",
            "All updates failed. Try again next life.",
            "You collided with Arch. System panic imminent.",
            "PC Man died… at least your cookies are safe.",
            "Gentoo compiled… a new way to defeat you.",
            "Mint says: 'See? Windows wasn't that great after all.'",
            "You got rekt by free software.",
            "Your HP is 0. Time to install Windows again.",
            "The penguin revolution continues… without you.",
    };

}
