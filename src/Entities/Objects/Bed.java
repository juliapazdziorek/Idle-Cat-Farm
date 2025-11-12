package Entities.Objects;

import Entities.Characters.FarmCat;
import Entities.Entity;
import Game.Farm;
import Resources.Animation;
import java.awt.Graphics2D;
import java.awt.Point;

public class Bed extends Entity {

    public enum BedState { EMPTY, RESERVED, OCCUPIED }
    public enum SunBubbleState { HIDDEN, OPENING, LOOPING, CLOSING }

    private BedState bedState;
    private FarmCat occupyingCat;
    private final int topTileId;
    private Point toBedPosition;
    private Animation sunSpeechBubbleAnimation;
    private SunBubbleState sunBubbleState = SunBubbleState.HIDDEN;

    public Bed(int topTileId) {
        super();

        isParent = true;
        clickable = false;
        
        this.topTileId = topTileId;
        this.bedState = BedState.EMPTY;
        this.occupyingCat = null;
    }

    @Override
    public void update() {
        super.update();

        updateSunSpeechBubble();

        if (isShowingSunBubble() && sunSpeechBubbleAnimation != null) {
            sunSpeechBubbleAnimation.update();

            if (sunBubbleState == SunBubbleState.OPENING && sunSpeechBubbleAnimation.getCurrentFrame() == sunSpeechBubbleAnimation.getNumberOfFrames() - 1) {
                startSunBubbleLooping();

            } else if (sunBubbleState == SunBubbleState.CLOSING && sunSpeechBubbleAnimation.getCurrentFrame() == sunSpeechBubbleAnimation.getNumberOfFrames() - 1) {
                hideSunSpeechBubble();
            }
        }
    }


    public BedState getBedState() {
        return bedState;
    }
    
    public boolean isAvailable() {
        return bedState == BedState.EMPTY;
    }
    
    public FarmCat getOccupyingCat() {
        return occupyingCat;
    }
    
    public boolean reserveBed(FarmCat cat) {
        if (bedState == BedState.EMPTY && cat != null) {
            this.occupyingCat = cat;
            this.bedState = BedState.RESERVED;
            return true;
        }
        return false;
    }
    
    public void setCatSleeping(FarmCat cat) {
        if (bedState == BedState.RESERVED && cat != null && occupyingCat == cat) {
            this.bedState = BedState.OCCUPIED;
            
            if (parts != null) {
                for (Entity part : parts) {
                    if (part instanceof BedPart bedPart) {
                        bedPart.startSleepingAnimation(cat.getColor());
                    }
                }
            }
        }
    }
    
    public void setCatAwake() {
        if ((bedState == BedState.OCCUPIED || bedState == BedState.RESERVED) && occupyingCat != null) {
            if (bedState == BedState.OCCUPIED) {
                occupyingCat.setVisible(true);
            }
            
            this.occupyingCat = null;
            this.bedState = BedState.EMPTY;
            
            if (parts != null) {
                for (Entity part : parts) {
                    if (part instanceof BedPart bedPart) {
                        bedPart.stopSleepingAnimation();
                    }
                }
            }
        }
    }

