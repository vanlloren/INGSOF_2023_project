package Util;

import server.Model.PlayableItemTile;

/**
 * This Class is a Factory used to create a single {@link PlayableItemTile PlayableItemTile}
 */
public class PlayableItemTileFactory {

    /**
     * Creates a single {@link server.Model.PlayableItemTile PlayableItemTile} with the specified {@link Colour Colour} and the correct {@code idCode}
     *
     * @param colour the {@link Colour Colour} to assign to the {@link PlayableItemTile PlayableItemTile}
     * @param id the {@code idCode} to assign to the {@link PlayableItemTile PlayableItemTile}
     * @return the {@link PlayableItemTile PlayableItemTile} just created
     */
    public PlayableItemTile createPlayableItemTile(String colour, int id){
        return new PlayableItemTile(colour, id);
    }
}
