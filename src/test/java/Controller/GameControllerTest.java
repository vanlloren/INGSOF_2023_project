package Controller;

import Network.ClientSide.RemoteClientImplementation;
import Network.message.InsertionReplyMessage;
import Network.message.MessageEnumeration;
import Network.message.TileReplyMessage;
import Util.Colour;
import Util.CommonGoalType;
import Util.PersonalGoalType;
import Util.RandPersonalGoal;
import client.view.TUI;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import server.Controller.GameController;
import server.Model.*;
import server.enumerations.PickTileResponse;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import static server.Controller.GameController.findAdjGroups;


public class GameControllerTest {

    @Test
    public void TestEmptyShelf() {
        Shelf shelf = new Shelf(new GameModel());
        shelf.setUp();
        PlayableItemTile[][] structure1 = shelf.getStructure();
        HashMap<Colour, ArrayList<Integer>> result = findAdjGroups(structure1);
        HashMap<Colour, ArrayList<Integer>> compare = new HashMap<>();
        Assertions.assertEquals(result, compare);
    }
    @Test
    public void testSingleObj(){
        Shelf shelf = new Shelf(new GameModel());
        shelf.setUp();
        PlayableItemTile[][] structure1 = shelf.getStructure();
        structure1[5][4] = new PlayableItemTile("PINK", 1);
        HashMap<Colour, ArrayList<Integer>> result = findAdjGroups(structure1);
        HashMap<Colour, ArrayList<Integer>> compare = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        compare.put(Colour.PINK, list);
        Assertions.assertEquals(compare, result);
    }
    @Test
    public void test3ObjGroup(){
        Shelf shelf = new Shelf(new GameModel());
        shelf.setUp();
        PlayableItemTile[][] structure1 = shelf.getStructure();
        structure1[0][0] = new PlayableItemTile("PINK", 1);
        structure1[0][1] = new PlayableItemTile("PINK", 11);
        structure1[0][2] = new PlayableItemTile("PINK", 1);

        HashMap<Colour, ArrayList<Integer>> result = findAdjGroups(structure1);
        HashMap<Colour, ArrayList<Integer>> compare = new HashMap<>();
        ArrayList<Integer> list1 = new ArrayList<>();

        list1.add(3);
        compare.put(Colour.PINK, list1);

        Assertions.assertEquals(compare, result);

    }
    @Test
    public void test4ObjGroup(){
        Shelf shelf = new Shelf(new GameModel());
        shelf.setUp();
        PlayableItemTile[][] structure1 = shelf.getStructure();
        structure1[0][0] = new PlayableItemTile("PINK", 1);
        structure1[0][1] = new PlayableItemTile("PINK", 1);
        structure1[1][0] = new PlayableItemTile("PINK", 1);
        structure1[1][1] = new PlayableItemTile("PINK", 1);

        HashMap<Colour, ArrayList<Integer>> result = findAdjGroups(structure1);
        HashMap<Colour, ArrayList<Integer>> compare = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(4);
        compare.put(Colour.PINK, list);
        Assertions.assertEquals(compare, result);
    }

    @Test
    public void generalTest(){
        Shelf shelf = new Shelf(new GameModel());
        shelf.setUp();
        PlayableItemTile[][] structure = shelf.getStructure();

        //expected PINK=5
        structure[0][0] = new PlayableItemTile("PINK", 1);
        structure[0][1] = new PlayableItemTile("PINK", 1);
        structure[0][2] = new PlayableItemTile("PINK", 1);
        structure[1][0] = new PlayableItemTile("PINK", 1);
        structure[1][1] = new PlayableItemTile("PINK", 1);
        //expected CYAN = 3 && 1
        structure[2][4] = new PlayableItemTile("CYAN", 1);
        structure[2][2] = new PlayableItemTile("CYAN", 1);
        structure[1][4] = new PlayableItemTile("CYAN", 1);
        structure[3][4] = new PlayableItemTile("CYAN", 1);
        //expected WHITE=2
        structure[5][4] = new PlayableItemTile("WHITE", 1);
        structure[4][4] = new PlayableItemTile("WHITE", 1);
        //expected YELLOW=0
        //expected GREEN=0
        //expected BLUE=0

        HashMap<Colour, ArrayList<Integer>> result = findAdjGroups(structure);
        HashMap<Colour, ArrayList<Integer>> compare = new HashMap<>();
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        ArrayList<Integer> list3 = new ArrayList<>();

        list2.add(3);
        list2.add(1);
        list3.add(2);
        list1.add(5);
        compare.put(Colour.PINK, list1);
        compare.put(Colour.CYAN, list2);
        compare.put(Colour.WHITE, list3);
        Assertions.assertEquals(compare, result);

    }

