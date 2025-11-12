package Map;

import Entities.FarmResources.Crop;
import Entities.Objects.Sign;
import Game.Farm;
import Game.FarmResourcesHandler.ResourceType;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Field {

    public enum FieldType { EAST, WEST }

    public enum FieldState { 
        EMPTY,
        PLANTING,
        GROWING,
        READY_TO_HARVEST
    }

    private final ArrayList<Point> cropPositions;
    private final FieldType fieldType;
    public ResourceType cropType;

    private final Map<Point, Crop> crops;

    private FieldState currentState;

    private boolean catWorkingOnField;

    public Field(FieldType fieldType) {
        this.fieldType = fieldType;
        cropPositions = new ArrayList<>();
        crops = new HashMap<>();
        this.currentState = FieldState.EMPTY;
        this.catWorkingOnField = false;
    }

    public void addCropPositions(ArrayList<Point> positions) {
        cropPositions.addAll(positions);
    }

    public boolean isAlreadyPlanted() {
        return !crops.isEmpty();
    }
    
    public ResourceType getCurrentCropType() {
        return cropType;
    }
    
    public FieldState getCurrentState() {
        return currentState;
    }
    
    public void setCatWorkingOnField(boolean working) {
        this.catWorkingOnField = working;
        updateFieldState();
    }
    
    public boolean isCatWorkingOnField() {
        return catWorkingOnField;
    }

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
    
    public void setCropType(ResourceType cropType) {
        this.cropType = cropType;
        Sign.updateAllSigns();
        updateFieldState();
    }

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

    public Map<Point, Crop> getCrops() {
        return crops;
    }

    public ArrayList<Point> getCropPositions() {
        return new ArrayList<>(cropPositions);
    }
    
    public FieldType getFieldType() {
        return fieldType;
    }
}
