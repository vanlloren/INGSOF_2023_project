package client.view;

import server.Model.*;

import java.io.Serial;
import java.io.Serializable;


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

    public SimpleGameModel getGameModel(){
        return this.gameModel;
    }

    public String getNicknameCurrentPlayer(){
        return this.currPlayer.getNickname();
    }

    public SimpleShelf getShelfTable(){
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

}




