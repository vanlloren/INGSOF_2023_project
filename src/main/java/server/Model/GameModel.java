package server.Model;
import Observer.Observable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Rappresenta il turn del gioco sasso carta forbice lizard spock
public class GameModel extends Observable implements Serializable {
    private static final long serialVersionUID = 44051L;

    private static GameModel instance;
    private Player chosenChairOwner;
    private Integer chosenPlayersNumber;

    public static final int maximumNumberPlayers = 4;
    //private Player currPlayer;
    //private String matchWinner; possiamo individuarlo attraverso un assaggio logico del giocatore con più punti e poi stabilire
    //Player matchWinner = playerInGame.equals(nickname)
    private final GameBoard myShelfie ;
    private List<Player> playersInGame;
    //private boolean endGame=false; Implementando l'enum GameState è possibile verificare lo stato della partita e quindi dell'ultimo turno di gioco

    private PlayableItemTile pickTile;

    private PlayableItemTile putTile;

    private GameModel(){
        this.myShelfie = new GameBoard();
        this.playersInGame = new ArrayList<>();
    }
    public static  GameModel getInstance(){
        if(instance == null)
            instance = new GameModel();
        return instance;
    }

    public PlayableItemTile getPickTile() {
        return pickTile;
    }

    public void setPickTile(PlayableItemTile pickTile) {
        this.pickTile = pickTile;
    }

    public PlayableItemTile getPutTile() {
        return putTile;
    }

    public void setPutTile(PlayableItemTile putTile) {
        this.putTile = putTile;
        setChangedAndNotifyObservers(EVENT.PUT_TILE);
    }

    public boolean isFinishTurn() {
        return finishTurn;
        setChangedAndNotifyObservers(Event.ENDGAME);
    }

    public void setFinishTurn(boolean finishTurn) {
        this.finishTurn = finishTurn;
    }

    public boolean getEndGame(){
        return endGame;
    }
    public void setEndGame(){
        this.endGame = true;
        setChangedAndNotifyObservers(Event.ENDGAME);
    }

    public Player getChairOwner() {
        return chairOwner;
    }

    public Player getCurrPlayer(){
        return this.currPlayer;
    }

    public void setCurrPlayer(Player player){
        //Servirà a stabilire il next turn: stabilendo e settando il prossimo giocatore
        //passeremo automaticamente al prossimo turno
        this.currPlayer = player;
        setChangedAndNotifyObservers(Event.CURR_PLAYER);
    }

    public void setMyShelfie(GameBoard gameBoard){
        this.myShelfie = gameBoard;
        setChangedAndNotifyObservers(Event.GAMEBOARD);
    }

    public int getPlayersNumber() {
        return this.playersNumber;
    }

    public void setPlayersNumber(int playersNumber){
        this.playersNumber = playersNumber;
        setChangedAndNotifyObservers(Event.PLAYERS_IN_GAME);
    }

    public void setChairOwner(Player player) {
      this.chairOwner = player;
        setChangedAndNotifyObservers(Event.CHAIR_OWNER);
    }

    public ArrayList<Player> getPlayersInGame() {
        return this.playersInGame;
    }

    public void setPlayersInGame(java.util.ArrayList<Player> playersInGame) {
        this.playersInGame = playersInGame;
        setChangedAndNotifyObservers(Event.PLAYERS_IN_GAME);
    }

    public void setmyShelfie(GameBoard myShelfie) {
        this.myShelfie = myShelfie;
        setChangedAndNotifyObservers(Event.GAME_BOARD);
    }
    public GameBoard getMyShelfie(){
        return myShelfie;
    }

    @Override
    public String askServerInfo(int portNum) {
        return null;
    }

    @Override
    public void askPlayersNumber() {

    }
    public void reset() {
        chairOwner = null;
        currPlayer= null;
        playersInGame = null;
        myShelfie = null;
        endGame= false;
    }

   /* public boolean haslaunchTerminate() {
        return true;
    }*/
}

    /*public ArrayList<Player> getTurnOrder(){

    }
*/




//SEQUENZA CORRETTA GAMEBOARD<-->LIVINGROOM:
//-creazione
//-fillLivingRoom (al suo interno avrà multiple chiamate di getNextInGameTile e putNextInGameTile)
//-updateAvailability
//----1° turno----
//-hasAdjacentTiles--->fillLivingRoom--->updateAvailability
//-getToPlayerFirstTile
//-checkAdjAvailability
//-pickedTilesNum
//-fineTurno/getToPlayerAnotherTile
//-checkAdjAvailability
//-pickedTilesNum
//-fineTurno/getToPlayerAnotherTile
//-fineTurno
//-updateAvailability
//----turno player succ----
//-hasAdjacentTiles--->fillLivingRoom--->updateAvailability
//-getToPlayerFirstTile
//-checkAdjAvailability
//-fineTurno/getToPlayerAnotherTile
//-checkAdjAvailability
//-fineTurno/getToPlayerAnotherTile
//-fineTurno
//-updateAvailability
//----turno player succ----
