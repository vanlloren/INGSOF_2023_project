package Network.message;

import server.Model.PlayableItemTile;

import java.util.ArrayList;

public class ToPutTileReplyMessage extends Message{
    private final int x;
    private final int y;
    private final PlayableItemTile tile;
    private final ArrayList<Integer> columnPosition;
    private final int numOfTiles;
    public ToPutTileReplyMessage(int x, int y , PlayableItemTile tile , ArrayList<Integer> columnPosition , int numOfTiles)  {
        super(MessageEnumeration.TO_PUT_TILE_REPLY);
        this.x = x;
        this.y = y;
        this.tile = tile;
        this.columnPosition = columnPosition;
        this.numOfTiles = numOfTiles;
    }

    public PlayableItemTile getTile() {
        return this.tile;
    }

    public ArrayList<Integer> getColumnPosition() {
        return this.columnPosition;
    }

    public int getNumOfTiles() {
        return this.numOfTiles;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
