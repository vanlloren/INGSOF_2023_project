package Network.ServerSide;

import Exceptions.MaxTilesPickedException;
import Network.ClientSide.RemoteClientInterface;
import Network.Events.Event;
import Network.message.*;

import Util.RandPersonalGoal;
import client.view.TurnView;
import server.Controller.GameController;
import server.Model.PlayableItemTile;
import server.Model.Player;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;


public class RemoteServerImplementation extends UnicastRemoteObject implements RemoteServerInterface{
    private final RMIServer server;

    private final TurnView turnView;
    private final Object lock = new Object();
    private boolean stop = false;
    private RemoteClientInterface client;

    private ArrayList<RemoteClientInterface> clientList;

    private HashMap<String, RemoteClientInterface> clientNickCombinations;
    private final GameController gameController;
    private ArrayList<PlayableItemTile> availableTiles;

    RemoteServerImplementation(RMIServer server, GameController gameController, TurnView turnView) throws RemoteException {
        this.server = server;
        this.gameController = gameController;
        this.turnView = turnView;
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
                        clientNickCombinations.put(message.getNickname(), newMessage.getClient());
                        Player newPlayer = new Player(message.getNickname(), turnView);
                        newPlayer.addObserver(turnView);
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
                            clientNickCombinations.put(message.getNickname(), newMessage.getClient());
                            Player newPlayer = new Player(message.getNickname(), turnView);
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
                            clientNickCombinations.put(message.getNickname(), newMessage.getClient());
                            Player newPlayer = new Player(message.getNickname(), turnView);
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
                    gameController.setMoveOn();
                }
                gameController.setMoveOn();
            }
            case TO_PICK_TILE_REPLY -> {
                ToPickTileReplyMessage newMessage = (ToPickTileReplyMessage) message;
                int x = newMessage.getXPos();
                int y = newMessage.getYPos();
                gameController.setPosCurrTile(x,y);
                gameController.setMoveOn();
            }
            case TO_PUT_TILE_REPLY ->{
                ToPutTileReplyMessage newMessage = (ToPutTileReplyMessage) message;
                int x = newMessage.getX();
                int y = newMessage.getY();



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
        }
    }

    public void onPickTilesBegin(String nickname) throws RemoteException{
        clientNickCombinations.get(nickname).onMessage(new StartPickingTileReplyMessage());
    }
    public void onMaxTilesPicked(String nickname) throws RemoteException{
        clientNickCombinations.get(nickname).onMessage(new MaxTilesPickedMessage());
    }

    @Override
    public void disconnect() throws RemoteException {

    }

    @Override
    public void handShake(RemoteClientInterface client) {
        clientList.add(client);
    }

    public void onUpdateAskKeepPicking(String nickname) throws RemoteException{
        clientNickCombinations.get(nickname).onMessage(new KeepPickingRequestMessage());
    }

    public void onUpdateToPutTile() throws RemoteException{
        client.onMessage((new ToPutTileRequestMessage()));
    }

    public void onTurnViewModified(TurnView turnView, Event event){
        for (RemoteClientInterface client: clientList
             ) {
            client.onModelModify(turnView, event);
        }
    }

}
