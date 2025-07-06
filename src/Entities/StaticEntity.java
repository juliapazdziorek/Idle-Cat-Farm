package Entities;

import java.awt.*;
import java.awt.image.BufferedImage;

public class StaticEntity extends Entity {

    private final BufferedImage bufferedImage;

    public StaticEntity(int positionX, int positionY, BufferedImage bufferedImage) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.bufferedImage = bufferedImage;
    }

    // updating & rendering
    @Override
    public void update() {}

    @Override
    public void render(Graphics2D graphics2D, int cameraX, int cameraY, int scale, int scaledTileSize) {
        graphics2D.drawImage(bufferedImage, cameraX + positionX * scale, cameraY + positionY * scale, scaledTileSize, scaledTileSize, null);
    }
}