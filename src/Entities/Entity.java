package Entities;

import Game.Farm;
import Resources.Animation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Entity {

    // position
    protected Point position;

    // parts
    public boolean isParent;
    public List<Entity> parts;
    public Entity parent;

    // image & animation
    protected BufferedImage currentImage;

    protected Animation currentAnimation;
    public boolean isAnimating;
    protected int frameCounter;

    // clicks
    public boolean clickable;
    public boolean isClicked;


    public Entity() {}

    public Entity(Point position) {
        this.position = new Point(position);
    }

    public Entity(Point position, int tileId) {
        this(position);
        currentImage = Farm.resourceHandler.tilesMap.get(tileId);
    }

    public Entity(Point position, Animation animation) {
        this(position);
        this.currentAnimation = animation;
        this.isAnimating = true;
    }


    // parts handling
    public void addPart(Entity part) {
        if (parts == null) {
            parts = new ArrayList<>();
        }

        parts.add(part);
        part.setParent(this);
    }

    public void setParent(Entity parent) {
        this.parent = parent;
    }


    // getters
    public Point getPosition() {
        return new Point(position);
    }

    public Point getTilePosition() {
        return new Point(getPosition().x / Farm.tileSize, getPosition().y / Farm.tileSize);
    }


    // mouse handling
    public void onClick() {};

    public boolean isPointInside(int mouseX, int mouseY) {
        // convert mouse screen coordinates to world coordinates
        int worldMouseX = (mouseX - Farm.camera.position.x) / Farm.scale;
        int worldMouseY = (mouseY - Farm.camera.position.y) / Farm.scale;

        int halfTile = Farm.tileSize / 2;
        return worldMouseX >= position.x - halfTile && worldMouseX <= position.x + Farm.tileSize + halfTile &&
               worldMouseY >= position.y + halfTile && worldMouseY <= position.y + Farm.tileSize + halfTile;
    }


    // updating
    public void update() {
        if (isAnimating && currentAnimation != null) {
            currentImage = currentAnimation.getCurrentFrameImage();
            currentAnimation.update();
        }

        if (isParent && parts != null) {
            for (Entity part : parts) {
                part.update();
            }
        }
    }


    // rendering
    public void render(Graphics2D graphics2D) {
        if (currentImage != null && !isParent) {
            graphics2D.drawImage(currentImage,
                    Farm.camera.position.x + position.x * Farm.scale,
                    Farm.camera.position.y + position.y * Farm.scale,
                    Farm.scaledTileSize,
                    Farm.scaledTileSize,
                    null);
        } else if (isParent && parts != null) {
            for (Entity part : parts) {
                part.render(graphics2D);
            }
        }
    }
}
