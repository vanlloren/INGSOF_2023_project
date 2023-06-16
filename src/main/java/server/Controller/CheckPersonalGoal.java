package server.Controller;

import Util.Colour;

import server.Model.PersonalGoal;
import server.Model.PlayableItemTile;
import server.Model.Player;

/**
 * Static Class used by the controllers to launch the necessary checks regarding the completion of a {@link PersonalGoal PersonalGoal} by a {@link Player Player}
 */
public class CheckPersonalGoal {
    /**
     * Static method which checks the completion of the {@link PersonalGoal PersonalGoal1}.
     * The method checks that, for every meaningful position in the PersonalGoal1 card,
     * there is a match between the expected colour of the {@link server.Model.ItemTile ItemTile} and
     * the actual colour of the {@link server.Model.ItemTile ItemTile} in the given structure.
     *
     * @param structure the specific structure of a {@link server.Model.Shelf Shelf} on which the check must be done
     *
     * @return the number of points to add to the {@link Player Player} points as established by the scores written on the PersonalGoal1 card
     */
    public static int Personal_goal_1(PlayableItemTile[][] structure) {
        int count = 0;
        if (structure[0][0].getColour() == Colour.PINK) {
            count++;
        }
        if (structure[0][2].getColour() == Colour.BLUE) {
            count++;
        }
        if (structure[1][4].getColour() == Colour.GREEN) {
            count++;
        }
        if (structure[2][3].getColour() == Colour.WHITE) {
            count++;
        }
        if (structure[3][1].getColour() == Colour.YELLOW) {
            count++;
        }
        if (structure[5][2].getColour() == Colour.CYAN) {
            count++;
        }
       return addPoint(count);
    }

    /**
     * Static method which checks the completion of the {@link PersonalGoal PersonalGoal2}.
     * The method checks that, for every meaningful position in the PersonalGoal2 card,
     * there is a match between the expected colour of the {@link server.Model.ItemTile ItemTile} and
     * the actual colour of the {@link server.Model.ItemTile ItemTile} in the given structure.
     *
     * @param structure the specific structure of a {@link server.Model.Shelf Shelf} on which the check must be done
     *
     * @return the number of points to add to the {@link Player Player} points as established by the scores written on the PersonalGoal2 card
     */
    public static int Personal_goal_2(PlayableItemTile[][] structure) {
        int count = 0;

        if (structure[1][1].getColour() == Colour.PINK) {
            count++;
        }
        if (structure[2][0].getColour() == Colour.GREEN) {
            count++;
        }
        if (structure[2][2].getColour() == Colour.YELLOW) {
            count++;
        }
        if (structure[3][4].getColour() == Colour.WHITE) {
            count++;
        }
        if (structure[4][3].getColour() == Colour.CYAN) {
            count++;
        }
        if (structure[5][4].getColour() == Colour.BLUE) {
            count++;
        }
        return addPoint(count);
    }

    /**
     * Static method which checks the completion of the {@link PersonalGoal PersonalGoal3}.
     * The method checks that, for every meaningful position in the PersonalGoal3 card,
     * there is a match between the expected colour of the {@link server.Model.ItemTile ItemTile} and
     * the actual colour of the {@link server.Model.ItemTile ItemTile} in the given structure.
     *
     * @param structure the specific structure of a {@link server.Model.Shelf Shelf} on which the check must be done
     *
     * @return the number of points to add to the {@link Player Player} points as established by the scores written on the PersonalGoal3 card
     */
    public static int Personal_goal_3(PlayableItemTile[][] structure) {
        int count = 0;

        if (structure[1][0].getColour() == Colour.BLUE) {
            count++;
        }
        if (structure[1][3].getColour() == Colour.YELLOW) {
            count++;
        }
        if (structure[2][2].getColour() == Colour.PINK) {
            count++;
        }
        if (structure[3][1].getColour() == Colour.GREEN) {
            count++;
        }
        if (structure[3][4].getColour() == Colour.CYAN) {
            count++;
        }
        if (structure[5][0].getColour() == Colour.WHITE) {
            count++;
        }
        return addPoint(count);
    }

