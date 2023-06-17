package server.Controller;
import Network.ServerSide.RemoteServerImplementation;
import Network.message.*;
import Util.Colour;
import Util.RandCommonGoal;

import client.view.TurnView;
import server.Model.*;
import server.enumerations.PickTileResponse;
import java.rmi.RemoteException;
import java.util.*;

/**
 * This Class is the controller for the {@link GameBoard GameModel} and also for the {@link GameBoardController GameBoardController}.
 * It also has a bind with the {@link RemoteServerImplementation RemoteServer} that runs the game.
 */
public class GameController {

    private final GameModel game;
    private final GameBoardController gameBoardController; // gameBoardController è il tramite tra GameController e le classi GameBoard, LivingRoom e ItemBag
    boolean moveOn = false;
    boolean full = false;


    /**
     * Creates an instance of {@link GameController GameController} binding it with {@link GameModel GameModel} which is the main Class of the Model of the Game.
     * Also creates a new {@link GameBoardController GameBoardController}.
     *
     * @param game the instance of {@link GameModel GameController} to bind
     */
    public GameController(GameModel game) {
        this.game = game;
        this.gameBoardController = new GameBoardController(this);
    }

    /**
     *
     * @return the controlled {@link GameModel GameModel}
     */
    public GameModel getGame() {
        return this.game;
    }

    /**
     * Sets to {@code true} the variable {@code full} of the {@link GameController GameController}
     */
    public void setFull() {
        this.full = true;
    }


    /**
     *
     * @return the corresponding {@link GameBoardController GameBoardController}
     */
    public GameBoardController getGameBoardController() {
        return this.gameBoardController;
    }


    /**
     * Method called when the number of {@link Player Players} has reached the requested number.
     * Creates a {@link GameBoard GameBoard}, binds it with a {@link LivingRoom LivingRoom}
     * and sets the {@link CommonGoal CommonGoals} for the game.
     */
    public void initGameBoard() {
        gameBoardController.gameBoardInit();  //inizializza itemBag e livingRoom
        game.setMyShelfie(gameBoardController.getControlledGameBoard());
        RandCommonGoal.setType(game.getMyShelfie().getLivingRoom().getCommonGoal1(), game.getMyShelfie().getLivingRoom().getCommonGoal2(), 0, 0);
        game.getMyShelfie().getLivingRoom().getCommonGoal1().setTokens(game.getPlayersNumber());
        game.getMyShelfie().getLivingRoom().getCommonGoal2().setTokens(game.getPlayersNumber());

    }

