package Network.message;

import server.Model.Player;

import java.util.ArrayList;

public class UpdateModelListPlayersMessage extends Message{
    private  ArrayList<Player> playerArrayList = new ArrayList<>();
    public UpdateModelListPlayersMessage(String nickname, ArrayList<Player> playerArrayList) {
        super(nickname, MessageEnumeration.UPDATE_MODEL_LISTPLAYERS);
        this.playerArrayList = playerArrayList;
    }

    public ArrayList<Player> getPlayerArrayList() {
        return this.playerArrayList;
    }
}
