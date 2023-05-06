package Network.message;

public class LoginReplyMessage extends  Message{

    private static final long serialVersionUID = -5L;
    private final boolean nicknameUniqueAccepted;


    public LoginReplyMessage(String proposedNickname, boolean nicknameUniqueAccepted) {
        super(proposedNickname, MessageEnumeration.LOGIN_REPLY);
        this.nicknameUniqueAccepted = nicknameUniqueAccepted;
    }

    public boolean isNicknameUniqueAccepted() {
        return nicknameUniqueAccepted;
    }


}
