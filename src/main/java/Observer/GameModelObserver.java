package Observer;

import Network.message.Message;
import server.Model.GameBoard;
import server.Model.Player;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface GameModelObserver {
    void update(Message message);

    void onUpdateModelListPlayers(ArrayList<Player> playerArrayList) ;

    void onUpdateModelEndGame(boolean endGame) ;

    void onUpdateModelPlayersNumber(int playersNumber) ;

    void onUpdateModelChairOwner(Player player);

    void onUpdateGameBoard(GameBoard gameBoard);
}