    /**
     * Method to pick a {@link PlayableItemTile PlayableItemTile} from the {@link LivingRoom LivingRoom}
     *
     * @param x the 'x' position of the {@link PlayableItemTile PlayableItemTile} to pick
     * @param y the 'y' position of the {@link PlayableItemTile PlayableItemTile} to pick
     * @return a {@link TileReplyMessage TileReplyMessage} containing the results of the procedure. The results could be the following:
     * (1) if the field {@code tile} in {@link TileReplyMessage TileReplyMessage} is {@code null} the procedure failed:
     * if the field {@link PickTileResponse PickTileResponse} in {@link TileReplyMessage TileReplyMessage} is {@code MAX_TILE_PICKED} it means that the {@link Player Player}
     * has already picked the maximum number of {@link PlayableItemTile PlayableItemTiles} for this turn,
     * otherwise if the field {@link PickTileResponse PickTileResponse} in {@link TileReplyMessage TileReplyMessage} is {@code INVALID_TILE} it means that the {@link Player Player}
     * tried to pick an invalid {@link ItemTile ItemTile} and the pick procedure needs to be redone.
     * (2) if the field {@code tile} in {@link TileReplyMessage TileReplyMessage} is not {@code null} the procedure has been completed correctly.
     */
    public TileReplyMessage pickTile(int x, int y) {
        PlayableItemTile tile;
        moveOn = false;
        full=false;

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
                            gameBoardController.getControlledLivingRoom().notifyObservers(obs -> {
                                try {
                                    obs.onUpdateRefillLivingRoom(new TurnView(game));
                                } catch (RemoteException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                            gameBoardController.getControlledLivingRoom().updateAvailability();
                        }
                        gameBoardController.getControlledGameBoard().getToPlayerTiles().clear();
                        return new TileReplyMessage(null, PickTileResponse.MAX_TILE_PICKED);
                    } else {
                        return new TileReplyMessage(null, PickTileResponse.INVALID_TILE);
                    }
                } else {
                    return new TileReplyMessage(tile, PickTileResponse.CORRECT_TILE);
                }
            } else {
                gameBoardController.getControlledLivingRoom().updateAvailability();
                if (!gameBoardController.checkIfAdjacentTiles()) {
                    gameBoardController.livingRoomFiller();
                    gameBoardController.getControlledLivingRoom().notifyObservers(obs -> {
                        try {
                            obs.onUpdateRefillLivingRoom(new TurnView(game));
                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    gameBoardController.getControlledLivingRoom().updateAvailability();
                }
                gameBoardController.getControlledGameBoard().getToPlayerTiles().clear();
                return new TileReplyMessage(null, PickTileResponse.MAX_TILE_PICKED);
            }
        } else {
            gameBoardController.getControlledLivingRoom().updateAvailability();
            if (!gameBoardController.checkIfAdjacentTiles()) {
                gameBoardController.livingRoomFiller();
                gameBoardController.getControlledLivingRoom().notifyObservers(obs -> {
                    try {
                        obs.onUpdateRefillLivingRoom(new TurnView(game));
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                });
                gameBoardController.getControlledLivingRoom().updateAvailability();
            }
            gameBoardController.getControlledGameBoard().getToPlayerTiles().clear();
            return new TileReplyMessage(null, PickTileResponse.MAX_TILE_PICKED);
        }


    }

    /**
     * Method to put the first {@link PlayableItemTile PlayableItemTile} in the {@link Shelf Shelf}
     *
     * @param xPos the 'x' position of the {@link PlayableItemTile PlayableItemTile} to put
     * @param yPos the 'y' position of the {@link PlayableItemTile PlayableItemTile} to put
     * @param selectedTile the {@link PlayableItemTile PlayableItemTile} to put
     * @param numOfTilesOnPutting the number of {@link PlayableItemTile PlayableItemTiles} to put during this turn
     *
     * @return a {@link InsertionReplyMessage InsertionReplyMessage} containing the results of the procedure. The results could be the
     * following:
     * (1) if the field {@code isValid} is {@code false} then the procedure has failed.
     * (2) if the field {@code isValid} is {@code true} the procedure has been successful.
     */

    public InsertionReplyMessage putTile(int xPos, int yPos, PlayableItemTile selectedTile, int numOfTilesOnPutting) {

        if (RuleShelf.isColumnAvailable(yPos, numOfTilesOnPutting, game.getCurrPlayer().getPersonalShelf().getStructure()) &&
                RuleShelf.commandPutTileCheckValidity(xPos, yPos,game.getCurrPlayer().getPersonalShelf().getStructure())) {

            game.getCurrPlayer().getPersonalShelf().putTile(xPos, yPos, selectedTile);
            Player currPlayer = game.getCurrPlayer();
            LivingRoom livingRoom = game.getMyShelfie().getLivingRoom();
            calculatePoint(currPlayer, livingRoom);
            boolean shelfIsFull = game.getCurrPlayer().getPersonalShelf().isFull();
            game.getCurrPlayer().getPersonalShelf().setColumnChosen(yPos);

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
        else {
            return new InsertionReplyMessage(false, false);
        }
    }

    /**
     * Method to put the next {@link PlayableItemTile PlayableItemTiles} in the {@link Shelf Shelf}
     *
     * @param xPos the 'x' position of the {@link PlayableItemTile PlayableItemTile} to put
     * @param selectedTile the {@link PlayableItemTile PlayableItemTile} to put
     * @param numOfTilesOnPutting the number of {@link PlayableItemTile PlayableItemTiles} to put during this turn
     *
     * @return a {@link InsertionReplyMessage InsertionReplyMessage} containing the results of the procedure. The results could be the
     * following:
     * (1) if the field {@code isValid} is {@code false} then the procedure has failed.
     * (2) if the field {@code isValid} is {@code true} the procedure has been successful.
     */
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


    /**
     * Method that determines the beginning of a new game turn and sets the new {@code currPlayer}
     */
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
                if (game.getPlayersInGame().indexOf(game.getCurrPlayer()) == game.getPlayersInGame().size() - 1) {
                    game.setCurrPlayer(listPLayer.get(0));
                } else game.setCurrPlayer(listPLayer.get(index + 1));
            }

        }


    }

