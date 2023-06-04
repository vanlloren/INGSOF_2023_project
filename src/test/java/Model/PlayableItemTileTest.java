package Model;

import org.junit.Assert;

import org.junit.jupiter.api.Test;




import server.Model.PlayableItemTile;
import Util.*;

import java.util.ArrayList;

public class PlayableItemTileTest {

    @Test
    public void testGetColour() {
        PlayableItemTile playableItemTile = new PlayableItemTile("GREEN",1);
        Assert.assertEquals(Colour.GREEN, playableItemTile.getColour());
    }

    @Test
    public void testGetIdCode() {
        PlayableItemTile playableItemTile = new PlayableItemTile("GREEN",1);
        Assert.assertEquals(1, playableItemTile.getIdCode());
    }

    @Test
    public void testNullDetection(){
        PlayableItemTile playableItemTile = new PlayableItemTile("PINK",1);
        Assert.assertFalse(playableItemTile.nullDetection());
    }

    @Test
    public void testMakeAvailable() {
        PlayableItemTile playableItemTile = new PlayableItemTile("GREEN",1);
        playableItemTile.makeAvailable();
        Assert.assertTrue(playableItemTile.getAvailability());
    }

    @Test
    public void testMakeUnavailable() {
        PlayableItemTile playableItemTile = new PlayableItemTile("GREEN",1);
        playableItemTile.makeAvailable();
        playableItemTile.makeUnavailable();
        Assert.assertFalse(playableItemTile.getAvailability());
    }

    @Test
    public void testSetAdjacency() {
        PlayableItemTile playableItemTile = new PlayableItemTile("GREEN",1);
        playableItemTile.setAdjacency();
        Assert.assertTrue(playableItemTile.getAdjacency());
    }

    @Test
    public void testResetAdjacency() {
        PlayableItemTile playableItemTile = new PlayableItemTile("GREEN",1);
        playableItemTile.setAdjacency();
        playableItemTile.resetAdjacency();
        Assert.assertFalse(playableItemTile.getAdjacency());
    }

    @Test
    public void testGetPosition(){
        PlayableItemTile tile = new PlayableItemTile("BLUE", 1);

        tile.setPosition(1,3);

        ArrayList<Integer> tilePosition = new ArrayList<Integer>();
        tilePosition.add(1);
        tilePosition.add(3);

        Assert.assertEquals(tilePosition, tile.getPosition());
    }

}

