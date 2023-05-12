package client.view;


import Observer.*;
import Util.CommonGoalType;
import Util.PersonalGoalType;
import server.Model.*;
import java.util.ArrayList;


public class TurnView extends TurnViewObservable implements LivingRoomObserver, ShelfObserver, PlayerObserver, GameModelObserver, PersonalGoalObserver,CommonGoalObserver {
   private final GameModel gameModel;

   public TurnView(GameModel gameModel) {  //CAPIRE DOVE FARE QUESTO PASSAGGIO, PROBABILMENTE VA FATTO PROPRIO ALL'INIZIO ALL'AVVIO DEL GAME E PASSARE CON UN SET PER OGNI TUI
       gameModel = new GameModel();
       this.gameModel = gameModel;

   }

   public GameBoard getGameBoard(){
       return this.gameModel.getMyShelfie();
   }


    public ArrayList<Player> getPlayerInGame() {
        return gameModel.getPlayersInGame();
    }





    public int getPlayersNumber(){
       return this.gameModel.getPlayersInGame().size();
    }

    public String getNickNameCurrentPlayer(){
       return gameModel.getCurrPlayer().getNickname();
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
}
