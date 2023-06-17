package Network.message;

import java.io.Serial;

/**
 * An extension of the {@link Message Message} Class.
 * A {@link WriteInChatMessage WriteInChatMessage} is sent whenever a {@link server.Model.Player Player}
 * decides to write a message in the game chat.
 * <i>Have a look at {@link Message Message} Class for more information.</i>
 */
public class WriteInChatMessage extends Message {
    @Serial
    private static final long serialVersionUID = -155371940956878789L;
    private final String Nickname;
    private final String chat;
    private final String receiver;

    /**
     * This method creates an instance of {@link WriteInChatMessage WriteInChatMessage}
     *
     * @param Nickname a {@link String String} containing the {@code nickname} of the sender
     * @param chat a {@link String String} containing the actual chat message
     * @param receiver a {@link String String} containing the {@code nickname} of the receiver, or 'Everyone'
     *                 if the message is meant to be shown on the public chat
     */
    public WriteInChatMessage(String Nickname, String chat,String receiver) {
        super(MessageEnumeration.WRITE_IN_CHAT);
        this.Nickname = Nickname;
        this.chat = chat;
        this.receiver = receiver;
    }

    @Override
    public String getNickname() {
        return Nickname;
    }

    /**
     *
     * @return the {@link String String} containing the chat message
     */
    public String getChat() {
        return chat;
    }

    /**
     *
     * @return a {@link String String} containing the {@code nickname} of the receiver, or
     * 'Everyone' if the message is meant to be sent in the public chat
     */
    public String getReceiver(){
        return receiver;
    }
}
