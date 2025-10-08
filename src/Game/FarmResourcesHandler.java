package Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Manages farm resources.
 * Handles all resource types from basic crops to premium products across 5 levels.
 */
public class FarmResourcesHandler {

    /**
     * All resource types available in the game, organized by progression level.
     * Level 0: Starting resources, Level Star: end-game resources.
     */
    public enum ResourceType {

        // Crops
        LETTUCE, TOMATO, // lvl 0
        CORN, CARROT, // lvl 1
        WHEAT, CUCUMBER, RADISH, // lvl 2
        CAULIFLOWER, EGGPLANT, PUMPKIN, // lvl 3
        STAR_FRUIT,  // lvl star

        // Tree fruits
        APPLE,        // lvl 1
        PEAR,         // lvl 2
        PEACH,        // lvl 3
        ORANGE,       // lvl star

        // Bush fruits #TODO

        // Animal products
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
    

    /** Adds resources to inventory and updates UI display. */
    public void addResource(ResourceType resourceType, int amount) {
        int currentQuantity = quantities.getOrDefault(resourceType, 0);
        quantities.put(resourceType, currentQuantity + amount);
        Farm.menuPanel.refreshResourcesDisplay();
    }
    
    /** Removes resources from inventory if a sufficient quantity exists. */
    public void removeResource(ResourceType resourceType, int amount) {
        int currentQuantity = quantities.getOrDefault(resourceType, 0);
        if (currentQuantity >= amount) {
            quantities.put(resourceType, currentQuantity - amount);
            Farm.menuPanel.refreshResourcesDisplay();
        }
    }

    /** Categorizes resources to crop category. */
    public static boolean isCropResource(ResourceType resourceType) {
        return switch (resourceType) {
            case LETTUCE, TOMATO, CORN, CARROT, WHEAT, CUCUMBER, RADISH,
                 CAULIFLOWER, EGGPLANT, PUMPKIN, STAR_FRUIT -> true;
            default -> false;
        };
    }

    /** Checks if a resource type is available for use in game progression. */
    public boolean isUnlocked(ResourceType resourceType) {
        return unlocked.getOrDefault(resourceType, false);
    }
    
    /** Unlocks a new resource type. */
    public void unlockResource(ResourceType resourceType) {
        unlocked.put(resourceType, true);
        Farm.ordersHandler.maintainOrderCount();
        Farm.menuPanel.refreshResourcesDisplay();
    }

    /** Returns current inventory quantity for a specific resource. */
    public int getQuantity(ResourceType resourceType) {
        return quantities.getOrDefault(resourceType, 0);
    }

    /** Provides a list of all currently unlocked resources. */
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