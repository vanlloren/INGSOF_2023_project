package Network.Events;

import server.Model.Player;

/**
 *An extension of the {@link Event event} Class.
 * A {@link UpdateCurrPlayerEvent updateCurrPlayerEvent} is created when
 * the value of the attribute {@code currPlayer} in the {@link server.Model.GameModel gameModel}
 * has changed and the info is subsequently encapsulated into this instance
 */
public class UpdateCurrPlayerEvent extends Event{
    private final Player currPlayer;

    /**
     *This method creates an instance of
     * {@link UpdateCurrPlayerEvent updateCurrPlayerEvent}
     * @param currPlayer is the new current {@link Player}
     */
    public UpdateCurrPlayerEvent(Player currPlayer) {
        super(EventEnum.UPDATE_CURR_PLAYER);
        this.currPlayer = currPlayer;
    }

    /**
     * @return the attribute {@code currPlayer} of this instance
     */
    public Player getCurrPlayer(){
        return this.currPlayer;
    }
}
