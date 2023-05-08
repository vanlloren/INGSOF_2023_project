package Network.ClientSide;

import Observer.ViewObserver;
import client.view.TUI;
import client.view.View;

public class IOManager{
    private Client client;


    public Client connectRMI(String address, int port, View userInterface) throws Exception{
        client = new RemoteClientImplementation(address, port, userInterface);
        client.connectionInit();
        return client;
    }

   // public Client connectSocket(String address, int port, View userInterface) throws Exception{
        //// andrà gestita la connessione a un clientSocket
    //}
}
