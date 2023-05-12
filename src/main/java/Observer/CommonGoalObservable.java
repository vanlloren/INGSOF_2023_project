package Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CommonGoalObservable {
    private final List<CommonGoalObserver> observers = new ArrayList<>();

    /**
     * Adds an observer.
     *
     * @param obs the observer to be added.
     */
    public void addObserver(CommonGoalObserver obs){ observers.add(obs);}

    /**
     * Removes an observer.
     *
     * @param obs the observer to be removed.
     */
    public void deleteObserver(CommonGoalObserver obs){ observers.remove(obs);}

    /**
     * Notifies all the current observers through the lambda argument.
     *
     * @param lambda the lambda to be called on the observers.
     */

    public void notifyObservers(Consumer<CommonGoalObserver> lambda) {
        for (CommonGoalObserver observer : observers) {
            lambda.accept(observer);
        }
    }
}
