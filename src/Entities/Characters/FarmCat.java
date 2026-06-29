package Entities.Characters;

import Entities.Entity;
import Entities.FarmResources.Crop;
import Entities.Nature.FruitTree;
import Entities.Objects.Bed;
import Entities.Objects.WaterTray;
import Entities.Objects.Well;
import Game.Farm;
import Game.FarmResourcesHandler.ResourceType;
import Game.FieldsHandler;
import Map.Field;
import Resources.Animation;
import Pathfinding.Node;
import Map.Map;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


public class FarmCat extends Entity {
    private CatActionState previousActionState = CatActionState.IDLE;

    // properties
    public final int catWidth = 42;
    public final int catHeight = 42;

        // enums
    public enum FarmCatColor {WHITE, GREY, GINGER, TRICOLOR}
    private enum FarmCatState {STANDING, WALKING, RUNNING, TILLING, CHOPPING, WATERING}
    private enum FarmCatFacing {DOWN, UP, RIGHT, LEFT}
    public enum FarmingLevel {LVL0, LVL1, LVL2, LVL3, LVL_STAR}
    
    // speech bubble system enums
    public enum SpeechBubbleType { ZZZ, WATERING_CAN }
    public enum SpeechBubbleState { HIDDEN, OPENING, LOOPING, CLOSING }

    // color
    private final FarmCatColor color;
    
    // cat stats
    private int energy; // 0-100
    private int wateringCan; // 0-100
    private FarmingLevel farmingLevel;
    private int experience; // current experience points

    // cat state
    private FarmCatState farmCatState;
    private FarmCatFacing farmCatFacing;

    // animations
    HashMap<String, Animation> animations;
    
    // speech bubble for tired cats
    private Animation speechBubbleAnimation;
    private SpeechBubbleType currentBubbleType;
    private SpeechBubbleState bubbleState;
    private int waterHintTimer; // keeps an explicit no-water hint up briefly (frames)

    // pathfinding
    private List<Node> currentPath;
    private int currentPathIndex;
    private boolean isFollowingPath;
    private int targetX;
    private int targetY;

    // moving
    private boolean isMoving;
    private boolean isActuallyMoving;
    private boolean isRunning;
    private FarmCatFacing lastDirection;

    private int moveCounter;
    private int directionChangeCounter;
    
    // planting action system
    public enum CatActionState { IDLE, PLANTING, WATERING, COLLECTING_FRUIT, GOING_TO_WELL, REFILLING, GOING_TO_SLEEP, SLEEPING, TIRED, FILLING_TRAY }
    private CatActionState actionState;
    private List<Point> plantingPositions;
    private int currentPlantingIndex;
    private ResourceType currentCropType;
    private Field.FieldType currentFieldType;
    private boolean isAtTillingPosition;
    private int tillingAnimationCounter;
    private static final int tillingAnimationDuration = 60;

    // watering action system
    private List<Point> wateringPositions;
    private int currentWateringIndex;
    private boolean isAtWateringPosition;
    private Animation wateringWaterAnimation;
    private int wateringAnimationCounter;
    private static final int wateringAnimationDuration = 60;

    // fruit collection action system
    private FruitTree targetFruitTree;
    private boolean isAtFruitTree;
    private int fruitCollectionCounter;
    private static final int fruitCollectionDuration = 60;

    // water tray filling action system
    private WaterTray targetWaterTray;
    private boolean isAtWaterTray;
    private int trayFillCounter;
    private static final int trayFillDuration = 60;

    // well refill action system
    private Well targetWell;
    private Point targetWellPosition;
    private int refillAnimationCounter;
    private static final int refillAnimationDuration = 120;
    
    // for bed sleeping functionality
    private Bed targetBed;
    private Point targetBedPosition;
    private int sleepEnergyTimer;
    
    // visibility
    private boolean visible;

    public FarmCat(int tileX, int tileY, FarmCatColor color) {
        super(new Point(tileX * Farm.tileSize + Farm.tileSize / 2, tileY * Farm.tileSize + Farm.tileSize / 2));
        this.color = color;

        // initialize cat stats
        this.energy = 100;
        this.wateringCan = 100;
        this.farmingLevel = FarmingLevel.LVL0;
        this.experience = 0;

        // get animations based on cat color
        animations = Farm.resourceHandler.animationFactory.createCatAnimationMap(color);

        // initialize cat state variables
        farmCatFacing = FarmCatFacing.DOWN;
        farmCatState = FarmCatState.STANDING;
        currentAnimation = animations.get("farmCatStandingDown");

        // initialize pathfinding and moving variables
        lastDirection = FarmCatFacing.DOWN;
        
        // initialize a planting action system
        actionState = CatActionState.IDLE;
        plantingPositions = new ArrayList<>();
        currentPlantingIndex = 0;
        isAtTillingPosition = false;
        tillingAnimationCounter = 0;

        // initialize a watering action system
        wateringPositions = new ArrayList<>();
        currentWateringIndex = 0;
        isAtWateringPosition = false;
        wateringWaterAnimation = null;
        wateringAnimationCounter = 0;

        // initialize a fruit collection action system
        targetFruitTree = null;
        isAtFruitTree = false;
        fruitCollectionCounter = 0;

        // initialize a well refill action system
        targetWell = null;
        targetWellPosition = null;
        refillAnimationCounter = 0;
        
        // initialize a sleeping action system
        targetBed = null;
        targetBedPosition = null;
        sleepEnergyTimer = 0;
        
        // speech bubble initialization
        speechBubbleAnimation = null;
        currentBubbleType = null;
        bubbleState = SpeechBubbleState.HIDDEN;

        visible = true;
    }


    // pathfinding
    public void moveToTile(int tileX, int tileY) {
        int startTileX = position.x / Farm.tileSize;
        int startTileY = position.y / Farm.tileSize;

        currentPath = Map.pathfinder.findPath(startTileX, startTileY, tileX, tileY);

        if (currentPath != null && currentPath.size() > 1) {
            currentPathIndex = 1;
            isFollowingPath = true;
            
            isRunning = currentPath.size() > 7;
            moveCounter = 0;
            
            Node nextNode = currentPath.get(currentPathIndex);
            targetX = nextNode.x * Farm.tileSize + Farm.tileSize / 2;
            targetY = nextNode.y * Farm.tileSize + Farm.tileSize / 2;
        }
    }

