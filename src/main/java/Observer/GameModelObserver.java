package Observer;

import Network.message.Message;
import server.Model.GameBoard;
import server.Model.Player;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface GameModelObserver {
    void update(Message message);

    void onUpdateModelListPlayers(String Nickname,ArrayList<Player> playerArrayList) throws RemoteException;

    void onUpdateModelEndGame(String Nickname,boolean endGame) throws RemoteException;

    void onUpdateModelPlayersNumber(String Nickname,int playersNumber) throws RemoteException;

    void onUpdateModelChairOwner(String Nickname,Player player) throws RemoteException;

    void onUpdateGameBoard(String nickName,GameBoard gameBoard) throws RemoteException;
}
