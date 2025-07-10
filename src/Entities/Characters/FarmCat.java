//package Entities.Characters;
//
//import Entities.AnimatedEntity;
//import Resources.Animation;
//
//import java.awt.*;
//import java.util.HashMap;
//
//public class FarmCat extends AnimatedEntity {
//
//    // properties
//    public final int catWidth = 48;
//    public final int catHeight = 48;
//
//    // enums
//    private enum FarmCatState {STANDING, WALKING, FARMING, WATERING}
//    private enum FarmCatFacing {DOWN, UP, RIGHT, LEFT}
//
//    // cat state
//    private FarmCatState farmCatState;
//    private FarmCatFacing farmCatFacing;
//    private boolean isMoving;
//
//    // animations
//    HashMap<String, Animation> animations;
//
//    // constructor
//    public FarmCat() {
//        super(100, 100);
//
//        // getting animations
//        animations = Farm.resourceHandler.getCatAnimationMap();
//
//        // setting cat
//        currentAnimation = animations.get("farmCatStandingDown");
//        farmCatFacing = FarmCatFacing.DOWN;
//        farmCatState = FarmCatState.STANDING;
//
//    }
//
//
//    // moving
//    public void moveUp() {
//        farmCatState = FarmCatState.WALKING;
//        farmCatFacing = FarmCatFacing.UP;
//        isMoving = true;
//
//    }
//
//    public void moveDown() {
//        farmCatState = FarmCatState.WALKING;
//        farmCatFacing = FarmCatFacing.DOWN;
//        isMoving = true;
//
//    }
//
//    public void moveLeft() {
//        farmCatState = FarmCatState.WALKING;
//        farmCatFacing = FarmCatFacing.LEFT;
//        isMoving = true;
//
//    }
//
//    public void moveRight() {
//        farmCatState = FarmCatState.WALKING;
//        farmCatFacing = FarmCatFacing.RIGHT;
//        isMoving = true;
//
//    }
//
//
//    // animation handling
//    private void setAnimation() {
//
//        if (!isMoving) {
//            if (farmCatState != FarmCatState.FARMING && farmCatState != FarmCatState.WATERING) {
//                farmCatState = FarmCatState.STANDING;
//                switch (farmCatFacing) {
//                    case UP: {
//                        currentAnimation = animations.get("farmCatStandingUp");
//                        break;
//                    }
//                    case DOWN: {
//                        currentAnimation = animations.get("farmCatStandingDown");
//                        break;
//                    }
//                    case LEFT: {
//                        currentAnimation = animations.get("farmCatStandingLeft");
//                        break;
//                    }
//                    case RIGHT: {
//                        currentAnimation = animations.get("farmCatStandingRight");
//                        break;
//                    }
//                }
//            }
//
//            /*if (farmCatState == FarmCatState.FARMING) {
//                switch (farmCatFacing) {
//                    case UP: {
//                        currentAnimation = animations.get("farmCatFarmingUp");
//                        break;
//                    }
//                    case DOWN: {
//                        currentAnimation = animations.get("farmCatFarmingDown");
//                        break;
//                    }
//                    case LEFT: {
//                        currentAnimation = animations.get("farmCatFarmingLeft");
//                        break;
//                    }
//                    case RIGHT: {
//                        currentAnimation = animations.get("farmCatFarmingRight");
//                        break;
//                    }
//                }
//            }
//
//            if (farmCatState == FarmCatState.WATERING) {
//                switch (farmCatFacing) {
//                    case UP: {
//                        currentAnimation = animations.get("farmCatWateringUp");
//                        break;
//                    }
//                    case DOWN: {
//                        currentAnimation = animations.get("farmCatWateringDown");
//                        break;
//                    }
//                    case LEFT: {
//                        currentAnimation = animations.get("farmCatWateringLeft");
//                        break;
//                    }
//                    case RIGHT: {
//                        currentAnimation = animations.get("farmCatWateringRight");
//                        break;
//                    }
//                }
//            }*/
//
//        } else {
//            switch (farmCatFacing) {
//                case UP: {
//                    currentAnimation = animations.get("farmCatWalkingUp");
//                    break;
//                }
//                case DOWN: {
//                    currentAnimation = animations.get("farmCatWalkingDown");
//                    break;
//                }
//                case LEFT: {
//                    currentAnimation = animations.get("farmCatWalkingLeft");
//                    break;
//                }
//                case RIGHT: {
//                    currentAnimation = animations.get("farmCatWalkingRight");
//                    break;
//                }
//            }
//
//        }
//
//
//    }
//
//    public void resetAnimations() {
//        animations.forEach((key, value) -> value.resetFrames());
//    }
//
//
//    // updating
//    @Override
//    public void update() {
//
//        // moving
//        if (actionHandler.upPressed) {
//            moveUp();
//        }
//        if (actionHandler.downPressed) {
//            moveDown();
//        }
//        if (actionHandler.leftPressed) {
//            moveLeft();
//        }
//        if (actionHandler.rightPressed) {
//            moveRight();
//        }
//
//        // set properties
//        setAnimation();
//        isMoving = false;
//        currentAnimation.update();
//
//    }
//
//    // rendering
//    @Override
//    public void render(Graphics2D graphics2D) {
//        graphics2D.drawImage(currentAnimation.getCurrentFrame(), positionX * scale, positionY * scale, catWidth * scale, catHeight * scale, null);
//    }
//
//    public int getCatPositionX() {
//        return positionX;
//    }
//
//    public int getCatPositionY() {
//        return positionY;
//    }
//
//    public int getCenterScreenCatPositionX() {
//        return (frame.getWidth() - catWidth * scale) / 2;
//    }
//
//    public int getCenterScreenCatPositionY() {
//        return (frame.getHeight() - catHeight * scale ) / 2;
//    }
//
//
//}
