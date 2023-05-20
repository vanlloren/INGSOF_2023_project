package Observer;


import Util.CommonGoalType;
import client.view.TurnView;
import server.Model.GameBoard;
import server.Model.Player;

import java.rmi.RemoteException;

public interface GameModelObserver {

    void onUpdateModelListPlayers(TurnView turnView, Player playerArrayList) throws RemoteException;

    void onUpdateModelEndGame(TurnView turnView, Player currPlayer, boolean endGame) throws RemoteException;

    void onUpdateModelPlayersNumber(TurnView turnView, int playersNumber) throws RemoteException;

    void onUpdateModelChairOwner(TurnView turnView, Player player) throws RemoteException;

    void onUpdateGameBoard(TurnView turnView, GameBoard gameBoard) throws RemoteException;

    void onUpdateModelGameHasStarted(TurnView turnView) throws RemoteException;

    void onUpdateModelCurrentPlayer(TurnView turnView, Player currPlayer) throws RemoteException;

    void onUpdateModelMatchWinner(TurnView turnView, String player) throws RemoteException;

    void onUpdateModelGameHasEnd(TurnView turnView) throws RemoteException;

    void onUpdateModelChat(TurnView turnView, String nickname, String chat) throws RemoteException;

    void OnUpdateModelCommonGoal(TurnView turnView, CommonGoalType commonGoalType1, CommonGoalType commonGoalType2) throws RemoteException;
}
