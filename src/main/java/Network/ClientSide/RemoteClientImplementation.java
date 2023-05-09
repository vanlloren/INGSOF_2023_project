package Network.ClientSide;

import Network.ServerSide.RemoteServerInterface;
import Network.message.*;
import Observer.ViewObserver;
import client.view.View;
import server.Model.PlayableItemTile;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Map;


public class RemoteClientImplementation extends Client implements RemoteClientInterface, ViewObserver {

    private RemoteServerInterface server;
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
                    //METODO CHE PRINTA LA SUA PERSONAL GOAL
                }
                this.userInterface.showLoginResults(newMessage.isNicknameUniqueAccepted(), newMessage.getNickname());
            }
            case PLAYERNUMBER_REQUEST -> {
                this.userInterface.askPlayersNumber();
                this.nickname = message.getNickname();
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
            case UPDATE_MODEL_ENDGAME -> {
                System.out.println("ENDGAME HAS BEEN LAUNCHED : THIS IS THE LAST TURN \n");

                UpdateEndGameMessage newMessage = (UpdateEndGameMessage)message;
            }
            case UPDATE_MODEL_GAMEBOARD -> {
                System.out.println("GAMEBOARD UPDATED : HERE IT IS THE CURRENT LIVING ROOM \n");
                this.userInterface.showLivingRoom();
                //CHIAMA METODO DELLA TUI CHE PRINTA LA LIVINGROOM
                UpdateModelGameBoardMessage newmessage = (UpdateModelGameBoardMessage)message;

            }
            case UPDATE_MODEL_LISTPLAYERS -> {
                System.out.println("LIST OF PLAYER UPDATED: HERE IT IS THE LIST OF ALL PLAYER \n");
                this.userInterface.showPlayersList();
                //CHIAMA METODO DELLA TUI CHE PRINTA TUTTI I NICKNAME DEI GIOCATORI PRESENTI FINO AD ORA
                UpdateModelListPlayersMessage newMessage = (UpdateModelListPlayersMessage)message;

            }
            case UPDATE_MODEL_PLAYERSNUMBER -> {
                System.out.println("NUMBER OF PLAYER UPDATED: HERE YOU HAVE THE NUMBER\n");
                this.userInterface.showNumberOfPlayers();
                //CHIAMA METODO DELLA TUI CHE PRINTA IL NUMERO DEI GIOCATORI PRESENTI FINO AD ORA
                UpdatePlayersNumberMessage newMessage = (UpdatePlayersNumberMessage)message;
            }
        }
    }

    @Override
    public void disconnect() throws RemoteException {

    }



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
    public void onUpdateAskKeepPicking(String nickname, String choice) throws RemoteException {
        server.onMessage(new KeepPickingReplyMessage(nickname, choice));
    }

    @Override
    public void onUpdatePlayersNumber(String Nickname,int playersNumber) throws RemoteException {
        server.onMessage(new PlayersNumberReplyMessage(Nickname,playersNumber) );
    }

    @Override
    public void onUpdateFirstPlayer(String nickname) throws RemoteException {

    }


    @Override
    public void onDisconnection() {

    }
}
