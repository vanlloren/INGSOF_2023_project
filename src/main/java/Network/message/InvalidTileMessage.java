package Network.message;

public class InvalidTileMessage extends Message{
    public InvalidTileMessage() {
        super(null, MessageEnumeration.INVALID_TILE);
    }
}
