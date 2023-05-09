package Network.message;

import server.Model.Player;

public class UpdateChairOwnerMessage extends Message {
    private final Player chairOwner;
    public UpdateChairOwnerMessage(String nickname, Player player) {
        super(nickname, MessageEnumeration.UPDATE_MODEL_CHAIROWNER);
        this.chairOwner = player;
    }
    public Player getChairOwner(){
       return this.chairOwner;
    }
}
