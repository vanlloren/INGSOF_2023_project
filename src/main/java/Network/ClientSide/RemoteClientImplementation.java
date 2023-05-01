package Network.ClientSide;

import Network.ServerSide.RemoteServerInterface;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RemoteClientImplementation extends Client implements RemoteClientInterface{

    private RemoteServerInterface server;

    RemoteClientImplementation(String address, int port, String username) throws RemoteException {
        super(username, address, port);
    }
    @Override
    public void connectionInit() throws Exception {
        Registry registry = LocateRegistry.getRegistry(getServerAddress(), getPortNum());
        server = (RemoteServerInterface) registry.lookup("MyShelfieServer");

        server.login(getNickname(), this);

    }

    @Override
    public void sendGameMessage(GameMessage message) throws IOException {

    }

    @Override
    public void closeConnection() throws Exception {

    }

    @Override
    public void onMessage(GameMessage message) throws RemoteException {

    }

    @Override
    public void disconnect() throws RemoteException {

    }
}
