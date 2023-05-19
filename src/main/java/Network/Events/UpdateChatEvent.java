package Network.Events;

public class UpdateChatEvent extends Event {
    private final String Nickname;
    private final String chat;
    public UpdateChatEvent(String Nickname,String chat) {
        super(EventEnum.UPDATE_CHAT);
        this.Nickname = Nickname;
        this.chat = chat;
    }

    public String getChat() {
        return chat;
    }

    public String getNickname() {
        return Nickname;
    }
}
