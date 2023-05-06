package Network.ServerSide;

import Network.ClientSide.RemoteClientInterface;
import Network.message.*;
import Observer.Observer;
import Util.RandPersonalGoal;
import server.Controller.GameController;
import server.Model.PlayableItemTile;
import server.Model.Player;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class RemoteServerImplementation extends UnicastRemoteObject implements RemoteServerInterface, Observer {
    private final RMIServer server;

    private RemoteClientInterface client;
    private GameController gameController;

    RemoteServerImplementation(RMIServer server, GameController gameController) throws RemoteException {
        this.server = server;
        this.gameController = gameController;
    }



    @Override
    public void onMessage(Message message) throws RemoteException {

        switch (message.getMessageEnumeration()){
            case LOGIN_REQUEST -> {
                if(gameController.getGame().getPlayersInGame().size() < 1){
                    Player newPlayer = new Player(message.getNickname());
                    RandPersonalGoal.setType(newPlayer, newPlayer.getPersonalGoal(),gameController.getGame().getPlayersInGame());
                    gameController.getGame().setPlayersInGame(newPlayer);
                    Message newMessage = new PlayersNumberRequestMessage(message.getNickname());
                    client.onMessage(newMessage);
                } else if (gameController.getGame().getPlayersInGame().size() < gameController.getGame().getPlayersNumber()) {
                    boolean approvedNick = gameController.getGame().isNicknameAvailable(message.getNickname());
                    Message newMessage = new LoginReplyMessage(message.getNickname(), approvedNick);
                    client.onMessage(newMessage);
                }
            }
            case PLAYERNUMBER_REPLY -> {
                PlayersNumberReplyMessage newMessage = (PlayersNumberReplyMessage)message;
                gameController.getGame().setPlayersNumber(newMessage.getPlayerNumber());
                gameController.initGameBoard();
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
        }
    }

    public void onUpdateAskKeepPicking() throws RemoteException{
        client.onMessage(new KeepPickingRequestMessage());
    }
    @Override
    public void disconnect() throws RemoteException {
        rmiStream.disconnection();
    }

    @Override
    public boolean handShake(RemoteClientInterface client) {
        this.client = client;
        return true;
    }

    @Override
    public void update(Message message) {

    }

    public void onUpdateToPickTile(ArrayList<PlayableItemTile> availableTiles) throws RemoteException {
        client.onMessage(new ToPickTileRequestMessage(availableTiles));
    }


    //implementazione metodi di RemoteServerInterface
}
