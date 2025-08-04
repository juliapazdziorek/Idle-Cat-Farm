package Entities;

import Game.Farm;

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
                    Farm.camera.position.x + position.x * Farm.scale,
                    Farm.camera.position.y + position.y * Farm.scale,
                    Farm.scaledTileSize,
                    Farm.scaledTileSize,
                    null);
        }
    }
}