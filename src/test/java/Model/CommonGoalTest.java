package Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import server.Model.CommonGoal;

import java.util.ArrayList;
public class CommonGoalTest {

    @Test
    public void testSetTokens(){
        CommonGoal commonGoal = new CommonGoal();
        commonGoal.setTokens(2);
        ArrayList<Integer> token_list = new ArrayList<>();
        token_list.add(4);
        token_list.add(8);
        Assertions.assertEquals(token_list, commonGoal.getToken_list());


        commonGoal = new CommonGoal();
        commonGoal.setTokens(3);
        token_list = new ArrayList<>();
        token_list.add(4);
        token_list.add(6);
        token_list.add(8);
        Assertions.assertEquals(token_list, commonGoal.getToken_list());


        commonGoal = new CommonGoal();
        commonGoal.setTokens(4);
        token_list = new ArrayList<>();
        token_list.add(2);
        token_list.add(4);
        token_list.add(6);
        token_list.add(8);
        Assertions.assertEquals(token_list, commonGoal.getToken_list());
    }
}
