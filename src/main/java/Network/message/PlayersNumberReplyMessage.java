package Network.message;

import java.io.Serial;

/**
 * An extension of the {@link Message Message} Class.
 * A {@link PlayersNumberReplyMessage PlayersNumberReplyMessage} is sent whenever the first
 * {@link server.Model.Player Player} to join the lobby answers to the {@link PlayersNumberRequestMessage PlayersNumberRequestMessage}
 * setting the {@code playersNumber} for the match.
 * <i>Have a look at {@link Message Message} Class for more information.</i>
 */
public class PlayersNumberReplyMessage extends Message{
    @Serial
    private static final long serialVersionUID = -19L;
    private final int playerNumber;

    /**
     * This method creates an instance of {@link PlayersNumberReplyMessage PlayersNumberReplyMessage}
     *
     * @param playerNumber the {@code playersNumber} set for the game
     */
    public PlayersNumberReplyMessage(int playerNumber) {
        super( MessageEnumeration.PLAYERNUMBER_REPLY);
        this.playerNumber = playerNumber;
    }

    /**
     *
     * @return the {@code playersNumber} set for the game
     */
    public int getNumPlayers() {
        return playerNumber;
    }

    @Override
    public String toString() {
        return "PlayerNumberReply{" +
                "nickname=" + getNickname() +
                ", playerNumber=" + playerNumber +
                '}';
    }
}
