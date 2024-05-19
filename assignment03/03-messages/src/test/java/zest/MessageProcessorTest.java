package zest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class MessageProcessorTest {

    @Test
    void test_numberOfInvocations() {

        // Create a mock instance of MessageService
        MessageService messageService = mock(MessageService.class);

        // Create a real messageProcessor instance, inject the mock
        MessageProcessor messageProcessor = new MessageProcessor(messageService);

        // Create a list of message instances
        List<Message> messages = Arrays.asList(
            new Message("sender1", "receiver1", "content1")
        );

        // Process the messages
        messageProcessor.processMessages(messages);

        // Verify the number of invocations
        verify(messageService, times(1)).sendMessage(anyString(), anyString());
    }

    @Test
    void test_ArgumentCaptor() {

        // Create a mock instance of MessageService
        MessageService messageService = mock(MessageService.class);

        // Create a real messageProcessor instance, inject the mock
        MessageProcessor messageProcessor = new MessageProcessor(messageService);

        // Create a list of message instances
        Message m1 = new Message("sender1", "receiver1", "content1");
        List<Message> messages = Arrays.asList(
                m1
        );

        // Process the messages
        messageProcessor.processMessages(messages);

        // Create ArgumentCaptor instances for the two string instances
        ArgumentCaptor<String> messageReceiverCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> messageContentCaptor = ArgumentCaptor.forClass(String.class);

        // Do not verify times, we did that already
        // Verify the arguments of the message are correct
        verify(messageService).sendMessage(
            messageReceiverCaptor.capture(),
            messageContentCaptor.capture()
        );

        // Verify that the strings match
        assertEquals(m1.getReceiver(), messageReceiverCaptor.getValue());
        assertEquals(m1.getContent(), messageContentCaptor.getValue());

    }

    @Test
    void test_increasingObservability() {
        // Create a mock instance of MessageService
        MessageService messageService = mock(MessageService.class);

        // Create a real messageProcessor instance, inject the mock
        MessageProcessor messageProcessor = new MessageProcessor(messageService);

        // Create a list of message instances
        Message m1 = new Message("sender1", "receiver1", "content1");
        Message m2 = new Message("sender2", "receiver2", "content2");
        List<Message> messages = Arrays.asList(
                m1,
                m2
        );

        // Process the messages
        messageProcessor.processMessages(messages);

        // Verify the status of the messages we went through the messageProcessor class
        assertTrue(messageProcessor.messagesOk);

    }

}