package Network.Events;

/**
 *An extension of the {@link Event event} Class.
 *A {@link UpdatePickedLivingRoomTileEvent updatePickedLivingRoomTileEvent} is created when
 * we want to incapsulate inside a class the attributes referred to a model change when
 * a picking from the {@link server.Model.LivingRoom livingRoom} has gone well
 */
public class UpdatePickedLivingRoomTileEvent extends Event{
    private final int xPos;
    private final int yPos;
    private final String currPlayer;

    /**
     *This method creates an instance of
     * {@link UpdatePickedLivingRoomTileEvent updatePickedLivingRoomTileEvent}
     * @param player is the name of the user who made the picking
     * @param x is the x-coordinate choosen previously
     * @param y is the y-coordinate choosen previously
     */
    public UpdatePickedLivingRoomTileEvent(String player, int x, int y) {
        super(EventEnum.UPDATE_PICKED_LIVINGROOM_TILE);
        this.xPos = x;
        this.yPos = y;
        this.currPlayer = player;
    }

    /**
     * @return the attribute {@code x} of this instance
     */
    public int getXPos(){
        return this.xPos;
    }
    /**
     * @return the attribute {@code y} of this instance
     */
    public int getYPos(){
        return this.yPos;
    }
    /**
     * @return the attribute {@code currPlayer} of this instance
     */
    public String getCurrPlayer(){
        return this.currPlayer;
    }
}
