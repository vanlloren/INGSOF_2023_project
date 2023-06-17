package Network.Events;

public class UpdateStatusCommonGoal2Event extends Event{
    private final String nickname;
    public UpdateStatusCommonGoal2Event(String nickname) {
        super(EventEnum.UPDATE_STATUS_COMMON_GOAL2);
        this.nickname = nickname;
    }

    public String getNickname(){
        return this.nickname;
    }
}
