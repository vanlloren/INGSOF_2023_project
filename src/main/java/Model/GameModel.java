package Model;

import org.example.GameBoard;
import org.example.Player;

import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private String chairOwner;
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

    public void setChairOwner(Player) {
        // ipotizzo di determinare/ricevere il nome utente di chi effettua il primo turno di gioco e di
        // inserirlo in chairOwner
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
        points++;
        return true;

    }
    public List<Player> getTurnOrder(){

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









<<<<<<< HEAD








<<<<<<< HEAD



