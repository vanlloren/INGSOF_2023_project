package server.Model;

import java.io.Serializable;

public interface SimpleShelf extends Serializable {
    PlayableItemTile[][] getStructure();
}
