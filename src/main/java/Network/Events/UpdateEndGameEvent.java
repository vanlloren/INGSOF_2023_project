package Network.Events;

public class UpdateEndGameEvent extends Event {
    private boolean endGame;

    public UpdateEndGameEvent(boolean endGame) {
        super(EventEnum.UPDATE_END_GAME);
        this.endGame = endGame;
    }

    public boolean isEndGame() {
        return this.endGame;
    }
}
