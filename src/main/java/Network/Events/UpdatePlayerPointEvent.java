package Network.Events;

public class UpdatePlayerPointEvent extends Event{
    private int points;
    private String player;

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
