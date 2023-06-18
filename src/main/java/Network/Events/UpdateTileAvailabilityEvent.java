package Network.Events;

import Network.message.LoginRequestMessage;

/**
 *An extension of the {@link Event event} Class.
 *A {@link UpdateTileAvailabilityEvent updateTileAvailabilityEventupdate} is created
 *when we want to encapsulate the information that represents a changing
 * in the currently available {@link server.Model.PlayableItemTile tiles} in the {@link server.Model.LivingRoom livingRoom}
 */
public class UpdateTileAvailabilityEvent extends Event{

    /**
     * This method creates an instance of {@link LoginRequestMessage LoginRequestMessage}.
     */
    public UpdateTileAvailabilityEvent() {
        super(EventEnum.UPDATE_TILES_AVAILABILITY);
    }
}