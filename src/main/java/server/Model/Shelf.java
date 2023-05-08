package server.Model;


import java.util.Vector;
import Util.Colour;

public class Shelf {
    private final PlayableItemTile[][] structure = new PlayableItemTile[5][4];

    public PlayableItemTile[][] setupOfStructure() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                structure[i][j] = new PlayableItemTile("void", 0);
            }
        }
        return this.structure;
    }

    public PlayableItemTile[][] getStructure() {
        return this.structure;
    }

    public PlayableItemTile getShelfTile(int x, int y) {
        return this.structure[x][y];
    }

    public void isColumnAvailableInGame(int y) {
        if (structure[0][y] != null) {
            System.out.println("The column is full");
        } else {
            int count = 0;
            for (int i = 0; i < 6; i++) {
                if (structure[i][y] == null) {
                    count++;
                }
            }
            System.out.println("The column is available and the number of cells free are: " + count);
        }
    }

    public Vector<Integer> putTile(int x, int y, PlayableItemTile Tile, int numberOfTilesPicked, Vector<Integer> position) {

        Object CellsAvailable;
        // The Vector class implements a growable array of objects. Like an array, it contains components that can be accessed using an integer index.
        // However, the size of a Vector can grow or shrink as needed to accommodate adding and removing items after the Vector has been created.
        boolean availability;
        availability = (boolean) iscolumnAvailable(y, numberOfTilesPicked)[0];
        if (availability) {
            // metodo che permette di distinguire i 3 casi di posizionamento: per una tessera , per due tessere e tre tessere
            // Nella pratica posiziona la tessera in una delle posizioni libere della libreria
            if (structure[x][y].getColour().equals(Colour.VOID) && !structure[x - 1][y].getColour().equals(Colour.VOID)) {
                String colour = toString(Tile);
                structure[x][y] = new PlayableItemTile(colour, Tile.getIdCode());
                position.add(y);
                boolean flag = CheckSamePosition(position);
                if(!flag){
                    System.out.println("DUMB,tiles picked in the same round MUST be in the same column!");
                    position.remove(y);
                    return position;
                }
            } else if (structure[x][y].getColour().equals(Colour.VOID)) {
                // caso inserimento in base
                String colour = toString(Tile);
                structure[x][y] = new PlayableItemTile(colour, Tile.getIdCode());
                position.add(y);
                boolean flag = CheckSamePosition(position);
                if(!flag){
                    System.out.println("DUMB,tiles picked in the same round MUST be in the same column!");
                    position.remove(y);
                    return position;
                }
            }
        }
        return position;
    }

    private Object[] iscolumnAvailable(int y, int numberOfTilesPicked) {
        // Metodo che permette di controllare che la colonna scelta dal giocatore per il posizionamento delle tessere sia effettivamente disponibile;
        // Dato che l'inserimento avviene stile pila(ovvero prendo un tile singola e la inserisco immediatamente nella libreria) applicheremo un approccio LIFO.
        int cellsAvailable = 0;
        boolean condition = false;
        boolean flag = true;
        while (flag && cellsAvailable != 6) {
            for (int i = 0; i < 6; i++) {
                if (!structure[i][y].getColour().equals(Colour.VOID)) {
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
                    System.out.println("You can put ONLY one tile in the column:" + y);
                    if (cellsAvailable < numberOfTilesPicked) {
                        System.err.println("Too many tiles picked");
                        System.out.println("Please select another column of the shelf");
                    } else {
                        condition = true;
                    }
                    break;
                case 2:
                    System.out.println("You can put ONLY two tiles in the column:" + y);
                    if (cellsAvailable < numberOfTilesPicked) {
                        System.err.println("Too many tiles picked");
                        System.out.println("Please select another column of the shelf");
                    } else {
                        condition = true;
                    }
                    break;
                default:
                    System.out.println("You can put up to 3 tiles in the column:" + y);
                    condition = true;
            }
        } else {
            System.err.println("The column chosen is not available");
            System.err.println("Please choose another column or pick a different number of tiles");
        }
        Object[] conditions = new Object[]{condition, cellsAvailable};
        return conditions;
    }

    private String toString(PlayableItemTile tile) {
        return String.valueOf(tile.getColour());
    }

    //i seguenti metodi vanno a supporto dell'inserimento corretto dell'item nella shelf
    public boolean CheckSamePosition(Vector<Integer> y) {
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
        }
        return true;
    }
        //nel controller
        public boolean isFull () {
            for (int j = 0; j < 5; j++) {
                if (this.structure[0][j].getColour().equals(Colour.VOID)) {
                    System.out.println("You have enough space to put tiles in the shelf");
                    return false;
                }
            }
            System.out.println("you have completed your shelf"); // i due print vanno visti meglio perchè devono finire nella view non dovrebbero stare nel model
            return true;
        }
}

