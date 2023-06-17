package Network.message;

import java.io.Serial;

/**
 * An extension of the {@link Message Message} Class.
 * A {@link LoginReplyMessage LoginReplyMessage} is sent whenever the {@link Network.ServerSide.RemoteServerImplementation RemoteServer}
 * receives a {@link LoginRequestMessage LoginRequestMessage} from a {@link server.Model.Player Player}
 * with a proposed {@code nickname} which univocity needs to be checked
 * <i>Have a look at {@link Message Message} Class for more information.</i>
 */
public class LoginReplyMessage extends  Message{
    @Serial
    private static final long serialVersionUID = -5L;
    private final boolean nicknameUniqueAccepted;

    /**
     * This method creates an instance of {@link LoginReplyMessage LoginReplyMessage}
     *
     * @param Nickname a {@link String String} containing the {@code nickname} of the sender
     * @param nicknameUniqueAccepted a boolean which indicates whether the proposed {@code nickname}
     *                               is unique or not
     */
    public LoginReplyMessage(String Nickname,boolean nicknameUniqueAccepted) {
        super(Nickname, MessageEnumeration.LOGIN_REPLY);
        this.nicknameUniqueAccepted = nicknameUniqueAccepted;

    }

    /**
     *
     * @return {@code true} if the proposed {@code nickname} is accepted by the {@link Network.ServerSide.RemoteServerImplementation RemoteServer}
     * , {@code false} otherwise
     */
    public boolean isNicknameUniqueAccepted() {
        return nicknameUniqueAccepted;
    }


}




