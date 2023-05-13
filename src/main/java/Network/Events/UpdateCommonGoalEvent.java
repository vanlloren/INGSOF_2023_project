package Network.Events;

import Util.CommonGoalType;

public class UpdateCommonGoalEvent extends Event{
    private CommonGoalType commonGoalType;

    public UpdateCommonGoalEvent(CommonGoalType commonGoalType) {
        super(EventEnum.UPDATE_COMMON_GOAL);
        this.commonGoalType = commonGoalType;
    }

    public CommonGoalType getCommonGoalType(){
        return this.commonGoalType;
    }
}
