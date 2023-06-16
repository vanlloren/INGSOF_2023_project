package Observer;

import client.view.TurnView;
import server.Model.PlayableItemTile;
import server.Model.Shelf;

import java.rmi.RemoteException;

/**
 * This Interface represents the adaptation of the standard {@link java.util.Observer Observer} Interface
 * to the methods and objects in use inside the Classes that needs to be updated
 * following changes in the {@link server.Model.Shelf Shelf} Class
 */
public interface ShelfObserver {

    /**
     * This method allows the {@link Shelf Shelf} to notify the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * when one of its cells is filled with a {@link PlayableItemTile PlayableItemTile}
     *
     * @param turnView the updated version of {@link TurnView TurnView} which is later sent to the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * @param x the 'x' coordinate of the cell in the {@link server.Model.Shelf Shelf} where the {@link PlayableItemTile PlayableItemTile}
     *          has been put
     * @param y the 'y' coordinate of the cell in the {@link Shelf Shelf} where the {@link PlayableItemTile PlayableItemTile}
     *          has been put
     * @param tile the {@link PlayableItemTile PlayableItemTile} that has been put into the {@link Shelf Shelf}
     * @throws RemoteException
     */
    void onUpdatePuttedTileIntoShelf(TurnView turnView, int x, int y, PlayableItemTile tile) throws RemoteException;
}
