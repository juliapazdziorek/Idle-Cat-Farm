package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Game.FarmResourcesHandler.ResourceType;

public class OrdersHandler {

    private static final Random random = new Random();
    private final FarmResourcesHandler farmResources;
    public ArrayList<ArrayList<OrderPart>> orders;
    private boolean createFirstOrder;

    public OrdersHandler(FarmResourcesHandler farmResourcesHandler) {
        this.farmResources = farmResourcesHandler;
        this.orders = new ArrayList<>();
        this.createFirstOrder = true;
    }

    public static class OrderPart {
        public ResourceType resourceType;
        public int quantity;

        public OrderPart(ResourceType resourceType) {
            this.resourceType = resourceType;
            this.quantity = random.nextInt(5) + 1;
        }

        public OrderPart(ResourceType resourceType, int quantity) {
            this.resourceType = resourceType;
            this.quantity = quantity;
        }
    }

    public void addNewOrder() {
        ArrayList<OrderPart> newOrder;
        if (createFirstOrder) {
            newOrder = createFirstOrder();
            orders.add(newOrder);
            createFirstOrder = false;
            return;
        }

        newOrder = createNewOrder();
        orders.add(newOrder);
    }

    private ArrayList<OrderPart> createFirstOrder() {
        List<ResourceType> unlockedResources = farmResources.getUnlockedResources();
        ArrayList<OrderPart> firstOrder = new ArrayList<>();

        int randomIndex = random.nextInt(unlockedResources.size());
        ResourceType randomResource = unlockedResources.get(randomIndex);
        firstOrder.add(new OrderPart(randomResource, 5));
        return firstOrder;
    }

    private ArrayList<OrderPart> createNewOrder() {
        List<ResourceType> unlockedResources = farmResources.getUnlockedResources();

        int maxOrderParts = Math.min(3, unlockedResources.size());
        int amountOfOrderParts = random.nextInt(maxOrderParts) + 1;
        ArrayList<OrderPart> newOrder = new ArrayList<>();

        List<ResourceType> availableResources = new ArrayList<>(unlockedResources);
        for (int i = 0; i < amountOfOrderParts; i++) {
            int randomIndex = random.nextInt(availableResources.size());
            ResourceType randomResource = availableResources.get(randomIndex);
            newOrder.add(new OrderPart(randomResource));
            availableResources.remove(randomIndex);
        }

        return newOrder;
    }

    public boolean canFulfillOrder(ArrayList<OrderPart> order) {
        for (OrderPart orderPart : order) {
            int availableQuantity = farmResources.getQuantity(orderPart.resourceType);
            if (availableQuantity < orderPart.quantity) {
                return false;
            }
        }
        return true;
    }

    public boolean fulfillOrder(ArrayList<OrderPart> order) {
        int moneyEarned = 0;
        for (OrderPart orderPart : order) {
            farmResources.removeResource(orderPart.resourceType, orderPart.quantity);
            moneyEarned += orderPart.quantity * 2;
        }

        MoneyHandler.addMoney(moneyEarned);
        orders.remove(order);

        maintainOrderCount();
        return true;
    }

    public void maintainOrderCount() {
        int targetCount = getOrderCount();
        while (orders.size() > targetCount) {
            orders.removeLast();
        }
        while (orders.size() < targetCount) {
            addNewOrder();
        }
    }

    private int getOrderCount() {
        int unlockedCount = farmResources.getUnlockedResources().size();
        if (unlockedCount < 5) return 1;
        else if (unlockedCount < 7) return 2;
        else if (unlockedCount < 10) return 2;
        else if (unlockedCount < 12) return 3;
        else if (unlockedCount < 15) return 4;
        else return 5;
    }
}
