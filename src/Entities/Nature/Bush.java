package Entities.Nature;

import Entities.StaticEntity;
import Game.FocusFarm;

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
        imageMap = FocusFarm.resourceHandler.entitiesResourcesMap.get("bush");
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
                FocusFarm.camera.position.x + position.x * FocusFarm.scale,
                FocusFarm.camera.position.y + position.y * FocusFarm.scale,
                FocusFarm.scaledTileSize,
                FocusFarm.scaledTileSize,
                null);
    }
}
