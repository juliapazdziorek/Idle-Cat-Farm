package Resources;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Frame-based animation system for sprite animation sequences.
 * Manages timing, frame progression, and sprite sheet extraction for smooth visual effects.
 */
public class Animation {

    private final int frameDelay;
    private BufferedImage[] frames;
    private int currentFrame;
    private final int numberOfFrames;
    private int frameCounter;

    /**
     * Creates animation from a sprite sheet by extracting frames from a specific row.
     * Automatically divides a sprite sheet into equal-sized frames for seamless animation.
     */
    public Animation(BufferedImage image, int numberOfFramesInImage, int numberOfRowsInImage, int rowOfAnimation, int numberOfFrames, int frameDelay) {
        this.frameDelay = frameDelay;
        this.numberOfFrames = numberOfFrames;

        initializeFrames(image, numberOfFramesInImage, numberOfRowsInImage, rowOfAnimation, numberOfFrames);
        currentFrame = 0;
        frameCounter = 0;
    }

    /** Creates animation from pre-extracted frame images. */
    public Animation(List<BufferedImage> frames, int frameDelay) {
        this.frames = frames.toArray(new BufferedImage[0]);
        this.frameDelay = frameDelay;
        this.numberOfFrames = frames.size();

        currentFrame = 0;
        frameCounter = 0;
    }

    /**
     * Extracts individual animation frames from a sprite sheet using precise grid positioning.
     * Calculates frame dimensions and extracts subimages from the specified row.
     */
    private void initializeFrames(BufferedImage image, int numberOfFramesInImage, int numberOfRowsInImage, int rowOfAnimation, int numberOfFrames) {
        int frameWidth = image.getWidth() / numberOfFramesInImage;
        int frameHeight = image.getHeight() / numberOfRowsInImage;
        frames = new BufferedImage[numberOfFrames];

        for (int i = 0; i < numberOfFrames; i++) {
            frames[i] = image.getSubimage(i * frameWidth, rowOfAnimation * frameHeight, frameWidth, frameHeight);
        }
    }


    /** Gets the current frame image for rendering. */
    public BufferedImage getCurrentFrameImage() {
        return frames[currentFrame];
    }

    /** Resets animation to first frame. */
    public void resetFrames() {
        currentFrame = 0;
    }

    /**
     * Updates animation timing and advances frames based on delay settings.
     * Should be called every game update cycle to maintain smooth animation.
     * Automatically loops back to the first frame when a sequence completes.
     */
    public void update() {
        frameCounter++;
        if (frameCounter >= frameDelay) {
            currentFrame = (currentFrame + 1) % frames.length;
            frameCounter = 0;
        }
    }

    /** Returns total number of frames in this animation sequence. */
    public int getNumberOfFrames() {
        return numberOfFrames;
    }

    /** Returns current frame index for animation state tracking. */
    public int getCurrentFrame() {
        return currentFrame;
    }
}
