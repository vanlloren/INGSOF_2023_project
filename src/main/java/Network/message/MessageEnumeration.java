package Network.message;
/**
 * This enum contains all the message type available and used by the server and clients.
 */
public enum MessageEnumeration {
        // MAIN MESSAGES
        LOGIN_REQUEST, LOGIN_REPLY,
        PLAYERNUMBER_REQUEST,
        REQUEST_PICK_TILE,
        REPLY_PICK_TILE,
        KEEP_PICKING_REPLY,
        TO_PUT_TILE_REPLY,
        TO_PUT_TILE_REQUEST,
        REPLY_INSERT_TILE_COLUMN,
        WINNER_MESSAGE,
        ERROR_MESSAGE,
        KEEP_PICKING_REQUEST,


        FULL_LOBBY,
        TO_PICK_TILE_REPLY, TO_PICK_TILE_REQUEST, PLAYERNUMBER_REPLY,
}