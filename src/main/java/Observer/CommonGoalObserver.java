package Observer;

import Util.CommonGoalType;
import client.view.TurnView;

import java.rmi.RemoteException;

public interface CommonGoalObserver {
    void OnUpdateModelCommonGoal(TurnView turnView, CommonGoalType commonGoalType) throws RemoteException;

}
