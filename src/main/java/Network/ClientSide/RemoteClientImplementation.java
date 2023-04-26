package Network.ClientSide;

import java.io.IOException;
import java.rmi.RemoteException;

public class RemoteClientImplementation extends Client implements RemoteClientInterface{
    @Override
    public void connectionInit() throws Exception {

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
