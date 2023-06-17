package Network.message;

import java.io.Serial;

/**
 * An extension of the {@link Message Message} Class.
 * A {@link StopPickingMessage StopPickingMessage} is sent whenever a {@link server.Model.Player Player}
 * decides to stop picking {@link server.Model.PlayableItemTile PlayableItemTiles} from the
 * {@link server.Model.LivingRoom LivingRoom}.
 * <i>Have a look at {@link Message Message} Class for more information.</i>
 */
public class StopPickingMessage extends Message{
    @Serial
    private static final long serialVersionUID = -168054004053784041L;

    /**
     * This method creates an instance of {@link StopPickingMessage StopPickingMessage}
     *
     * @param nickname a {@link String String} containing the {@code nickname} of the sender
     */
    public StopPickingMessage(String nickname) {
        super(nickname, MessageEnumeration.STOP_PICKING);
    }
}
