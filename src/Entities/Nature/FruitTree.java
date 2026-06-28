package Entities.Nature;

import Entities.Entity;
import Game.Farm;
import Game.FarmResourcesHandler.ResourceType;
import Resources.Animation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class FruitTree extends Entity {

    public enum State { IDLE, EMPTY_SHAKING, GROWING, RIPE, WIGGLING, DROPPING, FRUIT_READY, BEING_COLLECTED }

    private static final long FRUIT_INTERVAL = 60_000L; // ~1 min

    private final int centerTileX;
    private final int centerTileY;

    private State state;
    private ResourceType currentFruit;
    private long lastFruitTime;

    // empty (fruitless) look, shared with decorative trees
    private BufferedImage emptyIdleImage;
    private Animation emptyWiggleAnimation;

    // fruited look, loaded from the rolled fruit's sheet once it starts growing
    private Animation growthAnimation;
    private Animation wiggleAnimation;
    private Animation dropAnimation;
    private BufferedImage ripeImage;
    private BufferedImage droppedImage;

    public FruitTree(int centerTileX, int centerTileY) {
        super();
        this.centerTileX = centerTileX;
        this.centerTileY = centerTileY;

        this.position = new Point((centerTileX - 1) * Farm.tileSize, (centerTileY - 1) * Farm.tileSize);

        // starts as a plain fruitless tree, fruit is rolled later when it grows
        loadEmptyTreeSprites();
        this.currentFruit = ResourceType.APPLE;

        this.state = State.IDLE;
        this.currentImage = emptyIdleImage;
        this.clickable = true;
        this.lastFruitTime = System.currentTimeMillis();
    }

    private void loadEmptyTreeSprites() {
        emptyIdleImage = Farm.resourceHandler.animationFactory.getEmptyTreeIdleImage();
        emptyWiggleAnimation = Farm.resourceHandler.animationFactory.createEmptyTreeWiggleAnimation();
    }

    private void loadFruitSprites() {
        String fruitKey = fruitKey(currentFruit);
        growthAnimation = Farm.resourceHandler.animationFactory.createFruitTreeGrowthAnimation(fruitKey);
        wiggleAnimation = Farm.resourceHandler.animationFactory.createFruitTreeWiggleAnimation(fruitKey);
        dropAnimation = Farm.resourceHandler.animationFactory.createFruitTreeDropAnimation(fruitKey);
        ripeImage = Farm.resourceHandler.animationFactory.getFruitTreeRipeImage(fruitKey);
        droppedImage = Farm.resourceHandler.animationFactory.getFruitTreeDroppedImage(fruitKey);
    }

    private static String fruitKey(ResourceType fruit) {
        return switch (fruit) {
            case PEAR -> "pearTree";
            case PEACH -> "peachTree";
            case ORANGE -> "orangeTree";
            default -> "appleTree";
        };
    }

    @Override
    public void update() {
        switch (state) {
            case IDLE -> {
                if (System.currentTimeMillis() - lastFruitTime >= FRUIT_INTERVAL) {
                    startGrowing();
                }
            }
            case EMPTY_SHAKING -> advance(emptyWiggleAnimation, this::backToIdle);
            case GROWING -> advance(growthAnimation, this::setRipe);
            case WIGGLING -> advance(wiggleAnimation, this::setRipe);
            case DROPPING -> advance(dropAnimation, this::setFruitReady);
            default -> { /* RIPE, FRUIT_READY, BEING_COLLECTED are static */ }
        }
    }

    // play an animation once, run onComplete when it wraps back to the first frame
    private void advance(Animation animation, Runnable onComplete) {
        if (animation == null) {
            onComplete.run();
            return;
        }
        int previousFrame = animation.getCurrentFrame();
        currentImage = animation.getCurrentFrameImage();
        animation.update();
        if (previousFrame == animation.getNumberOfFrames() - 1 && animation.getCurrentFrame() == 0) {
            onComplete.run();
        }
    }

    private void startGrowing() {
        ResourceType rolled = Farm.farmResourcesHandler.getRandomUnlockedFruit();
        if (rolled == null) {
            // no fruit unlocked yet, stay a plain tree and wait another cycle
            lastFruitTime = System.currentTimeMillis();
            return;
        }
        currentFruit = rolled;
        loadFruitSprites();
        growthAnimation.resetFrames();
        currentImage = growthAnimation.getCurrentFrameImage();
        clickable = false;
        state = State.GROWING;
        if (Farm.menuPanel != null) {
            Farm.menuPanel.refreshResourcesDisplay();
        }
    }

    private void backToIdle() {
        state = State.IDLE;
        currentImage = emptyIdleImage;
        clickable = true;
    }

    private void setRipe() {
        state = State.RIPE;
        currentImage = ripeImage;
        clickable = true;
        if (Farm.menuPanel != null) {
            Farm.menuPanel.refreshResourcesDisplay();
        }
    }

    private void setFruitReady() {
        state = State.FRUIT_READY;
        currentImage = droppedImage;
        clickable = false;
        if (Farm.menuPanel != null) {
            Farm.menuPanel.refreshResourcesDisplay();
        }
    }

    @Override
    public void onClick() {
        if (state == State.IDLE) {
            // no fruit yet, just a normal tree shake
            emptyWiggleAnimation.resetFrames();
            clickable = false;
            state = State.EMPTY_SHAKING;
        } else if (state == State.RIPE) {
            clickable = false;
            if (Math.random() < 0.5) {
                dropAnimation.resetFrames();
                state = State.DROPPING;
            } else {
                wiggleAnimation.resetFrames();
                state = State.WIGGLING;
            }
        }
    }

    // marked busy while a cat is on the way
    public void setBeingCollected() {
        state = State.BEING_COLLECTED;
    }

    // cat couldn't finish (e.g. ran out of energy): put the fruit back up for collection
    public void cancelCollection() {
        state = State.FRUIT_READY;
        currentImage = droppedImage;
        clickable = false;
        if (Farm.menuPanel != null) {
            Farm.menuPanel.refreshResourcesDisplay();
        }
    }

    // called by the cat once it finishes collecting at the tree
    public void onCollected() {
        Farm.farmResourcesHandler.addResource(currentFruit, 3);
        state = State.IDLE;
        currentImage = emptyIdleImage;
        clickable = true;
        lastFruitTime = System.currentTimeMillis();
    }

    public boolean isFruitReady() {
        return state == State.FRUIT_READY;
    }

    public State getState() {
        return state;
    }

    public ResourceType getCurrentFruit() {
        return currentFruit;
    }

    public Point getCenterPixel() {
        return new Point(centerTileX * Farm.tileSize + Farm.tileSize / 2,
                centerTileY * Farm.tileSize + Farm.tileSize / 2);
    }

    // walkable tiles around the trunk a cat can stand on to collect
    public List<Point> getAccessPositions() {
        List<Point> positions = new ArrayList<>();
        int[][] candidates = {
            {centerTileX, centerTileY + 2},
            {centerTileX - 1, centerTileY + 1},
            {centerTileX + 1, centerTileY + 1},
            {centerTileX - 1, centerTileY + 2},
            {centerTileX + 1, centerTileY + 2},
            {centerTileX - 2, centerTileY + 1},
            {centerTileX + 2, centerTileY + 1}
        };
        for (int[] candidate : candidates) {
            Point point = new Point(candidate[0], candidate[1]);
            if (isWalkable(point) && !positions.contains(point)) {
                positions.add(point);
            }
        }
        return positions;
    }

    private boolean isWalkable(Point point) {
        return point.x >= 0 && point.x < Farm.mapWidthTiles &&
               point.y >= 0 && point.y < Farm.mapHeightTiles &&
               !Farm.entitiesHandler.map.hasObstacleAt(point.y, point.x);
    }

    @Override
    public void render(Graphics2D graphics2D) {
        if (currentImage == null) {
            return;
        }
        graphics2D.drawImage(currentImage,
                Farm.camera.position.x + position.x * Farm.scale,
                Farm.camera.position.y + position.y * Farm.scale,
                3 * Farm.scaledTileSize,
                3 * Farm.scaledTileSize,
                null);
    }

    @Override
    public boolean isPointInside(int mouseX, int mouseY) {
        int worldMouseX = (mouseX - Farm.camera.position.x) / Farm.scale;
        int worldMouseY = (mouseY - Farm.camera.position.y) / Farm.scale;
        return worldMouseX >= position.x && worldMouseX <= position.x + 3 * Farm.tileSize &&
               worldMouseY >= position.y && worldMouseY <= position.y + 3 * Farm.tileSize;
    }
}
