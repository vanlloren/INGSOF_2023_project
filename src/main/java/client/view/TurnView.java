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
    private final GameModel gameModel;

    public TurnView(GameModel gameModel){
        this.gameModel= gameModel;
    }

    public LivingRoom getLivingRoom(){
       return this.gameModel.getMyShelfie().getLivingRoom();
   }

    public ArrayList<Player> getPlayerInGame() {
        return gameModel.getPlayersInGame();
    }

    public boolean getIsGameOn(){
       return gameModel.getIsGameOn();
    }

    public Shelf getShelfTable(String nickname){
        for (Player player: this.gameModel.getPlayersInGame()
             ) {
            if (nickname.equals(player.getNickname())) {
                return this.gameModel.getCurrPlayer().getPersonalShelf();
            }
        }

        return null;

    }

    public int getPlayersNumber(){
       return this.gameModel.getPlayersInGame().size();
    }




    public int getPartialPoint(String nickName){
       int i = 0;
       int point = 0;
       while(i<getPlayersNumber()){
           if(nickName.equals(getPlayerInGame().get(i).getNickname())){
                point = getPlayerInGame().get(i).getPoints();

           }
           else i++;
       }
     return point;
    }


    public String getNickNameCurrentPlayer(){
       return gameModel.getCurrPlayer().getNickname();
    }


    public void WriteToAllClient(String Nickname, String chatMessage){
        //notifyObservers(obs -> obs.UpdateAllClientonNewMessageChat(Nickname,chatMessage));

    }


}
