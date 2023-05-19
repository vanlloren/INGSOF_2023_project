package Network.Events;

public class UpdateEndGameEvent extends Event {
    private boolean endGame;
    private String player;

    public UpdateEndGameEvent(String player,boolean endGame) {
        super(EventEnum.UPDATE_END_GAME);
        this.endGame = endGame;
        this.player = player;
    }

    public boolean isEndGame() {
        return this.endGame;
    }

    public String getPlayer() {
        return player;
    }
}
