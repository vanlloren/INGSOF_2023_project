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


public class TurnView extends TurnViewObservable implements LivingRoomObserver, ShelfObserver, PlayerObserver, GameModelObserver {
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



    public PlayableItemTile[][] getShelfTable(){
    return this.shelfTable;
    }

    public int getPlayersNumber(){
       return this.gameModel.getPlayersInGame().size();
    }

    public String getNickNameCurrentPlayer(){
       return gameModel.getCurrPlayer().getNickname();
    }


    public Integer getPartialPoint() {
        return partialPoint;
    }

//IN OGNUNO DI QUESTO DEVO MANDARE UNA NOTIFICA A TUTTI GLI OBSERVER OSSIA A TUTTA LA LISTA DEGLI CLIENTOBSERVER
    //QUESTA A SUA VOLTA ALLA RICEZIONE DI QUELLA UPDATE AVRA NEL SUO CODICE OVVERRIDE IL COIDCE CON SCRITTO IL TESTO MODIFICATA LA ...
    // E CHIAMO METODO SHOW CHE STAMPA LA NUOVA SITUAZIONE
    @Override
    public void update(Message message) {

    }

    @Override
    public void onUpdateModelListPlayers(ArrayList<Player> playerArrayList) {
    notifyObservers(obs ->{
        obs.UpdateAllClientonModelListPlayers(playerArrayList);
    });
    }

    @Override
    public void onUpdateModelEndGame(boolean endGame)  {
        notifyObservers(obs ->{
            obs.UpdateAllClientOnModelEndGame(endGame);
        });
    }

    @Override
    public void onUpdateModelPlayersNumber(int playersNumber)  {
        notifyObservers(obs ->{
            obs.UpdateAllClientOnPlayersNumber(playersNumber);
        });
    }

    @Override
    public void onUpdateModelChairOwner(Player player) {

    }

    @Override
    public void onUpdateGameBoard(GameBoard gameBoard) {
        notifyObservers(obs ->{
            obs.UpdateAllClientonModelGameBoard(gameBoard);
        });
    }
    //-------------------------Qua scrivo per le shelf---------------------------------//
    @Override
    public void onUpdateStructureShelf(int x, int y, PlayableItemTile Tile){
       notifyObservers(obs -> {
           obs.UpdatellClientonStructureShelf(x , y , Tile);
       });
    }
}
