package server.Model;


import Observer.PersonalGoalObservable;
import Util.PersonalGoalType;

public class PersonalGoal extends PersonalGoalObservable {
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










