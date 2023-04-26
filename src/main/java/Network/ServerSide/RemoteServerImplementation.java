package Network.ServerSide;

import Network.ClientSide.RemoteClientInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class RemoteServerImplementation extends UnicastRemoteObject implements RemoteServerInterface {
    private final Server server;
    private RMIConnection rmiStream;

    RemoteServerImplementation(Server server) throws RemoteException {
        this.server = server;
    }

    @Override
    public void logIntoServer(String nickname, RemoteClientInterface client) throws RemoteException {
        rmiStream = new RMIConnection(server, client);
        server.login(nickname, rmiStream);
    }

    @Override
    public void onMessage(GameMessage message) throws RemoteException {
        server.onMessage(message);
    }

    @Override
    public void disconnect() throws RemoteException {
        rmiStream.disconnection();
    }



    //implementazione metodi di RemoteServerInterface
}
