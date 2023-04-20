package Network.ServerSide;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteServerImplementation extends UnicastRemoteObject implements RemoteServerInterface {
    private final Server server;

    RemoteServerImplementation(Server server) throws RemoteException {
        this.server = server;
    }


    //implementazione metodi di RemoteServerInterface
}
