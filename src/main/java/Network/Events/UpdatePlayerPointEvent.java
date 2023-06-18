package Network.Events;

import server.Model.Player;

/**
 *An extension of the {@link Event event} Class.
 *A {@link UpdatePlayerPointEvent updatePlayerPointEvent} is created to
 * encapsulate the change from the model relating to a new
 * value of {@code points} for the current {@link Player player}
 */
public class UpdatePlayerPointEvent extends Event{
    private final int points;
    private final String player;

    /**
     *This method creates an instance of
     * {@link UpdatePlayerPointEvent updatePlayerPointEvent}
     * @param player is the name of the user who has the new {@code points}
     * @param points is the actual value
     */
    public UpdatePlayerPointEvent(String player, int points) {
        super(EventEnum.UPDATE_PLAYER_POINTS);
        this.points = points;
        this.player = player;
    }

    /**
     * @return the attribute {@code points} of this instance
     */
    public int getPoints(){
        return this.points;
    }

    /**
     * @return the attribute {@code player} of this instance
     */
    public String getPlayer(){
        return this.player;
    }
}