    @Test
    public void testInitGameBoard(){
        GameController gameController = new GameController(new GameModel());

        gameController.getGameBoardController().setPlayerNum(3);
        gameController.initGameBoard();

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

        Assert.assertEquals(95, gameController.getGameBoardController().getControlledGameBoard().getItemBag().getBag().size());
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Assert.assertEquals(livingRoom.getTileAvailability(i, j), gameController.getGameBoardController().getControlledGameBoard().getLivingRoom().getTileAvailability(i, j));
            }
        }
    }

    @Test
    public void testAddPoint(){
        GameController gameController = new GameController(new GameModel());
        CommonGoal commonGoal = new CommonGoal();

        commonGoal.setTokens(2);
        Assert.assertEquals(8, (int) gameController.addPoint(commonGoal));
        Assert.assertEquals(4, (int) gameController.addPoint(commonGoal));

        commonGoal = new CommonGoal();
        commonGoal.setTokens(3);
        Assert.assertEquals(8, (int) gameController.addPoint(commonGoal));
        Assert.assertEquals(6, (int) gameController.addPoint(commonGoal));
        Assert.assertEquals(4, (int) gameController.addPoint(commonGoal));

        commonGoal = new CommonGoal();
        commonGoal.setTokens(4);
        Assert.assertEquals(8, (int) gameController.addPoint(commonGoal));
        Assert.assertEquals(6, (int) gameController.addPoint(commonGoal));
        Assert.assertEquals(4, (int) gameController.addPoint(commonGoal));
        Assert.assertEquals(2, (int) gameController.addPoint(commonGoal));


    }

    @Test
    public void testCalculatePoint() {
        GameController gameController = new GameController(new GameModel());
        ItemBag bag = new ItemBag();
        bag.putTiles();
        LivingRoom livingRoom = new LivingRoom(new GameModel());
        livingRoom.createGameTable(2);
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

        livingRoom.getCommonGoal1().setTokens(2);
        livingRoom.getCommonGoal2().setTokens(2);
        livingRoom.getCommonGoal1().setCommonGoalType(CommonGoalType.COMMONGOAL08);
        livingRoom.getCommonGoal2().setCommonGoalType(CommonGoalType.COMMONGOAL09);

        try {
            Player player = new Player("lorenzo", new RemoteClientImplementation("localhost", 1099, new TUI()), new GameModel());
            player.getPersonalGoal().setPersonalGoalType(PersonalGoalType.PERSONALGOAL1, player.getNickname());

            gameController.calculatePoint(player, livingRoom);
            Assert.assertEquals(0, (int) player.getPoints());


            PlayableItemTile[][] structure = player.getPersonalShelf().getStructure();
            structure[0][0] = new PlayableItemTile("PINK", 10);
            player.getPersonalShelf().setStructure(structure);

            gameController.calculatePoint(player, livingRoom);
            Assert.assertEquals(1, (int) player.getPoints());

            structure[0][4] = new PlayableItemTile("PINK", 11);
            structure[5][0] = new PlayableItemTile("PINK", 12);
            structure[5][4] = new PlayableItemTile("PINK", 13);
            player.getPersonalShelf().setStructure(structure);

            gameController.calculatePoint(player, livingRoom);
            Assert.assertEquals(9, (int) player.getPoints());

            structure[0][1] = new PlayableItemTile("PINK", 14);
            structure[1][0] = new PlayableItemTile("PINK", 15);
            player.getPersonalShelf().setStructure(structure);

            gameController.calculatePoint(player, livingRoom);
            Assert.assertEquals(11, (int) player.getPoints());

            structure[2][2] = new PlayableItemTile("BLUE", 16);
            structure[2][3] = new PlayableItemTile("BLUE", 17);
            structure[3][3] = new PlayableItemTile("BLUE", 18);
            structure[3][4] = new PlayableItemTile("BLUE", 19);

            player.getPersonalShelf().setStructure(structure);

            gameController.calculatePoint(player, livingRoom);
            Assert.assertEquals(14, (int) player.getPoints());

            structure[4][4] = new PlayableItemTile("BLUE", 20);
            gameController.calculatePoint(player, livingRoom);
            Assert.assertEquals(16, (int) player.getPoints());

            structure[4][1] = new PlayableItemTile("BLUE", 21);
            structure[0][2] = new PlayableItemTile("BLUE", 22);
            structure[0][3] = new PlayableItemTile("BLUE", 23);
            player.getPersonalShelf().setStructure(structure);

            gameController.calculatePoint(player, livingRoom);
            Assert.assertEquals(25, (int) player.getPoints());

            structure[2][4] = new PlayableItemTile("BLUE", 24);
            player.getPersonalShelf().setStructure(structure);

            gameController.calculatePoint(player, livingRoom);
            Assert.assertEquals(28, (int) player.getPoints());

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testPickTileV1(){
        GameModel gameModel = new GameModel();
        try {
            gameModel.addObserver(new RemoteClientImplementation("localhost", 1099, new TUI()));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        GameController gameController = new GameController(gameModel);
        gameController.getGameBoardController().setPlayerNum(2);
        gameController.initGameBoard();

        try {
            gameController.getGameBoardController().getControlledLivingRoom().addObserver(new RemoteClientImplementation("localhost", 1099, new TUI()));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        try {
            Player player = new Player("lorenzo", new RemoteClientImplementation("localhost", 1099, new TUI()), new GameModel());
            gameController.getGame().setCurrPlayer(player);

            TileReplyMessage message = gameController.pickTile(5,5);
            Assert.assertEquals(message.getMessageEnumeration(), MessageEnumeration.TILE_REPLY);
            Assert.assertNull(message.getNickname());
            Assert.assertEquals(message.isTileAccepted(), PickTileResponse.INVALID_TILE);

            message = gameController.pickTile(1,3);
            Assert.assertEquals(message.getMessageEnumeration(), MessageEnumeration.TILE_REPLY);
            Assert.assertNull(message.getNickname());
            Assert.assertEquals(message.isTileAccepted(), PickTileResponse.CORRECT_TILE);

            message = gameController.pickTile(1,4);
            Assert.assertEquals(message.getMessageEnumeration(), MessageEnumeration.TILE_REPLY);
            Assert.assertNull(message.getNickname());
            Assert.assertEquals(message.isTileAccepted(), PickTileResponse.CORRECT_TILE);

            message = gameController.pickTile(1,5);
            Assert.assertEquals(message.getMessageEnumeration(), MessageEnumeration.TILE_REPLY);
            Assert.assertNull(message.getNickname());
            Assert.assertEquals(message.isTileAccepted(), PickTileResponse.MAX_TILE_PICKED);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }




    }

    @Test
    public void testPickTileV2(){
        GameModel gameModel = new GameModel();
        try {
            gameModel.addObserver(new RemoteClientImplementation("localhost", 1099, new TUI()));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        gameModel.setChat("lorenzo1", "ciao","io");
        GameController gameController = new GameController(gameModel);
        gameController.getGameBoardController().setPlayerNum(2);
        gameController.initGameBoard();

        try {
            gameController.getGameBoardController().getControlledLivingRoom().addObserver(new RemoteClientImplementation("localhost", 1099, new TUI()));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        try {
            Player player = new Player("lorenzo", new RemoteClientImplementation("localhost", 1099, new TUI()), new GameModel());
            gameController.getGame().setCurrPlayer(player);

            ItemTile[][] structure = gameController.getGameBoardController().getControlledGameBoard().getLivingRoom().getGameTable();
            for(int i=0; i<9; i++){
                for(int j=0; j<9; j++){
                    if(structure[i][j] != null){
                        if(!structure[i][j].nullDetection()){
                            structure[i][j] = null;
                        }
                    }
                }
            }

            structure[1][3] = new PlayableItemTile("GREEN", 12);
            structure[2][3] = new PlayableItemTile("BLUE", 26);

            gameController.getGameBoardController().getControlledGameBoard().getLivingRoom().setGameTable(structure);
            gameController.getGameBoardController().getControlledLivingRoom().updateAvailability();


            TileReplyMessage message = gameController.pickTile(1,3);
            gameController.getGameBoardController().getControlledLivingRoom().updateAdjacentAvailabilityV1(1,3);
            Assert.assertEquals(message.getMessageEnumeration(), MessageEnumeration.TILE_REPLY);
            Assert.assertNull(message.getNickname());
            Assert.assertEquals(message.isTileAccepted(), PickTileResponse.CORRECT_TILE);

            Assert.assertEquals(1, gameController.getGameBoardController().getControlledLivingRoom().getAvailableTiles().size());


            message = gameController.pickTile(2,3);
            gameController.getGameBoardController().getControlledLivingRoom().updateAdjacentAvailabilityV1(1,3);
            Assert.assertEquals(message.getMessageEnumeration(), MessageEnumeration.TILE_REPLY);
            Assert.assertNull(message.getNickname());
            Assert.assertEquals(message.isTileAccepted(), PickTileResponse.CORRECT_TILE);

            Assert.assertEquals(0, gameController.getGameBoardController().getControlledLivingRoom().getAvailableTiles().size());

            message = gameController.pickTile(1,5);
            Assert.assertEquals(message.getMessageEnumeration(), MessageEnumeration.TILE_REPLY);
            Assert.assertNull(message.getNickname());
            Assert.assertEquals(message.isTileAccepted(), PickTileResponse.MAX_TILE_PICKED);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }




    }

    @Test
    public void testPickTileV3(){
        GameModel gameModel = new GameModel();
        try {
            gameModel.addObserver(new RemoteClientImplementation("localhost", 1099, new TUI()));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        GameController gameController = new GameController(gameModel);
        gameController.getGameBoardController().setPlayerNum(2);
        gameController.initGameBoard();

        try {
            gameController.getGameBoardController().getControlledLivingRoom().addObserver(new RemoteClientImplementation("localhost", 1099, new TUI()));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }


        try {
            Player player = new Player("lorenzo", new RemoteClientImplementation("localhost", 1099, new TUI()), new GameModel());
            gameController.getGame().setCurrPlayer(player);

            ItemTile[][] structure = gameController.getGameBoardController().getControlledGameBoard().getLivingRoom().getGameTable();
            for(int i=0; i<9; i++){
                for(int j=0; j<9; j++){
                    if(structure[i][j] != null){
                        if(!structure[i][j].nullDetection()){
                            structure[i][j] = null;
                        }
                    }
                }
            }

            structure[1][3] = new PlayableItemTile("GREEN", 12);
            structure[5][3] = new PlayableItemTile("BLUE", 26);

            gameController.getGameBoardController().getControlledGameBoard().getLivingRoom().setGameTable(structure);
            gameController.getGameBoardController().getControlledLivingRoom().updateAvailability();

            PlayableItemTile[][] structure2 = gameController.getGame().getCurrPlayer().getPersonalShelf().getStructure();

            for(int i=0; i<6; i++){
                for(int j=0; j<5; j++){
                    structure2[i][j] = new PlayableItemTile("GREEN", 14);
                }
            }

            gameController.getGame().getCurrPlayer().getPersonalShelf().setStructure(structure2);


            TileReplyMessage message = gameController.pickTile(1,3);
            Assert.assertEquals(message.getMessageEnumeration(), MessageEnumeration.TILE_REPLY);
            Assert.assertNull(message.getNickname());
            Assert.assertEquals(message.isTileAccepted(), PickTileResponse.MAX_TILE_PICKED);



        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }




    }


    @Test
    public void testPickTileV4(){
        GameModel gameModel = new GameModel();
        try {
            gameModel.addObserver(new RemoteClientImplementation("localhost", 1099, new TUI()));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        GameController gameController = new GameController(gameModel);
        gameController.getGameBoardController().setPlayerNum(2);
        gameController.initGameBoard();

        try {
            gameController.getGameBoardController().getControlledLivingRoom().addObserver(new RemoteClientImplementation("localhost", 1099, new TUI()));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        try {
            Player player = new Player("lorenzo", new RemoteClientImplementation("localhost", 1099, new TUI()), new GameModel());
            gameController.getGame().setCurrPlayer(player);

            ItemTile[][] structure = gameController.getGameBoardController().getControlledGameBoard().getLivingRoom().getGameTable();
            for(int i=0; i<9; i++){
                for(int j=0; j<9; j++){
                    if(structure[i][j] != null){
                        if(!structure[i][j].nullDetection()){
                            structure[i][j] = null;
                        }
                    }
                }
            }

            structure[1][3] = new PlayableItemTile("GREEN", 12);
            structure[2][3] = new PlayableItemTile("BLUE", 26);
            structure[3][3] = new PlayableItemTile("GREEN", 20);
            structure[5][3] = new PlayableItemTile("BLUE", 26);


            gameController.getGameBoardController().getControlledGameBoard().getLivingRoom().setGameTable(structure);
            gameController.getGameBoardController().getControlledLivingRoom().updateAvailability();

            TileReplyMessage message = gameController.pickTile(1,3);
            Assert.assertEquals(message.getMessageEnumeration(), MessageEnumeration.TILE_REPLY);
            Assert.assertNull(message.getNickname());
            Assert.assertEquals(message.isTileAccepted(), PickTileResponse.CORRECT_TILE);

            message = gameController.pickTile(2,3);
            Assert.assertEquals(message.getMessageEnumeration(), MessageEnumeration.TILE_REPLY);
            Assert.assertNull(message.getNickname());
            Assert.assertEquals(message.isTileAccepted(), PickTileResponse.CORRECT_TILE);

            message = gameController.pickTile(3,3);
            Assert.assertEquals(message.getMessageEnumeration(), MessageEnumeration.TILE_REPLY);
            Assert.assertNull(message.getNickname());
            Assert.assertEquals(message.isTileAccepted(), PickTileResponse.CORRECT_TILE);

            message = gameController.pickTile(5,3);
            Assert.assertEquals(message.getMessageEnumeration(), MessageEnumeration.TILE_REPLY);
            Assert.assertNull(message.getNickname());
            Assert.assertEquals(message.isTileAccepted(), PickTileResponse.MAX_TILE_PICKED);

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }




    }

    @Test
    public void testCalculateWinner(){
        GameModel gameModel = new GameModel();
        try {
            gameModel.addObserver(new RemoteClientImplementation("localhost", 1099, new TUI()));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        GameController gameController = new GameController(gameModel);

        try {
            Player player1 = new Player("lorenzo", new RemoteClientImplementation("localhost", 1099, new TUI()), new GameModel());
            Player player2 = new Player("lorenzo", new RemoteClientImplementation("localhost", 1099, new TUI()), new GameModel());
            Player player3 = new Player("lorenzo", new RemoteClientImplementation("localhost", 1099, new TUI()), new GameModel());
            Player player4 = new Player("lorenzo", new RemoteClientImplementation("localhost", 1099, new TUI()), new GameModel());

            ArrayList<Player> playerArrayList = new ArrayList<>();

            playerArrayList.add(player1);
            playerArrayList.add(player2);
            playerArrayList.add(player3);
            playerArrayList.add(player4);

            gameModel.setChairOwner(player1);


            player1.setPoints(15);
            player2.setPoints(10);
            player3.setPoints(9);
            player4.setPoints(18);

            gameController.CalculateWinner(playerArrayList);

            Assert.assertEquals(player4.getNickname(), gameModel.getMatchWinner());

            player1.setPoints(15);
            player2.setPoints(15);
            player3.setPoints(15);
            player4.setPoints(15);

            gameController.CalculateWinner(playerArrayList);

            Assert.assertEquals(player4.getNickname(), gameModel.getMatchWinner());

            player1.setPoints(15);
            player2.setPoints(15);
            player3.setPoints(15);
            player4.setPoints(15);

            gameModel.setChairOwner(player2);
            gameController.CalculateWinner(playerArrayList);

            Assert.assertEquals(player1.getNickname(), gameModel.getMatchWinner());


        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    public void testNextTurn(){
        GameModel model = new GameModel();
        GameController controller = new GameController(model);

        try {
            Player player1 = new Player("lorenzo", new RemoteClientImplementation("localhost", 1099, new TUI()), model);
            Player player2 = new Player("lorenzo1", new RemoteClientImplementation("localhost", 1099, new TUI()), model);
            Player player3 = new Player("lorenzo2", new RemoteClientImplementation("localhost", 1099, new TUI()), model);
            Player player4 = new Player("lorenzo3", new RemoteClientImplementation("localhost", 1099, new TUI()), model);

            model.setPlayersInGame(player1);
            model.setPlayersInGame(player2);
            model.setPlayersInGame(player3);
            model.setPlayersInGame(player4);
            model.getPlayersInGame().get(0).setPoints(15);
            model.getPlayersInGame().get(1).setPoints(10);
            model.getPlayersInGame().get(2).setPoints(9);
            model.getPlayersInGame().get(3).setPoints(6);


            model.setChairOwner(player2);
            model.setCurrPlayer(player1);
            controller.nextTurn();
            Assert.assertEquals(player2, model.getCurrPlayer());

            model.setCurrPlayer(player4);
            controller.nextTurn();
            Assert.assertEquals(player1, model.getCurrPlayer());

            model.setEndGame();
            model.setCurrPlayer(player4);
            controller.nextTurn();
            Assert.assertEquals(player1, model.getCurrPlayer());

            model.setCurrPlayer(player3);
            controller.nextTurn();
            Assert.assertEquals(player4, model.getCurrPlayer());

            model.setCurrPlayer(player1);
            controller.nextTurn();
            Assert.assertEquals("lorenzo", model.getMatchWinner());




        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    public void testPutTile1(){
        GameModel gameModel = new GameModel();

        GameController gameController = new GameController(gameModel);
        gameController.getGameBoardController().setPlayerNum(2);
        gameController.initGameBoard();
        try {
            Player player = new Player("lorenzo", new RemoteClientImplementation("localhost", 1099, new TUI()), gameModel);
            RandPersonalGoal.setType(player, new ArrayList<>());

            gameModel.setCurrPlayer(player);
            gameModel.setPlayersInGame(player);

            PlayableItemTile[][] structure = new PlayableItemTile[6][5];
            for(int i=0; i<6; i++){
                for(int j=0; j<5; j++){
                    structure[i][j] = new PlayableItemTile("GREEN", 15);
                }
            }

            gameModel.getCurrPlayer().getPersonalShelf().setStructure(structure);

            InsertionReplyMessage message = gameController.putTile(3,3, new PlayableItemTile("BLUE", 6), 1);
            Assert.assertNull(message.getNickname());
            Assert.assertFalse(message.isValid());
            Assert.assertFalse(message.isLastTurn());

            structure = new PlayableItemTile[6][5];
            for(int i=0; i<6; i++){
                for(int j=0; j<5; j++){
                    structure[i][j] = new PlayableItemTile("GREEN", 15);
                }
            }

            structure[0][1] = new PlayableItemTile("VOID", -1);
            gameModel.getCurrPlayer().getPersonalShelf().setStructure(structure);

            message = gameController.putTile(0,1, new PlayableItemTile("BLUE", 6), 1);
            Assert.assertNull(message.getNickname());
            Assert.assertTrue(gameModel.getEndGame());
            Assert.assertTrue(message.isValid());
            Assert.assertTrue(message.isLastTurn());

            structure[0][1] = new PlayableItemTile("VOID", -1);
            gameModel.getCurrPlayer().getPersonalShelf().setStructure(structure);
            message = gameController.putTile(0,1, new PlayableItemTile("BLUE", 6), 1);
            Assert.assertNull(message.getNickname());
            Assert.assertTrue(message.isValid());
            Assert.assertTrue(message.isLastTurn());

            gameModel.resetEndgame();
            structure[0][1] = new PlayableItemTile("VOID", -1);
            structure[0][2] = new PlayableItemTile("VOID", -1);
            gameModel.getCurrPlayer().getPersonalShelf().setStructure(structure);
            message = gameController.putTile(0,2, new PlayableItemTile("BLUE", 6), 1);
            Assert.assertNull(message.getNickname());
            Assert.assertTrue(message.isValid());
            Assert.assertFalse(message.isLastTurn());

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testPutTileV2(){
        GameModel gameModel = new GameModel();

        GameController gameController = new GameController(gameModel);
        gameController.getGameBoardController().setPlayerNum(2);
        gameController.initGameBoard();
        try {
            Player player = new Player("lorenzo", new RemoteClientImplementation("localhost", 1099, new TUI()), gameModel);
            RandPersonalGoal.setType(player, new ArrayList<>());

            gameModel.setCurrPlayer(player);
            gameModel.setPlayersInGame(player);
            gameModel.getCurrPlayer().getPersonalShelf().setColumnChosen(0);

            PlayableItemTile[][] structure = new PlayableItemTile[6][5];
            for(int i=0; i<6; i++){
                for(int j=0; j<5; j++){
                    structure[i][j] = new PlayableItemTile("GREEN", 15);
                }
            }

            gameModel.getCurrPlayer().getPersonalShelf().setStructure(structure);

            InsertionReplyMessage message = gameController.putTile(3, new PlayableItemTile("BLUE", 6), 1);
            Assert.assertNull(message.getNickname());
            Assert.assertFalse(message.isValid());
            Assert.assertFalse(message.isLastTurn());


            structure = new PlayableItemTile[6][5];
            for(int i=0; i<6; i++){
                for(int j=0; j<5; j++){
                    structure[i][j] = new PlayableItemTile("GREEN", 15);
                }
            }

            structure[0][0] = new PlayableItemTile("VOID", -1);
            gameModel.getCurrPlayer().getPersonalShelf().setStructure(structure);

            message = gameController.putTile(0, new PlayableItemTile("BLUE", 6), 1);
            Assert.assertNull(message.getNickname());
            Assert.assertTrue(gameModel.getEndGame());
            Assert.assertTrue(message.isValid());
            Assert.assertTrue(message.isLastTurn());

            structure[0][0] = new PlayableItemTile("VOID", -1);
            gameModel.getCurrPlayer().getPersonalShelf().setStructure(structure);
            message = gameController.putTile(0, new PlayableItemTile("BLUE", 6), 1);
            Assert.assertNull(message.getNickname());
            Assert.assertTrue(message.isValid());
            Assert.assertTrue(message.isLastTurn());

            gameModel.resetEndgame();
            structure[0][0] = new PlayableItemTile("VOID", -1);
            structure[1][0] = new PlayableItemTile("VOID", -1);
            gameModel.getCurrPlayer().getPersonalShelf().setStructure(structure);
            message = gameController.putTile(1, new PlayableItemTile("BLUE", 6), 1);
            Assert.assertNull(message.getNickname());
            Assert.assertTrue(message.isValid());
            Assert.assertFalse(message.isLastTurn());


        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
