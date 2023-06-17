package server.Model;

import Util.CommonGoalType;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This Class represents the single {@link CommonGoal CommonGoal} card as written in the game rules.
 * The {@link CommonGoal CommonGoal} cards give points to the {@link Player Player}
 * that completes the schemes illustrated on the single cards.
 * Every {@link CommonGoal CommonGoal} possesses a number of token (from a minimum of 2 to a maximum of 4)
 * depending on the number of {@link Player Players} in the match. Every token has its score
 * which is added to the point total of the {@link Player Player} that completes the {@link CommonGoal CommonGoal}
 * depending on the number of {@link Player Players} that already completed it.
 * <i>Have a look at MyShelfie RuleBook for further information</i>
 */

public class CommonGoal implements Serializable {
    @Serial
    private static final long serialVersionUID = 6744912126943243456L;
    CommonGoalType commonGoalType;
    private final ArrayList<Integer> token_list = new ArrayList<>();
    private GameModel gameModel;

    /**
     * Method that creates an instance of {@link CommonGoal CommonGoal}.
     * It also provides the binding between a {@link CommonGoal CommonGoal} and the specific instance
     * of {@link GameModel GameModel}.
     *
     * @param gameModel the {@link GameModel GameModel} to bind
     */
    public CommonGoal(GameModel gameModel){
        this.gameModel=gameModel;
    }

    /**
     * This method returns to the caller the list of the {@link CommonGoal CommonGoal's} tokens.
     *
     * @return an {@link ArrayList ArrayList} containing the {@link CommonGoal CommonGoal's} token list
     */
    public ArrayList<Integer> getToken_list(){
        return this.token_list;
    }

    /**
     * This method sets the {@link CommonGoalType CommonGoalType} for a {@link CommonGoal CommonGoal}
     *
     * @param commonGoalType the {@link CommonGoalType CommonGoalType} to assign to a {@link CommonGoal CommonGoal}
     */
    public void setCommonGoalType(CommonGoalType commonGoalType){
        this.commonGoalType = commonGoalType;
    }

    /**
     * @return the {@link CommonGoalType CommonGoalType} of a {@link CommonGoal CommonGoal}
     */
    public CommonGoalType getCommonGoalType() {
        return commonGoalType;
    }

    /**
     * This method sets the tokens for a specific {@link CommonGoal CommonGoal}.
     * Depending on the {@code playersNumber} the procedure to assign the tokens to a {@link CommonGoal CommonGoal}
     * is the following:
     * (1) if there are 2 {@link Player Players} then 2 tokens are assigned to the {@link CommonGoal CommonGoal}
     * and their score will respectively be of 8 and 4 points;
     * (2) if there are 3 {@link Player Players} then 3 tokens are assigned to the {@link CommonGoal CommonGoal}
     * and their score will respectively be of 8, 6 and 4 points;
     * (3) if there are 4 {@link Player Players} then 4 tokens are assigned to the {@link CommonGoal CommonGoal}
     * and their score will respectively be of 8, 6, 4 and 2 points;
     * @param playersNumber the number of {@link Player Players} in the match
     */
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