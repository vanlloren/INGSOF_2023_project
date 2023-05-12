package Observer;
import Network.message.Message;
import java.util.function.Consumer;
import java.util.ArrayList;
import java.util.List;

public abstract class ServerObservable  {
    private final List<ServerObserver> observers = new ArrayList<>();

    /**
     * Adds an observer.
     *
     * @param obs the observer to be added.
     */
    public void addObserver(ServerObserver obs){ observers.add(obs);}

    /**
     * Removes an observer.
     *
     * @param obs the observer to be removed.
     */
    public void deleteObserver(ServerObserver obs){ observers.remove(obs);}

    /**
     * Notifies all the current observers through the update method and passes to them a {@link Message}.
     *
     * @param message the message to be passed to the observers.
     */

    public void notifyObservers(Consumer<ServerObserver> lambda) {
        for (ServerObserver observer : observers) {
            lambda.accept(observer);
        }
    }

}