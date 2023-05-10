package Network.message;

public class UpdatePlayersNumberMessage extends Message{
    private final int numPlayers;
    private final MessageEnumeration =

   public  UpdatePlayersNumberMessage(int numPlayers) {
        super(M);
        this.getMessageEnumeration() =
        this.numPlayers = this.numPlayers;
    }

    public int getNumPlayers() {
        return numPlayers;
    }
}
