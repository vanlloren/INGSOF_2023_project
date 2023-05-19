package Observer;

import client.view.TurnView;

public interface PlayerObserver {
    void OnUpdateModelPlayerPoint(TurnView turnView, Integer points);

    void OnUpdateModelStatusCommonGoal2(TurnView turnView);

    void OnUpdateModelStatusCommonGoal1(TurnView turnView);

}
