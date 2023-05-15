package server.Controller;

import Util.Colour;
import client.view.TurnView;
import server.Model.GameBoard;
import server.Model.LivingRoom;
import server.Model.PlayableItemTile;
import server.Model.Player;

import java.util.ArrayList;

public class GameBoardController {
    private GameBoard controlledGameBoard;

    private LivingRoom controlledLivingRoom;

    private int playerNum;
    private GameController gameController;

    private TurnView turnView;


    public GameBoardController(TurnView turnView, GameController gameController){
        this.turnView = turnView;
    }
    public void setPlayerNum(int playerNum){
        this.playerNum = playerNum;
    }

    public GameBoard getControlledGameBoard(){
        return this.controlledGameBoard;
    }

    public LivingRoom getControlledLivingRoom(){
        return this.controlledLivingRoom;
    }

    public void gameBoardInit(){
        this.controlledGameBoard = new GameBoard(this.turnView);
        this.controlledGameBoard.setItemBag();
        this.controlledGameBoard.setLivingRoom(playerNum);
        this.controlledLivingRoom = controlledGameBoard.getLivingRoom();
        this.controlledLivingRoom.updateAvailability();
    }



    public ArrayList<PlayableItemTile> PickManager(int x, int y){

        controlledLivingRoom.updateAvailability();

        if(controlledGameBoard.getPickedTilesNum()==0){
            if(checkTileAvailability(x,y)){
                controlledGameBoard.setToPlayerFirstTile(x,y);
            }else{
                //messaggio/eccezione che indichi che la tessera scelta non è disponibile e ne va scelta un'altra
                gameController.setInvalidTile();
            }
        }else if(controlledGameBoard.getPickedTilesNum()==1){
            if(checkAdjacentAvailability()) {
                if(checkTileAvailability(x,y)) {
                    controlledGameBoard.setToPlayerAnotherTile(x, y);
                }else {
                    //messaggio/eccezione che indichi che la tessera scelta non è disponibile e ne va scelta un'altra
                    gameController.setInvalidTile();
                }
            }else{
                //messaggio/eccezione che indichi che non posso più scegliere altre tessere
                gameController.resetStillGoing();
            }
        }else{
            if(checkAdjacentAvailability()) {
                if(checkTileAvailability(x,y)) {
                    controlledGameBoard.setToPlayerAnotherTile(x, y);
                }else{
                    //messaggio/eccezione che indichi che la tessera scelta non è disponibile e ne va scelta un'altra
                    gameController.setInvalidTile();
                }
            }else{
                gameController.resetStillGoing();
            }
        }

        return controlledGameBoard.getToPlayerTiles();
    }
    //----->LIVING ROOM
    public boolean checkTileAvailability(int x, int y){
        //determina se una tessera sulla LivingRoom è available o no
        //da lanciare prima di ogni getPlayerXTile
        return controlledLivingRoom.getTileAvailability(x, y);
    }

    public boolean checkPickedTilesNum() {//tiene conto del numero d' ItemTiles pescate ogni turno
        return controlledGameBoard.getPickedTilesNum() <3;
    }

