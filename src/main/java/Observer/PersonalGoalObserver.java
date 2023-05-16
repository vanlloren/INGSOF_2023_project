package Observer;

import Util.PersonalGoalType;
import client.view.TurnView;

public interface PersonalGoalObserver {

    void OnUpdateModelPersonalGoal(TurnView turnView, PersonalGoalType personalGoalType, String nickname);
}
