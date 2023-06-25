package client.view;

import server.Model.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * This Class represents a not modifiable copy of the {@link GameModel GameModel} which is
 * sent to the {@link Network.ClientSide.RemoteClientImplementation RemoteClients} following any
 * type of change made on the {@link GameModel GameModel} or one of its components.
 */
public class TurnView implements Serializable {
    @Serial
    private static final long serialVersionUID = -4523368433517565685L;
    private final SimpleLivingRoom livingRoom;
    private final SimpleGameModel gameModel;
    private final SimplePlayer currPlayer;

    /**
     * This method creates an instance of {@link TurnView TurnView} and binds it with the
     * {@link GameModel GameModel} it has to copy.
     *
     * @param gameModel the {@link GameModel GameModel} to bind
     */
    public TurnView(GameModel gameModel){
        this.gameModel = gameModel;
        this.currPlayer = gameModel.getCurrPlayer();
        this.livingRoom = gameModel.getMyShelfie().getLivingRoom();
    }

    /**
     * @return the current situation of the {@link LivingRoom LivingRoom}
     * simplified through a {@link SimpleLivingRoom SimpleLivingRoom}
     */
    public SimpleLivingRoom getLivingRoom(){
        return this.livingRoom;
    }

    /**
     * @return the current situation of the {@link GameModel GameModel}
     * simplified through a {@link SimpleGameModel SimpleGameModel}
     */
    public SimpleGameModel getGameModel(){
        return this.gameModel;
    }

    /**
     * @return the {@code nickname} of the {@code currPlayer}
     */
    public String getNicknameCurrentPlayer(){
        return this.currPlayer.getNickname();
    }

    /**
     * @param nickname the {@code nickname} of the {@link Player Player} who wants to see the
     * {@link Shelf Shelf}
     * @return the current situation of the {@link Shelf Shelf}
     * simplified through a {@link SimpleLivingRoom SimpleShelf}
     */
    public SimpleShelf getShelfTable(String nickname){
        for(SimplePlayer player : gameModel.getPlayersInGame()){
            if(player.getNickname().equals(nickname)){
                return player.getPersonalShelf();
            }
        }
        return null;
    }

    /**
     * @param nickName the {@code nickname} of the {@link Player Player} whose points has to be counted
     * @return the actual count of the points of the {@link Player Player}
     */
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




