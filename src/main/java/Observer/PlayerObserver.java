package Observer;

import client.view.TurnView;

import java.rmi.RemoteException;

/**
 * This Interface represents the adaptation of the standard {@link java.util.Observer Observer} Interface
 * to the methods and objects in use inside the Classes that needs to be updated
 * following changes in the {@link server.Model.Player Player} Class
 */
 public interface PlayerObserver {

    /**
     * This method allows a {@link server.Model.Player Player} Class to notify the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * when the point total gets modified
     *
     * @param turnView the updated version of {@link TurnView TurnView} which is later sent to the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * @param points the new amount of points scored by the {@link server.Model.Player Player}
     * @throws RemoteException exception that underlines problems with the RMI Connection
     */
    void OnUpdateModelPlayerPoint(TurnView turnView, Integer points) throws RemoteException;

    /**
     * This method allows a {@link server.Model.Player Player} Class to notify the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * when the completion status of the {@link server.Model.CommonGoal CommonGoal2} has been modified
     *
     * @param turnView the updated version of {@link TurnView TurnView} which is later sent to the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * @throws RemoteException exception that underlines problems with the RMI Connection
     */
    void OnUpdateModelStatusCommonGoal2(TurnView turnView) throws RemoteException;

    /**
     * This method allows a {@link server.Model.Player Player} Class to notify the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * when the completion status of the {@link server.Model.CommonGoal CommonGoal1} has been modified
     *
     * @param turnView the updated version of {@link TurnView TurnView} which is later sent to the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * @throws RemoteException exception that underlines problems with the RMI Connection
     */
    void OnUpdateModelStatusCommonGoal1(TurnView turnView) throws RemoteException;

}
