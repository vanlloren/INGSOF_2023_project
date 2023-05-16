package Network.ServerSide;

import Network.Events.*;
import Observer.*;
import Util.CommonGoalType;
import Util.PersonalGoalType;
import server.Model.GameBoard;
import server.Model.GameModel;
import server.Model.PlayableItemTile;
import server.Model.Player;

public class ProxyObserver implements LivingRoomObserver, ShelfObserver, PlayerObserver, GameModelObserver, CommonGoalObserver,PersonalGoalObserver{
    private GameModel gameModel;
    private RemoteServerImplementation server;

    public void setGameModel(GameModel gameModel){
        this.gameModel=gameModel;
    }
    public void setServer(RemoteServerImplementation server){
        this.server=server;
    }
    @Override
    public void onUpdateModelListPlayers(Player player) {
        server.onTurnViewModified( new UpdatePlayersListEvent(player));
    }

    @Override
    public void onUpdateModelEndGame(boolean endGame)  {
        server.onTurnViewModified( new UpdateEndGameEvent(endGame));
    }

    @Override
    public void onUpdateModelPlayersNumber(int playersNumber)  {
        server.onTurnViewModified( new UpdatePlayersNumberEvent(playersNumber));
    }

    @Override
    public void onUpdateModelChairOwner(Player player) {
        server.onTurnViewModified( new UpdateChairOwnerEvent(player));
    }

    @Override
    public void onUpdateGameBoard(GameBoard gameBoard) {
        server.onTurnViewModified( new UpdateGameBoardEvent(gameBoard));
    }

    @Override
    public void onUpdateModelGameHasStarted() {
        server.onTurnViewModified( new UpdateGameHasStartedEvent());
    }

    @Override
    public void onUpdateModelCurrentPlayer(Player currPlayer) {
        server.onTurnViewModified( new UpdateCurrPlayerEvent(currPlayer));
    }

    @Override
    public void onUpdateModelMatchWinner(String player) {
        server.onTurnViewModified( new UpdateMatchWinnerEvent(player));
    }

    @Override
    public void onUpdateModelGameHasEnd() {
        server.onTurnViewModified( new UpdateGameHasEndEvent());
    }

    @Override
    public void onUpdatePickedTileFromLivingRoom(int x, int y) {
        server.onTurnViewModified( new UpdatePickedLivingRoomTileEvent(this.gameModel.getCurrPlayer().getNickname() ,x, y));
    }

    @Override
    public void OnUpdateModelPersonalGoal(PersonalGoalType personalGoalType) {
        server.onTurnViewModified( new UpdatePersonalGoalEvent( ,personalGoalType));
    }

    @Override
    public void OnUpdateModelCommonGoal(CommonGoalType commonGoalType) {
        server.onTurnViewModified( new UpdateCommonGoalEvent(commonGoalType));
    }

    @Override
    public void OnUpdateModelPlayerPoint(Integer points) {
        server.onTurnViewModified( new UpdatePlayerPointEvent(this.gameModel.getCurrPlayer().getNickname(), points));
    }
    public void OnUpdateModelPlayerEndGame(Boolean endgame){
        server.onTurnViewModified( new UpdateEndGameEvent(this.gameModel.getCurrPlayer().getEndgame()));
    }
    @Override
    public void OnUpdateModelStatusCommonGoal2() {
        server.onTurnViewModified( new UpdateStatusCommonGoal2Event(this.gameModel.getCurrPlayer().getNickname()));
    }

    @Override
    public void OnUpdateModelStatusCommonGoal1() {
        server.onTurnViewModified(new UpdateStatusCommonGoal1Event(this.gameModel.getCurrPlayer().getNickname()));
    }
    //-------------------------Qua scrivo per le shelf---------------------------------//
    @Override
    public void onUpdatePuttedTileIntoShelf(int x, int y, PlayableItemTile tile){
        server.onTurnViewModified(new UpdatePutShelfTileEvent(x, y, tile));
    }
}
