package Network.ClientSide;

import client.view.TUI;

public class IOManager {
    private Client client;


    public void connectRMI(String address, int port, String nickname, TUI textualInterface) throws Exception{
        client = new RemoteClientImplementation(address, port, nickname);
        client.connectionInit();
    }

    public void connectSocket(String address, int port, String nickname, TUI textualInterface) throws Exception{
        //// andrà gestita la connessione a un clientSocket
    }
}
