package Network.ServerSide;


import Network.ClientSide.RemoteClientInterface;

import Network.message.*;
import Util.RandChairOwner;
import Util.RandCommonGoal;
import Util.RandPersonalGoal;
import server.Controller.GameController;
import server.Controller.RuleShelf;
import server.Model.LivingRoom;
import server.Model.PlayableItemTile;
import server.Model.Player;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;


public class RemoteServerImplementation extends UnicastRemoteObject implements RemoteServerInterface {
    private final RMIServer server;

    private final Object lock = new Object();
    private volatile boolean stop = false;
    private RemoteClientInterface client;

    private ArrayList<RemoteClientInterface> clientList = new ArrayList<>();

    private HashMap<String, RemoteClientInterface> clientNickCombinations;
    private final GameController gameController;
    private ArrayList<PlayableItemTile> availableTiles;

    RemoteServerImplementation(RMIServer server, GameController gameController) throws RemoteException {
        this.server = server;
        this.gameController = gameController;
    }

    public void resetStop() {
        stop = false;
    }

    @Override
    public void onMessage(Message message) throws RemoteException {

        switch (message.getMessageEnumeration()) {
            case LOGIN_REQUEST -> {
                synchronized (lock) {
                    if (gameController.getGame().getPlayersInGame().size() < 1) {
                        stop = true;
                        clientNickCombinations = new HashMap<>();
                        LoginRequestMessage newMessage = (LoginRequestMessage) message;
                        clientList.add(newMessage.getClient());
                        clientNickCombinations.put(message.getNickname(), newMessage.getClient());
                        Player newPlayer = new Player(message.getNickname(), newMessage.getClient(), gameController.getGame());
                        newPlayer.addObserver(newMessage.getClient());
                        gameController.getGame().addObserver(newMessage.getClient());
                        gameController.getGame().setPlayersInGame(newPlayer);
                        RandPersonalGoal.setType(newPlayer, gameController.getGame().getPlayersInGame());
                        Message newMessage2 = new PlayersNumberRequestMessage(message.getNickname());
                        newMessage.getClient().onMessage(newMessage2);



                        while (stop) {
                        }
                        gameController.initGameBoard();
                        gameController.getGame().getMyShelfie().getLivingRoom().addObserver(newMessage.getClient());
                    } else if (gameController.getGame().getPlayersInGame().size() < gameController.getGame().getPlayersNumber() - 1) {
                        boolean approvedNick = gameController.getGame().isNicknameAvailable(message.getNickname());
                        Message newMessage = new LoginReplyMessage(message.getNickname(), approvedNick);
                        if (approvedNick) {
                            LoginRequestMessage newMessage1 = (LoginRequestMessage) message;
                            clientList.add(newMessage1.getClient());
                            clientNickCombinations.put(message.getNickname(), newMessage1.getClient());
                            clientNickCombinations.get(message.getNickname()).onMessage(newMessage);
                            Player newPlayer = new Player(message.getNickname(), newMessage1.getClient(), gameController.getGame());
                            newPlayer.addObserver(newMessage1.getClient());
                            gameController.getGame().addObserver(newMessage1.getClient());
                            gameController.getGame().getMyShelfie().getLivingRoom().addObserver(newMessage1.getClient());
                            RandPersonalGoal.setType(newPlayer, gameController.getGame().getPlayersInGame());
                            gameController.getGame().setPlayersInGame(newPlayer);
                        }else {
                            LoginRequestMessage newMessage2 = (LoginRequestMessage) message;
                            newMessage2.getClient().onMessage(newMessage);
                        }
                    } else if (gameController.getGame().getPlayersInGame().size() == gameController.getGame().getPlayersNumber() - 1) {
                        boolean approvedNick = gameController.getGame().isNicknameAvailable(message.getNickname());
                        Message newMessage = new LoginReplyMessage(message.getNickname(), approvedNick);
                        if (approvedNick) {
                            LoginRequestMessage newMessage1 = (LoginRequestMessage) message;
                            clientList.add(newMessage1.getClient());
                            clientNickCombinations.put(message.getNickname(), newMessage1.getClient());
                            clientNickCombinations.get(message.getNickname()).onMessage(newMessage);
                            Player newPlayer = new Player(message.getNickname(), newMessage1.getClient(), gameController.getGame());
                            newPlayer.addObserver(newMessage1.getClient());
                            gameController.getGame().addObserver(newMessage1.getClient());
                            gameController.getGame().getMyShelfie().getLivingRoom().addObserver(newMessage1.getClient());
                            RandPersonalGoal.setType(newPlayer, gameController.getGame().getPlayersInGame());
                            gameController.getGame().setPlayersInGame(newPlayer);
                            gameController.getGame().setChairOwner(gameController.getGame().getPlayersInGame().get(RandChairOwner.ChooseRand(gameController.getGame().getPlayersNumber())));
                            gameController.getGame().setCurrPlayer(gameController.getGame().getChairOwner());
                        }else{
                            LoginRequestMessage newMessage2 = (LoginRequestMessage) message;
                            newMessage2.getClient().onMessage(newMessage);
                        }
                    } else if ((gameController.getGame().getPlayersInGame().size() == gameController.getGame().getPlayersNumber())) {
                        Message newMessage = new FullLobbyMessage();
                        LoginRequestMessage newMessage2 = (LoginRequestMessage) message;
                        newMessage2.getClient().onMessage(newMessage);
                    }
                }

            }
            case KEEP_PICKING_REPLY -> {
                KeepPickingReplyMessage newMessage = (KeepPickingReplyMessage) message;
                if (!newMessage.getKeepPicking()) {
                    gameController.setStopPicking();
                }
            }
            case TO_PICK_TILE_REPLY -> {
                ToPickTileReplyMessage newMessage = (ToPickTileReplyMessage) message;
                int x = newMessage.getXPos();
                int y = newMessage.getYPos();
                gameController.setPosCurrTile(x, y);
                gameController.setMoveOn();
            }
            case TO_PUT_TILE_REQUEST -> {
                ToPutTileRequestMessage newMessage = (ToPutTileRequestMessage) message;
                int x = newMessage.getX();
                int y = newMessage.getY();
                PlayableItemTile tile = newMessage.getTile();
                ArrayList<Integer> columnPosition = newMessage.getColumnPosition();
                int numOfTiles = newMessage.getNumOfTiles();

                /*---------------------------------------------*/
                if (RuleShelf.iscolumnAvailable(y, numOfTiles, gameController.getGame().getCurrPlayer().getPersonalShelf().getStructure())) {
                    if (RuleShelf.commandPutTileCheckValidity(x, y, tile, gameController.getGame().getCurrPlayer().getPersonalShelf().getStructure(), numOfTiles)) {
                        gameController.getGame().getCurrPlayer().getPersonalShelf().putTile(x, y, tile);
                        Player currPlayer = gameController.getGame().getCurrPlayer();
                        LivingRoom livingRoom = gameController.getGame().getMyShelfie().getLivingRoom();
                        gameController.calculatePoint(currPlayer, currPlayer.getPersonalShelf().getStructure(), livingRoom);
                        boolean shelfIsFull = gameController.getGame().getCurrPlayer().getPersonalShelf().isFull();
                        gameController.getGame().getCurrPlayer().getPersonalShelf().setColumnChosen(y);
                        ArrayList<PlayableItemTile> currentPlayableItemTile = newMessage.getPlayableItemTiles();
                        currentPlayableItemTile.remove(tile);
                        if (currentPlayableItemTile.size() > 0) {
                            ToKeepPuttingMessage newKeepPuttingMessage = new ToKeepPuttingMessage(currentPlayableItemTile);
                            client.onMessage(newKeepPuttingMessage);
                        } else {
                            if (shelfIsFull && !gameController.getGame().getEndGame()) {
                                //Sei il primo a settare endgame
                                gameController.getGame().setEndGame();
                                gameController.nextTurn();
                            } else if (gameController.getGame().getEndGame()) {
                                //Qua ultimo turno e te non sei il primo a settare l'endGame
                                gameController.nextTurn();
                            } else if (!gameController.getGame().getEndGame()) {
                                gameController.nextTurn();
                                clientNickCombinations.get(message.getNickname()).onMessage(newMessage);
                            }

                        }
                    }
                } else if (!RuleShelf.iscolumnAvailable(y, numOfTiles, gameController.getGame().getCurrPlayer().getPersonalShelf().getStructure()) || (RuleShelf.iscolumnAvailable(y, numOfTiles, gameController.getGame().getCurrPlayer().getPersonalShelf().getStructure()))
                        && !RuleShelf.commandPutTileCheckValidity(x, y, tile, gameController.getGame().getCurrPlayer().getPersonalShelf().getStructure(), numOfTiles)) {
                    ToPutTileReplyMessage newErrorMessage = new ToPutTileReplyMessage(newMessage.getPlayableItemTiles());
                    client.onMessage(newErrorMessage);
                }

            }

            case TO_PUT_TILE_2_OR_3_REQUEST -> {
                ToPut2Or3TileRequestMessage newMessage = (ToPut2Or3TileRequestMessage) message;
                int xPos = newMessage.getxPos();
                int yPos = gameController.getGame().getCurrPlayer().getPersonalShelf().getColumnChosen();
                PlayableItemTile tile = newMessage.getTile();
                ArrayList<PlayableItemTile> currentPlayableItemTile = newMessage.getCurrentPlayableItemTile();
                if (RuleShelf.commandPutTileCheckValidity(xPos, yPos, tile, gameController.getGame().getCurrPlayer().getPersonalShelf().getStructure(), currentPlayableItemTile.size())) {
                    gameController.getGame().getCurrPlayer().getPersonalShelf().putTile(xPos, yPos, tile);
                    Player currPlayer = gameController.getGame().getCurrPlayer();
                    LivingRoom livingRoom = gameController.getGame().getMyShelfie().getLivingRoom();
                    gameController.calculatePoint(currPlayer, currPlayer.getPersonalShelf().getStructure(), livingRoom);
                    boolean shelfIsFull = gameController.getGame().getCurrPlayer().getPersonalShelf().isFull();
                    currentPlayableItemTile.remove(tile);
                    if (currentPlayableItemTile.size() > 0) {
                        ToKeepPuttingMessage newKeepPuttingMessage = new ToKeepPuttingMessage(currentPlayableItemTile);
                        client.onMessage(newKeepPuttingMessage);
                    } else {
                        if (shelfIsFull && !gameController.getGame().getEndGame()) {
                            gameController.getGame().setEndGame();
                        }
                        gameController.nextTurn();
                    }
                } else {
                    ToPutTile2Or3ReplyMessage newErrorMessage = new ToPutTile2Or3ReplyMessage(newMessage.getCurrentPlayableItemTile());
                    client.onMessage(newErrorMessage);
                }
            }
            case PLAYERNUMBER_REPLY -> {
                PlayersNumberReplyMessage newMessage = (PlayersNumberReplyMessage) message;
                int playersNum = newMessage.getNumPlayers();
                gameController.getGame().setPlayersNumber(playersNum);
                gameController.getGameBoardController().setPlayerNum(playersNum);
                resetStop();
            }
            case START_PICKING_TILE_REQUEST -> {
                clientNickCombinations.get(message.getNickname()).onMessage(new StartPuttingTileRequestMessage(gameController.pickTilesArray(message.getNickname())));
            }
            case WRITE_IN_CHAT -> {
                WriteInChatMessage newMessage = (WriteInChatMessage) message;
                gameController.getGame().setChat(newMessage.getNickname(),newMessage.getChat());
            }
        }
    }



    public void onPickTilesBegin(String nickname) throws RemoteException{
        clientNickCombinations.get(nickname).onMessage(new StartPickingTileReplyMessage());
    }
    public void onMaxTilesPicked(String nickname) throws RemoteException{
        clientNickCombinations.get(nickname).onMessage(new MaxTilesPickedMessage());
    }

    public void onInvalidTile(String nickname) throws RemoteException{
        clientNickCombinations.get(nickname).onMessage(new InvalidTileMessage());
    }

    @Override
    public void disconnect() throws RemoteException {
    }

    public void onUpdateAskKeepPicking(String nickname) throws RemoteException{
        clientNickCombinations.get(nickname).onMessage(new KeepPickingRequestMessage());
    }

    public void onUpdateToPutTile(boolean errorInTheInsertion, String errorType , ArrayList<PlayableItemTile> tilesInPlayerHand) throws RemoteException{
        client.onMessage((new ToPutTileReplyMessage(tilesInPlayerHand)));
    }


}
