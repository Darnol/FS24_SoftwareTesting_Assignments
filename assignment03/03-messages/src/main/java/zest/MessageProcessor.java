package zest;

import java.util.ArrayList;
import java.util.List;

public class MessageProcessor {

    private MessageService messageService;

    // Task C - Save inserted arguments to the sendMessage method
    private List<Message> messagesToCheck;
    private boolean messagesOk = false;
    private void verifyMessages(List<Message> messages) {
        // verify that all messages are equal in both lists
        for (Message message : messages) {
//            TODO
        }
//        return
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

        verifyMessages(messages);

    }
}
