package Model;

import Util.PersonalGoalType;
import org.junit.*;
import server.Controller.CheckPersonalGoal;
import server.Model.GameModel;
import server.Model.PersonalGoal;
import server.Model.PlayableItemTile;


public class PersonalGoalTest {

    GameModel gameModel = new GameModel();
    PersonalGoal personalGoal =new PersonalGoal(gameModel);


    @Test
    public void testSetGetPoint(){
        personalGoal.setPoint(5);

        Assert.assertTrue(personalGoal.getPoint() == 5);
    }

    @Test
    public void testSetGetPersonalGoalType(){
        personalGoal.setPersonalGoalType(PersonalGoalType.PERSONALGOAL3, "tizio");
        Assert.assertTrue(personalGoal.getPersonalGoalType().equals(PersonalGoalType.PERSONALGOAL3));
        Assert.assertFalse(personalGoal.getPersonalGoalType().equals(PersonalGoalType.PERSONALGOAL2));
    }

}