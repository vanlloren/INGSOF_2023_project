package client.view;

import server.Model.*;

import java.net.SocketException;
import java.util.ArrayList;

/**
 * This interface represents the generic {@code View} of a {@link Network.ClientSide.RemoteClientImplementation RemoteClient}.
 * Its two possible implementations are {@link TUI TUI} and GUI.
 */
public interface View {

    /**
     * This method allows the user to insert the ipAddress of the
     * {@link Network.ServerSide.RemoteServerImplementation RemoteServer}
     * he wants to connect into.
     */
    String askServerInfo();

    /**
     *This method allows the user to insert the information regarding the socket port
     * of the {@link Network.ServerSide.RemoteServerImplementation RemoteServer} he wants to connect
     * into
     */
    int askServerPort();

     /**
     *This method allows the user to insert the {@code nickname}
     * he wants to use during the match
     */
    void askNickname();

    /**
     * This method allows the TUI to save the {@code nickname} selected by its user
     * @param currPlayer represents the new current {@link Player player} that is set to the
     *  attribute {@code currPlayer} of this {@code TUI} instance
     */
    void setCurrPlayer(String currPlayer);

    /**
     *This method asks the first user to connect to the {@link Network.ServerSide.RMIServer RemoteServer}
     * to suggest the number of {@link Player Players} for the current game
     */
    void askPlayersNumber();

    /**
     *This method handles the request of a user to start picking the {@link PlayableItemTile PlayableItemTiles}
     * from the {@link LivingRoom LivingRoom}, while showing the current disposition of the latter, and calls
     * afterward the method {@code askTileToPut} that manages the insertion in the {@link Shelf personalShelf}.
     */
    void askMovingTilePosition();

    /**
     *This method handles the request of a user to start inserting the {@link PlayableItemTile PlayableItemTiles}
     * picked from the {@link LivingRoom LivingRoom} into the {@link Shelf personalShelf} while showing the current
     * disposition of the latter.
     */
    void askTileToPut();

    /**
     *This method prints the {@code chosenNickname} only if the param {@code nickAccepted}
     * is {@code true}, otherwise it prints a negative comment
     *
     * @param nicknameAccepted contains the result from the {@link Network.ServerSide.RMIServer server}
     * of the {@code nickname} written previously.
     * @param chosenNickname is the same {@code nickname} written when requested
     */
    void showLoginResults(boolean nicknameAccepted, String chosenNickname);

    /**
     *This method prints all the {@code nicknames} of all the {@link Player players} in the attribute
     * {@link ArrayList<Player> playerList} of this instance
     */
    void showPlayersList(ArrayList<Player> playerList);

    /**
     *This method is called when a user is trying to connect to the {@link Network.ServerSide.RMIServer server}
     * that has already reached its full size
     */
    void fullLobbyTerminateUI();

    /**
     *This method prints the latest updated version of the {@link LivingRoom LivingRoom}
     */
    void showLivingRoom(SimpleLivingRoom livingRoom);

    /**
     *This method prints the latest updated version of the {@link Shelf Shelf}
     * of a {@link Player Player}
     * */
    void showPlayerShelf(SimpleShelf shelf);

    /**
     *This method prints the {@code nickname}
     * of the {@link Player currentPlayer} in turn
     */
    void showNickCurrentPlayer(String Nickname);

    /**
     *This method prints the current amount of {@code points} of a user
     */
    void showPartialPoint(int point);

    /**
     * Sets the actual {@link String nickname} of the user that has joined the game only when its nickname has been
     * previously accepted.
     *
     * @param nickname the instance of {@link String nickname} of the current TUI
     */
    void setNickname(String nickname);

    /**
     *This method sets the attribute  {@code isLastTurn} {@code true} only when the last turn played
     *from this user was their last one
     */
    void setIsTurn();

    void setPlayerList(ArrayList<Player> playerList);

    /**
     *This method is used to release the {@code semaphore}.
     * {@code Semaphore} is a variable used to control the correct execution of the flow of
     * the method {@code init}.
     */
    void riprendiEsecuzione();

    /**
     *This method sets the attribute {@code gameOn} to {@code false}
     *when the user has played his last turn
     */
    void resetGameOn();

    /**
     *This method allows the user to choose between the actions to execute during the match depending on
     *the given input on the command line.
     *By selecting '1', the user will enter the game turn which consists in picking and putting {@link PlayableItemTile PlayableItemTiles}.
     *The other inputs select different views of the game's database.
     */
    void askPlayerNextMove() throws SocketException;

    /**
     *It is the method that allows the user to write a message to be sent in private or
     * to all {@link Player Players} in the {@link ArrayList<Player> PlayerList} of the game
     */
    void WriteInChat();
}
