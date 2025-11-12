package Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FarmResourcesHandler {

    public enum ResourceType {

        LETTUCE, TOMATO, // lvl 0
        CORN, CARROT, // lvl 1
        WHEAT, CUCUMBER, RADISH, // lvl 2
        CAULIFLOWER, EGGPLANT, PUMPKIN, // lvl 3
        STAR_FRUIT,  // lvl star

        APPLE,        // lvl 1
        PEAR,         // lvl 2
        PEACH,        // lvl 3
        ORANGE,       // lvl star

        // Bush fruits #TODO

        EGG,                // lvl 1
        MILK,              // lvl 1
        CHOCOLATE_MILK,    // lvl 2
        STRAWBERRY_MILK,   // lvl star
    }

    public HashMap<ResourceType, Integer> quantities = new HashMap<>();
    public HashMap<ResourceType, Boolean> unlocked = new HashMap<>();

    public FarmResourcesHandler() {
        for (ResourceType resource : ResourceType.values()) {
            quantities.put(resource, 0);
            unlocked.put(resource, false);
        }

        unlocked.put(ResourceType.LETTUCE, true);
        unlocked.put(ResourceType.TOMATO, true);
    }
    

    public void addResource(ResourceType resourceType, int amount) {
        int currentQuantity = quantities.getOrDefault(resourceType, 0);
        quantities.put(resourceType, currentQuantity + amount);
        Farm.menuPanel.refreshResourcesDisplay();
    }
    
    public void removeResource(ResourceType resourceType, int amount) {
        int currentQuantity = quantities.getOrDefault(resourceType, 0);
        if (currentQuantity >= amount) {
            quantities.put(resourceType, currentQuantity - amount);
            Farm.menuPanel.refreshResourcesDisplay();
        }
    }

    public static boolean isCropResource(ResourceType resourceType) {
        return switch (resourceType) {
            case LETTUCE, TOMATO, CORN, CARROT, WHEAT, CUCUMBER, RADISH,
                 CAULIFLOWER, EGGPLANT, PUMPKIN, STAR_FRUIT -> true;
            default -> false;
        };
    }

    public boolean isUnlocked(ResourceType resourceType) {
        return unlocked.getOrDefault(resourceType, false);
    }
    
    public void unlockResource(ResourceType resourceType) {
        unlocked.put(resourceType, true);
        Farm.ordersHandler.maintainOrderCount();
        Farm.menuPanel.refreshResourcesDisplay();
    }

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