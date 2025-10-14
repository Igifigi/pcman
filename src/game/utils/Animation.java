package game.utils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animation {
    private int frameTickCount; //how long the current frame has been displayed
    private int frameDelay;     //how long to display each frame
    private int currentFrame;   //current frame of animation
    private int amountOfFrames; //length of the animation

    private boolean stopped;    //whether the animation is playing

    private List<Frame> frames = new ArrayList<Frame>();    //list of frames
    
    public Animation(BufferedImage[] frames, int frameDelay) {
        this.frameDelay = frameDelay;
        this.stopped = true;

        for (int i = 0; i < frames.length; i++) {
            addFrame(frames[i], frameDelay);
        }
    }

    public void start() {
        if (!stopped) {
            return;
        }

        if (frames.size() == 0) {
            return;
        }

        stopped = false;
    }

    public void stop() {
        if (frames.size() == 0) {
            return;
        }

        stopped = true;
    }

    public void restart() {
        if (frames.size() == 0) {
            return;
        }

        stopped = false;
        currentFrame = 0;
    }

    public void reset() {
        this.stopped = true;
        this.frameTickCount = 0;
        this.currentFrame = 0;
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

    public void update() {
        if(!stopped) {
            frameTickCount ++;

            if(frameTickCount > frameDelay) {
                frameTickCount = 0;
                currentFrame += 1;

                if(currentFrame > amountOfFrames -1) {
                    currentFrame = 0;
                }
            }
        }
    }
}
