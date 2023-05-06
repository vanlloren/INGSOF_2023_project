package client.view;

import server.Model.*;

import java.awt.*;
//metodi setter basta chiamarli a inizio partita perche tanto ho stessi riferimenti che sono sempre aggiornati
import java.util.ArrayList;


public class GameModelView {
   private LivingRoom livingRoom;
   private ArrayList<Player> playerInGame;
   private PlayableItemTile[][] shelfTable;
   private Integer addingPoint;
   private Integer partialPoint;

   GameModelView(){
       livingRoom = new LivingRoom();
       playerInGame = new ArrayList<Player>();
      // shelfTable = new PlayableItemTile[][];


   }

   public void setLivingRoom(LivingRoom livingRoom){
       this.livingRoom = livingRoom;

   }

    public LivingRoom getLivingRoom() {
        return livingRoom;
    }

    public ArrayList<Player> getPlayerInGame() {
        return playerInGame;
    }

    public void setPlayerInGame(ArrayList<Player> playerInGame) {
        this.playerInGame = playerInGame;
    }

    public PlayableItemTile[][] getShelfTable(){
    return this.shelfTable;
    }

    public void setShelfTable(PlayableItemTile[][] shelfTable){
       this.shelfTable = shelfTable;

    }

    public Integer getAddingPoint() {
        return addingPoint;
    }

    public void setAddingPoint(Integer addingPoint) {
        this.addingPoint = addingPoint;
    }

    public Integer getPartialPoint() {
        return partialPoint;
    }

    public void setPartialPoint(Integer partialPoint) {
        this.partialPoint = partialPoint;
    }
}
