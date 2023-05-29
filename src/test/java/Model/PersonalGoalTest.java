package Model;

import org.junit.*;
import server.Controller.CheckPersonalGoal;
import server.Model.GameModel;
import server.Model.PersonalGoal;
import server.Model.PlayableItemTile;


public class PersonalGoalTest {

    GameModel gameModel = new GameModel();
    PersonalGoal personalGoal=new PersonalGoal(gameModel);
    CheckPersonalGoal checkPersonalGoal = new CheckPersonalGoal();
    PlayableItemTile[][] structure = new PlayableItemTile[6][5];



    @Before
    public void initialize() {
        structure[0][0] = new PlayableItemTile("PINK", 1);
        structure[0][2] = new PlayableItemTile("BLUE", 1);
        structure[1][4] = new PlayableItemTile("GREEN", 1);
        structure[2][3] = new PlayableItemTile("WHITE", 1);
        structure[3][1] = new PlayableItemTile("YELLOW", 1);
        structure[5][2] = new PlayableItemTile("CYAN", 1);
    }
    @Test
    public void testPersonal_goal_1() {
        Assert.assertEquals (12, checkPersonalGoal.Personal_goal_1(structure));

    }

    @Before
    public void inizialized2(){
        structure[1][1]= new PlayableItemTile("PINK",1);
        structure[5][4]= new PlayableItemTile("BLUE",1);
        structure[2][0]= new PlayableItemTile("GREEN",1);
        structure[3][4]= new PlayableItemTile("WHITE",1);
        structure[2][2]= new PlayableItemTile("YELLOW",1);
        structure[4][3]= new PlayableItemTile("CYAN",1);
    }

    @Test
    public void testPersonal_goal_2() {
        Assert.assertEquals(12, checkPersonalGoal.Personal_goal_2(structure));
    }
    @Before
    public void inizialize3(){
        structure[2][2]= new PlayableItemTile("PINK",1);
        structure[1][0]= new PlayableItemTile("BLUE",1);
        structure[3][1]= new PlayableItemTile("GREEN",1);
        structure[5][0]= new PlayableItemTile("WHITE",1);
        structure[1][3]= new PlayableItemTile("YELLOW",1);
        structure[3][4]= new PlayableItemTile("CYAN",1);
    }


    @Test
    public void testPersonal_goal_3() {
        Assert.assertEquals(12, checkPersonalGoal.Personal_goal_3(structure));
    }
    @Before
    public void inizialize4(){
        structure[3][3]= new PlayableItemTile("PINK",1);
        structure[2][2]= new PlayableItemTile("BLUE",1);
        structure[4][2]= new PlayableItemTile("GREEN",1);
        structure[4][1]= new PlayableItemTile("WHITE",1);
        structure[0][4]= new PlayableItemTile("YELLOW",1);
        structure[2][0]= new PlayableItemTile("CYAN",1);
    }

    @Test
    public void testPersonal_goal_4() {
        Assert.assertEquals(12, checkPersonalGoal.Personal_goal_4(structure));
    }
    @Before
    public void inizialize5(){
        structure[4][4]= new PlayableItemTile("PINK",1);
        structure[3][1]= new PlayableItemTile("BLUE",1);
        structure[5][3]= new PlayableItemTile("GREEN",1);
        structure[3][2]= new PlayableItemTile("WHITE",1);
        structure[3][1]= new PlayableItemTile("YELLOW",1);
        structure[1][1]= new PlayableItemTile("CYAN",1);
    }


    @Test
    public void testPersonal_goal_5() {
        Assert.assertEquals(12, checkPersonalGoal.Personal_goal_5(structure));
    }
    @Before
    public void inizialize6(){
        structure[5][0]= new PlayableItemTile("PINK",1);
        structure[4][3]= new PlayableItemTile("BLUE",1);
        structure[0][4]= new PlayableItemTile("GREEN",1);
        structure[2][3]= new PlayableItemTile("WHITE",1);
        structure[4][1]= new PlayableItemTile("YELLOW",1);
        structure[0][2]= new PlayableItemTile("CYAN",1);
    }


