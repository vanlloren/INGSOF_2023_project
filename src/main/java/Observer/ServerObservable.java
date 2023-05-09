package Observer;
import java.util.function.Consumer;
import java.util.ArrayList;
import java.util.List;

public abstract class ServerObservable  {
    private final List<ModelObserver> observers = new ArrayList<>();

    /**
     * Adds an observer.
     *
     * @param obs the observer to be added.
     */
    public void addObserver(ModelObserver obs){ observers.add(obs);}

    /**
     * Removes an observer.
     *
     * @param obs the observer to be removed.
     */
    public void deleteObserver(ModelObserver obs){ observers.remove(obs);}

    /**
     * Notifies all the current observers through the lambda argument.
     *
     * @param lambda the lambda to be called on the observers.
     */

    public void notifyObservers(Consumer<ModelObserver> lambda) {
        for (ModelObserver observer : observers) {
            lambda.accept(observer);
        }
    }

}