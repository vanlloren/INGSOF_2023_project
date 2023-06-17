package Network.message;

import server.Model.Player;

import java.io.Serial;
import java.io.Serializable;

/**
 * Abstract message class which must be extended by each message type.
 * Both server and clients will communicate using this generic type of message.
 * This avoids the usage of the "instance of" primitive.
 */
public abstract class Message implements Serializable {

    @Serial
    private static final long serialVersionUID = 2L;

    private  String nickname;
    private final MessageEnumeration messageEnum;

    /**
     * This method creates an instance of {@link Message Message}
     *
     * @param nickname a {@link String String} containing the {@code nickname} of the {@link Player Player}
     *                 who sent the {@link Message Message}
     * @param messageEnum the {@link MessageEnumeration MessageEnumeration} specifying the type
     *                    of the {@link Message Message}
     */
    Message(String nickname, MessageEnumeration messageEnum) {
        this.nickname = nickname;
        this.messageEnum = messageEnum;
    }

    /**
     * This method creates an instance of {@link Message Message}
     *
     * @param messageEnum the {@link MessageEnumeration MessageEnumeration} specifying the type
     *                    of the {@link Message Message}
     */
    Message(MessageEnumeration messageEnum){
        this.messageEnum = messageEnum;
    }

    /**
     *
     * @return a {@link String String} containing the {@code nickname} of the sender of the
     * {@link Message Message}
     */
    public String getNickname() {
        return nickname;
    }

    /**
     *
     * @return the {@link MessageEnumeration MessageEnumeration} of the {@link Message Message}
     */
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
