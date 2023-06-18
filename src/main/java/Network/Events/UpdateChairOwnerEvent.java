package Network.Events;

import server.Model.Player;

/**
 *An extension of the {@link Event event} Class.
 *A {@link UpdateChairOwnerEvent updateChairOwnerEvent} is created to
 * encapsulate the change from the model of its attribute {@code ChairOwner }
 * which his the very first {@link Player player} of the game turn
 *
*/
public class UpdateChairOwnerEvent extends Event {
    private final Player player;

    /**
     *This method creates an instance of
     * {@link UpdateChairOwnerEvent updateChairOwnerEvent}
     * @param player is the name of the user who will be the first one to start its turn
     */
    public UpdateChairOwnerEvent(Player player) {
        super(EventEnum.UPDATE_CHAIR_OWNER);
        this.player = player;
    }

    /**
     * @return the attribute {@code player} of this instance
    */
    public Player getPlayer(){
        return this.player;

    }
}
