package Observer;

import Network.message.Message;
import server.Model.GameBoard;
import server.Model.Player;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerObserver  {
    void update(Message message);

    void onUpdateModelListPlayers(ArrayList<Player> playerArrayList);

    void onUpdateModelEndGame(boolean endGame) throws RemoteException;

    void onUpdateModelPlayersNumber(int playersNumber) throws RemoteException;

    void onUpdateModelChairOwner() throws RemoteException;

    void onUpdateGameBoard(GameBoard gameBoard) throws RemoteException;
}