    public void livingRoomFiller(){
        //riempie la LivingRoom di tessere usando getNextInGameTile e putNextInGameTile
        boolean isVoid;
        PlayableItemTile helperTile;

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                isVoid = controlledLivingRoom.searchVoid(i,j);
                if(isVoid){
                    helperTile = controlledGameBoard.getNextInGameTile();
                    if(helperTile.getColour() != Colour.VOID){
                        controlledLivingRoom.fillVoid(i, j, helperTile);
                    }
                    //se la borsa è vuota forse serve mandare messaggio che avvisi tutti
                }
            }
        }
    }

    public boolean checkAdjacentAvailability() {//controlla se almeno una delle tessere adiacenti è
        // disponibile

        for(int i =0; i<9; i++){
            for(int j=0; j<9; j++) {
                if (controlledLivingRoom.nullDetection(i, j) != true && controlledLivingRoom.nullTileDetection(i, j) != true){
                    if (controlledLivingRoom.getTileAdjacency(i, j) && controlledLivingRoom.getTileAvailability(i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkIfAdjacentTiles() {//check a inizio round su tessere adiacenti
        //ritorna true se almeno una PlayableItemTile ha una tessera adiacente != null e
        //la cui funzione nullDetection restituisce false
        int adjCounter = 0;

        for(int i=0; i<9 && adjCounter==0; i++){
            for(int j = 0; j < 9 && adjCounter == 0; j++){
                if (controlledLivingRoom.nullDetection(i, j) != true && controlledLivingRoom.nullTileDetection(i, j) != true){
                    //controllo corner cases
                    //1° tessere [0][3] e [0][4]
                    if(i == 0){
                        if (j==3) {
                            if(controlledLivingRoom.nullDetection(1, 3) != true && controlledLivingRoom.nullTileDetection(1, 3) != true){
                                adjCounter++;
                            }
                            if (controlledLivingRoom.nullDetection(0, 4) != true && controlledLivingRoom.nullTileDetection(0, 4) != true){
                                adjCounter++;
                            }
                        } else if (j==4){
                            if (controlledLivingRoom.nullDetection(0, 3) != true && controlledLivingRoom.nullTileDetection(0, 3) != true){
                                adjCounter++;
                            }
                            if (controlledLivingRoom.nullDetection(1, 4) != true && controlledLivingRoom.nullTileDetection(1, 4) != true){
                                adjCounter++;
                            }

                        }
                    }
                    //2° tessere  [4][0] e [5][0]
                    else if(j==0){
                        if(i==4){
                            if (controlledLivingRoom.nullDetection(4, 1) != true && controlledLivingRoom.nullTileDetection(4, 1) != true){
                                adjCounter++;
                            }
                            if (controlledLivingRoom.nullDetection(5, 0) != true && controlledLivingRoom.nullTileDetection(5, 0) != true){
                                adjCounter++;
                            }
                        }
                        if(i==5){
                            if (controlledLivingRoom.nullDetection(4, 0) != true && controlledLivingRoom.nullTileDetection(4, 0) != true){
                                adjCounter++;
                            }
                            if (controlledLivingRoom.nullDetection(5, 1) != true && controlledLivingRoom.nullTileDetection(5, 1) != true){
                                adjCounter++;
                            }
                        }
                    }
                    //3° tessere [8][4] e [8][5]
                    else if(i==8){
                        if(j==4){
                            if (controlledLivingRoom.nullDetection(7,4) != true && controlledLivingRoom.nullTileDetection(7,4) != true){
                                adjCounter++;
                            }
                            if (controlledLivingRoom.nullDetection(8, 5) != true && controlledLivingRoom.nullTileDetection(8, 5) != true){
                                adjCounter++;
                            }
                        }
                        if(j==5){
                            if (controlledLivingRoom.nullDetection(7, 5) != true && controlledLivingRoom.nullTileDetection(7, 5) != true){
                                adjCounter++;
                            }
                            if (controlledLivingRoom.nullDetection(8, 4) != true && controlledLivingRoom.nullTileDetection(8, 4) != true){
                                adjCounter++;
                            }
                        }
                    }
                    //4° tessere [3][8] e [4][8]
                    else if(j==8){
                        if(i==3){
                            if (controlledLivingRoom.nullDetection(4, 8) != true && controlledLivingRoom.nullTileDetection(4, 8) != true){
                                adjCounter++;
                            }
                            if (controlledLivingRoom.nullDetection(3, 7) != true && controlledLivingRoom.nullTileDetection(3, 7) != true){
                                adjCounter++;
                            }
                        }
                        if(i==4){
                            if (controlledLivingRoom.nullDetection(3, 8) != true && controlledLivingRoom.nullTileDetection(3, 8) != true){
                                adjCounter++;
                            }
                            if (controlledLivingRoom.nullDetection(4, 7) != true && controlledLivingRoom.nullTileDetection(4, 7) != true){
                                adjCounter++;
                            }
                        }
                    }
                    //ora guardo il caso generale
                    else{
                        //controllo la tile della riga sopra
                        if(controlledLivingRoom.nullDetection(i-1, j) != true && controlledLivingRoom.nullTileDetection(i-1, j) != true){
                            adjCounter++;
                        }
                        //controllo la tile della riga sotto
                        if (controlledLivingRoom.nullDetection(i+1, j) != true && controlledLivingRoom.nullTileDetection(i+1, j) != true){
                            adjCounter++;
                        }
                        //controllo la tile a sx
                        if (controlledLivingRoom.nullDetection(i, j-1) != true && controlledLivingRoom.nullTileDetection(i, j-1) != true){
                            adjCounter++;
                        }
                        //controllo la tile a dx
                        if (controlledLivingRoom.nullDetection(i, j+1) != true && controlledLivingRoom.nullTileDetection(i, j+1) != true){
                            adjCounter++;
                        }
                    }
                }
            }
        }

        if(adjCounter!=0) {
            return true;
        }
        return false;

    }


    //----->ITEM BAG
    public PlayableItemTile getNextInGameTile(){
        return controlledGameBoard.getNextInGameTile();
    }



    public ArrayList<PlayableItemTile> giveTileToPlayer(){
        return controlledGameBoard.getToPlayerTiles();
        //invoco metodo di playerController che assegna le tessere al player
    }

    public void toPlayerTilesResetter(){
        ArrayList<PlayableItemTile> list = new ArrayList<PlayableItemTile>();
        controlledGameBoard.resetToPlayerTiles(list);
    }

}
