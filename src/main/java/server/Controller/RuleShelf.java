package server.Controller;
import Util.Colour;
import server.Model.PlayableItemTile;
import server.Model.Shelf;

import java.io.Serial;
import java.io.Serializable;


/**
 * Static Class used to implement the rules that restrict the insertion of the {@link PlayableItemTile PlayableItemTile} in the {@link server.Model.Shelf Shelf}
 */
public class RuleShelf implements Serializable {

    @Serial
    private static final long serialVersionUID = -4871491315493787470L;

    /**
     * Checks whether the chosen position in the {@link Shelf Shelf} is available or not
     *
     * @param x the 'x' position of the element of the {@link Shelf Shelf} to check
     * @param y the 'y' position of the element of the {@link Shelf Shelf} to check
     * @param structure the structure on which the checks will be made
     * @return {@code true} if the position chosen is correct, {@code false} otherwise
     */
    public static boolean commandPutTileCheckValidity(int x, int y, PlayableItemTile[][] structure) {
        boolean flag = false;

        if ((x != 5 && structure[x][y].getColour() == Colour.VOID && (structure[x + 1][y].getColour() != Colour.VOID))) {
            flag = true;
        }
        else if (x == 5 && structure[x][y].getColour() == Colour.VOID) {
            flag = true;
        }
        return flag;
    }

    /**
     * Checks whether the chosen column in the {@link Shelf Shelf} is available or not
     *
     * @param Y the 'y' position of the element of the {@link Shelf Shelf} to check
     * @param NumberOfTilesPicked the number of {@link PlayableItemTile PlayableItemTile} picked from the {@link server.Model.LivingRoom LivingRoom}
     * @param structure the structure on which the checks will be made
     * @return {@code true} if the position chosen is correct, {@code false} otherwise
     */
    public static boolean isColumnAvailable(int Y, int NumberOfTilesPicked, PlayableItemTile[][] structure) {
        int cellsAvailable = 0;
        boolean condition = false;
        for(int i = 0; i<6;i++){
            if(structure[i][Y].getColour()==Colour.VOID)
                cellsAvailable++;
        }
        if(cellsAvailable>=NumberOfTilesPicked)
            condition = true;
        return condition;
    }
}


