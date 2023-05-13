package Network.ClientSide;

import Network.Events.*;
import Network.ServerSide.RemoteServerInterface;
import Network.message.*;
import Observer.ViewObserver;
import Util.CommonGoalType;
import Util.PersonalGoalType;
import client.view.TurnView;
import client.view.View;
import server.Model.GameBoard;
import server.Model.PlayableItemTile;
import server.Model.Player;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Map;


public  class RemoteClientImplementation extends Client implements RemoteClientInterface, ViewObserver {

    private RemoteServerInterface server;
    private TurnView turnView;
    private String nickname;



    public RemoteClientImplementation(String address, int port, View userInterface) throws RemoteException {
        super(address, port, userInterface);
    }
    @Override
    public void connectionInit() throws Exception {
        Registry registry = LocateRegistry.getRegistry(getServerAddress(), getPortNum());
        server = (RemoteServerInterface) registry.lookup("MyShelfieServer");
        server.handShake(this);
    }

    @Override
    public void sendMessage(Message message) throws IOException {

    }

    @Override
    public void closeConnection() throws Exception {

    }

    @Override
    public void onMessage(Message message) throws RemoteException {
        switch (message.getMessageEnumeration()){
            case LOGIN_REPLY -> {
                LoginReplyMessage newMessage = (LoginReplyMessage)message;
                if(newMessage.isNicknameUniqueAccepted()) {
                    this.nickname = newMessage.getNickname();
                    this.userInterface.setNickname(this.nickname);
                    //METODO CHE PRINTA LA SUA PERSONAL GOAL
                }
                this.userInterface.showLoginResults(newMessage.isNicknameUniqueAccepted(), newMessage.getNickname());
            }
            case PLAYERNUMBER_REQUEST -> {
                this.userInterface.askPlayersNumber();
                this.nickname = message.getNickname();
                this.userInterface.setNickname(this.nickname);
            }
            case KEEP_PICKING_REQUEST -> {
                this.userInterface.askStopPicking();
            }
            case TO_PICK_TILE_REQUEST -> {
                ToPickTileRequestMessage newMessage = (ToPickTileRequestMessage) message;
                ArrayList<PlayableItemTile> availableTiles = newMessage.getAvailableTiles();
                this.userInterface.askMovingTilePosition(availableTiles);
            }
            case TO_PUT_TILE_REQUEST -> {

                this.userInterface.showPutTileResults(boolean errorInTheInsertion ,String errorType , ArrayList<PlayableItemTile> tilesInPlayerHand);
            }
            case FULL_LOBBY -> {
                this.userInterface.fullLobbyTerminateUI();
            }



        }
    }

