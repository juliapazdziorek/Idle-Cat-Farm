package Entities.Characters;

import Entities.AnimatedEntity;
import Game.FocusFarm;
import Resources.Animation;
import Pathfinding.AStar;
import Pathfinding.Node;

import java.awt.*;
import java.util.HashMap;
import java.util.List;


public class FarmCat extends AnimatedEntity {

    // properties
    public final int catWidth = 48;
    public final int catHeight = 48;

    // enums
    private enum FarmCatState {STANDING, WALKING, RUNNING, TILLING, CHOPPING, WATERING}
    private enum FarmCatFacing {DOWN, UP, RIGHT, LEFT}

    // cat state
    private FarmCatState farmCatState;
    private FarmCatFacing farmCatFacing;
    private Animation currentAnimation;

    // animations
    HashMap<String, Animation> animations;

    // pathfinding
    private AStar pathfinder;
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
    
    public FarmCat(int positionX, int positionY) {
        super(positionX, positionY);

        // get animations
        animations = FocusFarm.resourceHandler.createCatAnimationMap();

        // initialize cat state variables
        farmCatFacing = FarmCatFacing.DOWN;
        farmCatState = FarmCatState.STANDING;
        currentAnimation = animations.get("farmCatStandingDown");

        // initialize pathfinding and moving variables
        lastDirection = FarmCatFacing.DOWN;
    }


    // pathfinding
    private void initializePathFinder() {
        if (pathfinder == null) {
            pathfinder = new AStar();
        }
    }

    public void moveToPosition(int worldX, int worldY) {
        initializePathFinder();

        int startTileX = positionX / FocusFarm.tileSize;
        int startTileY = positionY / FocusFarm.tileSize;
        int endTileX = worldX / FocusFarm.tileSize;
        int endTileY = worldY / FocusFarm.tileSize;

        currentPath = pathfinder.findPath(startTileX, startTileY, endTileX, endTileY);

        if (currentPath != null && currentPath.size() > 1) {
            currentPathIndex = 1;
            isFollowingPath = true;
            
            isRunning = currentPath.size() > 7;
            moveCounter = 0;
            
            Node nextNode = currentPath.get(currentPathIndex);
            targetX = nextNode.x * FocusFarm.tileSize + FocusFarm.tileSize / 2;
            targetY = nextNode.y * FocusFarm.tileSize + FocusFarm.tileSize / 2;
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

        int deltaX = targetX - positionX;
        int deltaY = targetY - positionY;
        int moveSpeed = 1;

        // waypoint reached
        if (Math.abs(deltaX) < moveSpeed && Math.abs(deltaY) < moveSpeed) {
            positionX = targetX;
            positionY = targetY;
            currentPathIndex++;

            // end path following
            if (currentPathIndex >= currentPath.size()) {
                isFollowingPath = false;
                currentPath = null;
                isActuallyMoving = false;
                return;
            }

            Node nextNode = currentPath.get(currentPathIndex);
            targetX = nextNode.x * FocusFarm.tileSize + FocusFarm.tileSize / 2;
            targetY = nextNode.y * FocusFarm.tileSize + FocusFarm.tileSize / 2;
            deltaX = targetX - positionX;
            deltaY = targetY - positionY;
        }

        // move
        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            if (deltaX > 0) {
                positionX += moveSpeed;
                if (isRunning) {
                    runWithDirection(FarmCatFacing.RIGHT);
                } else {
                    walkWithDirection(FarmCatFacing.RIGHT);
                }
            } else {
                positionX -= moveSpeed;
                if (isRunning) {
                    runWithDirection(FarmCatFacing.LEFT);
                } else {
                    walkWithDirection(FarmCatFacing.LEFT);
                }
            }
        } else {
            if (deltaY > 0) {
                positionY += moveSpeed;
                if (isRunning) {
                    runWithDirection(FarmCatFacing.DOWN);
                } else {
                    walkWithDirection(FarmCatFacing.DOWN);
                }
            } else {
                positionY -= moveSpeed;
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
    }

    public void resetAnimations() {
        animations.values().forEach(Animation::resetFrames);
    }

    // getters
    public int getCatPositionX() {
        return positionX;
    }

    public int getCatPositionY() {
        return positionY;
    }

    public int getCurrentTileX() {
        return positionX / FocusFarm.tileSize;
    }

    public int getCurrentTileY() {
        return positionY / FocusFarm.tileSize;
    }

    public int getCenterScreenCatPositionX() {
        return (FocusFarm.frame.getWidth() - catWidth * FocusFarm.scale) / 2;
    }

    public int getCenterScreenCatPositionY() {
        return (FocusFarm.frame.getHeight() - catHeight * FocusFarm.scale ) / 2;
    }


    // updating & rendering
    @Override
    public void update() {
        followPath();
        setAnimation();
        isMoving = isActuallyMoving;
        currentAnimation.update();
    }

    @Override
    public void render(Graphics2D graphics2D) {
        graphics2D.drawImage(currentAnimation.getCurrentFrame(),
                (positionX - catWidth / 2) * FocusFarm.scale + FocusFarm.camera.cameraX,
                (positionY - catHeight / 2) * FocusFarm.scale + FocusFarm.camera.cameraY,
                catWidth * FocusFarm.scale,
                catHeight * FocusFarm.scale,
                null);
    }
}
