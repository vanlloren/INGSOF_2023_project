package Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class LivingRoomObservable {
    private final List<LivingRoomObserver> observers = new ArrayList<>();

    /**
     * Adds an observer.
     *
     * @param obs the observer to be added.
     */
    public void addObserver(LivingRoomObserver obs){ observers.add(obs);}

    /**
     * Removes an observer.
     *
     * @param obs the observer to be removed.
     */
    public void deleteObserver(LivingRoomObserver obs){ observers.remove(obs);}

    /**
     * Notifies all the current observers through the lambda argument.
     *
     * @param lambda the lambda to be called on the observers.
     */

    public void notifyObservers(Consumer<LivingRoomObserver> lambda) {
        for (LivingRoomObserver observer : observers) {
            lambda.accept(observer);
        }
    }
}
