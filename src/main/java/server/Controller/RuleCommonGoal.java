package server.Controller;

import Util.Colour;
import server.Model.ItemTile;
import server.Model.PlayableItemTile;

import java.util.ArrayList;
import java.util.HashSet;

public class RuleCommonGoal {
        public static boolean checkCorner(PlayableItemTile[][] structure) {
            return structure[0][0].getColour() != Colour.VOID && structure[0][0].getColour() == structure[0][4].getColour()
                    && structure[0][0].getColour() == structure[5][0].getColour() && structure[0][0].getColour() == structure[5][4].getColour();

        }

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
                            if(j==3&&matrix[i][j+1] != Colour.VOID && matrix[i - 1][j+1] != Colour.VOID &&
                                    matrix[i][j+1] == matrix[i - 1][j+1]){
                                count++;
                                matrix[i][j+1] = Colour.VOID;
                                matrix[i - 1][j+1] = Colour.VOID;
                            }

                    }
                }
            }
            return count >= 6;
        }


        public static boolean checkFourGroups(PlayableItemTile[][] structure) {
            Colour[][] matrix= new Colour[6][5];
            int count = 0;
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 5; j++) {
                    if (structure[i][j].getColour() != Colour.VOID) {
                        matrix[i][j] = structure[i][j].getColour();
                    }
                    else matrix[i][j] = Colour.VOID;
                }
            }
            for(int i=0;i<6;i++){
                for(int j=0;j<2;j++){
                    if(matrix[i][j]!=Colour.VOID&&matrix[i][j+1]!=Colour.VOID&&matrix[i][j+2]!=Colour.VOID&&matrix[i][j+3]!=Colour.VOID
                    && matrix[i][j]==matrix[i][j+1]&&matrix[i][j]==matrix[i][j+2]&&matrix[i][j]==matrix[i][j+3]){
                        count++;
                        matrix[i][j] = Colour.VOID;
                        matrix[i][j+1] = Colour.VOID;
                        matrix[i][j+2] = Colour.VOID;
                        matrix[i][j+3] = Colour.VOID;
                    }
                }
            }
            for(int i=0;i<3;i++) {
                for (int j = 0; j < 3; j++) {
                    if(matrix[i][j]!=Colour.VOID&&matrix[i+1][j]!=Colour.VOID&&matrix[i+2][j]!=Colour.VOID&&matrix[i+3][j]!=Colour.VOID
                            && matrix[i][j]==matrix[i+1][j]&&matrix[i][j]==matrix[i+2][j]&&matrix[i][j]==matrix[i+3][j]) {
                        count++;
                    }
                }
            }
            return count >= 4;
        }

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





        public static boolean checkDiagonal(PlayableItemTile[][] structure) {

          return((structure[0][0].getColour()!=Colour.VOID&&structure[0][0].getColour()==structure[1][1].getColour()
          &&structure[0][0].getColour()==structure[2][2].getColour()&&structure[0][0].getColour()==structure[3][3].getColour()
          &&structure[0][0].getColour()==structure[4][4].getColour())||(structure[1][0].getColour()!=Colour.VOID&&structure[1][0].getColour()==structure[2][1].getColour()
                  &&structure[1][0].getColour()==structure[3][2].getColour()&&structure[1][0].getColour()==structure[4][3].getColour()
                  &&structure[1][0].getColour()==structure[5][4].getColour()));

        }

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



        public static boolean CheckColumn1(PlayableItemTile[][] structure) {//scorro colonne e uso algoritmo che controlla un max di 3 tipi diversi tramite un counter
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


        public static boolean CheckColumn2(PlayableItemTile[][] structure) {//due colonne di 6 tipi diversi una cazzata basta fare controllo delle colonne con due per considerare le 6 righe e scorro avanti di colonne quindi usa un while con dentro un for e usa un counter
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

        public static boolean CheckLine1(PlayableItemTile[][] structure) { //uguale a column 1 ma sviluppato per righe
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



        public static boolean CheckLine2(PlayableItemTile[][] structure) { //uguale a column 2 ma sviluppato per le righe
            int count = 0;
            for(int i=0;i<6;i++){
                HashSet<Colour> distinctColour = new HashSet<>();
                ArrayList<Colour> colourArrayList = new ArrayList<>();
                for(int j=0;j<5;j++){
                    if(structure[i][j].getColour()!=Colour.VOID){
                        colourArrayList.add(structure[i][j].getColour());
                    }
                }
                if(colourArrayList.size()==5){
                    distinctColour.addAll(colourArrayList);
                    if(distinctColour.size()==5)
                        count++;
                }
            }
            return count >= 2;
        }


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

