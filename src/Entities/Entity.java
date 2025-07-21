package Entities;

import Game.FocusFarm;

import java.awt.*;

public abstract class Entity {

    protected int positionX;
    protected int positionY;

    public boolean clickable;

    public Entity(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    // mouse handling
    public abstract void onClick();

    public boolean isPointInside(int x, int y) {
        int scaledX = FocusFarm.camera.cameraX + positionX * FocusFarm.scale;
        int scaledY = FocusFarm.camera.cameraY + positionY * FocusFarm.scale;

        return x >= scaledX && x <= scaledX + FocusFarm.scaledTileSize &&
                y >= scaledY && y <= scaledY + FocusFarm.scaledTileSize;
    }

    // abstract updating
    public abstract void update();

    // abstract rendering
    public abstract void render(Graphics2D graphics2D);
}
