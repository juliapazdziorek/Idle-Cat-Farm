package Entities.Characters;

import Entities.AnimatedEntity;
import Game.FocusFarm;
import Resources.Animation;

import java.awt.*;
import java.util.HashMap;

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
    private boolean isMoving;

    // animations
    HashMap<String, Animation> animations;

    // constructor
    public FarmCat(int positionX, int positionY) {
        super(positionX, positionY);

        animations = FocusFarm.resourceHandler.createCatAnimationMap();

        // setting cat state
        currentAnimation = animations.get("farmCatStandingDown");
        farmCatFacing = FarmCatFacing.DOWN;
        farmCatState = FarmCatState.STANDING;
    }


    // moving
    public void walkUp() {
        farmCatState = FarmCatState.WALKING;
        farmCatFacing = FarmCatFacing.UP;
        isMoving = true;
    }

    public void walkDown() {
        farmCatState = FarmCatState.WALKING;
        farmCatFacing = FarmCatFacing.DOWN;
        isMoving = true;
    }

    public void walkLeft() {
        farmCatState = FarmCatState.WALKING;
        farmCatFacing = FarmCatFacing.LEFT;
        isMoving = true;
    }

    public void walkRight() {
        farmCatState = FarmCatState.WALKING;
        farmCatFacing = FarmCatFacing.RIGHT;
        isMoving = true;
    }

    public void runUp() {
        farmCatState = FarmCatState.RUNNING;
        farmCatFacing = FarmCatFacing.UP;
        isMoving = true;
    }

    public void runDown() {
        farmCatState = FarmCatState.RUNNING;
        farmCatFacing = FarmCatFacing.DOWN;
        isMoving = true;
    }

    public void runLeft() {
        farmCatState = FarmCatState.RUNNING;
        farmCatFacing = FarmCatFacing.LEFT;
        isMoving = true;
    }

    public void runRight() {
        farmCatState = FarmCatState.RUNNING;
        farmCatFacing = FarmCatFacing.RIGHT;
        isMoving = true;
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
        animations.forEach((key, value) -> value.resetFrames());
    }


    // getters
    public int getCatPositionX() {
        return positionX;
    }

    public int getCatPositionY() {
        return positionY;
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
        setAnimation();
        isMoving = false;
        currentAnimation.update();
    }

    @Override
    public void render(Graphics2D graphics2D) {
        graphics2D.drawImage(currentAnimation.getCurrentFrame(),
                positionX * FocusFarm.scale,
                positionY * FocusFarm.scale,
                catWidth * FocusFarm.scale,
                catHeight * FocusFarm.scale,
                null);
    }
}