    public void onModelModify(TurnView turnView, Event event){
        this.turnView=turnView;
        switch (event.getEventType()){
            case UPDATE_PLAYERS_LIST -> {
                UpdatePlayersListEvent newEvent = (UpdatePlayersListEvent) event;
                UpdateAllClientOnModelListPlayers(newEvent.getPlayer());
            }
            case UPDATE_END_GAME -> {
                UpdateEndGameEvent newEvent = (UpdateEndGameEvent) event;
                UpdateAllClientOnModelEndGame(newEvent.isEndGame());
            }
            case UPDATE_PLAYERS_NUMBER -> {
                UpdatePlayersNumberEvent newEvent = (UpdatePlayersNumberEvent) event;
                UpdateAllClientOnPlayersNumber(newEvent.getPlayersNumber());
            }
            case UPDATE_CHAIR_OWNER -> {
                UpdateChairOwnerEvent newEvent = (UpdateChairOwnerEvent) event;
                UpdateAllClientOnChairOwner(newEvent.getPlayer());
            }
            case UPDATE_GAME_BOARD -> {
                UpdateGameBoardEvent newEvent = (UpdateGameBoardEvent) event;
                UpdateAllClientOnModelGameBoard(newEvent.getGameBoard());
            }
            case UPDATE_GAME_HAS_STARTED -> {
                UpdateGameHasStartedEvent newEvent = (UpdateGameHasStartedEvent) event;
                UpdateAllClientOnModelGameHasStarted();
            }
            case UPDATE_CURR_PLAYER -> {
                UpdateCurrPlayerEvent newEvent = (UpdateCurrPlayerEvent) event;
                UpdateAllClientOnModelCurrPlayer(newEvent.getCurrPlayer());
            }
            case UPDATE_MATCH_WINNER -> {
                UpdateMatchWinnerEvent newEvent = (UpdateMatchWinnerEvent) event;
                UpdateAllClientOnModelMatchWinner(newEvent.getMatchWinner());
            }
            case UPDATE_GAME_HAS_ENDED -> {
                UpdateGameHasEndEvent newEvent = (UpdateGameHasEndEvent) event;
                UpdateAllClientOnModelGameHasEnd();
            }
            case UPDATE_PICKED_LIVINGROOM_TILE -> {
                UpdatePickedLivingRoomTileEvent newEvent = (UpdatePickedLivingRoomTileEvent) event;
                UpdateAllClientOnPickedTileFromLivingRoom(newEvent.getCurrPlayer(), newEvent.getXPos(), newEvent.getYPos());
            }
            case UPDATE_PERSONAL_GOAL -> {
                UpdatePersonalGoalEvent newEvent = (UpdatePersonalGoalEvent) event;
                UpdateAllClientOnModelPersonalGoal(newEvent.getPlayer(), newEvent.getPersonalGoalType());
            }
            case UPDATE_COMMON_GOAL -> {
                UpdateCommonGoalEvent newEvent = (UpdateCommonGoalEvent) event;
                UpdateAllClientOnModelCommonGoal(newEvent.getCommonGoalType());
            }
            case UPDATE_PLAYER_POINTS -> {
                UpdatePlayerPointEvent newEvent = (UpdatePlayerPointEvent) event;
                UpdateAllClientOnModelPlayerPoint(newEvent.getPlayer(), newEvent.getPoints());
            }
            case UPDATE_STATUS_COMMON_GOAL2 -> {
                UpdateStatusCommonGoal2Event newEvent = (UpdateStatusCommonGoal2Event) event;
                UpdateAllClientOnModelStatusCommonGoal2(newEvent.getNickname());
            }
            case UPDATE_STATUS_COMMON_GOAL1 -> {
                UpdateStatusCommonGoal1Event newEvent = (UpdateStatusCommonGoal1Event) event;
                UpdateAllClientOnModelStatusCommonGoal1(newEvent.getNickname());
            }
            case UPDATE_PUT_SHELF_TILE -> {
                UpdatePutShelfTileEvent newEvent = (UpdatePutShelfTileEvent) event;
                UpdateAllClientOnStructureShelf(newEvent.getXPos(), newEvent.getYPos(), newEvent.getTile());
            }
        }
    }

    @Override
    public void disconnect() throws RemoteException {

    }

    @Override
    public void UpdateAllClientOnModelListPlayers(Player player) {
       System.out.println("...NEW UPDATE: We have a new player: "+player.getNickname()+" has joined this lobby");
    }

    @Override
    public void UpdateAllClientOnModelEndGame(boolean endGame) {
        System.out.println("CURRENT PLAYER HAS FILLED ALL HIS SHELF FOR FIRST :" +
                           "lAST TURN IN GAME....");
    }
    // gestisci un booleano per quando sei arrivato all'ultimo giocatore del turno finale
    // dal controller si determina il vincitore si comunica e a quel punto lancio

    @Override
    public void UpdateAllClientOnPlayersNumber(int playersNumber) {

        System.out.println("Player 1 your request of playerNumber for this game has been accepted" +
                           "This game will have "+playersNumber+" players");

    }



    @Override
    public void UpdateAllClientOnModelPersonalGoal(String Nickname,PersonalGoalType personalGoalType) {
    System.out.println("....NEW UPDATE:The PersonalGoal assigned to player "+Nickname+" is "+personalGoalType);
    }

    @Override
    public void UpdateAllClientOnModelCommonGoal(CommonGoalType commonGoalType) {
    System.out.println("....NEW UPDATE:CommonGoal for this game is "+commonGoalType);
    }

    @Override
    public void UpdateAllClientOnModelPlayerPoint(String nickNameCurrentPlayer, Integer points) {
        System.out.println("....NEW UPDATE:"+nickNameCurrentPlayer+"has now"+points+"points");
    }

    @Override
    public void UpdateAllClientOnModelStatusCommonGoal1(String nickNameCurrentPlayer) {
        System.out.println("...NEW UPDATE:"+nickNameCurrentPlayer+"has satisfied CommonGoal1");
    }

