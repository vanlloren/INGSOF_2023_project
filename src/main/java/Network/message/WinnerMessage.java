package Network.message;

public class WinnerMessage extends Message {
    private static final long serialVersionUID = 4L;

    public String getWinnerNickname() {
        return winnerNickname;
    }

    private final String winnerNickname;

    public WinnerMessage(String winnerNickname) {
        super(Game.SERVER_NICKNAME, MessageEnumeration.WIN_FX);
        this.winnerNickname = winnerNickname;
    }

    @Override
    public String toString() {
        return "WinnerMessage{" +
                "nickname=" + getNickname() +
                ", winnerNickname=" + winnerNickname +
                '}';
    }
}
