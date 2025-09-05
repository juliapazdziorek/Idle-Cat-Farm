package Game;

public class MoneyHandler {
    
    private static int money = 1000; // temporary starting amount TODO: change to 0

    
    // money management
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
    
    public static boolean canAfford(int amount)
    {
        return money >= amount;
    }


    // getters
    public static int getMoney() {
        return money;
    }
}
