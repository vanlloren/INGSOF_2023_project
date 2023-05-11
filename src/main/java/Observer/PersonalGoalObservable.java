package Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class PersonalGoalObservable {
    private final List<PersonalGoalObserver> observers = new ArrayList<>();

    /**
     * Adds an observer.
     *
     * @param obs the observer to be added.
     */
    public void addObserver(PersonalGoalObserver obs){ observers.add(obs);}

    /**
     * Removes an observer.
     *
     * @param obs the observer to be removed.
     */
    public void deleteObserver(PersonalGoalObserver obs){ observers.remove(obs);}

    /**
     * Notifies all the current observers through the lambda argument.
     *
     * @param lambda the lambda to be called on the observers.
     */

    public void notifyObservers(Consumer<PersonalGoalObserver> lambda) {
        for (PersonalGoalObserver observer : observers) {
            lambda.accept(observer);
        }
    }
}
