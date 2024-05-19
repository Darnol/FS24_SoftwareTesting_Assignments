package zest;

public class InventoryManager implements EventListener {
    @Override
    public Order onOrderPlaced(Order order) {
        // Logic to update inventory based on order would go here
        System.out.println("Inventory updated for order " + order.getOrderId());
        return order;
    }
}