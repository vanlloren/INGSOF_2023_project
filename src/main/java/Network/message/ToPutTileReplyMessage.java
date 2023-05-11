package Network.message;

public class ToPutTileReplyMessage extends Message{
    private final boolean flag;
    private final int x;

    private final int y;
    public ToPutTileReplyMessage(boolean flag, int x, int y) {
        super(MessageEnumeration.TO_PUT_TILE_REPLY);
        this.x = x;
        this.y = y;
        this.flag = flag;
    }
    public boolean getFlag(){
        return this.flag;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
