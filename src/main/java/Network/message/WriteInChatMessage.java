package Network.message;

public class WriteInChatMessage extends Message {
    private final String Nickname;
    private final String chat;
    public WriteInChatMessage(String Nickname, String chat) {
        super(MessageEnumeration.WRITE_IN_CHAT);
        this.Nickname = Nickname;
        this.chat = chat;
    }

    @Override
    public String getNickname() {
        return Nickname;
    }

    public String getChat() {
        return chat;
    }
}