    /**
     * Static method which checks the completion of the {@link PersonalGoal PersonalGoal4}.
     * The method checks that, for every meaningful position in the PersonalGoal4 card,
     * there is a match between the expected colour of the {@link server.Model.ItemTile ItemTile} and
     * the actual colour of the {@link server.Model.ItemTile ItemTile} in the given structure.
     *
     * @param structure the specific structure of a {@link server.Model.Shelf Shelf} on which the check must be done
     *
     * @return the number of points to add to the {@link Player Player} points as established by the scores written on the PersonalGoal4 card
     */
    public static int Personal_goal_4(PlayableItemTile[][] structure) {
        int count = 0;

        if (structure[0][4].getColour() == Colour.YELLOW) {
            count++;
        }
        if (structure[2][0].getColour() == Colour.CYAN) {
            count++;
        }
        if (structure[2][2].getColour() == Colour.BLUE) {
            count++;
        }
        if (structure[3][3].getColour() == Colour.PINK) {
            count++;
        }
        if (structure[4][1].getColour() == Colour.WHITE) {
            count++;
        }
        if (structure[4][2].getColour() == Colour.GREEN) {
            count++;
        }
        return addPoint(count);
    }

    /**
     * Static method which checks the completion of the {@link PersonalGoal PersonalGoal5}.
     * The method checks that, for every meaningful position in the PersonalGoal5 card,
     * there is a match between the expected colour of the {@link server.Model.ItemTile ItemTile} and
     * the actual colour of the {@link server.Model.ItemTile ItemTile} in the given structure.
     *
     * @param structure the specific structure of a {@link server.Model.Shelf Shelf} on which the check must be done
     *
     * @return the number of points to add to the {@link Player Player} points as established by the scores written on the PersonalGoal5 card
     */
    public static int Personal_goal_5(PlayableItemTile[][] structure) {
        int count = 0;

        if (structure[1][1].getColour() == Colour.CYAN) {
            count++;
        }
        if (structure[3][1].getColour() == Colour.BLUE) {
            count++;
        }
        if (structure[3][2].getColour() == Colour.WHITE) {
            count++;
        }
        if (structure[4][4].getColour() == Colour.PINK) {
            count++;
        }
        if (structure[5][0].getColour() == Colour.YELLOW) {
            count++;
        }
        if (structure[5][3].getColour() == Colour.GREEN) {
            count++;
        }
        return addPoint(count);
    }

    /**
     * Static method which checks the completion of the {@link PersonalGoal PersonalGoal6}.
     * The method checks that, for every meaningful position in the PersonalGoal6 card,
     * there is a match between the expected colour of the {@link server.Model.ItemTile ItemTile} and
     * the actual colour of the {@link server.Model.ItemTile ItemTile} in the given structure.
     *
     * @param structure the specific structure of a {@link server.Model.Shelf Shelf} on which the check must be done
     *
     * @return the number of points to add to the {@link Player Player} points as established by the scores written on the PersonalGoal6 card
     */
    public static int Personal_goal_6(PlayableItemTile[][] structure) {
        int count = 0;

        if (structure[0][2].getColour() == Colour.CYAN) {
            count++;
        }
        if (structure[0][4].getColour() == Colour.GREEN) {
            count++;
        }
        if (structure[2][3].getColour() == Colour.WHITE) {
            count++;
        }
        if (structure[4][1].getColour() == Colour.YELLOW) {
            count++;
        }
        if (structure[4][3].getColour() == Colour.BLUE) {
            count++;
        }
        if (structure[5][0].getColour() == Colour.PINK) {
            count++;
        }
        return addPoint(count);
    }

    /**
     * Static method which checks the completion of the {@link PersonalGoal PersonalGoal7}.
     * The method checks that, for every meaningful position in the PersonalGoal7 card,
     * there is a match between the expected colour of the {@link server.Model.ItemTile ItemTile} and
     * the actual colour of the {@link server.Model.ItemTile ItemTile} in the given structure.
     *
     * @param structure the specific structure of a {@link server.Model.Shelf Shelf} on which the check must be done
     *
     * @return the number of points to add to the {@link Player Player} points as established by the scores written on the PersonalGoal7 card
     */
    public static int Personal_goal_7(PlayableItemTile[][] structure) {
        int count = 0;

        if (structure[0][0].getColour() == Colour.YELLOW) {
            count++;
        }
        if (structure[1][3].getColour() == Colour.BLUE) {
            count++;
        }
        if (structure[2][1].getColour() == Colour.PINK) {
            count++;
        }
        if (structure[3][0].getColour() == Colour.CYAN) {
            count++;
        }
        if (structure[4][4].getColour() == Colour.YELLOW) {
            count++;
        }
        if (structure[5][2].getColour() == Colour.WHITE) {
            count++;
        }
        return addPoint(count);
    }

