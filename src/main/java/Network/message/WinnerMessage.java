package Network.message;

public class WinnerMessage extends Message {
    private static final long serialVersionUID = 4L;
    private final String winnerNickname;

    WinnerMessage(String nickname, String winnerNickname) {
        super(nickname, MessageEnumeration.WINNER_MESSAGE);
        this.winnerNickname = winnerNickname;
    }

    public String getWinnerNickname() {
        return this.winnerNickname;
    }




    @Override
    public String toString() {
        return "WinnerMessage{" +
                "nickname=" + getNickname() +
                ", winnerNickname=" + winnerNickname +
                '}';
    }
}
