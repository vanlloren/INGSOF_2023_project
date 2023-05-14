package server.Model;


import Observer.PersonalGoalObservable;
import Util.PersonalGoalType;

import java.io.Serial;
import java.io.Serializable;

public class PersonalGoal extends PersonalGoalObservable implements Serializable {
    @Serial
    private static final long serialVersionUID = -1257488172741056944L;
    public Integer point;
    public PersonalGoalType personalGoalType;

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;

    }




    public PersonalGoalType getPersonalGoalType(){
        return this.personalGoalType;
    }

    public void setPersonalGoalType(PersonalGoalType personalGoalType){
        this.personalGoalType = personalGoalType;
        notifyObservers(obs -> obs.OnUpdateModelPersonalGoal(personalGoalType));
    }

}










