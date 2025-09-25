package Entities.Objects;

import Entities.Characters.FarmCat;
import Entities.Entity;
import Game.Farm;
import Resources.Animation;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BedPart extends Entity {

    // images and animations
    public BufferedImage staticImage;
    public Animation sleepingAnimationWhite;
    public Animation sleepingAnimationGrey;
    public Animation sleepingAnimationGinger;
    public Animation sleepingAnimationTricolor;

    private final int tileId;
    private final boolean isTopPart;
    private boolean isSleeping;

    public BedPart(Point position, int tileId, boolean isTopPart) {
        super(position, tileId);
        this.tileId = tileId;
        this.isTopPart = isTopPart;
        this.isSleeping = false;

        // set static image
        staticImage = Farm.resourceHandler.tilesMap.get(tileId);
        currentImage = staticImage;

        // create sleeping animations for all cat colors (for both top and bottom parts)
        sleepingAnimationWhite = Farm.resourceHandler.animationFactory.createBedSleepingAnimation(tileId, FarmCat.FarmCatColor.WHITE);
        sleepingAnimationGrey = Farm.resourceHandler.animationFactory.createBedSleepingAnimation(tileId, FarmCat.FarmCatColor.GREY);
        sleepingAnimationGinger = Farm.resourceHandler.animationFactory.createBedSleepingAnimation(tileId, FarmCat.FarmCatColor.GINGER);
        sleepingAnimationTricolor = Farm.resourceHandler.animationFactory.createBedSleepingAnimation(tileId, FarmCat.FarmCatColor.TRICOLOR);
    }

    public void startSleepingAnimation(FarmCat.FarmCatColor catColor) {
        if (!isSleeping) {
            Animation selectedAnimation = getSleepingAnimationForColor(catColor);
            if (selectedAnimation != null) {
                selectedAnimation.resetFrames();
                currentAnimation = selectedAnimation;
                isAnimating = true;
                frameCounter = 0;
            }
            this.isSleeping = true;
        }
    }

    public void stopSleepingAnimation() {
        if (isSleeping) {
            currentAnimation = null;
            isAnimating = false;
            currentImage = staticImage;
            this.isSleeping = false;
        }
    }

    private Animation getSleepingAnimationForColor(FarmCat.FarmCatColor catColor) {
        return switch (catColor) {
            case WHITE -> sleepingAnimationWhite;
            case GREY -> sleepingAnimationGrey;
            case GINGER -> sleepingAnimationGinger;
            case TRICOLOR -> sleepingAnimationTricolor;
        };
    }

    @Override
    public void update() {
        if (isAnimating && currentAnimation != null) {
            super.update();
        } else {
            currentImage = staticImage;
        }
    }

    public boolean isTopPart() {
        return isTopPart;
    }
}