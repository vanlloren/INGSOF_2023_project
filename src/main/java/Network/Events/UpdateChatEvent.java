package Network.Events;

/**
 *An extension of the {@link Event event} Class.
 * A {@link UpdateChatEvent updateChatEvent} is created when
 * a player has written into the attribute of the model {@code chat} which has to be encapsulated
 * and propagated to the receiver
 */
public class UpdateChatEvent extends Event {
    private final String Nickname;
    private final String chat;
    private final String receiver;

    /**
     *This method creates an instance of
     * {@link Event event}
     * @param Nickname is the name of the sender
     * @param chat is the text written
     * @param receiver is the name of the receiver
     */
    public UpdateChatEvent(String Nickname,String chat,String receiver) {
        super(EventEnum.UPDATE_CHAT);
        this.Nickname = Nickname;
        this.chat = chat;
        this.receiver = receiver;
    }

    /**
     * @return the attribute {@code chat} of this instance
     */
    public String getChat() {
        return chat;
    }

    /**
     * @return the attribute {@code Nickname} of this instance
     */
    public String getNickname() {
        return Nickname;
    }

    /**
     * @return the attribute {@code receiver} of this instance
     */
    public String getReceiver(){
        return receiver;
    }
}
