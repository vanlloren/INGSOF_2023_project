package server.Controller;
import Network.ServerSide.RemoteServerImplementation;
import Network.message.*;
import Util.Colour;
import Util.RandCommonGoal;

import server.Model.*;
import server.enumerations.PickTileResponse;


import java.util.*;

public class GameController {

    private final GameModel game;
    //  private boolean timeOut;
    private final GameBoardController gameBoardController; // gameBoardController è il tramite tra GameController e le classi GameBoard, LivingRoom e ItemBag

    private RemoteServerImplementation remoteServer;
    boolean moveOn = false;
    boolean full = false;



    public GameController(GameModel game) {
        this.game = game;
        this.gameBoardController = new GameBoardController(this);
    }

    public GameModel getGame() {
        return this.game;
    }

    public void setRemoteServer(RemoteServerImplementation remoteServer) {
        this.remoteServer = remoteServer;
    }

    public void setFull() {
        this.full = true;
    }



    public GameBoardController getGameBoardController() {
        return this.gameBoardController;
    }


    /*
    the following method is called when the number of player has reached the requested number
    and as a consequence a game board with the right livingRoom (based on numOfPlayers) is created and the commonGoals are being set and prepared for the game
     */
    public void initGameBoard() {
        gameBoardController.gameBoardInit();  //inizializza itemBag e livingRoom
        game.setMyShelfie(gameBoardController.getControlledGameBoard());
        RandCommonGoal.setType(game.getMyShelfie().getLivingRoom().getCommonGoal1(), game.getMyShelfie().getLivingRoom().getCommonGoal2());
        game.getMyShelfie().getLivingRoom().getCommonGoal1().setTokens(game.getPlayersNumber());
        game.getMyShelfie().getLivingRoom().getCommonGoal2().setTokens(game.getPlayersNumber());

    }


    public TileReplyMessage pickTile(int x, int y) {  //restituisce le 1/2/3 tiles prese dalla livingRoom dal player nel suo turno
        PlayableItemTile tile;
        moveOn = false;


        //controllo su max 3 pick
        if (gameBoardController.checkPickedTilesNum()) {
            //controllo su max numero caselle libere shelf
            if (gameBoardController.getControlledGameBoard().getPickedTilesNum() < getGame().getCurrPlayer().getMaxTiles()) {
                tile = gameBoardController.PickManager(x, y);
                if (tile == null) {
                    if (full) {
                        gameBoardController.getControlledLivingRoom().updateAvailability();
                        if (!gameBoardController.checkIfAdjacentTiles()) {
                            gameBoardController.livingRoomFiller();
                            gameBoardController.getControlledLivingRoom().updateAvailability();
                        }
                        gameBoardController.toPlayerTilesResetter();
                        return new TileReplyMessage(null, PickTileResponse.MAX_TILE_PICKED);
                    } else {
                        return new TileReplyMessage(tile, PickTileResponse.INVALID_TILE);
                    }
                } else {
                    return new TileReplyMessage(tile, PickTileResponse.CORRECT_TILE);
                }
            } else {
                gameBoardController.getControlledLivingRoom().updateAvailability();
                if (!gameBoardController.checkIfAdjacentTiles()) {
                    gameBoardController.livingRoomFiller();
                    gameBoardController.getControlledLivingRoom().updateAvailability();
                }
                gameBoardController.toPlayerTilesResetter();
                return new TileReplyMessage(null, PickTileResponse.MAX_TILE_PICKED);
            }
        } else {
            gameBoardController.getControlledLivingRoom().updateAvailability();
            if (!gameBoardController.checkIfAdjacentTiles()) {
                gameBoardController.livingRoomFiller();
                gameBoardController.getControlledLivingRoom().updateAvailability();
            }
            gameBoardController.toPlayerTilesResetter();
            return new TileReplyMessage(null, PickTileResponse.MAX_TILE_PICKED);
        }


    }

    public InsertionReplyMessage putTile(int xPos, int yPos, PlayableItemTile selectedTile, int numOfTilesOnPutting) {

        int x = xPos;
        int y = yPos;
        PlayableItemTile tile = selectedTile;

        int numOfTiles = numOfTilesOnPutting;

        if (RuleShelf.iscolumnAvailable(y, numOfTiles, game.getCurrPlayer().getPersonalShelf().getStructure()) &&
                RuleShelf.commandPutTileCheckValidity(x, y,game.getCurrPlayer().getPersonalShelf().getStructure())) {

            game.getCurrPlayer().getPersonalShelf().putTile(x, y, tile);
            Player currPlayer = game.getCurrPlayer();
            LivingRoom livingRoom = game.getMyShelfie().getLivingRoom();
            calculatePoint(currPlayer, livingRoom);
            boolean shelfIsFull = game.getCurrPlayer().getPersonalShelf().isFull();
            game.getCurrPlayer().getPersonalShelf().setColumnChosen(y);

            if (shelfIsFull && !game.getEndGame()) {
                game.setEndGame();
                if(numOfTilesOnPutting==1) {
                    nextTurn();
                }
                return new InsertionReplyMessage(true, true);
            }  if (game.getEndGame()) {
                if(numOfTilesOnPutting==1) {
                    nextTurn();
                }
                return new InsertionReplyMessage(true, true);
            } else{
                if(numOfTilesOnPutting==1) {
                    nextTurn();
                }
                return new InsertionReplyMessage(true, false);
            }
        }
        return new InsertionReplyMessage(false, false);
    }


