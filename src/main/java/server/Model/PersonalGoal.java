package server.Model;

//SEQUENZA CORRETTA GAMEBOARD<-->LIVINGROOM:
//-creazione
//-fillLivingRoom (al suo interno avrà multiple chiamate di getNextInGameTile e putNextInGameTile)
//-updateAvailability
//----1° turno----
//-hasAdjacentTiles--->fillLivingRoom--->updateAvailability
//-getToPlayerFirstTile
//-checkAdjAvailability
//-pickedTilesNum
//-fineTurno/getToPlayerAnotherTile
//-checkAdjAvailability
//-pickedTilesNum
//-fineTurno/getToPlayerAnotherTile
//-fineTurno
//-updateAvailability
//----turno player succ----
//-hasAdjacentTiles--->fillLivingRoom--->updateAvailability
//-getToPlayerFirstTile
//-checkAdjAvailability
//-fineTurno/getToPlayerAnotherTile
//-checkAdjAvailability
//-fineTurno/getToPlayerAnotherTile
//-fineTurno
//-updateAvailability
//----turno player succ----

import Util.Colour;
import Util.PersonalGoalType;

public class PersonalGoal {

    PersonalGoalType personalGoalType;

    public int Personal_goal_1(PlayableItemTile[][] structure) {
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

    public int Personal_goal_2(PlayableItemTile[][] structure) {
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

    public int Personal_goal_3(PlayableItemTile[][] structure) {
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

    public int Personal_goal_4(PlayableItemTile[][] structure) {
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

    public int Personal_goal_5(PlayableItemTile[][] structure) {
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

    public int Personal_goal_6(PlayableItemTile[][] structure) {
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

    public int Personal_goal_7(PlayableItemTile[][] structure) {
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


    public int Personal_goal_8(PlayableItemTile[][] structure) {
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

    public int Personal_goal_9(PlayableItemTile[][] structure) {
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

    public int Personal_goal_10(PlayableItemTile[][] structure) {
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

    public int Personal_goal_11(PlayableItemTile[][] structure) {
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

    public int Personal_goal_12(PlayableItemTile[][] structure) {
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
    public int calculatePoint(PersonalGoal personalGoal, PlayableItemTile[][] structure){
        PersonalGoalType helperType;
        helperType=personalGoal.getPersonalGoalType();
        if(helperType==PersonalGoalType.PERSONALGOAL1){
            this.Personal_goal_1(structure);
        }
        if(helperType==PersonalGoalType.PERSONALGOAL2){
            this.Personal_goal_2(structure);
        }
        if(helperType==PersonalGoalType.PERSONALGOAL3){
            this.Personal_goal_3(structure);
        }
        if(helperType==PersonalGoalType.PERSONALGOAL4){
            this.Personal_goal_4(structure);
        }
        if(helperType==PersonalGoalType.PERSONALGOAL5){
            this.Personal_goal_5(structure);
        }
        if(helperType==PersonalGoalType.PERSONALGOAL6){
            this.Personal_goal_6(structure);
        }
        if(helperType==PersonalGoalType.PERSONALGOAL7){
            this.Personal_goal_7(structure);
        }
        if(helperType==PersonalGoalType.PERSONALGOAL8){
            this.Personal_goal_8(structure);
        }
        if(helperType==PersonalGoalType.PERSONALGOAL9){
            this.Personal_goal_9(structure);
        }
        if(helperType==PersonalGoalType.PERSONALGOAL10){
            this.Personal_goal_10(structure);
        }
        if(helperType==PersonalGoalType.PERSONALGOAL11){
            this.Personal_goal_11(structure);
        }
        if(helperType==PersonalGoalType.PERSONALGOAL12){
            this.Personal_goal_12(structure);
        }
    }
}










