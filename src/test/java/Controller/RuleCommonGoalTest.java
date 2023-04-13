package Controller;

import org.junit.Before;
import server.Controller.RuleCommonGoal;
import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.*;

import server.Model.ItemTile;


public class RuleCommonGoalTest  {
    private ItemTile[][] structure;

    /**
     * Tests rules of commonGoal card
     */
@BeforeEach

public void setUp(){
    ItemTile[][] structure = new ItemTile[6][5];
}

    @Test
    public void TestCheckCrux(){


    assertEquals(true,RuleCommonGoal.checkCrux(structure));
    }
    @Test
    public void TestCheckColumn1(){

        assertEquals(true,RuleCommonGoal.CheckColumn1(structure));
    }

    @Test
    public void TestCheckLine1(){

        assertEquals(true,RuleCommonGoal.CheckLine1(structure));
    }

    @Test
    public void TestCheckColumn2(){

        assertEquals(true,RuleCommonGoal.CheckColumn2(structure));
    }

    @Test
    public void TestCheckLine2(){

        assertEquals(true,RuleCommonGoal.CheckLine2(structure));
    }

    @Test
    public void TestCheckCorner(){

        assertEquals(true,RuleCommonGoal.checkCorner(structure));
    }

    @Test
    public void TestCheckDiagonal(){

        assertEquals(true,RuleCommonGoal.checkDiagonal(structure));
    }

    @Test
    public void TestCheckEight(){

        assertEquals(true,RuleCommonGoal.checkEight(structure));
    }

    @Test
    public void TestCheckFourGroups(){

        assertEquals(true,RuleCommonGoal.checkFourGroups(structure));
    }

    @Test
    public void TestCheckSixCouples(){

        assertEquals(true,RuleCommonGoal.checkSixCouples(structure));
    }

    @Test
    public void TestCheckSquare(){

        assertEquals(true,RuleCommonGoal.checkSquare(structure));
    }

    @Test
    public void TestCheckStair(){

        assertEquals(true,RuleCommonGoal.checkStair(structure));
    }










}
