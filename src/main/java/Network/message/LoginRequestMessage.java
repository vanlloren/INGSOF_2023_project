package Network.message;

import Network.ClientSide.RemoteClientInterface;

public class LoginRequestMessage extends Message {
    private static final long serialVersionUID = 7L;
    private RemoteClientInterface client;

    public LoginRequestMessage(RemoteClientInterface client, String nickname) {
        super(nickname, MessageEnumeration.LOGIN_REQUEST);
        this.client = client;
    }

    public RemoteClientInterface getClient(){
        return this.client;
    }

}
