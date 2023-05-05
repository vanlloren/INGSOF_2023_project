package Network.message;

public class PlayersNumberReplyMessage extends Message{
    private static final long serialVersionUID = -19L;
    private int playerNumber;

    public PlayersNumberReplyMessage(String nickname, int playerNumber) {
        super(nickname, MessageEnumeration.PLAYERNUMBER_REPLY);
        this.playerNumber = playerNumber;
    }


    public int getPlayerNumber() {
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
