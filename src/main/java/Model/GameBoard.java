package Model;

import org.example.ItemBag;
import org.example.LivingRoom;
import org.example.PlayableItemTile;
import org.example.UnavailableTileException;

import java.util.List;


public class GameBoard {
    private ItemBag bag;
    private LivingRoom livingRoom;

    private PlayableItemTile nextInGameTile;//é la tessera "da mettere in gioco" ovvero quella che dalla bag sta venendo piazzata sulla plancia

    //servono per regolare correttamente le adiacenze
    private int firstX;
    private int firstY;

    private List<PlayableItemTile> toPlayerTiles;//sono le tessere che il giocatore ha raccolto dalla plancia, forse si può fare meglio

    public void setItemBag(){ //genera la ItemBag a ogni inizio partita
        ItemBag helperBag = new ItemBag();
        helperBag.putTiles();
        this.bag = helperBag;
    }

    public void setLivingRoom(int playerNum){
        LivingRoom helperLivingRoom = new LivingRoom();
        helperLivingRoom.createGameTable(playerNum);
        this.livingRoom = helperLivingRoom;
    }

    public void fillLivingRoom(){//riempie la LivingRoom di tessere usando getNextInGameTile e putNextInGameTile
        boolean isVoid;

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                isVoid = livingRoom.searchVoid(i,j);
                if(isVoid){
                    getNextInGameTile();
                    livingRoom.fillVoid(i, j, nextInGameTile);
                }
            }
        }
    }

    public void getNextInGameTile(){
        this.nextInGameTile = bag.randPickTile();
    }

    public boolean pickedTilesNum() {//tiene conto del numero d' ItemTiles pescate ogni turno
        return toPlayerTiles.size() < 3;
    }

    //Check sulle tessere adiacenti già fatto, getToPlayerTile deve controllare al massimo
    //per TRE volte che la tessera che l'utente vuole sia disponibile.
    //getToPlayerTile riceverà dal GameBoard (e in principio quindi dal Player) le coordinate
    //della tessera da raccogliere dalla LivingRoom
    public void getToPlayerFirstTile(int x, int y) throws UnavailableTileException { //prende tessera 1 dalla LivingRoom

        boolean isAvailable = livingRoom.isTileAvailable(x, y);
        //questo check si focalizza sulla disponibilità della tessera che l'utente vuole
        if(isAvailable) {
            toPlayerTiles.add(livingRoom.pickTile(x, y));
            livingRoom.updateAdjacentAvailabilityV1(x, y);
            firstX = x;
            firstY = y;
        }else{
            throw new UnavailableTileException();
        }
    }
    public void getToPlayerAnotherTile(int x, int y) throws UnavailableTileException{ //prende tessera 2/3 dalla LivingRoom

        boolean isAvailable = livingRoom.isTileAvailable(x, y);
        //questo check si focalizza sulla disponibilità della tessera che l'utente vuole
        if(isAvailable) {
            toPlayerTiles.add(livingRoom.pickTile(x, y));
            livingRoom.updateAdjacentAvailabilityV2(x, y, this.firstX, this.firstY);
        }else{
            throw new UnavailableTileException();
        }
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



