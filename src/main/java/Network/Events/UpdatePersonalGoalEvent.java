package Network.Events;
import Util.PersonalGoalType;

/**
 *An extension of the {@link Event event} Class.
 *A {@link UpdatePersonalGoalEvent updatePersonalGoalEvent} is created when
 * we want to incapsulate inside a class all the attributes referred to a model change
 * in this case when the user has achieved a {@link server.Model.PersonalGoal personalGoal}
 */
public class UpdatePersonalGoalEvent extends Event{
    private final PersonalGoalType personalGoalType;
    private final String player;


    /**
     *This method creates an instance of {@link UpdatePersonalGoalEvent updatePersonalGoalEvent}
     * @param player is the name of the {@link server.Model.Player player}
     * that has achieved his personalGoal.
     * @param personalGoalType is the type of the achieved one
     */
    public UpdatePersonalGoalEvent(String player, PersonalGoalType personalGoalType) {
        super(EventEnum.UPDATE_PERSONAL_GOAL);
        this.personalGoalType = personalGoalType;
        this.player = player;
    }

    /**
     @return the {@link PersonalGoalType personalGoalType} achieved by the {@link server.Model.Player player}
     of this instance
     */
    public PersonalGoalType getPersonalGoalType(){
        return this.personalGoalType;
    }

    /**
     @return the attribute {@code player} of this instance
     */
    public String getPlayer(){
        return this.player;
    }
}

