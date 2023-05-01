package Network.ServerSide;

import Network.ClientSide.RemoteClientInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

//interfaccia inviata al client per comunicare col server

public interface RemoteServerInterface extends Remote {

    //andranno inseriti tutti i metodi che il server mette
    //a disposizione del client perché li invochi

    public void logIntoServer(String nickname, RemoteClientInterface client) throws RemoteException;

    public void onMessage(GameMessage message) throws RemoteException;

    public void disconnect() throws RemoteException;
}
