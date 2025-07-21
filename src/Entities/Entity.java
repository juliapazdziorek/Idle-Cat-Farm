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

    public boolean isPointInside(int mouseX, int mouseY) {
        // convert mouse screen coordinates to world coordinates
        int worldMouseX = (mouseX - FocusFarm.camera.position.x) / FocusFarm.scale;
        int worldMouseY = (mouseY - FocusFarm.camera.position.y) / FocusFarm.scale;

        int halfTile = FocusFarm.tileSize / 2;
        return worldMouseX >= position.x - halfTile && worldMouseX <= position.x + FocusFarm.tileSize + halfTile &&
               worldMouseY >= position.y + halfTile && worldMouseY <= position.y + FocusFarm.tileSize + halfTile;
    }

    // abstract updating
    public abstract void update();

    // abstract rendering
    public abstract void render(Graphics2D graphics2D);
}
