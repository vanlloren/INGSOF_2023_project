package Network.message;

import server.Model.PlayableItemTile;

import java.util.ArrayList;

public class ToPutTileRequestMessage extends Message{
    private final int x;
    private final int y;
    private final PlayableItemTile tile;
    private final ArrayList<Integer> columnPosition;
    private final int numOfTiles;
    private final ArrayList<PlayableItemTile> playableItemTiles;
    public ToPutTileRequestMessage(int x, int y , PlayableItemTile tile , ArrayList<Integer> columnPosition , int numOfTiles , ArrayList<PlayableItemTile> playableItemTiles)  {
        super(MessageEnumeration.TO_PUT_TILE_REQUEST);
        this.x = x;
        this.y = y;
        this.tile = tile;
        this.columnPosition = columnPosition;
        this.numOfTiles = numOfTiles;
        this.playableItemTiles = playableItemTiles;
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

    public ArrayList<PlayableItemTile> getPlayableItemTiles(){ return  this.playableItemTiles;}
}
