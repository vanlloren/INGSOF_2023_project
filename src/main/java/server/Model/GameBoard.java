package server.Model;

import Util.Colour;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class GameBoard implements Serializable {
    @Serial
    private static final long serialVersionUID = 2987798677668898344L;

    private final GameModel gameModel;
    private transient ItemBag bag = new ItemBag();
    private LivingRoom livingRoom;
    private PlayableItemTile nextInGameTile;//é la tessera "da mettere in gioco" ovvero quella che dalla bag sta venendo piazzata sulla plancia

    private ArrayList<PlayableItemTile> toPlayerTiles = new ArrayList<>();//sono le tessere che il giocatore ha raccolto dalla plancia, forse si può fare meglio

    //servono per regolare correttamente le adiacenze
    private int firstX;
    private int firstY;

    //MODEL
    public GameBoard(GameModel gameModel){
        this.gameModel = gameModel;
    }
    public void setItemBag(){ //genera la ItemBag a ogni inizio partita chiamato dal controller
        ItemBag helperBag = new ItemBag();
        helperBag.putTiles();
        this.bag = helperBag;
    }

    public ItemBag getItemBag(){
        return this.bag;
    }

    //MODEL
    public void setLivingRoom(int playerNum){ // il parametro viene passato dal controller
        LivingRoom helperLivingRoom = new LivingRoom(gameModel);
        helperLivingRoom.createGameTable(playerNum);
        this.livingRoom = helperLivingRoom;
        firstFilling(this.livingRoom);

        //qui va messa notify
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


