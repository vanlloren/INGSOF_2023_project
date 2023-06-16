package server.Model;

import Util.Colour;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * This Class resembles the actual structure of the game with all its components.
 * It provides the methods to create and manipulate the meaningful parts of the Model such as
 * {@link LivingRoom LivingRoom} and {@link ItemBag ItemBag}.
 *
 * <i>Have a look at MyShelfie RuleBook for further information</i>
 */
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

    /**
     * This method provide an instance of {@link GameBoard GameBoard}.
     * It also does the binding between the {@link GameBoard GameBoard} and a specific
     * instance of {@link GameModel GameModel}
     *
     * @param gameModel the instance of {@link GameModel GameModel} to bind
     */
    public GameBoard(GameModel gameModel){
        this.gameModel = gameModel;
    }

    /**
     * This method sets the {@link GameBoard GameBoard's} {@link ItemBag ItemBag} at the beginning of
     * every match. The procedure consists in creating a new {@link ItemBag ItemBag} and
     * filling it with the correct number of {@link PlayableItemTile PlayableItemTiles}
     */
    public void setItemBag(){
        ItemBag helperBag = new ItemBag();
        helperBag.putTiles();
        this.bag = helperBag;
    }

    /**
     *
     * @return the {@link ItemBag ItemBag} associated with the {@link GameBoard GameBoard}
     */
    public ItemBag getItemBag(){
        return this.bag;
    }

    /**
     * This method sets the {@link GameBoard GameBoard's} {@link LivingRoom LivingRoom} at the
     * beginning of every match.
     * The procedure consists in creating a new instance of {@link LivingRoom LivingRoom} and then putting
     * the {@link NullItemTile NullItemTiles} in the correct positions in the
     * {@link Util.LivingRoomFactory LivingRoomFactory} depending on the value of {@code playerNum}.
     *
     * @param playerNum the number of {@link Player Players} in the match
     */
    public void setLivingRoom(int playerNum){ // il parametro viene passato dal controller
        LivingRoom helperLivingRoom = new LivingRoom(gameModel);
        helperLivingRoom.createGameTable(playerNum);
        this.livingRoom = helperLivingRoom;
        firstFilling(this.livingRoom);

    }

    /**
     * This method operates the first ever filling of the {@link LivingRoom LivingRoom}
     * right after it has been created. The procedure searches for blank spaces inside the
     * {@link LivingRoom LivingRoom} and fills them by picking a {@link PlayableItemTile PlayableItemTile}
     * from the {@link ItemBag ItemBag}.
     *
     * @param livingRoom the {@link LivingRoom LivingRoom} to fill
     */
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

    /**
     *
     * @return the {@link LivingRoom LivingRoom} of the match
     */
    public LivingRoom getLivingRoom(){
        return this.livingRoom;
    }

    /**
     * This method sets the next {@link PlayableItemTile PlayableItemTile} to put into
     * the {@link LivingRoom LivingRoom} by picking it from the {@link ItemBag ItemBag}
     */
    public void setNextInGameTile() {
        this.nextInGameTile = bag.randPickTile();
    }

    /**
     *
     * @return the next {@link PlayableItemTile PlayableItemTile} picked from the
     * {@link ItemBag ItemBag} that has to be put into the {@link LivingRoom LivingRoom}
     */
    public PlayableItemTile getNextInGameTile(){
        setNextInGameTile();
        return nextInGameTile;
    }

    /**
     *
     * @return the number of {@link PlayableItemTile PlayableItemTiles} picked by a
     * {@link Player Player} during the current turn
     */
    public int getPickedTilesNum(){return toPlayerTiles.size();}

    /**
     * This method adds the first picked {@link PlayableItemTile PlayableItemTile} to the
     * {@link ArrayList ArrayList} {@code toPlayerTiles}.
     * The selected {@link PlayableItemTile PlayableItemTile} is picked from the {@link LivingRoom LivingRoom}
     * and then put into the {@link ArrayList ArrayList}.
     *
     * @param x the 'x' position on the {@link LivingRoom LivingRoom} of the
     * {@link PlayableItemTile PlayableItemTile} to add
     * @param y the 'y' position on the {@link LivingRoom LivingRoom} of the
     * {@link PlayableItemTile PlayableItemTile} to add
     */
    public void setToPlayerFirstTile(int x, int y){ //prende tessera 1 dalla LivingRoom

        toPlayerTiles = new ArrayList<PlayableItemTile>();

        toPlayerTiles.add(livingRoom.pickTile(x, y));
        livingRoom.updateAdjacentAvailabilityV1(x, y);
        firstX = x;
        firstY = y;
    }

    /**
     * This method adds the second or third picked {@link PlayableItemTile PlayableItemTile} to the
     * {@link ArrayList ArrayList} {@code toPlayerTiles}.
     * The selected {@link PlayableItemTile PlayableItemTile} is picked from the {@link LivingRoom LivingRoom}
     * and then put into the {@link ArrayList ArrayList}.
     *
     * @param x the 'x' position on the {@link LivingRoom LivingRoom} of the
     * {@link PlayableItemTile PlayableItemTile} to add
     * @param y the 'y' position on the {@link LivingRoom LivingRoom} of the
     * {@link PlayableItemTile PlayableItemTile} to add
     */
    public void setToPlayerAnotherTile(int x, int y){ //prende tessera 2/3 dalla LivingRoom

        toPlayerTiles.add(livingRoom.pickTile(x, y));
        livingRoom.updateAdjacentAvailabilityV2(x, y, this.firstX, this.firstY);
    }

    /**
     *
     * @return an {@link ArrayList ArrayList} containing the {@link PlayableItemTile PlayableItemTiles} that the {@link Player Player}
     * has picked during the turn
     */
    public ArrayList<PlayableItemTile> getToPlayerTiles(){
        return toPlayerTiles;
    }

}


