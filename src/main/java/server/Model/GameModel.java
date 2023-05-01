package server.Model;



import java.util.ArrayList;

public class GameModel {
    private Player chairOwner;
    private Integer playersNumber;
    private Player currPlayer;
    private String matchWinner;
    private GameBoard myShelfie = new GameBoard();
    private ArrayList<Player> playersInGame = new ArrayList<Player>();
    private boolean endGame=false;

    public void setEndGame(){
        this.endGame = true;
    }
    public boolean getEndGame(){
        return this.endGame;
    }

    public Player getChairOwner() {
        return chairOwner;
    }

    public Player getCurrPlayer(){
        return this.currPlayer;
    }

    public void setCurrPlayer(Player player){
        this.currPlayer = player;
    }

    public void setMyShelfie(GameBoard gameBoard){
        this.myShelfie = gameBoard;
    }


    public int getPlayersNumber() {
        return this.playersNumber;
    }

    public void setPlayersNumber(int playersNumber){
        this.playersNumber = playersNumber;
    }

    public void setChairOwner(Player player) {
      this.chairOwner = player;
    }

    public ArrayList<Player> getPlayersInGame() {
        return this.playersInGame;
    }

    public void setGameBoard() {
        myShelfie = new GameBoard();
    }
    public GameBoard getMyShelfie(){
        return myShelfie;
    }

    public void nextTurn() {
    }
    public boolean haslaunchTerminate() {
        return true;

    }
    /*public ArrayList<Player> getTurnOrder(){

    }
*/

}


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
