package server.Controller;

import Util.Colour;
import Util.PersonalGoalType;
import server.Model.PersonalGoal;
import server.Model.PlayableItemTile;

public class CheckPersonalGoal {

    public static int Personal_goal_1(PlayableItemTile[][] structure) {
        int count = 0;
        int point = 0;
        if (structure[0][0].getColour() == Colour.PINK) {
            point++;
            count++;
        }
        if (structure[2][0].getColour() == Colour.BLUE) {
            point++;
            count++;
        }
        if (structure[4][1].getColour() == Colour.GREEN) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
        }
        if (structure[3][2].getColour() == Colour.WHITE) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        if (structure[1][3].getColour() == Colour.YELLOW) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        if (structure[2][5].getColour() == Colour.CYAN) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        return point;
    }

    public static int Personal_goal_2(PlayableItemTile[][] structure) {
        int count = 0;
        int point = 0;
        if (structure[1][1].getColour() == Colour.PINK) {
            point++;
            count++;
        }
        if (structure[0][2].getColour() == Colour.GREEN) {
            point++;
            count++;
        }
        if (structure[2][2].getColour() == Colour.YELLOW) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
        }
        if (structure[4][3].getColour() == Colour.WHITE) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        if (structure[3][4].getColour() == Colour.CYAN) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        if (structure[4][5].getColour() == Colour.BLUE) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        return point;
    }

    public static int Personal_goal_3(PlayableItemTile[][] structure) {
        int count = 0;
        int point = 0;
        if (structure[0][1].getColour() == Colour.BLUE) {
            point++;
            count++;
        }
        if (structure[3][1].getColour() == Colour.YELLOW) {
            point++;
            count++;
        }
        if (structure[2][2].getColour() == Colour.PINK) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
        }
        if (structure[1][3].getColour() == Colour.GREEN) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        if (structure[4][3].getColour() == Colour.CYAN) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        if (structure[0][5].getColour() == Colour.WHITE) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        return point;
    }

    public static int Personal_goal_4(PlayableItemTile[][] structure) {
        int count = 0;
        int point = 0;
        if (structure[4][0].getColour() == Colour.YELLOW) {
            point++;
            count++;
        }
        if (structure[0][2].getColour() == Colour.CYAN) {
            point++;
            count++;
        }
        if (structure[2][2].getColour() == Colour.BLUE) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
        }
        if (structure[3][3].getColour() == Colour.PINK) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        if (structure[1][4].getColour() == Colour.WHITE) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        if (structure[2][4].getColour() == Colour.GREEN) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        return point;
    }

    public static int Personal_goal_5(PlayableItemTile[][] structure) {
        int count = 0;
        int point = 0;
        if (structure[1][1].getColour() == Colour.CYAN) {
            point++;
            count++;
        }
        if (structure[1][3].getColour() == Colour.BLUE) {
            point++;
            count++;
        }
        if (structure[2][3].getColour() == Colour.WHITE) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
        }
        if (structure[4][4].getColour() == Colour.PINK) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        if (structure[0][5].getColour() == Colour.YELLOW) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        if (structure[3][5].getColour() == Colour.GREEN) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        return point;
    }

    public static int Personal_goal_6(PlayableItemTile[][] structure) {
        int count = 0;
        int point = 0;
        if (structure[2][0].getColour() == Colour.CYAN) {
            point++;
            count++;
        }
        if (structure[4][0].getColour() == Colour.GREEN) {
            point++;
            count++;
        }
        if (structure[3][2].getColour() == Colour.WHITE) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
        }
        if (structure[1][4].getColour() == Colour.YELLOW) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        if (structure[3][4].getColour() == Colour.BLUE) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        if (structure[0][5].getColour() == Colour.PINK) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        return point;
    }

    public static int Personal_goal_7(PlayableItemTile[][] structure) {
        int count = 0;
        int point = 0;
        if (structure[0][0].getColour() == Colour.GREEN) {
            point++;
            count++;
        }
        if (structure[3][1].getColour() == Colour.BLUE) {
            point++;
            count++;
        }
        if (structure[0][2].getColour() == Colour.PINK) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
        }
        if (structure[0][3].getColour() == Colour.CYAN) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        if (structure[4][4].getColour() == Colour.YELLOW) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        if (structure[2][5].getColour() == Colour.WHITE) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        return point;
    }


    public static int Personal_goal_8(PlayableItemTile[][] structure) {
        int count = 0;
        int point = 0;
        if (structure[4][0].getColour() == Colour.BLUE) {
            point++;
            count++;
        }
        if (structure[1][1].getColour() == Colour.GREEN) {
            point++;
            count++;
        }
        if (structure[2][2].getColour() == Colour.CYAN) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
        }
        if (structure[0][3].getColour() == Colour.PINK) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        if (structure[3][4].getColour() == Colour.WHITE) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        if (structure[3][5].getColour() == Colour.YELLOW) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        return point;
    }

    public static int Personal_goal_9(PlayableItemTile[][] structure) {
        int count = 0;
        int point = 0;
        if (structure[2][0].getColour() == Colour.YELLOW) {
            point++;
            count++;
        }
        if (structure[2][2].getColour() == Colour.GREEN) {
            point++;
            count++;
        }
        if (structure[4][3].getColour() == Colour.WHITE) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
        }
        if (structure[1][4].getColour() == Colour.CYAN) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        if (structure[4][4].getColour() == Colour.PINK) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        if (structure[0][5].getColour() == Colour.BLUE) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        return point;
    }

    public static int Personal_goal_10(PlayableItemTile[][] structure) {
        int count = 0;
        int point = 0;
        if (structure[4][0].getColour() == Colour.CYAN) {
            point++;
            count++;
        }
        if (structure[1][1].getColour() == Colour.YELLOW) {
            point++;
            count++;
        }
        if (structure[0][2].getColour() == Colour.WHITE) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
        }
        if (structure[3][3].getColour() == Colour.GREEN) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        if (structure[1][4].getColour() == Colour.BLUE) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        if (structure[3][5].getColour() == Colour.PINK) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        return point;
    }

    public static int Personal_goal_11(PlayableItemTile[][] structure) {
        int count = 0;
        int point = 0;
        if (structure[2][0].getColour() == Colour.PINK) {
            point++;
            count++;
        }
        if (structure[1][1].getColour() == Colour.WHITE) {
            point++;
            count++;
        }
        if (structure[0][2].getColour() == Colour.YELLOW) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
        }
        if (structure[2][3].getColour() == Colour.BLUE) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        if (structure[4][4].getColour() == Colour.GREEN) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        if (structure[3][5].getColour() == Colour.CYAN) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        return point;
    }

    public static int Personal_goal_12(PlayableItemTile[][] structure) {
        int count = 0;
        int point = 0;
        if (structure[2][0].getColour() == Colour.WHITE) {
            point++;
            count++;
        }
        if (structure[1][1].getColour() == Colour.PINK) {
            point++;
            count++;
        }
        if (structure[2][2].getColour() == Colour.BLUE) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
        }
        if (structure[3][3].getColour() == Colour.CYAN) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        if (structure[4][4].getColour() == Colour.YELLOW) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        if (structure[0][5].getColour() == Colour.GREEN) {
            if (count > 2 && count <= 4) {
                point += 2;
                count++;
            }
            if (count < 2) {
                point++;
                count++;
            }
            if (count > 3 && count <= 5) {
                point += 3;
                count++;
            }
        }
        return point;
    }

    public static int calculatePoints(PersonalGoal personalGoal, PlayableItemTile[][] structure) {
        int point = 0;
        switch (personalGoal.getPersonalGoalType()) {
            case PERSONALGOAL1:  point = Personal_goal_1(structure);
            case PERSONALGOAL2:  point = Personal_goal_2(structure);
            case PERSONALGOAL3:  point = Personal_goal_3(structure);
            case PERSONALGOAL4:  point = Personal_goal_4(structure);
            case PERSONALGOAL5:  point = Personal_goal_5(structure);
            case PERSONALGOAL6:  point = Personal_goal_6(structure);
            case PERSONALGOAL7:  point = Personal_goal_7(structure);
            case PERSONALGOAL8:  point = Personal_goal_8(structure);
            case PERSONALGOAL9:  point = Personal_goal_9(structure);
            case PERSONALGOAL10: point = Personal_goal_10(structure);
            case PERSONALGOAL11: point = Personal_goal_11(structure);
            case PERSONALGOAL12: point = Personal_goal_12(structure);
        }
        return point;
    }
}

