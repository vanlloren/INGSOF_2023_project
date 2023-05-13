package server.Controller;

import Util.Colour;
import server.Model.ItemTile;
import server.Model.PlayableItemTile;

import java.util.ArrayList;
import java.util.HashSet;

public class RuleCommonGoal {
        public static boolean checkCorner(PlayableItemTile[][] structure) {
            boolean checker = false;
            if (structure[0][0] != null && structure[0][0].getColour() == structure[0][4].getColour()
                    && structure[0][0].getColour() == structure[5][0].getColour() && structure[0][0].getColour() == structure[5][4].getColour())
                checker = true;
            return checker;

        }

        public static boolean checkSixCouples(PlayableItemTile[][] structure) {
            Colour[][] matrix = null;
            int count = 0;

            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 5; j++) {
                    if (structure[i][j] != null) {

                        matrix[i][j] = structure[i][j].getColour();
                    }
                }
            }

            for (int i = 5; i > 0; i--) {
                for (int j = 0; j < 4; j++) {
                    if (matrix[i][j] != null && matrix[i][j + 1] != null &&
                            matrix[i][j + 1] == matrix[i][j]) {
                        count++;
                        matrix[i][j] = null;
                        matrix[i][j + 1] = null;
                    }
                    if (i < 5) {
                        if (matrix[i][j] != null && matrix[i + 1][j] != null &&
                                matrix[i][j] == matrix[i + 1][j]) {
                            count++;
                            matrix[i][j] = null;
                            matrix[i + 1][j] = null;
                        }
                    }
                }
            }
            if (count >= 6)
                return true;
            else return false;
        }


        public static boolean checkFourGroups(PlayableItemTile[][] structure) {
            Colour[][] matrix=null;
            int count = 0;
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 5; j++) {
                    if (structure[i][j] != null) {
                        matrix[i][j] = structure[i][j].getColour();
                    }
                }
            }
            for (int i = 5; i > 0; i--) {
                for (int j = 0; j < 2; j++) {
                    if (matrix[i][j] != null && matrix[i][j + 1] != null &&
                            matrix[i][j + 2] != null && matrix[i][j + 3] != null && matrix[i][j + 1] == matrix[i][j]
                            && matrix[i][j + 2] == matrix[i][j] && matrix[i][j + 3] == matrix[i][j]) {
                        count++;
                        matrix[i][j] = null;
                        matrix[i][j + 1] = null;
                        matrix[i][j + 2] = null;
                        matrix[i][j + 3] = null;
                    }
                    if (i < 2) {
                        if (matrix[i][j] != null && matrix[i + 1][j] != null &&
                                matrix[i + 2][j] != null && matrix[i + 3][j] != null &&
                                matrix[i][j] == matrix[i + 1][j] && matrix[i][j] == matrix[i + 2][j] && matrix[i][j] == matrix[i + 3][j]) {
                            count++;
                            matrix[i][j] = null;
                            matrix[i + 1][j] = null;
                            matrix[i + 2][j] = null;
                            matrix[i + 3][j] = null;
                        }
                    }
                }
            }
            if (count >= 4)
                return true;
            else return false;
        }

        public static boolean checkSquare(PlayableItemTile[][] structure) {
            int k = 0;
            for (int i = 0; i <= 4; i++) {
                for (int j = 0; j <= 3; j++) {
                    if (structure[i][j] != null) {
                        Colour value = structure[i][j].getColour();
                        if (structure[i][j + 1] != null && structure[i + 1][j] != null && structure[i + 1][j + 1] != null && structure[i][j + 1].getColour() == value &&
                                structure[i + 1][j].getColour() == value && structure[i + 1][j + 1].getColour() == value) {
                            k = i;
                            while (k < i + 2) {
                                int l = j + 2;
                                Colour val = structure[k][l].getColour();
                                while (l < 4) {
                                    if (structure[k][l + 1] != null && structure[k + 1][l] != null && structure[k + 1][l + 1] != null && structure[k][l + 1].getColour() == val &&
                                            structure[k + 1][l].getColour() == val && structure[k + 1][l + 1].getColour() == val) {
                                        return true;
                                    }
                                    l++;
                                }
                                k++;
                            }

                            k = i + 2;
                            while (k < 5) {
                                int l = j;
                                while (l < 5) {
                                    Colour val = structure[k][j].getColour();

                                    if (structure[k][l + 1] != null && structure[k + 1][l] != null && structure[k + 1][l + 1] != null && structure[k][l + 1].getColour() == val &&
                                            structure[k + 1][l].getColour() == val && structure[k + 1][l + 1].getColour() == val) {
                                        return true;
                                    }
                                    l++;
                                }
                                k++;
                            }
                        }

                    }

                }
            }
            return false;
        }


        public static boolean checkDiagonal(PlayableItemTile[][] structure) {
            boolean checker = true;
            if (structure[0][0]==null&&structure[1][0]==null){
                return false;
            }
            else if (structure[0][0] != null) {
                Colour value = structure[0][0].getColour();
                for (int i = 1; i <= 4; i++) {
                    if ((structure[i][i]!=null&&structure[i][i].getColour() != value)||structure[i][i]==null){
                        checker = false;
                        break;}
                }
            }
            else if (structure[1][0] != null) {
                Colour value = structure[1][0].getColour();
                for (int i = 2; i <= 5; i++) {
                    if ((structure[i][i-1]!=null&&structure[i][i-1].getColour() != value)||structure[i][i-1]==null){
                        checker = false;
                        break;}
                }
            }
        return checker;
        }

        public static boolean checkCrux(PlayableItemTile[][] structure) {
            for (int i = 1; i <= 4; i++) {
                for (int j = 1; j <= 3; j++) {
                    if (structure[i][j] != null && structure[i - 1][j - 1] != null &&
                            structure[i + 1][j + 1] != null && structure[i + 1][j - 1] != null && structure[i - 1][j + 1] != null &&
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

          if(structure[0][0]==null&&structure[1][0]!=null&&
             structure[1][1]==null&&structure[2][1]!=null&&
             structure[2][2]==null&&structure[3][2]!=null&&
             structure[3][3]==null&&structure[4][3]!=null&&
             structure[4][4]==null&&structure[5][4]!=null)
            return true;
          else if(structure[0][4]==null&&structure[1][4]!=null&&
                  structure[1][3]==null&&structure[2][3]!=null&&
                  structure[2][2]==null&&structure[3][2]!=null&&
                  structure[3][1]==null&&structure[4][1]!=null&&
                  structure[4][0]==null&&structure[5][0]!=null)
              return true;

          return false;
        }


        public static boolean CheckColumn1(PlayableItemTile[][] structure) {//scorro colonne e uso algoritmo che controlla un max di 3 tipi diversi tramite un counter
            int columnCount = 0;
            boolean checker = false;

            for (int j = 0; j < 5; j++) {
                if (structure[0][j] != null) {
                    HashSet<Colour> list = new HashSet<Colour>();
                    for (int i = 0; i < 6; i++) {
                        list.add(structure[i][j].getColour());
                    }
                    if (list.size() <= 3)
                        columnCount++;
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
                if (structure[0][j] != null) {
                    HashSet<Colour> list = new HashSet<Colour>();
                    for (int i = 0; i < 6; i++) {
                        list.add(structure[i][j].getColour());
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
                HashSet<Colour> distinctColour = new HashSet<Colour>();
                ArrayList<Colour> colourArrayList = new ArrayList<Colour>();
                for(int j=0;j<5;j++){
                    if(structure[i][j]!=null){
                        colourArrayList.add(structure[i][j].getColour());
                    }
                }
                if(colourArrayList.size()==5){
                    distinctColour.addAll(colourArrayList);
                    if(distinctColour.size()<=3)
                        count++;
                }
            }
        if(count>=4)
            return true;
        else return false;
        }



        public static boolean CheckLine2(PlayableItemTile[][] structure) { //uguale a column 2 ma sviluppato per le righe
            int count = 0;
            for(int i=0;i<6;i++){
                HashSet<Colour> distinctColour = new HashSet<Colour>();
                ArrayList<Colour> colourArrayList = new ArrayList<Colour>();
                for(int j=0;j<5;j++){
                    if(structure[i][j]!=null){
                        colourArrayList.add(structure[i][j].getColour());
                    }
                }
                if(colourArrayList.size()==5){
                    distinctColour.addAll(colourArrayList);
                    if(distinctColour.size()==5)
                        count++;
                }
            }
            if(count>=2)
                return true;
            else return false;
        }


        public static boolean checkEight(PlayableItemTile[][] structure) {
            for (int i = 0; i < structure.length; i++) {
                for (int j = 0; j < structure.length; j++) {
                    int count = 0;
                    if (structure[i][j] != null) {
                        Colour value = structure[i][j].getColour();
                        for (int k = 0; k < structure.length; k++) {
                            for (int l = 0; l < structure.length; l++) {
                                if (structure[k][l] != null && structure[k][l].getColour().equals(value)) {
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

