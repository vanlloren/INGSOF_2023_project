package server.Model;

import Observer.LivingRoomObservable;

import Util.*;
import client.view.TurnView;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.function.Consumer;

/**
 * This Class represents the {@link LivingRoom LivingRoom} as described in the Game Rules.
 * The {@link LivingRoom LivingRoom} main component is the {@code gameTable}: a bi-dimensional
 * Array of {@link ItemTile ItemTile} which is represented by a matrix with 9 columns and 9 rows.
 * There are 3 different {@link LivingRoom LivingRoom} patterns depending on the number of {@link Player Players}
 * in the match.
 * The {@link LivingRoom LivingRoom} also contains the 2 {@link CommonGoal CommonGoals} chosen randomly
 * at the beginning of the match.
 *
 * <i>Have a look at MyShelfie RuleBook for further information</i>
 */
public class LivingRoom extends LivingRoomObservable implements Serializable, SimpleLivingRoom {
    private static final long serialVersionUID = 44051L;
    private ItemTile[][] gameTable;
    private CommonGoal commonGoal1;
    private CommonGoal commonGoal2;
    private GameModel gameModel;

    /**
     * This method creates an instance of {@link LivingRoom LivingRoom} also binding it with
     * the actual {@link GameModel GameModel}.
     * The method also creates 2 new instances of {@link CommonGoal CommonGoal}.
     *
     * @param gameModel the {@link GameModel GameModel} to bind
     */
    public LivingRoom(GameModel gameModel){
        this.gameModel=gameModel;

        this.commonGoal1 = new CommonGoal(gameModel);
        this.commonGoal2 = new CommonGoal(gameModel);
    }
    //MODEL

    /**
     * Creates the {@code gameTable} of the {@link LivingRoom LivingRoom} following the different
     * set of rules which are determined by the number of {@link Player Players} in the match
     *
     * @param playerNum the number of {@link Player Players} in the match
     */
    public void createGameTable(int playerNum) { //nell'inserimento di playernum chiamo metodo di gamemodel getnumplayer e lo metto come argomento
        if (playerNum == 2) {
            LivingRoomFactory factory = new TwoLivingRoomFactory();
            this.gameTable = factory.create();
        } else if (playerNum == 3) {
            LivingRoomFactory factory = new ThreeLivingRoomFactory();
            this.gameTable = factory.create();
        } else if (playerNum == 4) {
            LivingRoomFactory factory = new FourLivingRoomFactory();
            this.gameTable = factory.create();
        }

    }

