package server.Model;


import java.util.Vector;


public class Shelf {
    private ItemTile[][] structure = new ItemTile[5][4];

    public ItemTile[][] getStructure(){
        return this.structure;
    }
    public void setStructure(ItemTile[][] structure) {
        this.structure = structure;
    }
    public ItemTile getShelfTile(int x ,int y){
        return this.structure[x][y];
    }

    public void isColumnAvailableInGame(int y){
        if(structure[0][y]!=null){
            System.out.println("The column is full");
        }else{
            int count = 0;
            for (int i = 0; i < 5; i++) {
                if (structure[i][y] == null) {
                    count++;
                }
            }
            System.out.println("The column is available and the number of cells free are: " + count);
        }
    }
    public Vector<Integer> putTile (int x , int y , ItemTile Tile , int numberOfTilesPicked){

        Object CellsAvailable;
        Vector<Integer> position = new Vector<Integer>();// The Vector class implements a growable array of objects. Like an array, it contains components that can be accessed using an integer index.
        // However, the size of a Vector can grow or shrink as needed to accommodate adding and removing items after the Vector has been created.
        boolean availability;
        availability = (boolean) iscolumnAvailable(y, numberOfTilesPicked)[0];
        if (availability == true) {
            // metodo che permette di distinguire i 3 casi di posizionamento: per una tessera , per due tessere e tre tessere
            // Nella pratica posiziona la tessera in una delle posizioni libere della libreria
            if (structure[x][y]==null && structure[x -1][y] !=null){
                structure[x][y] =Tile;
                position.add(y);
            }else if (structure[x][y]==null){
                // caso inserimento in base
                structure[x][y]=Tile;
                position.add(y);
            }
        }
        return position;
    }
    //i seguenti metodi vanno a supporto dell'inserimento corretto dell'item nella shelf
    public boolean CheckSamePosition(Vector<Integer> y){
        // verifichiamo se il vettore ha almeno un elemento
        if (y.size() > 0) {
            // memorizziamo il primo elemento del vettore in una variabile
            int firstElement = y.get(0);
            // confrontiamo ogni elemento del vettore con il primo elemento
            for (int i = 1; i < y.size(); i++) {
                if (y.get(i) != firstElement) {
                    System.out.println("DUMB,tiles picked in the same round MUST be in the same column!");
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
    public Object[] iscolumnAvailable ( int Y , int NumberOfTilesPicked) {
        // Metodo che permette di controllare che la colonna scelta dal giocatore per il posizionamento delle tessere sia effettivamente disponibile;
        // Dato che l'inserimento avviene stile pila(ovvero prendo un tile singola e la inserisco immediatamente nella libreria) applicheremo un approccio LIFO.
        int cellsAvailable = 0;
        boolean condition = false;
        boolean flag = true;
        while (flag && cellsAvailable != 4) {
            for (int i = 0; i < 4; i++) {
                if (structure[i][Y] != null) {
                    flag = false;
                    break; // Esci dal ciclo for quando trovi una cella non-vuota
                } else {
                    cellsAvailable = i; // Aggiorna l'indice dell'ultima cella vuota
                }
            }
        }
        if (cellsAvailable != 0) {
            switch (cellsAvailable) {
                case 1:
                    System.out.println("You can put ONLY one tile in the column:" +  Y);
                    if (cellsAvailable < NumberOfTilesPicked) {
                        System.err.println("Too many tiles picked");
                        System.out.println("Please select another column of the shelf");
                    } else {
                        condition = true;
                    }
                    break;
                case 2:
                    System.out.println("You can put ONLY two tiles in the column:" + Y);
                    if (cellsAvailable < NumberOfTilesPicked) {
                        System.err.println("Too many tiles picked");
                        System.out.println("Please select another column of the shelf");
                    } else {
                        condition = true;
                    }
                    break;
                default:
                    System.out.println("You can put up to 3 tiles in the column:" + Y);
                    condition = true;
            }
        }else{
            System.err.println("The column chosen is not available");
            System.err.println("Please choose another column or pick a different number of tiles");
        }
        Object[] conditions =new Object[]{condition, cellsAvailable};
        return conditions;
    }
    //nel controller
    public boolean isFull(ItemTile[][] structure) {
        for (int j = 0; j < 4; j++) {
            if (structure[0][j] == null) {
                System.out.println("You enough space to put tiles in the shelf");
                return false;
            }
        }
        System.out.println("you have completed your shelf"); // i due print vanno visti meglio perchè devono finire nella view non dovrebbero stare nel model
        return true;
    }

}