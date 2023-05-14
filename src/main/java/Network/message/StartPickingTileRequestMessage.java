package Network.message;

public class StartPickingTileRequestMessage extends Message{
    public StartPickingTileRequestMessage(String nickname) {
        super(nickname, MessageEnumeration.START_PICKING_TILE_REQUEST);
    }
}
