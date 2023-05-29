package Observer;

import Util.CommonGoalType;
import client.view.TurnView;

import java.rmi.RemoteException;

public interface LivingRoomObserver {


    void onUpdatePickedTileFromLivingRoom(TurnView turnView, int x, int y) throws RemoteException;

    void onUpdateTilesAvailability(TurnView turnView) throws RemoteException;

     void onUpdateRefillLivingRoom(TurnView turnView) throws RemoteException;
}
