package Network.ClientSide;

import client.view.View;

public class IOManager{


    public Client connectRMI(String address, int port, View userInterface) throws Exception{
        RemoteClientImplementation client = new RemoteClientImplementation(address, port, userInterface);
        client.connectionInit();
        return client;
    }
}
