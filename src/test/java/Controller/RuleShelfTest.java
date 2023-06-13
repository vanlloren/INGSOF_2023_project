package Controller;

import Util.Colour;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import server.Controller.RuleShelf;
import server.Model.GameModel;
import server.Model.PlayableItemTile;
import server.Model.Player;
import server.Model.Shelf;



public class RuleShelfTest {
    @Test
    public void testCommandPutTileCheckValidity(){
        Shelf shelf = new Shelf(new GameModel());
        shelf.setUp();


        Assert.assertTrue(RuleShelf.commandPutTileCheckValidity(5, 3, shelf.getStructure()));

        shelf.putTile(5, 3, new PlayableItemTile("GREEN", 1));
        Assert.assertTrue(RuleShelf.commandPutTileCheckValidity(4, 3, shelf.getStructure()));
        Assert.assertFalse(RuleShelf.commandPutTileCheckValidity(5, 3, shelf.getStructure()));
        Assert.assertFalse(RuleShelf.commandPutTileCheckValidity(4, 2, shelf.getStructure()));
    }

    @Test
    public void testIsColumnAvailable(){
        Shelf shelf = new Shelf(new GameModel());
        shelf.setUp();
        Assert.assertTrue(RuleShelf.isColumnAvailable(3, 3, shelf.getStructure()));
        Assert.assertTrue(RuleShelf.isColumnAvailable(3, 2, shelf.getStructure()));
        Assert.assertTrue(RuleShelf.isColumnAvailable(3, 1, shelf.getStructure()));

        shelf.putTile(5, 3, new PlayableItemTile("GREEN", 1));
        shelf.putTile(4, 3, new PlayableItemTile("GREEN", 3));
        shelf.putTile(3, 3, new PlayableItemTile("GREEN", 2));
        Assert.assertTrue(RuleShelf.isColumnAvailable(3, 3, shelf.getStructure()));
        Assert.assertTrue(RuleShelf.isColumnAvailable(3, 2, shelf.getStructure()));
        Assert.assertTrue(RuleShelf.isColumnAvailable(3, 1, shelf.getStructure()));

        shelf.putTile(2, 3, new PlayableItemTile("GREEN", 4));
        Assert.assertFalse(RuleShelf.isColumnAvailable(3, 3, shelf.getStructure()));
        Assert.assertTrue(RuleShelf.isColumnAvailable(3, 2, shelf.getStructure()));
        Assert.assertTrue(RuleShelf.isColumnAvailable(3, 1, shelf.getStructure()));

        shelf.putTile(1, 3, new PlayableItemTile("GREEN", 5));
        Assert.assertFalse(RuleShelf.isColumnAvailable(3, 3, shelf.getStructure()));
        Assert.assertFalse(RuleShelf.isColumnAvailable(3, 2, shelf.getStructure()));
        Assert.assertTrue(RuleShelf.isColumnAvailable(3, 1, shelf.getStructure()));

        shelf.putTile(0, 3, new PlayableItemTile("GREEN", 6));
        Assert.assertFalse(RuleShelf.isColumnAvailable(3, 3, shelf.getStructure()));
        Assert.assertFalse(RuleShelf.isColumnAvailable(3, 2, shelf.getStructure()));
        Assert.assertFalse(RuleShelf.isColumnAvailable(3, 1, shelf.getStructure()));
    }
}
