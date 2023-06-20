package Network.ClientSide;

import Network.Events.Event;
import Observer.*;
import client.view.TurnView;
import client.view.View;
import server.Model.Player;

import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * This abstract class contains all the methods that provides the expected functionalities provided
 * by a {@link RemoteClientImplementation RemoteClient}
 */
public abstract class Client extends UnicastRemoteObject implements ViewObserver, LivingRoomObserver, ShelfObserver, PlayerObserver, GameModelObserver, PersonalGoalObserver {

    @Serial
    private static final long serialVersionUID = 8809657577515345239L;
    private String nickname;
    private final String serverAddress;
    private final int portNum;

    protected final View userInterface;

    /**
     * This method creates an instance of {@link Client Client} and saves the
     * network parameters of the {@link Network.ServerSide.RemoteServerImplementation RemoteServer}
     * it will try to log into
     *
     * @param serverAddress the ipAddress of the {@link Network.ServerSide.RemoteServerImplementation RemoteServer}
     * @param portNum the number of the socketPort of the {@link Network.ServerSide.RemoteServerImplementation RemoteServer}
     * @param userInterface the {@link View View} that the user is currently using
     * @throws RemoteException an {@link Exception Exception} that notifies an error in the connection
     * with the {@link Network.ServerSide.RemoteServerImplementation RemoteServer}
     */
    public Client(String serverAddress, int portNum, View userInterface) throws RemoteException{
        this.serverAddress = serverAddress;
        this.portNum = portNum;
        this.userInterface = userInterface;
    }

    /**
     * This method sets the {@code nickname} of the {@link Player Player}
     * using this {@link RemoteClientImplementation RemoteClient} in the {@link RemoteClientImplementation RemoteClient}
     * itself
     *
     * @param nickName a {@link String String} containing the {@code nickname} of the {@link Player Player}
     */
    public void setNickname(String nickName){this.nickname = nickName;}

    /**
     *
     * @return the {@code nickname} of the {@link Player Player} using this {@link RemoteClientImplementation RemoteClient}
     */
    public String getNickname(){
        return nickname;
    }

    /**
     *
     * @return the ipAddress of the {@link Network.ServerSide.RemoteServerImplementation RemoteServer} into which
     * the {@link Client Client} is logged into
     */
    public String getServerAddress(){
        return serverAddress;
    }

    /**
     *
     * @return the {@code portNum} of the {@link Network.ServerSide.RemoteServerImplementation RemoteServer} into which
     * the {@link Client Client} is logged into
     */
    public int getPortNum(){
        return portNum;
    }

    /**
     * This method creates the connection between the {@link Client Client} and the {@link Network.ServerSide.RemoteServerImplementation RemoteServer}
     * @throws Exception  an {@link Exception Exception} that notifies an error in the connection
     * with the {@link Network.ServerSide.RemoteServerImplementation RemoteServer}
     */
    public abstract void connectionInit() throws Exception;

    /**
     * This method allows the {@link RemoteClientImplementation RemoteClient} to trace the specific
     * event that triggered the notification of a change in the {@link server.Model.GameModel GameModel}.
     *
     * @param turnView an updated instance of {@link TurnView TurnView}
     * @param event the {@link Event Event} that triggered the notification
     */
    public abstract void onModelModify(TurnView turnView, Event event);

}
