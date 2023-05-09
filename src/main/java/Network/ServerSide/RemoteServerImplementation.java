package Network.ServerSide;

import Network.ClientSide.RemoteClientInterface;
import Network.message.*;
import Observer.GameModelObserver;
import Util.RandPersonalGoal;
import server.Controller.GameController;
import server.Model.GameBoard;
import server.Model.PlayableItemTile;
import server.Model.Player;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class RemoteServerImplementation extends UnicastRemoteObject implements RemoteServerInterface, GameModelObserver {
    private final RMIServer server;

    private final Object lock = new Object();
    private boolean stop = false;
    private RemoteClientInterface client;
    private GameController gameController;
    private ArrayList<PlayableItemTile> availableTiles;

    RemoteServerImplementation(RMIServer server, GameController gameController) throws RemoteException {
        this.server = server;
        this.gameController = gameController;
    }

    public void resetStop(){
        stop = false;
    }


    @Override
    public void onMessage(Message message) throws RemoteException {

        switch (message.getMessageEnumeration()){
            case LOGIN_REQUEST -> {
                synchronized (lock) {
                    if (gameController.getGame().getPlayersInGame().size() < 1) {
                        Player newPlayer = new Player(message.getNickname());
                        RandPersonalGoal.setType(newPlayer, newPlayer.getPersonalGoal(), gameController.getGame().getPlayersInGame());
                        gameController.getGame().setPlayersInGame(newPlayer);
                        Message newMessage = new PlayersNumberRequestMessage(message.getNickname());
                        client.onMessage(newMessage);
                        stop = true;
                        while(stop){
                        }
                        gameController.initGameBoard();
                    } else if (gameController.getGame().getPlayersInGame().size() < gameController.getGame().getPlayersNumber()) {
                        boolean approvedNick = gameController.getGame().isNicknameAvailable(message.getNickname());
                        if(approvedNick){
                            Player newPlayer = new Player(message.getNickname());
                            RandPersonalGoal.setType(newPlayer, newPlayer.getPersonalGoal(), gameController.getGame().getPlayersInGame());
                            gameController.getGame().setPlayersInGame(newPlayer);
                        }
                        Message newMessage = new LoginReplyMessage(message.getNickname(), approvedNick);
                        client.onMessage(newMessage);
                    }
                     else if ((gameController.getGame().getPlayersInGame().size() == gameController.getGame().getPlayersNumber())){
                        Message newMessage = new FullLobbyMessage();
                        client.onMessage(newMessage);
                    }
                }
            }
            case KEEP_PICKING_REPLY -> {
                KeepPickingReplyMessage newMessage = (KeepPickingReplyMessage)message;
                if(!newMessage.getKeepPicking()) {
                    gameController.setStopPicking();
                    gameController.setMoveOn();
                }
                gameController.setMoveOn();
            }
            case TO_PICK_TILE_REPLY -> {
                ToPickTileReplyMessage newMessage = (ToPickTileReplyMessage) message;
                int x = newMessage.getXPos();
                int y = newMessage.getYPos();
                gameController.setPosCurrTile(x,y);
                gameController.setMoveOn();
            }
            case REQUEST_INSERT_TILE ->{

            }
            case PLAYERNUMBER_REPLY -> {
                PlayersNumberReplyMessage newMessage = (PlayersNumberReplyMessage)message;
                int playersNum = newMessage.getNumPlayers();
                gameController.getGame().setPlayersNumber(playersNum);
                resetStop();
            }



        }
    }


    @Override
    public void disconnect() throws RemoteException {

    }

    @Override
    public boolean handShake(RemoteClientInterface client) {
        this.client = client;
        return true;
    }

    @Override
    public void update(Message message) {

    }

    public void onUpdateToPickTile(ArrayList<PlayableItemTile> availableTiles) throws RemoteException {
        client.onMessage(new ToPickTileRequestMessage(availableTiles));
    }

    @Override
    public void onUpdateModelEndGame(String Nickname,boolean endGame) throws RemoteException{
        client.onMessage(new UpdateEndGameMessage(Nickname,endGame));}

    @Override
    public void onUpdateModelListPlayers(String Nickname,ArrayList<Player> playerArrayList)throws RemoteException{
        client.onMessage(new UpdateModelListPlayersMessage(Nickname,playerArrayList));
        }

   @Override
    public void onUpdateModelPlayersNumber(String Nickname,int playersNumber) throws RemoteException{
        client.onMessage(new UpdatePlayersNumberMessage(Nickname,playersNumber));
    }
    @Override
    public void onUpdateModelChairOwner(String nickName,Player player) throws RemoteException{
        client.onMessage(new UpdateChairOwnerMessage(nickName,player));
    }
    @Override
    public void onUpdateGameBoard(String Nickname,GameBoard gameBoard) throws RemoteException{
        client.onMessage(new UpdateModelGameBoardMessage(Nickname,gameBoard));
    }



    //implementazione metodi di RemoteServerInterface
}
