package Network.ServerSide;

import Network.ClientSide.RemoteClientImplementation;
import Network.ClientSide.RemoteClientInterface;
import Network.message.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

//interfaccia inviata al client per comunicare col server

public interface RemoteServerInterface extends Remote {

    //andranno inseriti tutti i metodi che il server mette
    //a disposizione del client perché li invochi

       public void onMessage(Message message) throws RemoteException;

    public void disconnect() throws RemoteException;

    public boolean handShake(RemoteClientInterface client);


}
