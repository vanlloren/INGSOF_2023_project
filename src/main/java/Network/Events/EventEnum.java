package Network.Events;
/**
 * This enum contains all the Event type available and used to distinguish the different
 * updates coming from the classes in the package {code Model}.
 */
public enum EventEnum {
    UPDATE_PLAYERS_LIST, UPDATE_END_GAME,
    UPDATE_PLAYERS_NUMBER,
    UPDATE_CHAIR_OWNER,
    UPDATE_GAME_HAS_STARTED,
    UPDATE_CURR_PLAYER,
    UPDATE_REFILL_LIVINGROOM,
    UPDATE_MATCH_WINNER,
    UPDATE_GAME_HAS_ENDED,
    UPDATE_PICKED_LIVINGROOM_TILE,
    UPDATE_PERSONAL_GOAL,
    UPDATE_COMMON_GOAL,
    UPDATE_PLAYER_POINTS,
    UPDATE_STATUS_COMMON_GOAL2,
    UPDATE_STATUS_COMMON_GOAL1,
    UPDATE_PUT_SHELF_TILE,
    UPDATE_CHAT,
    UPDATE_TILES_AVAILABILITY
}
