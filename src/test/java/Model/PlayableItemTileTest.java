package Model;

import org.junit.Assert;

import org.junit.jupiter.api.Test;




import server.Model.PlayableItemTile;
import Util.*;

public class PlayableItemTileTest {

    @Test
    public void testGetColour() {
        PlayableItemTile playableItemTile = new PlayableItemTile("GREEN");
        Assert.assertEquals(Colour.GREEN, playableItemTile.getColour());
    }

    @Test
    public void testGetIdCode() {
        PlayableItemTile playableItemTile = new PlayableItemTile("GREEN");
        Assert.assertEquals(1, playableItemTile.getIdCode());
    }

    @Test
    public void testNullDetection(){
        PlayableItemTile playableItemTile = new PlayableItemTile("PINK");
        Assert.assertFalse(playableItemTile.nullDetection());
    }

    @Test
    public void testMakeAvailable() {
        PlayableItemTile playableItemTile = new PlayableItemTile("GREEN");
        playableItemTile.makeAvailable();
        Assert.assertTrue(playableItemTile.getAvailability());
    }

    @Test
    public void testMakeUnavailable() {
        PlayableItemTile playableItemTile = new PlayableItemTile("GREEN");
        playableItemTile.makeAvailable();
        playableItemTile.makeUnavailable();
        Assert.assertFalse(playableItemTile.getAvailability());
    }

    @Test
    public void testSetAdjacency() {
        PlayableItemTile playableItemTile = new PlayableItemTile("GREEN");
        playableItemTile.setAdjacency();
        Assert.assertTrue(playableItemTile.getAdjacency());
    }

    @Test
    public void testResetAdjacency() {
        PlayableItemTile playableItemTile = new PlayableItemTile("GREEN");
        playableItemTile.setAdjacency();
        playableItemTile.resetAdjacency();
        Assert.assertFalse(playableItemTile.getAdjacency());
    }

}

