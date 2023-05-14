package Network.message;

import server.Model.PlayableItemTile;

import java.util.ArrayList;

public class ToKeepPuttingMessage extends Message{
    private final ArrayList<PlayableItemTile> playableItemTiles;

    public ArrayList<PlayableItemTile> getPlayableItemTiles() {
        return this.playableItemTiles;
    }

    public ToKeepPuttingMessage(ArrayList<PlayableItemTile> playableItemTiles) {
        super(null,MessageEnumeration.KEEP_PUTTING_REQUEST);
        this.playableItemTiles = playableItemTiles;
    }
}
