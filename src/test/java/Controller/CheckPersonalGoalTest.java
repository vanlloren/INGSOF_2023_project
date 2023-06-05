package Controller;
import static org.junit.Assert.*;


import org.junit.Test;

import server.Controller.CheckPersonalGoal;
import server.Model.PlayableItemTile;

public class CheckPersonalGoalTest {
    private PlayableItemTile[][] structure;
    /**
     * Tests rules of PersonalGoal card
     */

    @Test
    public void checkPersonalGoal1(){
        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }
        structure[0][0] = new PlayableItemTile("PINK",1);
        assertEquals(1,CheckPersonalGoal.Personal_goal_1(structure));
        structure[0][2] = new PlayableItemTile("BLUE",1);
        assertEquals(2,CheckPersonalGoal.Personal_goal_1(structure));
        structure[1][4] = new PlayableItemTile("GREEN",1);
        assertEquals(4,CheckPersonalGoal.Personal_goal_1(structure));
        structure[2][3] = new PlayableItemTile("WHITE",1);
        assertEquals(6,CheckPersonalGoal.Personal_goal_1(structure));
        structure[3][1] = new PlayableItemTile("YELLOW",1);
        assertEquals(9,CheckPersonalGoal.Personal_goal_1(structure));
        structure[5][2] = new PlayableItemTile("CYAN",1);
        assertEquals(12,CheckPersonalGoal.Personal_goal_1(structure));
    }



    @Test
    public void checkPersonalGoal2(){
        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }
        structure[1][1] = new PlayableItemTile("PINK",1);
        assertEquals(1,CheckPersonalGoal.Personal_goal_2(structure));
        structure[2][0] = new PlayableItemTile("GREEN",1);
        assertEquals(2,CheckPersonalGoal.Personal_goal_2(structure));
        structure[2][2] = new PlayableItemTile("YELLOW",1);
        assertEquals(4,CheckPersonalGoal.Personal_goal_2(structure));
        structure[3][4] = new PlayableItemTile("WHITE",1);
        assertEquals(6,CheckPersonalGoal.Personal_goal_2(structure));
        structure[4][3] = new PlayableItemTile("CYAN",1);
        assertEquals(9,CheckPersonalGoal.Personal_goal_2(structure));
        structure[5][4] = new PlayableItemTile("BLUE",1);
        assertEquals(12,CheckPersonalGoal.Personal_goal_2(structure));
    }

    @Test
    public void checkPersonalGoal3(){
        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }
        structure[1][0] = new PlayableItemTile("BLUE",1);
        assertEquals(1,CheckPersonalGoal.Personal_goal_3(structure));
        structure[1][3] = new PlayableItemTile("YELLOW",1);
        assertEquals(2,CheckPersonalGoal.Personal_goal_3(structure));
        structure[2][2] = new PlayableItemTile("PINK",1);
        assertEquals(4,CheckPersonalGoal.Personal_goal_3(structure));
        structure[3][1] = new PlayableItemTile("GREEN",1);
        assertEquals(6,CheckPersonalGoal.Personal_goal_3(structure));
        structure[3][4] = new PlayableItemTile("CYAN",1);
        assertEquals(9,CheckPersonalGoal.Personal_goal_3(structure));
        structure[5][0] = new PlayableItemTile("WHITE",1);
        assertEquals(12,CheckPersonalGoal.Personal_goal_3(structure));
    }

    @Test
    public void checkPersonalGoal4(){
        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }
        structure[0][4] = new PlayableItemTile("YELLOW",1);
        assertEquals(1,CheckPersonalGoal.Personal_goal_4(structure));
        structure[2][0] = new PlayableItemTile("CYAN",1);
        assertEquals(2,CheckPersonalGoal.Personal_goal_4(structure));
        structure[2][2] = new PlayableItemTile("BLUE",1);
        assertEquals(4,CheckPersonalGoal.Personal_goal_4(structure));
        structure[3][3] = new PlayableItemTile("PINK",1);
        assertEquals(6,CheckPersonalGoal.Personal_goal_4(structure));
        structure[4][1] = new PlayableItemTile("WHITE",1);
        assertEquals(9,CheckPersonalGoal.Personal_goal_4(structure));
        structure[4][2] = new PlayableItemTile("GREEN",1);
        assertEquals(12,CheckPersonalGoal.Personal_goal_4(structure));
    }

    @Test
    public void checkPersonalGoal5(){
        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }
        structure[1][1] = new PlayableItemTile("CYAN",1);
        assertEquals(1,CheckPersonalGoal.Personal_goal_5(structure));
        structure[3][1] = new PlayableItemTile("BLUE",1);
        assertEquals(2,CheckPersonalGoal.Personal_goal_5(structure));
        structure[3][2] = new PlayableItemTile("WHITE",1);
        assertEquals(4,CheckPersonalGoal.Personal_goal_5(structure));
        structure[4][4] = new PlayableItemTile("PINK",1);
        assertEquals(6,CheckPersonalGoal.Personal_goal_5(structure));
        structure[5][0] = new PlayableItemTile("YELLOW",1);
        assertEquals(9,CheckPersonalGoal.Personal_goal_5(structure));
        structure[5][3] = new PlayableItemTile("GREEN",1);
        assertEquals(12,CheckPersonalGoal.Personal_goal_5(structure));
    }

    @Test
    public void checkPersonalGoal6(){
        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }
        structure[0][2] = new PlayableItemTile("CYAN",1);
        assertEquals(1,CheckPersonalGoal.Personal_goal_6(structure));
        structure[0][4] = new PlayableItemTile("GREEN",1);
        assertEquals(2,CheckPersonalGoal.Personal_goal_6(structure));
        structure[2][3] = new PlayableItemTile("WHITE",1);
        assertEquals(4,CheckPersonalGoal.Personal_goal_6(structure));
        structure[4][1] = new PlayableItemTile("YELLOW",1);
        assertEquals(6,CheckPersonalGoal.Personal_goal_6(structure));
        structure[4][3] = new PlayableItemTile("BLUE",1);
        assertEquals(9,CheckPersonalGoal.Personal_goal_6(structure));
        structure[5][0] = new PlayableItemTile("PINK",1);
        assertEquals(12,CheckPersonalGoal.Personal_goal_6(structure));
    }

    @Test
    public void checkPersonalGoal7(){
        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }
        structure[0][0] = new PlayableItemTile("YELLOW",1);
        assertEquals(1,CheckPersonalGoal.Personal_goal_7(structure));
        structure[1][3] = new PlayableItemTile("BLUE",1);
        assertEquals(2,CheckPersonalGoal.Personal_goal_7(structure));
        structure[2][1] = new PlayableItemTile("PINK",1);
        assertEquals(4,CheckPersonalGoal.Personal_goal_7(structure));
        structure[3][0] = new PlayableItemTile("CYAN",1);
        assertEquals(6,CheckPersonalGoal.Personal_goal_7(structure));
        structure[4][4] = new PlayableItemTile("YELLOW",1);
        assertEquals(9,CheckPersonalGoal.Personal_goal_7(structure));
        structure[5][2] = new PlayableItemTile("WHITE",1);
        assertEquals(12,CheckPersonalGoal.Personal_goal_7(structure));
    }

    @Test
    public void checkPersonalGoal8(){
        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }
        structure[0][4] = new PlayableItemTile("BLUE",1);
        assertEquals(1,CheckPersonalGoal.Personal_goal_8(structure));
        structure[1][1] = new PlayableItemTile("GREEN",1);
        assertEquals(2,CheckPersonalGoal.Personal_goal_8(structure));
        structure[2][2] = new PlayableItemTile("CYAN",1);
        assertEquals(4,CheckPersonalGoal.Personal_goal_8(structure));
        structure[3][0] = new PlayableItemTile("PINK",1);
        assertEquals(6,CheckPersonalGoal.Personal_goal_8(structure));
        structure[4][3] = new PlayableItemTile("WHITE",1);
        assertEquals(9,CheckPersonalGoal.Personal_goal_8(structure));
        structure[5][3] = new PlayableItemTile("YELLOW",1);
        assertEquals(12,CheckPersonalGoal.Personal_goal_8(structure));
    }

    @Test
    public void checkPersonalGoal9(){
        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }
        structure[0][2] = new PlayableItemTile("YELLOW",1);
        assertEquals(1,CheckPersonalGoal.Personal_goal_9(structure));
        structure[2][2] = new PlayableItemTile("GREEN",1);
        assertEquals(2,CheckPersonalGoal.Personal_goal_9(structure));
        structure[3][4] = new PlayableItemTile("WHITE",1);
        assertEquals(4,CheckPersonalGoal.Personal_goal_9(structure));
        structure[4][1] = new PlayableItemTile("CYAN",1);
        assertEquals(6,CheckPersonalGoal.Personal_goal_9(structure));
        structure[4][4] = new PlayableItemTile("PINK",1);
        assertEquals(9,CheckPersonalGoal.Personal_goal_9(structure));
        structure[5][0] = new PlayableItemTile("BLUE",1);
        assertEquals(12,CheckPersonalGoal.Personal_goal_9(structure));
    }

    @Test
    public void checkPersonalGoal10(){
        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }
        structure[0][4] = new PlayableItemTile("CYAN",1);
        assertEquals(1,CheckPersonalGoal.Personal_goal_10(structure));
        structure[1][1] = new PlayableItemTile("YELLOW",1);
        assertEquals(2,CheckPersonalGoal.Personal_goal_10(structure));
        structure[2][0] = new PlayableItemTile("WHITE",1);
        assertEquals(4,CheckPersonalGoal.Personal_goal_10(structure));
        structure[3][3] = new PlayableItemTile("GREEN",1);
        assertEquals(6,CheckPersonalGoal.Personal_goal_10(structure));
        structure[4][1] = new PlayableItemTile("BLUE",1);
        assertEquals(9,CheckPersonalGoal.Personal_goal_10(structure));
        structure[5][3] = new PlayableItemTile("PINK",1);
        assertEquals(12,CheckPersonalGoal.Personal_goal_10(structure));
    }

    @Test
    public void checkPersonalGoal11(){
        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }
        structure[0][2] = new PlayableItemTile("PINK",1);
        assertEquals(1,CheckPersonalGoal.Personal_goal_11(structure));
        structure[1][1] = new PlayableItemTile("WHITE",1);
        assertEquals(2,CheckPersonalGoal.Personal_goal_11(structure));
        structure[2][0] = new PlayableItemTile("YELLOW",1);
        assertEquals(4,CheckPersonalGoal.Personal_goal_11(structure));
        structure[3][2] = new PlayableItemTile("BLUE",1);
        assertEquals(6,CheckPersonalGoal.Personal_goal_11(structure));
        structure[4][4] = new PlayableItemTile("GREEN",1);
        assertEquals(9,CheckPersonalGoal.Personal_goal_11(structure));
        structure[5][3] = new PlayableItemTile("CYAN",1);
        assertEquals(12,CheckPersonalGoal.Personal_goal_11(structure));
    }

    @Test
    public void checkPersonalGoal12(){
        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }
        structure[0][2] = new PlayableItemTile("WHITE",1);
        assertEquals(1,CheckPersonalGoal.Personal_goal_12(structure));
        structure[1][1] = new PlayableItemTile("PINK",1);
        assertEquals(2,CheckPersonalGoal.Personal_goal_12(structure));
        structure[2][2] = new PlayableItemTile("BLUE",1);
        assertEquals(4,CheckPersonalGoal.Personal_goal_12(structure));
        structure[3][3] = new PlayableItemTile("CYAN",1);
        assertEquals(6,CheckPersonalGoal.Personal_goal_12(structure));
        structure[4][4] = new PlayableItemTile("YELLOW",1);
        assertEquals(9,CheckPersonalGoal.Personal_goal_12(structure));
        structure[5][0] = new PlayableItemTile("GREEN",1);
        assertEquals(12,CheckPersonalGoal.Personal_goal_12(structure));
    }

}
