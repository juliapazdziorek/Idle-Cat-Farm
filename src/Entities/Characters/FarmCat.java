package Entities.Characters;

import Entities.Entity;
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


public class FarmCat extends Entity {

    // properties
    public final int catWidth = 42;
    public final int catHeight = 42;

    // enums
    public enum FarmCatColor {WHITE, GREY, GINGER, TRICOLOR}
    private enum FarmCatState {STANDING, WALKING, RUNNING, TILLING, CHOPPING, WATERING}
    private enum FarmCatFacing {DOWN, UP, RIGHT, LEFT}

    // color
    private final FarmCatColor color;

    // cat state
    private FarmCatState farmCatState;
    private FarmCatFacing farmCatFacing;

    // animations
    HashMap<String, Animation> animations;

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
    public enum CatActionState { IDLE, PLANTING }
    private CatActionState actionState;
    private List<Point> plantingPositions;
    private int currentPlantingIndex;
    private ResourceType currentCropType;
    private Field.FieldType currentFieldType;
    private boolean isAtTillingPosition;
    private int tillingAnimationCounter;
    private static final int tillingAnimationDuration = 60;

    public FarmCat(int tileX, int tileY, FarmCatColor color) {
        super(new Point(tileX * Farm.tileSize + Farm.tileSize / 2, tileY * Farm.tileSize + Farm.tileSize / 2));
        this.color = color;

        // get animations
        animations = Farm.resourceHandler.animationFactory.createCatAnimationMap();

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
            Field field = FieldsHandler.getFieldByTypeFromMap(currentFieldType);
            if (field != null) {
                field.setCatWorkingOnField(false);
            }
            
            actionState = CatActionState.IDLE;
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

                // tilling animation completes, create the crop
                Point cropPosition = plantingPositions.get(currentPlantingIndex);
                
                // use field method to create crop at position
                Field field = FieldsHandler.getFieldByTypeFromMap(currentFieldType);
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
                moveToNextPlantingPosition();
            }
        }
    }


    // getters
    public List<Node> getCurrentPath() {
        return currentPath;
    }


    // updating & rendering
    @Override
    public void update() {

        if (actionState == CatActionState.PLANTING) {
            updatePlantingAction();
        }
        
        followPath();
        setAnimation();
        isMoving = isActuallyMoving;
        
        // Set the current image from the current animation
        if (currentAnimation != null) {
            currentImage = currentAnimation.getCurrentFrameImage();
            currentAnimation.update();
        }
    }

    @Override
    public void render(Graphics2D graphics2D) {
        if (currentImage != null) {
            graphics2D.drawImage(currentImage,
                    (position.x - catWidth / 2) * Farm.scale + Farm.camera.position.x,
                    (position.y - catHeight / 2) * Farm.scale + Farm.camera.position.y,
                    catWidth * Farm.scale,
                    catHeight * Farm.scale,
                    null);
        }
    }
}
