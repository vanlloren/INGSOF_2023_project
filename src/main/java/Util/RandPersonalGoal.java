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
                    personalGoal.setPersonalGoalType(PersonalGoalType.PERSONALGOAL1, player.getNickname());
                case 1:
                    personalGoal.setPersonalGoalType(PersonalGoalType.PERSONALGOAL2, player.getNickname());
                case 2:
                    personalGoal.setPersonalGoalType(PersonalGoalType.PERSONALGOAL3, player.getNickname());
                case 3:
                    personalGoal.setPersonalGoalType(PersonalGoalType.PERSONALGOAL4, player.getNickname());
                case 4:
                    personalGoal.setPersonalGoalType(PersonalGoalType.PERSONALGOAL5, player.getNickname());
                case 5:
                    personalGoal.setPersonalGoalType(PersonalGoalType.PERSONALGOAL6, player.getNickname());
                case 6:
                    personalGoal.setPersonalGoalType(PersonalGoalType.PERSONALGOAL7, player.getNickname());
                case 7:
                    personalGoal.setPersonalGoalType(PersonalGoalType.PERSONALGOAL8, player.getNickname());
                case 8:
                    personalGoal.setPersonalGoalType(PersonalGoalType.PERSONALGOAL9, player.getNickname());
                case 9:
                    personalGoal.setPersonalGoalType(PersonalGoalType.PERSONALGOAL10, player.getNickname());
                case 10:
                    personalGoal.setPersonalGoalType(PersonalGoalType.PERSONALGOAL11, player.getNickname());
                case 11:
                    personalGoal.setPersonalGoalType(PersonalGoalType.PERSONALGOAL12, player.getNickname());
            }
            cursor++;
        } while (cursor-1 < playersInGame.size() && !(player.equals(playersInGame.get(cursor)))
                 && player.getPersonalGoal().getPersonalGoalType() == playersInGame.get(cursor).getPersonalGoal().getPersonalGoalType());
    }
}
