package Network.message;
/**
 * This enum contains all the message type available and used by the server and clients.
 */
public enum MessageEnumeration {
        // MAIN MESSAGES
        LOGIN_REQUEST,
        LOGIN_REPLY,
        PLAYERNUMBER_REQUEST,
        REQUEST_PICK_TILE,
        KEEP_PICKING_REPLY,
        TO_PUT_TILE_REPLY,

        START_PICKING_TILE_REQUEST,
        START_PICKING_TILE_REPLY,
        START_PUTTING_TILE_REQUEST,
        MAX_TILE_PICKED,
        TO_PUT_FIRST_TILE,
        TO_PUT_TILE_REQUEST,
        TO_PUT_TILE_REPLY_ERROR,

        TO_PUT_TILE_2_OR_3_REQUEST,

        TO_PUT_TILE_2_OR_3_REPLY_ERROR,
        KEEP_PUTTING_REQUEST,

        ERROR_MESSAGE,
        KEEP_PICKING_REQUEST,
        INVALID_TILE,
        WRITE_IN_CHAT,


        FULL_LOBBY,
        TO_PICK_TILE_REPLY, TO_PICK_TILE_REQUEST, PLAYERNUMBER_REPLY,
}