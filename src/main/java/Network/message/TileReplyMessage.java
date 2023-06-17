package Network.message;

import server.Model.PlayableItemTile;
import server.enumerations.PickTileResponse;

import java.io.Serial;

public class TileReplyMessage extends Message{
    @Serial
    private static final long serialVersionUID = 503277110316194731L;
    private final PlayableItemTile tile;
    private final PickTileResponse tileResponse;

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
