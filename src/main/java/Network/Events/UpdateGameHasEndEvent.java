package Network.Events;


/**
 *An extension of the {@link Event event} Class.
 * A {@link UpdateGameHasEndEvent updateGameHasEndEvent} is created when
 * the game has reached it ends which is indicated by an attribute in the model
 * and puts the infos in this class through the {@link EventEnum UpdateGameHasEndEvent}
 */
public class UpdateGameHasEndEvent extends Event{

    /**
     *This method creates an instance of
     * {@link UpdateGameHasEndEvent updateGameHasEndEvent}
     */
    public UpdateGameHasEndEvent() {
        super(EventEnum.UPDATE_GAME_HAS_ENDED);
    }
}
