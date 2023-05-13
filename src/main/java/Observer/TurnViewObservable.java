package Observer;

import Network.ClientSide.RemoteClientInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class TurnViewObservable {
    private final List<RemoteClientInterface> observers = new ArrayList<>();

    /**
     * Adds an observer.
     *
     * @param obs the observer to be added.
     */
    public void addObserver(RemoteClientInterface obs){ observers.add(obs);}

    /**
     * Removes an observer.
     *
     * @param obs the observer to be removed.
     */
    public void deleteObserver(RemoteClientInterface obs){ observers.remove(obs);}

    /**
     * Notifies all the current observers through the lambda argument.
     *
     * @param lambda the lambda to be called on the observers.
     */

    public void notifyObservers(Consumer<RemoteClientInterface> lambda) {
        for (RemoteClientInterface observer : observers) {
            lambda.accept(observer);
        }
    }
}
