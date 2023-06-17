package Network.Events;

import server.Model.Player;

public class UpdateCurrPlayerEvent extends Event{
    private final Player currPlayer;

    public UpdateCurrPlayerEvent(Player currPlayer) {
        super(EventEnum.UPDATE_CURR_PLAYER);
        this.currPlayer = currPlayer;
    }

    public Player getCurrPlayer(){
        return this.currPlayer;
    }
}
