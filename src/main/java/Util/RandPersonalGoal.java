package Util;

import server.Model.PersonalGoal;
import server.Model.Player;

import java.util.ArrayList;
import java.util.Random;


public class RandPersonalGoal {
    public static void setType(Player player,PersonalGoal personalGoal, ArrayList<Player> playersInGame) {
        int cursor = 0;
        do{ Random rand = new Random();
            int n = rand.nextInt(12);
            switch (n) {
                case 0:
                    personalGoal.setPersonalGoalType(PersonalGoalType.PERSONALGOAL1);
                case 1:
                    personalGoal.setPersonalGoalType(PersonalGoalType.PERSONALGOAL2);
                case 2:
                    personalGoal.setPersonalGoalType(PersonalGoalType.PERSONALGOAL3);
                case 3:
                    personalGoal.setPersonalGoalType(PersonalGoalType.PERSONALGOAL4);
                case 4:
                    personalGoal.setPersonalGoalType(PersonalGoalType.PERSONALGOAL5);
                case 5:
                    personalGoal.setPersonalGoalType(PersonalGoalType.PERSONALGOAL6);
                case 6:
                    personalGoal.setPersonalGoalType(PersonalGoalType.PERSONALGOAL7);
                case 7:
                    personalGoal.setPersonalGoalType(PersonalGoalType.PERSONALGOAL8);
                case 8:
                    personalGoal.setPersonalGoalType(PersonalGoalType.PERSONALGOAL9);
                case 9:
                    personalGoal.setPersonalGoalType(PersonalGoalType.PERSONALGOAL10);
                case 10:
                    personalGoal.setPersonalGoalType(PersonalGoalType.PERSONALGOAL11);
                case 11:
                    personalGoal.setPersonalGoalType(PersonalGoalType.PERSONALGOAL12);
            }
            cursor++;
        } while (cursor-1 < playersInGame.size() && !(player.equals(playersInGame.get(cursor)))
                 && player.getPersonalGoal().getPersonalGoalType() == playersInGame.get(cursor).getPersonalGoal().getPersonalGoalType());
    }
}
