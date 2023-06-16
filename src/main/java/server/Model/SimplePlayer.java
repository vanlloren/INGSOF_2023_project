package server.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * {@link SimplePlayer SimplePlayer} is the immutable copy of {@link Player Player} which
 * is provided to the {@link Network.ClientSide.RemoteClientImplementation RemoteClient} through
 * {@link client.view.TurnView TurnView}.
 * It contains only the methods of {@link Player Player} that are strictly necessary to the
 * {@link Network.ClientSide.RemoteClientImplementation RemoteClient} to interact with the objects in
 * the {@link GameModel GameModel}.
 *
 * <i>Have a look at MyShelfie RuleBook for further information</i>
 */
public interface SimplePlayer extends Serializable {

    /**
     *
     * @return the unique {@code nickname} assigned to the {@link Player Player}
     */
    String getNickname();


    /**
     *
     * @return the {@link Shelf Shelf} possessed by the {@link Player Player}
     */
    Shelf getPersonalShelf();
}
