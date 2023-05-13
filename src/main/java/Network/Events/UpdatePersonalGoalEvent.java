package Network.Events;

import Util.PersonalGoalType;

public class UpdatePersonalGoalEvent extends Event{
    private PersonalGoalType personalGoalType;
    private String player;

    public UpdatePersonalGoalEvent(String player, PersonalGoalType personalGoalType) {
        super(EventEnum.UPDATE_PERSONAL_GOAL);
        this.personalGoalType = personalGoalType;
        this.player = player;
    }

    public PersonalGoalType getPersonalGoalType(){
        return this.personalGoalType;
    }

    public String getPlayer(){
        return this.player;
    }
}

