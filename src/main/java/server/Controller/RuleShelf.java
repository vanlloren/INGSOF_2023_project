package server.Controller;
import server.Model.PlayableItemTile;

import java.util.List;
import java.util.Vector;


public class RuleShelf {
    /*
    public static boolean isColumnAvailableInGame(int y, PlayableItemTile[][] structure) {
        boolean flag;
        if (structure[0][y].getIdCode() != 0) {
            flag = false;
        } else {
            int count = 0;
            for (int i = 0; i < 5; i++) {
                if (structure[i][y].getIdCode()==0) {
                    count++;
                }
            }
            flag = true;
        }
        return flag;
    }
*/
    public static boolean putTileCheckValidity(int x, int y, PlayableItemTile Tile,PlayableItemTile[][] structure, int numberOfTilesPicked) {
        boolean flag= false;
        boolean availability;
        availability = (boolean) iscolumnAvailable(y, numberOfTilesPicked, structure)[0];
        if (availability == true) {
            if ((structure[x][y].getIdCode() == 0 && (structure[x - 1][y].getIdCode() != 0))) {
                flag = true;
            } else if (structure[x][y].getIdCode()==0) {
                // caso inserimento in base
                flag = true;
            }
        }else{
            flag = false;
        }
        return flag;
    }
    public static boolean CheckSamePosition(Vector<Integer> y) {
        // verifichiamo se il vettore ha almeno un elemento
        if (y.size() > 0) {
            // memorizziamo il primo elemento del vettore in una variabile
            int firstElement = y.get(0);
            // confrontiamo ogni elemento del vettore con il primo elemento
            for (int i = 1; i < y.size(); i++) {
                if (y.get(i) != firstElement) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public static Object[] iscolumnAvailable(int Y, int NumberOfTilesPicked, PlayableItemTile[][] structure) {
        int cellsAvailable = 0;
        boolean condition = false;
        boolean flag = true;
        while (flag && cellsAvailable != 4) {
            for (int i = 0; i < 4; i++) {
                if (structure[i][Y].getIdCode() != 0) {
                    flag = false;
                    break; // Esci dal ciclo for quando trovi una cella non-vuota
                } else {
                    cellsAvailable = i+1; // Aggiorna l'indice dell'ultima cella vuota
                }
            }
        }
        if (cellsAvailable != 0) {
            switch (cellsAvailable) {
                case 1:
                    //System.out.println("You can put ONLY one tile in the column:" + Y);
                    if (cellsAvailable < NumberOfTilesPicked) {
                        //System.err.println("Too many tiles picked");
                        //System.out.println("Please select another column of the shelf");
                        condition = false;
                    } else {
                        condition = true;
                    }
                    break;
                case 2:
                    //System.out.println("You can put ONLY two tiles in the column:" + Y);
                    if (cellsAvailable < NumberOfTilesPicked) {
                        //System.err.println("Too many tiles picked");
                        //System.out.println("Please select another column of the shelf");
                        condition = false;
                    } else {
                        condition = true;
                    }
                    break;
                default:
                    //System.out.println("You can put up to 3 tiles in the column:" + Y);
                    condition = true;
            }
        } else {
            //System.err.println("The column chosen is not available");
            //System.err.println("Please choose another column or pick a different number of tiles");
        }
        Object[] conditions = new Object[]{condition, cellsAvailable};
        return conditions;
    }
    public int freeCellsInShelf(PlayableItemTile[][] structure){
        //Useful method to check also the maximum number of tiles that could be picked in the livingRoom
        List<Integer> list = new Vector<Integer>();
        int count=0;
        for(int j= 0; j<5; j++){
            for(int i= 0; j<6; j++){
                if(structure[i][j].getIdCode() !=0){
                   count++;
                }
            }
            list.add(count);
        }
        int max = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            int valore = list.get(i);
            if (valore > max) {
                max = valore;
            }
        }
        return max;
    }

    public static boolean isFull(PlayableItemTile[][] structure) {
        for (int j = 0; j < 5; j++) {
            if (structure[0][j] == null) {
                return false;
            }
        }
        return true;
    }
}
