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

    public TileReplyMessage onTilePickMessage(String nickname, int x, int y){
        TileReplyMessage message = gameController.pickTile(x, y);

        return message;
    }
    public InsertionReplyMessage ToPutTileRequestMessage(int xPos, int yPos , PlayableItemTile tile , int numOfTiles){
       InsertionReplyMessage message = gameController.putTile(xPos,yPos,tile,numOfTiles);
       return message;
    }
    public InsertionReplyMessage ToPutTileRequestMessage(int xPos, PlayableItemTile tile , int numOfTiles){
        InsertionReplyMessage message = gameController.putTile(xPos,tile,numOfTiles);
        return message;
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

            case PLAYERNUMBER_REPLY -> {
                PlayersNumberReplyMessage newMessage = (PlayersNumberReplyMessage) message;
                int playersNum = newMessage.getNumPlayers();
                gameController.getGame().setPlayersNumber(playersNum);
                gameController.getGameBoardController().setPlayerNum(playersNum);
                resetStop();
            }
            case WRITE_IN_CHAT -> {
                WriteInChatMessage newMessage = (WriteInChatMessage) message;
                gameController.getGame().setChat(newMessage.getNickname(),newMessage.getChat());
            }
            case STOP_PICKING -> {
                gameController.getGameBoardController().getControlledLivingRoom().updateAvailability();
                if (!gameController.getGameBoardController().checkIfAdjacentTiles()) {
                    gameController.getGameBoardController().livingRoomFiller();
                    gameController.getGameBoardController().getControlledLivingRoom().updateAvailability();
                }
                gameController.getGameBoardController().toPlayerTilesResetter();
            }
        }
    }


    @Override
    public void disconnect() throws RemoteException {
    }


}
