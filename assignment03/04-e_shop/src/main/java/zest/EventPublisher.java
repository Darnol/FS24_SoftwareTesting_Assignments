package zest;

import java.util.ArrayList;
import java.util.List;

public class EventPublisher {
    private List<EventListener> listeners = new ArrayList<>();

    // C. Content of invocations—Increasing observability
    private boolean placedOrderVerified = true;

    // C. Content of invocations—Increasing observability
    private boolean verifyOrderInput(Order o1, Order o2) {
        return o1.getOrderId().equals(o2.getOrderId()) && o1.getAmount() == o2.getAmount();
    }
    public boolean isOrderOk() {
        return this.placedOrderVerified;
    }

    public void subscribe(EventListener listener) {
        listeners.add(listener);
    }

    public void publishOrderToAllListeners(Order order) {
        for (EventListener listener : listeners) {
            Order tmpOrder = listener.onOrderPlaced(order);

            // C. Content of invocations—Increasing observability
            // Each order should pass the test, if not set the flag to false
            if (!(verifyOrderInput(order, tmpOrder))) {
                this.placedOrderVerified = false;
            }
        }
    }
}