    private void followPath() {
        if (!isFollowingPath || currentPath == null) {
            isActuallyMoving = false;
            return;
        }

        // handle movement
        int remainingTiles = currentPath.size() - currentPathIndex;
        isRunning = remainingTiles > 3;
        moveCounter++;
        int moveInterval = isRunning ? 1 : 2;
        
        if (moveCounter < moveInterval) {
            isActuallyMoving = false;
            return;
        }
        moveCounter = 0;
        isActuallyMoving = true;

        int deltaX = targetX - position.x;
        int deltaY = targetY - position.y;
        int moveSpeed = 1;

        // waypoint reached
        if (Math.abs(deltaX) < moveSpeed && Math.abs(deltaY) < moveSpeed) {
            position.x = targetX;
            position.y = targetY;
            currentPathIndex++;

            // end path following
            if (currentPathIndex >= currentPath.size()) {
                isFollowingPath = false;
                currentPath = null;
                isActuallyMoving = false;
                return;
            }

            Node nextNode = currentPath.get(currentPathIndex);
            targetX = nextNode.x * Farm.tileSize + Farm.tileSize / 2;
            targetY = nextNode.y * Farm.tileSize + Farm.tileSize / 2;
            deltaX = targetX - position.x;
            deltaY = targetY - position.y;
        }

        // move
        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            if (deltaX > 0) {
                position.x += moveSpeed;
                if (isRunning) {
                    runWithDirection(FarmCatFacing.RIGHT);
                } else {
                    walkWithDirection(FarmCatFacing.RIGHT);
                }
            } else {
                position.x -= moveSpeed;
                if (isRunning) {
                    runWithDirection(FarmCatFacing.LEFT);
                } else {
                    walkWithDirection(FarmCatFacing.LEFT);
                }
            }
        } else {
            if (deltaY > 0) {
                position.y += moveSpeed;
                if (isRunning) {
                    runWithDirection(FarmCatFacing.DOWN);
                } else {
                    walkWithDirection(FarmCatFacing.DOWN);
                }
            } else {
                position.y -= moveSpeed;
                if (isRunning) {
                    runWithDirection(FarmCatFacing.UP);
                } else {
                    walkWithDirection(FarmCatFacing.UP);
                }
            }
        }
    }


    // moving
    private void walkWithDirection(FarmCatFacing direction) {
        farmCatState = FarmCatState.WALKING;
        changeDirectionWithCooldown(direction);
        isMoving = true;
    }

    private void runWithDirection(FarmCatFacing direction) {
        farmCatState = FarmCatState.RUNNING;
        changeDirectionWithCooldown(direction);
        isMoving = true;
    }

    private void changeDirectionWithCooldown(FarmCatFacing newDirection) {
        if (lastDirection != newDirection) {
            if (directionChangeCounter <= 0) {
                lastDirection = newDirection;
                farmCatFacing = newDirection;
                directionChangeCounter = 8;
            }
        } else {
            farmCatFacing = newDirection;
        }
        directionChangeCounter = Math.max(0, directionChangeCounter - 1);
    }


    // animation handling
    private void setAnimation() {
        Animation previousAnimation = currentAnimation;

        if (!isMoving) {
            if (farmCatState != FarmCatState.TILLING && farmCatState != FarmCatState.CHOPPING && farmCatState != FarmCatState.WATERING) {
                farmCatState = FarmCatState.STANDING;
                switch (farmCatFacing) {
                    case UP: {
                        currentAnimation = animations.get("farmCatStandingUp");
                        break;
                    }
                    case DOWN: {
                        currentAnimation = animations.get("farmCatStandingDown");
                        break;
                    }
                    case LEFT: {
                        currentAnimation = animations.get("farmCatStandingLeft");
                        break;
                    }
                    case RIGHT: {
                        currentAnimation = animations.get("farmCatStandingRight");
                        break;
                    }
                }
            }

            if (farmCatState == FarmCatState.TILLING) {
                switch (farmCatFacing) {
                    case UP: {
                        currentAnimation = animations.get("farmCatTillingUp");
                        break;
                    }
                    case DOWN: {
                        currentAnimation = animations.get("farmCatTillingDown");
                        break;
                    }
                    case LEFT: {
                        currentAnimation = animations.get("farmCatTillingLeft");
                        break;
                    }
                    case RIGHT: {
                        currentAnimation = animations.get("farmCatTillingRight");
                        break;
                    }
                }
            }

            if (farmCatState == FarmCatState.CHOPPING) {
                switch (farmCatFacing) {
                    case UP: {
                        currentAnimation = animations.get("farmCatChoppingUp");
                        break;
                    }
                    case DOWN: {
                        currentAnimation = animations.get("farmCatChoppingDown");
                        break;
                    }
                    case LEFT: {
                        currentAnimation = animations.get("farmCatChoppingLeft");
                        break;
                    }
                    case RIGHT: {
                        currentAnimation = animations.get("farmCatChoppingRight");
                        break;
                    }
                }
            }

            if (farmCatState == FarmCatState.WATERING) {
                switch (farmCatFacing) {
                    case UP: {
                        currentAnimation = animations.get("farmCatWateringUp");
                        break;
                    }
                    case DOWN: {
                        currentAnimation = animations.get("farmCatWateringDown");
                        break;
                    }
                    case LEFT: {
                        currentAnimation = animations.get("farmCatWateringLeft");
                        break;
                    }
                    case RIGHT: {
                        currentAnimation = animations.get("farmCatWateringRight");
                        break;
                    }
                }
            }

        } else {
            if (farmCatState == FarmCatState.WALKING) {
                switch (farmCatFacing) {
                    case UP: {
                        currentAnimation = animations.get("farmCatWalkingUp");
                        break;
                    }
                    case DOWN: {
                        currentAnimation = animations.get("farmCatWalkingDown");
                        break;
                    }
                    case LEFT: {
                        currentAnimation = animations.get("farmCatWalkingLeft");
                        break;
                    }
                    case RIGHT: {
                        currentAnimation = animations.get("farmCatWalkingRight");
                        break;
                    }
                }
            } else {
                switch (farmCatFacing) {
                    case UP: {
                        currentAnimation = animations.get("farmCatRunningUp");
                        break;
                    }
                    case DOWN: {
                        currentAnimation = animations.get("farmCatRunningDown");
                        break;
                    }
                    case LEFT: {
                        currentAnimation = animations.get("farmCatRunningLeft");
                        break;
                    }
                    case RIGHT: {
                        currentAnimation = animations.get("farmCatRunningRight");
                        break;
                    }
                }
            }
        }
        
        // reset animation if it changed for smooth transitions
        if (previousAnimation != currentAnimation && currentAnimation != null) {
            currentAnimation.resetFrames();
        }
    }

    public void resetAnimations() {
        animations.values().forEach(Animation::resetFrames);
    }


    // idling
    public boolean isIdle() {
        return actionState == CatActionState.IDLE && !isFollowingPath;
    }
    
    public boolean isSleeping() {
        return actionState == CatActionState.SLEEPING;
    }
    
    public boolean isTired() {
        return actionState == CatActionState.TIRED;
    }

    public boolean canStartEnergyFreeAction() {
        return (actionState == CatActionState.IDLE || actionState == CatActionState.TIRED) && !isFollowingPath;
    }

    private void endEnergyFreeAction() {
        if (hasEnoughEnergyForAction()) {
            actionState = CatActionState.IDLE;
        } else {
            becomeTired();
        }
    }

    public CatActionState getActionState() {
        return actionState;
    }
    
    public String getLevelDisplayText() {
        return switch (farmingLevel) {
            case LVL0 -> "lvl 0";
            case LVL1 -> "lvl 1";
            case LVL2 -> "lvl 2";
            case LVL3 -> "lvl 3";
            case LVL_STAR -> "lvl star";
        };
    }
    
    public void setActionState(CatActionState state) {
        this.actionState = state;
    }
    
    public void setTargetBed(Bed bed) {
        this.targetBed = bed;
    }


    // planting action system
    public void startPlantingAction(List<Point> cropPositions, ResourceType cropType, Field.FieldType fieldType) {
        if (!isIdle()) {
            return;
        }

        actionState = CatActionState.PLANTING;
        plantingPositions = new ArrayList<>(cropPositions);
        currentCropType = cropType;
        currentFieldType = fieldType;
        currentPlantingIndex = 0;

        if (!plantingPositions.isEmpty()) {
            moveToNextPlantingPosition();
        }
    }

    private void moveToNextPlantingPosition() {
        if (currentPlantingIndex >= plantingPositions.size()) {

            // planting complete
            Field field = FieldsHandler.getFieldByType(currentFieldType);
            if (field != null) {
                field.setCatWorkingOnField(false);
            }
            
            actionState = CatActionState.IDLE;
            plantingPositions.clear();
            return;
        }
        
    if (!hasEnoughEnergyForAction()) {
            Field field = FieldsHandler.getFieldByType(currentFieldType);
            if (field != null) {
                field.setCatWorkingOnField(false);
            }
            
            becomeTired();
            plantingPositions.clear();
            return;
        }

        // visit the nearest remaining crop next
        selectNearestRemaining(plantingPositions, currentPlantingIndex);

        Point targetCropPosition = plantingPositions.get(currentPlantingIndex);
        Point tillingPosition = findTillingPosition(targetCropPosition);

        if (tillingPosition != null) {
            int tillingTileX = tillingPosition.x / Farm.tileSize;
            int tillingTileY = tillingPosition.y / Farm.tileSize;
            moveToTile(tillingTileX, tillingTileY);
            isAtTillingPosition = false;
        } else {
            currentPlantingIndex++;
            moveToNextPlantingPosition();
        }
    }

    private Point findTillingPosition(Point cropPosition) {
        int cropTileX = cropPosition.x;
        int cropTileY = cropPosition.y;

        // check adjacent tiles
        int[] adjacentTilePositions = {
                cropTileX, cropTileY - 1,
                cropTileX, cropTileY + 1,
                cropTileX - 1, cropTileY,
                cropTileX + 1, cropTileY
        };

        // find the closest accessible position
        Point closestPosition = null;
        int shortestPathLength = Integer.MAX_VALUE;

        int startTileX = position.x / Farm.tileSize;
        int startTileY = position.y / Farm.tileSize;

        for (int i = 0; i < adjacentTilePositions.length; i += 2) {
            int adjTileX = adjacentTilePositions[i];
            int adjTileY = adjacentTilePositions[i + 1];

            List<Node> path = Map.pathfinder.findPath(startTileX, startTileY, adjTileX, adjTileY);
            
            if (path != null && !path.isEmpty()) {
                int pathLength = path.size();
                if (pathLength < shortestPathLength) {
                    shortestPathLength = pathLength;
                    closestPosition = new Point(adjTileX * Farm.tileSize + Farm.tileSize / 2,
                                               adjTileY * Farm.tileSize + Farm.tileSize / 2);
                }
            }
        }

        return closestPosition;
    }

    // shortest A* path length to a tile next to the crop
    private int shortestAccessPathLength(int fromTileX, int fromTileY, Point cropPosition) {
        int[] adjacentTilePositions = {
                cropPosition.x, cropPosition.y - 1,
                cropPosition.x, cropPosition.y + 1,
                cropPosition.x - 1, cropPosition.y,
                cropPosition.x + 1, cropPosition.y
        };

        int shortestPathLength = Integer.MAX_VALUE;
        for (int i = 0; i < adjacentTilePositions.length; i += 2) {
            List<Node> path = Map.pathfinder.findPath(fromTileX, fromTileY,
                    adjacentTilePositions[i], adjacentTilePositions[i + 1]);
            if (path != null && !path.isEmpty()) {
                shortestPathLength = Math.min(shortestPathLength, path.size());
            }
        }
        return shortestPathLength;
    }

    // move the nearest remaining position to fromIndex
    private void selectNearestRemaining(List<Point> positions, int fromIndex) {
        int catTileX = position.x / Farm.tileSize;
        int catTileY = position.y / Farm.tileSize;

        int bestIndex = fromIndex;
        int bestLength = Integer.MAX_VALUE;
        for (int i = fromIndex; i < positions.size(); i++) {
            int length = shortestAccessPathLength(catTileX, catTileY, positions.get(i));
            if (length < bestLength) {
                bestLength = length;
                bestIndex = i;
            }
        }

        if (bestIndex != fromIndex) {
            Point nearest = positions.get(bestIndex);
            positions.set(bestIndex, positions.get(fromIndex));
            positions.set(fromIndex, nearest);
        }
    }

    private void updatePlantingAction() {
        if (!isFollowingPath && !isAtTillingPosition) {

            // cat reached the tilling position, start tilling animation
            isAtTillingPosition = true;
            tillingAnimationCounter = 0;
            farmCatState = FarmCatState.TILLING;

            // reset animations for smooth transition
            resetAnimations();

            // face the crop position when tilling
            Point cropPosition = plantingPositions.get(currentPlantingIndex);
            int cropPixelX = cropPosition.x * Farm.tileSize + Farm.tileSize / 2;
            int cropPixelY = cropPosition.y * Farm.tileSize + Farm.tileSize / 2;

            int deltaX = cropPixelX - position.x;
            int deltaY = cropPixelY - position.y;
            int threshold = Farm.tileSize / 4;
            
            if (Math.abs(deltaX) > Math.abs(deltaY) && Math.abs(deltaX) > threshold) {
                if (deltaX > 0) {
                    farmCatFacing = FarmCatFacing.RIGHT;
                    currentAnimation = animations.get("farmCatTillingRight");
                } else {
                    farmCatFacing = FarmCatFacing.LEFT;
                    currentAnimation = animations.get("farmCatTillingLeft");
                }
            } else if (Math.abs(deltaY) > threshold) {
                if (deltaY > 0) {
                    farmCatFacing = FarmCatFacing.DOWN;
                    currentAnimation = animations.get("farmCatTillingDown");
                } else {
                    farmCatFacing = FarmCatFacing.UP;
                    currentAnimation = animations.get("farmCatTillingUp");
                }
            } else {
                switch (farmCatFacing) {
                    case RIGHT -> currentAnimation = animations.get("farmCatTillingRight");
                    case LEFT -> currentAnimation = animations.get("farmCatTillingLeft");
                    case DOWN -> currentAnimation = animations.get("farmCatTillingDown");
                    case UP -> currentAnimation = animations.get("farmCatTillingUp");
                }
            }
        }

        if (isAtTillingPosition) {
            tillingAnimationCounter++;
            if (tillingAnimationCounter >= tillingAnimationDuration) {

                // check and consume energy when tilling completes
                if (!consumeEnergyForTilling()) {
                    tillingAnimationCounter = 0;
                    farmCatState = FarmCatState.STANDING;
                    
                    // set field as not being worked on
                    Field field = FieldsHandler.getFieldByType(currentFieldType);
                    if (field != null) {
                        field.setCatWorkingOnField(false);
                    }
                    
                    becomeTired();
                    plantingPositions.clear();
                    return;
                }

                // tilling completed successfully, gain experience
                addExperience(1);

                // tilling animation completes, create the crop
                Point cropPosition = plantingPositions.get(currentPlantingIndex);
                
                // use field method to create crop at position
                Field field = FieldsHandler.getFieldByType(currentFieldType);
                if (field != null) {
                    field.createCropAtPosition(cropPosition, currentCropType);
                }

                // reset animation for smoother transition
                resetAnimations();
                tillingAnimationCounter = 0;
                
                // move to the next planting position
                currentPlantingIndex++;
                isAtTillingPosition = false;
                farmCatState = FarmCatState.STANDING;
                
                // check energy
                if (!hasEnoughEnergyForAction()) {
                    Field currentField = FieldsHandler.getFieldByType(currentFieldType);
                    if (currentField != null) {
                        currentField.setCatWorkingOnField(false);
                    }
                    becomeTired();
                    plantingPositions.clear();

                } else if (currentPlantingIndex >= plantingPositions.size()) {

                    // planting complete
                    Field currentField = FieldsHandler.getFieldByType(currentFieldType);
                    if (currentField != null) {
                        currentField.setCatWorkingOnField(false);
                    }

                    actionState = CatActionState.IDLE;
                    plantingPositions.clear();
                } else {
                    // continue to next position
                    moveToNextPlantingPosition();
                }
            }
        }
    }

    // face a target tile and play the given action animation (e.g. "farmCatWatering")
    private void faceTargetAndAnimate(int targetPixelX, int targetPixelY, String animationPrefix) {
        int deltaX = targetPixelX - position.x;
        int deltaY = targetPixelY - position.y;
        int threshold = Farm.tileSize / 4;

        if (Math.abs(deltaX) > Math.abs(deltaY) && Math.abs(deltaX) > threshold) {
            if (deltaX > 0) {
                farmCatFacing = FarmCatFacing.RIGHT;
                currentAnimation = animations.get(animationPrefix + "Right");
            } else {
                farmCatFacing = FarmCatFacing.LEFT;
                currentAnimation = animations.get(animationPrefix + "Left");
            }
        } else if (Math.abs(deltaY) > threshold) {
            if (deltaY > 0) {
                farmCatFacing = FarmCatFacing.DOWN;
                currentAnimation = animations.get(animationPrefix + "Down");
            } else {
                farmCatFacing = FarmCatFacing.UP;
                currentAnimation = animations.get(animationPrefix + "Up");
            }
        } else {
            switch (farmCatFacing) {
                case RIGHT -> currentAnimation = animations.get(animationPrefix + "Right");
                case LEFT -> currentAnimation = animations.get(animationPrefix + "Left");
                case DOWN -> currentAnimation = animations.get(animationPrefix + "Down");
                case UP -> currentAnimation = animations.get(animationPrefix + "Up");
            }
        }
    }

    // watering action system
    public void startWateringAction(List<Point> unwateredPositions, ResourceType cropType, Field.FieldType fieldType) {
        if (!canStartEnergyFreeAction()) {
            return;
        }

        actionState = CatActionState.WATERING;
        wateringPositions = new ArrayList<>(unwateredPositions);
        currentCropType = cropType;
        currentFieldType = fieldType;
        currentWateringIndex = 0;

        if (!wateringPositions.isEmpty()) {
            moveToNextWateringPosition();
        }
    }

    private void moveToNextWateringPosition() {
        if (currentWateringIndex >= wateringPositions.size() || !hasEnoughWaterForAction()) {

            // watering complete, or cat ran out of water - stop and free the field
            Field field = FieldsHandler.getFieldByType(currentFieldType);
            if (field != null) {
                field.setCatWorkingOnField(false);
            }

            endEnergyFreeAction();
            wateringPositions.clear();
            return;
        }

        // water the nearest remaining crop next
        selectNearestRemaining(wateringPositions, currentWateringIndex);

        Point targetCropPosition = wateringPositions.get(currentWateringIndex);
        Point wateringPosition = findTillingPosition(targetCropPosition);

        if (wateringPosition != null) {
            int wateringTileX = wateringPosition.x / Farm.tileSize;
            int wateringTileY = wateringPosition.y / Farm.tileSize;
            moveToTile(wateringTileX, wateringTileY);
            isAtWateringPosition = false;
        } else {
            currentWateringIndex++;
            moveToNextWateringPosition();
        }
    }

    private void updateWateringAction() {
        if (!isFollowingPath && !isAtWateringPosition) {

            // cat reached the watering position, start watering animation
            isAtWateringPosition = true;
            wateringAnimationCounter = 0;
            farmCatState = FarmCatState.WATERING;
            resetAnimations();

            // face the crop being watered
            Point cropPosition = wateringPositions.get(currentWateringIndex);
            int cropPixelX = cropPosition.x * Farm.tileSize + Farm.tileSize / 2;
            int cropPixelY = cropPosition.y * Farm.tileSize + Farm.tileSize / 2;
            faceTargetAndAnimate(cropPixelX, cropPixelY, "farmCatWatering");
            wateringWaterAnimation = createWateringWaterAnimation();
        }

        if (isAtWateringPosition) {
            wateringAnimationCounter++;
            if (wateringAnimationCounter >= wateringAnimationDuration) {

                // check and consume water when watering completes; if empty, stop and
                // wait for the player to refill the cat at the well
                if (!consumeWaterForWatering()) {
                    wateringAnimationCounter = 0;
                    farmCatState = FarmCatState.STANDING;

                    Field field = FieldsHandler.getFieldByType(currentFieldType);
                    if (field != null) {
                        field.setCatWorkingOnField(false);
                    }

                    endEnergyFreeAction();
                    isAtWateringPosition = false;
                    wateringPositions.clear();
                    return;
                }

                // watering succeeded, gain experience (same as tilling)
                addExperience(1);

                // watering completed for this crop - let it start growing
                Point cropPosition = wateringPositions.get(currentWateringIndex);
                Field field = FieldsHandler.getFieldByType(currentFieldType);
                if (field != null) {
                    Crop crop = field.getCrops().get(cropPosition);
                    if (crop != null) {
                        crop.setWatered();
                    }
                }

                resetAnimations();
                wateringAnimationCounter = 0;
                currentWateringIndex++;
                isAtWateringPosition = false;
                farmCatState = FarmCatState.STANDING;

                if (!hasEnoughWaterForAction()) {

                    // out of water - stop, remaining crops wait for a refilled cat
                    Field currentField = FieldsHandler.getFieldByType(currentFieldType);
                    if (currentField != null) {
                        currentField.setCatWorkingOnField(false);
                    }
                    endEnergyFreeAction();
                    wateringPositions.clear();

                } else if (currentWateringIndex >= wateringPositions.size()) {

                    // watering complete
                    Field currentField = FieldsHandler.getFieldByType(currentFieldType);
                    if (currentField != null) {
                        currentField.setCatWorkingOnField(false);
                    }
                    endEnergyFreeAction();
                    wateringPositions.clear();

                } else {
                    // continue to next crop
                    moveToNextWateringPosition();
                }
            }
        }
    }

    private Animation createWateringWaterAnimation() {
        return switch (farmCatFacing) {
            case UP -> Farm.resourceHandler.animationFactory.createWateringWaterUpAnimation();
            case DOWN -> Farm.resourceHandler.animationFactory.createWateringWaterDownAnimation();
            case LEFT, RIGHT -> Farm.resourceHandler.animationFactory.createWateringWaterSideAnimation();
        };
    }

    // well refill action system
    public boolean startGoingToWell() {
        if (!canStartEnergyFreeAction()) {
            return false;
        }

        if (Farm.entitiesHandler == null || Farm.entitiesHandler.map == null || Farm.entitiesHandler.map.wells == null) {
            return false;
        }

        int startTileX = position.x / Farm.tileSize;
        int startTileY = position.y / Farm.tileSize;

        Well nearestWell = null;
        Point nearestWellPosition = null;
        int shortestPathLength = Integer.MAX_VALUE;

        for (Well well : Farm.entitiesHandler.map.wells) {
            for (Point access : well.getAccessPositions()) {
                List<Node> path = Map.pathfinder.findPath(startTileX, startTileY, access.x, access.y);
                if (path != null && !path.isEmpty() && path.size() < shortestPathLength) {
                    shortestPathLength = path.size();
                    nearestWell = well;
                    nearestWellPosition = access;
                }
            }
        }

        if (nearestWell == null) {
            return false;
        }

        actionState = CatActionState.GOING_TO_WELL;
        targetWell = nearestWell;
        targetWellPosition = nearestWellPosition;
        refillAnimationCounter = 0;
        moveToTile(nearestWellPosition.x, nearestWellPosition.y);
        return true;
    }

    private void updateWellAction() {
        if (actionState == CatActionState.GOING_TO_WELL) {
            if (!isFollowingPath && targetWellPosition != null
                    && position.x == targetWellPosition.x * Farm.tileSize + Farm.tileSize / 2
                    && position.y == targetWellPosition.y * Farm.tileSize + Farm.tileSize / 2) {

                // reached the well, start the refill animation
                actionState = CatActionState.REFILLING;
                refillAnimationCounter = 0;
                farmCatState = FarmCatState.WATERING;
                resetAnimations();

                // face toward the well while refilling
                if (targetWell != null && targetWell.getReferenceTilePosition() != null) {
                    Point reference = targetWell.getReferenceTilePosition();
                    int wellPixelX = reference.x * Farm.tileSize + Farm.tileSize / 2;
                    int wellPixelY = reference.y * Farm.tileSize + Farm.tileSize / 2;
                    faceTargetAndAnimate(wellPixelX, wellPixelY, "farmCatWatering");
                }
            }

        } else if (actionState == CatActionState.REFILLING) {
            farmCatState = FarmCatState.WATERING;
            refillAnimationCounter++;

            if (refillAnimationCounter >= refillAnimationDuration) {
                setWateringCan(100);
                refillAnimationCounter = 0;
                farmCatState = FarmCatState.STANDING;
                endEnergyFreeAction();
                targetWell = null;
                targetWellPosition = null;

                // refilling the watering can also grants experience
                addExperience(1);

                if (Farm.menuPanel != null) {
                    Farm.menuPanel.refreshResourcesDisplay();
                }
            }
        }
    }

    // fruit collection action system
    public boolean startFruitCollectionAction(FruitTree tree) {
        if (!isIdle() || tree == null) {
            return false;
        }

        int startTileX = position.x / Farm.tileSize;
        int startTileY = position.y / Farm.tileSize;

        // walk to the nearest reachable tile around the tree
        Point nearestAccess = null;
        int shortestPathLength = Integer.MAX_VALUE;
        for (Point access : tree.getAccessPositions()) {
            List<Node> path = Map.pathfinder.findPath(startTileX, startTileY, access.x, access.y);
            if (path != null && !path.isEmpty() && path.size() < shortestPathLength) {
                shortestPathLength = path.size();
                nearestAccess = access;
            }
        }

        if (nearestAccess == null) {
            return false;
        }

        actionState = CatActionState.COLLECTING_FRUIT;
        targetFruitTree = tree;
        tree.setBeingCollected();
        isAtFruitTree = false;
        fruitCollectionCounter = 0;
        moveToTile(nearestAccess.x, nearestAccess.y);
        return true;
    }

    private void updateFruitCollectionAction() {
        if (targetFruitTree == null) {
            actionState = CatActionState.IDLE;
            return;
        }

        if (!isFollowingPath && !isAtFruitTree) {

            // cat reached the tree, start collecting (reuse the chopping pose)
            isAtFruitTree = true;
            fruitCollectionCounter = 0;
            farmCatState = FarmCatState.CHOPPING;
            resetAnimations();

            Point treeCenter = targetFruitTree.getCenterPixel();
            faceTargetAndAnimate(treeCenter.x, treeCenter.y, "farmCatChopping");
        }

        if (isAtFruitTree) {
            fruitCollectionCounter++;
            if (fruitCollectionCounter >= fruitCollectionDuration) {

                // collecting costs energy by level (same as tilling); a tired cat stops and frees the tree
                if (!consumeEnergyForTilling()) {
                    fruitCollectionCounter = 0;
                    farmCatState = FarmCatState.STANDING;
                    isAtFruitTree = false;
                    targetFruitTree.cancelCollection();
                    targetFruitTree = null;
                    becomeTired();
                    return;
                }

                // collecting finished, gain experience (same as tilling); the tree hands over its fruit
                addExperience(1);
                targetFruitTree.onCollected();

                resetAnimations();
                fruitCollectionCounter = 0;
                isAtFruitTree = false;
                farmCatState = FarmCatState.STANDING;
                actionState = CatActionState.IDLE;
                targetFruitTree = null;
            }
        }
    }

    // water tray filling action system
    public boolean startFillingTrayAction(WaterTray tray) {
        if (!canStartEnergyFreeAction() || tray == null) {
            return false;
        }

        int startTileX = position.x / Farm.tileSize;
        int startTileY = position.y / Farm.tileSize;

        // walk to the nearest reachable tile next to the tray
        Point nearestAccess = null;
        int shortestPathLength = Integer.MAX_VALUE;
        for (Point access : tray.getAccessPositions()) {
            List<Node> path = Map.pathfinder.findPath(startTileX, startTileY, access.x, access.y);
            if (path != null && !path.isEmpty() && path.size() < shortestPathLength) {
                shortestPathLength = path.size();
                nearestAccess = access;
            }
        }

        if (nearestAccess == null) {
            return false;
        }

        actionState = CatActionState.FILLING_TRAY;
        targetWaterTray = tray;
        isAtWaterTray = false;
        trayFillCounter = 0;
        moveToTile(nearestAccess.x, nearestAccess.y);
        return true;
    }

    private void updateFillTrayAction() {
        if (targetWaterTray == null) {
            actionState = CatActionState.IDLE;
            return;
        }

        if (!isFollowingPath && !isAtWaterTray) {

            // reached the tray, start the watering animation
            isAtWaterTray = true;
            trayFillCounter = 0;
            farmCatState = FarmCatState.WATERING;
            resetAnimations();

            Point reference = targetWaterTray.getReferenceTilePosition();
            if (reference != null) {
                int trayPixelX = reference.x * Farm.tileSize + Farm.tileSize / 2;
                int trayPixelY = reference.y * Farm.tileSize + Farm.tileSize / 2;
                faceTargetAndAnimate(trayPixelX, trayPixelY, "farmCatWatering");
            }
            wateringWaterAnimation = createWateringWaterAnimation();
        }

        if (isAtWaterTray) {
            trayFillCounter++;
            if (trayFillCounter >= trayFillDuration) {

                // filling costs 3x the per-crop water; out of water stops the action
                if (!consumeWaterForTrayFill()) {
                    trayFillCounter = 0;
                    farmCatState = FarmCatState.STANDING;
                    isAtWaterTray = false;
                    targetWaterTray = null;
                    endEnergyFreeAction();
                    return;
                }

                // filling finished, gain experience (same as watering); fill the tray
                addExperience(1);
                targetWaterTray.setFull();

                resetAnimations();
                trayFillCounter = 0;
                isAtWaterTray = false;
                farmCatState = FarmCatState.STANDING;
                targetWaterTray = null;
                endEnergyFreeAction();

                if (Farm.menuPanel != null) {
                    Farm.menuPanel.refreshResourcesDisplay();
                }
            }
        }
    }

    // sleeping action methods
    public void startGoingToSleep(Bed bed) {
        if (!isIdle() && actionState != CatActionState.TIRED) {
            return;
        }

        if (!bed.reserveBed(this)) {
            return;
        }

        actionState = CatActionState.GOING_TO_SLEEP;
        targetBed = bed;
        targetBedPosition = bed.getToBedPosition();

        if (targetBedPosition != null) {

            // move directly to the bed position
            moveToTile(targetBedPosition.x, targetBedPosition.y);
        } else {

            // no valid position, cancel sleep action and free the bed
            if (targetBed != null) {
                targetBed.setCatAwake();
            }
            actionState = CatActionState.IDLE;
            targetBed = null;
        }
    }

    private void reachBedAndSleep() {
        if (targetBed != null && targetBedPosition != null && position.x == targetBedPosition.x * Farm.tileSize + Farm.tileSize / 2 && position.y == targetBedPosition.y * Farm.tileSize + Farm.tileSize / 2) {
            setVisible(false);
            targetBed.setCatSleeping(this);
            actionState = CatActionState.SLEEPING;
            sleepEnergyTimer = 0;
        }

        targetBedPosition = null;
    }
    
    // sleep management methods
    public boolean tryGoToSleep() {
        if (!isIdle() && actionState != CatActionState.TIRED) {
            return false;
        }

        if (Farm.entitiesHandler != null && Farm.entitiesHandler.map != null && Farm.entitiesHandler.map.beds != null) {
            ArrayList<Bed> availableBeds = new ArrayList<>();
            for (Bed bed : Farm.entitiesHandler.map.beds) {
                if (bed.isAvailable() && bed.hasValidPosition()) {
                    availableBeds.add(bed);
                }
            }

            int startTileX = position.x / Farm.tileSize;
            int startTileY = position.y / Farm.tileSize;
            for (Bed bed : availableBeds) {
                Point bedPos = bed.getToBedPosition();
                List<Node> path = Map.pathfinder.findPath(startTileX, startTileY, bedPos.x, bedPos.y);
                if (path != null && !path.isEmpty()) {
                    startGoingToSleep(bed);
                    return true;
                }
            }
        }
        return false;
    }
    
    public void wakeUp() {
        if ((actionState == CatActionState.SLEEPING || actionState == CatActionState.GOING_TO_SLEEP) && targetBed != null) {
            if (actionState == CatActionState.SLEEPING) {
                Point bedPosition = targetBed.getToBedPosition();
                moveToTile(bedPosition.x, bedPosition.y);

            } else {
                // cat is walking to bed - stop pathfinding and stay at current position
                currentPath = null;
                currentPathIndex = 0;
                isFollowingPath = false;
            }

            // wake up the cat and free the bed
            setVisible(true);
            targetBed.setCatAwake();

            // reset to idle state
            actionState = CatActionState.IDLE;
            targetBed = null;
            targetBedPosition = null;
            sleepEnergyTimer = 0;
        }
    }

    private void updateSleepingAction() {
        if (actionState == CatActionState.GOING_TO_SLEEP) {
            if (!isFollowingPath && targetBedPosition != null && position.x == targetBedPosition.x * Farm.tileSize + Farm.tileSize / 2 && position.y == targetBedPosition.y * Farm.tileSize + Farm.tileSize / 2) {
                reachBedAndSleep();
            }
        } else if (actionState == CatActionState.SLEEPING) {
            restoreEnergyWhileSleeping();
        }
    }

    // getters
    public List<Node> getCurrentPath() {
        return currentPath;
    }


    // updating & rendering
    @Override
    public void update() {
        if ((previousActionState == CatActionState.IDLE || previousActionState == CatActionState.TIRED)
            && !(actionState == CatActionState.IDLE || actionState == CatActionState.TIRED)
            && isShowingSpeechBubble() && currentBubbleType == SpeechBubbleType.ZZZ && bubbleState != SpeechBubbleState.CLOSING) {
            closeSpeechBubble();
        }

        // refresh the farm panel's cat status whenever a cat changes what it's doing
        if (actionState != previousActionState && Farm.menuPanel != null) {
            Farm.menuPanel.refreshResourcesDisplay();
        }
        previousActionState = actionState;

        if (actionState == CatActionState.PLANTING) {
            updatePlantingAction();
        } else if (actionState == CatActionState.WATERING) {
            updateWateringAction();
        } else if (actionState == CatActionState.COLLECTING_FRUIT) {
            updateFruitCollectionAction();
        } else if (actionState == CatActionState.FILLING_TRAY) {
            updateFillTrayAction();
        } else if (actionState == CatActionState.GOING_TO_WELL || actionState == CatActionState.REFILLING) {
            updateWellAction();
        } else if (actionState == CatActionState.GOING_TO_SLEEP || actionState == CatActionState.SLEEPING) {
            updateSleepingAction();
        } else if (actionState == CatActionState.TIRED) {
            updateTiredAction();
        }
        
        followPath();
        setAnimation();
        isMoving = isActuallyMoving;
        
        if (currentAnimation != null) {
            currentImage = currentAnimation.getCurrentFrameImage();
            currentAnimation.update();
        }

        if (wateringWaterAnimation != null && farmCatState == FarmCatState.WATERING && (isAtWateringPosition || isAtWaterTray)) {
            wateringWaterAnimation.update();
        }
        
        // idle hints, lowest priority
        if (isIdle() && !isShowingSpeechBubble()) {
            if (!hasEnoughEnergyForAction()) {
                showSpeechBubble(SpeechBubbleType.ZZZ);
            } else if (!hasEnoughWaterForAction()) {
                showSpeechBubble(SpeechBubbleType.WATERING_CAN);
            }
        }

        // dismiss the watering-can hint once busy or refilled (an explicit tray-fill
        // hint stays up until its timer expires, since it needs 3x water not 1x)
        if (waterHintTimer > 0) {
            waterHintTimer--;
        }
        if (isShowingSpeechBubble() && currentBubbleType == SpeechBubbleType.WATERING_CAN
                && bubbleState != SpeechBubbleState.CLOSING
                && (!isIdle() || (hasEnoughWaterForAction() && waterHintTimer <= 0))) {
            closeSpeechBubble();
        }
        if (isShowingSpeechBubble() && speechBubbleAnimation != null) {
            speechBubbleAnimation.update();
            if (bubbleState == SpeechBubbleState.OPENING && speechBubbleAnimation.getCurrentFrame() == speechBubbleAnimation.getNumberOfFrames() - 1) {
                startSpeechBubbleLooping();
            } else if (bubbleState == SpeechBubbleState.CLOSING && speechBubbleAnimation.getCurrentFrame() == speechBubbleAnimation.getNumberOfFrames() - 1) {
                hideSpeechBubble();
            }
        }
    }
    
    // tired cat methods
    private void becomeTired() {
        actionState = CatActionState.TIRED;
        farmCatState = FarmCatState.STANDING;
        farmCatFacing = FarmCatFacing.DOWN;
        
        // show ZZZ speech bubble
        showSpeechBubble(SpeechBubbleType.ZZZ);
    }
    
    private void updateTiredAction() {
        farmCatState = FarmCatState.STANDING;
        farmCatFacing = FarmCatFacing.DOWN;
    }
    
    private void showSpeechBubble(SpeechBubbleType bubbleType) {
        if (bubbleState == SpeechBubbleState.HIDDEN || bubbleState == SpeechBubbleState.CLOSING) {
            currentBubbleType = bubbleType;
            try {
                SpeechBubbleType type = Objects.requireNonNull(bubbleType);
                if (type == SpeechBubbleType.ZZZ) {
                    speechBubbleAnimation = Farm.resourceHandler.animationFactory.createSpeechBubbleZzzOpeningAnimation();
                } else if (type == SpeechBubbleType.WATERING_CAN) {
                    speechBubbleAnimation = Farm.resourceHandler.animationFactory.createSpeechBubbleWaterCanOpeningAnimation();
                }
                bubbleState = SpeechBubbleState.OPENING;
            } catch (Exception e) {
                hideSpeechBubble();
            }
        }
    }
    
    private void startSpeechBubbleLooping() {
        if (currentBubbleType != null) {
            if (currentBubbleType == SpeechBubbleType.ZZZ) {
                speechBubbleAnimation = Farm.resourceHandler.animationFactory.createSpeechBubbleZzzAnimation();
            } else if (currentBubbleType == SpeechBubbleType.WATERING_CAN) {
                speechBubbleAnimation = Farm.resourceHandler.animationFactory.createSpeechBubbleWaterCanAnimation();
            }
            bubbleState = SpeechBubbleState.LOOPING;
        }
    }
    
    // shown when the player asks to fill a tray but no idle cat has enough water
    public void showWaterShortageHint() {
        if (canStartEnergyFreeAction()) {
            waterHintTimer = 180; // ~3s, so the hint isn't dismissed by the 1x-water check
            showSpeechBubble(SpeechBubbleType.WATERING_CAN);
        }
    }

    public void handleTiredCatClick() {
        if (actionState == CatActionState.TIRED) {
            closeSpeechBubble();
            tryGoToSleep();
        }
    }
    
    private void closeSpeechBubble() {
        if (isShowingSpeechBubble() && currentBubbleType != null) {
            if (currentBubbleType == SpeechBubbleType.ZZZ) {
                speechBubbleAnimation = Farm.resourceHandler.animationFactory.createSpeechBubbleZzzClosingAnimation();
            } else if (currentBubbleType == SpeechBubbleType.WATERING_CAN) {
                speechBubbleAnimation = Farm.resourceHandler.animationFactory.createSpeechBubbleWaterCanClosingAnimation();
            }
            bubbleState = SpeechBubbleState.CLOSING;
        }
    }
    
    private void hideSpeechBubble() {
        bubbleState = SpeechBubbleState.HIDDEN;
        speechBubbleAnimation = null;
        currentBubbleType = null;
    }
    
    public boolean isShowingSpeechBubble() {
        return bubbleState != SpeechBubbleState.HIDDEN;
    }
    
    public void renderSpeechBubble(Graphics2D graphics2D) {
        if (speechBubbleAnimation != null) {
            int bubbleX = (position.x - 24) * Farm.scale + Farm.camera.position.x;
            int bubbleY = (position.y - catHeight - 20) * Farm.scale + Farm.camera.position.y;
            int bubbleSize = 48 * Farm.scale;
            
            graphics2D.drawImage(speechBubbleAnimation.getCurrentFrameImage(),
                    bubbleX, bubbleY, bubbleSize, bubbleSize, null);
        }
    }
    


    @Override
    public void render(Graphics2D graphics2D) {
        // water pouring overlay, rendered under the cat and nudged in front
        if (visible && wateringWaterAnimation != null
                && farmCatState == FarmCatState.WATERING && (isAtWateringPosition || isAtWaterTray)) {
            int frontOffset = 8;
            int offsetX = 0;
            int offsetY = 0;
            switch (farmCatFacing) {
                case DOWN -> offsetY = frontOffset;
                case UP -> offsetY = -frontOffset;
                case LEFT -> offsetX = -frontOffset;
                case RIGHT -> offsetX = frontOffset;
            }
            int dx = (position.x - catWidth / 2 + offsetX) * Farm.scale + Farm.camera.position.x;
            int dy = (position.y - catHeight / 2 + offsetY) * Farm.scale + Farm.camera.position.y;
            int dw = catWidth * Farm.scale;
            int dh = catHeight * Farm.scale;
            var frame = wateringWaterAnimation.getCurrentFrameImage();
            if (frame != null) {
                if (farmCatFacing == FarmCatFacing.LEFT) {
                    graphics2D.drawImage(frame, dx + dw, dy, dx, dy + dh,
                            0, 0, frame.getWidth(), frame.getHeight(), null);
                } else {
                    graphics2D.drawImage(frame, dx, dy, dw, dh, null);
                }
            }
        }

        if (visible && currentImage != null) {
            graphics2D.drawImage(currentImage,
                    (position.x - catWidth / 2) * Farm.scale + Farm.camera.position.x,
                    (position.y - catHeight / 2) * Farm.scale + Farm.camera.position.y,
                    catWidth * Farm.scale,
                    catHeight * Farm.scale,
                    null);
        }
    }
    
    public int getEnergy() {
        return energy;
    }
    
    public void setEnergy(int energy) {
        this.energy = Math.max(0, Math.min(100, energy));
    }
    
    public void addEnergy(int amount) {
        setEnergy(energy + amount);
    }
    
    private void restoreEnergyWhileSleeping() {
        sleepEnergyTimer++;
        
        // restore energy every second (60 frames at 60 FPS)
        if (sleepEnergyTimer >= 60) {
            sleepEnergyTimer = 0;
            
            int energyRestoration = switch (farmingLevel) {
                case LVL0 -> 1;
                case LVL1 -> 2;
                case LVL2 -> 3;
                case LVL3 -> 4;
                case LVL_STAR -> 5;
            };
            
            if (energy < 100) {
                addEnergy(energyRestoration);
                
                if (Farm.menuPanel != null) {
                    Farm.menuPanel.refreshResourcesDisplay();
                }
            }
        }
    }
    
    public boolean consumeEnergyForTilling() {
        int energyCost = getEnergyCostForAction();
        
        if (energy < energyCost) {
            return false;
        }
        
        setEnergy(energy - energyCost);
        return true;
    }
    
    private int getEnergyCostForAction() {
        switch (farmingLevel) {
            case LVL0 -> { return 10; }
            case LVL1 -> { return 7; }
            case LVL2 -> { return 5; }
            case LVL3 -> { return 3; }
            case LVL_STAR -> { return 1; }
            default -> { return 10; }
        }
    }

    public boolean hasEnoughEnergyForAction() {
        return energy >= getEnergyCostForAction();
    }

    private int getWaterCostForAction() {
        switch (farmingLevel) {
            case LVL0 -> { return 10; }
            case LVL1 -> { return 7; }
            case LVL2 -> { return 5; }
            case LVL3 -> { return 3; }
            case LVL_STAR -> { return 1; }
            default -> { return 10; }
        }
    }

    public boolean hasEnoughWaterForAction() {
        return wateringCan >= getWaterCostForAction();
    }

    public boolean consumeWaterForWatering() {
        int waterCost = getWaterCostForAction();

        if (wateringCan < waterCost) {
            return false;
        }

        setWateringCan(wateringCan - waterCost);
        return true;
    }

    // filling a water tray costs 3x a single crop watering
    private int getWaterCostForTrayFill() {
        return 3 * getWaterCostForAction();
    }

    public boolean hasEnoughWaterForTrayFill() {
        return wateringCan >= getWaterCostForTrayFill();
    }

    public boolean consumeWaterForTrayFill() {
        int waterCost = getWaterCostForTrayFill();

        if (wateringCan < waterCost) {
            return false;
        }

        setWateringCan(wateringCan - waterCost);
        return true;
    }

    public int getWateringCan() {
        return wateringCan;
    }
    
    public void setWateringCan(int wateringCan) {
        this.wateringCan = Math.max(0, Math.min(100, wateringCan));
    }
    
    public void addWateringCan(int amount) {
        setWateringCan(wateringCan + amount);
    }
    
    public void consumeWateringCan(int amount) {
        setWateringCan(wateringCan - amount);
    }
    
    public FarmingLevel getFarmingLevel() {
        return farmingLevel;
    }
    
    public void setFarmingLevel(FarmingLevel farmingLevel) {
        this.farmingLevel = farmingLevel;
    }
    
    public boolean canLevelUp() {
        return farmingLevel != FarmingLevel.LVL_STAR;
    }
    
    public void levelUp() {
        if (canLevelUp()) {
            int requiredExp = getRequiredExperience();
            int excessExperience = experience - requiredExp;
            
            switch (farmingLevel) {
                case LVL0 -> farmingLevel = FarmingLevel.LVL1;
                case LVL1 -> farmingLevel = FarmingLevel.LVL2;
                case LVL2 -> farmingLevel = FarmingLevel.LVL3;
                case LVL3 -> farmingLevel = FarmingLevel.LVL_STAR;
                case LVL_STAR -> { /* Already at max level */ }
            }
            
            experience = Math.max(0, excessExperience);
        }
    }
    
    // experience methods
    public int getExperience() {
        return experience;
    }
    
    public void setExperience(int experience) {
        this.experience = Math.max(0, experience);
    }
    
    public void addExperience(int amount) {
        setExperience(experience + amount);
    }
    
    public int getRequiredExperience() {
        switch (farmingLevel) {
            case LVL0: return 10;
            case LVL1: return 25;
            case LVL2: return 50;
            case LVL3: return 100;
            case LVL_STAR: return 0; // max level
            default: return 10;
        }
    }
    
    public boolean hasEnoughExperienceToLevelUp() {
        return farmingLevel != FarmingLevel.LVL_STAR && experience >= getRequiredExperience();
    }
    
    public String getFarmingLevelString() {
        switch (farmingLevel) {
            case LVL1 -> { return "Level 1"; }
            case LVL2 -> { return "Level 2"; }
            case LVL3 -> { return "Level 3"; }
            case LVL_STAR -> { return "Level ★"; }
            default -> { return "Level 0"; }
        }
    }
    
    public FarmCatColor getColor() {
        return color;
    }
    
    public String getColorString() {
        switch (color) {
            case WHITE -> { return "White"; }
            case GREY -> { return "Grey"; }
            case GINGER -> { return "Ginger"; }
            case TRICOLOR -> { return "Tricolor"; }
            default -> { return "Unknown"; }
        }
    }
    
    public boolean isVisible() {
        return visible;
    }
    
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
