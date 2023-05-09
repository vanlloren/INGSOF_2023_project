package client.view;

import Network.message.Message;
import Observer.GameModelObserver;
import Observer.LivingRoomObserver;
import Observer.PlayerObserver;
import Observer.ShelfObserver;
import server.Model.*;

//metodi setter basta chiamarli a inizio partita perche tanto ho stessi riferimenti che sono sempre aggiornati
import java.rmi.RemoteException;
import java.util.ArrayList;


public class TurnView implements LivingRoomObserver, ShelfObserver, PlayerObserver, GameModelObserver {
   private final GameModel gameModel;
   private GameBoard gameBoard = new GameBoard();
   private ArrayList<Player> playerInGame;
   private PlayableItemTile[][] shelfTable;
   private int addingPoint;
   private int partialPoint;
   private String nicKName;
   private String nickNameCurrentPlayer;

   public TurnView(GameModel gameModel) {  //CAPIRE DOVE FARE QUESTO PASSAGGIO, PROBABILMENTE VA FATTO PROPRIO ALL'INIZIO ALL'AVVIO DEL GAME E PASSARE CON UN SET PER OGNI TUI
       gameModel = new GameModel();
       this.gameModel = gameModel;

   }


   public GameBoard getGameBoard(){
       return this.gameBoard;
   }


    public ArrayList<Player> getPlayerInGame() {
        return gameModel.getPlayersInGame();
    }

    public void setPlayerInGame(ArrayList<Player> playerInGame) {
        this.playerInGame = playerInGame;
    }

    public PlayableItemTile[][] getShelfTable(){
    return this.shelfTable;
    }




    public Integer getPartialPoint() {
        return partialPoint;
    }


    @Override
    public void update(Message message) {

    }

    @Override
    public void onUpdateModelListPlayers(String Nickname, ArrayList<Player> playerArrayList) throws RemoteException {

    }

    @Override
    public void onUpdateModelEndGame(String Nickname, boolean endGame) throws RemoteException {

    }

    @Override
    public void onUpdateModelPlayersNumber(String Nickname, int playersNumber) throws RemoteException {

    }

    @Override
    public void onUpdateModelChairOwner(String Nickname, Player player) throws RemoteException {

    }

    @Override
    public void onUpdateGameBoard(String nickName, GameBoard gameBoard) throws RemoteException {

    }
}
