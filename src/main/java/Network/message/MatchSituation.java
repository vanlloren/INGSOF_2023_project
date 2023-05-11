package Network.message;

import server.Model.Shelf;

import java.util.List;

public class MatchSituation extends Message{
    public final List<String> actualPlayers;
    public final List<Shelf> actualShelf;
    public final String activePlayersNickname;

    public List<String> getActualPlayers() {
        return actualPlayers;
    }

    public List<Shelf> getActualShelf() {
        return actualShelf;
    }

    public String getActivePlayersNickname() {
        return activePlayersNickname;
    }
    public String toString() {
        return "MatchInfoMessage{" +
                "nickname=" + getNickname() +
                ", actualPlayers=" + actualPlayers +
                ", actualShelf=" + actualShelf +
                ", actualPlayerNickname=" + activePlayersNickname +
                '}';
    }
    public MatchSituation(String askingNickname, MessageEnumeration messageEnum, List<String> actualPlayers, List<Shelf> actualShelf, String actualPlayerNickname) {
        super(askingNickname, messageEnum);
        this.actualPlayers = actualPlayers;
        this.actualShelf = actualShelf;
        this.activePlayersNickname = actualPlayerNickname;
    }
}
