package Network.ClientSide;

import client.view.View;

/**
 * This Class allows the {@link Client Client} to begin the process of initialization
 * of the connection with a {@link Network.ServerSide.RemoteServerImplementation RemoteServer}.
 */
public class IOManager{

    /**
     * This method creates an instance of {@link RemoteClientImplementation RemoteClient} and
     * launches the process of connection with the {@link Network.ServerSide.RemoteServerImplementation RemoteServer}.
     *
     * @param address the ipAddress of the {@link Network.ServerSide.RemoteServerImplementation RemoteServer}
     * @param port the port of the {@link Network.ServerSide.RemoteServerImplementation RemoteServer}
     * @param userInterface the instance of {@link View View} linked with the user
     * @return a fully functioning {@link RemoteClientImplementation RemoteClient}
     * @throws Exception  an {@link Exception Exception} that notifies an error in the connection
     * with the {@link Network.ServerSide.RemoteServerImplementation RemoteServer}
     */
    public Client connectRMI(String address, int port, View userInterface) throws Exception{
        RemoteClientImplementation client = new RemoteClientImplementation(address, port, userInterface);
        client.connectionInit();
        return client;
    }
}
