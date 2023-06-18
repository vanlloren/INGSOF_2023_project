package Network.Events;

/**
 * Abstract Event class which must be extended by each Event type.
 * The model communicates to the server that a change has happened
 * and in the {@link Network.ClientSide.RemoteClientImplementation remoteClientImplementation}
 * the update is
 * encapsulated in an Event that is then used to show the change to the user
 * This avoids the usage of the "instance of" primitive.
 */
public abstract class Event {
    private final EventEnum eventType;

    public Event(EventEnum eventType){
        this.eventType = eventType;
    }

    public EventEnum getEventType(){
        return this.eventType;
    }
}
