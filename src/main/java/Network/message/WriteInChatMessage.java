package Network.message;

public class WriteInChatMessage extends Message {
    private final String Nickname;
    private final String chat;
    private final String receiver;
    public WriteInChatMessage(String Nickname, String chat,String receiver) {
        super(MessageEnumeration.WRITE_IN_CHAT);
        this.Nickname = Nickname;
        this.chat = chat;
        this.receiver = receiver;
    }

    @Override
    public String getNickname() {
        return Nickname;
    }

    public String getChat() {
        return chat;
    }

    public String getReceiver(){
        return receiver;
    }
}
