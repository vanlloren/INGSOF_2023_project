package Observer;


import Util.CommonGoalType;
import client.view.TurnView;
import server.Model.CommonGoal;
import server.Model.GameBoard;
import server.Model.Player;

import java.rmi.RemoteException;

/**
 * This Interface represents the adaptation of the standard {@link java.util.Observer Observer} Interface
 * to the methods and objects in use inside the Classes that needs to be updated
 * following changes in the {@link server.Model.GameModel GameModel} Class
 */
public interface GameModelObserver {

    /**
     * This method allows the {@link server.Model.GameModel GameModel} to notify the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * that a new {@link Player Player} has been added to the match
     *
     * @param turnView the updated version of {@link TurnView TurnView} which is later sent to the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * @param player the {@link Player Player} added to the match
     * @throws RemoteException
     */
    void onUpdateModelListPlayers(TurnView turnView, Player player) throws RemoteException;

    /**
     * This method allows the {@link server.Model.GameModel GameModel} to notify the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * that the game has ended
     *
     * @param turnView the updated version of {@link TurnView TurnView} which is later sent to the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * @param currPlayer the {@link Player Player} which is the {@code currPlayer} in the match
     * @param endGame a {@link Boolean Boolean} signaling whether the game has ended or not
     * @throws RemoteException
     */
    void onUpdateModelEndGame(TurnView turnView, Player currPlayer, boolean endGame) throws RemoteException;

    /**
     * This method allows the {@link server.Model.GameModel GameModel} to notify the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * that a {@link Player Player} has set the total number of {@link Player Players} in the match
     *
     * @param turnView the updated version of {@link TurnView TurnView} which is later sent to the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * @param playersNumber the number of {@link Player Players} set for this match
     * @throws RemoteException
     */
    void onUpdateModelPlayersNumber(TurnView turnView, int playersNumber) throws RemoteException;

    /**
     * This method allows the {@link server.Model.GameModel GameModel} to notify the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * that the {@code chairOwner} for this game has been set
     *
     * @param turnView the updated version of {@link TurnView TurnView} which is later sent to the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * @param player the {@link Player Player} who has been made {@code chairOwner} for this match
     * @throws RemoteException
     */
    void onUpdateModelChairOwner(TurnView turnView, Player player) throws RemoteException;

    /**
     * This method allows the {@link server.Model.GameModel GameModel} to notify the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * that the game has started
     *
     * @param turnView the updated version of {@link TurnView TurnView} which is later sent to the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * @throws RemoteException
     */
    void onUpdateModelGameHasStarted(TurnView turnView) throws RemoteException;

    /**
     * This method allows the {@link server.Model.GameModel GameModel} to notify the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * that a new {@code currPlayer} has been set
     *
     * @param turnView the updated version of {@link TurnView TurnView} which is later sent to the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * @param currPlayer the {@link Player Player} who has become {@code currPlayer}
     * @throws RemoteException
     */
    void onUpdateModelCurrentPlayer(TurnView turnView, Player currPlayer) throws RemoteException;

    /**
     * This method allows the {@link server.Model.GameModel GameModel} to notify the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * that a {@link Player Player} has won the match
     *
     * @param turnView the updated version of {@link TurnView TurnView} which is later sent to the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * @param player the {@code nickname} of the {@link Player Player} that has won the match
     * @throws RemoteException
     */
    void onUpdateModelMatchWinner(TurnView turnView, String player) throws RemoteException;

    /**
     * This method allows the {@link server.Model.GameModel GameModel} to notify the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * that the match has ended
     *
     * @param turnView the updated version of {@link TurnView TurnView} which is later sent to the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * @throws RemoteException
     */
    void onUpdateModelGameHasEnd(TurnView turnView) throws RemoteException;

    /**
     * This method allows the {@link server.Model.GameModel GameModel} to notify the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * that a {@link Player Player} has written a message in the game chat
     *
     * @param turnView the updated version of {@link TurnView TurnView} which is later sent to the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * @param nickname the {@code nickname} of the {@link Player Player} that has sent the message
     * @param chat a {@link String String} that contains the actual message sent in the chat
     * @throws RemoteException
     */
    void onUpdateModelChat(TurnView turnView, String nickname, String chat,String receiver) throws RemoteException;

    /**
     * This method allows the {@link server.Model.GameModel GameModel} to notify the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * that the {@link server.Model.CommonGoal CommonGoals} of this match have been set and which their
     * {@link CommonGoalType CommonGoalTypes} are
     *
     * @param turnView the updated version of {@link TurnView TurnView} which is later sent to the {@link Network.ClientSide.RemoteClientImplementation RemoteClients}
     * @param commonGoalType1 the {@link CommonGoalType CommonGoalType} set for {@link server.Model.CommonGoal CommonGoal1}
     * @param commonGoalType2 the {@link CommonGoalType CommonGoalType} set for {@link CommonGoal CommonGoal2}
     * @throws RemoteException
     */
    void OnUpdateModelCommonGoal(TurnView turnView, CommonGoalType commonGoalType1, CommonGoalType commonGoalType2) throws RemoteException;
}
