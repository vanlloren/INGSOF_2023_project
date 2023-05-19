package Util;


import server.Model.Player;

import java.util.ArrayList;
import java.util.Random;


public class RandPersonalGoal {
    public static void setType(Player player, ArrayList<Player> playersInGame) {
        boolean isOK;
        int n;
        PersonalGoalType newPersonalGoalType = null;
        ArrayList<PersonalGoalType> personalGoalTypeList = new ArrayList<>();
        for (Player p : playersInGame) {
            if (!p.equals(player))
                personalGoalTypeList.add(p.getPersonalGoal().getPersonalGoalType());
        }
        do {
            isOK = true;
            Random rand = new Random();
             n = rand.nextInt(12)+1;
            switch (n) {
                case 1:
                    newPersonalGoalType = PersonalGoalType.PERSONALGOAL1;
                case 2:
                    newPersonalGoalType = PersonalGoalType.PERSONALGOAL2;
                case 3:
                    newPersonalGoalType = PersonalGoalType.PERSONALGOAL3;
                case 4:
                    newPersonalGoalType = PersonalGoalType.PERSONALGOAL4;
                case 5:
                    newPersonalGoalType = PersonalGoalType.PERSONALGOAL5;
                case 6:
                    newPersonalGoalType = PersonalGoalType.PERSONALGOAL6;
                case 7:
                    newPersonalGoalType = PersonalGoalType.PERSONALGOAL7;
                case 8:
                    newPersonalGoalType = PersonalGoalType.PERSONALGOAL8;
                case 9:
                    newPersonalGoalType = PersonalGoalType.PERSONALGOAL9;
                case 10:
                    newPersonalGoalType = PersonalGoalType.PERSONALGOAL10;
                case 11:
                    newPersonalGoalType = PersonalGoalType.PERSONALGOAL11;
                case 12:
                    newPersonalGoalType = PersonalGoalType.PERSONALGOAL12;
            }
            for (PersonalGoalType p : personalGoalTypeList) {
                if (p == newPersonalGoalType) {
                    isOK = false;
                }
            }
        } while (!isOK);

        player.getPersonalGoal().setPersonalGoalType(newPersonalGoalType, player.getNickname());

    }
}
