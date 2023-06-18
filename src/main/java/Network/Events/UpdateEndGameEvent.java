package Network.Events;

/**
 *An extension of the {@link Event event} Class.
 * A {@link UpdateEndGameEvent updateEndGameEvent} is created when
 * the attribute {@code endGame} in the {@link server.Model.GameModel gameModel} is {@code true}
 * so encapsulate that change in this class
 */
public class UpdateEndGameEvent extends Event {
    private final String player;

    /**
     *This method creates an instance of
     * {@link UpdateEndGameEvent UpdateEndGameEvent}
     * @param player is the name of the {@link server.Model.Player player} that has set
     * the attribute {@code endGame} {@code true}
     */
    public UpdateEndGameEvent(String player) {
        super(EventEnum.UPDATE_END_GAME);
        this.player = player;
    }
    /**
     * @return the attribute {@code player} of this instance
     */
    public String getPlayer() {
        return player;
    }
}
