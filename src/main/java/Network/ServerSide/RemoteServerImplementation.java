package Network.ServerSide;

import Network.ClientSide.RemoteClientInterface;
import Network.message.*;
import Observer.Observer;
import Util.RandPersonalGoal;
import server.Controller.GameController;
import server.Model.Player;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class RemoteServerImplementation extends UnicastRemoteObject implements RemoteServerInterface {
    private final RMIServer server;
    private RMIConnection rmiStream;
    private RemoteClientInterface client;
    private GameController gameController;

    RemoteServerImplementation(RMIServer server, GameController gameController) throws RemoteException {
        this.server = server;
        this.gameController = gameController;
    }

    @Override
    public void logIntoServer(String nickname, RemoteClientInterface client) throws RemoteException {
        rmiStream = new RMIConnection(server, client);
        server.login(nickname, rmiStream);
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
                    gameController.getGameBoardController().setStopPicking();
                    gameController.getGameBoardController().setMoveOn();
                }
                gameController.getGameBoardController().setMoveOn();
            }
        }
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


    //implementazione metodi di RemoteServerInterface
}