    public InsertionReplyMessage putTile(int xPos, PlayableItemTile selectedTile, int numOfTilesOnPutting) {

        int yPos = game.getCurrPlayer().getPersonalShelf().getColumnChosen();

        if (RuleShelf.commandPutTileCheckValidity(xPos, yPos, game.getCurrPlayer().getPersonalShelf().getStructure())) {
            game.getCurrPlayer().getPersonalShelf().putTile(xPos, yPos, selectedTile);
            Player currPlayer = game.getCurrPlayer();
            LivingRoom livingRoom = game.getMyShelfie().getLivingRoom();
            calculatePoint(currPlayer, livingRoom);
            boolean shelfIsFull = game.getCurrPlayer().getPersonalShelf().isFull();


            if (shelfIsFull && !game.getEndGame()) {
                game.setEndGame();
                if(numOfTilesOnPutting==1) {
                    nextTurn();
                }
                return new InsertionReplyMessage(true, true);
            }  if (game.getEndGame()) {
                if(numOfTilesOnPutting==1) {
                    nextTurn();
                }
                return new InsertionReplyMessage(true, true);
            } else {
                if(numOfTilesOnPutting==1) {
                    nextTurn();
                }
                return new InsertionReplyMessage(true, false);
            }
        }


     return new InsertionReplyMessage(false,false);

}




    public void nextTurn() {

        ArrayList<Player> listPLayer = game.getPlayersInGame();

        int index = listPLayer.indexOf(game.getCurrPlayer());

        if (!game.getEndGame()) {
            if (game.getPlayersInGame().indexOf(game.getCurrPlayer()) == game.getPlayersInGame().size() - 1) {
                game.setCurrPlayer(listPLayer.get(0));
            } else game.setCurrPlayer(listPLayer.get(index + 1));
        } else {
            if (game.getPlayersInGame().indexOf(game.getCurrPlayer()) == game.getPlayersInGame().indexOf(game.getChairOwner()) - 1) {
                CalculateWinner(game.getPlayersInGame());
            } else {
                if (game.getPlayersInGame().indexOf(game.getCurrPlayer()) == game.getPlayersInGame().size()) {
                    game.setCurrPlayer(listPLayer.get(0));
                } else game.setCurrPlayer(listPLayer.get(index + 1));
            }

        }


    }

    public void CalculateWinner(ArrayList<Player> playerArrayList) {
        ArrayList<Integer> pointsList = new ArrayList<>();
        ArrayList<Player> Winners = new ArrayList<>();
        for (Player p : playerArrayList) {
            pointsList.add(p.getPoints());
        }
        Integer MaxPoint = Collections.max(pointsList);
        for (Player p : playerArrayList) {
            if (p.getPoints().equals(MaxPoint))
                Winners.add(p);
        }
        if (Winners.size() == 1) {
            game.setMatchWinner(Winners.get(0));
        } else
        {
            Player Winner;
            Player chairOwner = game.getChairOwner();
            int i;
            ArrayList<Integer> positions = new ArrayList<>();
            for (Player p : playerArrayList) {
                if (p.getPoints().equals(MaxPoint))
                    positions.add(playerArrayList.indexOf(p));
                i = positions.indexOf(playerArrayList.indexOf(chairOwner));
                if (i > 0) {
                    Winner = playerArrayList.get(positions.get(i - 1));
                } else {
                    Winner = playerArrayList.get(positions.get(positions.size() - 1));
                }
                game.setMatchWinner(Winner);

            }

        }
    }


