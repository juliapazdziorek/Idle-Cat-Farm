package Entities;

import Game.Farm;
import Resources.Animation;

import java.awt.*;

public class AnimatedEntity extends Entity {

    protected Animation animation;

    public AnimatedEntity(Point position) {
        super(position);
    }

    public AnimatedEntity(Point position, Animation animation) {
        super(position); // used for tiles with one animation (ex. water)
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
            graphics2D.drawImage(animation.getCurrentFrameImage(),
                    Farm.camera.position.x + position.x * Farm.scale,
                    Farm.camera.position.y + position.y * Farm.scale,
                    Farm.scaledTileSize,
                    Farm.scaledTileSize,
                    null);
        }
    }
}
