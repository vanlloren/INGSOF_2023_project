package Util;
import server.Model.CommonGoal;
import java.util.Random;

public class RandCommonGoal {


    public static void setType(CommonGoal commonGoal1,CommonGoal commonGoal2, int i, int j){
        //i e j sono sempre diversi perché non vengono mai realmente inseriti, sono solo utili ai fini dei test

        int n;
        int m;

        if(i == 0 && j == 0) {
            Random rand = new Random();
            n = rand.nextInt(12);
            m = rand.nextInt(12);
            while (m == n)
                m = rand.nextInt(12);
        }else{
          n = i;
          m = j;
        }

        switch(n) {
            case 0: {
                commonGoal1.setCommonGoalType(CommonGoalType.COMMONGOAL01);
                switch (m) {
                    case 1:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL02);
                        break;
                    case 2:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL03);
                        break;
                    case 3:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL04);
                        break;
                    case 4:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL05);
                        break;
                    case 5:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL06);
                        break;
                    case 6:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL07);
                        break;
                    case 7:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL08);
                        break;
                    case 8:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL09);
                        break;
                    case 9:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL10);
                        break;
                    case 10:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL11);
                        break;
                    case 11:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL12);
                }
                break;
            }
            case 1: {
                commonGoal1.setCommonGoalType(CommonGoalType.COMMONGOAL02);
                switch (m) {
                    case 0:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL01);
                        break;
                    case 2:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL03);
                        break;
                    case 3:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL04);
                        break;
                    case 4:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL05);
                        break;
                    case 5:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL06);
                        break;
                    case 6:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL07);
                        break;
                    case 7:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL08);
                        break;
                    case 8:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL09);
                        break;
                    case 9:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL10);
                        break;
                    case 10:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL11);
                        break;
                    case 11:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL12);
                        break;
                }
                break;
            }
            case 2: {
                commonGoal1.setCommonGoalType(CommonGoalType.COMMONGOAL03);
                switch (m) {
                    case 0:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL01);
                        break;
                    case 1:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL02);
                        break;
                    case 3:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL04);
                        break;
                    case 4:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL05);
                        break;
                    case 5:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL06);
                        break;
                    case 6:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL07);
                        break;
                    case 7:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL08);
                        break;
                    case 8:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL09);
                        break;
                    case 9:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL10);
                        break;
                    case 10:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL11);
                        break;
                    case 11:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL12);
                }
                break;
            }
            case 3: {
                commonGoal1.setCommonGoalType(CommonGoalType.COMMONGOAL04);
                switch (m) {
                    case 0:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL01);
                        break;
                    case 1:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL02);
                        break;
                    case 2:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL03);
                        break;
                    case 4:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL05);
                        break;
                    case 5:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL06);
                        break;
                    case 6:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL07);
                        break;
                    case 7:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL08);
                        break;
                    case 8:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL09);
                        break;
                    case 9:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL10);
                        break;
                    case 10:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL11);
                        break;
                    case 11:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL12);
                }
                break;
            }
            case 4: {
                commonGoal1.setCommonGoalType(CommonGoalType.COMMONGOAL05);
                switch (m) {
                    case 0:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL01);
                        break;
                    case 1:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL02);
                        break;
                    case 2:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL03);
                        break;
                    case 3:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL04);
                        break;
                    case 5:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL06);
                        break;
                    case 6:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL07);
                        break;
                    case 7:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL08);
                        break;
                    case 8:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL09);
                        break;
                    case 9:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL10);
                        break;
                    case 10:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL11);
                        break;
                    case 11:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL12);
                }
                break;
            }
            case 5: {
                commonGoal1.setCommonGoalType(CommonGoalType.COMMONGOAL06);
                switch (m) {
                    case 0:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL01);
                        break;
                    case 1:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL02);
                        break;
                    case 2:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL03);
                        break;
                    case 3:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL04);
                        break;
                    case 4:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL05);
                        break;
                    case 6:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL07);
                        break;
                    case 7:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL08);
                        break;
                    case 8:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL09);
                        break;
                    case 9:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL10);
                        break;
                    case 10:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL11);
                        break;
                    case 11:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL12);
                }
                break;
            }
            case 6: {
                commonGoal1.setCommonGoalType(CommonGoalType.COMMONGOAL07);
                switch (m) {
                    case 0:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL01);
                        break;
                    case 1:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL02);
                        break;
                    case 2:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL03);
                        break;
                    case 3:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL04);
                        break;
                    case 4:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL05);
                        break;
                    case 5:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL06);
                        break;
                    case 7:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL08);
                        break;
                    case 8:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL09);
                        break;
                    case 9:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL10);
                        break;
                    case 10:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL11);
                        break;
                    case 11:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL12);
                }
                break;
            }
            case 7: {
                commonGoal1.setCommonGoalType(CommonGoalType.COMMONGOAL08);
                switch (m) {
                    case 0:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL01);
                        break;
                    case 1:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL02);
                        break;
                    case 2:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL03);
                        break;
                    case 3:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL04);
                        break;
                    case 4:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL05);
                        break;
                    case 5:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL06);
                        break;
                    case 6:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL07);
                        break;
                    case 8:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL09);
                        break;
                    case 9:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL10);
                        break;
                    case 10:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL11);
                        break;
                    case 11:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL12);
                }
                break;
            }
            case 8: {
                commonGoal1.setCommonGoalType(CommonGoalType.COMMONGOAL09);
                switch (m) {
                    case 0:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL01);
                        break;
                    case 1:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL02);
                        break;
                    case 2:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL03);
                        break;
                    case 3:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL04);
                        break;
                    case 4:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL05);
                        break;
                    case 5:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL06);
                        break;
                    case 6:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL07);
                        break;
                    case 7:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL08);
                        break;
                    case 9:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL10);
                        break;
                    case 10:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL11);
                        break;
                    case 11:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL12);
                }
                break;
            }
            case 9: {
                commonGoal1.setCommonGoalType(CommonGoalType.COMMONGOAL10);
                switch (m) {
                    case 0:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL01);
                        break;
                    case 1:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL02);
                        break;
                    case 2:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL03);
                        break;
                    case 3:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL04);
                        break;
                    case 4:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL05);
                        break;
                    case 5:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL06);
                        break;
                    case 6:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL07);
                        break;
                    case 7:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL08);
                        break;
                    case 8:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL09);
                        break;
                    case 10:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL11);
                        break;
                    case 11:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL12);

                }
                break;
            }
            case 10: {
                commonGoal1.setCommonGoalType(CommonGoalType.COMMONGOAL11);
                switch (m) {
                    case 0:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL01);
                        break;
                    case 1:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL02);
                        break;
                    case 2:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL03);
                        break;
                    case 3:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL04);
                        break;
                    case 4:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL05);
                        break;
                    case 5:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL06);
                        break;
                    case 6:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL07);
                        break;
                    case 7:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL08);
                        break;
                    case 8:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL09);
                        break;
                    case 9:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL10);
                        break;
                    case 11:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL12);

                }
                break;
            }
            case 11: {
                commonGoal1.setCommonGoalType(CommonGoalType.COMMONGOAL12);
                switch (m) {
                    case 0:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL01);
                        break;
                    case 1:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL02);
                        break;
                    case 2:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL03);
                        break;
                    case 3:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL04);
                        break;
                    case 4:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL05);
                        break;
                    case 5:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL06);
                        break;
                    case 6:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL07);
                        break;
                    case 7:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL08);
                        break;
                    case 8:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL09);
                        break;
                    case 9:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL10);
                        break;
                    case 10:
                        commonGoal2.setCommonGoalType(CommonGoalType.COMMONGOAL11);

                }
                break;
            }
        }
    }
}


