package Network.message;

import java.io.Serial;

public class InsertionReplyMessage extends Message{
    @Serial
    private static final long serialVersionUID = 7430768461854580650L;
    private final boolean isValid;
    private final boolean isLastTurn;
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
