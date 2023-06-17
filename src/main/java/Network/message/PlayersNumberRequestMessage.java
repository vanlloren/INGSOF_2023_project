package Network.message;

import java.io.Serial;

/**
 * An extension of the {@link Message Message} Class.
 * A {@link PlayersNumberRequestMessage PlayersNumberRequestMessage} is sent whenever the
 * {@link Network.ServerSide.RemoteServerImplementation RemoteServer} receives the first login request
 * from a {@link Network.ClientSide.RemoteClientImplementation RemoteClient}. It is used to ask the
 * first {@link server.Model.Player Player} who joins the lobby to set the {@code playersNumber} of the match
 * <i>Have a look at {@link Message Message} Class for more information.</i>
 */
public class PlayersNumberRequestMessage extends Message{

    @Serial
    private static final long serialVersionUID = -2155556142315548857L;

    /**
     * This method creates an instance of {@link PlayersNumberRequestMessage PlayersNumberRequestMessage}
     *
     * @param nickName a {@link String String} containing the {@code nickname} of the sender
     */
    public PlayersNumberRequestMessage(String nickName) {
        super(nickName, MessageEnumeration.PLAYERNUMBER_REQUEST);
    }

    @Override
    public String toString() {
        return "PlayerNumberRequest{" +
                "nickname=" + getNickname() +
                '}';
    }
}
