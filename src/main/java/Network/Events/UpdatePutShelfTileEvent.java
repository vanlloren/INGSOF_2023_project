package Network.Events;

import server.Model.PlayableItemTile;

public class UpdatePutShelfTileEvent extends Event{
    private final int xPos;
    private final int yPos;
    private final PlayableItemTile tile;

    public UpdatePutShelfTileEvent(int x, int y, PlayableItemTile tile) {
        super(EventEnum.UPDATE_PUT_SHELF_TILE);
        this.tile = tile;
        this.xPos = x;
        this.yPos = y;
    }

    public int getXPos(){
        return this.xPos;
    }

    public int getYPos(){
        return this.yPos;
    }

    public PlayableItemTile getTile(){
        return this.tile;
    }
}
