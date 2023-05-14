package server.Controller;
import server.Model.PlayableItemTile;

import java.util.List;
import java.util.Vector;


public class RuleShelf {

    public static boolean commandPutTileCheckValidity(int x, int y, PlayableItemTile Tile,PlayableItemTile[][] structure, int numberOfTilesPicked) {
        boolean flag= false;
        boolean availability;
        availability = iscolumnAvailable(y, numberOfTilesPicked, structure);
        if (availability == true) {
            if ((structure[x][y].getIdCode() == 0 && (structure[x - 1][y].getIdCode() != 0))) {
                flag = true;
            } else if (structure[x][y].getIdCode()==0) {
                flag = true;
            }
        }else{
            flag = false;
        }
        return flag;
    }


    public static boolean iscolumnAvailable(int Y, int NumberOfTilesPicked, PlayableItemTile[][] structure) {
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
                    if (cellsAvailable < NumberOfTilesPicked) {
                        condition = false;
                    } else {
                        condition = true;
                    }
                    break;
                case 2:
                    if (cellsAvailable < NumberOfTilesPicked) {
                        condition = false;
                    } else {
                        condition = true;
                    }
                    break;
                default:
                    condition = true;
            }
        } else {
        }
        return condition;
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

}
