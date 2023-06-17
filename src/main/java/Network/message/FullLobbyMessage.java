package Network.message;

import java.io.Serial;

public class FullLobbyMessage extends Message{
    @Serial
    private static final long serialVersionUID = 5807932042666507576L;

    public FullLobbyMessage() {
        super(null, MessageEnumeration.FULL_LOBBY);
    }
}
