package Network.message;

public class UpdatePlayersNumberMessage extends Message{
    private final int numPlayers;

    UpdatePlayersNumberMessage(String nickname, int numPlayers) {
        super(nickname, MessageEnumeration.UPDATE_MODEL_PLAYERSNUMBER);
        this.numPlayers = numPlayers;
    }

    public int getNumPlayers() {
        return numPlayers;
    }
}
