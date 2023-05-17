package server.Model;

import java.io.Serializable;
import java.util.ArrayList;

public interface SimpleLivingRoom extends Serializable{

    ArrayList<PlayableItemTile> getAvailableTiles();

    ItemTile[][] getGameTable();
}
