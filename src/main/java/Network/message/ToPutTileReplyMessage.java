package Network.message;

import server.Model.PlayableItemTile;

import java.util.ArrayList;

public class ToPutTileReplyMessage extends Message{

    private final ArrayList<PlayableItemTile> playableItemTile;
    public ToPutTileReplyMessage(ArrayList<PlayableItemTile> playableItemTiles) {
        super(null , MessageEnumeration.TO_PUT_TILE_REPLY_ERROR);
        this.playableItemTile = playableItemTiles;
    }
    public ArrayList<PlayableItemTile> getPlayableItemTile(){
        return this.playableItemTile;
    }
}
