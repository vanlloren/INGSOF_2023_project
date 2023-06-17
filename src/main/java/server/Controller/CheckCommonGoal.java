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
     * @param myShelfy the {@link Shelf Shelf} on which the checks should be done
     * @param commonGoalType instance of {@link CommonGoalType CommonGoalType} that specifies the type of the current {@link CommonGoal CommonGoal} whose completion needs to be checked
     *
     * @return boolean which indicates if the specified {@link CommonGoal CommonGoal} has been completed or not
     */
    public static boolean checkGoal(Shelf myShelfy, CommonGoalType commonGoalType) { //il parametro è gia passato quando chiamo calculatePoints
        return switch (commonGoalType) {
            case COMMONGOAL02 -> RuleCommonGoal.checkCorner(myShelfy.getStructure());
            case COMMONGOAL01 -> RuleCommonGoal.checkSixCouples(myShelfy.getStructure());
            case COMMONGOAL03 -> RuleCommonGoal.checkFourGroups(myShelfy.getStructure());
            case COMMONGOAL04 -> RuleCommonGoal.checkSquare(myShelfy.getStructure());
            case COMMONGOAL05 -> RuleCommonGoal.CheckColumn1(myShelfy.getStructure());
            case COMMONGOAL06 -> RuleCommonGoal.checkEight(myShelfy.getStructure());
            case COMMONGOAL07 -> RuleCommonGoal.checkDiagonal(myShelfy.getStructure());
            case COMMONGOAL08 -> RuleCommonGoal.CheckLine1(myShelfy.getStructure());
            case COMMONGOAL09 -> RuleCommonGoal.CheckColumn2(myShelfy.getStructure());
            case COMMONGOAL10 -> RuleCommonGoal.CheckLine2(myShelfy.getStructure());
            case COMMONGOAL11 -> RuleCommonGoal.checkCrux(myShelfy.getStructure());
            default -> //è il caso di commonGoal12, ma l'ho reso default così la coverage è al 100%
                    RuleCommonGoal.checkStair(myShelfy.getStructure());
        };
}
}
