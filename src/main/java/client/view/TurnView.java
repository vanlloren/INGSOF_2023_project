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


public class TurnView implements LivingRoomObserver, ShelfObserver, PlayerObserver, GameModelObserver,CommonGoalObserver,PersonalGoalObserver, Serializable {
    @Serial
    private static final long serialVersionUID = -4523368433517565685L;
    private GameModel gameModel;
   private RemoteServerImplementation server;

   public void setGameModel(GameModel gameModel){
       this.gameModel=gameModel;
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

    public void setServer(RemoteServerImplementation server) {
        this.server = server;
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
        notifyObservers(obs -> obs.UpdateAllClientonNewMessageChat(Nickname,chatMessage));

    }

    @Override
    public void onUpdateModelListPlayers(Player player) {
       server.onTurnViewModified(this, new UpdatePlayersListEvent(player));
    }

    @Override
    public void onUpdateModelEndGame(boolean endGame)  {
       server.onTurnViewModified(this, new UpdateEndGameEvent(endGame));
    }

    @Override
    public void onUpdateModelPlayersNumber(int playersNumber)  {
       server.onTurnViewModified(this, new UpdatePlayersNumberEvent(playersNumber));
    }

    @Override
    public void onUpdateModelChairOwner(Player player) {
        server.onTurnViewModified(this, new UpdateChairOwnerEvent(player));
    }

    @Override
    public void onUpdateGameBoard(GameBoard gameBoard) {
       server.onTurnViewModified(this, new UpdateGameBoardEvent(gameBoard));
    }

    @Override
    public void onUpdateModelGameHasStarted() {
        server.onTurnViewModified(this, new UpdateGameHasStartedEvent());
    }

    @Override
    public void onUpdateModelCurrentPlayer(Player currPlayer) {
        server.onTurnViewModified(this, new UpdateCurrPlayerEvent(currPlayer));
    }

    @Override
    public void onUpdateModelMatchWinner(String player) {
       server.onTurnViewModified(this, new UpdateMatchWinnerEvent(player));
    }

    @Override
    public void onUpdateModelGameHasEnd() {
       server.onTurnViewModified(this, new UpdateGameHasEndEvent());
    }

    @Override
    public void onUpdatePickedTileFromLivingRoom(int x, int y) {
        server.onTurnViewModified(this, new UpdatePickedLivingRoomTileEvent(this.gameModel.getCurrPlayer().getNickname() ,x, y));
    }

    @Override
    public void OnUpdateModelPersonalGoal(PersonalGoalType personalGoalType) {
        server.onTurnViewModified(this, new UpdatePersonalGoalEvent( ,personalGoalType));
    }

    @Override
    public void OnUpdateModelCommonGoal(CommonGoalType commonGoalType) {
        server.onTurnViewModified(this, new UpdateCommonGoalEvent(commonGoalType));
    }

    @Override
    public void OnUpdateModelPlayerPoint(Integer points) {
       server.onTurnViewModified(this, new UpdatePlayerPointEvent(this.gameModel.getCurrPlayer().getNickname(), points));
    }
    public void OnUpdateModelPlayerEndGame(Boolean endgame){
       server.onTurnViewModified(this, new UpdateEndGameEvent(this.gameModel.getCurrPlayer().getEndgame()));
    }
    @Override
    public void OnUpdateModelStatusCommonGoal2() {
        server.onTurnViewModified(this, new UpdateStatusCommonGoal2Event(this.gameModel.getCurrPlayer().getNickname()));
    }

    @Override
    public void OnUpdateModelStatusCommonGoal1() {
        server.onTurnViewModified(this, new UpdateStatusCommonGoal1Event(this.gameModel.getCurrPlayer().getNickname()));
    }
    //-------------------------Qua scrivo per le shelf---------------------------------//
    @Override
    public void onUpdatePuttedTileIntoShelf(int x, int y, PlayableItemTile tile){
       server.onTurnViewModified(this, new UpdatePutShelfTileEvent(x, y, tile));
    }
}
