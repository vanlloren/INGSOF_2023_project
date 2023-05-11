package Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class PlayerObservable {
    private final List<PlayerObserver> observers = new ArrayList<>();

    /**
     * Adds an observer.
     *
     * @param obs the observer to be added.
     */
    public void addObserver(PlayerObserver obs){ observers.add(obs);}

    /**
     * Removes an observer.
     *
     * @param obs the observer to be removed.
     */
    public void deleteObserver(PlayerObserver obs){ observers.remove(obs);}

    /**
     * Notifies all the current observers through the lambda argument.
     *
     * @param lambda the lambda to be called on the observers.
     */

    public void notifyObservers(Consumer<PlayerObserver> lambda) {
        for (PlayerObserver observer : observers) {
            lambda.accept(observer);
        }
    }
}
