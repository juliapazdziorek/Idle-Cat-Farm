package Entities.FarmResources;

import Entities.Entity;
import Game.Farm;
import Game.FarmResourcesHandler.ResourceType;
import Map.Field;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Crop extends Entity {

    // crop properties
    private ResourceType cropType;
    private int currentGrowthStage;
    private int maxGrowthStages;
    private boolean isFullyGrown;
    
    // growth timing
    private long lastGrowthTime;
    private long growthDuration;

    private static final Map<ResourceType, Integer> CROP_GROWTH_STAGES = new HashMap<>();
    private static final Map<ResourceType, Long> CROP_GROWTH_DURATIONS = new HashMap<>();
    
    static {
        CROP_GROWTH_STAGES.put(ResourceType.LETTUCE, 4);
        CROP_GROWTH_STAGES.put(ResourceType.TOMATO, 4);
        CROP_GROWTH_STAGES.put(ResourceType.CORN, 5);
        CROP_GROWTH_STAGES.put(ResourceType.CARROT, 4);
        CROP_GROWTH_STAGES.put(ResourceType.WHEAT, 4);
        CROP_GROWTH_STAGES.put(ResourceType.CUCUMBER, 4);
        CROP_GROWTH_STAGES.put(ResourceType.RADISH, 4);
        CROP_GROWTH_STAGES.put(ResourceType.CAULIFLOWER, 4);
        CROP_GROWTH_STAGES.put(ResourceType.EGGPLANT, 4);
        CROP_GROWTH_STAGES.put(ResourceType.PUMPKIN, 4);
        CROP_GROWTH_STAGES.put(ResourceType.STAR_FRUIT, 4);

        for (ResourceType type : CROP_GROWTH_STAGES.keySet()) {
            CROP_GROWTH_DURATIONS.put(type, 3000L); // 3 seconds per stage for testing
        }
    }

    public Crop(int tileX, int tileY, ResourceType cropType) {
        super();
        this.position = new Point(tileX * Farm.tileSize, tileY * Farm.tileSize);
        this.cropType = cropType;
        this.currentGrowthStage = 1;
        this.maxGrowthStages = CROP_GROWTH_STAGES.getOrDefault(cropType, 4);
        this.growthDuration = CROP_GROWTH_DURATIONS.getOrDefault(cropType, 30000L);
        this.isFullyGrown = false;
        this.lastGrowthTime = System.currentTimeMillis();
        this.clickable = false;
        
        updateCurrentImage();
    }
    
    public Crop(Point position, ResourceType cropType) {
        this(position.x, position.y, cropType);
    }

    @Override
    public void update() {
        super.update();

        if (!isFullyGrown && System.currentTimeMillis() - lastGrowthTime >= growthDuration) {
            grow();
        }
    }
    
    private void grow() {
        if (currentGrowthStage < maxGrowthStages) {
            currentGrowthStage++;
            lastGrowthTime = System.currentTimeMillis();
            updateCurrentImage();
            
            if (currentGrowthStage == maxGrowthStages) {
                isFullyGrown = true;
                clickable = true;
                
                // notify field to update state when crop becomes fully grown
                notifyFieldStateChange();
            }
        }
    }
    
    private void updateCurrentImage() {
        String imageKey = getCropImageKey();
        currentImage = Farm.resourceHandler.cropsMap.get(imageKey);
    }
    
    private void notifyFieldStateChange() {
        // find the field that contains this crop and update its state
        Point cropPosition = new Point(position.x / Farm.tileSize, position.y / Farm.tileSize);
        if (Farm.entitiesHandler != null && Farm.entitiesHandler.map != null) {
            for (Field field : Farm.entitiesHandler.map.fields) {
                if (field.getCrops().containsKey(cropPosition)) {
                    // call updateFieldState through a public method
                    field.setCatWorkingOnField(field.isCatWorkingOnField()); // this will trigger updateFieldState
                    break;
                }
            }
        }
    }
    
    private String getCropImageKey() {
        String cropName = cropType.name().toLowerCase();
        
        if (cropType == ResourceType.STAR_FRUIT) {
            cropName = "starFruit";
        }
        
        if (cropType == ResourceType.CORN && currentGrowthStage >= 4) {
            if (currentGrowthStage == 4) {
                return "corn4bottom";
            } else if (currentGrowthStage == 5) {
                return "corn5bottom";
            }
        }
        
        return cropName + currentGrowthStage;
    }
    

    public void harvest() {
        if (!isFullyGrown) {
            return;
        }

        Farm.farmResourcesHandler.addResource(cropType, 1);
        Point cropPosition = new Point(position.x / Farm.tileSize, position.y / Farm.tileSize);
        if (Farm.entitiesHandler != null && Farm.entitiesHandler.map != null) {
            for (Field field : Farm.entitiesHandler.map.fields) {
                if (field.getCrops().containsKey(cropPosition)) {
                    field.removeCrop(cropPosition);
                    break;
                }
            }
        }
        
        if (Farm.entitiesHandler != null) {
            Farm.entitiesHandler.queueEntityForRemoval(this);
        }

    }
    
    public void instantGrow() {
        currentGrowthStage = maxGrowthStages;
        isFullyGrown = true;
        clickable = true;
        updateCurrentImage();
    }

    @Override
    public void render(Graphics2D g2) {
        g2.drawImage(currentImage,
                Farm.camera.position.x + position.x * Farm.scale,
                Farm.camera.position.y + position.y * Farm.scale,
                Farm.scaledTileSize,
                Farm.scaledTileSize,
                null);

        if (cropType == ResourceType.CORN && currentGrowthStage >= 4) {
            String topImageKey = "corn" + currentGrowthStage + "up";
            BufferedImage topImage = Farm.resourceHandler.cropsMap.get(topImageKey);
            if (topImage != null) {
                g2.drawImage(topImage,
                        Farm.camera.position.x + position.x * Farm.scale,
                        Farm.camera.position.y + position.y * Farm.scale - Farm.scaledTileSize,
                        Farm.scaledTileSize, Farm.scaledTileSize, null);
            }
        }
    }
    
    @Override
    public void onClick() {
        if (clickable && isFullyGrown) {
            harvest();
        }
    }
    
    public ResourceType getCropType() {
        return cropType;
    }
    
    public int getCurrentGrowthStage() {
        return currentGrowthStage;
    }
    
    public int getMaxGrowthStages() {
        return maxGrowthStages;
    }
    
    public boolean isFullyGrown() {
        return isFullyGrown;
    }
    
    public long getGrowthProgress() {
        if (isFullyGrown) return 100;
        long timeSinceLastGrowth = System.currentTimeMillis() - lastGrowthTime;
        return Math.min(100, (timeSinceLastGrowth * 100) / growthDuration);
    }
    
    public void setGrowthDuration(long duration) {
        this.growthDuration = duration;
    }
    
    public void setCropType(ResourceType newType) {
        this.cropType = newType;
        this.maxGrowthStages = CROP_GROWTH_STAGES.getOrDefault(newType, 4);
        this.growthDuration = CROP_GROWTH_DURATIONS.getOrDefault(newType, 30000L);
        updateCurrentImage();
    }
}
