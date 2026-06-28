package Entities.Nature;

import Entities.Entity;
import Game.Farm;
import Resources.Animation;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FruitTreePart extends Entity {

    private final int tileRow;
    private final int tileCol;

    private final BufferedImage emptyIdleImage;
    private final Animation emptyWiggleAnimation;

    private Animation growthAnimation;
    private Animation wiggleAnimation;
    private Animation dropAnimation;
    private BufferedImage ripeImage;
    private BufferedImage droppedImage;

    public FruitTreePart(Point position, int tileRow, int tileCol, int tileId) {
        super(position);
        this.tileRow = tileRow;
        this.tileCol = tileCol;

        emptyIdleImage = Farm.resourceHandler.entitiesResourcesMap.get("tree").get(String.valueOf(tileId));
        emptyWiggleAnimation = Farm.resourceHandler.animationFactory.createEmptyTreeWiggleTileAnimation(tileRow, tileCol);

        currentImage = emptyIdleImage;
        clickable = true;
    }

    public void loadFruitSprites(String fruitKey) {
        growthAnimation = Farm.resourceHandler.animationFactory.createFruitTreeGrowthTileAnimation(fruitKey, tileRow, tileCol);
        wiggleAnimation = Farm.resourceHandler.animationFactory.createFruitTreeWiggleTileAnimation(fruitKey, tileRow, tileCol);
        dropAnimation = Farm.resourceHandler.animationFactory.createFruitTreeDropTileAnimation(fruitKey, tileRow, tileCol);
        ripeImage = Farm.resourceHandler.animationFactory.getFruitTreeRipeTile(fruitKey, tileRow, tileCol);
        droppedImage = Farm.resourceHandler.animationFactory.getFruitTreeDroppedTile(fruitKey, tileRow, tileCol);
    }

    public void showEmptyIdle() { showStatic(emptyIdleImage); }
    public void showRipe() { showStatic(ripeImage); }
    public void showDropped() { showStatic(droppedImage); }

    public void startEmptyWiggle() { playAnimation(emptyWiggleAnimation); }
    public void startGrowth() { playAnimation(growthAnimation); }
    public void startWiggle() { playAnimation(wiggleAnimation); }
    public void startDrop() { playAnimation(dropAnimation); }

    private void showStatic(BufferedImage image) {
        isAnimating = false;
        currentAnimation = null;
        currentImage = image;
    }

    private void playAnimation(Animation animation) {
        animation.resetFrames();
        currentAnimation = animation;
        isAnimating = true;
        currentImage = animation.getCurrentFrameImage();
    }

    @Override
    public void onClick() {
        ((FruitTree) parent).onClick();
    }
}
