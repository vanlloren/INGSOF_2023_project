package server.Controller;

import Util.Colour;
import server.Model.GameBoard;
import server.Model.LivingRoom;
import server.Model.PlayableItemTile;

import java.util.ArrayList;

/**
 * This Class is the controller for the {@link GameBoard GameBoard} and its components {@link LivingRoom LivingRoom} and {@link server.Model.ItemBag ItemBag}
 */
public class GameBoardController {
    private GameBoard controlledGameBoard;

    private LivingRoom controlledLivingRoom;

    private int playerNum;
    private GameController gameController;


    /**
     * Creates an instance of {@link GameBoardController GameBoardController} binding it with {@link GameController GameController} which is the main controller
     *
     * @param gameController the instance of {@link GameController GameController} to bind
     */
    public GameBoardController(GameController gameController){
        this.gameController=gameController;
    }

    /**
     * Method that sets the number of players in the game
     *
     * @param playerNum a number between 2 and 4
     */
    public void setPlayerNum(int playerNum){
        this.playerNum = playerNum;
    }

    /**
     *
     * @return the controlled {@link GameBoard GameBoard}
     */
    public GameBoard getControlledGameBoard(){
        return this.controlledGameBoard;
    }

    /**
     *
     * @return the controlled {@link LivingRoom LivingRoom}
     */
    public LivingRoom getControlledLivingRoom(){
        return this.controlledLivingRoom;
    }

    /**
     * Begins the initialization of the {@link GameBoard GameBoard}. Creates and binds a new instance of {@link GameBoard GameBoard},
     * creates and fills the {@link server.Model.ItemBag ItemBag}, sets the {@link LivingRoom LivingRoom} also invoking the
     * filling procedure and the first availability update on the {@link LivingRoom LivingRoom}'s tiles.
     */
    public void gameBoardInit(){
        this.controlledGameBoard = new GameBoard(gameController.getGame());
        this.controlledGameBoard.setItemBag();
        this.controlledGameBoard.setLivingRoom(playerNum);
        this.controlledLivingRoom = controlledGameBoard.getLivingRoom();
        this.controlledLivingRoom.updateAvailability();
    }


    /**
     * Launches and controls the steps of the procedure that allows a {@link server.Model.Player Player} to pick
     * a {@link server.Model.PlayableItemTile PlayableItemTile} from the {@link LivingRoom LivingRoom}
     *
     * @param x the "x" position of the {@link server.Model.PlayableItemTile PlayableItemTile} to pick from the {@link LivingRoom LivingRoom}
     * @param y the "y" position of the {@link server.Model.PlayableItemTile PlayableItemTile} to pick from the {@link LivingRoom LivingRoom}
     * @return the chosen {@link server.Model.PlayableItemTile PlayableItemTile} if the procedure is correct, otherwise <strong>null</strong>.
     *     The procedure is <strong>not correct</strong> when:
     *     (1) the chosen {@link server.Model.PlayableItemTile PlayableItemTile} is <strong>not available</strong>
     *     (2) the chosen {@link server.Model.PlayableItemTile PlayableItemTile} is <strong>available but not adjacent</strong> to a {@link server.Model.PlayableItemTile PlayableItemTile}
     *     picked during the same turn by the same {@link server.Model.Player Player}
     *     (3) there are no more {@link server.Model.PlayableItemTile PlayableItemTiles} adjacent to a {@link server.Model.PlayableItemTile PlayableItemTile}
     *     picked during the same turn by the same {@link server.Model.Player Player}
     */
    public PlayableItemTile PickManager(int x, int y){

        if(controlledGameBoard.getPickedTilesNum()==0){
            if(checkTileAvailability(x,y)){
                controlledGameBoard.getLivingRoom().resetAdjacentAvailability();
                controlledGameBoard.setToPlayerFirstTile(x,y);
                return controlledGameBoard.getToPlayerTiles().get(0);
            }else{
                return null;
            }
        }else if(controlledGameBoard.getPickedTilesNum()==1){
            if(checkAdjacentAvailability()) {
                if(checkTileAvailability(x,y) && checkTileAdjacency(x,y)) {
                    controlledGameBoard.setToPlayerAnotherTile(x, y);
                    return controlledGameBoard.getToPlayerTiles().get(1);
                }else {
                    return null;
                }
            }else{
                gameController.setFull();
                return null;
            }
        }else{
            if(checkAdjacentAvailability()) {
                if(checkTileAvailability(x,y) && checkTileAdjacency(x,y)) {
                    controlledGameBoard.setToPlayerAnotherTile(x, y);
                    return controlledGameBoard.getToPlayerTiles().get(2);
                }else{
                    return null;
                }
            }else{
                gameController.setFull();
                return null;
            }
        }

    }
    //----->LIVING ROOM

    /**
     * Launches the procedure to check if an {@link server.Model.ItemTile ItemTile} is available
     *
     * @param x the "x" position of the {@link server.Model.ItemTile ItemTile}
     * @param y the "y" position of the {@link server.Model.ItemTile ItemTile}
     * @return {@code true} if it is available, {@code false} otherwise
     */
    private boolean checkTileAvailability(int x, int y){
        //determina se una tessera sulla LivingRoom è available o no
        //da lanciare prima di ogni getPlayerXTile
        return controlledLivingRoom.getTileAvailability(x, y);
    }

    /**
     * Launches the procedure to check if an {@link server.Model.ItemTile ItemTile} is adjacent to a previously picked {@link PlayableItemTile PlayableItemTile}
     *
     * @param x the "x" position of the {@link server.Model.ItemTile ItemTile}
     * @param y the "y" position of the {@link server.Model.ItemTile ItemTile}
     * @return {@code true} if it is available, {@code false} otherwise
     */
    private boolean checkTileAdjacency(int x, int y){
        return controlledLivingRoom.getTileAdjacency(x,y);
    }

    /**
     * Checks the number of {@link PlayableItemTile PlayableItemTiles} picked in the current turn
     *
     * @return {@code true} if the number is smaller than 3, {@code false} otherwise
     */
    public boolean checkPickedTilesNum() {//tiene conto del numero d' ItemTiles pescate ogni turno
        return controlledGameBoard.getPickedTilesNum() <3;
    }

    /**
     * Method that fills the controlled {@link LivingRoom LivingRoom} with {@link PlayableItemTile PlayableItemTiles} picked from the {@link server.Model.ItemBag Itembag}.
     * For every pair (x,y) checks if the corresponding cell in the {@link LivingRoom LivingRoom} is equal to {@code null}. If it is, picks a
     * {@link PlayableItemTile PlayableItemTile} from the {@link server.Model.ItemBag Itembag} and puts it in the empty cell of the {@link LivingRoom LivingRoom}.
     */
    public void livingRoomFiller(){

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

    private boolean checkAdjacentAvailability() {

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

    /**
     * Method that is executed at the beginning of every turn. It checks the presence of at least 2 adjacent {@link PlayableItemTile PlayableItemTiles}
     * in the {@link LivingRoom LivingRoom}
     *
     * @return {@code true} if there is at least one PlayableItemTile with an adjacent {@link server.Model.ItemTile ItemTile} which is <strong>not null</strong>
     * and which is not a {@link server.Model.NullItemTile NullItemTile}, {@code false} otherwise
     */

    public boolean checkIfAdjacentTiles() {//check a inizio round su tessere adiacenti
        //ritorna
        int adjCounter = 0;

        for(int i=0; i<9; i++){
            for(int j = 0; j < 9; j++){
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

}