    @Test
    public void testPersonal_goal_6() {
        Assert.assertEquals(12, checkPersonalGoal.Personal_goal_6(structure));
    }
    @Before
    public void inizialize7(){
        structure[2][1]= new PlayableItemTile("PINK",1);
        structure[1][3]= new PlayableItemTile("BLUE",1);
        structure[0][0]= new PlayableItemTile("GREEN",1);
        structure[5][2]= new PlayableItemTile("WHITE",1);
        structure[4][4]= new PlayableItemTile("YELLOW",1);
        structure[3][0]= new PlayableItemTile("CYAN",1);
    }


    @Test
    public void testPersonal_goal_7() {
        Assert.assertEquals(12, checkPersonalGoal.Personal_goal_7(structure));
    }
    @Before
    public void inizialize8(){
        structure[3][0]= new PlayableItemTile("PINK",1);
        structure[0][4]= new PlayableItemTile("BLUE",1);
        structure[1][1]= new PlayableItemTile("GREEN",1);
        structure[4][3]= new PlayableItemTile("WHITE",1);
        structure[5][3]= new PlayableItemTile("YELLOW",1);
        structure[2][2]= new PlayableItemTile("CYAN",1);
    }

    @Test
    public void testPersonal_goal_8() {
        Assert.assertEquals(12, checkPersonalGoal.Personal_goal_8(structure));
    }
    @Before
    public void inizialize9(){
        structure[4][4]= new PlayableItemTile("PINK",1);
        structure[5][0]= new PlayableItemTile("BLUE",1);
        structure[2][2]= new PlayableItemTile("GREEN",1);
        structure[3][4]= new PlayableItemTile("WHITE",1);
        structure[0][2]= new PlayableItemTile("YELLOW",1);
        structure[4][1]= new PlayableItemTile("CYAN",1);

    }


    @Test
    public void testTestPersonal_goal_9() {
        Assert.assertEquals(12, checkPersonalGoal.Personal_goal_9(structure));
    }
    @Before
    public void inizialize10(){
        structure[5][3]= new PlayableItemTile("PINK",1);
        structure[4][1]= new PlayableItemTile("BLUE",1);
        structure[3][3]= new PlayableItemTile("GREEN",1);
        structure[2][0]= new PlayableItemTile("WHITE",1);
        structure[1][1]= new PlayableItemTile("YELLOW",1);
        structure[0][4]= new PlayableItemTile("CYAN",1);
    }

    @Test
    public void testPersonal_goal_10() {
        Assert.assertEquals(12, checkPersonalGoal.Personal_goal_10(structure));
    }
    @Before
    public void inizialize11(){
        structure[0][2]= new PlayableItemTile("PINK",1);
        structure[3][2]= new PlayableItemTile("BLUE",1);
        structure[4][4]= new PlayableItemTile("GREEN",1);
        structure[1][1]= new PlayableItemTile("WHITE",1);
        structure[2][0]= new PlayableItemTile("YELLOW",1);
        structure[5][3]= new PlayableItemTile("CYAN",1);
    }


    @Test
    public void testPersonal_goal_11() {
        Assert.assertEquals(12, checkPersonalGoal.Personal_goal_11(structure));
    }
    @Before
    public void inizialize12 (){
        structure[1][1]= new PlayableItemTile("PINK",1);
        structure[2][2]= new PlayableItemTile("BLUE",1);
        structure[5][0]= new PlayableItemTile("GREEN",1);
        structure[0][2]= new PlayableItemTile("WHITE",1);
        structure[4][4]= new PlayableItemTile("YELLOW",1);
        structure[3][3]= new PlayableItemTile("CYAN",1);
    }

    @Test
    public void testPersonal_goal_12() {
        Assert.assertEquals(12, checkPersonalGoal.Personal_goal_12(structure));
    }


}