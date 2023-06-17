package Network.message;

import java.io.Serial;

public class LoginReplyMessage extends  Message{
    @Serial
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




