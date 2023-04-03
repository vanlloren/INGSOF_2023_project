package Model;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ItemBag(){


    private List<PlayableItemTile> bag;


    public void putTiles() {
        bag = new ArrayList<PlayableItemTile>();
        PlayableItemTileFactory factory = new PlayableItemTileFactory();

        for (int i = 0; i < 132; i++) {
            if (i < 22) {
                try {
                    bag.add(factory.createPlayableItemTile("GREEN", i + 1));
                } catch (InvalidPlayableItemTileColourException exc) {

                }
            } else if (i < 44) {
                try {
                    bag.add(factory.createPlayableItemTile("WHITE", i + 1));
                } catch (InvalidPlayableItemTileColourException exc) {

                }
            } else if (i < 66) {
                try {
                    bag.add(factory.createPlayableItemTile("YELLOW", i + 1));
                } catch (InvalidPlayableItemTileColourException exc) {

                }
            } else if (i < 88) {
                try {
                    bag.add(factory.createPlayableItemTile("BLUE", i + 1));
                } catch (InvalidPlayableItemTileColourException exc) {

                }
            } else if (i < 110) {
                try {
                    bag.add(factory.createPlayableItemTile("CYAN", i + 1));
                } catch (InvalidPlayableItemTileColourException exc) {

                }
            } else if (i < 132) {
                try {
                    bag.add(factory.createPlayableItemTile("PINK", i + 1));
                } catch (InvalidPlayableItemTileColourException exc) {

                }
            }
        }
    }

    //dovrebbe effettuare pick random di ItemTile da ItemBag
    public org.example.PlayableItemTile randPickTile() {
        final Random RAND = new Random();
        int index = RAND.nextInt(bag.size());
        PlayableItemTile helperTile = bag.get(index);
        bag.remove(index);
        return helperTile;
    }
}



