package Observer;


import server.Model.GameBoard;
import server.Model.Player;

public interface GameModelObserver {

    void onUpdateModelListPlayers(Player playerArrayList) ;

    void onUpdateModelEndGame(boolean endGame) ;

    void onUpdateModelPlayersNumber(int playersNumber) ;

    void onUpdateModelChairOwner(Player player);

    void onUpdateGameBoard(GameBoard gameBoard);

    void onUpdateModelGameHasStarted();

    void onUpdateModelCurrentPlayer(Player currPlayer);

    void onUpdateModelMatchWinner(String player);

    void onUpdateModelGameHasEnd();
}
