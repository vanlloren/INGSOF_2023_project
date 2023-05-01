package server.Model;
import Util.Colour;
import Util.PersonalGoalType;

public class PersonalGoal {
    PersonalGoalType personalGoalType;
    int point;

    public PersonalGoalType getPersonalGoalType() {
        return this.personalGoalType;
    }

    public int getPoint() {
        return this.point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setPersonalGoalType(PersonalGoalType personalGoalType) {
        this.personalGoalType = personalGoalType;
    }


}










