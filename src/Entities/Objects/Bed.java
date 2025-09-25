package Entities.Objects;

import Entities.Characters.FarmCat;
import Entities.Entity;
import Game.Farm;
import java.awt.Point;

public class Bed extends Entity {

    public enum BedState { EMPTY, RESERVED, OCCUPIED }
    
    private BedState bedState;
    private FarmCat occupyingCat;
    private final int topTileId;
    private Point toBedPosition;


    public Bed(int topTileId) {
        super();
        
        // set flags
        isParent = true;
        clickable = false;
        
        this.topTileId = topTileId;
        this.bedState = BedState.EMPTY;
        this.occupyingCat = null;
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
        
        // calculate accessible positions based on bed orientation
        if (topTileId == 313) {
            // Bed 313: priority order - bottom, right, left
            Point[] priorityPositions = {
                new Point(bottomPartTilePos.x, bottomPartTilePos.y + 1),
                new Point(bottomPartTilePos.x + 1, bottomPartTilePos.y),
                new Point(bottomPartTilePos.x - 1, bottomPartTilePos.y)
            };

            for (Point pos : priorityPositions) {
                if (pos.x >= 0 && pos.x < Farm.mapWidthTiles && 
                    pos.y >= 0 && pos.y < Farm.mapHeightTiles &&
                    !Farm.entitiesHandler.map.hasObstacleAt(pos.y, pos.x)) {
                    toBedPosition = pos;
                    break;
                }
            }
        } else {
            // Bed 317: priority order - top, top-right, top-left (only choose the first available)
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
}