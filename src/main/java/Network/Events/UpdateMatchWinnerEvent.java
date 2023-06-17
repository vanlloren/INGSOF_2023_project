package Network.Events;

public class UpdateMatchWinnerEvent extends Event{
    private final String player;

    public UpdateMatchWinnerEvent(String player) {
        super(EventEnum.UPDATE_MATCH_WINNER);
        this.player = player;
    }

    public String getMatchWinner(){
        return this.player;
    }
}
