package Observer;

public interface PlayerObserver {
    void OnUpdateModelPlayerPoint(Integer points);

    void OnUpdateModelStatusCommonGoal2();

    void OnUpdateModelStatusCommonGoal1();

    void OnUpdateModelPlayerEndGame(Boolean endgame);
}
