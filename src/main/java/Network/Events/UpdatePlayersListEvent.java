package Network.Events;

import server.Model.Player;

public class UpdatePlayersListEvent extends Event{
    private final Player player;

    public UpdatePlayersListEvent(Player player){
        super(EventEnum.UPDATE_PLAYERS_LIST);
        this.player = player;
    }

    public Player getPlayer(){
        return this.player;
    }
}
