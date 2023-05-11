package Network.message;

public class ReplyInsertTileColumnMessage extends Message{
    private final boolean flag;
    private final int x;

    private final int y;
    public ReplyInsertTileColumnMessage(boolean flag, int x, int y) {
        super(MessageEnumeration.REPLY_INSERT_TILE_COLUMN);
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
