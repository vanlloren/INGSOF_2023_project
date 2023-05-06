package Network.message;

import server.Model.PlayableItemTile;

import java.util.ArrayList;

public class ToPickTileRequestMessage extends Message{

    private ArrayList<PlayableItemTile> availableTiles;
    public ToPickTileRequestMessage(ArrayList<PlayableItemTile> availableTiles) {
        super(null, MessageEnumeration.TO_PICK_TILE_REQUEST);
        this.availableTiles = availableTiles;
    }

    public ArrayList<PlayableItemTile> getAvailableTiles(){
        return this.availableTiles;
    }


}
