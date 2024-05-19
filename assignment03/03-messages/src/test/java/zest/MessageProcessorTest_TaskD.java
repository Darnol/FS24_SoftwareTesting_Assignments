package zest;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

import net.jqwik.api.*;

class MessageProcessorTest_TaskD {

    /**
     * Task D - Automate the tests using jqwik
     */

    void printMessage(Message m) {
        /*
        Helper to look at some of the generated messages
        */
        System.out.println(m.getSender() + " to " + m.getReceiver() + " : " + m.getContent());
    }

    @Provide
    Arbitrary<Message> messages() {

        /*
          Allow for empty strings
          Maximum length 100, not relevant
         */

        // Generate random strings for sender, receiver and content
        Arbitrary<String> sender = Arbitraries.strings().alpha().ofMinLength(0).ofMaxLength(100);
        Arbitrary<String> receiver = Arbitraries.strings().alpha().ofMinLength(0).ofMaxLength(100);
        Arbitrary<String> content = Arbitraries.strings().alpha().ofMinLength(0).ofMaxLength(100);

        return Combinators.combine(sender, receiver, content).as(Message::new);
    }

    @Property(tries = 20)
    void test_ArgumentCaptor(
            @ForAll("messages") Message m
    ) {
        /**
         * Test the ArgumentCaptor variant with randomly generated messages
         */

//         DEBUG, check the generated message
//        printMessage(m);

        // Create a mock instance of MessageService
        MessageService messageService = mock(MessageService.class);

        // Create a real messageProcessor instance, inject the mock
        MessageProcessor messageProcessor = new MessageProcessor(messageService);

        // Create a list of message instances from the provided messages
        List<Message> messages = Arrays.asList(
                m
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
        assertEquals(m.getReceiver(), messageReceiverCaptor.getValue());
        assertEquals(m.getContent(), messageContentCaptor.getValue());
    }

    @Property(tries = 20)
    void test_increasingObservability(
            @ForAll("messages") Message m
    ) {
        /**
         * Test the increased observability variant with randomly generated messages
         */

//         DEBUG, check the generated message
//        printMessage(m);

        // Create a mock instance of MessageService
        MessageService messageService = mock(MessageService.class);

        // Create a real messageProcessor instance, inject the mock
        MessageProcessor messageProcessor = new MessageProcessor(messageService);

        // Create a list of message instances
        List<Message> messages = Arrays.asList(
                m
        );

        // Process the messages
        messageProcessor.processMessages(messages);

        // Verify the status of the messages we went through the messageProcessor class
        assertTrue(messageProcessor.messagesOk);
    }

}