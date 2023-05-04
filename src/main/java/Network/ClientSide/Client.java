package Network.ClientSide;

import Network.message.Message;
import client.view.View;
import Observer.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public abstract class Client extends UnicastRemoteObject {

    private String nickname;
    private final String serverAddress;
    private final int portNum;

    private final View userInterface;
    private final ArrayList<Message> messageQueue;

    public Client(String serverAddress, int portNum, View userInterface) throws RemoteException{
        this.serverAddress = serverAddress;
        this.portNum = portNum;
        this.userInterface = userInterface;

        this.messageQueue = new ArrayList<Message>();

    }

    public void setNickname(String nickName){this.nickname = nickName;}
    public String getNickname(){
        return nickname;
    }

    public String getServerAddress(){
        return serverAddress;
    }

    public int getPortNum(){
        return portNum;
    }

    public ArrayList<Message> getMessageQueue(){
        return messageQueue;
    }
    public abstract void connectionInit() throws Exception;

    public abstract void sendMessage(Message message) throws IOException;

    public abstract void closeConnection() throws Exception;

    public ArrayList<Message> getMessages(){
        ArrayList<Message> helperQueue;

        // gestione coda dei messaggi
    }
}
