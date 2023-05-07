package Network.message;

public class UpdateEndGameMessage extends Message{

    private final boolean endGame;
    UpdateEndGameMessage(String nickname,boolean endGame) {
        super(nickname, MessageEnumeration.UPDATE_MODEL_ENDGAME);
        this.endGame = endGame;
    }
    public boolean getEndGame() {
        return this.endGame;
    }

}
