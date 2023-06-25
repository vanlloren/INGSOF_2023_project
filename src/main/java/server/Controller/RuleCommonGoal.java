package server.Controller;

import Util.Colour;
import server.Model.PlayableItemTile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static server.Controller.GameController.findAdjGroups;

/**
 * Static Class that checks the completion of a {@link server.Model.CommonGoal CommonGoal} by a {@link server.Model.Player Player}
 */
public class RuleCommonGoal {

    /**
     * Method that check the completion of {@link server.Model.CommonGoal CommonGoal2}
     *
     * @param structure the structure on which the check will be made
     * @return {@code true} if the {@link server.Model.CommonGoal CommonGoal} is completed, {@code false} otherwise
     */
    public static boolean checkCorner(PlayableItemTile[][] structure) {
        return structure[0][0].getColour() != Colour.VOID && structure[0][0].getColour() == structure[0][4].getColour()
                && structure[0][0].getColour() == structure[5][0].getColour() && structure[0][0].getColour() == structure[5][4].getColour();
    }

    /**
     * Method that check the completion of {@link server.Model.CommonGoal CommonGoal1}
     *
     * @param structure the structure on which the check will be made
     * @return {@code true} if the {@link server.Model.CommonGoal CommonGoal} is completed, {@code false} otherwise
     */
    public static boolean checkSixCouples(PlayableItemTile[][] structure) {
        Colour[][] matrix = new Colour[6][5];
        int count = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                if (structure[i][j].getColour() != Colour.VOID) {
                    matrix[i][j] = structure[i][j].getColour();
                }
                else
                    matrix[i][j] = Colour.VOID;
            }
        }

        for (int i = 5; i >= 0; i--) {
            for (int j = 0; j < 4; j++) {
                if (matrix[i][j] != Colour.VOID && matrix[i][j + 1] != Colour.VOID &&
                        matrix[i][j + 1] == matrix[i][j]) {
                    count++;
                    matrix[i][j] = Colour.VOID;
                    matrix[i][j + 1] = Colour.VOID;
                }
                if (i > 0) {
                    if (matrix[i][j] != Colour.VOID && matrix[i - 1][j] != Colour.VOID &&
                            matrix[i][j] == matrix[i - 1][j]) {
                        count++;
                        matrix[i][j] = Colour.VOID;
                        matrix[i - 1][j] = Colour.VOID;
                    }
                    if (j == 3 && matrix[i][j + 1] != Colour.VOID && matrix[i - 1][j + 1] != Colour.VOID &&
                            matrix[i][j + 1] == matrix[i - 1][j + 1]) {
                        count++;
                        matrix[i][j + 1] = Colour.VOID;
                        matrix[i - 1][j + 1] = Colour.VOID;
                    }
                }
            }
        }
        return count >= 6;
    }

    /**
     * Method that check the completion of {@link server.Model.CommonGoal CommonGoal3}
     *
     * @param structure the structure on which the check will be made
     * @return {@code true} if the {@link server.Model.CommonGoal CommonGoal} is completed, {@code false} otherwise
     */
        public static boolean checkFourGroups(PlayableItemTile[][] structure) {
            int count = 0;
            HashMap<Colour, ArrayList<Integer>> groups = findAdjGroups(structure);
            Set<Colour> keys = groups.keySet();
            for (Colour colour : keys) {
                ArrayList<Integer> counter = groups.get(colour);
                for (Integer dim : counter) {
                  if(dim>=4&&dim < 8) count++;
                  if(dim>=8&&dim<12 ) count+=2;
                  if(dim>=12&&dim < 16) count+=3;
                  if(dim>=16&&dim < 20) count+=4;
                  if(dim>=20) count+=5;
                }
            }
            return count >= 4;
        }

    /**
     * Method that check the completion of {@link server.Model.CommonGoal CommonGoal4}
     *
     * @param structure the structure on which the check will be made
     * @return {@code true} if the {@link server.Model.CommonGoal CommonGoal} is completed, {@code false} otherwise
     */
        public static boolean checkSquare(PlayableItemTile[][] structure) {
            for (int i = 5; i > 0; i--) {
                for (int j = 0; j < 4; j++) {
                    if (structure[i][j].getColour() != Colour.VOID&&structure[i][j].getColour()==structure[i][j+1].getColour()&&
                        structure[i][j].getColour()==structure[i-1][j].getColour()&&structure[i][j].getColour()==structure[i-1][j+1].getColour()) {
                        Colour value = structure[i][j].getColour();
                        if(j<2) {
                            for (int l = j + 2; j < 4; j++) {
                                if (structure[i][l].getColour() == value && structure[i - 1][l].getColour() == value &&
                                        structure[i][l + 1].getColour() == value && structure[i - 1][l + 1].getColour() == value) {
                                    return true;
                                }
                            }
                        }
                            for (int k = i - 2; k > 0; k--) {
                                for (int x = 0; x < 4; x++) {
                                    if (structure[k][x].getColour() == value && structure[k - 1][x].getColour() == value &&
                                            structure[k][x + 1].getColour() == value && structure[k - 1][x + 1].getColour() == value) {
                                        return true;
                                    }
                                }
                            }

                    }
                }
            }
            return false;

        }




    /**
     * Method that check the completion of {@link server.Model.CommonGoal CommonGoal7}
     *
     * @param structure the structure on which the check will be made
     * @return {@code true} if the {@link server.Model.CommonGoal CommonGoal} is completed, {@code false} otherwise
     */
        public static boolean checkDiagonal(PlayableItemTile[][] structure) {

          return((structure[0][0].getColour()!=Colour.VOID&&structure[0][0].getColour()==structure[1][1].getColour()
          &&structure[0][0].getColour()==structure[2][2].getColour()&&structure[0][0].getColour()==structure[3][3].getColour()
          &&structure[0][0].getColour()==structure[4][4].getColour())||(structure[1][0].getColour()!=Colour.VOID&&structure[1][0].getColour()==structure[2][1].getColour()
                  &&structure[1][0].getColour()==structure[3][2].getColour()&&structure[1][0].getColour()==structure[4][3].getColour()
                  &&structure[1][0].getColour()==structure[5][4].getColour()));

        }

    /**
     * Method that check the completion of {@link server.Model.CommonGoal CommonGoal11}
     *
     * @param structure the structure on which the check will be made
     * @return {@code true} if the {@link server.Model.CommonGoal CommonGoal} is completed, {@code false} otherwise
     */
        public static boolean checkCrux(PlayableItemTile[][] structure) {
            for (int i = 1; i <= 4; i++) {
                for (int j = 1; j <= 3; j++) {
                    if (structure[i][j].getColour() != Colour.VOID && structure[i - 1][j - 1].getColour() != Colour.VOID &&
                            structure[i + 1][j + 1].getColour() != Colour.VOID && structure[i + 1][j - 1].getColour() != Colour.VOID && structure[i - 1][j + 1].getColour() != Colour.VOID &&
                            structure[i][j].getColour().equals(structure[i - 1][j - 1].getColour()) &&
                            structure[i][j].getColour().equals(structure[i + 1][j + 1].getColour()) &&
                            structure[i][j].getColour().equals(structure[i + 1][j - 1].getColour()) &&
                            structure[i][j].getColour().equals(structure[i - 1][j + 1].getColour())
                    )
                        return true;
                }
            }
            return false;
        }

    /**
     * Method that check the completion of {@link server.Model.CommonGoal CommonGoal12}
     *
     * @param structure the structure on which the check will be made
     * @return {@code true} if the {@link server.Model.CommonGoal CommonGoal} is completed, {@code false} otherwise
     */
        public static boolean checkStair(PlayableItemTile[][] structure) {

            if (structure[0][0].getColour() == Colour.VOID && structure[1][0].getColour() != Colour.VOID &&
                    structure[1][1].getColour() == Colour.VOID && structure[2][1].getColour() != Colour.VOID &&
                    structure[2][2].getColour() == Colour.VOID && structure[3][2].getColour() != Colour.VOID &&
                    structure[3][3].getColour() == Colour.VOID && structure[4][3].getColour() != Colour.VOID &&
                    structure[4][4].getColour() == Colour.VOID && structure[5][4].getColour() != Colour.VOID)
                return true;
            else if (structure[0][0].getColour() != Colour.VOID &&
                    structure[0][1].getColour() == Colour.VOID && structure[1][1].getColour() != Colour.VOID &&
                    structure[1][2].getColour() == Colour.VOID && structure[2][2].getColour() != Colour.VOID &&
                    structure[2][3].getColour() == Colour.VOID && structure[3][3].getColour() != Colour.VOID &&
                    structure[3][4].getColour() == Colour.VOID && structure[4][4].getColour() != Colour.VOID)
                return true;
            else if (structure[0][4].getColour() == Colour.VOID && structure[1][4].getColour() != Colour.VOID &&
                    structure[1][3].getColour() == Colour.VOID && structure[2][3].getColour() != Colour.VOID &&
                    structure[2][2].getColour() == Colour.VOID && structure[3][2].getColour() != Colour.VOID &&
                    structure[3][1].getColour() == Colour.VOID && structure[4][1].getColour() != Colour.VOID &&
                    structure[4][0].getColour() == Colour.VOID && structure[5][0].getColour() != Colour.VOID)
                return true;
            else if (structure[0][4].getColour() != Colour.VOID &&
                    structure[0][3].getColour() == Colour.VOID && structure[1][3].getColour() != Colour.VOID &&
                    structure[1][2].getColour() == Colour.VOID && structure[2][2].getColour() != Colour.VOID &&
                    structure[2][1].getColour() == Colour.VOID && structure[3][1].getColour() != Colour.VOID &&
                    structure[3][0].getColour() == Colour.VOID && structure[4][0].getColour() != Colour.VOID)
                return true;
            return false;
        }


    /**
     * Method that check the completion of {@link server.Model.CommonGoal CommonGoal5}
     *
     * @param structure the structure on which the check will be made
     * @return {@code true} if the {@link server.Model.CommonGoal CommonGoal} is completed, {@code false} otherwise
     */
        public static boolean CheckColumn1(PlayableItemTile[][] structure) {
            int columnCount = 0;
            boolean checker = false;

            for (int j = 0; j < 5; j++) {
                if (structure[0][j].getColour() !=Colour.VOID ) {
                    HashSet<Colour> distinctColour;
                    distinctColour = new HashSet<>();
                    ArrayList<Colour> colourArrayList = new ArrayList<>();
                    for (int i = 0; i < 6; i++) {
                        if(structure[i][j].getColour()!=Colour.VOID){
                            colourArrayList.add(structure[i][j].getColour());
                        }
                    }
                    if(colourArrayList.size()==6){
                        distinctColour.addAll(colourArrayList);
                        if(distinctColour.size()<=3)
                            columnCount++;
                    }
                }
            }
            if (columnCount >= 3)
                checker = true;
            return checker;

        }

    /**
     * Method that check the completion of {@link server.Model.CommonGoal CommonGoal9}
     *
     * @param structure the structure on which the check will be made
     * @return {@code true} if the {@link server.Model.CommonGoal CommonGoal} is completed, {@code false} otherwise
     */
        public static boolean CheckColumn2(PlayableItemTile[][] structure) {
            int columnCount = 0;
            boolean checker = false;

            for (int j = 0; j < 5; j++) {
                if (structure[0][j].getColour() != Colour.VOID) {
                    HashSet<Colour> list = new HashSet<>();
                    for (int i = 0; i < 6; i++) {
                        if(structure[i][j].getColour()!=Colour.VOID) {
                            list.add(structure[i][j].getColour());
                        }
                    }
                    if (list.size() == 6)
                        columnCount++;
                }
            }
            if (columnCount >= 2)
                checker = true;
            return checker;

        }

    /**
     * Method that check the completion of {@link server.Model.CommonGoal CommonGoal8}
     *
     * @param structure the structure on which the check will be made
     * @return {@code true} if the {@link server.Model.CommonGoal CommonGoal} is completed, {@code false} otherwise
     */
        public static boolean CheckLine1(PlayableItemTile[][] structure) {
            int count = 0;
            for(int i=0;i<6;i++){
                HashSet<Colour> distinctColour;
                distinctColour = new HashSet<>();
                ArrayList<Colour> colourArrayList = new ArrayList<>();
                for(int j=0;j<5;j++){
                    if(structure[i][j].getColour()!=Colour.VOID){
                        colourArrayList.add(structure[i][j].getColour());
                    }
                }
                if(colourArrayList.size()==5){
                    distinctColour.addAll(colourArrayList);
                    if(distinctColour.size()<=3)
                        count++;
                }
            }
            return count >= 4;
        }


    /**
     * Method that check the completion of {@link server.Model.CommonGoal CommonGoal10}
     *
     * @param structure the structure on which the check will be made
     * @return {@code true} if the {@link server.Model.CommonGoal CommonGoal} is completed, {@code false} otherwise
     */
        public static boolean CheckLine2(PlayableItemTile[][] structure) {
            int count = 0;
            for(int i=0;i<6;i++){
                ArrayList<Colour> colourArrayList = new ArrayList<>();
                for(int j=0;j<5;j++){
                    if(structure[i][j].getColour()!=Colour.VOID){
                        colourArrayList.add(structure[i][j].getColour());
                    }
                }
                if(colourArrayList.size()==5){
                    HashSet<Colour> distinctColour = new HashSet<>(colourArrayList);
                    if(distinctColour.size()==5)
                        count++;
                }
            }
            return count >= 2;
        }

    /**
     * Method that check the completion of {@link server.Model.CommonGoal CommonGoal6}
     *
     * @param structure the structure on which the check will be made
     * @return {@code true} if the {@link server.Model.CommonGoal CommonGoal} is completed, {@code false} otherwise
     */
        public static boolean checkEight(PlayableItemTile[][] structure) {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 5; j++) {
                    int count = 0;
                    if (structure[i][j].getColour() != Colour.VOID) {
                        Colour value = structure[i][j].getColour();
                        for (int k = 0; k < 6; k++) {
                            for (int l = 0; l < 5; l++) {
                                if (structure[k][l].getColour() != Colour.VOID && structure[k][l].getColour().equals(value)) {
                                    count++;
                                }
                                if (count >= 8) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }

}

