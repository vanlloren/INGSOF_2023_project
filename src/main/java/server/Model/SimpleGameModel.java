package server.Model;

import java.io.Serializable;
import java.util.ArrayList;

public interface SimpleGameModel extends Serializable {

    boolean getIsGameOn();

    ArrayList<Player> getPlayersInGame();

    int getPlayersNumber();
}
