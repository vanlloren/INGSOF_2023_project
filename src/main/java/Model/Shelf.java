package Model;

import org.example.ItemTile;


public class Shelf {
    private ItemTile[][] structure
    private structure = new ItemTile[6][5];
    public boolean getTile(int x,int y){
        //metodo che non serve probabilmente//
    }
    public ItemTile[][] getStructure(){
        return this.structure;
    }
    public Vector<Integer> putTile ( int x , int y , ItemTile Tile , int NumberOfTilesPicked){
        int CellsAvailable;
        Vector<Integer> position = new Vector<Integer>();// The Vector class implements a growable array of objects. Like an array, it contains components that can be accessed using an integer index.
        // However, the size of a Vector can grow or shrink as needed to accommodate adding and removing items after the Vector has been created.
        boolean availability,validation;
        availability = iscolumnAvailable(int y)[0];
        CellsAvailable = iscolumnAvailable(int y)[1];
        if (availability == true ) {
            // metodo che permette di distinguire i 3 casi di posizionamento: per una tessera , per due tessere e tre tessere
            // Nella pratica posiziona la tessera in una delle posizioni libere della libreria
            if (structure[ int x ][int y] ==null && structure[ int x -1][int y ] !=null){
                structure[ int x ][int y] =ItemTile Tile;
                position.add(y);
            }else if (structure[ int x ][int y] ==null ){
                // caso inserimento in base
                structure[ int x ][int y] =ItemTile Tile;
                position.add(y)
            }
        /*else{
            System.err.println("The cell choosen is full");
            System.err.println("Please choose another cell");
        }*/
    /*else {
        System.err.println("You cannot choose different columns ");
    }*/
        }
        return position;
    }
    public boolean CheckSamePosition(Vector<Integer> y){
        // verifichiamo se il vettore ha almeno un elemento
        if (y.size() > 0) {
            // memorizziamo il primo elemento del vettore in una variabile
            int firstElement = y[0];
            // confrontiamo ogni elemento del vettore con il primo elemento
            for (int i = 1; i < y.size(); i++) {
                if (y[i] != firstElement) {
                    System.out.println("DUMB,tiles picked in the same round MUST be in the same column!")
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
    public static Object[] iscolumnAvailable ( int Y , int NumberOfTilesPicked) {
        // Metodo che permette di controllare che la colonna scelta dal giocatore per il posizionamento delle tessere sia effettivamente disponibile;
        // Dato che l'inserimento avviene stile pila(ovvero prendo un tile singola e la inserisco immediatamente nella libreria) applicheremo un approccio LIFO.
        int cellsAvailable;
        cellsAvailable = 0;
        boolean condition, flag;
        condition = false;
        flag = true;
        while (flag) {
            for (int i = 0; i < 5; i++) {
                if (structure[i][Y] != null) {
                    flag = false;
                }
                cellsAvailable = i;
            }
        }
        if (cellsAvailable != 0) {
            switch (cellsAvailable)
            case 1:
                System.out.println("You can put ONLY one tile in the column:" + int Y);
                if (cellsAvailable < NumberOfTilesPicked) {
                    System.err.println("Too many tiles picked");
                    System.out.println("Please select another column of the shelf");
                } else {
                    condition = true;
                }
                break;
            case 2:
                System.out.println("You can put ONLY two tiles in the column:" + int Y);
                if (cellsAvailable < NumberOfTilesPicked) {
                    System.err.println("Too many tiles picked");
                    System.out.println("Please select another column of the shelf");
                } else {
                    condition = true;
                }
                break;
            default:
                System.out.println("You can put up to 3 tiles in the column:" + int Y);
                condition = true;
        } else {
            System.err.println("The column chosen is not available");
            System.err.println("Please choose another column or pick a different number of tiles");
        }
        return new Object[]{condition, cellsAvailable};
    }

    public boolean isFull(ItemTile[][] structure) {
        for (int j = 0; j < 4; j++) {
            if (structure[0][j] == null) {
                System.out.println("You enough space to put tiles in the shelf")
                return false;
            }
        }
        return true;
    }

}


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









<<<<<<< HEAD








<<<<<<< HEAD



