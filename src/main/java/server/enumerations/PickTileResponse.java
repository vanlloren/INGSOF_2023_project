package server.enumerations;

/**
 * This enum contains all possible answers following the request of picking a {@link server.Model.ItemTile ItemTile} from the {@link server.Model.LivingRoom LivingRoom}.
 */
public enum PickTileResponse {
    CORRECT_TILE,
    INVALID_TILE,
    MAX_TILE_PICKED,
    CORRECT_LAST_TILE
}
