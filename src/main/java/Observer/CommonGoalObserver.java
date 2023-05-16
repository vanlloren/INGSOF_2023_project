package Observer;

import Util.CommonGoalType;
import client.view.TurnView;

public interface CommonGoalObserver {
    void OnUpdateModelCommonGoal(TurnView turnView, CommonGoalType commonGoalType);

}
