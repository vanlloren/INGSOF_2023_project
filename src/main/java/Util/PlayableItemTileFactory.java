package Util;

import Exceptions.InvalidPlayableItemTileColourException;
import server.Model.PlayableItemTile;

public class PlayableItemTileFactory {
    public PlayableItemTile createPlayableItemTile(String colour, int id) throws InvalidPlayableItemTileColourException {
        if (colour.equals("GREEN") || colour.equals("WHITE") || colour.equals("YELLOW") || colour.equals("BLUE") || colour.equals("CYAN") || colour.equals("PINK")) {
            PlayableItemTile helperTile = new PlayableItemTile(colour);
            return helperTile;
        } else {
            throw new InvalidPlayableItemTileColourException();
        }
    }
}
