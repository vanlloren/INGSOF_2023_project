package Util;

import server.Model.ItemTile;
import server.Model.LivingRoom;

/**
 * This abstract Class is a Factory used to create the structure made of {@link ItemTile ItemTiles} of a {@link LivingRoom LivingRoom}.
 */
public abstract class LivingRoomFactory {

    /**
     * This method is abstract and generates the {@link LivingRoom LivingRoom} depending on the number of {@link server.Model.Player Players} and the
     * specified patterns in the rules of the game.
     *
     * @return the structure of the new {@link LivingRoom LivingRoom} with the {@link server.Model.NullItemTile NullItemTile} placed in
     * the right positions
     */
    abstract public ItemTile[][] create();
}
