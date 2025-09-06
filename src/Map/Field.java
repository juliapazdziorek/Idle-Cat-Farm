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
        if (!isAvailableForPlanting()) {
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
                Farm.entitiesHandler.renderableMapEntities.add(newCrop);
                Farm.entitiesHandler.updatableMapEntities.add(newCrop);
            }
        }
        
        return true;
    }
    
    // field status checks
    public boolean isAvailableForPlanting() {
        return crops.isEmpty();
    }
    
    public boolean isCompletelyHarvested() {
        return crops.isEmpty();
    }
    
    public ResourceType getCurrentCropType() {
        return cropType;
    }
    
    // field maintenance
    public void completeFieldPlanting() {
        if (cropType != null) {
            for (Point position : cropPositions) {
                if (!crops.containsKey(position)) {
                    Crop newCrop = new Crop(position.x, position.y, cropType);
                    crops.put(position, newCrop);
                    
                    if (Farm.entitiesHandler != null) {
                        Farm.entitiesHandler.clickableMapEntities.add(newCrop);
                        Farm.entitiesHandler.renderableMapEntities.add(newCrop);
                        Farm.entitiesHandler.updatableMapEntities.add(newCrop);
                    }
                }
            }
        }
        
        // update signs after completing planting
        Sign.updateAllSigns();
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
    
    public String getFieldName() {
        return switch (fieldType) {
            case EAST -> "east field";
            case WEST -> "west field";
        };
    }
    
    // utility methods
    public static boolean plantCropInAvailableField(ResourceType cropType) {
        if (Farm.entitiesHandler == null || Farm.entitiesHandler.map == null || 
            Farm.entitiesHandler.map.fields.isEmpty()) {
            return false;
        }

        for (Field field : Farm.entitiesHandler.map.fields) {
            if (field.isAvailableForPlanting()) {
                boolean success = field.plantCrop(cropType);
                if (success) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public static Field getFieldByType(FieldType fieldType) {
        if (Farm.entitiesHandler == null || Farm.entitiesHandler.map == null) {
            return null;
        }
        
        for (Field field : Farm.entitiesHandler.map.fields) {
            if (field.getFieldType() == fieldType) {
                return field;
            }
        }
        
        return null;
    }
}
