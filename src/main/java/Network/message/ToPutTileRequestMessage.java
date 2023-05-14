package Network.message;

import server.Model.PlayableItemTile;

import java.util.ArrayList;

public class ToPutTileRequestMessage extends Message{
    private ArrayList<PlayableItemTile> tilesInPlayerHand;
    private boolean errorInTheInsertion;
    private String errorType;

    public ArrayList<PlayableItemTile> getTilesInPlayerHand() {
        return this.tilesInPlayerHand;
    }

    public boolean isErrorInTheInsertion() {
        return this.errorInTheInsertion;
    }

    public String getErrorType() {
        return this.errorType;
    }

    public ToPutTileRequestMessage(boolean errorInTheInsertion, String errorType , ArrayList<PlayableItemTile> tilesInPlayerHand) {
        super(null, MessageEnumeration.TO_PUT_TILE_REQUEST);
        this.errorInTheInsertion =errorInTheInsertion;
        this.errorType = errorType;
        this.tilesInPlayerHand = tilesInPlayerHand;
    }
}
