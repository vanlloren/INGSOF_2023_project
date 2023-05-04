package Network.ServerSide;

import Network.ClientSide.RemoteClientInterface;
import Network.message.Message;

import java.rmi.RemoteException;

public class RMIConnection {
    private ServerApp server;
    private RemoteClientInterface client;

    private boolean isConnected = true;

    RMIConnection(ServerApp server, RemoteClientInterface client){
        this.server = server;
        this.client = client;
    }

    public boolean checkConnection(){
        return isConnected;
    }

    public void sendMessage(Message message) throws RemoteException {
        client.onMessage(message);
    }

    public void disconnection(){
        if(isConnected){
            isConnected=false;
            try{
                client.disconnect();
            }catch(RemoteException e){

            }

            server.onDisconnection(this);
        }
    }
}
