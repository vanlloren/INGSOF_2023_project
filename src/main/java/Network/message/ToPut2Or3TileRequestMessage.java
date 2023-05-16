package Network.message;

import server.Model.PlayableItemTile;
import server.Model.Player;

import java.util.ArrayList;

public class ToPut2Or3TileRequestMessage extends Message{
    private final int xPos;

    private final PlayableItemTile tile;
    private final ArrayList<PlayableItemTile> currentPlayableItemTile;

    public int getxPos() {
        return this.xPos;
    }

    public PlayableItemTile getTile() {
        return this.tile;
    }

    public ArrayList<PlayableItemTile> getCurrentPlayableItemTile() {
        return this.currentPlayableItemTile;
    }

    public ToPut2Or3TileRequestMessage(int xPos, PlayableItemTile tile, ArrayList<PlayableItemTile> currentPlayableItemTile) {
        super(null, MessageEnumeration.TO_PUT_TILE_2_OR_3_REQUEST);
        this.tile = tile;
        this.xPos = xPos;
        this.currentPlayableItemTile = currentPlayableItemTile;
    }
}
