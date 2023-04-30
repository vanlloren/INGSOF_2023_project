package Network.message;
/**
 * This enum contains all the message type available and used by the server and clients.
 */
public enum MessageEnumeration {

        LOGIN_REQUEST, LOGIN_REPLY,
        PLAYERNUMBER_REQUEST, PLAYERNUMBER_REPLY,
        LOBBY,
        PICK_CHAI_OWNER,
        LIVINGROOM,
        PERSONAL_GOAL_LIST,
        PICKTILE,
        PUTTILE,
        WIN,
        WIN_FX,
        LOSE,

        //utility:
        GAME_LOAD,
        MATCH_INFO,
        ERROR,
        PERSISTENCE

}
