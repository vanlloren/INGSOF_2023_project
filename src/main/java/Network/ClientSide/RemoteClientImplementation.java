package Network.ClientSide;

import Network.ServerSide.RemoteServerInterface;
import Network.message.Message;
import Observer.ViewObserver;
import client.view.TUI;
import client.view.View;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RemoteClientImplementation extends Client, ViewObserver implements RemoteClientInterface{

    private RemoteServerInterface server;

    RemoteClientImplementation(String address, int port, View userInterface) throws RemoteException {
        super(address, port, userInterface);
    }
    @Override
    public void connectionInit() throws Exception {
        Registry registry = LocateRegistry.getRegistry(getServerAddress(), getPortNum());
        server = (RemoteServerInterface) registry.lookup("MyShelfieServer");

        server.handShake(this);

    }

    @Override
    public void sendMessage(Message message) throws IOException {

    }

    @Override
    public void closeConnection() throws Exception {

    }

    @Override
    public void onMessage(Message message) throws RemoteException {

    }

    @Override
    public void disconnect() throws RemoteException {

    }
}
