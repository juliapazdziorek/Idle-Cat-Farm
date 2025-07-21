package Entities;

import Game.FocusFarm;

import java.awt.*;

public abstract class Entity {

    protected Point position;
    public boolean clickable;

    public Entity(Point position) {
        this.position = new Point(position);
    }


    // mouse handling
    public abstract void onClick();

    public boolean isPointInside(int x, int y) {
        int scaledX = FocusFarm.camera.cameraX + position.x * FocusFarm.scale;
        int scaledY = FocusFarm.camera.cameraY + position.y * FocusFarm.scale;

        return x >= scaledX && x <= scaledX + FocusFarm.scaledTileSize &&
                y >= scaledY && y <= scaledY + FocusFarm.scaledTileSize;
    }

    // abstract updating
    public abstract void update();

    // abstract rendering
    public abstract void render(Graphics2D graphics2D);
}
