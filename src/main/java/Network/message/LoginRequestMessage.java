package Network.message;

public class LoginRequestMessage extends Message {
    private static final long serialVersionUID = 7L;

    public LoginRequestMessage(String nickname) { super(nickname, MessageEnumeration.LOGIN_REQUEST)};

    @Override
    public String toString(){
        return "LoginRequest{" + "nickname = " + getNickname() + '}';
    }
}
