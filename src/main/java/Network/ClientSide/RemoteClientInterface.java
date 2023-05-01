package Network.ClientSide;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteClientInterface extends Remote {

    void onMessage(GameMessage message) throws RemoteException;

    //void ping() throws RemoteException;

    void disconnect() throws RemoteException;
}
