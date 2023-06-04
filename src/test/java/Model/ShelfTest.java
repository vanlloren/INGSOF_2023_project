package Model;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

import Network.ClientSide.RemoteClientImplementation;
import Network.ClientSide.RemoteClientInterface;
import Network.message.Message;
import Util.Colour;
import Util.CommonGoalType;
import Util.PersonalGoalType;
import client.view.TUI;
import client.view.TurnView;
import org.junit.Assert;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;




import server.Model.*;


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
                Assert.assertTrue(shelf.getStructure()[i][j].getColour() == Colour.VOID );
                Assert.assertTrue(shelf.getStructure()[i][j].getIdCode() == -1);
            }
        }
    }


    @Test
    public void testIsColumnAvailableInGame() {
    }
    @Test
    public void testPutTile() {
        shelf.putTile(1,2, new PlayableItemTile("GREEN", 4));
        Assert.assertTrue(shelf.getStructure()[1][2].getColour().equals(Colour.GREEN));
        Assert.assertTrue(shelf.getStructure()[1][2].getIdCode() == 4);
    }
    @Test
    public void testIsFull() {
        shelf.setUp();
        Assert.assertFalse(shelf.isFull());

        int count=1;
        for(int i=0;i<6;i++){
            for (int j=0; j<5; j++) {
                shelf.putTile(i, j, new PlayableItemTile("GREEN", count));
                count++;
            }
        }

        Assert.assertTrue(shelf.isFull());
    }
}