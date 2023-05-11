package Observer;

import server.Model.PlayableItemTile;

public interface ShelfObserver {
    void onUpdatePuttedTileIntoShelf(int x, int y, PlayableItemTile Tile);
}
