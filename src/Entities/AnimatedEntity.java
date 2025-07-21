package Entities;

import Game.FocusFarm;
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
            graphics2D.drawImage(animation.getCurrentFrame(),
                    FocusFarm.camera.cameraX + position.x * FocusFarm.scale,
                    FocusFarm.camera.cameraY + position.y * FocusFarm.scale,
                    FocusFarm.scaledTileSize,
                    FocusFarm.scaledTileSize,
                    null);
        }
    }
}
