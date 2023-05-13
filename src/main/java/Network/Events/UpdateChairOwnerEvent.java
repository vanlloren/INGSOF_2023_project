package Network.Events;

import server.Model.Player;

public class UpdateChairOwnerEvent extends Event {
    private Player player;
    public UpdateChairOwnerEvent(Player player) {
        super(EventEnum.UPDATE_CHAIR_OWNER);
        this.player = player;
    }

    public Player getPlayer(){
        return this.player;

    }
}
