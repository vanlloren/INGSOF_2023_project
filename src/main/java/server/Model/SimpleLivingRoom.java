package server.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * {@link SimpleLivingRoom SimpleLivingRoom} is the immutable copy of {@link LivingRoom LivingRoom} which
 * is provided to the {@link Network.ClientSide.RemoteClientImplementation RemoteClient} through
 * {@link client.view.TurnView TurnView}.
 * It contains only the methods of {@link LivingRoom LivingRoom} that are strictly necessary to the
 * {@link Network.ClientSide.RemoteClientImplementation RemoteClient} to interact with the objects in
 * the {@link GameModel GameModel}.
 * <i>Have a look at MyShelfie RuleBook for further information</i>
 */
public interface SimpleLivingRoom extends Serializable{

    /**
     *
     * @return an {@link ArrayList Arraylist} of all the available {@link PlayableItemTile PlayableItemTiles}
     * in the {@link LivingRoom LivingRoom}
     */
    ArrayList<PlayableItemTile> getAvailableTiles();

    /**
     *
     * @return the structure of the {@code gameTable}
     */
    ItemTile[][] getGameTable();
}
