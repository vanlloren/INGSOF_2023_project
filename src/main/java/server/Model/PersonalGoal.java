package server.Model;


import Observer.PersonalGoalObservable;
import Util.PersonalGoalType;
import client.view.TurnView;

import java.io.Serial;
import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * This Class represents the single {@link PersonalGoal PersonalGoal} card as written in the game rules.
 * The {@link PersonalGoal PersonalGoal} cards give points to the {@link Player Player}
 * that completes the schemes illustrated on the single cards.
 * Every {@link PersonalGoal PersonalGoal} possesses a unique scheme requiring that
 * {@link PlayableItemTile PlayableItemTiles} with a specific {@link Util.Colour Colour} must be placed
 * in specific positions in the {@link Shelf Shelf} in order to collect points.
 * <i>Have a look at MyShelfie RuleBook for further information</i>
 */
public class PersonalGoal extends PersonalGoalObservable implements Serializable {
    @Serial
    private static final long serialVersionUID = -1257488172741056944L;
    public Integer point = 0;
    public GameModel gameModel;
    public PersonalGoalType personalGoalType;

    /**
     * This method creates a new {@link PersonalGoal PersonalGoal}.
     * It also provides the bind with the actual {@link GameModel GameModel}.
     *
     * @param gameModel the {@link GameModel GameModel} to bind
     */
    public PersonalGoal(GameModel gameModel){
        this.gameModel = gameModel;
    }

    /**
     *
     * @return the amount of points achieved so far for a {@link PersonalGoal PersonalGoal}
     */
    public Integer getPoint() {
        return point;
    }

    /**
     * Sets the new amount of points achieved for a {@link PersonalGoal PersonalGoal}
     * @param point the {@link int point} to assign
     */
    public void setPoint(Integer point) {
        this.point = point;

    }

    /**
     *
     * @return the {@link PersonalGoalType PersonalGoalType} of a {@link PersonalGoal PersonalGoal}
     */
    public PersonalGoalType getPersonalGoalType(){
        return this.personalGoalType;
    }

    /**
     * Sets the {@link PersonalGoalType PersonalGoalType} of a {@link PersonalGoal PersonalGoal}
     *
     * @param personalGoalType the {@link PersonalGoalType PersonalGoalType} to assign
     * @param nickname the nickname of the {@link Player Player} who possesses the
     * {@link PersonalGoal PersonalGoal}
     */
    public void setPersonalGoalType(PersonalGoalType personalGoalType, String nickname){
        this.personalGoalType = personalGoalType;
        notifyObservers(obs -> {
            try {
                obs.OnUpdateModelPersonalGoal(new TurnView(gameModel), personalGoalType, nickname);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
    }

}










