package Model;

import org.junit.Assert;
import org.junit.Test;

import org.junit.jupiter.api.BeforeEach;
import server.Model.NullItemTile;
import Util.*;


public class NullItemTileTest {

    NullItemTile nullItemTile;

    @BeforeEach
    public void setup(){
        nullItemTile = new NullItemTile();
    }

    @Test
    public void testGetColour() {
        Assert.assertEquals(Colour.VOID, nullItemTile.getColour());
    }

    @Test
    public void testGetIdCode() {
        Assert.assertEquals(0, nullItemTile.getIdCode());
    }

    @Test
    public void testGetAvailability() {
        Assert.assertFalse(nullItemTile.getAvailability());
    }

    @Test
    public void testNullDetection() {
        Assert.assertTrue(nullItemTile.nullDetection());
    }

    @Test
    public void testGetAdjacency() {
        Assert.assertFalse(nullItemTile.getAdjacency());
    }

    @Test
    public void testResetAdjacency() {
        nullItemTile.resetAdjacency();
        Assert.assertFalse(nullItemTile.getAdjacency());
    }
}