    /**
     * Static method which checks the completion of the {@link PersonalGoal PersonalGoal8}.
     * The method checks that, for every meaningful position in the PersonalGoal8 card,
     * there is a match between the expected colour of the {@link server.Model.ItemTile ItemTile} and
     * the actual colour of the {@link server.Model.ItemTile ItemTile} in the given structure.
     *
     * @param structure the specific structure of a {@link server.Model.Shelf Shelf} on which the check must be done
     *
     * @return the number of points to add to the {@link Player Player} points as established by the scores written on the PersonalGoal8 card
     */
    public static int Personal_goal_8(PlayableItemTile[][] structure) {
        int count = 0;

        if (structure[0][4].getColour() == Colour.BLUE) {
            count++;
        }
        if (structure[1][1].getColour() == Colour.GREEN) {
            count++;
        }
        if (structure[2][2].getColour() == Colour.CYAN) {
            count++;
        }
        if (structure[3][0].getColour() == Colour.PINK) {
            count++;
        }
        if (structure[4][3].getColour() == Colour.WHITE) {
            count++;
        }
        if (structure[5][3].getColour() == Colour.YELLOW) {
            count++;
        }
        return addPoint(count);
    }

    /**
     * Static method which checks the completion of the {@link PersonalGoal PersonalGoal9}.
     * The method checks that, for every meaningful position in the PersonalGoal9 card,
     * there is a match between the expected colour of the {@link server.Model.ItemTile ItemTile} and
     * the actual colour of the {@link server.Model.ItemTile ItemTile} in the given structure.
     *
     * @param structure the specific structure of a {@link server.Model.Shelf Shelf} on which the check must be done
     *
     * @return the number of points to add to the {@link Player Player} points as established by the scores written on the PersonalGoal9 card
     */
    public static int Personal_goal_9(PlayableItemTile[][] structure) {
        int count = 0;

        if (structure[0][2].getColour() == Colour.YELLOW) {
            count++;
        }
        if (structure[2][2].getColour() == Colour.GREEN) {
            count++;
        }
        if (structure[3][4].getColour() == Colour.WHITE) {
            count++;
        }
        if (structure[4][1].getColour() == Colour.CYAN) {
            count++;
        }
        if (structure[4][4].getColour() == Colour.PINK) {
            count++;
        }
        if (structure[5][0].getColour() == Colour.BLUE) {
            count++;
        }
        return addPoint(count);
    }

    /**
     * Static method which checks the completion of the {@link PersonalGoal PersonalGoal10}.
     * The method checks that, for every meaningful position in the PersonalGoal10 card,
     * there is a match between the expected colour of the {@link server.Model.ItemTile ItemTile} and
     * the actual colour of the {@link server.Model.ItemTile ItemTile} in the given structure.
     *
     * @param structure the specific structure of a {@link server.Model.Shelf Shelf} on which the check must be done
     *
     * @return the number of points to add to the {@link Player Player} points as established by the scores written on the PersonalGoal10 card
     */
    public static int Personal_goal_10(PlayableItemTile[][] structure) {
        int count = 0;

        if (structure[0][4].getColour() == Colour.CYAN) {
            count++;
        }
        if (structure[1][1].getColour() == Colour.YELLOW) {
            count++;
        }
        if (structure[2][0].getColour() == Colour.WHITE) {
            count++;
        }
        if (structure[3][3].getColour() == Colour.GREEN) {
            count++;
        }
        if (structure[4][1].getColour() == Colour.BLUE) {
            count++;
        }
        if (structure[5][3].getColour() == Colour.PINK) {
            count++;
        }
        return addPoint(count);
    }

