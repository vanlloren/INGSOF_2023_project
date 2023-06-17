package Model;

import Util.Colour;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.Model.GameModel;
import server.Model.PlayableItemTile;
import server.Model.Shelf;


public class ShelfTest {
    private Shelf shelf;

    @BeforeEach
    public void setUp(){
        shelf = new Shelf(new GameModel());
    }

    @Test
    public void testSetUp(){
        shelf.setUp();

        for(int i=0;i<6;i++){
            for (int j=0; j<5; j++){
                Assertions.assertSame(shelf.getStructure()[i][j].getColour(), Colour.VOID);
                Assertions.assertEquals(-1, shelf.getStructure()[i][j].getIdCode());
            }
        }
    }


    @Test
    public void testIsColumnAvailableInGame() {
    }
    @Test
    public void testPutTile() {
        shelf.putTile(1,2, new PlayableItemTile("GREEN", 4));
        Assertions.assertEquals(shelf.getStructure()[1][2].getColour(), Colour.GREEN);
        Assertions.assertEquals(4, shelf.getStructure()[1][2].getIdCode());
    }
    @Test
    public void testIsFull() {
        shelf.setUp();
        Assertions.assertFalse(shelf.isFull());

        int count=1;
        for(int i=0;i<6;i++){
            for (int j=0; j<5; j++) {
                shelf.putTile(i, j, new PlayableItemTile("GREEN", count));
                count++;
            }
        }

        Assertions.assertTrue(shelf.isFull());
    }

    @Test
    public void testCountMaxVoidTilesShelfColumns(){
        shelf.setUp();
        Assertions.assertEquals(6, shelf.countMaxVoidTilesShelfColumns());

        shelf.putTile(5,0, new PlayableItemTile("GREEN", 4));
        Assertions.assertEquals(6, shelf.countMaxVoidTilesShelfColumns());


        shelf.putTile(4,0, new PlayableItemTile("GREEN", 4));
        Assertions.assertEquals(6, shelf.countMaxVoidTilesShelfColumns());


        shelf.putTile(3,0, new PlayableItemTile("GREEN", 4));
        Assertions.assertEquals(6, shelf.countMaxVoidTilesShelfColumns());

        shelf.putTile(2,0, new PlayableItemTile("GREEN", 4));
        Assertions.assertEquals(6, shelf.countMaxVoidTilesShelfColumns());

        for(int j=1;j<5;j++){
            for(int i=0;i<6;i++){
                shelf.putTile(i, j, new PlayableItemTile("GREEN", 10));
            }
        }
        shelf.putTile(0,1, new PlayableItemTile("VOID", -1));
        Assertions.assertEquals(2, shelf.countMaxVoidTilesShelfColumns());

        for(int j=1;j<5;j++){
            for(int i=0;i<6;i++){
                shelf.putTile(i, j, new PlayableItemTile("GREEN", 10));
            }
        }
        Assertions.assertEquals(2, shelf.countMaxVoidTilesShelfColumns());

        shelf.putTile(1,0, new PlayableItemTile("GREEN", 4));
        Assertions.assertEquals(1, shelf.countMaxVoidTilesShelfColumns());

        shelf.putTile(0,0, new PlayableItemTile("GREEN", 4));
        Assertions.assertEquals(0, shelf.countMaxVoidTilesShelfColumns());
    }
}