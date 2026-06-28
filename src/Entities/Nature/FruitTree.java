package Entities.Nature;

import Entities.Entity;
import Game.Farm;
import Game.FarmResourcesHandler.ResourceType;
import Resources.Animation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FruitTree extends Entity {

    public enum State { IDLE, EMPTY_SHAKING, GROWING, RIPE, WIGGLING, DROPPING, FRUIT_READY, BEING_COLLECTED }

    private static final long FRUIT_INTERVAL = 60_000L; // ~1 min

    private final int centerTileX;
    private final int centerTileY;

    private State state;
    private ResourceType currentFruit;
    private long lastFruitTime;

    private Animation emptyWiggleAnimation;
    private Animation growthAnimation;
    private Animation wiggleAnimation;
    private Animation dropAnimation;

    public FruitTree(int centerTileX, int centerTileY) {
        super();
        this.centerTileX = centerTileX;
        this.centerTileY = centerTileY;

        this.position = new Point((centerTileX - 1) * Farm.tileSize, (centerTileY - 1) * Farm.tileSize);
        this.isParent = true;

        loadEmptyTreeClock();
        this.currentFruit = ResourceType.APPLE;
        this.state = State.IDLE;
        this.lastFruitTime = System.currentTimeMillis();
    }

    private void loadEmptyTreeClock() {
        emptyWiggleAnimation = Farm.resourceHandler.animationFactory.createEmptyTreeWiggleAnimation();
    }

    private void loadFruitClocks() {
        String fruitKey = fruitKey(currentFruit);
        growthAnimation = Farm.resourceHandler.animationFactory.createFruitTreeGrowthAnimation(fruitKey);
        wiggleAnimation = Farm.resourceHandler.animationFactory.createFruitTreeWiggleAnimation(fruitKey);
        dropAnimation = Farm.resourceHandler.animationFactory.createFruitTreeDropAnimation(fruitKey);
    }

    private static String fruitKey(ResourceType fruit) {
        return switch (fruit) {
            case PEAR -> "pearTree";
            case PEACH -> "peachTree";
            case ORANGE -> "orangeTree";
            default -> "appleTree";
        };
    }

    private void forEachPart(Consumer<FruitTreePart> action) {
        if (parts == null) {
            return;
        }
        for (Entity part : parts) {
            action.accept((FruitTreePart) part);
        }
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
        super.update();
    }

    private void advance(Animation clock, Runnable onComplete) {
        if (clock == null) {
            onComplete.run();
            return;
        }
        int previousFrame = clock.getCurrentFrame();
        clock.update();
        if (previousFrame == clock.getNumberOfFrames() - 1 && clock.getCurrentFrame() == 0) {
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
        loadFruitClocks();
        growthAnimation.resetFrames();
        String fruitKey = fruitKey(currentFruit);
        forEachPart(part -> {
            part.loadFruitSprites(fruitKey);
            part.startGrowth();
        });
        state = State.GROWING;
        refreshMenu();
    }

    private void backToIdle() {
        state = State.IDLE;
        forEachPart(FruitTreePart::showEmptyIdle);
    }

    private void setRipe() {
        state = State.RIPE;
        forEachPart(FruitTreePart::showRipe);
        refreshMenu();
    }

    private void setFruitReady() {
        state = State.FRUIT_READY;
        forEachPart(FruitTreePart::showDropped);
        refreshMenu();
    }

    @Override
    public void onClick() {
        if (state == State.IDLE) {
            // no fruit yet, just a normal tree shake
            emptyWiggleAnimation.resetFrames();
            forEachPart(FruitTreePart::startEmptyWiggle);
            state = State.EMPTY_SHAKING;
        } else if (state == State.RIPE) {
            if (Math.random() < 0.5) {
                dropAnimation.resetFrames();
                forEachPart(FruitTreePart::startDrop);
                state = State.DROPPING;
            } else {
                wiggleAnimation.resetFrames();
                forEachPart(FruitTreePart::startWiggle);
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
        forEachPart(FruitTreePart::showDropped);
        refreshMenu();
    }

    // called by the cat once it finishes collecting at the tree
    public void onCollected() {
        Farm.farmResourcesHandler.addResource(currentFruit, 3);
        state = State.IDLE;
        forEachPart(FruitTreePart::showEmptyIdle);
        lastFruitTime = System.currentTimeMillis();
    }

    private void refreshMenu() {
        if (Farm.menuPanel != null) {
            Farm.menuPanel.refreshResourcesDisplay();
        }
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
}
