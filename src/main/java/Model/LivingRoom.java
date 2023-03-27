package Model;

import org.example.CommonGoal;
import org.example.*;


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

public class LivingRoom {

    private ItemTile[][] gameTable;
    private org.example.CommonGoal commonGoal1 = new org.example.CommonGoal();
    private org.example.CommonGoal commonGoal2 = new org.example.CommonGoal();


    public void createGameTable(int playerNum) { //nell'inserimento di playernum chiamo metodo di gamemodel getnumplayer e lo metto come argomento
        if (playerNum == 2) {
            LivingRoomFactory factory = new TwoLivingRoomFactory();
            this.gameTable = factory.create();
        } else if (playerNum == 3) {
            LivingRoomFactory factory = new ThreeLivingRoomFactory();
            this.gameTable = factory.create();
        } else if (playerNum == 4) {
            LivingRoomFactory factory = new FourLivingRoomFactory();
            this.gameTable = factory.create();
        }
    }


    public void setCommonGoal(){
        //usare un metodo random che sceglie che commongoaltype  da assegnare a commongoal1 e poi tenendo conto che non ho piu un commongoaltype richiamo il metodo
        // random e assegno a commongoal2
    }

    public org.example.CommonGoal getCommonGoal1() {  // questi due metodi verranno chiamati da tutti i player cosicchè vadano a ottenre i common goal della living room e internamente gestire i check dal controller
        return commonGoal1;
    }

    public CommonGoal getCommonGoal2() {
        return commonGoal2;
    }

    public PlayableItemTile pickTile(int x, int y) {//questo metodo permette alla LivingRoom di passare una sua tessera alla GameBoard
        PlayableItemTile helperTile;
        helperTile = (PlayableItemTile) gameTable[x][y];

        gameTable[x][y] = null;

        return helperTile;
    }

    public boolean searchVoid(int x, int y){
        if(gameTable[x][y] != null){
            return false;
        }
        return true;
    }

    public void fillVoid(int x, int y, PlayableItemTile tile){
        gameTable[x][y] = tile;
    }

    public boolean isTileAvailable(int x, int y) {//verifica se la singola Tile nella LivingRoom alle
        // coordinate x e y è disponibile per essere presa dal player

        return gameTable[x][y].isAvailable();
    }

    public boolean hasAdjacentTiles() {//check a inizio round su tessere adiacenti
        //ritorna true se almeno una PlayableItemTile ha una tessera adiacente != null e
        //la cui funzione nullDetection restituisce false
        int adjCounter = 0;

        for(int i=0; i<9 && adjCounter==0; i++){
            for(int j = 0; j < 9 && adjCounter == 0; j++){
                if (!gameTable[i][j].nullDetection() && gameTable[i][j]!=null){
                    //controllo corner cases
                    //1° tessere [0][3] e [0][4]
                    if(i == 0){
                        if (j==3) {
                            if(gameTable[1][3] != null && !gameTable[1][3].nullDetection()){
                                adjCounter++;
                            }
                            if (gameTable[0][4] != null && !gameTable[0][4].nullDetection()){
                                adjCounter++;
                            }
                        } else if (j==4){
                            if (gameTable[0][3] != null && !gameTable[0][3].nullDetection()){
                                adjCounter++;
                            }
                            if (gameTable[1][4] != null && !gameTable[1][4].nullDetection()){
                                adjCounter++;
                            }

                        }
                    }
                    //2° tessere  [4][0] e [5][0]
                    else if(j==0){
                        if(i==4){
                            if (gameTable[4][1] != null && !gameTable[4][1].nullDetection()){
                                adjCounter++;
                            }
                            if (gameTable[5][0] != null && !gameTable[5][0].nullDetection()){
                                adjCounter++;
                            }
                        }
                        if(i==5){
                            if (gameTable[4][0] != null && !gameTable[4][0].nullDetection()){
                                adjCounter++;
                            }
                            if (gameTable[5][1] != null && !gameTable[5][1].nullDetection()){
                                adjCounter++;
                            }
                        }
                    }
                    //3° tessere [8][4] e [8][5]
                    else if(i==8){
                        if(j==4){
                            if (gameTable[7][4] != null && !gameTable[7][4].nullDetection()){
                                adjCounter++;
                            }
                            if (gameTable[8][5] != null && !gameTable[8][5].nullDetection()){
                                adjCounter++;
                            }
                        }
                        if(j==5){
                            if (gameTable[7][5] != null && !gameTable[7][5].nullDetection()){
                                adjCounter++;
                            }
                            if (gameTable[8][4] != null && !gameTable[8][4].nullDetection()){
                                adjCounter++;
                            }
                        }
                    }
                    //4° tessere [3][8] e [4][8]
                    else if(j==8){
                        if(i==3){
                            if (gameTable[4][8] != null && !gameTable[4][8].nullDetection()){
                                adjCounter++;
                            }
                            if (gameTable[3][7] != null && !gameTable[3][7].nullDetection()){
                                adjCounter++;
                            }
                        }
                        if(i==4){
                            if (gameTable[3][8] != null && !gameTable[3][8].nullDetection()){
                                adjCounter++;
                            }
                            if (gameTable[4][7] != null && !gameTable[4][7].nullDetection()){
                                adjCounter++;
                            }
                        }
                    }
                    //ora guardo il caso generale
                    else{
                        //controllo la tile della riga sopra
                        if(gameTable[i-1][j] != null && !gameTable[i-1][j].nullDetection()){
                            adjCounter++;
                        }
                        //controllo la tile della riga sotto
                        if (gameTable[i+1][j] != null && !gameTable[i+1][j].nullDetection()){
                            adjCounter++;
                        }
                        //controllo la tile a sx
                        if (gameTable[i][j-1] != null && !gameTable[i][j-1].nullDetection()){
                            adjCounter++;
                        }
                        //controllo la tile a dx
                        if (gameTable[i][j+1] != null && !gameTable[i][j+1].nullDetection()){
                            adjCounter++;
                        }
                    }
                }
            }
        }

        if(adjCounter!=0) {
            return true;
        }
        return false;

    }

