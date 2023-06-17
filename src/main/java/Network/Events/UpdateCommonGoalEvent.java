package Network.Events;

import Util.CommonGoalType;

public class UpdateCommonGoalEvent extends Event{
    private final CommonGoalType commonGoalType1;
    private final CommonGoalType commonGoalType2;

    public UpdateCommonGoalEvent(CommonGoalType commonGoalType1, CommonGoalType commonGoalType2) {
        super(EventEnum.UPDATE_COMMON_GOAL);
        this.commonGoalType1 = commonGoalType1;
        this.commonGoalType2 = commonGoalType2;
    }

    public CommonGoalType getCommonGoalType1(){
        return this.commonGoalType1;
    }
    public CommonGoalType getCommonGoalType2(){return this.commonGoalType2;}
}
