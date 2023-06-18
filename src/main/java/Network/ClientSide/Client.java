package Network.ClientSide;

import Network.Events.Event;
import Util.CommonGoalType;
import Util.PersonalGoalType;
import client.view.TurnView;
import client.view.View;
import Observer.*;
import server.Model.PlayableItemTile;
import server.Model.Player;

import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public abstract class Client extends UnicastRemoteObject implements ViewObserver, LivingRoomObserver, ShelfObserver, PlayerObserver, GameModelObserver, PersonalGoalObserver {

    @Serial
    private static final long serialVersionUID = 8809657577515345239L;
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



    public abstract void onModelModify(TurnView turnView, Event event);

}
