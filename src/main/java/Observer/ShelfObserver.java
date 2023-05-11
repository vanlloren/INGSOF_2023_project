package Observer;

import server.Model.PlayableItemTile;

public interface ShelfObserver {
    void onUpdateStructureShelf(int x, int y, PlayableItemTile Tile);
}
