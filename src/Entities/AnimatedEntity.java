package Entities;

import Resources.Animation;

import java.awt.*;

public class AnimatedEntity extends Entity {

    protected Animation animation;

    public AnimatedEntity(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public AnimatedEntity(int positionX, int positionY, Animation animation) {
        // used for one-row animations (water etc.)
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
}
