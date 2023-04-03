package Util;

import Model.ItemTile;
import java.util.HashSet;

public class RuleCommonGoal {
        public static boolean checkCorner(ItemTile[][] structure) {
            boolean checker = false;
            if (structure[0][0] != null && structure[0][0].getColour() == structure[0][4].getColour()
                    && structure[0][0].getColour() == structure[5][0].getColour() && structure[0][0].getColour() == structure[5][4].getColour())
                checker = true;
            return checker;

        }

        public static boolean checkSixCouples(ItemTile[][] structure) {
            Colour[][] matrix=null;
            int count = 0;

            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 5; j++) {
                    if (structure[i][j] != null) {
                        matrix[i][j] = structure[i][j].getColour();
                    }
                }
            }
            //inizio a controllare quante coppie ci sono e appena ne trovo una la rendo null per evitare sovrapposizione(caso particolare forma ad L di 4 tessere uguali devo valutare la disposizione della coppia
            //in maniera tale da contare 2 coppie e non una coppia sbagliata con due caselle non adiacenti che se avessi scelto meglio avrei ottenuto due coppie
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


        public static boolean checkFourGroups(ItemTile[][] structure) {
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

        public static boolean checkSquare(ItemTile[][] structure) {                          //per scrivere codice che controlla le 6 coppie sfrutta questo algoritmo modificando alcuni parametri
            boolean checker = false;
            int k = 0;
            for (int i = 0; i <= 4; i++) {
                for (int j = 0; j <= 3; j++) {
                    if (structure[i][j] != null) {
                        Colour value = structure[i][j].getColour();
                        if (structure[i][j + 1] != null && structure[i + 1][j] != null && structure[i + 1][j + 1] != null && structure[i][j + 1].getColour() == value &&
                                structure[i + 1][j].getColour() == value && structure[i +1][j + 1].getColour() == value) {
                            k = i;
                            while (k < i+2) {
                                int l = j + 2;
                                while (l < 4) {
                                    //scrivi controlla quadrato//
                                    if (//qui scrivi condizioni quadrati) {
                                        checker = true;
                                        l++;
                                    }
                                k++;
                                }

                            k = i + 2;
                            while (k < 5) {
                                int l = j;
                                while (l < 5) {
                                    //uguale a commento sopra//

                                    if (//trovato un altro quadrato con stesso colore) {
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


        public static boolean checkDiagonal(ItemTile[][] structure) {
            boolean checker = true;
            if (structure[0][0] != null) {
                Colour value = structure[0][0].getColour();
                for (int i = 1; i <= 4; i++) {
                    if (structure[i][i].getColour() != value)
                        checker = false;
                    break;
                }
            }
            if (structure[1][0] != null) {
                Colour value = structure[0][0].getColour();
                for (int i = 2; i <= 5; i++) {
                    if (structure[i][i].getColour() != value)
                        checker = false;
                    break;
                }
            }
            // per antidiagonale chiedo al prof
            if () {
                Colour value = structure[0][0].getColour();

                for (int i = 0; i <= 4; i++) {
                    if (structure[i][i].getColour() != value)
                        checker = false;
                    break;
                }
            }
            if () {
                Colour value = structure[0][0].getColour();
                for (int i = 0; i <= 4; i++) {
                    if (structure[i][i].getColour() != value)
                        checker = false;
                    break;

                }
            }
            return checker;
        }

        public static boolean checkCrux(ItemTile[][] structure) {
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

        public static boolean checkStair(ItemTile[][] structure) {
            boolean checker = true;
            int k = 4;
            for (int i = 5; i >= 0; i--) {
                for (int j = k; j >= 0; j--) {
                    if (structure[i][j] == null) {
                        checker = false;
                    }
                }
                int z = 4;
                while (z > k) {
                    if (structure[i][z] != null)
                        checker = false;
                    z--;
                }
                k--;
            }
            k = 0;
            for (int i = 5; i >= 0; i--) {
                for (int j = k; j <= 4; j++) {
                    if (structure[i][j] == null) {
                        checker = false;
                    }
                    int z = 0;
                    while (z < k) {
                        if (structure[i][z] != null)
                            checker = false;
                        z++;
                    }
                    k++;
                }
            }

            return checker;
        }


        public static boolean CheckColumn1(ItemTile[][] structure) {//scorro colonne e uso algoritmo che controlla un max di 3 tipi diversi tramite un counter
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


        public static boolean CheckColumn2(ItemTile[][] structure) {//due colonne di 6 tipi diversi una cazzata basta fare controllo delle colonne con due per considerarer le 6 righe e scorro avanti di colonne quindi usa un while con dentro un for e usa un counter
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

        public static boolean CheckLine1(ItemTile[][] structure) { //uguale a column 1 ma sviluppato per righe
            int lineCount = 0;
            boolean checker = false;
            for (int i = 5; i >= 0; i--) {
                int val = 1;
                for (int j = 0; j < 5; j++) {
                    if (structure[i][j] == null) {    //partendo dal basso controllo che la riga sia piena senno non ha senso
                        val = 0;
                        break;
                    }
                }
                if (val == 1) {
                    HashSet<Colour> list = new HashSet<Colour>();
                    for (int k = 0; k < 5; k++) {
                        list.add(structure[i][k].getColour());
                    }
                    if (list.size() <= 3) {
                        lineCount++;
                    }
                } else
                    break; //se la riga inferiore rispetto a quella controllata non è completa non ha senso continuare il for perchè l'obiettivo non è raggiungibile per come è composto l'inserimento delle carte dal basso verso l'alto
            }
            if (lineCount >= 4)
                checker = true;
            return checker;}



        public static boolean CheckLine2(ItemTile[][] structure) { //uguale a column 2 ma sviluppato per le righe
            int lineCount = 0;
            boolean checker = false;
            for (int i = 5; i >= 0; i--) {
                int val = 1;
                for (int j = 0; j < 5; j++) {
                    if (structure[i][j] == null) {    //partendo dal basso controllo che la riga sia piena senno non ha senso
                        val = 0;
                        break;
                    }
                }
                if (val == 1) {
                    HashSet<Colour> list = new HashSet<Colour>();
                    for (int k = 0; k < 5; k++) {
                        list.add(structure[i][k].getColour());
                    }
                    if (list.size() == 5) {
                        lineCount++;
                    }
                } else
                    break; //se la riga inferiore rispetto a quella controllata non è completa non ha senso continuare il for perchè l'obiettivo non è raggiungibile per come è composto l'inserimento delle carte dal basso verso l'alto
            }
            if (lineCount == 2)
                checker = true;
            return checker;


        }


        public static boolean checkEight(ItemTile[][] structure) {
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

