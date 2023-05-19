package Network.ClientSide;

import Network.Events.*;
import Network.ServerSide.RemoteServerInterface;
import Network.message.*;
import Observer.*;
import Util.CommonGoalType;
import Util.PersonalGoalType;
import client.view.TurnView;
import client.view.View;
import server.Model.GameBoard;
import server.Model.PlayableItemTile;
import server.Model.Player;

import java.io.IOException;
import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Map;


public  class RemoteClientImplementation extends Client implements RemoteClientInterface, ViewObserver, LivingRoomObserver, ShelfObserver, PlayerObserver, GameModelObserver, CommonGoalObserver,PersonalGoalObserver {

    @Serial
    private static final long serialVersionUID = 3324195868427777436L;
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
                }
                this.userInterface.showLoginResults(newMessage.isNicknameUniqueAccepted(), newMessage.getNickname());
            }
            case PLAYERNUMBER_REQUEST -> {
                this.userInterface.askPlayersNumber();
                this.nickname = message.getNickname();
                this.userInterface.setNickname(this.nickname);
            }
            case KEEP_PICKING_REQUEST -> this.userInterface.askStopPicking();
            case INVALID_TILE -> {
                this.userInterface.invalidTileHandler();
            }
            case TO_PICK_TILE_REQUEST -> {
                ToPickTileRequestMessage newMessage = (ToPickTileRequestMessage) message;
                ArrayList<PlayableItemTile> availableTiles = newMessage.getAvailableTiles();
                this.userInterface.askMovingTilePosition(availableTiles);
            }
            case TO_PUT_TILE_REPLY_ERROR-> {
                ToPutTileReplyMessage newMessage = (ToPutTileReplyMessage) message;
                ArrayList<PlayableItemTile> tilesInPlayerHand = newMessage.getPlayableItemTile();
                this.userInterface.showNegativePutTileResults(tilesInPlayerHand);
            }

            case TO_PUT_TILE_2_OR_3_REPLY_ERROR ->{
                ToPutTile2Or3ReplyMessage newMessage = (ToPutTile2Or3ReplyMessage) message;
                ArrayList<PlayableItemTile> tilesInPlayerHand = newMessage.getPlayableItemTile();
                this.userInterface.showNegativePut2Or3TileResults(tilesInPlayerHand);
            }
            case KEEP_PUTTING_REQUEST -> {
                ToKeepPuttingMessage newMessage = (ToKeepPuttingMessage) message;
                ArrayList<PlayableItemTile> tilesInPlayerHand = newMessage.getPlayableItemTiles();
                this.userInterface.askTileToPut2or3tile(tilesInPlayerHand);
            }
            case FULL_LOBBY -> {
                this.userInterface.fullLobbyTerminateUI();
            }
            case START_PICKING_TILE_REPLY -> {
                this.userInterface.setMoveOn();
            }
            case START_PUTTING_TILE_REQUEST -> { //alf questo è esattamente uguale al tuo toPutFirstTile, quindi l'ho tolto
                StartPuttingTileRequestMessage newMessage = (StartPuttingTileRequestMessage) message;
                this.userInterface.askTileToPut(newMessage.getTilesArray());
            }
            case MAX_TILE_PICKED -> {
                this.userInterface.maxTilesPicked();
            }



        }
    }

    @Override
    public void onModelModify(TurnView turnView, Event event){
        this.turnView=turnView;
        switch (event.getEventType()){
            case UPDATE_PLAYERS_LIST -> {
                UpdatePlayersListEvent newEvent = (UpdatePlayersListEvent) event;
                UpdateAllClientOnModelListPlayers(newEvent.getPlayer());
            }
            case UPDATE_END_GAME -> {
                UpdateEndGameEvent newEvent = (UpdateEndGameEvent) event;
                UpdateAllClientOnModelEndGame(newEvent.getPlayer(),newEvent.isEndGame());
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
            case UPDATE_CHAT -> {
                UpdateChatEvent newEvent = (UpdateChatEvent) event;
                UpdateAllClientOnNewMessageChat(newEvent.getNickname(),newEvent.getChat());

            }
        }
    }
    @Override
    public void UpdateAllClientOnModelGameHasEnd() {
        this.userInterface.resetGameOn();
        System.out.println(".......................\n" +
                           ".......................\n" +
                           ".......................\n" +
                           ".......................\n" +
                           ".......................\n" +
                           ".......GAME OVER......."
               );

    }
    @Override
    public void UpdateAllClientOnModelMatchWinner(String matchWinner) {
        System.out.println("CONGRATULATION : "+matchWinner+" HAS WON THIS GAME");
    }
    @Override
    public void UpdateAllClientOnModelCurrPlayer(Player currPlayer) {
        this.userInterface.setCurrPlayer(currPlayer.getNickname());
        System.out.println("NEW UPDATE: "+currPlayer.getNickname()+"is now the current player");
    }

    @Override
    public void disconnect() throws RemoteException {

    }

    @Override
    public void UpdateAllClientOnModelListPlayers(Player player) {
       System.out.println("...NEW UPDATE: We have a new player: "+player.getNickname()+" has joined this lobby");
    }

    @Override
    public void UpdateAllClientOnModelEndGame(String Nickname,boolean endGame) {
        System.out.println(Nickname+" HAS FILLED ALL HIS SHELF FOR FIRST :" +
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
        this.userInterface.askPlayerNextMove();
    }

    @Override
    public void UpdateAllClientOnModelGameHasStarted() {
        System.out.println("LOBBY HAS REACHED THE REQUESTED NUMBER OF PLAYER: GAME IS STARTING.....");

    }

    @Override
    public void onUpdateAllClientOnCurrentPlayer(Player currPlayer) {
        System.out.println("...NEW UPDATE: Now it's"+currPlayer.getNickname()+" turn");
    }

    @Override
    public void UpdateAllClientOnStructureShelf(int x, int y, PlayableItemTile Tile) {
        System.out.println("Il giocatore " + turnView.getNicknameCurrentPlayer() + " ha posizionato la tessera "
        + Tile.getColour() + " " + Tile.getIdCode() + " nella posizione x=" + x + ", y=" + y);

        System.out.println("A seguire verrà stampata la PlayerShelf aggiornata");
        this.userInterface.showPlayerShelf(turnView.getShelfTable(turnView.getNicknameCurrentPlayer()));

    }

    @Override
    public void UpdateAllClientOnModelGameBoard(GameBoard gameBoard) {

    }

    @Override
    public void UpdateAllClientOnNewMessageChat(String Nickname, String chatMessage) {
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

    public void onUpdateToPutTile( int xPos, int yPos , PlayableItemTile tile , ArrayList<Integer> columnPosition , int numOfTiles , ArrayList<PlayableItemTile> playableItemTiles) throws RemoteException {
        server.onMessage(new ToPutTileRequestMessage( xPos, yPos , tile , columnPosition , numOfTiles , playableItemTiles));
    }

    public void onUpdateToPut2or3Tile(int finalXPos,PlayableItemTile tile , ArrayList<PlayableItemTile> playableItemTiles) throws RemoteException {
        server.onMessage(new ToPut2Or3TileRequestMessage(finalXPos , tile , playableItemTiles));
    }


    public void onUpdateStartPicking() throws RemoteException{
        server.onMessage(new StartPickingTileRequestMessage(nickname));
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
    public void onUpdateChat(String Nickname, String chat) throws RemoteException {
        server.onMessage(new WriteInChatMessage(Nickname, chat));
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
        this.userInterface.setCurrPlayer(turnView.getNicknameCurrentPlayer());
    }

    @Override
    public void onUpdateShowAvailableTiles(){
        this.userInterface.askMovingTilePosition(turnView.getLivingRoom().getAvailableTiles());
    }

    @Override
    public void onUpdateShowPlayersList() {
        this.userInterface.showPlayersList(turnView.getGameModel().getPlayersInGame());
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
        this.userInterface.showNickCurrentPlayer(turnView.getNicknameCurrentPlayer());
    }




    @Override
    public void onDisconnection() {

    }

    @Override
    public void onUpdateModelListPlayers(TurnView turnView, Player player) {
        onModelModify(turnView, new UpdatePlayersListEvent(player));
    }

    @Override
    public void onUpdateModelEndGame(TurnView turnView,Player player, boolean endGame)  {
        onModelModify(turnView, new UpdateEndGameEvent(player.getNickname(), endGame));
    }

    @Override
    public void onUpdateModelPlayersNumber(TurnView turnView, int playersNumber)  {
        onModelModify(turnView, new UpdatePlayersNumberEvent(playersNumber));
    }

    @Override
    public void onUpdateModelChairOwner(TurnView turnView, Player player) {
        onModelModify(turnView, new UpdateChairOwnerEvent(player));
    }

    @Override
    public void onUpdateGameBoard(TurnView turnView, GameBoard gameBoard) {
        onModelModify(turnView, new UpdateGameBoardEvent(gameBoard));
    }

    @Override
    public void onUpdateModelGameHasStarted(TurnView turnView) {
        onModelModify(turnView, new UpdateGameHasStartedEvent());
    }

    @Override
    public void onUpdateModelCurrentPlayer(TurnView turnView, Player currPlayer) {
        onModelModify(turnView, new UpdateCurrPlayerEvent(currPlayer));
    }

    @Override
    public void onUpdateModelMatchWinner(TurnView turnView, String player) {
        onModelModify(turnView, new UpdateMatchWinnerEvent(player));
    }

    @Override
    public void onUpdateModelGameHasEnd(TurnView turnView) {
        onModelModify(turnView, new UpdateGameHasEndEvent());
    }

    @Override
    public void onUpdateModelChat(TurnView turnView, String nickname, String chat) {
        onModelModify(turnView, new UpdateChatEvent(nickname,chat));
    }

    @Override
    public void onUpdatePickedTileFromLivingRoom(TurnView turnView, int x, int y) {
        onModelModify(turnView, new UpdatePickedLivingRoomTileEvent(turnView.getNicknameCurrentPlayer() ,x, y));
    }

    @Override
    public void OnUpdateModelPersonalGoal(TurnView turnView, PersonalGoalType personalGoalType, String nickname) {
        onModelModify(turnView, new UpdatePersonalGoalEvent(nickname, personalGoalType));
    }

    @Override
    public void OnUpdateModelCommonGoal(TurnView turnView, CommonGoalType commonGoalType) {
        onModelModify(turnView, new UpdateCommonGoalEvent(commonGoalType));
    }

    @Override
    public void OnUpdateModelPlayerPoint(TurnView turnView, Integer points) {
        onModelModify(turnView, new UpdatePlayerPointEvent(turnView.getNicknameCurrentPlayer(), points));
    }

    @Override
    public void OnUpdateModelStatusCommonGoal2(TurnView turnView) {
        onModelModify(turnView, new UpdateStatusCommonGoal2Event(turnView.getNicknameCurrentPlayer()));
    }

    @Override
    public void OnUpdateModelStatusCommonGoal1(TurnView turnView) {
        onModelModify(turnView, new UpdateStatusCommonGoal1Event(turnView.getNicknameCurrentPlayer()));
    }



    //-------------------------Qua scrivo per le shelf---------------------------------//
    @Override
    public void onUpdatePuttedTileIntoShelf(TurnView turnView, int x, int y, PlayableItemTile tile){
        onModelModify(turnView, new UpdatePutShelfTileEvent(x, y, tile));
    }
}
