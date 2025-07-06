package Entities;

import java.awt.*;

public abstract class Entity {

    protected int positionX;
    protected int positionY;

    // abstract updating
    public abstract void update();

    // abstract rendering
    public abstract void render(Graphics2D graphics2D, int cameraX, int cameraY, int scale, int scaledTileSize);
}
