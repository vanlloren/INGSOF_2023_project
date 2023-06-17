package Util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import server.Model.CommonGoal;

public class RandCommonGoalTest {


    @Test
    public void testSetType(){
        for(int i=0; i<12; i++){
            for(int j=0; j<12; j++){
                if(i!=j){
                    CommonGoal commonGoal1 = new CommonGoal();
                    CommonGoal commonGoal2 = new CommonGoal();
                    RandCommonGoal.setType(commonGoal1, commonGoal2, i, j);
                    Assertions.assertNotEquals(commonGoal1.getCommonGoalType(), commonGoal2.getCommonGoalType());
                }
            }
        }

        CommonGoal commonGoal1 = new CommonGoal();
        CommonGoal commonGoal2 = new CommonGoal();
        RandCommonGoal.setType(commonGoal1, commonGoal2, 0, 0);
        Assertions.assertNotEquals(commonGoal1.getCommonGoalType(), commonGoal2.getCommonGoalType());

    }
}
