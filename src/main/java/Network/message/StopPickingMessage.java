package Network.message;

public class StopPickingMessage extends Message{
    public StopPickingMessage(String nickname) {
        super(nickname, MessageEnumeration.STOP_PICKING);
    }
}
