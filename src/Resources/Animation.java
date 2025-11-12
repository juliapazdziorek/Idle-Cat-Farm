package Resources;

import java.awt.image.BufferedImage;
import java.util.List;

public class Animation {

    private final int frameDelay;
    private BufferedImage[] frames;
    private int currentFrame;
    private final int numberOfFrames;
    private int frameCounter;

    public Animation(BufferedImage image, int numberOfFramesInImage, int numberOfRowsInImage, int rowOfAnimation, int numberOfFrames, int frameDelay) {
        this.frameDelay = frameDelay;
        this.numberOfFrames = numberOfFrames;

        initializeFrames(image, numberOfFramesInImage, numberOfRowsInImage, rowOfAnimation, numberOfFrames);
        currentFrame = 0;
        frameCounter = 0;
    }

    public Animation(List<BufferedImage> frames, int frameDelay) {
        this.frames = frames.toArray(new BufferedImage[0]);
        this.frameDelay = frameDelay;
        this.numberOfFrames = frames.size();

        currentFrame = 0;
        frameCounter = 0;
    }

    private void initializeFrames(BufferedImage image, int numberOfFramesInImage, int numberOfRowsInImage, int rowOfAnimation, int numberOfFrames) {
        int frameWidth = image.getWidth() / numberOfFramesInImage;
        int frameHeight = image.getHeight() / numberOfRowsInImage;
        frames = new BufferedImage[numberOfFrames];

        for (int i = 0; i < numberOfFrames; i++) {
            frames[i] = image.getSubimage(i * frameWidth, rowOfAnimation * frameHeight, frameWidth, frameHeight);
        }
    }


    public BufferedImage getCurrentFrameImage() {
        return frames[currentFrame];
    }

    public void resetFrames() {
        currentFrame = 0;
    }

    public void update() {
        frameCounter++;
        if (frameCounter >= frameDelay) {
            currentFrame = (currentFrame + 1) % frames.length;
            frameCounter = 0;
        }
    }

    public int getNumberOfFrames() {
        return numberOfFrames;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }
}
