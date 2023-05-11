package Network.ClientSide;

import Network.message.Message;
import server.Model.GameBoard;
import server.Model.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteClientInterface extends Remote {

    void onMessage(Message message) throws RemoteException;

    //void ping() throws RemoteException;

    void disconnect() throws RemoteException;

    void UpdateAllClientOnPickedTileFromLivingRoom(String currPlayer, int x, int y);

    void UpdateAllClientonModelListPlayers(ArrayList<Player> playerArrayList);

    void UpdateAllClientOnModelEndGame(boolean endGame);

    void UpdateAllClientOnPlayersNumber(int playersNumber);

    void UpdateAllClientonModelGameBoard(GameBoard gameBoard);
}
