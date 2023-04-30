package Network.message;

public class PlayersNumberRequestMessage extends Message{

    private static final long serialVersionUID = -2155556142315548857L;

    public PlayersNumberRequestMessage() {
        super(Game.SERVER_NICKNAME, MessageEnumeration.PLAYERNUMBER_REQUEST);
    }

    @Override
    public String toString() {
        return "PlayerNumberRequest{" +
                "nickname=" + getNickname() +
                '}';
    }
}
