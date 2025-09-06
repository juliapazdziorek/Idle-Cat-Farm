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

    // field types
    public enum FieldType { EAST, WEST }

    // field structure
    private final ArrayList<Point> cropPositions;
    private final FieldType fieldType;
    public ResourceType cropType;
    
    // crop management
    private final Map<Point, Crop> crops;

    public Field(FieldType fieldType) {
        this.fieldType = fieldType;
        cropPositions = new ArrayList<>();
        crops = new HashMap<>();
    }

    // field setup methods
    public void addCropPositions(ArrayList<Point> positions) {
        cropPositions.addAll(positions);
    }

    // planting operations
    public boolean plantCrop(ResourceType cropType) {
        if (isAlreadyPlanted()) {
            return false;
        }
        
        this.cropType = cropType;
        
        // update signs immediately when crop type is set
        Sign.updateAllSigns();
        
        for (Point position : cropPositions) {
            Crop newCrop = new Crop(position.x, position.y, cropType);
            crops.put(position, newCrop);
            
            if (Farm.entitiesHandler != null) {
                Farm.entitiesHandler.clickableMapEntities.add(newCrop);
                Farm.entitiesHandler.topRenderableEntities.add(newCrop);
                Farm.entitiesHandler.updatableMapEntities.add(newCrop);
            }
        }
        
        return true;
    }
    
    // field status checks
    public boolean isAlreadyPlanted() {
        return !crops.isEmpty();
    }
    
    public ResourceType getCurrentCropType() {
        return cropType;
    }
    
    public void setCropType(ResourceType cropType) {
        this.cropType = cropType;
        Sign.updateAllSigns();
    }
    
    public void createCropAtPosition(Point position, ResourceType cropType) {
        Crop newCrop = new Crop(position.x, position.y, cropType);
        crops.put(position, newCrop);
        
        // set field crop type if not already set
        if (this.cropType == null) {
            this.cropType = cropType;
            Sign.updateAllSigns();
        }
        
        // add crop to entities
        if (Farm.entitiesHandler != null) {
            Farm.entitiesHandler.clickableMapEntities.add(newCrop);
            Farm.entitiesHandler.topRenderableEntities.add(newCrop);
            Farm.entitiesHandler.updatableMapEntities.add(newCrop);
        }
    }

    // crop removal
    public void removeCrop(Point position) {
        crops.remove(position);
        
        if (crops.isEmpty()) {
            cropType = null;
            Sign.updateAllSigns();
        }
    }


    // getters
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
