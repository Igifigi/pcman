package game.utils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for a frame-based animation composed of BufferedImage frames.
 *
 * <p>This class stores an ordered list of frames and advances the displayed frame
 * as update() is called. Each frame is displayed for {@link #frameDelay} ticks.
 * When the last frame has been shown, the animation wraps back to the
 * first frame.</p>
 *
 * <p>Usage example:
 * <pre>
 * BufferedImage[] frames = ...;
 * Animation anim = new Animation(frames, 5); // each frame shown for 5 update calls
 * // In game loop:
 * anim.update();
 * BufferedImage current = anim.getSprite(); //frame to be drawn by {@link GameFrame}
 * </pre>
 * </p>
 */
public class Animation {
    private int frameTickCount; // how long the current frame has been displayed
    private int frameDelay; // how long to display each frame
    private int currentFrame; // current frame of animation
    private int amountOfFrames; // length of the animation

    private boolean stopped; // whether the animation is playing

    private List<Frame> frames = new ArrayList<Frame>(); // list of frames

    /**
     * Creates a new animation.
     * 
     * @param frames - list of BufferedImages to be displayed in sequence
     * @param frameDelay - how long to display each frame
     */
    public Animation(BufferedImage[] frames, int frameDelay) {
        this.frameDelay = frameDelay;
        this.stopped = false;
        amountOfFrames = frames.length;
        for (int i = 0; i < frames.length; i++) {
            addFrame(frames[i], frameDelay);
        }
    }

    private void addFrame(BufferedImage frame, int duration) {
        if (duration <= 0) {
            System.err.println("Invalid duration: " + duration);
            throw new RuntimeException("Invalid duration: " + duration);
        }

        frames.add(new Frame(frame, duration));
        currentFrame = 0;
    }

    public BufferedImage getSprite() {
        return frames.get(currentFrame).getFrame();
    }

    /**
     * Updates the animation.
     * <p>
     * If {@link #frameDelay} ticks (update() calls) have passed, advance to the next frame.
     * <p>
     * If the last frame is reached, go back to the first after {@link #frameDelay} ticks.
     */
    public void update() {
        if (!stopped) {
            frameTickCount++;

            if (frameTickCount > frameDelay) {
                frameTickCount = 0;
                currentFrame += 1;

                if (currentFrame > amountOfFrames - 1) {
                    currentFrame = 0;
                }
            }
        }
    }
}
