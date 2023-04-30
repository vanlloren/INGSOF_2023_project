package Network.message;

public class LoginReplyMessage extends  Message{

    private static final long serialVersionUID = -5L;
    private final boolean nicknameUniqueAccepted;
    private final boolean connectionVerified;

    public LoginReplyMessage(boolean nicknameUniqueAccepted, boolean connectionVerified) {
        super(Game.SERVER_NICKNAME, MessageEnumeration.LOGIN_REPLY);
        this.nicknameUniqueAccepted = nicknameUniqueAccepted;
        this.connectionVerified = connectionVerified;
    }

    public boolean isNicknameUniqueAccepted() {
        return nicknameUniqueAccepted;
    }

    public boolean isConnectionVerified() {
        return connectionVerified;
    }

    @Override
    public String toString() {
        return "LoginReply{" +
                "nickname=" + getNickname() +
                ", nicknameUniqueAccepted=" + nicknameUniqueAccepted +
                ", connectionVerified=" + connectionVerified +
                '}';
    }
}
