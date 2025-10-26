package game.utils;

import java.awt.image.BufferedImage;

/**
 * Helper class for use with sprite animations.
 * 
 * <p>
 * Handles passing animation frames from the SpriteSheet to the
 * {@link Animation} class.
 * Stores sprites as {@link BufferedImage}. The intiger {@link #duration} tells
 * {@link Animation} for how
 * many ticks to display this frame.
 * 
 * @see Animation
 * @see game.world.sprites.SpriteSheet
 */
public class Frame {
    private BufferedImage frame;
    private int duration;

    /**
     * Creates a Frame.
     * <p>
     * A {@link Frame} stores a {@link BufferedImage} and a {@link #duration}
     * 
     * @param frame    - image from the spritesheet
     * @param duration - how many animation ticks this frame is displayed
     */
    public Frame(BufferedImage frame, int duration) {
        this.frame = frame;
        this.duration = duration;
    }

    public BufferedImage getFrame() {
        return frame;
    }

    public void setFrame(BufferedImage frame) {
        this.frame = frame;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
