package Entities;

import Game.FocusFarm;

import java.awt.*;
import java.awt.image.BufferedImage;

public class StaticEntity extends Entity {

    private final BufferedImage image;

    public StaticEntity(Point position) {
        super(position);
        this.image = null;
    }
    
    public StaticEntity(Point position, BufferedImage image) {
        super(position);
        this.image = image;
    }


    // mouse handling
    @Override
    public void onClick() {}


    // updating & rendering
    @Override
    public void update() {}

    @Override
    public void render(Graphics2D graphics2D) {
        if (image != null) {
            graphics2D.drawImage(image,
                    FocusFarm.camera.cameraX + position.x * FocusFarm.scale,
                    FocusFarm.camera.cameraY + position.y * FocusFarm.scale,
                    FocusFarm.scaledTileSize,
                    FocusFarm.scaledTileSize,
                    null);
        }
    }
}