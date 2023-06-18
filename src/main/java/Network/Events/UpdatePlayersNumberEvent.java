package Network.Events;

import server.Model.Player;

/**
 *An extension of the {@link Event event} Class.
 *A {@link UpdatePlayersNumberEvent updatePlayersNumberEvent} is created when
 *the number of {@link Player player} given from the first user has been accepted
 * then the information is encapsulated in this class
 */
public class UpdatePlayersNumberEvent extends Event{
    private final int playersNumber;

    /**
     *This method creates an instance of
     * {@link UpdatePlayersNumberEvent updatePlayersNumberEvent}
     * @param playersNumber is the actual number of the {@link Player players}
     * that the gameLobby will have
     */
    public UpdatePlayersNumberEvent(int playersNumber) {
        super(EventEnum.UPDATE_PLAYERS_NUMBER);
        this.playersNumber = playersNumber;
    }

    /**
     * @return the attribute {@code player} of this instance
     */
    public int getPlayersNumber(){
        return this.playersNumber;
    }
}
