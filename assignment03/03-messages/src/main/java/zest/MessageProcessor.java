package zest;

import java.util.ArrayList;
import java.util.List;

public class MessageProcessor {

    private final MessageService messageService;

    // Task C - Save inserted arguments to the sendMessage method
    private List<Message> messagesToCheck = new ArrayList<>();
    public boolean messagesOk = false;
    private void verifyMessages(List<Message> messages) {
        // verify that all messages are equal in both lists
        for (Message message : messages) {

//            System.out.println("Processing message with content: " + message.getContent());

            if (!(messagesToCheck.contains(message))) {
                messagesOk = false;
                return;
            }
        }
        // if we went through and all messages were found, we have checked the messages
        messagesOk = true;
    }

    public MessageProcessor(MessageService messageService) {
        this.messageService = messageService;
    }

    public void processMessages(List<Message> messages) {

        // Task C - Reinitialize list
        messagesToCheck = new ArrayList<>();

        for (Message message : messages) {
            messageService.sendMessage(message.getReceiver(), message.getContent());

            // Task C - Save inserted arguments to the sendMessage method
            messagesToCheck.add(new Message(message.getSender(), message.getReceiver(), message.getContent()));
        }

//        System.out.println("Current messages contents saved to check: ");
//        messagesToCheck.forEach((m -> System.out.println(m.getContent())));

        // verify the integrity of the messages we received
        verifyMessages(messages);
        // After verifying, reset the list of saved messages, ready for the next processMessages
        messagesToCheck = new ArrayList<>();
    }
}
