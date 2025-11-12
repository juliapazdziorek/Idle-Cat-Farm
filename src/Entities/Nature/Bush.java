package Entities.Nature;

import Entities.Entity;
import Game.Farm;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

public class Bush extends Entity {

    private final Map<String, BufferedImage> imageMap;

    public Bush(Point position) {
        super(position);

        imageMap = Farm.resourceHandler.entitiesResourcesMap.get("bush");
        this.currentImage = imageMap.get("bush grown");

        clickable = true;
    }

    @Override
    public void update() {

        if (isClicked) {
            frameCounter++;
            if (frameCounter >= 30) { // shrink duration
                isClicked = false;
                currentImage = imageMap.get("bush grown");
                frameCounter = 0;
            }
        }
    }

    @Override
    public void onClick() {
        if (!isClicked) {
            isClicked = true;
            currentImage = imageMap.get("bush shrunken");
            frameCounter = 0;
        }
    }
}