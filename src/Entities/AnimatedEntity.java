package Entities;

import Game.FocusFarm;
import Resources.Animation;

import java.awt.*;

public class AnimatedEntity extends Entity {

    protected Animation animation;

    public AnimatedEntity(int positionX, int positionY) {
        super(positionX, positionY);
    }

    public AnimatedEntity(int positionX, int positionY, Animation animation) {
        // used for one-row animations (water etc.)
        super(positionX, positionY);
        this.animation = animation;
    }


    // mouse handling
    @Override
    public void onClick() {}


    // updating & rendering
    @Override
    public void update() {
        if (animation != null) {
            animation.update();
        }
    }

    @Override
    public void render(Graphics2D graphics2D) {
        if (animation != null) {
            graphics2D.drawImage(animation.getCurrentFrame(),
                    FocusFarm.camera.cameraX + positionX * FocusFarm.scale,
                    FocusFarm.camera.cameraY + positionY * FocusFarm.scale,
                    FocusFarm.scaledTileSize,
                    FocusFarm.scaledTileSize,
                    null);
        }
    }
}
