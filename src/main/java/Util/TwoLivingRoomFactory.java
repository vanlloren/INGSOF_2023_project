package Util;

import server.Model.ItemTile;
import server.Model.NullItemTile;
import server.Model.PlayableItemTile;

public class TwoLivingRoomFactory extends LivingRoomFactory {
    @Override
    public ItemTile[][] create() {
        ItemTile[][] helperTable = new ItemTile[9][9];
        NullItemTile nullTile = new NullItemTile();

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                helperTable[i][j] = null;
            }
        }

        for (int i = 0; i < 9; i++) {
            //metto null Tiles da [0][0] a [0][8]
            for (int j = 0; j < 9 && i == 0; j++) {
                helperTable[i][j] = nullTile;
            }
            //metto null Tiles da [1][0] a [1][2] e da [1][5] a [1][8]
            for (int j = 0; j < 9 && i == 1; j++) {
                if (j < 3 || j > 4) {
                    helperTable[i][j] = nullTile;
                }
            }

            //metto null Tiles da [7][0] a [7][3] e da [7][6] a [7][8]
            for (int j = 0; j < 9 && i == 7; j++) {
                if (j < 4 || j > 5) {
                    helperTable[i][j] = nullTile;
                }
            }
            //metto null Tiles da [2][0] a [2][2] e da [2][6] a [2][8]
            //metto null Tiles da [6][0] a [6][2] e da [6][6] a [6][8]
            for (int j = 0; j < 9 && (i == 2 || i == 6); j++) {
                if (j < 3 || j > 5) {
                    helperTable[i][j] = nullTile;
                }
            }
            //metto null Tile in [3][0] e [3][1] e [3][8]
            helperTable[3][0] = nullTile;
            helperTable[3][1] = nullTile;
            helperTable[3][8] = nullTile;

            //metto null Tile in [4][0] e [4][8]
            helperTable[4][0] = nullTile;
            helperTable[4][8] = nullTile;

            //metto null Tiles in [5][1] e [5][8] e [5][7]
            helperTable[5][1] = nullTile;
            helperTable[5][7] = nullTile;
            helperTable[5][8] = nullTile;


            //metto null Tiles da [8][0] a [8][8]
            for (int j = 0; j < 9 && i == 8; j++) {
                helperTable[i][j] = nullTile;
            }
        }
        return helperTable;
    }
}
