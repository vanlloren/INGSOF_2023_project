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
    public static boolean checkGoal(Shelf myShelfy, CommonGoalType commonGoalType) { //il parametro è gia passato quando chiamo calculatepoints
    boolean checker;
    switch (commonGoalType) {
        case COMMONGOAL02:
            checker = RuleCommonGoal.checkCorner(myShelfy.getStructure());
            break;
        case COMMONGOAL01:
            checker = RuleCommonGoal.checkSixCouples(myShelfy.getStructure());
            break;
        case COMMONGOAL03:
            checker = RuleCommonGoal.checkFourGroups(myShelfy.getStructure());
            break;
        case COMMONGOAL04:
            checker = RuleCommonGoal.checkSquare(myShelfy.getStructure());
            break;
        case COMMONGOAL05:
            checker = RuleCommonGoal.CheckColumn1(myShelfy.getStructure());
            break;
        case COMMONGOAL06:
            checker = RuleCommonGoal.checkEight(myShelfy.getStructure());
            break;
        case COMMONGOAL07:
            checker = RuleCommonGoal.checkDiagonal(myShelfy.getStructure());
            break;
        case COMMONGOAL08:
            checker = RuleCommonGoal.CheckLine1(myShelfy.getStructure());
            break;
        case COMMONGOAL09:
            checker = RuleCommonGoal.CheckColumn2(myShelfy.getStructure());
            break;
        case COMMONGOAL10:
            checker = RuleCommonGoal.CheckLine2(myShelfy.getStructure());
            break;
        case COMMONGOAL11:
            checker = RuleCommonGoal.checkCrux(myShelfy.getStructure());
            break;
        default : //è il caso di commonGoal12, ma l'ho reso default così la coverage è al 100%
            checker = RuleCommonGoal.checkStair(myShelfy.getStructure());
            break;
    }
    return checker;
}
}
