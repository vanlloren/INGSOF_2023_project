package Network.message;

import server.Model.PlayableItemTile;

import java.util.ArrayList;

public class ToPutTile2Or3ReplyMessage extends Message{

    private final ArrayList<PlayableItemTile> playableItemTile;
    public ToPutTile2Or3ReplyMessage(ArrayList<PlayableItemTile> playableItemTiles) {
        super(null , MessageEnumeration.TO_PUT_TILE_2_OR_3_REPLY_ERROR);
        this.playableItemTile = playableItemTiles;
    }
    public ArrayList<PlayableItemTile> getPlayableItemTile(){
        return this.playableItemTile;
    }
}
