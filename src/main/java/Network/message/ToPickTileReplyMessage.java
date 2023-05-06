package Network.message;

public class ToPickTileReplyMessage extends Message{
    private int xPos;
    private int yPos;
    public ToPickTileReplyMessage(String nickname, int x, int y) {
        super(nickname, MessageEnumeration.TO_PICK_TILE_REPLY);
        xPos = x;
        yPos = y;
    }

    public int getXPos(){
        return this.xPos;
    }
    public int getYPos(){
        return this.yPos;
    }
}
