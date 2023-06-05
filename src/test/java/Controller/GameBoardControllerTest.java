package Controller;

import Util.Colour;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import server.Controller.GameBoardController;
import server.Controller.GameController;
import server.Model.*;

public class GameBoardControllerTest {
    @Test
    public void testGameBoardInit() {
        GameBoardController gameBoardController = new GameBoardController(new GameController(new GameModel()));
        ItemBag bag = new ItemBag();
        bag.putTiles();
        LivingRoom livingRoom = new LivingRoom(new GameModel());
        livingRoom.createGameTable(3);
        boolean isVoid;
        PlayableItemTile helperTile;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                isVoid = livingRoom.searchVoid(i, j);
                if (isVoid) {
                    helperTile = bag.randPickTile();
                    if (helperTile.getColour() != Colour.VOID) {
                        livingRoom.fillVoid(i, j, helperTile);
                    }
                }
            }
        }
        livingRoom.updateAvailability();

        gameBoardController.setPlayerNum(3);
        gameBoardController.gameBoardInit();

        Assert.assertEquals(95, gameBoardController.getControlledGameBoard().getItemBag().getBag().size());
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Assert.assertEquals(livingRoom.getTileAvailability(i, j), gameBoardController.getControlledGameBoard().getLivingRoom().getTileAvailability(i, j));
            }
        }
    }

    @Test
    public void testLivingRoomFiller() {
        GameBoardController gameBoardController = new GameBoardController(new GameController(new GameModel()));
        gameBoardController.setPlayerNum(3);
        gameBoardController.gameBoardInit();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!gameBoardController.getControlledLivingRoom().nullDetection(i, j)) {
                    gameBoardController.getControlledLivingRoom().getGameTable()[i][j] = null;
                }
            }
        }

        gameBoardController.livingRoomFiller();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Assert.assertTrue(gameBoardController.getControlledLivingRoom().getGameTable()[i][j] != null);
            }
        }
    }

    @Test
    public void testPickManager() {
        GameBoardController gameBoardController = new GameBoardController(new GameController(new GameModel()));
        gameBoardController.setPlayerNum(2);
        gameBoardController.gameBoardInit();

        Colour helperColour = gameBoardController.getControlledGameBoard().getLivingRoom().getGameTable()[1][3].getColour();
        int helperId = gameBoardController.getControlledGameBoard().getLivingRoom().getGameTable()[1][3].getIdCode();

        Assert.assertEquals(null, gameBoardController.PickManager(4, 4));

        PlayableItemTile helperTile = gameBoardController.PickManager(1, 3);
        Assert.assertTrue(helperColour.equals(helperTile.getColour()) &&
                helperId == helperTile.getIdCode());

        Assert.assertEquals(null, gameBoardController.PickManager(5, 1));

        helperColour = gameBoardController.getControlledGameBoard().getLivingRoom().getGameTable()[1][4].getColour();
        helperId = gameBoardController.getControlledGameBoard().getLivingRoom().getGameTable()[1][4].getIdCode();

        helperTile = gameBoardController.PickManager(1, 4);
        Assert.assertTrue(helperColour.equals(helperTile.getColour()) &&
                helperId == helperTile.getIdCode());

        Assert.assertEquals(null, gameBoardController.PickManager(5, 5));


        gameBoardController = new GameBoardController(new GameController(new GameModel()));
        gameBoardController.setPlayerNum(2);
        gameBoardController.gameBoardInit();


        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!gameBoardController.getControlledLivingRoom().nullDetection(i, j)) {
                    gameBoardController.getControlledLivingRoom().getGameTable()[i][j] = null;
                }
            }
        }

        gameBoardController.getControlledLivingRoom().getGameTable()[3][2] = new PlayableItemTile("BLUE", 1);
        gameBoardController.getControlledLivingRoom().getGameTable()[4][2] = new PlayableItemTile("BLUE", 2);
        gameBoardController.getControlledLivingRoom().getGameTable()[4][1] = new PlayableItemTile("BLUE", 3);
        gameBoardController.getControlledLivingRoom().getGameTable()[4][3] = new PlayableItemTile("BLUE", 4);
        gameBoardController.getControlledLivingRoom().getGameTable()[3][3] = new PlayableItemTile("BLUE", 5);
        gameBoardController.getControlledLivingRoom().getGameTable()[3][4] = new PlayableItemTile("BLUE", 6);
        gameBoardController.getControlledLivingRoom().getGameTable()[5][2] = new PlayableItemTile("BLUE", 7);
        gameBoardController.getControlledLivingRoom().getGameTable()[2][3] = new PlayableItemTile("BLUE", 8);

        helperColour = gameBoardController.getControlledGameBoard().getLivingRoom().getGameTable()[3][2].getColour();
        helperId = gameBoardController.getControlledGameBoard().getLivingRoom().getGameTable()[3][2].getIdCode();

        gameBoardController.getControlledLivingRoom().updateAvailability();

        helperTile = gameBoardController.PickManager(3, 2);
        Assert.assertTrue(helperColour.equals(helperTile.getColour()) &&
                helperId == helperTile.getIdCode());
        Assert.assertEquals(null, gameBoardController.PickManager(4, 2));
    }

    @Test
    public void testCheckIfAdjacentTiles(){
        GameBoardController gameBoardController = new GameBoardController(new GameController(new GameModel()));
        gameBoardController.setPlayerNum(4);
        gameBoardController.gameBoardInit();

        Assert.assertTrue(gameBoardController.checkIfAdjacentTiles());

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!gameBoardController.getControlledLivingRoom().nullDetection(i, j)) {
                    gameBoardController.getControlledLivingRoom().getGameTable()[i][j] = null;
                }
            }
        }

        gameBoardController.getControlledLivingRoom().getGameTable()[3][3] = new PlayableItemTile("BLUE", 5);
        gameBoardController.getControlledLivingRoom().getGameTable()[5][5] = new PlayableItemTile("PINK", 10);

        Assert.assertFalse(gameBoardController.checkIfAdjacentTiles());

    }
}
