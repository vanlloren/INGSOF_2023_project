package Observer;

import client.view.TurnView;

import java.rmi.RemoteException;

public interface PlayerObserver {
    void OnUpdateModelPlayerPoint(TurnView turnView, Integer points) throws RemoteException;

    void OnUpdateModelStatusCommonGoal2(TurnView turnView) throws RemoteException;

    void OnUpdateModelStatusCommonGoal1(TurnView turnView) throws RemoteException;

}
