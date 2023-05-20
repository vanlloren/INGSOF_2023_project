package server.Model;

import Util.CommonGoalType;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;



public class CommonGoal implements Serializable {
    @Serial
    private static final long serialVersionUID = 6744912126943243456L;
    CommonGoalType commonGoalType;
    private ArrayList<Integer> token_list = new ArrayList<>();
    private GameModel gameModel;

    public CommonGoal(GameModel gameModel){
        this.gameModel=gameModel;
    }
    public ArrayList<Integer> getToken_list(){
        return this.token_list;
    }


    public void setCommonGoalType(CommonGoalType commonGoalType){
        this.commonGoalType = commonGoalType;
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