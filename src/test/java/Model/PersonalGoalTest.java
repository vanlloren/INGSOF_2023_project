package Model;

import org.junit.*;
import server.Model.PersonalGoal;
import server.Model.PlayableItemTile;


public class PersonalGoalTest {

    PersonalGoal personalGoal=new PersonalGoal();
    PlayableItemTile[][] structure = new PlayableItemTile[6][5];

    @Before
    public void initialize() {
        structure[0][0] = new PlayableItemTile("PINK", 0 );
        structure[2][0] = new PlayableItemTile("BLUE", 0);
        structure[4][1] = new PlayableItemTile("GREEN", 0);
        structure[3][2] = new PlayableItemTile("WHITE", 0);
        structure[1][3] = new PlayableItemTile("YELLOW", 0);
        structure[2][5] = new PlayableItemTile("CYAN", 0);
    }
    @Test
    public void testPersonal_goal_1() {
        Assert.assertEquals (12, personalGoal.Personal_goal_1(structure));

    }
    @Before
    public void inizialized2(){
        structure[1][1]= new PlayableItemTile("PINK", 0);
        structure[4][5]= new PlayableItemTile("BLUE", 0);
        structure[0][2]= new PlayableItemTile("GREEN", 0);
        structure[4][3]= new PlayableItemTile("WHITE", 0);
        structure[2][2]= new PlayableItemTile("YELLOW", 0);
        structure[3][4]= new PlayableItemTile("CYAN", 0);
    }


    public void testPersonal_goal_2() {
        Assert.assertEquals(12, personalGoal.Personal_goal_2(structure));
    }
    @Before
    public void inizialize3(){
        structure[2][2]= new PlayableItemTile("PINK", 0);
        structure[0][1]= new PlayableItemTile("BLUE", 0);
        structure[1][3]= new PlayableItemTile("GREEN", 0);
        structure[0][5]= new PlayableItemTile("WHITE", 0);
        structure[3][1]= new PlayableItemTile("YELLOW", 0);
        structure[4][3]= new PlayableItemTile("CYAN", 0);
    }



    public void testPersonal_goal_3() {
        assert(12, personalGoal.Personal_goal_3(structure));
    }
    @Before
    public void inizialize4(){
        structure[3][3]= new PlayableItemTile("PINK", 0);
        structure[2][2]= new PlayableItemTile("BLUE", 0);
        structure[2][4]= new PlayableItemTile("GREEN", 0);
        structure[1][4]= new PlayableItemTile("WHITE", 0);
        structure[4][0]= new PlayableItemTile("YELLOW", 0);
        structure[0][2]= new PlayableItemTile("CYAN", 0);
    }

    public void testPersonal_goal_4() {
        assert(12, personalGoal.Personal_goal_4(structure));
    }
    @Before
    public void inizialize5(){
        structure[4][4]= new PlayableItemTile("PINK", 0);
        structure[1][3]= new PlayableItemTile("BLUE", 0);
        structure[3][5]= new PlayableItemTile("GREEN", 0);
        structure[2][3]= new PlayableItemTile("WHITE", 0);
        structure[1][3]= new PlayableItemTile("YELLOW", 0);
        structure[1][1]= new PlayableItemTile("CYAN", 0);
    }


    public void testPersonal_goal_5() {
        assert(12, personalGoal.Personal_goal_5(structure));
    }
    @Before
    public void inizialize6(){
        structure[0][5]= new PlayableItemTile("PINK", 0);
        structure[3][4]= new PlayableItemTile("BLUE", 0);
        structure[4][0]= new PlayableItemTile("GREEN", 0);
        structure[3][2]= new PlayableItemTile("WHITE", 0);
        structure[1][4]= new PlayableItemTile("YELLOW", 0);
        structure[2][0]= new PlayableItemTile("CYAN", 0);
    }


    public void testPersonal_goal_6() {
        assert(12, personalGoal.Personal_goal_6(structure));
    }
    @Before
    public void inizialize7(){
        structure[0][2]= new PlayableItemTile("PINK", 0);
        structure[3][1]= new PlayableItemTile("BLUE", 0);
        structure[0][0]= new PlayableItemTile("GREEN", 0);
        structure[2][5]= new PlayableItemTile("WHITE", 0);
        structure[4][4]= new PlayableItemTile("YELLOW", 0);
        structure[0][3]= new PlayableItemTile("CYAN", 0);
    }



    public void testPersonal_goal_7() {
        assert(12, personalGoal.Personal_goal_7(structure));
    }
    @Before
    public void inizialize8(){
        structure[0][3]= new PlayableItemTile("PINK", 0);
        structure[4][0]= new PlayableItemTile("BLUE", 0);
        structure[1][1]= new PlayableItemTile("GREEN", 0);
        structure[3][4]= new PlayableItemTile("WHITE", 0);
        structure[3][5]= new PlayableItemTile("YELLOW", 0);
        structure[2][2]= new PlayableItemTile("CYAN", 0);
    }

    public void testPersonal_goal_8() {
        assert(12, personalGoal.Personal_goal_8(structure));
    }
    @Before
    public void inizialize9(){
        structure[4][4]= new PlayableItemTile("PINK", 0);
        structure[0][5]= new PlayableItemTile("BLUE", 0);
        structure[2][2]= new PlayableItemTile("GREEN", 0);
        structure[4][3]= new PlayableItemTile("WHITE", 0);
        structure[2][0]= new PlayableItemTile("YELLOW", 0);
        structure[1][4]= new PlayableItemTile("CYAN", 0);

    }


    public void testTestPersonal_goal_9() {
        assert(12, personalGoal.Personal_goal_9(structure));
    }
    @Before
    public void inizialize10(){
        structure[3][5]= new PlayableItemTile("PINK", 0);
        structure[1][4]= new PlayableItemTile("BLUE", 0);
        structure[3][3]= new PlayableItemTile("GREEN", 0);
        structure[0][2]= new PlayableItemTile("WHITE", 0);
        structure[1][1]= new PlayableItemTile("YELLOW", 0);
        structure[4][0]= new PlayableItemTile("CYAN", 0);
    }


    public void testPersonal_goal_10() {
        assert(12, personalGoal.Personal_goal_10(structure));
    }
    @Before
    public void inizialize11(){
        structure[2][0]= new PlayableItemTile("PINK", 0);
        structure[2][3]= new PlayableItemTile("BLUE", 0);
        structure[4][4]= new PlayableItemTile("GREEN", 0);
        structure[1][1]= new PlayableItemTile("WHITE", 0);
        structure[0][2]= new PlayableItemTile("YELLOW", 0);
        structure[3][5]= new PlayableItemTile("CYAN", 0);
    }



    public void testPersonal_goal_11() {
        assert(12, personalGoal.Personal_goal_11(structure));
    }
    @Before
    public void inizialize12 (){
        structure[1][1]= new PlayableItemTile("PINK", 0);
        structure[2][2]= new PlayableItemTile("BLUE", 0);
        structure[0][5]= new PlayableItemTile("GREEN", 0);
        structure[2][0]= new PlayableItemTile("WHITE", 0);
        structure[4][4]= new PlayableItemTile("YELLOW", 0);
        structure[3][3]= new PlayableItemTile("CYAN",0);
    }

    public void testPersonal_goal_12() {
        assert(12, personalGoal.Personal_goal_12(structure));
    }
}