    public void updateAvailability() {//determina per ogni tile sulla LivingRoom se essa é disponibile o meno
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!gameTable[i][j].nullDetection() && gameTable[i][j] != null) {
                    gameTable[i][j].makeUnavailable();
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!gameTable[i][j].nullDetection() && gameTable[i][j] != null) {
                    //metto available tutti i corner cases
                    //1° tessere [0][3] e [0][4]
                    if (i == 0) {
                        if (j == 3) {
                            gameTable[i][j].makeAvailable();
                        }else if (j == 4) {
                            gameTable[i][j].makeAvailable();
                        }
                    }
                    //2° tessere  [4][0] e [5][0]
                    else if (j == 0) {
                        if (i == 4) {
                            gameTable[i][j].makeAvailable();
                        }
                        if (i == 5) {
                            gameTable[i][j].makeAvailable();
                        }
                    }
                    //3° tessere [8][4] e [8][5]
                    else if (i == 8) {
                        if (j == 4) {
                            gameTable[i][j].makeAvailable();
                        }
                        if (j == 5) {
                            gameTable[i][j].makeAvailable();
                        }
                    }
                    //4° tessere [3][8] e [4][8]
                    else if (j == 8) {
                        if (i == 3) {
                            gameTable[i][j].makeAvailable();
                        }
                        if (i == 4) {
                            gameTable[i][j].makeAvailable();
                        }
                    }
                    //guardo il caso generale
                    else{
                        //per ogni casella controllo se su almeno uno dei 4 lati c'è una NullItemTile
                        //oppure null
                        //controllo la tile della riga sopra
                        if(gameTable[i-1][j] == null || gameTable[i-1][j].nullDetection()){
                            gameTable[i][j].makeAvailable();
                        }
                        //controllo la tile della riga sotto
                        if (gameTable[i+1][j] == null || gameTable[i+1][j].nullDetection()){
                            gameTable[i][j].makeAvailable();
                        }
                        //controllo la tile a sx
                        if (gameTable[i][j-1] != null || !gameTable[i][j-1].nullDetection()){
                            gameTable[i][j].makeAvailable();
                        }
                        //controllo la tile a dx
                        if (gameTable[i][j+1] != null || !gameTable[i][j+1].nullDetection()){
                            gameTable[i][j].makeAvailable();
                        }
                    }
                }
            }
        }
    }

    public void updateAdjacentAvailabilityV1(int x, int y) {
        //Setta la variabile adjacency delle ItemTiles adiacenti

        gameTable[x][y].resetAdjacency();

        //adiacenza sopra
        if(gameTable[x-1][y] != null && !gameTable[x-1][y].getAdjacency()&& !gameTable[x-1][y].nullDetection()){
            gameTable[x-1][y].setAdjacency();
        }
        //adiacenza sotto
        if(gameTable[x+1][y] != null && !gameTable[x+1][y].getAdjacency()&& !gameTable[x+1][y].nullDetection()){
            gameTable[x+1][y].setAdjacency();
        }
        //adiacenza a sx
        if(gameTable[x][y-1] != null && !gameTable[x][y-1].getAdjacency()&& !gameTable[x][y-1].nullDetection()){
            gameTable[x][y-1].setAdjacency();
        }
        //adiacenza a dx
        if(gameTable[x][y+1] != null && !gameTable[x][y+1].getAdjacency() && !gameTable[x][y+1].nullDetection()){
            gameTable[x][y+1].setAdjacency();
        }
    }
    public void resetAdjacentAvailability(){
        for(int i=0; i<9; i++){
            for(int j=0; j<9 ; j++){
                if(gameTable[i][j] != null && !gameTable[i][j].nullDetection())
                    gameTable[i][j].resetAdjacency();
            }
        }
    }

    public void updateAdjacentAvailabilityV2(int x, int y, int firstX, int firstY) {
        //Setta la variabile adjacency delle ItemTiles adiacenti

        gameTable[x][y].resetAdjacency();

        //capisco dove mi sono spostato
        if (x == firstX - 1) { //sono andato sopra
            if (gameTable[x - 1][y] != null && !gameTable[x - 1][y].nullDetection()) {
                gameTable[x - 1][y].setAdjacency();
            }
            if (gameTable[firstX][firstY - 1] != null && !gameTable[firstX][firstY - 1].nullDetection()) {
                gameTable[firstX][firstY - 1].resetAdjacency();
            }
            if (gameTable[firstX][firstY - 1] != null && !gameTable[firstX][firstY - 1].nullDetection()) {
                gameTable[firstX][firstY + 1].resetAdjacency();
            }
        } else if (x == firstX + 1) { //sono andato sotto
            if (gameTable[x + 1][y] != null && !gameTable[x + 1][y].nullDetection()) {
                gameTable[x + 1][y].setAdjacency();
            }
            if (gameTable[firstX][firstY - 1] != null && !gameTable[firstX][firstY - 1].nullDetection()) {
                gameTable[firstX][firstY - 1].resetAdjacency();
            }
            if (gameTable[firstX][firstY + 1] != null && !gameTable[firstX][firstY + 1].nullDetection()) {
                gameTable[firstX][firstY + 1].resetAdjacency();
            }
        } else if (y == firstY - 1) { //sono andato a sx
            if (gameTable[x][y - 1] != null && !gameTable[x][y - 1].nullDetection()) {
                gameTable[x][y - 1].setAdjacency();
            }
            if (gameTable[firstX + 1][firstY] != null && !gameTable[firstX + 1][firstY].nullDetection()) {
                gameTable[firstX + 1][firstY].resetAdjacency();
            }
            if (gameTable[firstX - 1][firstY] != null && !gameTable[firstX - 1][firstY].nullDetection()) {
                gameTable[firstX - 1][firstY].resetAdjacency();
            }
        } else if (y == firstY + 1) { //sono andato a dx
            if (gameTable[x][y + 1] != null && !gameTable[x][y + 1].nullDetection()) {
                gameTable[x][y + 1].setAdjacency();
            }
            if (gameTable[firstX - 1][firstY] != null && !gameTable[firstX - 1][firstY].nullDetection()) {
                gameTable[firstX - 1][firstY].resetAdjacency();
            }
            if (gameTable[firstX + 1][firstY] != null && !gameTable[firstX + 1][firstY].nullDetection()) {
                gameTable[firstX + 1][firstY].resetAdjacency();
            }
        }
    }

    public boolean checkAdjacentAvailability() {//controlla se almeno una delle tessere adiacenti è
        // disponibile

        for(int i =0; i<9; i++){
            for(int j=0; j<9; j++) {
                if (gameTable[i][j] != null && !gameTable[i][j].nullDetection()){
                    if (gameTable[i][j].getAdjacency() && gameTable[i][j].isAvailable()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}









<<<<<<< HEAD








<<<<<<< HEAD


