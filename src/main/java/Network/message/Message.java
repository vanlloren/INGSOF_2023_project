package Network.message;

import java.io.Serializable;

/**
 * Abstract message class which must be extended by each message type.
 * Both server and clients will communicate using this generic type of message.
 * This avoids the usage of the "instance of" primitive.
 */
public abstract class Message implements Serializable {

    private static final long serialVersionUID = 2L;

    private final String nickname;
    private final MessageEnumeration messageEnum;

    Message(String nickname, MessageEnumeration messageEnum) {
        this.nickname = nickname;
        this.messageEnum = messageEnum;
    }

    public String getNickname() {
        return nickname;
    }

    public MessageEnumeration getMessageEnumeration() {
        return messageEnum;
    }

    @Override
    public String toString() {
        return "Message{" +
                "nickname=" + nickname +
                ", messageType=" + messageEnum +
                '}';
    }

}
