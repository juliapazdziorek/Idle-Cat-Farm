package Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FarmResourcesHandler {

    // farm resources
    public enum ResourceType {

        // crops
        LETTUCE, // lvl 0
        TOMATO, // lvl 0
        CORN, // lvl 1
        CARROT, // lvl 1
        WHEAT, // lvl 2
        CUCUMBER, // lvl 2
        RADISH, // lvl 2
        CAULIFLOWER, // lvl 3
        EGGPLANT, // lvl 3
        PUMPKIN, // lvl 3
        STAR, // lvl star

        // fruits
        APPLE, // lvl 1
        PEAR, // lvl 2
        PEACH, // lvl 3
        ORANGE, // lvl star

        // eggs
        EGG, // lvl 1

        // milk
        MILK, // lvl 1
        CHOCOLATE_MILK, // lvl 2
        STRAWBERRY_MILK, // lvl star
    }

    // quantities
    public HashMap<ResourceType, Integer> quantities = new HashMap<>();

    // unlocked resources
    public HashMap<ResourceType, Boolean> unlocked = new HashMap<>();

    public FarmResourcesHandler() {

        // initialize quantities
        for (ResourceType resource : ResourceType.values()) {
            quantities.put(resource, 0);
            unlocked.put(resource, false);
        }

        // set initially unlocked resources
        unlocked.put(ResourceType.LETTUCE, true);
        unlocked.put(ResourceType.TOMATO, true);
    }
    

    // add resources
    public void addResource(ResourceType resourceType, int amount) {
        int currentQuantity = quantities.getOrDefault(resourceType, 0);
        quantities.put(resourceType, currentQuantity + amount);
        
        // notify GUI to refresh
        if (Farm.menuPanel != null) {
            Farm.menuPanel.refreshResourcesDisplay();
        }
    }
    
    // remove resources
    public void removeResource(ResourceType resourceType, int amount) {
        int currentQuantity = quantities.getOrDefault(resourceType, 0);
        if (currentQuantity >= amount) {
            quantities.put(resourceType, currentQuantity - amount);
            
            // notify GUI to refresh
            if (Farm.menuPanel != null) {
                Farm.menuPanel.refreshResourcesDisplay();
            }
        }
    }

    
    // check if resource is unlocked
    public boolean isUnlocked(ResourceType resourceType) {
        return unlocked.getOrDefault(resourceType, false);
    }
    
    // unlock a resource
    public void unlockResource(ResourceType resourceType) {
        unlocked.put(resourceType, true);

        if (Farm.ordersHandler != null) {
            Farm.ordersHandler.maintainOrderCount();
        }

        if (Farm.menuPanel != null) {
            Farm.menuPanel.refreshResourcesDisplay();
        }
    }


    // getters
    public int getQuantity(ResourceType resourceType) {
        return quantities.getOrDefault(resourceType, 0);
    }

    public List<ResourceType> getUnlockedResources() {
        List<ResourceType> unlockedResources = new ArrayList<>();
        for (ResourceType resourceType : ResourceType.values()) {
            if (isUnlocked(resourceType)) {
                unlockedResources.add(resourceType);
            }
        }
        return unlockedResources;
    }
}
