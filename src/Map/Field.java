package Map;

import Entities.FarmResources.Crop;
import Game.Farm;
import Game.FarmResourcesHandler.ResourceType;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Field {

    private final ArrayList<Point> cropPositions;
    public ResourceType cropType;
    
    private final Map<Point, Crop> crops;

    public Field() {
        cropPositions = new ArrayList<>();
        crops = new HashMap<>();
    }

    public void addCropPositions(ArrayList<Point> positions) {
        cropPositions.addAll(positions);
    }

    public void plantCrop(ResourceType cropType) {
        this.cropType = cropType;
        
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
    

    public boolean isAvailableForPlanting() {
        return crops.isEmpty();
    }

    public Map<Point, Crop> getCrops() {
        return crops;
    }

    public void removeCrop(Point position) {
        crops.remove(position);
    }

    public ArrayList<Point> getCropPositions() {
        return new ArrayList<>(cropPositions);
    }
}
