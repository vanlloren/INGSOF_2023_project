package server.Model;

import Observer.CommonGoalObservable;
import Util.CommonGoalType;

import java.util.ArrayList;



public class CommonGoal extends CommonGoalObservable {
    CommonGoalType commonGoalType;
    private ArrayList<Integer> token_list = new ArrayList<>();

    public ArrayList<Integer> getToken_list(){
        return this.token_list;
    }


    public void setCommonGoalType(CommonGoalType commonGoalType){
        this.commonGoalType = commonGoalType;
        notifyObservers(obs -> obs.OnUpdateModelCommonGoal(commonGoalType));
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