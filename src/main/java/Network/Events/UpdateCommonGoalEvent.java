package Network.Events;

import Util.CommonGoalType;

/**
 *An extension of the {@link Event event} Class.
 * A {@link UpdateCommonGoalEvent updateCommonGoalEvent} is created when
 * at the beginning of the game when the lobby has reached
 * the requested size the {@link server.Model.GameModel gameModel} sets the two
 * {@link server.Model.CommonGoal commonGoals} so this info is encapsulated into this instance
 */
public class UpdateCommonGoalEvent extends Event{
    private final CommonGoalType commonGoalType1;
    private final CommonGoalType commonGoalType2;

    /**
     *This method creates an instance of
     * {@link Event event}
     * @param commonGoalType1 is the {@link CommonGoalType type} of the {@link server.Model.CommonGoal commonGoal1}
     * @param commonGoalType2 is the {@link CommonGoalType type} of the {@link server.Model.CommonGoal commonGoal2}
     */
    public UpdateCommonGoalEvent(CommonGoalType commonGoalType1, CommonGoalType commonGoalType2) {
        super(EventEnum.UPDATE_COMMON_GOAL);
        this.commonGoalType1 = commonGoalType1;
        this.commonGoalType2 = commonGoalType2;
    }

    /**
     * @return the attribute {@code commonGoalType1} of this instance
     */
    public CommonGoalType getCommonGoalType1(){
        return this.commonGoalType1;
    }

    /**
     * @return the attribute {@code commonGoalType2} of this instance
     */
    public CommonGoalType getCommonGoalType2(){return this.commonGoalType2;}
}
