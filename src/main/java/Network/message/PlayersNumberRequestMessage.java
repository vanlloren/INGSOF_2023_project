package Network.message;

import java.io.Serial;

public class PlayersNumberRequestMessage extends Message{

    @Serial
    private static final long serialVersionUID = -2155556142315548857L;

    public PlayersNumberRequestMessage(String nickName) {
        super(nickName, MessageEnumeration.PLAYERNUMBER_REQUEST);
    }

    @Override
    public String toString() {
        return "PlayerNumberRequest{" +
                "nickname=" + getNickname() +
                '}';
    }
}
