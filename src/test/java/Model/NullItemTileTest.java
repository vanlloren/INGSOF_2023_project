package Model;

import Util.Colour;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.Model.NullItemTile;

import java.util.ArrayList;


public class NullItemTileTest {

    NullItemTile nullItemTile;

    @BeforeEach
    public void setup(){
        nullItemTile = new NullItemTile();
    }

    @Test
    public void testGetColour() {
        Assertions.assertEquals(Colour.VOID, nullItemTile.getColour());
    }

    @Test
    public void testGetIdCode() {
        Assertions.assertEquals(0, nullItemTile.getIdCode());
    }

    @Test
    public void testGetAvailability() {
        Assertions.assertFalse(nullItemTile.getAvailability());
    }

    @Test
    public void testNullDetection() {
        Assertions.assertTrue(nullItemTile.nullDetection());
    }

    @Test
    public void testGetAdjacency() {
        nullItemTile.setAdjacency();
        Assertions.assertFalse(nullItemTile.getAdjacency());
    }

    @Test
    public void testResetAdjacency() {
        nullItemTile.resetAdjacency();
        Assertions.assertFalse(nullItemTile.getAdjacency());
    }

    @Test
    public void testModifyAvailability(){
        nullItemTile.makeAvailable();
        Assertions.assertFalse(nullItemTile.getAvailability());
        nullItemTile.makeUnavailable();
        Assertions.assertFalse(nullItemTile.getAvailability());
    }

    @Test
    public void testGetAndSetPosition(){
        nullItemTile.setPosition(1,1);
        ArrayList<Integer> noPos = new ArrayList<>();
        Assertions.assertEquals(noPos, nullItemTile.getPosition());
    }
}

