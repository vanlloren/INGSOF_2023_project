package Network.Events;

public class UpdateStatusCommonGoal1Event extends Event{
    private final String nickname;
    public UpdateStatusCommonGoal1Event(String nickname) {
        super(EventEnum.UPDATE_STATUS_COMMON_GOAL1);
        this.nickname = nickname;
    }

    public String getNickname(){
        return this.nickname;
    }
}

