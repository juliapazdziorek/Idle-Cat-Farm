package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Game.FarmResourcesHandler.ResourceType;

public class OrdersHandler {

    // random
    private static final Random random = new Random();

    // orders
    public ArrayList<ArrayList<OrderPart>> orders;
    private final FarmResourcesHandler farmResources;

    public OrdersHandler(FarmResourcesHandler farmResources) {
        this.orders = new ArrayList<>();
        this.farmResources = farmResources;
    }


    // order part class
    public static class OrderPart {
        public ResourceType resourceType;
        public int quantity;

        public OrderPart(ResourceType resourceType) {
            this.resourceType = resourceType;
            this.quantity = random.nextInt(5) + 1;
        }
    }


    // create an order
    public ArrayList<OrderPart> createNewOrder() {
        List<ResourceType> unlockedResources = farmResources.getUnlockedResources();
        if (unlockedResources.isEmpty()) {
            return new ArrayList<>();
        }

        int maxOrderParts = Math.min(3, unlockedResources.size());
        int numOrderParts = random.nextInt(maxOrderParts) + 1;
        ArrayList<OrderPart> newOrder = new ArrayList<>();

        List<ResourceType> availableResources = new ArrayList<>(unlockedResources);
        for (int i = 0; i < numOrderParts; i++) {
            int randomIndex = random.nextInt(availableResources.size());
            ResourceType selectedResource = availableResources.get(randomIndex);
            newOrder.add(new OrderPart(selectedResource));
            availableResources.remove(randomIndex);
        }

        return newOrder;
    }

    // add a new order to the list
    public void addNewOrder() {
        ArrayList<OrderPart> newOrder = createNewOrder();
        if (!newOrder.isEmpty()) {
            orders.add(newOrder);
        }
    }

    // check if an order can be fulfilled
    public boolean canFulfillOrder(ArrayList<OrderPart> order) {
        for (OrderPart orderPart : order) {
            int availableQuantity = farmResources.getQuantity(orderPart.resourceType);
            if (availableQuantity < orderPart.quantity) {
                return false;
            }
        }
        return true;
    }

    // fulfill an order
    public boolean fulfillOrder(ArrayList<OrderPart> order) {
        if (!canFulfillOrder(order)) {
            return false;
        }

        // calculate total money earned and remove resources
        int moneyEarned = 0;
        for (OrderPart orderPart : order) {
            farmResources.removeResource(orderPart.resourceType, orderPart.quantity);
            moneyEarned += orderPart.quantity;
        }

        Farm.addMoney(moneyEarned);
        orders.remove(order);

        maintainOrderCount();
        return true;
    }

    public void maintainOrderCount() {
        int targetCount = getTargetOrderCount();
        while (orders.size() > targetCount) {
            orders.removeLast();
        }
        while (orders.size() < targetCount) {
            addNewOrder();
        }
    }

    private int getTargetOrderCount() {
        int unlockedCount = farmResources.getUnlockedResources().size();
        if (unlockedCount < 5) return 1;
        else if (unlockedCount < 7) return 2;
        else if (unlockedCount < 10) return 2;
        else if (unlockedCount < 12) return 3;
        else if (unlockedCount < 15) return 4;
        else return 5; // 19 (every) resources unlocked
    }
}
