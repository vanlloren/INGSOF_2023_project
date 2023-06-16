package Observer;
import server.Model.LivingRoom;
import server.Model.Shelf;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * This Class represents the adaptation of the standard {@link java.util.Observable Observable} Class
 * to the methods and objects in use inside the {@link client.view.View View} interface and
 * its implementations
 */
public abstract class ViewObservable {

    protected final List<ViewObserver> observers = new ArrayList<>();

    /**
     * Adds an observer.
     *
     * @param obs the observer to be added.
     */
    public void addObserver(ViewObserver obs) {
        observers.add(obs);
    }

    /**
     * Adds a list of observers.
     *
     * @param observerList the list of observers to be added.
     */
    public void addAllObservers(List<ViewObserver> observerList) {
        observers.addAll(observerList);
    }

    /**
     * Removes an observer.
     *
     * @param obs the observer to be removed.
     */
    public void removeObserver(ViewObserver obs) {
        observers.remove(obs);
    }

    /**
     * Removes a list of observers.
     *
     * @param observerList the list of observers to be removed.
     */
    public void removeAllObservers(List<ViewObserver> observerList) {
        observers.removeAll(observerList);
    }

    /**
     * Notifies all the current observers through the lambda argument.
     *
     * @param lambda the lambda to be called on the observers.
     */
    protected void notifyObserver(Consumer<ViewObserver> lambda) {
        for (ViewObserver observer : observers) {
            lambda.accept(observer);

        }
    }

}
