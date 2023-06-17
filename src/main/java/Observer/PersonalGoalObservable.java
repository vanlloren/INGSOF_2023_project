package Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * This Class represents the adaptation of the standard {@link java.util.Observable Observable} Class
 * to the methods and objects in use inside the {@link server.Model.PersonalGoal PersonalGoal} Class
 */
public class PersonalGoalObservable {
    private final List<PersonalGoalObserver> observers = new ArrayList<>();

    /**
     * Adds an observer.
     *
     * @param obs the observer to be added.
     */
    public void addObserver(PersonalGoalObserver obs){ observers.add(obs);}

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
