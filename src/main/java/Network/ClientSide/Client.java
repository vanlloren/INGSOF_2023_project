package Network.ClientSide;

import Network.Events.Event;
import Network.message.Message;
import Util.CommonGoalType;
import Util.PersonalGoalType;
import client.view.TurnView;
import client.view.View;
import Observer.*;

import server.Model.PlayableItemTile;
import server.Model.Player;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public abstract class Client extends UnicastRemoteObject implements ViewObserver, LivingRoomObserver, ShelfObserver, PlayerObserver, GameModelObserver, PersonalGoalObserver {

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

    public abstract void onModelModify(TurnView turnView, Event event);

    abstract void UpdateAllClientOnModelGameHasEnd();

    abstract void UpdateAllClientOnModelMatchWinner(String matchWinner);

    abstract void UpdateAllClientOnModelCurrPlayer(Player currPlayer);

    abstract void UpdateAllClientOnNewMessageChat(String Nickname, String chatMessage);

    abstract void UpdateAllClientOnPickedTileFromLivingRoom(String currPlayer, int x, int y);

    abstract void UpdateAllClientOnModelListPlayers(Player player);

    abstract void UpdateAllClientOnModelEndGame(String Nickname, boolean endGame);

    abstract void UpdateAllClientOnPlayersNumber(int playersNumber);


    abstract void UpdateAllClientOnModelPersonalGoal(String Nickname, PersonalGoalType personalGoalType);

    abstract void UpdateAllClientOnModelCommonGoal(CommonGoalType commonGoalType1, CommonGoalType commonGoalType2);

    abstract void UpdateAllClientOnModelPlayerPoint(String nickNameCurrentPlayer, Integer points);

    abstract void UpdateAllClientOnModelStatusCommonGoal1(String nickNameCurrentPlayer);

    abstract void UpdateAllClientOnModelStatusCommonGoal2(String nickNameCurrentPlayer);

    abstract void UpdateAllClientOnChairOwner(Player player);

    abstract void UpdateAllClientOnModelGameHasStarted();

    abstract void UpdateAllClientOnStructureShelf(int x, int y, PlayableItemTile Tile);


}
