package Network.message;

import java.io.Serial;

/**
 * An extension of the {@link Message Message} Class.
 * A {@link FullLobbyMessage FullLobbyMessage} is sent whenever the number of {@link server.Model.Player Players}
 * in the lobby has reached the specified value.
 * <i>Have a look at {@link Message Message} Class for more information.</i>
 */
public class FullLobbyMessage extends Message{
    @Serial
    private static final long serialVersionUID = 5807932042666507576L;

    /**
     * This method creates an instance of {@link FullLobbyMessage FullLobbyMessage}.
     */
    public FullLobbyMessage() {
        super(null, MessageEnumeration.FULL_LOBBY);
    }
}
