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

        // Mock the response of the EventListener dependencies
        when(emailService.onOrderPlaced(order)).thenReturn(new Order(order.getOrderId(), order.getAmount()));
        when(inventoryManager.onOrderPlaced(order)).thenReturn(new Order(order.getOrderId(), order.getAmount()));

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

        // Mock the response of the EventListener dependencies
        when(emailService.onOrderPlaced(order)).thenReturn(new Order(order.getOrderId(), order.getAmount()));
        when(inventoryManager.onOrderPlaced(order)).thenReturn(new Order(order.getOrderId(), order.getAmount()));

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

        /**
         * This test is for task C. Content of invocations—Increasing observability
         */

        // a real instance of EventPublisher and Order, no need to mock them
        EventPublisher eventPublisher = new EventPublisher();
        Order order = new Order("firstOrder", 1);

        // Mock the two EventListener dependencies
        EventListener emailService = mock(EmailNotificationService.class);
        EventListener inventoryManager = mock(InventoryManager.class);

        // Mock the response of the EventListener dependencies
        when(emailService.onOrderPlaced(order)).thenReturn(new Order(order.getOrderId(), order.getAmount()));
        when(inventoryManager.onOrderPlaced(order)).thenReturn(new Order(order.getOrderId(), order.getAmount()));

        // Add the two listeners to our eventPublishers
        eventPublisher.subscribe(emailService);
        eventPublisher.subscribe(inventoryManager);

        // Call the publish method
        eventPublisher.publishOrderToAllListeners(order);

        // Verify that the returned order objects did pass the verification, e.g. return the correct order id and amount
        assertTrue(eventPublisher.isOrderOk());

    }

    @Test
    void test_enhancedObservability_assertFailure() {

        /**
         * This test asserts that the mocking and the observability really work by mocking a "false" answer in the
         * EventListener dependency "emailService"
         */

        // a real instance of EventPublisher and Order, no need to mock them
        EventPublisher eventPublisher = new EventPublisher();
        Order order = new Order("firstOrder", 1);

        // Mock the two EventListener dependencies
        EventListener emailService = mock(EmailNotificationService.class);
        EventListener inventoryManager = mock(InventoryManager.class);

        // Mock the response of the EventListener dependencies
        // ON PURPOSE introduce a "false" order return value in the emailService
        when(emailService.onOrderPlaced(order)).thenReturn(new Order("falseOrderId", 99));
        when(inventoryManager.onOrderPlaced(order)).thenReturn(new Order(order.getOrderId(), order.getAmount()));

        // Add the two listeners to our eventPublishers
        eventPublisher.subscribe(emailService);
        eventPublisher.subscribe(inventoryManager);

        // Call the publish method
        eventPublisher.publishOrderToAllListeners(order);

        // Verify that the returned order objects DID NOT pass the verification
        assertFalse(eventPublisher.isOrderOk());

    }

}
