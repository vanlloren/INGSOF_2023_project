package server.Controller;
import Util.Colour;
import server.Model.PlayableItemTile;

import java.io.Serial;
import java.io.Serializable;




public class RuleShelf implements Serializable {

    @Serial
    private static final long serialVersionUID = -4871491315493787470L;

    public static boolean commandPutTileCheckValidity(int x, int y, PlayableItemTile[][] structure) {
        boolean flag = false;

        if ((x != 5 && structure[x][y].getColour() == Colour.VOID && (structure[x - 1][y].getColour() != Colour.VOID))) {
            flag = true;
        } else if (x == 5 && structure[x][y].getColour() == Colour.VOID) {
            flag = true;
        }
        return flag;
    }


    public static boolean iscolumnAvailable(int Y, int NumberOfTilesPicked, PlayableItemTile[][] structure) {
        int cellsAvailable = 0;
        boolean condition = false;
        for(int i = 0; i<6;i++){
            if(structure[i][Y].getColour()!=Colour.VOID)
                cellsAvailable++;
        }
        if(cellsAvailable>=NumberOfTilesPicked)
            condition = true;
        return condition;
    }
}


