package Observer;

import Util.PersonalGoalType;
import client.view.TurnView;

import java.rmi.RemoteException;

public interface PersonalGoalObserver {

    void OnUpdateModelPersonalGoal(TurnView turnView, PersonalGoalType personalGoalType, String nickname) throws RemoteException;
}
