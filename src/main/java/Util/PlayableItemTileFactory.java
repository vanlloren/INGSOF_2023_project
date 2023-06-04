package Util;

import Exceptions.InvalidPlayableItemTileColourException;
import server.Model.PlayableItemTile;

public class PlayableItemTileFactory {
    public PlayableItemTile createPlayableItemTile(String colour, int id){
        PlayableItemTile helperTile = new PlayableItemTile(colour, id);
        return helperTile;
    }
}
