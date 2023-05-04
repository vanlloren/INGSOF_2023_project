package Network.message;
import server.Model.GameModel;

import java.util.List;
public class LobbyMessage extends Message{
    private static final long serialVersionUID = -8L;
    private final List<String> nicknameList;
    private final int maxPlayers;

    public LobbyMessage(List<String> nicknameList, int maxPlayers) {
        super(GameModel.Server_Nick, MessageEnumeration.LOBBY);
        this.nicknameList = nicknameList;
        this.maxPlayers = maxPlayers;
    }

    public List<String> getNicknameList() {
        return nicknameList;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    @Override
    public String toString() {
        return "LobbyMessage{" +
                "nickname=" + getNickname() +
                ", nicknameList=" + nicknameList +
                ", numPlayers=" + maxPlayers +
                '}';
    }

}
