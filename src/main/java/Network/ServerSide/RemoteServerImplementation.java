package Network.ServerSide;

import Network.ClientSide.RemoteClientInterface;
import Network.message.*;

import Util.RandPersonalGoal;
import client.view.TurnView;
import server.Controller.GameController;
import server.Model.PlayableItemTile;
import server.Model.Player;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class RemoteServerImplementation extends UnicastRemoteObject implements RemoteServerInterface{
    private final RMIServer server;

    private final TurnView turnView;
    private final Object lock = new Object();
    private boolean stop = false;
    private RemoteClientInterface client;
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
                        Player newPlayer = new Player(message.getNickname());
                        RandPersonalGoal.setType(newPlayer, newPlayer.getPersonalGoal(), gameController.getGame().getPlayersInGame());
                        gameController.getGame().setPlayersInGame(newPlayer);
                        Message newMessage = new PlayersNumberRequestMessage(message.getNickname());
                        client.onMessage(newMessage);
                        stop = true;
                        while(stop){
                        }
                        gameController.initGameBoard();
                    } else if (gameController.getGame().getPlayersInGame().size() < gameController.getGame().getPlayersNumber()) {
                        boolean approvedNick = gameController.getGame().isNicknameAvailable(message.getNickname());
                        if(approvedNick){
                            Player newPlayer = new Player(message.getNickname());
                            RandPersonalGoal.setType(newPlayer, newPlayer.getPersonalGoal(), gameController.getGame().getPlayersInGame());
                            gameController.getGame().setPlayersInGame(newPlayer);
                        }
                        Message newMessage = new LoginReplyMessage(message.getNickname(), approvedNick);
                        client.onMessage(newMessage);
                    }
                     else if ((gameController.getGame().getPlayersInGame().size() == gameController.getGame().getPlayersNumber())){
                        Message newMessage = new FullLobbyMessage();
                        client.onMessage(newMessage);
                    }
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
            case REQUEST_INSERT_TILE ->{

            }
            case PLAYERNUMBER_REPLY -> {
                PlayersNumberReplyMessage newMessage = (PlayersNumberReplyMessage)message;
                int playersNum = newMessage.getNumPlayers();
                gameController.getGame().setPlayersNumber(playersNum);
                resetStop();
            }



        }
    }


    @Override
    public void disconnect() throws RemoteException {

    }

    @Override
    public boolean handShake(RemoteClientInterface client) {
        this.client = client;
        turnView.addObserver(client);
        return true;
    }



    public void onUpdateToPickTile(ArrayList<PlayableItemTile> availableTiles) throws RemoteException {
        client.onMessage(new ToPickTileRequestMessage(availableTiles));
    }

}
