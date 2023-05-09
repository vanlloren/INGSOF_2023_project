package Network.message;

import server.Model.Player;

public class UpdateCurrentPlayerMessage extends Message{
    private final Player player;
    UpdateCurrentPlayerMessage(String nickname,Player player) {
        super(nickname, MessageEnumeration.UPDDATE_CURRENTPLAYER);
        this.player = player;
    }

}
