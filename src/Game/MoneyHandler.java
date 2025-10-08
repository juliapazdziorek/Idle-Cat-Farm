package Game;

/**
 * Manages the farm's monetary system, handling transactions and 
 * automatically updating UI displays when money changes.
 */
public class MoneyHandler {
    
    private static int money = 10000; // TODO: change to 0 when done testing
    
    /** Adds money to the farm balance and updates the UI display. */
    public static void addMoney(int amount) {
        money += amount;
        Farm.menuPanel.refreshResourcesDisplay();
    }

    /** Subtracts money from the farm balance if sufficient funds exist, updates UI display. */
    public static void subtractMoney(int amount) {
        if (money >= amount) {
            money -= amount;
            Farm.menuPanel.refreshResourcesDisplay();
        }
    }
    
    /** Checks if the farm has sufficient funds for a purchase. */
    public static boolean canAfford(int amount) {
        return money >= amount;
    }

    /** Returns the current farm money balance. */
    public static int getMoney() {
        return money;
    }
}
