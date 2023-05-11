package Network.message;

import server.Model.GameModel;

public class DefaultMessagge extends Message {

    private static final long serialVersionUID = 934399396584368694L;

    private final String message;


    public DefaultMessagge(String message) {
        super(GameModel.Server_Nick, MessageEnumeration.DEFAULT_MESSAGGE);
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "DefaultMessage{" +
                "nickname=" + getNickname() +
                ", message=" + message +
                '}';
    }

}
