package server.Model;

import Util.LivingRoomFactory;
import Util.TwoLivingRoomFactory;
import Util.ThreeLivingRoomFactory;
import Util.FourLivingRoomFactory;


public class LivingRoom {

    private ItemTile[][] gameTable;
    private CommonGoal commonGoal1 = new CommonGoal();
    private CommonGoal commonGoal2 = new CommonGoal();


    //MODEL
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

    public ItemTile[][] getGameTable(){
        return this.gameTable;
    }


    public void setCommonGoal1(CommonGoal commonGoal1) { // delego al controller come scegliere random i commongoal e poi uso qusti due metodi per settare alla living room quali sono i commongoal
        this.commonGoal1 = commonGoal1;
    }

    public void setCommonGoal2(CommonGoal commonGoal2) {
        this.commonGoal2 = commonGoal2;
    }

    public CommonGoal getCommonGoal1() { //
        return commonGoal1;
    }

    public CommonGoal getCommonGoal2() {
        return commonGoal2;
    }

    public boolean nullDetection(int x, int y){
        if(gameTable[x][y] == null){
            return true;
        }
        return false;
    }

    public boolean nullTileDetection(int x, int y){
        return gameTable[x][y].nullDetection();
    }
    public boolean getTileAvailability(int x, int y){
        return gameTable[x][y].getAvailability();
    }

    public boolean getTileAdjacency(int x, int y){
        return gameTable[x][y].getAdjacency();
    }


    //MODEL
    public PlayableItemTile pickTile(int x, int y) {//questo metodo permette alla LivingRoom di passare una sua tessera alla GameBoard
        PlayableItemTile helperTile;
        helperTile = (PlayableItemTile) gameTable[x][y];

        gameTable[x][y] = null;

        return helperTile;
    }

    //MODEL
    public boolean searchVoid(int x, int y) {
        if (gameTable[x][y] != null) {
            return false;
        }
        return true;
    }

    //MODEL
    public void fillVoid(int x, int y, PlayableItemTile tile) {
        gameTable[x][y] = tile;
    }

    //MODEL
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

    //MODEL
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

    //MODEL
    public void resetAdjacentAvailability(){
        for(int i=0; i<9; i++){
            for(int j=0; j<9 ; j++){
                if(gameTable[i][j] != null && !gameTable[i][j].nullDetection())
                    gameTable[i][j].resetAdjacency();
            }
        }
    }

    //MODEL
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


}







