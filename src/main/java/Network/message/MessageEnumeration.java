package Network.message;
/**
 * This enum contains all the message type available and used by the server and clients.
 */
public enum MessageEnumeration {
        // MAIN MESSAGES
        LOGIN_REQUEST, LOGIN_REPLY,
        PLAYERNUMBER_REQUEST, PLAYERNUMBER_REPLY,
        REQUEST_PICK_TILE,
        REPLY_PICK_TILE,
        KEEP_PICKING_REPLY,
        REQUEST_INSERT_TILE,
        REPLY_INSERT_TILE,
        WINNER_MESSAGE,
        ERROR_MESSAGE,


        //OPTIONAL REQUEST SATISFIABLE TRHOUGH A SIMPLE VIEW OF THE MODEL MADE OF GETTER METHODS
        SHOW_LIVINGROOM,
        SHOW_PERSONAL_GOAL,
        SHOW_PERSONAL_SHELF,
        SHOW_ADD_POINT,
        SHOW_PARTIAL_SCORE,
        SHOW_LOBBY,

}