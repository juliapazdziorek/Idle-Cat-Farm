package Game;

public class MoneyHandler {
    
    private static int money = 10000; // TODO: change to 0 when done testing
    
    public static void addMoney(int amount) {
        money += amount;
        Farm.menuPanel.refreshResourcesDisplay();
    }

    public static void subtractMoney(int amount) {
        if (money >= amount) {
            money -= amount;
            Farm.menuPanel.refreshResourcesDisplay();
        }
    }
    
    public static boolean canAfford(int amount) {
        return money >= amount;
    }

    public static int getMoney() {
        return money;
    }
}
