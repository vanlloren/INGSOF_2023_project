package Network.Events;

public abstract class Event {
    private EventEnum eventType;

    public Event(EventEnum eventType){
        this.eventType = eventType;
    }

    public EventEnum getEventType(){
        return this.eventType;
    }
}
