package server.Model;

import java.io.Serializable;

/**
 * {@link SimpleShelf SimpleShelf} is the immutable copy of {@link Shelf Shelf} which
 * is provided to the {@link Network.ClientSide.RemoteClientImplementation RemoteClient} through
 * {@link client.view.TurnView TurnView}.
 * It contains only the methods of {@link Shelf Shelf} that are strictly necessary to the
 * {@link Network.ClientSide.RemoteClientImplementation RemoteClient} to interact with the objects in
 * the {@link GameModel GameModel}.
 * <i>Have a look at MyShelfie RuleBook for further information</i>
 */
public interface SimpleShelf extends Serializable {

    /**
     *
     * @return the structure of the {@link Shelf Shelf}
     */
    PlayableItemTile[][] getStructure();
}
