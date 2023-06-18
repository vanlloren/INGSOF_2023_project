package Network.Events;

/**
 *An extension of the {@link Event event} Class.
 *A {@link UpdateRefillLivingRoomEvent updateRefillLivingRoomEvent } is created when
 * we want to incapsulate inside a class all the attributes referred to a model change
 * The type {@link EventEnum} of this class is used to describe a new refilling of the {@link server.Model.LivingRoom livingRoom}
 */
public class UpdateRefillLivingRoomEvent extends Event{
    /**
     *This method creates an instance of {@link UpdatePersonalGoalEvent updatePersonalGoalEvent}
     */
    public UpdateRefillLivingRoomEvent() {
        super(EventEnum.UPDATE_REFILL_LIVINGROOM);
    }
}