    /**
     * Static method which checks the completion of the {@link PersonalGoal PersonalGoal11}.
     * The method checks that, for every meaningful position in the PersonalGoal11 card,
     * there is a match between the expected colour of the {@link server.Model.ItemTile ItemTile} and
     * the actual colour of the {@link server.Model.ItemTile ItemTile} in the given structure.
     *
     * @param structure the specific structure of a {@link server.Model.Shelf Shelf} on which the check must be done
     *
     * @return the number of points to add to the {@link Player Player} points as established by the scores written on the PersonalGoal11 card
     */
    public static int Personal_goal_11(PlayableItemTile[][] structure) {
        int count = 0;

        if (structure[0][2].getColour() == Colour.PINK) {
            count++;
        }
        if (structure[1][1].getColour() == Colour.WHITE) {
            count++;
        }
        if (structure[2][0].getColour() == Colour.YELLOW) {
            count++;
        }
        if (structure[3][2].getColour() == Colour.BLUE) {
            count++;
        }
        if (structure[4][4].getColour() == Colour.GREEN) {
            count++;
        }
        if (structure[5][3].getColour() == Colour.CYAN) {
            count++;
        }
        return addPoint(count);
    }

    /**
     * Static method which checks the completion of the {@link PersonalGoal PersonalGoal12}.
     * The method checks if, for every meaningful position in the PersonalGoal12 card,
     * there is a match between the expected colour of the {@link server.Model.ItemTile ItemTile} and
     * the actual colour of the {@link server.Model.ItemTile ItemTile} in the given structure.
     *
     * @param structure the specific structure of a {@link server.Model.Shelf Shelf} on which the check must be done
     *
     * @return the number of points to add to the {@link Player Player} points as established by the scores written on the PersonalGoal12 card
     */
    public static int Personal_goal_12(PlayableItemTile[][] structure) {
        int count = 0;

        if (structure[0][2].getColour() == Colour.WHITE) {
            count++;
        }
        if (structure[1][1].getColour() == Colour.PINK) {
            count++;
        }
        if (structure[2][2].getColour() == Colour.BLUE) {
            count++;
        }
        if (structure[3][3].getColour() == Colour.CYAN) {
            count++;
        }
        if (structure[4][4].getColour() == Colour.YELLOW) {
            count++;
        }
        if (structure[5][0].getColour() == Colour.GREEN) {
            count++;
        }
        return addPoint(count);
    }


    /**
     * Method that invokes the specific method of {@link CheckPersonalGoal CheckPersonalGoal} depending on the {@link Util.PersonalGoalType PersonalGoalType}
     * taken into account
     *
     * @param personalGoal the {@link PersonalGoal PersonalGoal}  which completion has to be checked
     * @param structure the specific structure on which the check must be done
     *
     * @return the number of points to add to the {@link Player Player} points
     */
    public static Integer calculatePoints(PersonalGoal personalGoal, PlayableItemTile[][] structure) {
        int point = 0;

        switch (personalGoal.getPersonalGoalType()) {
            case PERSONALGOAL1 -> point = Personal_goal_1(structure);
            case PERSONALGOAL2 -> point = Personal_goal_2(structure);
            case PERSONALGOAL3 -> point = Personal_goal_3(structure);
            case PERSONALGOAL4 -> point = Personal_goal_4(structure);
            case PERSONALGOAL5 -> point = Personal_goal_5(structure);
            case PERSONALGOAL6 -> point = Personal_goal_6(structure);
            case PERSONALGOAL7 -> point = Personal_goal_7(structure);
            case PERSONALGOAL8 -> point = Personal_goal_8(structure);
            case PERSONALGOAL9 -> point = Personal_goal_9(structure);
            case PERSONALGOAL10 -> point = Personal_goal_10(structure);
            case PERSONALGOAL11 -> point = Personal_goal_11(structure);
            case PERSONALGOAL12 -> point = Personal_goal_12(structure);
        }
        return point;
    }


    private static int addPoint(int count){
        int point = 0;
        switch (count){
            case 1 -> point = 1;
            case 2 -> point = 2;
            case 3 -> point = 4;
            case 4 -> point = 6;
            case 5 -> point = 9;
            case 6 -> point = 12;
        }
        return point;
    }
}

