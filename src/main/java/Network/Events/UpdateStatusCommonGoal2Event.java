package Network.Events;

import Network.message.LoginRequestMessage;

/**
 *An extension of the {@link Event event} Class.
 *A {@link UpdateStatusCommonGoal1Event Commongoal1} is created when
 * we want to incapsulate inside a class all the attributes referred to a model change
 * in this case when the user has achieved a {@link server.Model.CommonGoal commonGoal2}
 */
public class UpdateStatusCommonGoal2Event extends Event{
    private final String nickname;

    /**
     * This method creates an instance of {@link LoginRequestMessage LoginRequestMessage}.
     * @param nickname a {@link String String} containing the {@code nickname} of the player that
     *  has satisfied {@link server.Model.CommonGoal commonGoal2}
     */
    public UpdateStatusCommonGoal2Event(String nickname) {
        super(EventEnum.UPDATE_STATUS_COMMON_GOAL2);
        this.nickname = nickname;
    }

    /**
     * @return the attribute {@code nickname} of this instance
     */
    public String getNickname(){
        return this.nickname;
    }
}
