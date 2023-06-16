package server.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * {@link SimpleGameModel SimpleGameModel} is the immutable copy of {@link GameModel GameModel} which
 * is provided to the {@link Network.ClientSide.RemoteClientImplementation RemoteClient} through
 * {@link client.view.TurnView TurnView}.
 * It contains only the methods of {@link GameModel GameModel} that are strictly necessary to the
 * {@link Network.ClientSide.RemoteClientImplementation RemoteClient} to interact with the objects in
 * the {@link GameModel GameModel}.
 *
 * <i>Have a look at MyShelfie RuleBook for further information</i>
 */
public interface SimpleGameModel extends Serializable {

    /**
     *
     * @return {@code true} if the current match is still ongoing,
     * {@code false} otherwise
     */
    boolean getIsGameOn();

    /**
     *
     * @return an {@link ArrayList ArrayList} containing all the {@link Player Players} in the current match
     */
    ArrayList<Player> getPlayersInGame();

    /**
     *
     * @return the number of {@link Player Players} chosen for the current match
     */
    int getPlayersNumber();
}
