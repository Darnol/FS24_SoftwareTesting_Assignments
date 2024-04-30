package zest;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventPublisherTest {

    @Test
    void test_dummy() {
        assertTrue(true);
    }

    @Test
    void test_numberOfInvocations() {

        // a real instance of EventPublisher and Order, no need to mock them
        EventPublisher eventPublisher = new EventPublisher();
        Order order = new Order("firstOrder", 1);

        // Mock the two EventListener dependencies
        EventListener emailService = mock(EmailNotificationService.class);
        EventListener inventoryManager = mock(InventoryManager.class);

        // Add the two listeners to our eventPublishers
        eventPublisher.subscribe(emailService);
        eventPublisher.subscribe(inventoryManager);

        // Call the publish method
        eventPublisher.publishOrderToAllListeners(order);

        // Verify that each service called the onOrderPlaced method once
        verify(emailService, times(1)).onOrderPlaced(order);
        verify(inventoryManager, times(1)).onOrderPlaced(order);
    }

    @Test
    void test_ArgumentCaptor() {

        // a real instance of EventPublisher and Order, no need to mock them
        EventPublisher eventPublisher = new EventPublisher();
        Order order = new Order("firstOrder", 1);

        // Mock the two EventListener dependencies
        EventListener emailService = mock(EmailNotificationService.class);
        EventListener inventoryManager = mock(InventoryManager.class);

        // Add the two listeners to our eventPublishers
        eventPublisher.subscribe(emailService);
        eventPublisher.subscribe(inventoryManager);

        // Call the publish method
        eventPublisher.publishOrderToAllListeners(order);

        // Create ArgumentCaptors instances
        ArgumentCaptor<Order> emailServiceCaptor = ArgumentCaptor.forClass(Order.class);
        ArgumentCaptor<Order> inventoryManagerCaptor = ArgumentCaptor.forClass(Order.class);

        // Do not verify times, we did that already
        // Verify the arguments of the order are correct
        verify(emailService).onOrderPlaced(emailServiceCaptor.capture());
        verify(inventoryManager).onOrderPlaced(inventoryManagerCaptor.capture());

        // OrderId
        assertEquals(order.getOrderId(), emailServiceCaptor.getValue().getOrderId());
        assertEquals(order.getOrderId(), inventoryManagerCaptor.getValue().getOrderId());

        // Amount
        assertEquals(order.getAmount(), emailServiceCaptor.getValue().getAmount());
        assertEquals(order.getAmount(), inventoryManagerCaptor.getValue().getAmount());

    }

    @Test
    void test_enhancedObservability() {

        // a real instance of EventPublisher and Order, no need to mock them
        EventPublisher eventPublisher = new EventPublisher();
        Order order = new Order("firstOrder", 1);

        // Mock the two EventListener dependencies
        EventListener emailService = mock(EmailNotificationService.class);
        EventListener inventoryManager = mock(InventoryManager.class);

        // Add the two listeners to our eventPublishers
        eventPublisher.subscribe(emailService);
        eventPublisher.subscribe(inventoryManager);

        // Call the publish method
        List<Order> publishedOrders = eventPublisher.publishOrderToAllListeners(order);

        // Verify that the returned order objects from the publisher listeners are correct
        for (Order o : publishedOrders) {
            assertEquals(order.getOrderId(), o.getOrderId());
            assertEquals(order.getAmount(), o.getAmount());
        }
    }

}
