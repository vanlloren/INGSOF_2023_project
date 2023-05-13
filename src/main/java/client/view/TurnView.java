package client.view;

import Observer.*;
import Util.CommonGoalType;
import Util.PersonalGoalType;
import server.Model.*;
import java.util.ArrayList;


public class TurnView extends TurnViewObservable implements LivingRoomObserver, ShelfObserver, PlayerObserver, GameModelObserver,CommonGoalObserver,PersonalGoalObserver {
   private GameModel gameModel;

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

    public Shelf getShelfTable(){
    return this.gameModel.getCurrPlayer().getPersonalShelf();
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
        notifyObservers(obs -> obs.UpdateAllClientonNewMessageChat(Nickname,chatMessage));

    }

    @Override
    public void onUpdateModelListPlayers(Player player) {
    notifyObservers(obs -> obs.UpdateAllClientonModelListPlayers(player));
    }

    @Override
    public void onUpdateModelEndGame(boolean endGame)  {
        notifyObservers(obs -> obs.UpdateAllClientOnModelEndGame(endGame));
    }

    @Override
    public void onUpdateModelPlayersNumber(int playersNumber)  {
        notifyObservers(obs -> obs.UpdateAllClientOnPlayersNumber(playersNumber));
    }

    @Override
    public void onUpdateModelChairOwner(Player player) {
        notifyObservers(obs -> obs.UpdateAllClientOnChairOwner(player));

    }

    @Override
    public void onUpdateGameBoard(GameBoard gameBoard) {
        notifyObservers(obs -> obs.UpdateAllClientOnModelGameBoard(gameBoard));
    }

    @Override
    public void onUpdateModelGameHasStarted() {
        notifyObservers(obs -> obs.UpdateAllClientOnModelGameHasStarted());
    }

    @Override
    public void onUpdateModelCurrentPlayer(Player currPlayer) {
        notifyObservers(obs -> obs.onUpdateAllClientOnCurrentPlayer(currPlayer));

    }

    @Override
    public void onUpdateModelMatchWinner(String player) {

    }

    @Override
    public void onUpdateModelGameHasEnd() {

    }

    @Override
    public void onUpdatePickedTileFromLivingRoom(int x, int y) {
        notifyObservers(obs -> obs.UpdateAllClientOnPickedTileFromLivingRoom(getNickNameCurrentPlayer(), x, y));
    }

    @Override
    public void OnUpdateModelPersonalGoal(PersonalGoalType personalGoalType) {
        notifyObservers(obs -> obs.UpdateAllClientOnModelPersonalGoal(getNickNameCurrentPlayer(),personalGoalType));
    }

    @Override
    public void OnUpdateModelCommonGoal(CommonGoalType commonGoalType) {
        notifyObservers(obs -> obs.UpdateAllClientOnModelCommonGoal(commonGoalType));

    }

    @Override
    public void OnUpdateModelPlayerPoint(Integer points) {
        notifyObservers(obs -> obs.UpdateAllClientOnModelPlayerPoint(getNickNameCurrentPlayer(),points));

    }

    @Override
    public void OnUpdateModelStatusCommonGoal2() {
        notifyObservers(obs -> obs.UpdateAllClientOnModelStatusCommonGoal1(getNickNameCurrentPlayer()));

    }

    @Override
    public void OnUpdateModelStatusCommonGoal1() {
        notifyObservers(obs -> obs.UpdateAllClientOnModelStatusCommonGoal2(getNickNameCurrentPlayer()));

    }
    //-------------------------Qua scrivo per le shelf---------------------------------//
    @Override
    public void onUpdatePuttedTileIntoShelf(int x, int y, PlayableItemTile Tile){
       notifyObservers(obs -> {
           obs.UpdatellClientonStructureShelf(x , y , Tile);
       });
    }
}
