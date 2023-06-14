package server.Model;


import java.util.ArrayList;
import java.util.Random;
import Util.*;
import Exceptions.*;

/**
 * This Class represents the {@link ItemBag ItemBag} as described in the game rules.
 * The {@link ItemBag ItemBag} is an {@link ArrayList ArrayList} of {@link PlayableItemTile PlayableItemTile}
 * that are used to fill the blank spaces inside the {@link LivingRoom LivingRoom} during the game.
 *
 * <i>Have a look at MyShelfie RuleBook for further information</i>
 */
public class ItemBag {


    private ArrayList<PlayableItemTile> bag;


    /**
     *
     * @return the {@link ArrayList ArrayList} containing all the {@link PlayableItemTile PlayableItemTiles}
     * in the {@link ItemBag ItemBag}
     */
    public ArrayList<PlayableItemTile> getBag(){
        return bag;
    }

    public ArrayList<PlayableItemTile> getBagForTest(){
        bag = new ArrayList<PlayableItemTile>();
        return bag;
    }

    /**
     * This method fills the {@link ItemBag ItemBag} with 132 {@link PlayableItemTile PlayableItemTiles}
     * as written in the Game Rules.
     * The {@link PlayableItemTile PlayableItemTiles} are created one by one, in groups of 22
     * {@link PlayableItemTile PlayableItemTiles} that share the same {@link Colour Colour},
     * using a progressive index to determine their {@code idCode}.
     */
    public void putTiles() {
        bag = new ArrayList<PlayableItemTile>();
        PlayableItemTileFactory factory = new PlayableItemTileFactory();

        for (int i = 0; i < 132; i++) {
            if (i < 22) {
                bag.add(factory.createPlayableItemTile("GREEN", i + 1));
            } else if (i < 44) {
                bag.add(factory.createPlayableItemTile("WHITE", i + 1));
            } else if (i < 66) {
                bag.add(factory.createPlayableItemTile("YELLOW", i + 1));
            } else if (i < 88) {
                bag.add(factory.createPlayableItemTile("BLUE", i + 1));
            } else if (i < 110) {
                bag.add(factory.createPlayableItemTile("CYAN", i + 1));
            } else if (i < 132) {
                bag.add(factory.createPlayableItemTile("PINK", i + 1));
            }
        }
    }

    //dovrebbe effettuare pick random di ItemTile da ItemBag

    /**
     * This method picks a {@link PlayableItemTile PlayableItemTile} randomly from the {@link ItemBag ItemBag}
     *
     * @return the randomly picked {@link PlayableItemTile PlayableItemTile}
     */
    public PlayableItemTile randPickTile(){
        PlayableItemTile helperTile;
        final Random RAND = new Random();
        if(bag.size() == 0){
            helperTile = new PlayableItemTile("VOID", 0);
        }else {
            int index = RAND.nextInt(bag.size());
            helperTile = bag.get(index);
            bag.remove(index);
        }
        return helperTile;
    }
}



