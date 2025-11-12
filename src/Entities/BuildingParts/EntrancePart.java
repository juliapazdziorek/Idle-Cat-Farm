package Entities.BuildingParts;

import Entities.Entity;
import Game.Farm;
import Resources.Animation;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EntrancePart extends Entity {

    private final BufferedImage openImage;
    private final BufferedImage closedImage;
    private final Animation openAnimation;
    private final Animation closeAnimation;
    protected boolean isOpen;

    public EntrancePart(Point position, int tileId, Entrance parentEntrance) {
        super(position);
        this.isOpen = false;
        setParent(parentEntrance);

        openImage = Farm.resourceHandler.entitiesResourcesMap.get("entrances").get(tileId + "open");
        closedImage = Farm.resourceHandler.entitiesResourcesMap.get("entrances").get(tileId + "closed");
        openAnimation = Farm.resourceHandler.animationFactory.createEntranceOpenAnimation(tileId);
        closeAnimation = Farm.resourceHandler.animationFactory.createEntranceCloseAnimation(tileId);

        currentImage = closedImage;
    }

    @Override
    public void update() {
        if (isAnimating) {
            int previousFrame = currentAnimation.getCurrentFrame();
            super.update();

            if (previousFrame == currentAnimation.getNumberOfFrames() - 1 && currentAnimation.getCurrentFrame() == 0) {
                frameCounter++;

                if (frameCounter >= 1) {
                    isAnimating = false;
                    currentAnimation = null;

                    if (isOpen) {
                        isOpen = false;
                        currentImage = closedImage;
                    } else {
                        isOpen = true;
                        currentImage = openImage;
                    }

                    ((Entrance) parent).CompleteAnimation();
                }
            }
        }
    }


    public void startOpenAnimation() {
        if (!isAnimating && !isOpen) {
            currentAnimation = openAnimation;
            currentAnimation.resetFrames();
            isAnimating = true;
            frameCounter = 0;
        }
    }

    public void startCloseAnimation() {
        if (!isAnimating && isOpen) {
            currentAnimation = closeAnimation;
            currentAnimation.resetFrames();
            isAnimating = true;
            frameCounter = 0;
        }
    }
}