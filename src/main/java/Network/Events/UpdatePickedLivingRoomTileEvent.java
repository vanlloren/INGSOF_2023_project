package Network.Events;

public class UpdatePickedLivingRoomTileEvent extends Event{
    private int xPos;
    private int yPos;
    private String currPlayer;

    public UpdatePickedLivingRoomTileEvent(String player, int x, int y) {
        super(EventEnum.UPDATE_PICKED_LIVINGROOM_TILE);
        this.xPos = x;
        this.yPos = y;
        this.currPlayer = player;
    }

    public int getXPos(){
        return this.xPos;
    }

    public int getYPos(){
        return this.yPos;
    }

    public String getCurrPlayer(){
        return this.currPlayer;
    }
}
