package Network.message;

import server.Model.PlayableItemTile;

import java.util.ArrayList;

public class ToPutFirstTileReplyMessage extends Message{
    private final ArrayList<PlayableItemTile> currentPlayableItemTile;
    ToPutFirstTileReplyMessage(ArrayList<PlayableItemTile> currentPlayableItemTile) {
        super(null, MessageEnumeration.TO_PUT_FIRST_TILE);
        this.currentPlayableItemTile = currentPlayableItemTile;
    }
    public ArrayList<PlayableItemTile> getCurrentPlayableItemTile() {
        return this.currentPlayableItemTile;
    }


}
