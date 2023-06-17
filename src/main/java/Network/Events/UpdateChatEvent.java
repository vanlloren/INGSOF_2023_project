package Network.Events;

public class UpdateChatEvent extends Event {
    private final String Nickname;
    private final String chat;
    private final String receiver;

    public UpdateChatEvent(String Nickname,String chat,String receiver) {
        super(EventEnum.UPDATE_CHAT);
        this.Nickname = Nickname;
        this.chat = chat;
        this.receiver = receiver;
    }

    public String getChat() {
        return chat;
    }

    public String getNickname() {
        return Nickname;
    }

    public String getReceiver(){
        return receiver;
    }
}
