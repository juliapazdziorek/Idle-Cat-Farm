package Entities.Nature;

import Entities.Entity;
import Game.Farm;
import Resources.Animation;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TreePart extends Entity {

    // images
    public BufferedImage staticImage;
    public Animation clickAnimation;

    public TreePart(Point position, int tileId, Tree parent) {
        super(position, parent);

        // set image and animation
        staticImage = Farm.resourceHandler.entitiesResourcesMap.get("tree").get(String.valueOf(tileId));
        clickAnimation = Farm.resourceHandler.animationFactory.createClickTreePartAnimation(tileId);
        
        // initialize current image
        currentImage = staticImage;

        // set flags
        clickable = true;
    }


    // mouse handling
    @Override
    public void onClick() {
        ((Tree) parent).playTreeClickAnimation();
    }


    // animation
    public void startTreeClickAnimation() {
        if (clickAnimation != null) {
            clickAnimation.resetFrames();
            currentAnimation = clickAnimation;
            isAnimating = true;
            frameCounter = 0;
        }
    }

    // updating
    @Override
    public void update() {
        if (isAnimating && currentAnimation != null) {
            int previousFrame = currentAnimation.getCurrentFrame();
            super.update();

            if (previousFrame == currentAnimation.getNumberOfFrames() - 1 && currentAnimation.getCurrentFrame() == 0) {
                frameCounter++;

                if (frameCounter >= 1) {
                    isAnimating = false;
                    currentAnimation = null;
                    currentImage = staticImage;
                    parent.isAnimating = false;
                }
            }
        } else {
            currentImage = staticImage;
        }
    }
}
