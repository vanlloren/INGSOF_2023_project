package Network.message;

public class InsertionReplyMessage extends Message{
    private boolean isValid;
    private boolean isLastTurn;
    public InsertionReplyMessage(boolean isValid, boolean isLastTurn) {
        super(MessageEnumeration.INSERTION_REPLY);
        this.isValid = isValid;
        this.isLastTurn = isLastTurn;
    }

    public boolean isValid() {
        return isValid;
    }

    public boolean isLastTurn() {
        return isLastTurn;
    }
}
