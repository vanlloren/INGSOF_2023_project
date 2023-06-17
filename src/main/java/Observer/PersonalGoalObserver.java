package Observer;

import Util.PersonalGoalType;
import client.view.TurnView;

import java.rmi.RemoteException;

/**
 * This Interface represents the adaptation of the standard {@link java.util.Observer Observer} Interface
 * to the methods and objects in use inside the Classes that needs to be updated
 * following changes in the {@link server.Model.PersonalGoal PersonalGoal} Class
 */
public interface PersonalGoalObserver {

    /**
     * This method allows a {@link server.Model.PersonalGoal PersonalGoal} to notify the {@link Network.ClientSide.RemoteClientImplementation RemoteClient}
     * of its {@link server.Model.Player Player} that its {@link PersonalGoalType PersonalGoalType} has been modified
     *
     * @param turnView the updated version of {@link TurnView TurnView} which is later sent to the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * @param personalGoalType the {@link PersonalGoalType PersonalGoalType} that has been chosen for the {@link server.Model.PersonalGoal PersonaGoal}
     *                         belonging to the {@link server.Model.Player Player}
     * @param nickname the {@code nickname} of the {@link server.Model.Player Player} whose {@link server.Model.PersonalGoal PersonalGoal}
     *                 has been modified
     * @throws RemoteException exception that underlines problems with the RMI Connection
     */
    void OnUpdateModelPersonalGoal(TurnView turnView, PersonalGoalType personalGoalType, String nickname) throws RemoteException;
}
