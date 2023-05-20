package Network.ClientSide;

import Network.message.Message;
import Observer.*;
import Util.CommonGoalType;
import Util.PersonalGoalType;
import client.view.TurnView;
import server.Model.GameBoard;
import server.Model.PlayableItemTile;
import server.Model.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteClientInterface extends Remote, LivingRoomObserver, ShelfObserver, PlayerObserver, GameModelObserver, PersonalGoalObserver {

    void onMessage(Message message) throws RemoteException;

    //void ping() throws RemoteException;

    void disconnect() throws RemoteException;



    void OnUpdateModelCommonGoal(TurnView turnView, CommonGoalType commonGoalType1, CommonGoalType commonGoalType2) throws RemoteException;



    void onUpdateModelListPlayers(TurnView turnView, Player player) throws RemoteException;

    void onUpdateModelEndGame(TurnView turnView,Player player, boolean endGame) throws RemoteException;



    void onUpdateModelChairOwner(TurnView turnView, Player player) throws RemoteException;





    void onUpdateModelCurrentPlayer(TurnView turnView, Player currPlayer) throws RemoteException;

    void onUpdateModelMatchWinner(TurnView turnView, String player) throws RemoteException;

    void onUpdateModelGameHasEnd(TurnView turnView) throws RemoteException;

    void onUpdatePickedTileFromLivingRoom(TurnView turnView, int x, int y) throws RemoteException;

    void OnUpdateModelPersonalGoal(TurnView turnView, PersonalGoalType personalGoalType, String nickname) throws RemoteException;

    void OnUpdateModelPlayerPoint(TurnView turnView, Integer points) throws RemoteException;

    void OnUpdateModelStatusCommonGoal2(TurnView turnView) throws RemoteException;

    void OnUpdateModelStatusCommonGoal1(TurnView turnView) throws RemoteException;

    void onUpdatePuttedTileIntoShelf(TurnView turnView, int x, int y, PlayableItemTile Tile) throws RemoteException;
    void onUpdateModelPlayersNumber(TurnView turnView, int playersNumber) throws RemoteException;
    void onUpdateGameBoard(TurnView turnView, GameBoard gameBoard) throws RemoteException;
    void onUpdateModelGameHasStarted(TurnView turnView) throws RemoteException;

}
