package server.Model;

import Util.CommonGoalType;

import java.util.ArrayList;



public class CommonGoal {
    CommonGoalType commonGoalType;
    private ArrayList<Integer> token_list = new ArrayList<Integer>();

    public ArrayList<Integer> getToken_list(){
        return this.token_list;
    }

    public CommonGoalType getCommonGoalType() {
        return commonGoalType;
    }

    public void setTokens(int playersNumber) { //parametro dato dal controller
        if (playersNumber == 2) {
            token_list.add(4);
            token_list.add(8);
        }
        if (playersNumber == 3) {
            token_list.add(4);
            token_list.add(6);
            token_list.add(8);
        }
        if (playersNumber == 4) {
            token_list.add(2);
            token_list.add(4);
            token_list.add(6);
            token_list.add(8);
        }
    }
}