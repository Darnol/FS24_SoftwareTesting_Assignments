package zest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

import net.jqwik.api.*;
import org.mockito.ArgumentCaptor;

public class EventPublisherTest_TaskD {
    /**
     * This test suite concerns task D
     * It tests the EventPublisher class using the ArgumentCaptor approach
     */

    private EventPublisher eventPublisher;
    private EventListener emailService;
    private EventListener inventoryManager;

    void printOrder(Order order) {
        /**
         * Utility to look at some generated inputs
         */
        System.out.println(order.getOrderId() + " " + order.getAmount());
    }

    @Provide
    Arbitrary<Order> orders() {

        // Generate random id strings
        Arbitrary<String> ids = Arbitraries.strings().alpha().ofMinLength(0).ofMaxLength(100);

        // Generate random amounts
        Arbitrary<Double> amounts = Arbitraries.doubles().between(-1000.0, 10000.0);

        return Combinators.combine(ids, amounts).as(Order::new);
    }

    @Property(tries = 20)
    void test_publishOrderToAllListeners_ArgumentCaptor(
        @ForAll("orders") Order order
    ) {

        // a real instance of EventPublisher and Order, no need to mock them
        EventPublisher eventPublisher = new EventPublisher();

        // Mock the two EventListener dependencies
        EventListener emailService = mock(EmailNotificationService.class);
        EventListener inventoryManager = mock(InventoryManager.class);

        // Add the two listeners to our eventPublishers
        eventPublisher.subscribe(emailService);
        eventPublisher.subscribe(inventoryManager);

        // Mock the response of the EventListener dependencies
        when(emailService.onOrderPlaced(order)).thenReturn(new Order(order.getOrderId(), order.getAmount()));
        when(inventoryManager.onOrderPlaced(order)).thenReturn(new Order(order.getOrderId(), order.getAmount()));

        // Call the publish method
        eventPublisher.publishOrderToAllListeners(order);

        // Create ArgumentCaptors instances
        ArgumentCaptor<Order> emailServiceCaptor = ArgumentCaptor.forClass(Order.class);
        ArgumentCaptor<Order> inventoryManagerCaptor = ArgumentCaptor.forClass(Order.class);

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

    @Property(tries = 20)
    void test_publishOrderToAllListeners_enhancedObservability(
            @ForAll("orders") Order order
    ) {

        // a real instance of EventPublisher and Order, no need to mock them
        EventPublisher eventPublisher = new EventPublisher();

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

}
