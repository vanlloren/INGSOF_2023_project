package server.Model;


import Observer.ShelfObservable;
import client.view.TurnView;
import java.io.Serial;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Vector;
import Util.Colour;

/**
 * This Class represents the {@link Shelf Shelf} of a single {@link Player Player} as described in the game rules.
 * The {@link Shelf Shelf} consists in a bi-dimensional array of {@link PlayableItemTile PlayableItemTiles}
 * which generates a matrix with 6 rows and 5 columns.
 * <i>Have a look at MyShelfie RuleBook for further information</i>
 */
public class Shelf extends ShelfObservable implements Serializable, SimpleShelf {
    private int columnChosen;
    private int pointsAdj = 0;
    private final GameModel gameModel;


    @Serial
    private static final long serialVersionUID = -5591053634616843792L;
    private PlayableItemTile[][] structure = new PlayableItemTile[6][5];

    /**
     * This method creates an instance of {@link Shelf Shelf} and also provides the binding with
     * the actual {@link GameModel GameModel}
     *
     * @param gameModel the {@link GameModel GameModel} to bind
     */
    public Shelf(GameModel gameModel){
        this.gameModel = gameModel;
    }

    /**
     *
     * @return the structure of the {@link Shelf Shelf}
     */
    public PlayableItemTile[][] getStructure(){
        return this.structure;
    }

    /**
     * Sets the structure of the {@link Shelf Shelf} directly without following the {@link Player Player}
     * instructions. Useful for testing procedures.
     *
     * @param structure the expected structure from which the structure of the {@link Shelf Shelf} will
     * be generated
     */
    public void setStructure(PlayableItemTile[][] structure){ //da usare solo nei test!!!!!!!
        this.structure = structure;
    }

    /**
     *
     * @return the total amount of adjacency points scored by a {@link Player Player}.
     */
    public int getPointsAdj() {
        return this.pointsAdj;
    }

    /**
     * Sets the total amount of adjacency points scored by a {@link Player Player}.
     * Adjacency points are awarded to a {@link Player Player} when groups of adjacent
     * {@link PlayableItemTile PlayableItemTiles} with the same {@link Colour Colour} of determined size
     * are present in the {@link Shelf Shelf}.
     *
     * @param pointsAdj the amount of adjacency points to award to a {@link Player Player}
     */
    public void setPointsAdj(int pointsAdj) {
        this.pointsAdj = pointsAdj;
    }

    /**
     * Generates the internal structure of a {@link Shelf Shelf} by placing in all its cells
     * an instance of a {@link PlayableItemTile} with {@link Colour Colour} {@code VOID} and {@code idCode}
     * set to -1
     * @return
     */
    public PlayableItemTile[][] setUp(){
        for (int i= 0;i<6;i++){
            for ( int j=0;j<5;j++){
                this.structure[i][j] = new PlayableItemTile("VOID", -1);
            }
        }
        return this.structure;
    }

    /**
     * Method that inserts a specific {@link PlayableItemTile PlayableItemTile} picked from the
     * {@link LivingRoom LivingRoom} into the {@link Shelf Shelf}
     *
     * @param x the 'x' position in the {@link Shelf Shelf} in which the {@link PlayableItemTile PlayableItemTile} has to be put
     * @param y the 'y' position in the {@link Shelf Shelf} in which the {@link PlayableItemTile PlayableItemTile} has to be put
     * @param Tile the {@link PlayableItemTile PlayableItemTile} to put into the {@link Shelf Shelf}
     */
    public void putTile(int x, int y, PlayableItemTile Tile){
        this.structure[x][y] = Tile;
        notifyObservers(obs-> {
            try {
                obs.onUpdatePuttedTileIntoShelf(new TurnView(gameModel), x,y,Tile);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Method that counts, for every column of the structure of a {@link Shelf Shelf}, the number of cells
     * which contain a {@link PlayableItemTile PlayableItemTile} with {@link Colour Colour} {@code VOID} and
     * {@code idCode} that is equal to -1. Then the method computes the maximum between the results,
     * which represents the maximum number of tiles that could be picked in the livingRoom in a single turn.
     *
     * @return the maximum between the values of available cells in each column of the {@link Shelf Shelf's}
     * structure
     */
    public int countMaxVoidTilesShelfColumns(){
        //Useful method to check also
        List<Integer> list = new Vector<>();

        for(int j= 0; j<5; j++){
            int count=0;
            for(int i= 0; i<6; i++){
                if(this.structure[i][j].getIdCode() == -1){
                    count++;
                }
            }
            list.add(count);
        }
        int max = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            int valore = list.get(i);
            if (valore > max) {
                max = valore;
            }
        }
        return max;
    }

    /**
     *
     * @return {@code false} if there is at least one cell in the {@link Shelf Shelf} which <strong>does not
     * contain</strong> a {@link PlayableItemTile PlayableItemTile} with {@link Colour Colour} different
     * from {@code VOID} and {@code idCode} different from -1, {@code true} otherwise
     */
    public boolean isFull() {
        for (int j = 0; j < 5; j++) {
            if (this.structure[0][j].getColour() == Colour.VOID) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @return the column of the structure chosen by the {@link Player CurrentPlayer} when putting the first
     * {@link PlayableItemTile PlayableItemTile} in the {@link Shelf Shelf}
     */
    public  int getColumnChosen(){
        return this.columnChosen;
    }

    /**
     * Sets the attribute {@code columnChosen}, which indicates the column chosen by the
     * {@link Player CurrentPlayer} when putting the first {@link PlayableItemTile PlayableItemTile}
     * in the {@link Shelf Shelf}
     * @param columnChosen
     */
    public void setColumnChosen(int columnChosen){
        this.columnChosen = columnChosen;
    }
}