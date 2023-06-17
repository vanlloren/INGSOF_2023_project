package Network.message;

import server.Model.PlayableItemTile;
import server.enumerations.PickTileResponse;

import java.io.Serial;

/**
 * An extension of the {@link Message Message} Class.
 * A {@link TileReplyMessage TileReplyMessage} is sent whenever the {@link Network.ServerSide.RemoteServerImplementation RemoteServer}
 * needs to inform a {@link server.Model.Player Player} on whether the {@link PlayableItemTile PlayableItemTile}
 * he decided to pick has been picked correctly or not.
 * <i>Have a look at {@link Message Message} Class for more information.</i>
 */
public class TileReplyMessage extends Message{
    @Serial
    private static final long serialVersionUID = 503277110316194731L;
    private final PlayableItemTile tile;
    private final PickTileResponse tileResponse;

    /**
     * This method creates an instance of {@link TileReplyMessage TileReplyMessage}
     *
     * @param tile the picked {@link PlayableItemTile}, or {@code null} if the pick is unsuccessful
     * @param tileResponse a {@link PickTileResponse PickTileResponse} which indicates if the pick has
     *                     been successful or not
     */
    public TileReplyMessage(PlayableItemTile tile, PickTileResponse tileResponse ) {
        super(null, MessageEnumeration.TILE_REPLY);
        this.tile=tile;
        this.tileResponse=tileResponse;
    }

    /**
     *
     * @return the picked {@link PlayableItemTile PlayableItemTile} if the pick has been successful,
     * {@code null} otherwise
     */
    public PlayableItemTile getTile(){
        return tile;
    }

    /**
     *
     * @return the {@link PickTileResponse PickTileResponse} for the specific pick
     */
    public PickTileResponse isTileAccepted(){
        return tileResponse;
    }
}
