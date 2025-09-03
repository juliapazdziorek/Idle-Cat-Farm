package Map;

import Entities.FarmResources.Crop;
import Game.Farm;
import Game.FarmResourcesHandler.ResourceType;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Field {

    // field structure
    private final ArrayList<Point> cropPositions;
    public ResourceType cropType;
    
    // crop management
    private final Map<Point, Crop> crops;

    public Field() {
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
    }

    // crop removal
    public void removeCrop(Point position) {
        crops.remove(position);
        
        if (crops.isEmpty()) {
            cropType = null;
        }
    }

    // getters
    public Map<Point, Crop> getCrops() {
        return crops;
    }

    public ArrayList<Point> getCropPositions() {
        return new ArrayList<>(cropPositions);
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
}
