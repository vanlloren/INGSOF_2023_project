package Util;
import server.Model.CommonGoal;
import java.util.Random;

public class RandCommonGoal {


    public static void setType(CommonGoal commonGoal1,CommonGoal commonGoal2){
        Random rand = new Random();
        int n = rand.nextInt(12);
        int m;
        switch(n) {
            case 0: {
                commonGoal1.setCommonGoalType(CommonGoalType.COMMONGOAL01);
                m = rand.nextInt(12);
                while (m == 0)
                    m = rand.nextInt(12);
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
                m = rand.nextInt(12);
                while (m == 1)
                    m = rand.nextInt(12);
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
                m = rand.nextInt(12);
                while (m == 2)
                    m = rand.nextInt(12);
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
                m = rand.nextInt(12);
                while (m == 3)
                    m = rand.nextInt(12);
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
                m = rand.nextInt(12);
                while (m == 4)
                    m = rand.nextInt(12);
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
                m = rand.nextInt(12);
                while (m == 5)
                    m = rand.nextInt(12);
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
                m = rand.nextInt(12);
                while (m == 6)
                    m = rand.nextInt(12);
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
                m = rand.nextInt(12);
                while (m == 7)
                    m = rand.nextInt(12);
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
                m = rand.nextInt(12);
                while (m == 8)
                    m = rand.nextInt(12);
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
                m = rand.nextInt(12);
                while (m == 9)
                    m = rand.nextInt(12);
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
                m = rand.nextInt(12);
                while (m == 10)
                    m = rand.nextInt(12);
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
                m = rand.nextInt(12);
                while (m == 11)
                    m = rand.nextInt(12);
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