    /**
     *
     * @return an {@link ArrayList Arraylist} of all the available {@link PlayableItemTile PlayableItemTiles}
     * in the {@link LivingRoom LivingRoom}
     */
    public ArrayList<PlayableItemTile> getAvailableTiles(){
        ArrayList<PlayableItemTile> availableTiles = new ArrayList<>();
        for (int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(gameTable[i][j] != null) {
                    if (gameTable[i][j].getAvailability()) {
                        if(gameModel.getMyShelfie().getPickedTilesNum()>0) {
                            if (gameTable[i][j].getAdjacency()) {
                                availableTiles.add((PlayableItemTile) gameTable[i][j]);
                            }
                        }else{
                            availableTiles.add((PlayableItemTile) gameTable[i][j]);
                        }
                    }
                }
            }
        }
        return availableTiles;
    }

    /**
     * Sets the attribute {@code gameTable} manually by simply copying an already
     * built structure. <strong>For testing purposes only.</strong>
     *
     * @param gameTable the already built structure to copy into the {@code gameTable}
     */
    public void setGameTable(ItemTile[][] gameTable){
        //!!ATTENZIONE!!
        // da usare solo nel test di unità della LivingRoom
        this.gameTable = gameTable;
    }

    /**
     *
     * @return the structure of the {@code gameTable}
     */
    public ItemTile[][] getGameTable(){
        return this.gameTable;
    }

    /**
     *
     * @return the {@link CommonGoal CommonGoal1} of the {@link LivingRoom LivingRoom}
     */
    public CommonGoal getCommonGoal1() { //
        return commonGoal1;
    }

    /**
     *
     * @return the {@link CommonGoal CommonGoal2} of the {@link LivingRoom LivingRoom}
     */
    public CommonGoal getCommonGoal2() {
        return commonGoal2;
    }

    /**
     * Checks whether the {@link ItemTile ItemTile} in the selected position of the {@link LivingRoom LivingRoom}
     * is a {@link NullItemTile NullItemTile} or not.
     *
     * @param x the 'x' position in the {@link LivingRoom LivingRoom} of the {@link ItemTile ItemTile} to check
     * @param y the 'y' position in the {@link LivingRoom LivingRoom} of the {@link ItemTile ItemTile} to check
     * @return {@code true} if the {@link ItemTile ItemTile} is a {@link NullItemTile NullItemTile},
     * {@code false} otherwise
     */
    public boolean nullTileDetection(int x, int y){
        if (gameTable[x][y]==null) {
            return false;
        }
        return gameTable[x][y].nullDetection();
    }

    /**
     * This method is used to verify if a cell into the {@link LivingRoom LivingRoom} contains a {@link ItemTile ItemTile}
     * or not
     *
     * @param x the 'x' position in the {@link LivingRoom LivingRoom} to check
     * @param y the 'y' position in the {@link LivingRoom LivingRoom} to check
     * @return {@code true} if the cell is empty, {@code false} otherwise
     */
    public boolean nullDetection(int x, int y){
        if (gameTable[x][y] == null){
            return true;
        }
        return false;
    }

    /**
     * Checks whether the {@link ItemTile ItemTile} in the selected position of the {@link LivingRoom LivingRoom}
     * is available or not.
     *
     * @param x the 'x' position in the {@link LivingRoom LivingRoom} of the {@link ItemTile ItemTile} to check
     * @param y the 'y' position in the {@link LivingRoom LivingRoom} of the {@link ItemTile ItemTile} to check
     * @return {@code true} if the {@link ItemTile ItemTile} is available,
     * {@code false} otherwise
     */
    public boolean getTileAvailability(int x, int y){
        if(gameTable[x][y]==null){
            return false;
        }
        return gameTable[x][y].getAvailability();
    }

    /**
     * Checks whether the {@link ItemTile ItemTile} in the selected position of the {@link LivingRoom LivingRoom}
     * is adjacent to a previously picked {@link PlayableItemTile PlayableItemTile} or not.
     *
     * @param x the 'x' position in the {@link LivingRoom LivingRoom} of the {@link ItemTile ItemTile} to check
     * @param y the 'y' position in the {@link LivingRoom LivingRoom} of the {@link ItemTile ItemTile} to check
     * @return {@code true} if the {@link ItemTile ItemTile} is adjacent,
     * {@code false} otherwise
     */
    public boolean getTileAdjacency(int x, int y){
        if (gameTable[x][y]==null) {
            return false;
        }
        return gameTable[x][y].getAdjacency();
    }

    //MODEL

    /**
     * This method allows the {@link LivingRoom LivingRoom} to pass one of its {@link PlayableItemTile PlayableItemTile}
     * to the {@link GameBoard GameBoard}
     *
     * @param x the 'x' position in the {@link LivingRoom LivingRoom} of the {@link PlayableItemTile PlayableItemTile} to pick
     * @param y the 'y' position in the {@link LivingRoom LivingRoom} of the {@link PlayableItemTile PlayableItemTile} to pick
     * @return the {@link PlayableItemTile PlayableItemTile} in the selected position in the {@link LivingRoom LivingRoom}
     */
    public PlayableItemTile pickTile(int x, int y) {
        PlayableItemTile helperTile;
        helperTile = (PlayableItemTile) gameTable[x][y];
        gameTable[x][y] = null;
        notifyObservers(obs -> {
            try {
                obs.onUpdatePickedTileFromLivingRoom(new TurnView(gameModel), x, y);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
        return helperTile;
    }

    //MODEL

    /**
     * This method checks if a cell inside the {@code gameTable} of the {@link LivingRoom LivingRoom} is empty
     *
     * @param x the 'x' position in the {@link LivingRoom LivingRoom} of the {@link ItemTile ItemTile} to check
     * @param y the 'y' position in the {@link LivingRoom LivingRoom} of the {@link ItemTile ItemTile} to check
     * @return {@code true} if the selected cell is empty, {@code false} otherwise
     */
    public boolean searchVoid(int x, int y) {
        if(gameTable[x][y] == null) {
            return true;
        }else {
            return false;
        }
    }

    //MODEL
    /**
     * This method fills a blank space inside the {@code gameTable} of the {@link LivingRoom LivingRoom}
     *
     * @param x the 'x' position in the {@link LivingRoom LivingRoom} of the {@link ItemTile ItemTile} to check
     * @param y the 'y' position in the {@link LivingRoom LivingRoom} of the {@link ItemTile ItemTile} to check
     * @param tile the {@link PlayableItemTile PlayableItemTile} to put into the empty cell
     */
    public void fillVoid(int x, int y, PlayableItemTile tile) {
        tile.setPosition(x,y);
        gameTable[x][y] = tile;
    }

    //MODEL

    /**
     * This method decides for each {@link ItemTile ItemTile} in the {@link LivingRoom LivingRoom}
     * if it is available or not during the turn of the {@link Player CurrentPlayer}.
     */
    public void updateAvailability() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!nullTileDetection(i,j) && gameTable[i][j] != null) {
                    gameTable[i][j].makeUnavailable();
                }
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!nullTileDetection(i,j) && gameTable[i][j] != null) {
                    //metto available tutti i corner cases
                    //1° tessere [0][3] e [0][4]
                    if (i == 0) {
                        if (j == 3) {
                            gameTable[i][j].makeAvailable();
                        }else if (j == 4) {
                            gameTable[i][j].makeAvailable();
                        }
                    }
                    //2° tessere  [4][0] e [5][0]
                    else if (j == 0) {
                        if (i == 4) {
                            gameTable[i][j].makeAvailable();
                        }
                        if (i == 5) {
                            gameTable[i][j].makeAvailable();
                        }
                    }
                    //3° tessere [8][4] e [8][5]
                    else if (i == 8) {
                        if (j == 4) {
                            gameTable[i][j].makeAvailable();
                        }
                        if (j == 5) {
                            gameTable[i][j].makeAvailable();
                        }
                    }
                    //4° tessere [3][8] e [4][8]
                    else if (j == 8) {
                        if (i == 3) {
                            gameTable[i][j].makeAvailable();
                        }
                        if (i == 4) {
                            gameTable[i][j].makeAvailable();
                        }
                    }
                    //guardo il caso generale
                    else{
                        //per ogni casella controllo se su almeno uno dei 4 lati c'è una NullItemTile
                        //oppure null
                        //controllo la tile della riga sopra
                        if(gameTable[i-1][j] == null || nullTileDetection(i-1,j)){
                            gameTable[i][j].makeAvailable();
                        }
                        //controllo la tile della riga sotto
                        if (gameTable[i+1][j] == null || nullTileDetection(i+1,j)){
                            gameTable[i][j].makeAvailable();
                        }
                        //controllo la tile a sx
                        if (gameTable[i][j-1] == null || nullTileDetection(i,j-1)){
                            gameTable[i][j].makeAvailable();
                        }
                        //controllo la tile a dx
                        if (gameTable[i][j+1] == null || nullTileDetection(i,j+1)){
                            gameTable[i][j].makeAvailable();
                        }
                    }
                }
            }
        }

        notifyObservers(obs -> {
            try {
                obs.onUpdateTilesAvailability(new TurnView(gameModel));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
    }

    //MODEL
    /**
     * This method is used after the {@link Player Player} has picked the <strong>first</strong> {@link PlayableItemTile PlayableItemTile}
     * from the {@link LivingRoom LivingRoom} during the current turn.
     * The method decides, for each {@link ItemTile ItemTile} in the {@link LivingRoom LivingRoom},
     * whether it is adjacent or not to the previously picked {@link PlayableItemTile PlayableItemTile}.
     * If all the checks are successful, then the {@link ItemTile ItemTile} is made adjacent.
     */
    public void updateAdjacentAvailabilityV1(int x, int y) {
        //Setta la variabile adjacency delle ItemTiles adiacenti


        //adiacenza sopra
        if(x-1>=0){
            if(gameTable[x-1][y] != null && !getTileAdjacency(x-1,y)&& !nullTileDetection(x-1,y)) {
                gameTable[x - 1][y].setAdjacency();
            }
        }
        //adiacenza sotto
        if(x+1<9){
            if(gameTable[x+1][y] != null && !getTileAdjacency(x+1,y)&& !nullTileDetection(x+1,y)) {
                gameTable[x + 1][y].setAdjacency();
            }
        }
        //adiacenza a sx
        if(y-1>=0){
            if(gameTable[x][y-1] != null && !getTileAdjacency(x,y-1)&& !nullTileDetection(x,y-1)) {
                gameTable[x][y - 1].setAdjacency();
            }
        }
        //adiacenza a dx
        if(y+1<9) {
            if (gameTable[x][y + 1] != null && !getTileAdjacency(x, y + 1) && !nullTileDetection(x, y + 1)) {
                gameTable[x][y + 1].setAdjacency();
            }
        }

        notifyObservers(obs -> {
            try {
                obs.onUpdateTilesAvailability(new TurnView(gameModel));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
    }

    //MODEL

    /**
     * Sets the variable {@code adjacency} to {@code false} for every {@link ItemTile ItemTile}
     * in the {@link LivingRoom LivingRoom}
     */
    public void resetAdjacentAvailability(){
        for(int i=0; i<9; i++){
            for(int j=0; j<9 ; j++){
                if(gameTable[i][j] != null && !nullTileDetection(i,j))
                    gameTable[i][j].resetAdjacency();
            }
        }

        notifyObservers(obs -> {
            try {
                obs.onUpdateTilesAvailability(new TurnView(gameModel));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
    }

    //MODEL
    /**
     * This method is used after the {@link Player Player} has picked the <strong>second</strong> {@link PlayableItemTile PlayableItemTile}
     * from the {@link LivingRoom LivingRoom} during the current turn.
     * The method decides, for each {@link ItemTile ItemTile} in the {@link LivingRoom LivingRoom},
     * whether it is adjacent or not to one of the previously picked {@link PlayableItemTile PlayableItemTiles} and,
     * if it is, also checks if the {@link ItemTile ItemTile} stands in the same row
     * (if the first 2 picked {@link PlayableItemTile PlayableItemTiles} were in the same row of the
     * {@link LivingRoom Livingroom}) or in the same column (if the first 2 picked
     * {@link PlayableItemTile PlayableItemTiles} were in the same column of the {@link LivingRoom Livingroom})
     * of the other 2 picked {@link PlayableItemTile PlayableItemTiles}.
     * If all the checks are successful, then the {@link ItemTile ItemTile} becomes adjacent.
     */
    public void updateAdjacentAvailabilityV2(int x, int y, int firstX, int firstY) {
        //Setta la variabile adjacency delle ItemTiles adiacenti

        //capisco dove mi sono spostato
        if (x == firstX - 1) { //sono andato sopra
            if(x-1>=0) {
                if (gameTable[x - 1][y] != null && !nullTileDetection(x - 1, y)) {
                    gameTable[x - 1][y].setAdjacency();
                }
            }
            if(firstY-1>=0) {
                if (gameTable[firstX][firstY - 1] != null && !nullTileDetection(firstX, firstY - 1)) {
                    gameTable[firstX][firstY - 1].resetAdjacency();
                }
            }
            if(firstY+1<9) {
                if (gameTable[firstX][firstY + 1] != null && !nullTileDetection(firstX, firstY + 1)) {
                    gameTable[firstX][firstY + 1].resetAdjacency();
                }
            }
        } else if (x == firstX + 1) { //sono andato sotto
            if(x+1<9) {
                if (gameTable[x + 1][y] != null && !nullTileDetection(x + 1, y)) {
                    gameTable[x + 1][y].setAdjacency();
                }
            }
            if(firstY-1>=0) {
                if (gameTable[firstX][firstY - 1] != null && !nullTileDetection(firstX, firstY - 1)) {
                    gameTable[firstX][firstY - 1].resetAdjacency();
                }
            }
            if(firstY+1<9) {
                if (gameTable[firstX][firstY + 1] != null && !nullTileDetection(firstX, firstY + 1)) {
                    gameTable[firstX][firstY + 1].resetAdjacency();
                }
            }
        } else if (y == firstY - 1) { //sono andato a sx
            if(y-1>=0) {
                if (gameTable[x][y - 1] != null && !nullTileDetection(x, y - 1)) {
                    gameTable[x][y - 1].setAdjacency();
                }
            }
            if(firstX+1<9) {
                if (gameTable[firstX + 1][firstY] != null && !nullTileDetection(firstX + 1, firstY)) {
                    gameTable[firstX + 1][firstY].resetAdjacency();
                }
            }
            if(firstX-1>=0) {
                if (gameTable[firstX - 1][firstY] != null && !nullTileDetection(firstX - 1, firstY)) {
                    gameTable[firstX - 1][firstY].resetAdjacency();
                }
            }
        } else if (y == firstY + 1) { //sono andato a dx
            if(y+1<9) {
                if (gameTable[x][y + 1] != null && !nullTileDetection(x, y + 1)) {
                    gameTable[x][y + 1].setAdjacency();
                }
            }
            if(firstX-1>=0) {
                if (gameTable[firstX - 1][firstY] != null && !nullTileDetection(firstX - 1, firstY)) {
                    gameTable[firstX - 1][firstY].resetAdjacency();
                }
            }
            if(firstX+1<9) {
                if (gameTable[firstX + 1][firstY] != null && !nullTileDetection(firstX + 1, firstY)) {
                    gameTable[firstX + 1][firstY].resetAdjacency();
                }
            }
        }
        notifyObservers(obs -> {
            try {
                obs.onUpdateTilesAvailability(new TurnView(gameModel));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
    }


}








