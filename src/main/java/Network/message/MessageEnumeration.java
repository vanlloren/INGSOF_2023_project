
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


        INSERTION_REPLY,

        START_PUTTING_TILE_REQUEST,



        ERROR_MESSAGE,
        WRITE_IN_CHAT,


        FULL_LOBBY,
        TO_PICK_TILE_REQUEST, PLAYERNUMBER_REPLY,
}