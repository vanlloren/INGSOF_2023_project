package Observer;
import server.Model.PlayableItemTile;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public interface ViewObserver {

    /**
     * Create a new connection to the server with the updated info.
     *
     * @param serverInfo a map of server address and server port.
     */
    void onUpdateServerInfo(Map<String, String> serverInfo) throws RemoteException;

    /**
     * Sends a message to the server with the updated nickname.
     *
     * @param nickname the nickname to be sent.
     */
     void onUpdateNickname(String nickname) throws RemoteException;

    /**
     * Sends a message to the server with the updated choice of keep picking tiles.
     *
     * @param choice the choice to be sent.
     */
     void onUpdateAskKeepPicking(String choice) throws RemoteException;
    /**
     * Sends a message to the server with the player number chosen by the user.
     *
     * @param playersNumber the number of players.
     */
     void onUpdatePlayersNumber(int playersNumber) throws RemoteException;

    /**
     * Sends a message to the server with the nickname of the first player chosen by the user.
     *
     * @param nickname the nickname of the first player.
     */
    void onUpdateFirstPlayer(String nickname) throws RemoteException;

    /**
     * Handles a disconnection wanted by the user.
     * (e.g. a click on the back button into the GUI).
     */
    void onDisconnection();

    void onUpdateToPickTile(int xPos, int yPos) throws RemoteException;

    void onUpdateToPutTile(int xPos, int yPos , PlayableItemTile tile , ArrayList<Integer> columnPosition , int numOfTiles , ArrayList<PlayableItemTile> playableItemTiles) throws  RemoteException;
    void onUpdateToPut2or3Tile(int finalXPos, PlayableItemTile tile , ArrayList<PlayableItemTile> playableItemTiles ) throws RemoteException;
    void onUpdateIsGameOn();

    void onUpdateShowLivingRoom() ;

    void onUpdateShowCurrPlayer();

    void onUpdateShowAvailableTiles();

    void onUpdateStartPicking() throws RemoteException;

    void onUpdateShowPlayersList();

    void onUpdateShowPlayerShelf(String nickname);

    void onUpdateShowPartialPoint(String nickname);

    void onUpdateShowNickCurrPlayer();

    void onUpdateChat(String Nickname, String chat) throws RemoteException;
}
