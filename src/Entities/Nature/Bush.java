package Entities.Nature;

import Entities.StaticEntity;
import Game.FocusFarm;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bush extends StaticEntity {

    private final BufferedImage normalImage;
    private final BufferedImage clickedImage;
    private BufferedImage currentImage;
    
    private boolean isClicked = false;
    private int frameCounter = 0;

    public Bush(Point position) {
        super(position);
        this.normalImage = FocusFarm.resourceHandler.entitiesResourcesMap.get("bush").get(0);
        this.clickedImage = FocusFarm.resourceHandler.entitiesResourcesMap.get("bush").get(1);
        this.currentImage = normalImage;

        clickable = true;
    }

    public void onClick() {
        if (!isClicked) {
            isClicked = true;
            currentImage = clickedImage;
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
                currentImage = normalImage;
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
