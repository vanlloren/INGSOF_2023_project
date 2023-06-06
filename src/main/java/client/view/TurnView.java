package client.view;

import Network.ClientSide.RemoteClientImplementation;
import Network.ClientSide.RemoteClientInterface;
import Network.Events.UpdatePlayersListEvent;
import Network.Events.*;
import Network.ServerSide.RemoteServerImplementation;
import Observer.*;
import Util.CommonGoalType;
import Util.PersonalGoalType;
import server.Model.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;


public class TurnView implements Serializable {
    @Serial
    private static final long serialVersionUID = -4523368433517565685L;
    private final SimpleLivingRoom livingRoom;
    private final SimpleGameModel gameModel;
    private final SimplePlayer currPlayer;

    public TurnView(GameModel gameModel){
        this.gameModel = gameModel;
        this.currPlayer = gameModel.getCurrPlayer();
        this.livingRoom = gameModel.getMyShelfie().getLivingRoom();
    }


    public SimpleLivingRoom getLivingRoom(){
        return this.livingRoom;
    }

    public boolean getIsGameOn(){
        return this.gameModel.getIsGameOn();
    }

    public SimpleGameModel getGameModel(){
        return this.gameModel;
    }

    public String getNicknameCurrentPlayer(){
        return this.currPlayer.getNickname();
    }

    public SimplePlayer getPlayer(String nickname){
        SimplePlayer player=null;
        for (SimplePlayer p: gameModel.getPlayersInGame()
        ) {
            if(p.getNickname().equals(nickname)){
                player = p;
            }
        }
        return player;
    }

    public SimpleShelf getShelfTable(String nickname){
        return currPlayer.getPersonalShelf();
    }

    public int getPartialPoint(String nickName){
        int i = 0;
        int point = 0;
        while(i<gameModel.getPlayersNumber()){
            if(nickName.equals(gameModel.getPlayersInGame().get(i).getNickname())){
                point = gameModel.getPlayersInGame().get(i).getPoints();
                break;
            }
            else {
                i++;
            }
        }
        return point;
    }
    // public void WriteToAllClient(String Nickname, String chatMessage){
    //notifyObservers(obs -> obs.UpdateAllClientonNewMessageChat(Nickname,chatMessage));

}




