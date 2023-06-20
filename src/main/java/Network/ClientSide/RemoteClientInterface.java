package Network.ClientSide;

import Network.message.Message;
import Observer.*;
import Util.CommonGoalType;
import Util.PersonalGoalType;
import client.view.TurnView;
import server.Model.PlayableItemTile;
import server.Model.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteClientInterface extends Remote, LivingRoomObserver, ShelfObserver, PlayerObserver, GameModelObserver, PersonalGoalObserver {

    /**
     * This method allows the {@link RemoteClientImplementation RemoteClient} to receive requests through
     * {@link Message Messages} from the {@link Network.ServerSide.RemoteServerImplementation RemoteServer}
     *
     * @param message the {@link Message Message} to send to the {@link RemoteClientImplementation RemoteClient}
     * @throws RemoteException  an {@link Exception Exception} that notifies an error in the connection
     *      * with the {@link Network.ServerSide.RemoteServerImplementation RemoteServer}
     */
    void onMessage(Message message) throws RemoteException;


    void OnUpdateModelCommonGoal(TurnView turnView, CommonGoalType commonGoalType1, CommonGoalType commonGoalType2) throws RemoteException;



    void onUpdateModelListPlayers(TurnView turnView, Player player) throws RemoteException;

    void onUpdateModelEndGame(TurnView turnView,Player player, boolean endGame) throws RemoteException;



    void onUpdateModelChairOwner(TurnView turnView, Player player) throws RemoteException;



    void onUpdateModelChat(TurnView turnView, String nickname, String chat,String receiver) throws RemoteException;



    void onUpdateRefillLivingRoom(TurnView turnView) throws RemoteException;

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

    void onUpdateModelGameHasStarted(TurnView turnView) throws RemoteException;

    void onUpdateTilesAvailability(TurnView turnView) throws RemoteException;

    /**
     * This method allows the {@link RemoteClientImplementation RemoteClient} to answer to a ping request from
     * the {@link Network.ServerSide.RemoteServerImplementation RemoteServer}
     * @throws RemoteException
     */
    void pingReply() throws RemoteException;
}
