package Network.ClientSide;

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
        turnView = server.handShake(this);
        this.userInterface.setTurnView(turnView);
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
            case REPLY_INSERT_TILE -> {

            }
            case FULL_LOBBY -> {
                this.userInterface.fullLobbyTerminateUI();
            }
        }
    }

    @Override
    public void disconnect() throws RemoteException {

    }

    @Override
    public void UpdateAllClientonModelListPlayers(Player player) {
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
    public void UpdateAllClientOnModelGameBoard(GameBoard gameBoard) {

    }

    @Override
    public void UpdateAllClientOnPickedTileFromLivingRoom(String currPlayer, int x, int y) {
        System.out.println("Il giocatore " + currPlayer + " ha pescato da LivingRoom " +
                "la tessera in posizione: x=" + x + ", y=" + y);
    }

    //
    @Override
    public void onUpdateServerInfo(Map<String, String> serverInfo) {

    }

    public void onUpdateToPickTile(int x, int y) throws RemoteException{
        server.onMessage(new ToPickTileReplyMessage(nickname, x, y));
    }

    @Override
    public void onUpdateNickname(String nickname) throws RemoteException {
        server.onMessage(new LoginRequestMessage(nickname));
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


    @Override
    public void onDisconnection() {

    }
}
