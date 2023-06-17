package Network.message;

import java.io.Serial;

public class StopPickingMessage extends Message{
    @Serial
    private static final long serialVersionUID = -168054004053784041L;

    public StopPickingMessage(String nickname) {
        super(nickname, MessageEnumeration.STOP_PICKING);
    }
}
