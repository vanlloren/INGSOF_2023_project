package Observer;

import Util.CommonGoalType;
import client.view.TurnView;

import java.rmi.RemoteException;

/**
 * This Interface represents the adaptation of the standard {@link java.util.Observer Observer} Interface
 * to the methods and objects in use inside the Classes that needs to be updated
 * following changes in the {@link server.Model.LivingRoom LivingRoom} Class
 */
public interface LivingRoomObserver {

    /**
     * This method allows a {@link server.Model.LivingRoom LivingRoom} to notify the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * that a {@link server.Model.Player Player} has picked a {@link server.Model.PlayableItemTile PlayableItemTile}
     * from the {@link server.Model.LivingRoom LivingRoom}
     *
     * @param turnView the updated version of {@link TurnView TurnView} which is later sent to the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * @param x the 'x' coordinate of the {@link server.Model.PlayableItemTile PlayableItemTile} that
     *          has been picked from the {@link server.Model.LivingRoom LivingRoom}
     * @param y the 'y' coordinate of the {@link server.Model.PlayableItemTile PlayableItemTile} that
     *          has been picked from the {@link server.Model.LivingRoom LivingRoom}
     * @throws RemoteException
     */
    void onUpdatePickedTileFromLivingRoom(TurnView turnView, int x, int y) throws RemoteException;

    /**
     * This method allows a {@link server.Model.LivingRoom LivingRoom} to notify the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * that the availability of the {@link server.Model.PlayableItemTile PlayableItemTiles} in
     * the {@link server.Model.LivingRoom LivingRoom} may have changed
     *
     * @param turnView the updated version of {@link TurnView TurnView} which is later sent to the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * @throws RemoteException
     */
    void onUpdateTilesAvailability(TurnView turnView) throws RemoteException;

    /**
     * This method allows a {@link server.Model.LivingRoom LivingRoom} to notify the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * that the {@link server.Model.LivingRoom LivingRoom} has been refilled with {@link server.Model.PlayableItemTile PlayableItemTiles}
     *
     * @param turnView the updated version of {@link TurnView TurnView} which is later sent to the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * @throws RemoteException
     */
     void onUpdateRefillLivingRoom(TurnView turnView) throws RemoteException;
}
