package Entities;

import Game.FocusFarm;

import java.awt.*;
import java.awt.image.BufferedImage;

public class StaticEntity extends Entity {

    private final BufferedImage image;

    public StaticEntity(int positionX, int positionY) {
        super(positionX, positionY);
        this.image = null;
    }

    public StaticEntity(int positionX, int positionY, BufferedImage image) {
        super(positionX, positionY);
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
                    FocusFarm.camera.cameraX + positionX * FocusFarm.scale,
                    FocusFarm.camera.cameraY + positionY * FocusFarm.scale,
                    FocusFarm.scaledTileSize,
                    FocusFarm.scaledTileSize,
                    null);
        }
    }
}