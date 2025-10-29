package Map;

import Entities.FarmResources.Crop;
import Entities.Objects.Sign;
import Game.Farm;
import Game.FarmResourcesHandler.ResourceType;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Field management system for crop cultivation and harvest operations.
 * Handles field states, crop positioning, planting coordination, and harvest readiness tracking.
 */
public class Field {

    /** Defines the geographical orientation of field areas within the farm layout. */
    public enum FieldType { EAST, WEST }
    
    /** Represents the current operational state of agricultural field management. */
    public enum FieldState { 
        EMPTY, // No crops planted
        PLANTING, // Cat is actively planting
        GROWING, // Crops are growing, no cat working
        READY_TO_HARVEST // Crops are ready to be harvested
    }

    private final ArrayList<Point> cropPositions;
    private final FieldType fieldType;
    public ResourceType cropType;

    private final Map<Point, Crop> crops;

    private FieldState currentState;

    private boolean catWorkingOnField;

    /**
     * Initializes a new agricultural field with specified orientation and empty state.
     * Prepares field for crop position registration and planting operations.
     */
    public Field(FieldType fieldType) {
        this.fieldType = fieldType;
        cropPositions = new ArrayList<>();
        crops = new HashMap<>();
        this.currentState = FieldState.EMPTY;
        this.catWorkingOnField = false;
    }

    /** Registers additional planting positions for crop cultivation within this field. */
    public void addCropPositions(ArrayList<Point> positions) {
        cropPositions.addAll(positions);
    }

    /** Returns whether this field currently contains any planted crops. */
    public boolean isAlreadyPlanted() {
        return !crops.isEmpty();
    }
    
    /** Gets the crop variety currently being cultivated in this field. */
    public ResourceType getCurrentCropType() {
        return cropType;
    }
    
    /** Gets the current operational state of this agricultural field. */
    public FieldState getCurrentState() {
        return currentState;
    }
    
    /** Updates cat work assignment status and recalculates field operational state. */
    public void setCatWorkingOnField(boolean working) {
        this.catWorkingOnField = working;
        updateFieldState();
    }
    
    /** Returns whether a farm cat is currently assigned to work on this field. */
    public boolean isCatWorkingOnField() {
        return catWorkingOnField;
    }
    
    /**
     * Recalculates field operational state based on cat activity and crop development.
     * Automatically updates UI displays when state transitions occur for real-time feedback.
     */
    private void updateFieldState() {
        if (catWorkingOnField) {
            currentState = FieldState.PLANTING;

        } else if (crops.isEmpty()) {
            currentState = FieldState.EMPTY;

        } else {

            boolean allCropsReady = crops.values().stream()
                    .allMatch(Crop::isFullyGrown);
            
            if (allCropsReady) {
                currentState = FieldState.READY_TO_HARVEST;
            } else {
                currentState = FieldState.GROWING;
            }
        }
        
        if (Farm.menuPanel != null) {
            Farm.menuPanel.refreshResourcesDisplay();
        }
    }
    
    /** Updates the crop variety for this field and refreshes all related game displays. */
    public void setCropType(ResourceType cropType) {
        this.cropType = cropType;
        Sign.updateAllSigns();
        updateFieldState();
    }

    /**
     * Plants a new crop at the specified position and integrates it into the game world.
     * Handles entity registration, obstacle placement, and field type assignment automatically.
     */
    public void createCropAtPosition(Point position, ResourceType cropType) {
        Crop newCrop = new Crop(position.x, position.y, cropType);
        crops.put(position, newCrop);
        
        if (this.cropType == null) {
            this.cropType = cropType;
            Sign.updateAllSigns();
        }
        
        if (Farm.entitiesHandler != null) {
            Farm.entitiesHandler.clickableMapEntities.add(newCrop);
            Farm.entitiesHandler.topRenderableEntities.add(newCrop);
            Farm.entitiesHandler.updatableMapEntities.add(newCrop);
            Farm.entitiesHandler.map.addObstacleToTile(position.x, position.y);
        }
        
        updateFieldState();
    }

    /**
     * Removes a crop from the specified position and cleans up all associated game state.
     * Automatically resets field type and updates displays when field becomes empty.
     */
    public void removeCrop(Point position) {
        crops.remove(position);
        
        if (crops.isEmpty()) {
            cropType = null;
            Sign.updateAllSigns();
        }
        
        if (Farm.entitiesHandler != null) {
            Farm.entitiesHandler.map.removeObstacleFromTile(position.x, position.y);
        }
        
        updateFieldState();
    }

    /** Returns the complete collection of active crops mapped to their positions. */
    public Map<Point, Crop> getCrops() {
        return crops;
    }

    /** Gets a copy of all available planting positions within this field. */
    public ArrayList<Point> getCropPositions() {
        return new ArrayList<>(cropPositions);
    }
    
    /** Returns the geographical orientation classification of this field. */
    public FieldType getFieldType() {
        return fieldType;
    }
}
