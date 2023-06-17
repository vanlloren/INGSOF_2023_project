package server.Controller;


import Util.CommonGoalType;
import server.Model.*;

/**
 * Static Class used by the controllers to launch the necessary checks regarding the completion of a {@link CommonGoal CommonGoal} by a {@link Player Player}
 */
public class CheckCommonGoal {

    /**
     * Method that invokes {@link RuleCommonGoal RuleCommonGoal} to check the completion of a specific {@link CommonGoal CommonGoal}
     *
     * @param myShelfie the {@link Shelf Shelf} on which the checks should be done
     * @param commonGoalType instance of {@link CommonGoalType CommonGoalType} that specifies the type of the current {@link CommonGoal CommonGoal} whose completion needs to be checked
     *
     * @return boolean which indicates if the specified {@link CommonGoal CommonGoal} has been completed or not
     */
    public static boolean checkGoal(Shelf myShelfie, CommonGoalType commonGoalType) { //il parametro è gia passato quando chiamo calculatePoints
        return switch (commonGoalType) {
            case COMMONGOAL01 -> RuleCommonGoal.checkSixCouples(myShelfie.getStructure());
            case COMMONGOAL02 -> RuleCommonGoal.checkCorner(myShelfie.getStructure());
            case COMMONGOAL03 -> RuleCommonGoal.checkFourGroups(myShelfie.getStructure());
            case COMMONGOAL04 -> RuleCommonGoal.checkSquare(myShelfie.getStructure());
            case COMMONGOAL05 -> RuleCommonGoal.CheckColumn1(myShelfie.getStructure());
            case COMMONGOAL06 -> RuleCommonGoal.checkEight(myShelfie.getStructure());
            case COMMONGOAL07 -> RuleCommonGoal.checkDiagonal(myShelfie.getStructure());
            case COMMONGOAL08 -> RuleCommonGoal.CheckLine1(myShelfie.getStructure());
            case COMMONGOAL09 -> RuleCommonGoal.CheckColumn2(myShelfie.getStructure());
            case COMMONGOAL10 -> RuleCommonGoal.CheckLine2(myShelfie.getStructure());
            case COMMONGOAL11 -> RuleCommonGoal.checkCrux(myShelfie.getStructure());
            default ->
                    RuleCommonGoal.checkStair(myShelfie.getStructure());
        };
}
}