    public void calculateToBedPosition() {
        toBedPosition = null;
        
        if (parts == null || parts.isEmpty()) {
            return;
        }
        
        Point topPartTilePos = null;
        Point bottomPartTilePos = null;
        
        for (Entity part : parts) {
            if (part instanceof BedPart bedPart) {
                Point partTilePos = part.getTilePosition();
                if (bedPart.isTopPart()) {
                    topPartTilePos = partTilePos;
                } else {
                    bottomPartTilePos = partTilePos;
                }
            }
        }

        if (topTileId == 313) {
            // Bed 313: priority order - bottom, right, left
            assert bottomPartTilePos != null;
            Point[] priorityPositions = {
                new Point(bottomPartTilePos.x, bottomPartTilePos.y + 1),
                new Point(bottomPartTilePos.x + 1, bottomPartTilePos.y + 1),
                new Point(bottomPartTilePos.x - 1, bottomPartTilePos.y + 1)
            };

            for (Point position : priorityPositions) {
                if (position.x >= 0 && position.x < Farm.mapWidthTiles &&
                    position.y >= 0 && position.y < Farm.mapHeightTiles &&
                    !Farm.entitiesHandler.map.hasObstacleAt(position.y, position.x)) {
                    System.out.println("Found valid toBedPosition at: " + position);
                    toBedPosition = position;
                    break;
                }
            }
        } else {
            // Bed 317: priority order - top, top-right, top-left (only choose the first available)
            assert topPartTilePos != null;
            Point[] priorityPositions = {
                new Point(topPartTilePos.x, topPartTilePos.y - 1),
                new Point(topPartTilePos.x + 1, topPartTilePos.y - 1),
                new Point(topPartTilePos.x - 1, topPartTilePos.y - 1)
            };
            
            for (Point pos : priorityPositions) {
                if (pos.x >= 0 && pos.x < Farm.mapWidthTiles && 
                    pos.y >= 0 && pos.y < Farm.mapHeightTiles &&
                    !Farm.entitiesHandler.map.hasObstacleAt(pos.y, pos.x)) {
                    toBedPosition = pos;
                    break;
                }
            }
        }
    }
    
    public Point getToBedPosition() {
        return toBedPosition;
    }
    
    public boolean hasValidPosition() {
        return toBedPosition != null;
    }

    private void updateSunSpeechBubble() {
        boolean shouldShowSunBubble = bedState == BedState.OCCUPIED && 
                                     occupyingCat != null && 
                                     occupyingCat.getEnergy() == 100;

        if (shouldShowSunBubble && sunBubbleState == SunBubbleState.HIDDEN) {
            showSunSpeechBubble();
        }

        else if (!shouldShowSunBubble && isShowingSunBubble() && sunBubbleState != SunBubbleState.CLOSING) {
            closeSunBubble();
        }
    }

    private void showSunSpeechBubble() {
        try {
            sunSpeechBubbleAnimation = Farm.resourceHandler.animationFactory.createSpeechBubbleSunOpeningAnimation();
            sunBubbleState = SunBubbleState.OPENING;
        } catch (Exception exception) {
            hideSunSpeechBubble();
        }
    }
    
    private void startSunBubbleLooping() {
        sunSpeechBubbleAnimation = Farm.resourceHandler.animationFactory.createSpeechBubbleSunAnimation();
        sunBubbleState = SunBubbleState.LOOPING;
    }
    
    private void closeSunBubble() {
        sunSpeechBubbleAnimation = Farm.resourceHandler.animationFactory.createSpeechBubbleSunClosingAnimation();
        sunBubbleState = SunBubbleState.CLOSING;
    }
    
    private void hideSunSpeechBubble() {
        sunBubbleState = SunBubbleState.HIDDEN;
        sunSpeechBubbleAnimation = null;
    }
    
    public boolean isShowingSunBubble() {
        return sunBubbleState != SunBubbleState.HIDDEN;
    }

    public void renderSunSpeechBubble(Graphics2D graphics2D) {
        if (isShowingSunBubble() && sunSpeechBubbleAnimation != null) {
            Point topPartPosition = null;
            if (parts != null) {
                for (Entity part : parts) {
                    if (part instanceof BedPart bedPart && bedPart.isTopPart()) {
                        topPartPosition = part.getPosition();
                        break;
                    }
                }
            }
            
            if (topPartPosition != null) {
                int bubbleX = (topPartPosition.x - 16) * Farm.scale + Farm.camera.position.x;
                int bubbleY = (topPartPosition.y - 40) * Farm.scale + Farm.camera.position.y;
                int bubbleSize = 48 * Farm.scale;
                
                java.awt.image.BufferedImage bubbleImage = sunSpeechBubbleAnimation.getCurrentFrameImage();
                graphics2D.drawImage(bubbleImage, bubbleX, bubbleY, bubbleSize, bubbleSize, null);
            }
        }
    }
}