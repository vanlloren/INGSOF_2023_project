package Network.ClientSide;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public abstract class Client extends UnicastRemoteObject {

    private final String nickname;
    private final String serverAddress;
    private final int portNum;
    private final ArrayList<GameMessage> messageQueue;

    public Client(String nickname, String serverAddress, int portNum) throws RemoteException{
        this.nickname = nickname;
        this.serverAddress = serverAddress;
        this.portNum = portNum;

        this.messageQueue = new ArrayList<>();

    }

    public String getNickname(){
        return nickname;
    }

    public String getServerAddress(){
        return serverAddress;
    }

    public int getPortNum(){
        return portNum;
    }

    public ArrayList<GameMessage> getMessageQueue(){
        return messageQueue;
    }
    public abstract void connectionInit() throws Exception;

    public abstract void sendGameMessage(GameMessage message) throws IOException;

    public abstract void closeConnection() throws Exception;

    public ArrayList<GameMessage> getGameMessages(){
        ArrayList<GameMessage> helperQueue;

        // gestione coda dei messaggi
    }
}
