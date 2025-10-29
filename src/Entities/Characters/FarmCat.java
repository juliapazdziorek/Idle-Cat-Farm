package Entities.Characters;

import Entities.Entity;
import Entities.Objects.Bed;
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
    public enum SpeechBubbleType { ZZZ }
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
    public enum CatActionState { IDLE, PLANTING, GOING_TO_SLEEP, SLEEPING, TIRED }
    private CatActionState actionState;
    private List<Point> plantingPositions;
    private int currentPlantingIndex;
    private ResourceType currentCropType;
    private Field.FieldType currentFieldType;
    private boolean isAtTillingPosition;
    private int tillingAnimationCounter;
    private static final int tillingAnimationDuration = 60;
    
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
        previousActionState = actionState;

        if (actionState == CatActionState.PLANTING) {
            updatePlantingAction();
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
        
        if (isIdle() && !hasEnoughEnergyForAction() && !isShowingSpeechBubble()) {
            showSpeechBubble(SpeechBubbleType.ZZZ);
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
                if (Objects.requireNonNull(bubbleType) == SpeechBubbleType.ZZZ) {
                    speechBubbleAnimation = Farm.resourceHandler.animationFactory.createSpeechBubbleZzzOpeningAnimation();
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
            }
            bubbleState = SpeechBubbleState.LOOPING;
        }
    }
    
    public void handleTiredCatClick() {
        if (actionState == CatActionState.TIRED) {
            // close the speech bubble with animation
            closeSpeechBubble();
            // attempt to go to sleep
            tryGoToSleep();
        }
    }
    
    private void closeSpeechBubble() {
        if (isShowingSpeechBubble() && currentBubbleType != null) {
            if (currentBubbleType == SpeechBubbleType.ZZZ) {
                speechBubbleAnimation = Farm.resourceHandler.animationFactory.createSpeechBubbleZzzClosingAnimation();
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
        if (visible && currentImage != null) {
            graphics2D.drawImage(currentImage,
                    (position.x - catWidth / 2) * Farm.scale + Farm.camera.position.x,
                    (position.y - catHeight / 2) * Farm.scale + Farm.camera.position.y,
                    catWidth * Farm.scale,
                    catHeight * Farm.scale,
                    null);
        }
    }
    
    // stat management methods
    public int getEnergy() {
        return energy;
    }
    
    public void setEnergy(int energy) {
        this.energy = Math.max(0, Math.min(100, energy));
    }
    
    public void addEnergy(int amount) {
        setEnergy(energy + amount);
    }
    
    // restore energy while sleeping based on farming level
    private void restoreEnergyWhileSleeping() {
        sleepEnergyTimer++;
        
        // restore energy every second (60 frames at 60 FPS)
        if (sleepEnergyTimer >= 60) {
            sleepEnergyTimer = 0;
            
            // energy restoration based on farming level
            int energyRestoration = switch (farmingLevel) {
                case LVL0 -> 1;
                case LVL1 -> 2;
                case LVL2 -> 3;
                case LVL3 -> 4;
                case LVL_STAR -> 5;
            };
            
            // only restore if not at full energy
            if (energy < 100) {
                addEnergy(energyRestoration);
                
                // refresh UI to update the energy bar
                if (Farm.menuPanel != null) {
                    Farm.menuPanel.refreshResourcesDisplay();
                }
            }
        }
    }
    
    // consume energy for tilling based on cat level, returns true if successful
    public boolean consumeEnergyForTilling() {
        int energyCost = getEnergyCostForTilling();
        
        if (energy < energyCost) {
            return false;
        }
        
        setEnergy(energy - energyCost);
        return true;
    }
    
    private int getEnergyCostForTilling() {
        switch (farmingLevel) {
            case LVL0 -> { return 10; }
            case LVL1 -> { return 7; }
            case LVL2 -> { return 5; }
            case LVL3 -> { return 3; }
            case LVL_STAR -> { return 1; }
            default -> { return 10; }
        }
    }
    
    public boolean hasEnoughEnergyForTilling() {
        return energy >= getEnergyCostForTilling();
    }

    public boolean hasEnoughEnergyForAction() {
        return energy >= getEnergyCostForTilling();
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
