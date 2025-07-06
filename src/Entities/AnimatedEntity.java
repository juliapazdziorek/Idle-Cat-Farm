package Entities;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimatedEntity extends Entity {

    protected Animation animation;

    public AnimatedEntity(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public AnimatedEntity(int positionX, int positionY, Animation animation) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.animation = animation;
    }

    // updating & rendering
    @Override
    public void update() {
        if (animation != null) {
            animation.update();
        }
    }

    @Override
    public void render(Graphics2D graphics2D, int cameraX, int cameraY, int scale, int scaledTileSize) {
        if (animation != null) {
            graphics2D.drawImage(animation.getCurrentFrame(), cameraX + positionX * scale, cameraY + positionY * scale, scaledTileSize, scaledTileSize, null);
        }
    }


    public static class Animation {

        // image
        private final BufferedImage image;
        private final int numberOfFramesInImage;
        private final int numberOfRowsInImage;
        private final int rowOfAnimation;
        private final int numberOfFrames;
        private final int frameDelay;

        // animation
        private BufferedImage[] frames;
        private int currentFrame;
        private int frameCounter;


        public Animation(BufferedImage image, int numberOfFramesInImage, int numberOfRowsInImage, int rowOfAnimation, int numberOfFrames, int frameDelay) {
            this.image = image;
            this.numberOfFramesInImage = numberOfFramesInImage;
            this.numberOfRowsInImage = numberOfRowsInImage;
            this.rowOfAnimation = rowOfAnimation;
            this.numberOfFrames = numberOfFrames;
            this.frameDelay = frameDelay;

            initializeFrames();
            currentFrame = 0;
            frameCounter = 0;
        }

        private void initializeFrames() {
            int frameWidth = image.getWidth() / numberOfFramesInImage;
            int frameHeight = image.getHeight() / numberOfRowsInImage;
            frames = new BufferedImage[numberOfFrames];

            for (int i = 0; i < numberOfFrames; i++) {
                frames[i] = image.getSubimage(i * frameWidth, rowOfAnimation * frameHeight, frameWidth, frameHeight);
            }
        }


        // animation handling
        public BufferedImage getCurrentFrame() {
            return frames[currentFrame];
        }

        public void resetFrames() {
            currentFrame = 0;
        }


        // animation updating
        public void update() {
            frameCounter++;
            if (frameCounter >= frameDelay) {
                currentFrame = (currentFrame + 1) % frames.length;
                frameCounter = 0;
            }
        }
    }
}