    /**
     * Method that determines the winner of the game and sets the new {@code matchWinner}.
     * The winner is the {@link Player Player} with more points.
     * If there is more than one {@link Player Player} with the highest total of points they are put in an {@link ArrayList ArrayList},
     * the method chooses from this {@link ArrayList ArrayList} the matchWinner by finding the farthest {@link Player Player} from the {@code chairOwner} according
     * to the order in which the {@link Player Players} are inside the {@code playerArrayList}.
     *
     * @param playerArrayList the {@link ArrayList ArrayList} of the {@link Player Players} in the game
     */
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

    /**
     * Static method to find the dimensions of the groups of adjacent {@link PlayableItemTile PlayableItemTiles} of the same {@link Colour Colour}
     * in the {@link Shelf Shelf}
     *
     * @param shelf the {@link Shelf Shelf} on which the method will search the groups of adjacent {@link PlayableItemTile PlayableItemTiles}
     * @return an {@link HashMap Hashmap} in which the keys are the possible {@link Colour Colours} of the {@link PlayableItemTile PlayableItemTiles}
     * and the elements associated to the keys, which are stored in {@link ArrayList ArrayLists}, are counters which indicates the size of the group/groups of
     * adjacent {@link PlayableItemTile PlayableItemTiles}
     */
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
                if (shelf[i][j].getColour()!=Colour.VOID) {
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

    private static int findAdjGroupDim(PlayableItemTile[][] structure, boolean[][] visited, int i, int j, Colour colour, ArrayList<Integer> dimension) {
        if (i < 0 || i >= structure.length || j < 0 || j >= structure[0].length || structure[i][j] == null || visited[i][j] || structure[i][j].getColour() != colour) {
            return 0;
        }

        visited[i][j] = true;
        int dimensione = 1;

        for (Integer d : dimension) {
            if (d != null && d == dimensione) {
                dimensione += findAdjGroupDim(structure, visited, i - 1, j, colour, dimension); // Alto
                dimensione += findAdjGroupDim(structure, visited, i + 1, j, colour, dimension); // Basso
                dimensione += findAdjGroupDim(structure, visited, i, j - 1, colour, dimension); // Sinistra
                dimensione += findAdjGroupDim(structure, visited, i, j + 1, colour, dimension); // Destra
                return dimensione;
            }
        }

        dimension.add(dimensione);
        dimensione += findAdjGroupDim(structure, visited, i - 1, j, colour, dimension); // Alto
        dimensione += findAdjGroupDim(structure, visited, i + 1, j, colour, dimension); // Basso
        dimensione += findAdjGroupDim(structure, visited, i, j - 1, colour, dimension); // Sinistra
        dimensione += findAdjGroupDim(structure, visited, i, j + 1, colour, dimension); // Destra
        return dimensione;
    }

    private Integer AddAdjacencyPoint(HashMap<Colour, ArrayList<Integer>> adjacencyGroups) {
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
                } else if (integer >= 6) {
                    point = point + 8;
                }
            }
        }

        return point;
    }

    /**
     * This method computes the new amount of points to award to a {@link Player Player}.
     * The checks are done on the completion status of the {@link Player Player} {@link PersonalGoal PersonalGoal}, of the {@link CommonGoal CommonGoals}
     * and of the presence of groups of adjacent {@link PlayableItemTile PlayableItemTiles} of the same {@link Colour Colour}.
     *
     * @param player the {@link Player Player} to whom the points will be awarded
     * @param livingRoom the {@link LivingRoom LivingRoom} on which the checks will be made
     */
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

    /**
     *
     *
     * @param commonGoal the {@link CommonGoal CommonGoal} which has been completed
     * @return the amount of points awarded to the {@link Player Player} that has completed it.
     * This value depends on the number of {@link Player Players} that already completed such {@link CommonGoal CommonGoal}.
     */
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

