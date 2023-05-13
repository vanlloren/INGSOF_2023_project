package Network.Events;

import server.Model.Player;

public class UpdateMatchWinnerEvent extends Event{
    private String player;

    public UpdateMatchWinnerEvent(String player) {
        super(EventEnum.UPDATE_MATCH_WINNER);
        this.player = player;
    }

    public String getMatchWinner(){
        return this.player;
    }
}
