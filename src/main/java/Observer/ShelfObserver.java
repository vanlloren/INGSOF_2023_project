package Observer;

import client.view.TurnView;
import server.Model.PlayableItemTile;

import java.rmi.RemoteException;

public interface ShelfObserver {

    //-------------------------Qua scrivo per le shelf---------------------------------//
    void onUpdatePuttedTileIntoShelf(TurnView turnView, int x, int y, PlayableItemTile tile) throws RemoteException;
}