    public static HashMap<Colour, ArrayList<Integer>> findAdjGroups(PlayableItemTile[][] shelf) {
        HashMap<Colour, ArrayList<Integer>> adjGroups = new HashMap<>();

        boolean[][] visited = new boolean[6][5];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                visited[i][j] = false;
            }
        }

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                if (shelf[i][j] != null) {
                    if (!visited[i][j]) {
                        Colour colore = shelf[i][j].getColour();
                        ArrayList<Integer> dimensioni = new ArrayList<>();
                        int dimension = findAdjGroupDim(shelf, visited, i, j, colore, dimensioni);


                        if (adjGroups.containsKey(colore)) {
                            adjGroups.get(colore).add(dimension);
                        } else {
                            ArrayList<Integer> nuovaLista = new ArrayList<>();
                            nuovaLista.add(dimension);
                            adjGroups.put(colore, nuovaLista);
                        }
                    }
                }
            }
        }

        return adjGroups;
    }

    public static int findAdjGroupDim(PlayableItemTile[][] structure, boolean[][] visitated, int i, int j, Colour colour, ArrayList<Integer> dimension) {
        if (i < 0 || i >= structure.length || j < 0 || j >= structure[0].length || structure[i][j] == null || visitated[i][j] || structure[i][j].getColour() != colour) {
            return 0;
        }

        visitated[i][j] = true;
        int dimensione = 1;

        for (Integer d : dimension) {
            if (d != null && d == dimensione) {
                dimensione += findAdjGroupDim(structure, visitated, i - 1, j, colour, dimension); // Alto
                dimensione += findAdjGroupDim(structure, visitated, i + 1, j, colour, dimension); // Basso
                dimensione += findAdjGroupDim(structure, visitated, i, j - 1, colour, dimension); // Sinistra
                dimensione += findAdjGroupDim(structure, visitated, i, j + 1, colour, dimension); // Destra
                return dimensione;
            }
        }

        dimension.add(dimensione);
        dimensione += findAdjGroupDim(structure, visitated, i - 1, j, colour, dimension); // Alto
        dimensione += findAdjGroupDim(structure, visitated, i + 1, j, colour, dimension); // Basso
        dimensione += findAdjGroupDim(structure, visitated, i, j - 1, colour, dimension); // Sinistra
        dimensione += findAdjGroupDim(structure, visitated, i, j + 1, colour, dimension); // Destra
        return dimensione;
    }

    public Integer AddAdjacencyPoint(HashMap<Colour, ArrayList<Integer>> adjacencyGroups) {
        Set<Colour> keys = adjacencyGroups.keySet();
        int point = 0;
        for (Colour colour : keys) {
            ArrayList<Integer> counter = adjacencyGroups.get(colour);
            for (Integer integer : counter) {
                if (integer == 3) {
                    point = point + 2;
                } else if (integer == 4) {
                    point = point + 3;
                } else if (integer == 5) {
                    point = point + 5;
                } else if (integer > 6) {
                    point = point + 8;
                }
            }
        }

        return point;
    }

    public void calculatePoint(Player player, LivingRoom livingRoom) {
        if (!player.getHasCommonGoal1() && CheckCommonGoal.checkGoal(player.getPersonalShelf(), livingRoom.getCommonGoal1().getCommonGoalType())) {
            Integer i;
            i = player.getPoints();
            i = i + addPoint(livingRoom.getCommonGoal1());
            player.setStatusCommonGoal1();
            player.setPoints(i);
        }

        if (!player.getHasCommonGoal2() && CheckCommonGoal.checkGoal(player.getPersonalShelf(), livingRoom.getCommonGoal2().getCommonGoalType())) {
            Integer i;
            i = player.getPoints();
            i = i + addPoint(livingRoom.getCommonGoal2());
            player.setStatusCommonGoal2();
            player.setPoints(i);
        }
        if (player.getPersonalGoal().getPoint() < CheckPersonalGoal.calculatePoints(player.getPersonalGoal(), player.getPersonalShelf().getStructure())) {
            Integer i;
            Integer x;
            Integer y;
            i = player.getPoints();
            x = CheckPersonalGoal.calculatePoints(player.getPersonalGoal(), player.getPersonalShelf().getStructure());
            y = player.getPersonalGoal().getPoint();
            i = i + x - y;
            player.getPersonalGoal().setPoint(x);
            player.setPoints(i);

        }
        if (player.getPersonalShelf().getPointsAdj() < AddAdjacencyPoint(findAdjGroups(player.getPersonalShelf().getStructure()))) {
            int i = AddAdjacencyPoint(findAdjGroups(player.getPersonalShelf().getStructure()));
            int x = player.getPoints();
            int y = player.getPersonalShelf().getPointsAdj();
            player.getPersonalShelf().setPointsAdj(i);
            x = x + i - y;
            player.setPoints(x);

        }
    }


    public Integer addPoint(CommonGoal commonGoal) {
        ArrayList<Integer> token_list = commonGoal.getToken_list();
        Integer i = 0;
        if (0 < token_list.size()) {
            i = token_list.get(token_list.size() - 1);
            token_list.remove(token_list.size() - 1);
        }
        return i;
    }
}

