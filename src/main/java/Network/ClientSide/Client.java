package Network.ClientSide;

import Network.message.Message;
import client.view.View;
import Observer.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public abstract class Client extends UnicastRemoteObject implements ViewObserver, LivingRoomObserver, ShelfObserver, PlayerObserver, GameModelObserver, CommonGoalObserver,PersonalGoalObserver {

    private String nickname;
    private final String serverAddress;
    private final int portNum;

    protected final View userInterface;

    public Client(String serverAddress, int portNum, View userInterface) throws RemoteException{
        this.serverAddress = serverAddress;
        this.portNum = portNum;
        this.userInterface = userInterface;



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

    public abstract void connectionInit() throws Exception;

    public abstract void sendMessage(Message message) throws IOException;

    public abstract void closeConnection() throws Exception;

}
