package Network.message;
/**
 * This enum contains all the message type available and used by the server and clients.
 */
public enum MessageEnumeration {
        // MAIN MESSAGES
        LOGIN_REQUEST,
        LOGIN_REPLY,

        TILE_REPLY,
        STOP_PICKING,

        CHOICE_BEGIN,
        PLAYERNUMBER_REQUEST,




        START_PUTTING_TILE_REQUEST,

        TO_PUT_FIRST_TILE,
        TO_PUT_TILE_REQUEST,
        TO_PUT_TILE_REPLY_ERROR,

        TO_PUT_TILE_2_OR_3_REQUEST,

        TO_PUT_TILE_2_OR_3_REPLY_ERROR,
        KEEP_PUTTING_REQUEST,

        ERROR_MESSAGE,
        INVALID_TILE,
        WRITE_IN_CHAT,


        FULL_LOBBY,
        TO_PICK_TILE_REPLY, TO_PICK_TILE_REQUEST, PLAYERNUMBER_REPLY,
}