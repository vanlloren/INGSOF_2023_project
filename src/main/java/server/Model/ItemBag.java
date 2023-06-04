package server.Model;


import java.util.ArrayList;
import java.util.Random;
import Util.*;
import Exceptions.*;


public class ItemBag {


    private ArrayList<PlayableItemTile> bag;


    public ArrayList<PlayableItemTile> getBag(){
        return bag;
    }

    public ArrayList<PlayableItemTile> getBagForTest(){
        bag = new ArrayList<PlayableItemTile>();
        return bag;
    }

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



