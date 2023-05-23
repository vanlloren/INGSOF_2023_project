package Network.message;

import server.Model.PlayableItemTile;
import server.Model.Player;
import server.enumerations.PickTileResponse;

public class TileReplyMessage extends Message{
    private PlayableItemTile tile;
    private PickTileResponse tileResponse;

    public TileReplyMessage(PlayableItemTile tile, PickTileResponse tileResponse ) {
        super(null, MessageEnumeration.TILE_REPLY);
        this.tile=tile;
        this.tileResponse=tileResponse;
    }

    public PlayableItemTile getTile(){
        return tile;
    }

    public PickTileResponse isTileAccepted(){
        return tileResponse;
    }
}
