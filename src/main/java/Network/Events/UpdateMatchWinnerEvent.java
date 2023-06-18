package Network.Events;

/**
 *An extension of the {@link Event event} Class.
 * A {@link UpdateMatchWinnerEvent updateMatchWinnerEvent} is created when
 * the model has determined the winner and encapsulate it into this class
 */
public class UpdateMatchWinnerEvent extends Event{
    private final String player;

    /**
     *This method creates an instance of
     * {@link UpdateMatchWinnerEvent updateMatchWinnerEvent}
     * @param player is the name of the actual winner
     */
    public UpdateMatchWinnerEvent(String player) {
        super(EventEnum.UPDATE_MATCH_WINNER);
        this.player = player;
    }
    /**
     * @return the attribute {@code player} of this instance
     */
    public String getMatchWinner(){
        return this.player;
    }
}
