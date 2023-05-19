package Observer;


import client.view.TurnView;
import server.Model.GameBoard;
import server.Model.Player;

public interface GameModelObserver {

    void onUpdateModelListPlayers(TurnView turnView, Player playerArrayList) ;

    void onUpdateModelEndGame(TurnView turnView, Player currPlayer, boolean endGame) ;

    void onUpdateModelPlayersNumber(TurnView turnView, int playersNumber) ;

    void onUpdateModelChairOwner(TurnView turnView, Player player);

    void onUpdateGameBoard(TurnView turnView, GameBoard gameBoard);

    void onUpdateModelGameHasStarted(TurnView turnView);

    void onUpdateModelCurrentPlayer(TurnView turnView, Player currPlayer);

    void onUpdateModelMatchWinner(TurnView turnView, String player);

    void onUpdateModelGameHasEnd(TurnView turnView);

    void onUpdateModelChat(TurnView turnView, String nickname, String chat);
}
