package Network.message;

public class LoginReplyMessage extends  Message{
    private static final long serialVersionUID = -5L;
    private final boolean nicknameUniqueAccepted;


    public LoginReplyMessage(String Nickname,boolean nicknameUniqueAccepted) {
        super(Nickname, MessageEnumeration.LOGIN_REPLY);
        this.nicknameUniqueAccepted = nicknameUniqueAccepted;

    }

    public boolean isNicknameUniqueAccepted() {
        return nicknameUniqueAccepted;
    }


}




