package Network.ServerSide;

import Network.ClientSide.RemoteClientInterface;
import Network.message.*;
import Observer.Observer;
import server.Controller.GameController;

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
        //server.onMessage(message);
        switch (message.getMessageEnumeration()){
            case LOGIN_REQUEST -> {
                gameController.
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
