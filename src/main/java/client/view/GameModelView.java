package client.view;

import server.Model.*;

import java.awt.*;
//metodi setter basta chiamarli a inizio partita perche tanto ho stessi riferimenti che sono sempre aggiornati
import java.util.ArrayList;


public class GameModelView {
   private final GameModel gameModel;
   private GameBoard gameBoard = new GameBoard();
   private ArrayList<Player> playerInGame;
   private PlayableItemTile[][] shelfTable;
   private int addingPoint;
   private int partialPoint;
   private String nicKName;
   private String nickNameCurrentPlayer;

   public GameModelView(GameModel gameModel){  //CAPIRE DOVE FARE QUESTO PASSAGGIO, PROBABILMENTE VA FATTO PROPRIO ALL'INIZIO ALL'AVVIO DEL GAME E PASSARE CON UN SET PER OGNI TUI
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


}
