package Model;

import Util.Colour;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import server.Model.PlayableItemTile;

import java.util.ArrayList;

public class PlayableItemTileTest {

    @Test
    public void testGetColour() {
        PlayableItemTile playableItemTile = new PlayableItemTile("GREEN",1);
        Assertions.assertEquals(Colour.GREEN, playableItemTile.getColour());
    }

    @Test
    public void testGetIdCode() {
        PlayableItemTile playableItemTile = new PlayableItemTile("GREEN",1);
        Assertions.assertEquals(1, playableItemTile.getIdCode());
    }

    @Test
    public void testNullDetection(){
        PlayableItemTile playableItemTile = new PlayableItemTile("PINK",1);
        Assertions.assertFalse(playableItemTile.nullDetection());
    }

    @Test
    public void testMakeAvailable() {
        PlayableItemTile playableItemTile = new PlayableItemTile("GREEN",1);
        playableItemTile.makeAvailable();
        Assertions.assertTrue(playableItemTile.getAvailability());
    }

    @Test
    public void testMakeUnavailable() {
        PlayableItemTile playableItemTile = new PlayableItemTile("GREEN",1);
        playableItemTile.makeAvailable();
        playableItemTile.makeUnavailable();
        Assertions.assertFalse(playableItemTile.getAvailability());
    }

    @Test
    public void testSetAdjacency() {
        PlayableItemTile playableItemTile = new PlayableItemTile("GREEN",1);
        playableItemTile.setAdjacency();
        Assertions.assertTrue(playableItemTile.getAdjacency());
    }

    @Test
    public void testResetAdjacency() {
        PlayableItemTile playableItemTile = new PlayableItemTile("GREEN",1);
        playableItemTile.setAdjacency();
        playableItemTile.resetAdjacency();
        Assertions.assertFalse(playableItemTile.getAdjacency());
    }

    @Test
    public void testGetPosition(){
        PlayableItemTile tile = new PlayableItemTile("BLUE", 1);

        tile.setPosition(1,3);

        ArrayList<Integer> tilePosition = new ArrayList<>();
        tilePosition.add(1);
        tilePosition.add(3);

        Assertions.assertEquals(tilePosition, tile.getPosition());
    }

}

