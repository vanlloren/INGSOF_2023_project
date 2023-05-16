package Model;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;


import server.Model.*;

public class GameBoardTest {

    private GameBoard board;

    @BeforeEach
    public void setUp() {
        GameModel model = new GameModel();
        board = new GameBoard(model);
    }

    @Test
    public void testSetItemBag() {
        board.setItemBag();
        assertNotNull(board.getBag());
    }

    @Test
    public void testSetLivingRoom() {
        board.setLivingRoom(2);
        assertNotNull(board.getLivingRoom());
    }

    @Test
    public void testSetNextInGameTile() {
        board.setItemBag();
        board.setNextInGameTile();
        assertNotNull(board.getNextInGameTile());
    }

    @Test
    public void testSetToPlayerFirstTile() {
        board.setLivingRoom(2);
        board.setToPlayerFirstTile(4, 4);
        ArrayList<PlayableItemTile> helperList = board.getToPlayerTiles();
        assertEquals(1, helperList.size());
        assertNotNull(helperList.get(0));
    }

    @Test
    public void testSetToPlayerAnotherTile() {
        board.setLivingRoom(2);
        board.setToPlayerFirstTile(4, 4);
        board.setToPlayerAnotherTile(4, 5);
        ArrayList<PlayableItemTile> helperList = board.getToPlayerTiles();
        assertEquals(2, helperList.size());
        assertNotNull(helperList.get(1));
    }

    @Test
    public void testFirstFilling(){
        board.setLivingRoom(2);
        board.firstFilling(board.getLivingRoom());

        LivingRoom helperLivingRoom = board.getLivingRoom();
        int checker=0;
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                ItemTile[][] table = helperLivingRoom.getGameTable();
                if(table[i][j] == null ){
                    checker++;
                }
            }
        }

        Assert.assertEquals(0, checker);
    }
}