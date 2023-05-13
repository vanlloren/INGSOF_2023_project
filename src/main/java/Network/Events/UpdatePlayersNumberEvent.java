package Network.Events;

public class UpdatePlayersNumberEvent extends Event{
    private int playersNumber;
    public UpdatePlayersNumberEvent(int playersNumber) {
        super(EventEnum.UPDATE_PLAYERS_NUMBER);
        this.playersNumber = playersNumber;
    }

    public int getPlayersNumber(){
        return this.playersNumber;
    }
}
