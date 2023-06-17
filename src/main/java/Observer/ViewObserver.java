package Observer;


import server.Model.LivingRoom;
import server.Model.Player;
import server.Model.Shelf;

import java.rmi.RemoteException;

/**
 * This Interface represents the adaptation of the standard {@link java.util.Observer Observer} Interface
 * to the methods and objects in use inside the Classes that needs to be updated
 * following changes in the {@link client.view.View View} Interface or in its implementations
 */
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
     * Tells the {@link Network.ServerSide.RemoteServerImplementation RemoteServer} that a
     * {@link Player Player} wants to begin picking {@link server.Model.PlayableItemTile PlayableItemTiles}
     * from the {@link server.Model.LivingRoom LivingRoom}
     *
     * @throws RemoteException exception that underlines problems with the RMI Connection
     */
    void onUpdateToPickTile() throws RemoteException;

    /**
     * Tells the {@link Network.ClientSide.RemoteClientImplementation RemoteClient} that a
     * {@link Player Player} wants to see the current situation of the {@link server.Model.LivingRoom LivingRoom}
     */
    void onUpdateShowLivingRoom() ;

    /**
     * Tells the {@link Network.ClientSide.RemoteClientImplementation RemoteClient} that a
     * {@link Player Player} wants to see the nickname of the {@code currPlayer} of the match
     */
    void onUpdateShowCurrPlayer();

    /**
     * Informs the {@link Network.ClientSide.RemoteClientImplementation RemoteClient} that the {@link Player Player}
     * has asked to see the {@link java.util.ArrayList ArrayList} containing the available {@link server.Model.PlayableItemTile PlayableItemTile}
     * in the {@link LivingRoom LivingRoom}
     */
    void onUpdateShowAvailableTiles();

    /**
     * Informs the {@link Network.ClientSide.RemoteClientImplementation RemoteClient} that the {@link Player Player}
     * has asked to see the {@link java.util.ArrayList ArrayList} containing the {@link Player Players}
     * in the current match MODIFICAREEEEEEE
     */
    void onUpdateSetPlayersList();

    /**
     * Informs the {@link Network.ClientSide.RemoteClientImplementation RemoteClient} that the {@link Player Player}
     * has asked to see the current situation of the {@link Shelf Shelf}
     *
     * @param nickname the {@code nickname} of the {@link Player Player} that makes the request
     */
    void onUpdateShowPlayerShelf(String nickname);

    /**
     * Informs the {@link Network.ClientSide.RemoteClientImplementation RemoteClient} that the {@link Player Player}
     * has asked to see the current total of the points scored in the match
     *
     * @param nickname the {@code nickname} of the {@link Player Player} that makes the request
     */
    int onUpdateShowPartialPoint(String nickname);

    /**
     * Informs the {@link Network.ClientSide.RemoteClientImplementation RemoteClient} that the
     * {@link Player Player} has requested to see the {@code nickname} of the {@code currPlayer}
     * of the match
     */
    void onUpdateShowNickCurrPlayer();

    /**
     * This method notifies to the {@link Network.ClientSide.RemoteClientImplementation RemoteClient}
     * that a {@link Player Player} wants to write a message in the game chat
     *
     * @param Nickname the {@code nickname} of the {@link Player Player} who wants to send the message
     * @param chat a {@link String String} representing the actual content of the chat message
     * @throws RemoteException exception that underlines problems with the RMI Connection
     */
    void onUpdateChat(String Nickname, String chat,String receiver) throws RemoteException;

    /**
     * Tells the {@link Network.ServerSide.RemoteServerImplementation RemoteServer} that a
     * {@link Player Player} wants to begin putting {@link server.Model.PlayableItemTile PlayableItemTiles}
     * into the {@link server.Model.Shelf Shelf}
     *
     * @throws RemoteException exception that underlines problems with the RMI Connection
     */
    void onUpdateToStartPutting() throws RemoteException;

    /**
     * Method used to verify if the connection with the {@link Network.ServerSide.RemoteServerImplementation RemoteServer}
     * is still on and running
     *
     * @throws RemoteException exception that underlines problems with the RMI Connection
     */
    void onConnectionVerify() throws RemoteException;
}