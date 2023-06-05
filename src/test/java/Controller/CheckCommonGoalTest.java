package Controller;
import Util.Colour;
import Util.CommonGoalType;
import org.junit.Assert;
import server.Controller.CheckCommonGoal;
import server.Controller.RuleCommonGoal;
import Util.Colour;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import server.Controller.RuleShelf;
import server.Model.GameModel;
import server.Model.PlayableItemTile;
import server.Model.Player;
import server.Model.Shelf;

import static org.junit.Assert.*;

import org.junit.jupiter.api.*;


import server.Model.PlayableItemTile;

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
