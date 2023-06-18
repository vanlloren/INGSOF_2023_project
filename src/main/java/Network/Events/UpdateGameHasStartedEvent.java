package Network.Events;

/**
 *An extension of the {@link Event event} Class.
 * A {@link UpdateGameHasStartedEvent updateGameHasStartedEvent} is created when
 * the model has reached the requested number of {@link server.Model.Player players}
 * and puts the infos in this class through the {@link EventEnum UpdateGameHasStartedEvent}
 */
public class UpdateGameHasStartedEvent extends Event{

    /**
     *This method creates an instance of
     * {@link UpdateGameHasStartedEvent updateGameHasStartedEvent}
     */
    public UpdateGameHasStartedEvent() {
        super(EventEnum.UPDATE_GAME_HAS_STARTED);
    }
}
