package Network.Events;

public class UpdatePlayerPointEvent extends Event{
    private final int points;
    private final String player;

    public UpdatePlayerPointEvent(String player, int points) {
        super(EventEnum.UPDATE_PLAYER_POINTS);
        this.points = points;
        this.player = player;
    }

    public int getPoints(){
        return this.points;
    }

    public String getPlayer(){
        return this.player;
    }
}
