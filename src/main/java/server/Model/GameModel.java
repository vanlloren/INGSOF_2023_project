package server.Model;



import java.util.ArrayList;

public class GameModel {
    private Player chairOwner;
    private int playersNumber;
    private String currPlayer;
    private String matchWinner;
    private GameBoard myShelfie;
    private ArrayList<Player> playersInGame = new ArrayList<Player>();
    private Player[] turnOrder;
    private Player[] has;

    public void launchGame_Myshelfie(){
        // va scritta tutta la partita con lo svolgimento//







    }
    public int getPlayersNumber(){
        return this.playersNumber;
    }
    public void setPlayersNumber(){
        this.playersNumber = playersInGame.size();

    }

    public void setChairOwner(Player player) {
      this.chairOwner = player;
    }

    public ArrayList<Player> getPlayersInGame() {
        return playersInGame;
    }
    public void setPlayersInGame(){
        //metodo che inserisce i giocatori quando si collegano va implementato


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
    public ArrayList<Player> getTurnOrder(){

    }


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
