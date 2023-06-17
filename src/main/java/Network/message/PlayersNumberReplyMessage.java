package Network.message;

import java.io.Serial;

public class PlayersNumberReplyMessage extends Message{
    @Serial
    private static final long serialVersionUID = -19L;
    private final int playerNumber;

    public PlayersNumberReplyMessage(int playerNumber) {
        super( MessageEnumeration.PLAYERNUMBER_REPLY);
        this.playerNumber = playerNumber;
    }

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
