package Observer;


import java.rmi.RemoteException;

public interface ViewObserver {


    /**
     * Sends a message to the server with the updated nickname.
     *
     * @param nickname the nickname to be sent.
     */
     void onUpdateNickname(String nickname) throws RemoteException;


    /**
     * Sends a message to the server with the player number chosen by the user.
     *
     * @param playersNumber the number of players.
     */
     void onUpdatePlayersNumber(int playersNumber) throws RemoteException;


    /**
     * Handles a disconnection wanted by the user.
     * (e.g. a click on the back button into the GUI).
     */
    void onDisconnection();

    void onUpdateToPickTile() throws RemoteException;

    void onUpdateShowLivingRoom() ;

    void onUpdateShowCurrPlayer();

    void onUpdateShowAvailableTiles();

    void onUpdateShowPlayersList();

    void onUpdateShowPlayerShelf(String nickname);

    int onUpdateShowPartialPoint(String nickname);

    void onUpdateShowNickCurrPlayer();

    void onUpdateChat(String Nickname, String chat) throws RemoteException;

    void onUpdateToStartPutting() throws RemoteException;

    void onConnectionVerify() throws RemoteException;
}
