package Controller;

import Util.CommonGoalType;
import org.junit.Assert;
import server.Controller.CheckCommonGoal;

import org.junit.Test;

import server.Model.GameModel;

import server.Model.Shelf;

public class CheckCommonGoalTest {
    Shelf shelf = new Shelf(new GameModel());

    @Test
    public void testCheckGoal(){
        shelf.setUp();


        Assert.assertFalse(CheckCommonGoal.checkGoal(shelf, CommonGoalType.COMMONGOAL01));
        Assert.assertFalse(CheckCommonGoal.checkGoal(shelf, CommonGoalType.COMMONGOAL02));
        Assert.assertFalse(CheckCommonGoal.checkGoal(shelf, CommonGoalType.COMMONGOAL03));
        Assert.assertFalse(CheckCommonGoal.checkGoal(shelf, CommonGoalType.COMMONGOAL04));
        Assert.assertFalse(CheckCommonGoal.checkGoal(shelf, CommonGoalType.COMMONGOAL05));
        Assert.assertFalse(CheckCommonGoal.checkGoal(shelf, CommonGoalType.COMMONGOAL06));
        Assert.assertFalse(CheckCommonGoal.checkGoal(shelf, CommonGoalType.COMMONGOAL07));
        Assert.assertFalse(CheckCommonGoal.checkGoal(shelf, CommonGoalType.COMMONGOAL08));
        Assert.assertFalse(CheckCommonGoal.checkGoal(shelf, CommonGoalType.COMMONGOAL09));
        Assert.assertFalse(CheckCommonGoal.checkGoal(shelf, CommonGoalType.COMMONGOAL10));
        Assert.assertFalse(CheckCommonGoal.checkGoal(shelf, CommonGoalType.COMMONGOAL11));
        Assert.assertFalse(CheckCommonGoal.checkGoal(shelf, CommonGoalType.COMMONGOAL12));
    }


}
