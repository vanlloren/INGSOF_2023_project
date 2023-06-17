package Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.Model.*;

import java.util.ArrayList;

public class GameBoardTest {

    private GameBoard board;

    @BeforeEach
    public void setUp() {
        GameModel model = new GameModel();
        board = new GameBoard(model);
        board.setItemBag();
    }

    @Test
    public void testSetItemBag() {
        board.setItemBag();
        Assertions.assertNotNull(board.getItemBag());
    }

    @Test
    public void testSetLivingRoom() {
        board.setLivingRoom(2);
        Assertions.assertNotNull(board.getLivingRoom());
    }

    @Test
    public void testSetNextInGameTile() {
        board.setItemBag();
        board.setNextInGameTile();
        Assertions.assertNotNull(board.getNextInGameTile());
    }

    @Test
    public void testSetToPlayerFirstTile() {
        board.setLivingRoom(2);
        board.setToPlayerFirstTile(4, 4);
        ArrayList<PlayableItemTile> helperList = board.getToPlayerTiles();
        Assertions.assertEquals(1, helperList.size());
        Assertions.assertNotNull(helperList.get(0));
    }

    @Test
    public void testSetToPlayerAnotherTile() {
        board.setLivingRoom(2);
        board.setToPlayerFirstTile(4, 4);
        board.setToPlayerAnotherTile(4, 5);
        ArrayList<PlayableItemTile> helperList = board.getToPlayerTiles();
        Assertions.assertEquals(2, helperList.size());
        Assertions.assertNotNull(helperList.get(1));
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

        Assertions.assertEquals(0, checker);
    }

    @Test
    public void testGetPickedTilesNumber(){
        Assertions.assertEquals(0, board.getPickedTilesNum());
    }
}