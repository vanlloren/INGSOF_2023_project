package Network.ServerSide;

import Exceptions.MaxTilesPickedException;
import Network.ClientSide.RemoteClientInterface;
import Network.Events.Event;
import Network.message.*;

import Util.RandPersonalGoal;
import client.view.TurnView;
import server.Controller.GameController;
import server.Controller.RuleShelf;
import server.Model.LivingRoom;
import server.Model.PlayableItemTile;
import server.Model.Player;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;


public class RemoteServerImplementation extends UnicastRemoteObject implements RemoteServerInterface{
    private final RMIServer server;

    private final ProxyObserver proxyObserver;
    private final Object lock = new Object();
    private boolean stop = false;
    private RemoteClientInterface client;

    private ArrayList<RemoteClientInterface> clientList;

    private HashMap<String, RemoteClientInterface> clientNickCombinations;
    private final GameController gameController;
    private ArrayList<PlayableItemTile> availableTiles;

    RemoteServerImplementation(RMIServer server, GameController gameController, ProxyObserver proxyObserver) throws RemoteException {
        this.server = server;
        this.gameController = gameController;
        this.proxyObserver = proxyObserver;
    }

    public void resetStop(){
        stop = false;
    }

    @Override
    public void onMessage(Message message) throws RemoteException {

        switch (message.getMessageEnumeration()){
            case LOGIN_REQUEST -> {
                synchronized (lock) {
                    if (gameController.getGame().getPlayersInGame().size() < 1) {
                        clientNickCombinations = new HashMap<>();
                        LoginRequestMessage newMessage = (LoginRequestMessage) message;
                        clientList.add(newMessage.getClient());
                        clientNickCombinations.put(message.getNickname(), newMessage.getClient());
                        Player newPlayer = new Player(message.getNickname(), proxyObserver);
                        newPlayer.addObserver(proxyObserver);
                        RandPersonalGoal.setType(newPlayer, newPlayer.getPersonalGoal(), gameController.getGame().getPlayersInGame());
                        gameController.getGame().setPlayersInGame(newPlayer);
                        Message newMessage2 = new PlayersNumberRequestMessage(message.getNickname());
                        newMessage.getClient().onMessage(newMessage2);
                        stop = true;
                        while(stop){
                        }
                        gameController.initGameBoard();
                    } else if (gameController.getGame().getPlayersInGame().size() < gameController.getGame().getPlayersNumber()-1) {
                        boolean approvedNick = gameController.getGame().isNicknameAvailable(message.getNickname());
                        if(approvedNick){
                            LoginRequestMessage newMessage = (LoginRequestMessage) message;
                            clientList.add(newMessage.getClient());
                            clientNickCombinations.put(message.getNickname(), newMessage.getClient());
                            Player newPlayer = new Player(message.getNickname(), proxyObserver);
                            RandPersonalGoal.setType(newPlayer, newPlayer.getPersonalGoal(), gameController.getGame().getPlayersInGame());
                            gameController.getGame().setPlayersInGame(newPlayer);
                        }
                        Message newMessage = new LoginReplyMessage(message.getNickname(), approvedNick);
                        clientNickCombinations.get(message.getNickname()).onMessage(newMessage);
                          }
                    else if(gameController.getGame().getPlayersInGame().size() == gameController.getGame().getPlayersNumber()-1){
                        boolean approvedNick = gameController.getGame().isNicknameAvailable(message.getNickname());
                        if(approvedNick){
                            LoginRequestMessage newMessage = (LoginRequestMessage) message;
                            clientList.add(newMessage.getClient());
                            clientNickCombinations.put(message.getNickname(), newMessage.getClient());
                            Player newPlayer = new Player(message.getNickname(), proxyObserver);
                            RandPersonalGoal.setType(newPlayer, newPlayer.getPersonalGoal(), gameController.getGame().getPlayersInGame());
                            gameController.getGame().setPlayersInGame(newPlayer);
                        }
                        Message newMessage = new LoginReplyMessage(message.getNickname(), approvedNick);
                        clientNickCombinations.get(message.getNickname()).onMessage(newMessage);                    }

                     else if ((gameController.getGame().getPlayersInGame().size() == gameController.getGame().getPlayersNumber())){
                        Message newMessage = new FullLobbyMessage();
                        clientNickCombinations.get(message.getNickname()).onMessage(newMessage);                    }
                }
            }
            case KEEP_PICKING_REPLY -> {
                KeepPickingReplyMessage newMessage = (KeepPickingReplyMessage)message;
                if(!newMessage.getKeepPicking()) {
                    gameController.setStopPicking();
                }
            }
            case TO_PICK_TILE_REPLY -> {
                ToPickTileReplyMessage newMessage = (ToPickTileReplyMessage) message;
                int x = newMessage.getXPos();
                int y = newMessage.getYPos();
                gameController.setPosCurrTile(x,y);
                gameController.setMoveOn();
            }
            case TO_PUT_TILE_REQUEST ->{
                ToPutTileRequestMessage newMessage = (ToPutTileRequestMessage) message;
                int x = newMessage.getX();
                int y = newMessage.getY();
                PlayableItemTile tile = newMessage.getTile();
                ArrayList<Integer> columnPosition = newMessage.getColumnPosition();
                int numOfTiles= newMessage.getNumOfTiles();

                /*---------------------------------------------*/
                if(RuleShelf.iscolumnAvailable(y,numOfTiles,gameController.getGame().getCurrPlayer().getPersonalShelf().getStructure())){
                    if(RuleShelf.commandPutTileCheckValidity(x , y , tile , gameController.getGame().getCurrPlayer().getPersonalShelf().getStructure(), numOfTiles )){
                        gameController.getGame().getCurrPlayer().getPersonalShelf().putTile(x , y , tile);
                        Player currPlayer = gameController.getGame().getCurrPlayer();
                        LivingRoom livingRoom = gameController.getGame().getMyShelfie().getLivingRoom();
                        gameController.calculatePoint(currPlayer,currPlayer.getPersonalShelf().getStructure(), livingRoom );
                        boolean shelfIsFull = gameController.getGame().getCurrPlayer().getPersonalShelf().isFull();
                        gameController.getGame().getCurrPlayer().getPersonalShelf().setColumnChoosen(y);
                        ArrayList<PlayableItemTile> currentPlayableItemTile = newMessage.getPlayableItemTiles();
                        currentPlayableItemTile.remove(tile);
                        if(currentPlayableItemTile.size() > 0) {
                            ToKeepPuttingMessage newKeepPuttingMessage = new ToKeepPuttingMessage(currentPlayableItemTile);
                            client.onMessage(newKeepPuttingMessage);
                        }else{
                            if(shelfIsFull && !gameController.getGame().getEndGame()){
                                gameController.getGame().setEndGame();
                            }
                            gameController.nextTurn();
                        }
                } else if(!RuleShelf.iscolumnAvailable(y,numOfTiles,gameController.getGame().getCurrPlayer().getPersonalShelf().getStructure()) || (RuleShelf.iscolumnAvailable(y,numOfTiles,gameController.getGame().getCurrPlayer().getPersonalShelf().getStructure()))
                    && !RuleShelf.commandPutTileCheckValidity(x , y , tile , gameController.getGame().getCurrPlayer().getPersonalShelf().getStructure(), numOfTiles )){
                        ToPutTileReplyMessage newErrorMessage = new ToPutTileReplyMessage(newMessage.getPlayableItemTiles());
                        client.onMessage(newErrorMessage);
                    }

                }

            }
            case TO_PUT_TILE_REPLY_ERROR -> {
            }
            case TO_PUT_TILE_2_OR_3_REQUEST -> {
                ToPut2Or3TileRequestMessage newMessage = (ToPut2Or3TileRequestMessage) message;
                int xPos = newMessage.getxPos();
                int yPos = gameController.getGame().getCurrPlayer().getPersonalShelf().getColumnChoosen();
                PlayableItemTile tile = newMessage.getTile();
                ArrayList<PlayableItemTile> currentPlayableItemTile = newMessage.getCurrentPlayableItemTile();
                if(RuleShelf.commandPutTileCheckValidity(xPos , yPos , tile , gameController.getGame().getCurrPlayer().getPersonalShelf().getStructure(),currentPlayableItemTile.size() )){
                    gameController.getGame().getCurrPlayer().getPersonalShelf().putTile(xPos , yPos , tile);
                    Player currPlayer = gameController.getGame().getCurrPlayer();
                    LivingRoom livingRoom = gameController.getGame().getMyShelfie().getLivingRoom();
                    gameController.calculatePoint(currPlayer,currPlayer.getPersonalShelf().getStructure(), livingRoom );
                    boolean shelfIsFull = gameController.getGame().getCurrPlayer().getPersonalShelf().isFull();
                    currentPlayableItemTile.remove(tile);
                    if(currentPlayableItemTile.size()>0){
                        ToKeepPuttingMessage newKeepPuttingMessage = new ToKeepPuttingMessage(currentPlayableItemTile);
                        client.onMessage(newKeepPuttingMessage);
                    }else{
                        if(shelfIsFull && !gameController.getGame().getEndGame()){
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
                resetStop();
            }
            case START_PICKING_TILE_REQUEST -> {
                clientNickCombinations.get(message.getNickname()).onMessage(new StartPuttingTileRequestMessage(gameController.pickTilesArray(message.getNickname())));
            }
            case TO_PUT_TILE_2_OR_3_REPLY_ERROR -> {
            }
            case KEEP_PUTTING_REQUEST -> {
            }
            case WINNER_MESSAGE -> {
            }
            case ERROR_MESSAGE -> {
            }
            case KEEP_PICKING_REQUEST -> {
            }
            case TO_PICK_TILE_REQUEST -> {
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

    public void onTurnViewModified(Event event){
        for (RemoteClientInterface client: clientList
             ) {
            client.onModelModify(new TurnView(gameController.getGame()), event);
        }
    }

}
