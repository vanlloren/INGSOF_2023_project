package Network.Events;

import server.Model.Player;

/**
 *An extension of the {@link Event event} Class.
 *A {@link UpdatePlayersListEvent UpdatePlayersListEvent} is created to
 * encapsulate the change from the model relating to a new {@link Player player}
 * joining the game
 */
public class UpdatePlayersListEvent extends Event{
    private final Player player;

    /**
     *This method creates an instance of
     * {@link UpdatePlayerPointEvent updatePlayerPointEvent}
     * @param player is the name of the user who has just joined the lobby
     */
    public UpdatePlayersListEvent(Player player){
        super(EventEnum.UPDATE_PLAYERS_LIST);
        this.player = player;
    }

    /**
     * @return the attribute {@code player} of this instance
     */
    public Player getPlayer(){
        return this.player;
    }
}
