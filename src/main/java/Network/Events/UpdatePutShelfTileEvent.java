package Network.Events;

import server.Model.PlayableItemTile;

/**
 *An extension of the {@link Event event} Class.
 *A {@link UpdatePutShelfTileEvent updatePutShelfTileEvent} is created when
 * we want to incapsulate inside a class the attributes referred to a model change when
 * an insertion into the {@link server.Model.Shelf personalShelf} has gone well
 */
public class UpdatePutShelfTileEvent extends Event{
    private final int xPos;
    private final int yPos;
    private final PlayableItemTile tile;

    /**
     *This method creates an instance of
     * {@link UpdatePutShelfTileEvent updatePutShelfTileEvent}
     * @param tile is the tile chosen from the ones in the user hand
     * @param x is the x-coordinate chosen previously
     * @param y is the y-coordinate chosen previously
     */
    public UpdatePutShelfTileEvent(int x, int y, PlayableItemTile tile) {
        super(EventEnum.UPDATE_PUT_SHELF_TILE);
        this.tile = tile;
        this.xPos = x;
        this.yPos = y;
    }

    /**
     * @return the attribute {@code xPos} of this instance
     */
    public int getXPos(){
        return this.xPos;
    }

    /**
     * @return the attribute {@code yPos} of this instance
     */
    public int getYPos(){
        return this.yPos;
    }

    /**
     * @return the attribute {@code tile} of this instance
     */
    public PlayableItemTile getTile(){
        return this.tile;
    }
}
