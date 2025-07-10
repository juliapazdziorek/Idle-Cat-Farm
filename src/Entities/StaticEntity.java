package Entities;

import Game.FocusFarm;

import java.awt.*;
import java.awt.image.BufferedImage;

public class StaticEntity extends Entity {

    private final BufferedImage bufferedImage;

    public StaticEntity(int positionX, int positionY, BufferedImage bufferedImage) {
        super(positionX, positionY);
        this.bufferedImage = bufferedImage;
    }

    // updating & rendering
    @Override
    public void update() {}

    @Override
    public void render(Graphics2D graphics2D) {
        graphics2D.drawImage(bufferedImage,
                FocusFarm.camera.cameraX + positionX * FocusFarm.scale,
                FocusFarm.camera.cameraY + positionY * FocusFarm.scale,
                FocusFarm.scaledTileSize,
                FocusFarm.scaledTileSize,
                null);
    }
}