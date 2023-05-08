package Network.message;

public class PlayersNumberReplyMessage extends Message{

    private final int numPlayers;
    public PlayersNumberReplyMessage(String nickname, int numPlayers) {
        super(nickname, MessageEnumeration.PLAYERNUMBER_REPLY);
        this.numPlayers = numPlayers;
    }
    public int getNumPlayers(){
        return this.numPlayers;
    }
}
