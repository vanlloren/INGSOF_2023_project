package Network.message;

public class ReplyPickTileMessage extends Message{

    private final boolean isTileValid ;
    ReplyPickTileMessage(String nickname, boolean isTileValid) {
        super(nickname, MessageEnumeration.REPLY_PICK_TILE);
        this.isTileValid = isTileValid;
    }
    public boolean getIsTileValid(){
        return this.isTileValid;
    }
}
