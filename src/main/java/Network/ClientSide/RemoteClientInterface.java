package Network.ClientSide;

import Network.Events.Event;
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

public interface RemoteClientInterface extends Remote, LivingRoomObserver, ShelfObserver, PlayerObserver, GameModelObserver, CommonGoalObserver, PersonalGoalObserver {

    void onMessage(Message message) throws RemoteException;

    //void ping() throws RemoteException;

    void disconnect() throws RemoteException;

    void UpdateAllClientOnPickedTileFromLivingRoom(String currPlayer, int x, int y);

    void UpdateAllClientOnModelListPlayers(Player player);

    void UpdateAllClientOnModelEndGame(boolean endGame);

    void UpdateAllClientOnPlayersNumber(int playersNumber);

    void UpdateAllClientOnModelGameBoard(GameBoard gameBoard);

    void UpdateAllClientOnModelPersonalGoal(String Nickname,PersonalGoalType personalGoalType);

    void UpdateAllClientOnModelCommonGoal(CommonGoalType commonGoalType);

    void UpdateAllClientOnModelPlayerPoint(String nickNameCurrentPlayer, Integer points);

    void UpdateAllClientOnModelStatusCommonGoal1(String nickNameCurrentPlayer);

    void UpdateAllClientOnModelStatusCommonGoal2(String nickNameCurrentPlayer);

    void UpdateAllClientOnChairOwner(Player player);

    void UpdateAllClientOnModelGameHasStarted();

    void onUpdateAllClientOnCurrentPlayer(Player currPlayer);

    void UpdateAllClientOnStructureShelf(int x, int y, PlayableItemTile Tile);

    void onModelModify(TurnView turnView, Event message);


    void UpdateAllClientOnNewMessageChat(String string, String chatMessage);

    void OnUpdateModelCommonGoal(TurnView turnView, CommonGoalType commonGoalType);



    void onUpdateModelListPlayers(TurnView turnView, Player player);

    void onUpdateModelEndGame(TurnView turnView, boolean endGame);



    void onUpdateModelChairOwner(TurnView turnView, Player player);





    void onUpdateModelCurrentPlayer(TurnView turnView, Player currPlayer);

    void onUpdateModelMatchWinner(TurnView turnView, String player);

    void onUpdateModelGameHasEnd(TurnView turnView);

    void onUpdatePickedTileFromLivingRoom(TurnView turnView, int x, int y);

    void OnUpdateModelPersonalGoal(TurnView turnView, PersonalGoalType personalGoalType, String nickname);

    void OnUpdateModelPlayerPoint(TurnView turnView, Integer points);

    void OnUpdateModelStatusCommonGoal2(TurnView turnView);

    void OnUpdateModelStatusCommonGoal1(TurnView turnView);

    void OnUpdateModelPlayerEndGame(TurnView turnView, Boolean endgame);

    void onUpdatePuttedTileIntoShelf(TurnView turnView, int x, int y, PlayableItemTile Tile);
    void onUpdateModelPlayersNumber(TurnView turnView, int playersNumber) ;
    void onUpdateGameBoard(TurnView turnView, GameBoard gameBoard);
    void onUpdateModelGameHasStarted(TurnView turnView);

}
