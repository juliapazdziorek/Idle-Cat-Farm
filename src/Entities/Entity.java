package Entities;

import Game.Farm;

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
        int worldMouseX = (mouseX - Farm.camera.position.x) / Farm.scale;
        int worldMouseY = (mouseY - Farm.camera.position.y) / Farm.scale;

        int halfTile = Farm.tileSize / 2;
        return worldMouseX >= position.x - halfTile && worldMouseX <= position.x + Farm.tileSize + halfTile &&
               worldMouseY >= position.y + halfTile && worldMouseY <= position.y + Farm.tileSize + halfTile;
    }

    // abstract updating
    public abstract void update();

    // abstract rendering
    public abstract void render(Graphics2D graphics2D);
}
