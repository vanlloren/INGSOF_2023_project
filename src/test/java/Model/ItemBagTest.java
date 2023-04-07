package Model;

import Util.Colour;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import org.junit.jupiter.api.*;

import server.Model.ItemBag;
import server.Model.PlayableItemTile;


public class ItemBagTest {

    private ItemBag itemBag;

    @BeforeEach
    public void setUp() {
        itemBag = new ItemBag();
    }

    @Test
    public void testPutTiles1() { //testo riempiemento con 132 PlayableItemTiles
        itemBag.putTiles();
        ArrayList<PlayableItemTile> bag = itemBag.getBag();
        Assertions.assertEquals(132, bag.size());
    }

    @Test
    public void testRandPickTile() {
        itemBag.putTiles();
        ArrayList<PlayableItemTile> bag = itemBag.getBag();
        PlayableItemTile tile = itemBag.randPickTile();
        Assertions.assertFalse(bag.contains(tile));
        Assertions.assertEquals(131, bag.size());
    }

    @Test
    public void testRandPickTileEmptyBag() {
        ArrayList<PlayableItemTile> bag = itemBag.getBag();
        Assertions.assertTrue(bag.isEmpty());
        PlayableItemTile tile = itemBag.randPickTile();
        Assertions.assertEquals(Colour.VOID, tile.getColour());
    }

    @Test
    public void testPutTiles2() { //testo che le 132 tiles siano 22 per ognuno dei 6 tipi
        itemBag.putTiles();

        Map<String, Integer> expectedCounts = new HashMap<>();
        expectedCounts.put("GREEN", 22);
        expectedCounts.put("WHITE", 22);
        expectedCounts.put("YELLOW", 22);
        expectedCounts.put("BLUE", 22);
        expectedCounts.put("CYAN", 22);
        expectedCounts.put("PINK", 22);

        Map<String, Integer> actualCounts = new HashMap<>();
        ArrayList<PlayableItemTile> bag = itemBag.getBag();
        for (PlayableItemTile tile : bag) {
            String colour = tile.getColour().toString();
            actualCounts.put(colour, actualCounts.getOrDefault(colour, 0) + 1);
        }

        assertEquals(expectedCounts, actualCounts);
    }
}

