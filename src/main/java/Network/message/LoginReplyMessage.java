package Network.message;

public class LoginReplyMessage extends  Message{

    private static final long serialVersionUID = -5L;
    private final boolean nicknameUniqueAccepted;
    private final boolean connectionVerified;

    public LoginReplyMessage(String proposedNickname, boolean nicknameUniqueAccepted) {
        super(proposedNickname, MessageEnumeration.LOGIN_REPLY);
        this.nicknameUniqueAccepted = nicknameUniqueAccepted;
    }

    public boolean isNicknameUniqueAccepted() {
        return nicknameUniqueAccepted;
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
