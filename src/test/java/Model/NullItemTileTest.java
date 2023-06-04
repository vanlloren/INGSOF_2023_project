package Model;

import org.junit.Assert;

import org.junit.jupiter.api.BeforeEach;
import server.Model.NullItemTile;
import Util.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;


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
        nullItemTile.setAdjacency();
        Assert.assertFalse(nullItemTile.getAdjacency());
    }

    @Test
    public void testResetAdjacency() {
        nullItemTile.resetAdjacency();
        Assert.assertFalse(nullItemTile.getAdjacency());
    }

    @Test
    public void testModifyAvailability(){
        nullItemTile.makeAvailable();
        Assert.assertTrue(nullItemTile.getAvailability()==false);
        nullItemTile.makeUnavailable();
        Assert.assertTrue(nullItemTile.getAvailability()==false);
    }

    @Test
    public void testGetAndSetPosition(){
        nullItemTile.setPosition(1,1);
        ArrayList<Integer> noPos = new ArrayList<Integer>();
        Assert.assertEquals(noPos, nullItemTile.getPosition());
    }
}

