package Network.message;

import java.io.Serial;

/**
 * An extension of the {@link Message Message} Class.
 * A {@link InsertionReplyMessage InsertionReplyMessage} is sent whenever a {@link server.Model.Player Player}
 * ask the {@link Network.ServerSide.RemoteServerImplementation RemoteServer} to put a
 * {@link server.Model.PlayableItemTile PlayableItemTile} into the {@link server.Model.Shelf Shelf}.
 * <i>Have a look at {@link Message Message} Class for more information.</i>
 */
public class InsertionReplyMessage extends Message{
    @Serial
    private static final long serialVersionUID = 7430768461854580650L;
    private final boolean isValid;
    private final boolean isLastTurn;

    /**
     * This method creates an instance of {@link InsertionReplyMessage InsertionReplyMessage}
     *
     * @param isValid a boolean which indicates whether the insertion is valid or not
     * @param isLastTurn a boolean which indicates whether this is the last turn of the {@link server.Model.Player Player}
     *                   or not
     */
    public InsertionReplyMessage(boolean isValid, boolean isLastTurn) {
        super(MessageEnumeration.INSERTION_REPLY);
        this.isValid = isValid;
        this.isLastTurn = isLastTurn;
    }

    /**
     *
     * @return {@code true} if the insertion was successful, {@code false} otherwise
     */
    public boolean isValid() {
        return isValid;
    }

    /**
     *
     * @return {@code true} if this is the {@link server.Model.Player Player} last turn, {@code false} otherwise
     */
    public boolean isLastTurn() {
        return isLastTurn;
    }
}
