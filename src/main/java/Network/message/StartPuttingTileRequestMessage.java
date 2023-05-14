package Network.message;

import server.Model.PlayableItemTile;

import java.util.ArrayList;

public class StartPuttingTileRequestMessage extends Message{
    private ArrayList<PlayableItemTile> tilesArray;

    public StartPuttingTileRequestMessage(ArrayList<PlayableItemTile> tilesArray) {
        super(null, MessageEnumeration.START_PUTTING_TILE_REQUEST);
        this.tilesArray = tilesArray;
    }

    public ArrayList<PlayableItemTile> getTilesArray(){
        return tilesArray;
    }
}
