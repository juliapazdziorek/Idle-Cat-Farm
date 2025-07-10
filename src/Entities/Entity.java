package Entities;

import java.awt.*;

public abstract class Entity {

    protected int positionX;
    protected int positionY;

    public Entity(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    // abstract updating
    public abstract void update();

    // abstract rendering
    public abstract void render(Graphics2D graphics2D);
}
