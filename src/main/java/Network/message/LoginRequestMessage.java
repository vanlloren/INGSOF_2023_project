package Network.message;

import Network.ClientSide.RemoteClientInterface;

import java.io.Serial;

/**
 * An extension of the {@link Message Message} Class.
 * A {@link LoginRequestMessage LoginRequestMessage} is sent whenever a {@link server.Model.Player Player}
 * logs into the {@link Network.ServerSide.RemoteServerImplementation RemoteServer}.
 * <i>Have a look at {@link Message Message} Class for more information.</i>
 */
public class LoginRequestMessage extends Message {
    @Serial
    private static final long serialVersionUID = 7L;
    private final RemoteClientInterface client;

    /**
     * This method creates an instance of {@link LoginRequestMessage LoginRequestMessage}
     * @param client the {@link Network.ClientSide.RemoteClientImplementation RemoteClient} that is connecting to
     *               the {@link Network.ServerSide.RemoteServerImplementation RemoteServer}
     * @param nickname a {@link String String} containing the {@code nickname} of the sender
     */
    public LoginRequestMessage(RemoteClientInterface client, String nickname) {
        super(nickname, MessageEnumeration.LOGIN_REQUEST);
        this.client = client;
    }

    /**
     *
     * @return the {@link Network.ClientSide.RemoteClientImplementation RemoteClient} logging into
     * the {@link Network.ServerSide.RemoteServerImplementation RemoteServer}
     */
    public RemoteClientInterface getClient(){
        return this.client;
    }

}
