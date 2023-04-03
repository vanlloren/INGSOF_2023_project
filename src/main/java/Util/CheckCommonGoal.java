package Util;

import server.Model.Shelf;


public class CheckCommonGoal {
    public static boolean checkGoal(Shelf myshelfy,CommonGoalType commonGoalType) { //il parametro è gia passato quando chiamo calculatepoints
    boolean checker = false;
    switch (commonGoalType) {
        case COMMONGOAL02:
            checker = RuleCommonGoal.checkCorner(myshelfy.getStructure());
            break;
        case COMMONGOAL01:
            checker = RuleCommonGoal.checkSixCouples(myshelfy.getStructure());
            break;
        case CommonGOAL03:
            checker = RuleCommonGoal.checkFourGroups(myshelfy.getStructure());
            break;
        case COMMONGOAL04:
            checker = RuleCommonGoal.checkSquare(myshelfy.getStructure());
            break;
        case COMMONGOAL05:
            checker = RuleCommonGoal.CheckColumn1(myshelfy.getStructure());
            break;
        case COMMONGOAL06:
            checker = RuleCommonGoal.checkEight(myshelfy.getStructure());
            break;
        case COMMONGOAL07:
            checker = RuleCommonGoal.checkDiagonal(myshelfy.getStructure());
            break;
        case COMMONGOAL08:
            checker = RuleCommonGoal.CheckLine1(myshelfy.getStructure());
            break;
        case COMMONGOAL09:
            checker = RuleCommonGoal.CheckColumn2(myshelfy.getStructure());
            break;
        case COMMONGOAL10:
            checker = RuleCommonGoal.CheckLine2(myshelfy.getStructure());
            break;
        case COMMONGOAL11:
            checker = RuleCommonGoal.checkCrux(myshelfy.getStructure());
            break;
        case COMMONGOAL12:
            checker = RuleCommonGoal.checkStair(myshelfy.getStructure());
            break;
    }
    return checker;
}
}
