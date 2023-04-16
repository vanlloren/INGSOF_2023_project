package server.Model;

import Util.Colour;

import java.util.ArrayList;

public class GameBoard {
    private ItemBag bag = new ItemBag();
    private LivingRoom livingRoom = new LivingRoom();
    private PlayableItemTile nextInGameTile;//é la tessera "da mettere in gioco" ovvero quella che dalla bag sta venendo piazzata sulla plancia

    //servono per regolare correttamente le adiacenze
    private int firstX;
    private int firstY;

    private ArrayList<PlayableItemTile> toPlayerTiles;//sono le tessere che il giocatore ha raccolto dalla plancia, forse si può fare meglio

    //MODEL
    public void setItemBag(){ //genera la ItemBag a ogni inizio partita chiamato dal controller
        ItemBag helperBag = new ItemBag();
        helperBag.putTiles();
        this.bag = helperBag;
    }

    public ItemBag getBag(){
        return this.bag;
    }

    //MODEL
    public void setLivingRoom(int playerNum){ // il parametro viene passato dal controller
        LivingRoom helperLivingRoom = new LivingRoom();
        helperLivingRoom.createGameTable(playerNum);
        firstFilling(helperLivingRoom);
        this.livingRoom = helperLivingRoom;
    }

    public void firstFilling(LivingRoom livingRoom) {
        boolean isVoid;
        PlayableItemTile helperTile;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                isVoid = livingRoom.searchVoid(i, j);
                if (isVoid) {
                    helperTile = bag.randPickTile();
                    if (helperTile.getColour() != Colour.VOID) {
                        livingRoom.fillVoid(i, j, helperTile);
                    }
                }
            }
        }
    }

    public LivingRoom getLivingRoom(){
        return this.livingRoom;
    }


    //va messo nel controller
    public void setNextInGameTile() {
        this.nextInGameTile = bag.randPickTile();
    }

    public PlayableItemTile getNextInGameTile(){
        setNextInGameTile();
        return nextInGameTile;
    }

    //CONTROLLER
    public int getPickedTilesNum(){return toPlayerTiles.size();}

    //Check sulle tessere adiacenti già fatto, getToPlayerTile deve controllare al massimo
    //per TRE volte che la tessera che l'utente vuole sia disponibile.
    //getToPlayerTile riceverà dal GameBoard (e in principio quindi dal Player) le coordinate
    //della tessera da raccogliere dalla LivingRoom

    //MODEL
    public void setToPlayerFirstTile(int x, int y){ //prende tessera 1 dalla LivingRoom

        toPlayerTiles = new ArrayList<PlayableItemTile>();

        toPlayerTiles.add(livingRoom.pickTile(x, y));
        livingRoom.updateAdjacentAvailabilityV1(x, y);
        firstX = x;
        firstY = y;

    }

    //MODEL
    public void setToPlayerAnotherTile(int x, int y){ //prende tessera 2/3 dalla LivingRoom

        toPlayerTiles.add(livingRoom.pickTile(x, y));
        livingRoom.updateAdjacentAvailabilityV2(x, y, this.firstX, this.firstY);
    }

    public ArrayList<PlayableItemTile> getToPlayerTiles(){
        return toPlayerTiles;
    }
}

//SEQUENZA CORRETTA GAMEBOARD<-->LIVINGROOM:
//-creazione
//-fillLivingRoom (al suo interno avrà multiple chiamate di getNextInGameTile e putNextInGameTile)
//-updateAvailability
//----1° turno----
//-checkIfAdjacentTiles--->fillLivingRoom--->updateAvailability
//-checkTileAvailability
//-getToPlayerFirstTile
//-checkAdjAvailability
//-pickedTilesNum
//-checkTileAvailability
//-fineTurno/getToPlayerAnotherTile
//-checkAdjAvailability
//-pickedTilesNum
//-checkTileAvailability
//-fineTurno/getToPlayerAnotherTile
//-fineTurno
//-updateAvailability
//----turno player succ----
//-checkIfAdjacentTiles--->fillLivingRoom--->updateAvailability
//-checkTileAvailability
//-getToPlayerFirstTile
//-checkAdjAvailability
//-checkTileAvailability
//-fineTurno/getToPlayerAnotherTile
//-checkAdjAvailability
//-checkTileAvailability
//-fineTurno/getToPlayerAnotherTile
//-fineTurno
//-updateAvailability
//----turno player succ----