package zest;

import java.util.Objects;

public class  Message {
    private String sender;
    private String receiver;
    private String content;

    public Message(String sender, String receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }

    // Getters
    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Message other = (Message) obj;
        // Check equality of fields
        return this.sender.equals(other.sender) &&
                this.receiver.equals(other.receiver) &&
                this.content.equals(other.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.sender, this.receiver, this.content);
    }
}