    @Override
    public void UpdateAllClientOnModelStatusCommonGoal2(String nickNameCurrentPlayer) {
        System.out.println("...NEW UPDATE:"+nickNameCurrentPlayer+"has satisfied CommonGoal2");
    }

    @Override
    public void UpdateAllClientOnChairOwner(Player player) {
        System.out.println("...NEW UPDATE: The chair owner is "+player.getNickname());
    }

    @Override
    public void UpdateAllClientOnModelGameHasStarted() {
        System.out.println("LOBBY HAS REACHED THE REQUESTED NUMBER OF PLAYER: GAME IS STARTING.....");
        this.userInterface.askPlayerNextMove();
    }

    @Override
    public void onUpdateAllClientOnCurrentPlayer(Player currPlayer) {
        System.out.println("...NEW UPDATE: Now it's"+currPlayer.getNickname()+" turn");
    }

    @Override
    public void UpdateAllClientOnStructureShelf(int x, int y, PlayableItemTile Tile) {
        System.out.println("Il giocatore " + turnView.getNickNameCurrentPlayer() + " ha posizionato la tessera "
        + Tile.getColour() + " " + Tile.getIdCode() + " nella posizione x=" + x + ", y=" + y);

        System.out.println("A seguire verrà stampata la PlayerShelf aggiornata");
        this.userInterface.showPlayerShelf(turnView.getShelfTable(turnView.getNickNameCurrentPlayer()));

    }

    @Override
    public void UpdateAllClientOnModelGameBoard(GameBoard gameBoard) {

    }

    @Override
    public void UpdateAllClientonNewMessageChat(String Nickname, String chatMessage) {
        System.out.println(Nickname+": "+chatMessage+".");
    }

    @Override
    public void UpdateAllClientOnPickedTileFromLivingRoom(String currPlayer, int x, int y) {
        System.out.println("Il giocatore " + currPlayer + " ha pescato da LivingRoom " +
                "la tessera in posizione: x=" + x + ", y=" + y);

        System.out.println("A seguire verrà stampata la LivingRoom aggiornata");
        this.userInterface.showLivingRoom(turnView.getLivingRoom());
    }

    //
    @Override
    public void onUpdateServerInfo(Map<String, String> serverInfo) {

    }

    public void onUpdateToPickTile(int x, int y) throws RemoteException{
        server.onMessage(new ToPickTileReplyMessage(nickname, x, y));
    }

    public void onUpdateToPutTile(boolean flag, int xPos, int yPos) throws RemoteException {
        server.onMessage(new ToPutTileReplyMessage(flag, xPos, yPos));
    }

    @Override
    public void onUpdateNickname(String nickname) throws RemoteException {
        server.onMessage(new LoginRequestMessage(this, nickname));
    }

    @Override
    public void onUpdateAskKeepPicking(String choice) throws RemoteException {
        server.onMessage(new KeepPickingReplyMessage(nickname, choice));
    }

    @Override
    public void onUpdatePlayersNumber(int playersNumber) throws RemoteException {
        server.onMessage(new PlayersNumberReplyMessage(playersNumber) );
    }

    @Override
    public void onUpdateFirstPlayer(String nickname) throws RemoteException {

    }

    public void onUpdateIsGameOn(){
        if(turnView.getIsGameOn()){
            this.userInterface.resetGameOn();
        }
    }

    public void onUpdateShowLivingRoom(){
        this.userInterface.showLivingRoom(turnView.getLivingRoom());
    }

    @Override
    public void onUpdateShowCurrPlayer(){
        this.userInterface.setCurrPlayer(turnView.getNickNameCurrentPlayer());
    }

    @Override
    public void onUpdateShowAvailableTiles(){
        this.userInterface.askMovingTilePosition(turnView.getLivingRoom().getAvailableTiles());
    }

    @Override
    public void onUpdateShowPlayersList() {
        this.userInterface.showPlayersList(turnView.getPlayerInGame());
    }

    @Override
    public void onUpdateShowPlayerShelf(String nickname) {
        this.userInterface.showPlayerShelf(turnView.getShelfTable(nickname));
    }

    @Override
    public void onUpdateShowPartialPoint(String nickname) {
        this.userInterface.showPartialPoint(turnView.getPartialPoint(nickname));
    }

    @Override
    public void onUpdateShowNickCurrPlayer() {
        this.userInterface.showNickCurrentPlayer(turnView.getNickNameCurrentPlayer());
    }


    @Override
    public void onDisconnection() {

    }
}
