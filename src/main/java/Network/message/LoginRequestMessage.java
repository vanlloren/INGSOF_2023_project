package Network.message;

import Network.ClientSide.RemoteClientInterface;

import java.io.Serial;

public class LoginRequestMessage extends Message {
    @Serial
    private static final long serialVersionUID = 7L;
    private final RemoteClientInterface client;

    public LoginRequestMessage(RemoteClientInterface client, String nickname) {
        super(nickname, MessageEnumeration.LOGIN_REQUEST);
        this.client = client;
    }

    public RemoteClientInterface getClient(){
        return this.client;
    }

}
