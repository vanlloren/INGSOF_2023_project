package Model;

import Util.PersonalGoalType;
import org.junit.Assert;
import org.junit.Test;
import server.Model.GameModel;
import server.Model.PersonalGoal;


public class PersonalGoalTest {

    GameModel gameModel = new GameModel();
    PersonalGoal personalGoal =new PersonalGoal(gameModel);


    @Test
    public void testSetGetPoint(){
        personalGoal.setPoint(5);

        Assert.assertEquals(5, (int) personalGoal.getPoint());
    }

    @Test
    public void testSetGetPersonalGoalType(){
        personalGoal.setPersonalGoalType(PersonalGoalType.PERSONALGOAL3, "tizio");
        Assert.assertEquals(personalGoal.getPersonalGoalType(), PersonalGoalType.PERSONALGOAL3);
        Assert.assertNotEquals(personalGoal.getPersonalGoalType(), PersonalGoalType.PERSONALGOAL2);
    }

}