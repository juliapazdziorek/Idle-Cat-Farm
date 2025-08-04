package Entities.Nature;

import Entities.StaticEntity;
import Game.Farm;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

public class Bush extends StaticEntity {

    // images
    private final Map<String, BufferedImage> imageMap;
    private BufferedImage currentImage;

    // click handling
    private boolean isClicked = false;
    private int frameCounter = 0;

    public Bush(Point position) {
        super(position);
        imageMap = Farm.resourceHandler.entitiesResourcesMap.get("bush");
        this.currentImage = imageMap.get("bush grown");

        clickable = true;
    }


    // mouse handling
    public void onClick() {
        if (!isClicked) {
            isClicked = true;
            currentImage = imageMap.get("bush shrunken");
            frameCounter = 0;
        }
    }


    // updating & rendering
    @Override
    public void update() {
        if (isClicked) {
            frameCounter++;
            int shrinkDuration = 100;
            if (frameCounter >= shrinkDuration) {
                isClicked = false;
                currentImage = imageMap.get("bush grown");
                frameCounter = 0;
            }
        }
    }

    @Override
    public void render(Graphics2D graphics2D) {
        graphics2D.drawImage(currentImage,
                Farm.camera.position.x + position.x * Farm.scale,
                Farm.camera.position.y + position.y * Farm.scale,
                Farm.scaledTileSize,
                Farm.scaledTileSize,
                null);
    }
}
