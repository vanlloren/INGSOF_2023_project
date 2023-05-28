package server.Model;


import Observer.PersonalGoalObservable;
import Util.PersonalGoalType;
import client.view.TurnView;

import java.io.Serial;
import java.io.Serializable;
import java.rmi.RemoteException;

public class PersonalGoal extends PersonalGoalObservable implements Serializable {
    @Serial
    private static final long serialVersionUID = -1257488172741056944L;
    public Integer point = 0;
    public GameModel gameModel;
    public PersonalGoalType personalGoalType;

    public PersonalGoal(GameModel gameModel){
        this.gameModel = gameModel;
    }
    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;

    }
    public PersonalGoalType getPersonalGoalType(){
        return this.personalGoalType;
    }

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










