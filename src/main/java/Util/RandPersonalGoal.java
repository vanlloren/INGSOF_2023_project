package Util;

import server.Model.PersonalGoal;
import server.Model.Player;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class which sets the {@link PersonalGoalType PersonalGoalType} of the {@link PersonalGoal PersonalGoal} of the {@link Player Player}
 */
public class RandPersonalGoal {

    /**
     * Static method to assign randomly the {@link PersonalGoalType PersonalGoalType} of the {@link PersonalGoal PersonalGoal} of the {@link Player Player}
     *
     * @param player the {@link Player Player} who possess the {@link PersonalGoal PersonalGoal}
     * @param playersInGame {@link ArrayList ArrayList} that contains the {@link Player Players} in the match
     */
    public static void setType(Player player, ArrayList<Player> playersInGame) {
        boolean isOK;

        PersonalGoalType newPersonalGoalType = null;
        ArrayList<PersonalGoalType> personalGoalTypeList = new ArrayList<>();
        for (Player p : playersInGame) {
            if (!p.equals(player))
                personalGoalTypeList.add(p.getPersonalGoal().getPersonalGoalType());
        }
        do {

            isOK = true;
            Random rand = new Random();
            int n = rand.nextInt(12)+1;
            switch (n) {
                case 1:
                    newPersonalGoalType = PersonalGoalType.PERSONALGOAL1;
                    break;
                case 2:
                    newPersonalGoalType = PersonalGoalType.PERSONALGOAL2;
                    break;
                case 3:
                    newPersonalGoalType = PersonalGoalType.PERSONALGOAL3;
                    break;
                case 4:
                    newPersonalGoalType = PersonalGoalType.PERSONALGOAL4;
                    break;
                case 5:
                    newPersonalGoalType = PersonalGoalType.PERSONALGOAL5;
                    break;
                case 6:
                    newPersonalGoalType = PersonalGoalType.PERSONALGOAL6;
                    break;
                case 7:
                    newPersonalGoalType = PersonalGoalType.PERSONALGOAL7;
                    break;
                case 8:
                    newPersonalGoalType = PersonalGoalType.PERSONALGOAL8;
                    break;
                case 9:
                    newPersonalGoalType = PersonalGoalType.PERSONALGOAL9;
                    break;
                case 10:
                    newPersonalGoalType = PersonalGoalType.PERSONALGOAL10;
                    break;
                case 11:
                    newPersonalGoalType = PersonalGoalType.PERSONALGOAL11;
                    break;
                case 12:
                    newPersonalGoalType = PersonalGoalType.PERSONALGOAL12;
            }
            for (PersonalGoalType p : personalGoalTypeList) {
                if (p == newPersonalGoalType) {
                    isOK = false;
                    break;
                }
            }
        } while (!isOK);

        player.getPersonalGoal().setPersonalGoalType(newPersonalGoalType, player.getNickname());

    }
}
