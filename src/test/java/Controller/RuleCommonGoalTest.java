package Controller;

import server.Controller.RuleCommonGoal;
import static org.junit.Assert.*;
import org.junit.Test;
import server.Model.PlayableItemTile;

public class RuleCommonGoalTest  {
    private PlayableItemTile[][] structure;

    /**
     * Tests rules of commonGoal card
     */

    @Test
    public void TestCheckCrux(){

        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }
        int i = 1;
        int j = 1;
        structure[i][j] = new PlayableItemTile("GREEN", 1);
        structure[i+1][j+1] = new PlayableItemTile("GREEN", 1);
        structure[i-1][j-1] = new PlayableItemTile("GREEN", 1);
        structure[i+1][j-1] = new PlayableItemTile("GREEN", 1);
        structure[i-1][j+1] = new PlayableItemTile("GREEN", 1);
        assertTrue(RuleCommonGoal.checkCrux(structure));



        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }
        j = 2;
        structure[i][j] = new PlayableItemTile("GREEN", 1);
        structure[i+1][j+1] = new PlayableItemTile("GREEN", 1);
        structure[i-1][j-1] = new PlayableItemTile("GREEN", 1);
        structure[i+1][j-1] = new PlayableItemTile("GREEN", 1);
        structure[i-1][j+1] = new PlayableItemTile("GREEN", 1);
        assertTrue(RuleCommonGoal.checkCrux(structure));

        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }
        j = 3;
        structure[i][j] = new PlayableItemTile("GREEN", 1);
        structure[i+1][j+1] = new PlayableItemTile("GREEN",1 );
        structure[i-1][j-1] = new PlayableItemTile("GREEN", 1);
        structure[i+1][j-1] = new PlayableItemTile("GREEN", 1);
        structure[i-1][j+1] = new PlayableItemTile("GREEN", 1);
        assertTrue(RuleCommonGoal.checkCrux(structure));


        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }
        i = 2;
        j = 1;
        structure[i][j] = new PlayableItemTile("GREEN", 1);
        structure[i+1][j+1] = new PlayableItemTile("GREEN", 1);
        structure[i-1][j-1] = new PlayableItemTile("GREEN", 1);
        structure[i+1][j-1] = new PlayableItemTile("GREEN", 1);
        structure[i-1][j+1] = new PlayableItemTile("GREEN", 1);
        assertTrue(RuleCommonGoal.checkCrux(structure));

        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }
        j = 2;
        structure[i][j] = new PlayableItemTile("GREEN", 1);
        structure[i+1][j+1] = new PlayableItemTile("GREEN", 1);
        structure[i-1][j-1] = new PlayableItemTile("GREEN", 1);
        structure[i+1][j-1] = new PlayableItemTile("GREEN", 1);
        structure[i-1][j+1] = new PlayableItemTile("GREEN", 1);
        assertTrue(RuleCommonGoal.checkCrux(structure));

        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }

        j = 3;
        structure[i][j] = new PlayableItemTile("GREEN", 1);
        structure[i+1][j+1] = new PlayableItemTile("GREEN", 1);
        structure[i-1][j-1] = new PlayableItemTile("GREEN", 1);
        structure[i+1][j-1] = new PlayableItemTile("GREEN", 1);
        structure[i-1][j+1] = new PlayableItemTile("GREEN", 1);
        assertTrue(RuleCommonGoal.checkCrux(structure));

        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }

        i = 3;
        j = 1;
        structure[i][j] = new PlayableItemTile("GREEN", 1);
        structure[i+1][j+1] = new PlayableItemTile("GREEN", 1);
        structure[i-1][j-1] = new PlayableItemTile("GREEN", 1);
        structure[i+1][j-1] = new PlayableItemTile("GREEN", 1);
        structure[i-1][j+1] = new PlayableItemTile("GREEN", 1);
        assertTrue(RuleCommonGoal.checkCrux(structure));

        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }
        j = 2;
        structure[i][j] = new PlayableItemTile("GREEN", 1);
        structure[i+1][j+1] = new PlayableItemTile("GREEN", 1);
        structure[i-1][j-1] = new PlayableItemTile("GREEN", 1);
        structure[i+1][j-1] = new PlayableItemTile("GREEN", 1);
        structure[i-1][j+1] = new PlayableItemTile("GREEN", 1);
        assertTrue(RuleCommonGoal.checkCrux(structure));

        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }
         j = 3;
        structure[i][j] = new PlayableItemTile("GREEN", 1);
        structure[i+1][j+1] = new PlayableItemTile("GREEN", 1);
        structure[i-1][j-1] = new PlayableItemTile("GREEN", 1);
        structure[i+1][j-1] = new PlayableItemTile("GREEN", 1);
        structure[i-1][j+1] = new PlayableItemTile("GREEN", 1);
        assertTrue(RuleCommonGoal.checkCrux(structure));

        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }
        i = 4;
        j = 1;
        structure[i][j] = new PlayableItemTile("GREEN", 1);
        structure[i+1][j+1] = new PlayableItemTile("GREEN", 1);
        structure[i-1][j-1] = new PlayableItemTile("GREEN", 1);
        structure[i+1][j-1] = new PlayableItemTile("GREEN", 1);
        structure[i-1][j+1] = new PlayableItemTile("GREEN", 1);
        assertTrue(RuleCommonGoal.checkCrux(structure));


        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }

        j = 2;
        structure[i][j] = new PlayableItemTile("GREEN", 1);
        structure[i+1][j+1] = new PlayableItemTile("GREEN", 1);
        structure[i-1][j-1] = new PlayableItemTile("GREEN", 1);
        structure[i+1][j-1] = new PlayableItemTile("GREEN", 1);
        structure[i-1][j+1] = new PlayableItemTile("GREEN", 1);
        assertTrue(RuleCommonGoal.checkCrux(structure));

        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }
        j = 3;
        structure[i][j] = new PlayableItemTile("GREEN", 1);
        structure[i+1][j+1] = new PlayableItemTile("GREEN", 1);
        structure[i-1][j-1] = new PlayableItemTile("GREEN", 1);
        structure[i+1][j-1] = new PlayableItemTile("GREEN", 1);
        structure[i-1][j+1] = new PlayableItemTile("GREEN",1 );
        assertTrue(RuleCommonGoal.checkCrux(structure));

        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }

        structure[3][2] = new PlayableItemTile("YELLOW", 1);
        structure[5][0] = new PlayableItemTile("BLUE", 1);
        assertFalse(RuleCommonGoal.checkCrux(structure));

    }
    @Test
    public void TestCheckColumn1(){
    structure = new PlayableItemTile[6][5];
    for (int x=0; x<6;x++){
        for (int y=0; y<5;y++) {
            structure[x][y] = new PlayableItemTile("VOID", 1);
        }
    }

    for(int i=0;i<6;i++){
        structure[i][0] = new PlayableItemTile("WHITE", 1);
    }
    structure[1][0] = new PlayableItemTile("BLUE", 1);
    structure[2][0] = new PlayableItemTile("GREEN", 1);

    assertNotEquals(true,RuleCommonGoal.CheckColumn1(structure));

        for(int i=0;i<6;i++){
            structure[i][3] = new PlayableItemTile("BLUE", 1);
        }
        for(int i=0;i<6;i++){
            structure[i][2] = new PlayableItemTile("PINK", 1);
        }
        assertTrue(RuleCommonGoal.CheckColumn1(structure));

        structure[3][0] = new PlayableItemTile("PINK", 1);
        assertNotEquals(true,RuleCommonGoal.CheckColumn1(structure));

        for(int i=0;i<6;i++){
            structure[i][4] = new PlayableItemTile("GREEN", 1);
        }
        assertTrue(RuleCommonGoal.CheckColumn1(structure));

        structure[1][4]= new PlayableItemTile("VOID", 1);
        assertNotEquals(true,RuleCommonGoal.CheckColumn1(structure));
    }

    @Test
    public void TestCheckLine1(){
        //easy
        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }

        structure[0][0] = new PlayableItemTile("BLUE", 1);
        structure[0][1] = new PlayableItemTile("BLUE", 1);
        structure[0][2] = new PlayableItemTile("BLUE", 1);
        structure[0][3] = new PlayableItemTile("BLUE", 1);
        structure[0][4] = new PlayableItemTile("BLUE", 1);
        structure[1][0] = new PlayableItemTile("GREEN", 2);
        structure[1][1] = new PlayableItemTile("GREEN", 2);
        structure[1][2] = new PlayableItemTile("GREEN", 2);
        structure[1][3] = new PlayableItemTile("GREEN", 2);
        structure[1][4] = new PlayableItemTile("GREEN", 2);
        structure[5][0] = new PlayableItemTile("PINK", 2);
        structure[5][1] = new PlayableItemTile("PINK", 2);
        structure[5][2] = new PlayableItemTile("PINK", 2);
        structure[5][3] = new PlayableItemTile("PINK", 2);
        structure[5][4] = new PlayableItemTile("PINK", 2);
        structure[4][0] = new PlayableItemTile("YELLOW", 2);
        structure[4][1] = new PlayableItemTile("YELLOW", 2);
        structure[4][2] = new PlayableItemTile("YELLOW", 2);
        structure[4][3] = new PlayableItemTile("YELLOW", 2);
        structure[4][4] = new PlayableItemTile("YELLOW", 2);
        assertTrue(RuleCommonGoal.CheckLine1(structure));

        structure[4][4] = new PlayableItemTile("WHITE", 2);
        assertTrue(RuleCommonGoal.CheckLine1(structure));

        structure[4][3] = new PlayableItemTile("CYAN", 2);
        assertTrue(RuleCommonGoal.CheckLine1(structure));

        structure[5][3] = new PlayableItemTile("BLUE", 2);
        assertTrue(RuleCommonGoal.CheckLine1(structure));

        structure[4][2] = new PlayableItemTile("PINK", 2);
        assertFalse(RuleCommonGoal.CheckLine1(structure));
    }

    @Test
    public void TestCheckColumn2(){
        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }

        structure[0][2] = new PlayableItemTile("CYAN", 2);
        structure[1][2] = new PlayableItemTile("WHITE", 2);
        structure[2][2] = new PlayableItemTile("BLUE", 2);
        structure[3][2] = new PlayableItemTile("GREEN", 2);
        structure[4][2] = new PlayableItemTile("YELLOW", 2);
        structure[5][2] = new PlayableItemTile("PINK", 2);
        assertNotEquals(true,RuleCommonGoal.CheckColumn2(structure));

        structure[0][3] = new PlayableItemTile("CYAN", 2);
        structure[1][3] = new PlayableItemTile("WHITE", 2);
        structure[2][3] = new PlayableItemTile("BLUE", 2);
        structure[3][3] = new PlayableItemTile("GREEN", 2);
        structure[4][3] = new PlayableItemTile("YELLOW", 2);
        structure[5][3] = new PlayableItemTile("PINK", 2);
        assertTrue(RuleCommonGoal.CheckColumn2(structure));

        structure[0][4] = new PlayableItemTile("CYAN", 2);
        structure[1][4] = new PlayableItemTile("WHITE", 2);
        structure[2][4] = new PlayableItemTile("BLUE", 2);
        structure[3][4] = new PlayableItemTile("GREEN", 2);
        structure[4][4] = new PlayableItemTile("YELLOW", 2);
        structure[5][4] = new PlayableItemTile("PINK", 2);
        assertTrue(RuleCommonGoal.CheckColumn2(structure));

        structure[5][4] = new PlayableItemTile("YELLOW", 2);
        assertTrue(RuleCommonGoal.CheckColumn2(structure));

        structure[5][2] = new PlayableItemTile("CYAN", 2);
        assertNotEquals(true,RuleCommonGoal.CheckColumn2(structure));

    }

    @Test
    public void TestCheckLine2(){
        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }

        structure[0][0] = new PlayableItemTile("CYAN", 1);
        structure[0][1] = new PlayableItemTile("BLUE", 1);
        structure[0][2] = new PlayableItemTile("PINK", 1);
        structure[0][3] = new PlayableItemTile("GREEN", 1);
        structure[0][4] = new PlayableItemTile("WHITE", 1);
        assertNotEquals(true,RuleCommonGoal.CheckColumn2(structure));

        structure[4][0] = new PlayableItemTile("YELLOW", 2);
        structure[4][1] = new PlayableItemTile("WHITE", 2);
        structure[4][2] = new PlayableItemTile("GREEN", 2);
        structure[4][3] = new PlayableItemTile("PINK", 2);
        structure[4][4] = new PlayableItemTile("BLUE", 2);
        assertTrue(RuleCommonGoal.CheckLine2(structure));

        structure[0][4] = new PlayableItemTile("BLUE", 1);
        assertNotEquals(true,RuleCommonGoal.CheckColumn2(structure));
    }

    @Test
    public void TestCheckCorner(){ // basta testare il funzionamento per un colore generico
    structure = new PlayableItemTile[6][5];
        String val = "BLUE";
        structure[0][0] = new PlayableItemTile(val, 1);
        structure[0][4] = new PlayableItemTile(val, 1);
        structure[5][0] = new PlayableItemTile(val, 1);
        structure[5][4] = new PlayableItemTile(val, 1);

        assertTrue(RuleCommonGoal.checkCorner(structure));
        // verifico che sia false nel momento in cui uno dei corner è diverso rispetto agli altri
        structure[0][0] = new PlayableItemTile("RED", 1);

        assertFalse(RuleCommonGoal.checkCorner((structure)));
    }

    @Test
    public void TestCheckDiagonal(){
        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }

        for(int i =0;i<5;i++){
            structure[i][i] = new PlayableItemTile("BLUE", 1);
        }
        assertTrue(RuleCommonGoal.checkDiagonal(structure));
        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }
        for(int i =0;i<5;i++){
            structure[i+1][i] = new PlayableItemTile("BLUE", 1);
        }
        assertTrue(RuleCommonGoal.checkDiagonal(structure));


    }


    @Test
    public void TestCheckEight(){
        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }


        for(int i =0;i<5;i++){
            structure[i][i] = new PlayableItemTile("BLUE", 1);
        }
        structure[0][2] = new PlayableItemTile("BLUE", 1);
        structure[4][1] = new PlayableItemTile("BLUE", 1);
        structure[1][4] = new PlayableItemTile("BLUE", 1);
        assertTrue(RuleCommonGoal.checkEight(structure));

        structure[1][4] = new PlayableItemTile("GREEN", 2);
        assertFalse(RuleCommonGoal.checkEight(structure));
    }

    @Test
    public void TestCheckFourGroups(){

        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }
        structure[5][0] = new PlayableItemTile("BLUE",1);
        structure[5][1] = new PlayableItemTile("BLUE",1);
        structure[5][2] = new PlayableItemTile("BLUE",1);
        structure[5][3] = new PlayableItemTile("BLUE",1);
        structure[4][0] = new PlayableItemTile("BLUE",1);
        structure[4][1] = new PlayableItemTile("BLUE",1);
        structure[4][2] = new PlayableItemTile("BLUE",1);
        structure[4][3] = new PlayableItemTile("BLUE",1);
        structure[0][0] = new PlayableItemTile("CYAN",1);
        structure[0][1] = new PlayableItemTile("CYAN",1);
        structure[0][2] = new PlayableItemTile("CYAN",1);
        structure[0][3] = new PlayableItemTile("CYAN",1);
        structure[2][4] = new PlayableItemTile("BLUE",1);
        structure[2][3] = new PlayableItemTile("BLUE",1);
        structure[3][3] = new PlayableItemTile("BLUE",1);
        structure[3][2] = new PlayableItemTile("BLUE",1);
        assertTrue(RuleCommonGoal.checkFourGroups(structure));


    }

    @Test
    public void TestCheckSixCouples(){
        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }
        structure[5][4] = new PlayableItemTile("GREEN",1);
        structure[4][4] = new PlayableItemTile("GREEN",1);
        structure[5][3] = new PlayableItemTile("YELLOW",1);
        structure[4][3] = new PlayableItemTile("YELLOW",1);
        structure[5][0] = new PlayableItemTile("BLUE",1);
        structure[5][1] = new PlayableItemTile("BLUE",1);
        structure[4][0] = new PlayableItemTile("PINK",1);
        structure[4][1] = new PlayableItemTile("PINK",1);
        structure[3][0] = new PlayableItemTile("PINK",1);
        structure[3][1] = new PlayableItemTile("PINK",1);
        structure[3][2] = new PlayableItemTile("CYAN",1);
        structure[3][3] = new PlayableItemTile("CYAN",1);
        assertTrue(RuleCommonGoal.checkSixCouples(structure));

        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }
        structure[5][0] = new PlayableItemTile("GREEN",1);
        structure[5][1] = new PlayableItemTile("GREEN",1);
        structure[5][3] = new PlayableItemTile("YELLOW",1);
        structure[4][3] = new PlayableItemTile("YELLOW",1);
        structure[5][4] = new PlayableItemTile("BLUE",1);
        structure[4][4] = new PlayableItemTile("BLUE",1);
        structure[5][2] = new PlayableItemTile("PINK",1);
        structure[4][2] = new PlayableItemTile("PINK",1);
        structure[4][1] = new PlayableItemTile("PINK",1);
        structure[3][1] = new PlayableItemTile("PINK",1);
        structure[1][3] = new PlayableItemTile("CYAN",1);
        structure[20][3] = new PlayableItemTile("CYAN",1);
        assertTrue(RuleCommonGoal.checkSixCouples(structure));
    }

    @Test
    public void TestCheckSquare(){
        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }

        structure[0][4]= new PlayableItemTile("PINK",1);
        structure[1][3] = new PlayableItemTile("WHITE",1);
        structure[1][4] = new PlayableItemTile("WHITE",1);
        structure[2][3] = new PlayableItemTile("WHITE",1);

        structure[2][4] = new PlayableItemTile("WHITE",1);
        structure[3][0] = new PlayableItemTile("GREEN",1);
        structure[3][1] = new PlayableItemTile("WHITE",1);
        structure[3][2] = new PlayableItemTile("WHITE",1);
        structure[3][3] = new PlayableItemTile("BLUE",1);
        structure[3][4] = new PlayableItemTile("YELLOW",1);
        structure[4][0] = new PlayableItemTile("BLUE",1);
        structure[4][1] = new PlayableItemTile("WHITE",1);
        structure[4][2] = new PlayableItemTile("WHITE",1);

        structure[4][3] = new PlayableItemTile("BLUE",1);
        structure[4][4] = new PlayableItemTile("GREEN ",1);
        structure[5][0] = new PlayableItemTile("PINK",1);
        structure[5][1] = new PlayableItemTile("PINK",1);
        structure[5][2] = new PlayableItemTile("WHITE",1);
        structure[5][3] = new PlayableItemTile("BLUE",1);
        structure[5][4] = new PlayableItemTile("GREEN",1);
 assertTrue(RuleCommonGoal.checkSquare(structure));
        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }
        assertNotEquals(true,RuleCommonGoal.checkSquare(structure));

        structure[1][1] = new PlayableItemTile("BLUE",1);
        structure[1][2] = new PlayableItemTile("BLUE",1);
        structure[2][2] = new PlayableItemTile("BLUE",1);
        structure[2][1] = new PlayableItemTile("BLUE",1);

        structure[4][3] = new PlayableItemTile("CYAN",1);
        structure[4][2] = new PlayableItemTile("BLUE",1);
        structure[5][2] = new PlayableItemTile("BLUE",1);
        structure[5][3] = new PlayableItemTile("BLUE",1);
        assertNotEquals(true,RuleCommonGoal.checkSquare(structure));

        structure[4][3] = new PlayableItemTile("BLUE",1);
        assertTrue(RuleCommonGoal.checkSquare(structure));

        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }
        assertNotEquals(true,RuleCommonGoal.checkSquare(structure));

        structure[5][3] = new PlayableItemTile("BLUE",1);
        structure[5][4] = new PlayableItemTile("BLUE",1);
        structure[4][3] = new PlayableItemTile("BLUE",1);
        structure[4][4] = new PlayableItemTile("BLUE",1);

        structure[0][0] = new PlayableItemTile("CYAN",1);
        structure[0][1] = new PlayableItemTile("BLUE",1);
        structure[1][1] = new PlayableItemTile("BLUE",1);
        structure[1][0] = new PlayableItemTile("BLUE",1);
        assertNotEquals(true,RuleCommonGoal.checkSquare(structure));
        structure[0][0] = new PlayableItemTile("BLUE",1);
        assertTrue(RuleCommonGoal.checkSquare(structure));

        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }
        assertNotEquals(true,RuleCommonGoal.checkSquare(structure));

        structure[5][3] = new PlayableItemTile("BLUE",1);
        structure[5][4] = new PlayableItemTile("BLUE",1);
        structure[4][3] = new PlayableItemTile("BLUE",1);
        structure[4][4] = new PlayableItemTile("BLUE",1);
        assertFalse(RuleCommonGoal.checkSquare(structure));
        structure[3][3] = new PlayableItemTile("CYAN",1);
        structure[3][4] = new PlayableItemTile("BLUE",1);
        structure[2][3] = new PlayableItemTile("BLUE",1);
        structure[2][4] = new PlayableItemTile("BLUE",1);
        assertNotEquals(true,RuleCommonGoal.checkSquare(structure));
        structure[3][3] = new PlayableItemTile("BLUE",1);
        assertTrue(RuleCommonGoal.checkSquare(structure));

        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }
        assertNotEquals(true,RuleCommonGoal.checkSquare(structure));

        structure[5][0] = new PlayableItemTile("BLUE",1);
        structure[5][1] = new PlayableItemTile("BLUE",1);
        structure[4][0] = new PlayableItemTile("BLUE",1);
        structure[4][1] = new PlayableItemTile("BLUE",1);
        assertFalse(RuleCommonGoal.checkSquare(structure));
        structure[2][1] = new PlayableItemTile("CYAN",1);
        structure[2][2] = new PlayableItemTile("BLUE",1);
        structure[3][1] = new PlayableItemTile("BLUE",1);
        structure[3][2] = new PlayableItemTile("BLUE",1);
        assertNotEquals(true,RuleCommonGoal.checkSquare(structure));
        structure[2][1] = new PlayableItemTile("BLUE",1);
        assertTrue(RuleCommonGoal.checkSquare(structure));

    }

    @Test
    public void TestCheckStair(){
        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }

        structure[1][0] = new PlayableItemTile("PINK", 1);
        structure[2][1] = new PlayableItemTile("GREEN", 1);
        structure[3][2] = new PlayableItemTile("BLUE", 1);
        structure[4][3] = new PlayableItemTile("PINK", 1);
        structure[5][4] = new PlayableItemTile("PINK", 1);
        assertTrue(RuleCommonGoal.checkStair(structure));

        structure[0][0] = new PlayableItemTile("PINK", 1);
        assertNotEquals(true,RuleCommonGoal.checkStair(structure));

        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }
        structure[1][4] = new PlayableItemTile("PINK", 1);
        structure[2][3] = new PlayableItemTile("GREEN", 1);
        structure[3][2] = new PlayableItemTile("BLUE", 1);
        structure[4][1] = new PlayableItemTile("PINK", 1);
        structure[5][0] = new PlayableItemTile("PINK", 1);
        assertTrue(RuleCommonGoal.checkStair(structure));

        structure[4][0] = new PlayableItemTile("PINK", 1);
        assertNotEquals(true,RuleCommonGoal.checkStair(structure));

        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }
        structure[0][0] = new PlayableItemTile("PINK", 1);
        structure[1][1] = new PlayableItemTile("GREEN", 1);
        structure[2][2] = new PlayableItemTile("BLUE", 1);
        structure[3][3] = new PlayableItemTile("PINK", 1);
        structure[4][4] = new PlayableItemTile("PINK", 1);
        assertTrue(RuleCommonGoal.checkStair(structure));

        structure[2][3] = new PlayableItemTile("PINK", 1);
        assertNotEquals(true,RuleCommonGoal.checkStair(structure));

        structure = new PlayableItemTile[6][5];
        for (int x=0; x<6;x++){
            for (int y=0; y<5;y++) {
                structure[x][y] = new PlayableItemTile("VOID", 1);
            }
        }
        structure[0][4] = new PlayableItemTile("PINK", 1);
        structure[1][3] = new PlayableItemTile("GREEN", 1);
        structure[2][2] = new PlayableItemTile("BLUE", 1);
        structure[3][1] = new PlayableItemTile("PINK", 1);
        structure[4][0] = new PlayableItemTile("PINK", 1);
        assertTrue(RuleCommonGoal.checkStair(structure));

        structure[2][1] = new PlayableItemTile("PINK", 1);
        assertNotEquals(true,RuleCommonGoal.checkStair(structure));
    }

}
