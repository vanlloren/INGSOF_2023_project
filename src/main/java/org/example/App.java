package org.example;

package org.example;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class GameModel {
    private String chairOwner;
    private int playersNumber;
    private String currPlayer;
    private String matchWinner;
    private GameBoard myShelfie;
    private List<Player> playersInGame;
    private Player[] turnOrder;
    private Player[] has;

    public void launchGame_Myshelfie(){
        // va scritta tutta la partita con lo svolgimento//







    }



    public void setChairOwner(Player) {
        // ipotizzo di determinare/ricevere il nome utente di chi effettua il primo turno di gioco e di
        // inserirlo in chairOwner
    }

    public List<Player> getPlayersInGame(List<Player> playersInGame) {
        playersInGame = new ArrayList<Player>();
        playersNumber =
        return playersInGame;
    }

    public String addPlayer() {
        return player;
    }

    public void generateGameBoard() {
        GameBoard helperBoard = new GameBoard();
        myShelfie = helperBoard;
    }

    public void nextTurn() {
    }
    public boolean haslaunchTerminate() {
        points++;
        return true;

    }
    public List<Player> getTurnOrder(){

    }

}

public class Player {
    private String nickname;
    private int points;
    private Shelf personalShelf;
    private PersonalGoal personalGoal;
    private CommonGoal commonGoal1;
    private CommonGoal commonGoal2;
    private RuleCommonGoal ruleCommonGoal;
    private LivingRoom livingRoom;

    public LivingRoom setLivingRoom(LivingRoom livingRoom){
        this.livingRoom= livingRoom;



    }
    public setRuleCommonGoal(){
        ruleCommonGoal = new RuleCommonGoal();
        ruleCommonGoal.setMyShelf(personalShelf);}

    public Shelf setShelf(){
        personalShelf= new Shelf(); }

    public String getName(){
        return this.nickname;}
    public List<ScoringTokensType> setListToken(){



    }

    public int calculatePoint(){
        if(commonGoal1.checkGoal()){

            this.points=this.points+commonGoal1.getPoint();

        }
        if(commonGoal2.checkGoal()) {
            this.points = this.points + commonGoal2.getPoint();{
            }
            if(personalGoal){
                this.points=this.points+personalGoal.getPoint();


            }}}
    public void setPersonalGoal(){
        personalGoal = new PersonalGoal();
        //funzione che assegna personalgoal//
        return personalGoal;
    }
    public void setCommonGoal(){
        commonGoal1 = new CommonGoal();
        commonGoal1 = livingRoom.setCommonGoal1();
        commonGoal2 = new CommonGoal();
        commonGoal2 = livingRoom.setCommonGoal2();
    }
    public void insertTile(int x,int y) throws NotValidCoordinate,Notavailable{
        //metodo che gestisce inserimento in libreria
    }
}




public class Shelf {
    private ItemTile[][] structure;
    private structure = new ItemTile[6][5];
    public boolean getTile(int x,int y){
        //metodo che non serve probabilmente//
    }
    public ItemTile[][] getStructure(){
        return structure;
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
        flag = 0;
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



public class GameBoard {
    private ItemBag bag;
    private LivingRoom livingRoom;

    private PlayableItemTile nextInGameTile;//é la tessera "da mettere in gioco" ovvero quella che dalla bag sta venendo piazzata sulla plancia

    //servono per regolare correttamente le adiacenze
    private int firstX;
    private int firstY;

    private List<PlayableItemTile> toPlayerTiles;//sono le tessere che il giocatore ha raccolto dalla plancia, forse si può fare meglio

    public GameBoard(){
        this.getItemBag();
        this.getLivingRoom(playerNum); //sarà fornito dal GameModel, DA FARE);
        toPlayerTiles = new ArrayList<PlayableItemTile>();
    }
    public void getItemBag(){ //genera la ItemBag a ogni inizio partita
        ItemBag helperBag = new ItemBag();
        helperBag.putTiles();
        this.bag = helperBag;
    }

    public void getLivingRoom(int playerNum){
        LivingRoom helperLivingRoom = new LivingRoom();
        helperLivingRoom.createGameTable(playerNum);
        this.livingRoom = helperLivingRoom;
    }

    public void fillLivingRoom(){//riempie la LivingRoom di tessere usando getNextInGameTile e putNextInGameTile
        boolean isVoid;

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                isVoid = livingRoom.searchVoid(i,j);
                if(isVoid){
                    getNextInGameTile();
                    livingRoom.fillVoid(i, j, nextInGameTile);
                }
            }
        }
    }

    public void getNextInGameTile(){
        this.nextInGameTile = bag.randPickTile();
    }

    public boolean pickedTilesNum() {//tiene conto del numero d' ItemTiles pescate ogni turno
        return toPlayerTiles.size() < 3;
    }

    //Check sulle tessere adiacenti già fatto, getToPlayerTile deve controllare al massimo
    //per TRE volte che la tessera che l'utente vuole sia disponibile.
    //getToPlayerTile riceverà dal GameBoard (e in principio quindi dal Player) le coordinate
    //della tessera da raccogliere dalla LivingRoom
    public void getToPlayerFirstTile(int x, int y) throws UnavailableTileException{ //prende tessera 1 dalla LivingRoom

        boolean isAvailable = livingRoom.isTileAvailable(x, y);
        //questo check si focalizza sulla disponibilità della tessera che l'utente vuole
        if(isAvailable) {
            toPlayerTiles.add(livingRoom.pickTile(x, y));
            livingRoom.updateAdjacentAvailabilityV1(x, y);
            firstX = x;
            firstY = y;
        }else{
            throw new UnavailableTileException();
        }
    }
    public void getToPlayerAnotherTile(int x, int y) throws UnavailableTileException{ //prende tessera 2/3 dalla LivingRoom

        boolean isAvailable = livingRoom.isTileAvailable(x, y);
        //questo check si focalizza sulla disponibilità della tessera che l'utente vuole
        if(isAvailable) {
            toPlayerTiles.add(livingRoom.pickTile(x, y));
            livingRoom.updateAdjacentAvailabilityV2(x, y, this.firstX, this.firstY);
        }else{
            throw new UnavailableTileException();
        }
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

public class LivingRoom {

    private ItemTile[][] gameTable;
    private CommonGoal commonGoal1;
    private CommonGoal commonGoal2;
    private List<ScoringTokensType> tokenList;

    public void createGameTable(int playerNum) {
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
    public List<ScoringTokensType> setTokenList(){
        tokenList = new ArrayList();

    }
    public List<ScoringTokensType> getTokenList(){
        return tokenList;

    }


    public CommonGoal pickCommonGoal() {
        //metodo che instanzia ed estrae common goal
    }
    public CommonGoal setCommonGoal1(){


        return commonGoal1;
    }
    public CommonGoal setCommonGoal2(){
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

public class CommonGoal {
    CommonGoalType commonGoalType;
    private List<ScoringTokensType> token_list;
    private RuleCommonGoal ruleCommonGoal;
    private LivingRoom livingRoom;

    public RuleCommonGoal setRC(RuleCommonGoal ruleCommonGoal){
        this.ruleCommonGoal=ruleCommonGoal;
    }

    public LivingRoom setLivingRoom(LivingRoom livingRoom){
        this.livingRoom=livingRoom;
    }

    public List<ScoringTokensType> setTokenList(){
        this.token_list = livingRoom.getTokenList();
    }



    public void setTokens(int playersNumber){
        if(playersNumber==2){
            token_list.add('TOKEN4');
            token_list.add('TOKEN8');
        }
        if(playersNumber==3){
            token_list.add('TOKEN4');
            token_list.add('TOKEN6')
            token_list.add('TOKEN8');
        }
        if(playersNumber==4) {
            token_list.add('TOKEN2')
            token_list.add('TOKEN4');
            token_list.add('TOKEN6')
            token_list.add('TOKEN8');

        }
    }


    public boolean checkGoal() {
        switch (commonGoalType) {
            case COMMONGOAL02:
                return ruleCommonGoal.checkCorner();
                break;
            case COMMONGOAL01:
                return ruleCommonGoal.checkSixCouples();
                break;
            case CommonGOAL03:
                return ruleCommonGoal.checkFourGroups();
                break;
            case COMMONGOAL04:
                return ruleCommonGoal.checkSquare();
                break;
            case COMMONGOAL05:
                return ruleCommonGoal.CheckColumn1();
                break;
            case COMMONGOAL06:
                return ruleCommonGoal.checkEight();
                break;
                case COMMONGOAL07:
                return ruleCommonGoal.checkDiagonal();
            break;
            case COMMONGOAL08:
                return ruleCommonGoal.CheckLine1();
            break;
            case COMMONGOAL09:
                return ruleCommonGoal.CheckColumn2();
            break;
            case COMMONGOAL10:
                return ruleCommonGoal.CheckLine2();
            break;
            case COMMONGOAL11:
                return ruleCommonGoal.checkCrux();
            break;
            case COMMONGOAL12:
                return ruleCommonGoal.checkStair();
            break;
        }
        }
    }

    public int getPoint(){

        public final int i=0;
        if() // metodo che gestisce lista dei punti token sopra
            // le common goal e quando uno ottiene per primo il token prende i punti e si toglie token dalla lisa



            if(token_card.is){



            }
    }}


public class RuleCommonGoal {
    private Shelf myShelf;
    private ItemTile[][] structure;

    public void setMyShelf(Shelf myShelf){
        this.myShelf = myShelf;

    }
    public ItemTile[][] setStructure(){
        structure= myShelf.getStructure();

    }


    public boolean checkCorner(){
        return structure[0][0] != null && structure[0][0].equals(structure[0][4]) && structure[0][0].equals(structure[5][0]) && structure[0][0].equals(structure[5][4]);
    }

    public boolean checkSixCouples(){


    }




    public boolean checkFourGroups(){



    }
    public boolean checkSquare(){




    }
    public boolean checkDiagonal(){
        int x=1;
        for(int i=0;i<=3;i++){
            if(structure[i][i]!=null&&structure[i+1][i+1]!=null&&
               !(structure[i][i].getColour().equals(structure[i+1][i+1].getColour()))){
                x=0;
                break;
            }
        }
        for(int i=1;i<=4;i++){
            if(structure[i][i]!=null&&structure[i+1][i+1]!=null&&
               !(structure[i][i].getColour().equals(structure[i+1][i+1].getColour()))) {
                x=0;
                break;
            }
        }
        for(int i=1;i<=3;i++){
            if(structure[i][i+3]!=structure[i+1][i+2]) {           //devo sistemare antidiagonale con altro for annidato o con una variabile ausiliare
                x=0;
                break;
            }}
        for(int i=0;i<=3;i++){
            if(structure[i][i]!=structure[i+1][i+1]) {
                x=0;
                break;
            }}
    if(x==1) {return true};
    else return false;
    }
    public boolean checkCrux(){
        for (int i = 1; i <= 4; i++){
            for (int j = 1; j <= 3; j++) {
                if (structure[i][j]!=null&&structure[i-1][j-1]!=null&&
                    structure[i+1][j+1]!=null&&structure[i+1][j-1]!=null&&structure[i-1][j+1]!=null&&
                    structure[i][j].getColour().equals(structure[i-1][j-1].getColour())&&
                    structure[i][j].getColour().equals(structure[i+1][j+1].getColour())&&
                    structure[i][j].getColour().equals(structure[i+1][j-1].getColour())&&
                    structure[i][j].getColour().equals(structure[i-1][j+1].getColour())
                    )
                    return true;}}
        return false;
    }

    public boolean checkStair(){






    return false;
    }
    public boolean CheckColumn1(){



    }
    public boolean CheckColumn2(){



    }
    public boolean CheckLine1(){



    }
    public boolean CheckLine2(){



    }




    public boolean checkEight(){
        for(int i=0;i< structure.length;i++) {
            for (int j = 0; j < structure.length; j++) {
                int count = 0;
                if (structure[i][j] != null) {
                    Colour value = structure[i][j].getColour();
                    for (int k = 0; k < structure.length; k++) {
                        for (int l = 0; l < structure.length; l++) {
                            if (structure[k][l] != null && structure[k][l].getColour().equals(value)) {
                                count++;
                            }
                            if (count >= 8) {
                                return true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}









<<<<<<< HEAD








public class ItemBag(){

    private List<PlayableItemTile> bag;



    public void putTiles(){
        bag = new ArrayList<PlayableItemTile>();
        PlayableItemTileFactory factory = new PlayableItemTileFactory();

        for(int i=0; i<132; i++){
            if(i<22){
                try {
                    bag.add(factory.createPlayableItemTile("GREEN", i + 1));
                }catch(InvalidPlayableItemTileColourException exc){

                }
            }else if(i<44){
                try{
                    bag.add(factory.createPlayableItemTile("WHITE", i+1));
                }catch(InvalidPlayableItemTileColourException exc){

                }
            }else if(i<66){
                try{
                    bag.add(factory.createPlayableItemTile("YELLOW", i+1));
                }catch(InvalidPlayableItemTileColourException exc){

                }
            }else if(i<88){
                try{
                    bag.add(factory.createPlayableItemTile("BLUE", i+1));
                }catch(InvalidPlayableItemTileColourException exc){

                }
            }else if(i<110){
                try{
                    bag.add(factory.createPlayableItemTile("CYAN", i+1));
                }catch(InvalidPlayableItemTileColourException exc){

                }
            }else if(i<132){
                try{
                    bag.add(factory.createPlayableItemTile("PINK", i+1));
                }catch(InvalidPlayableItemTileColourException exc){

                }
            }
        }
    }

    //dovrebbe effettuare pick random di ItemTile da ItemBag
    public PlayableItemTile randPickTile(){
        final Random RAND = new Random();
        int index = RAND.nextInt(bag.size());
        PlayableItemTile helperTile = bag.get(index);
        bag.remove(index);
        return helperTile;
    }
}

public abstract class ItemTile{
    private Colour colour;
    private int idCode;
    private boolean availability;
    private boolean adjacency;

    public abstract Colour getColour();
    public abstract int getIdCode();
    public abstract boolean nullDetection();
    public abstract boolean isAvailable();

    public abstract void makeAvailable();

    public abstract void makeUnavailable();

    public abstract void setAdjacency();
    public abstract boolean getAdjacency();

    public abstract void resetAdjacency();
}

public class NullItemTile extends ItemTile{
    private Colour colour;
    private int idCode;
    private boolean availability;
    private boolean adjacency;

    public NullItemTile(){
        colour = Colour.VOID;
        availability = false;
        adjacency = false;
        idCode = 0;
    }

    @java.lang.Override
    public org.example.Colour getColour() {
        return colour;
    }

    @Override
    public int getIdCode() {
        return idCode;
    }

    public void makeAvailable(){}

    public void makeUnavailable() {}

    @java.lang.Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public boolean nullDetection() {
        return true;
    }
    public void setAdjacency(){};
    public boolean getAdjacency(){
        return adjacency;
    };
    @Override
    public void resetAdjacency() {
        adjacency = false;
    }
}
public class PlayableItemTile extends ItemTile {
    private Colour colour;
    private int idCode;
    private boolean availability;
    private boolean adjacency=false;

    @java.lang.Override
    public org.example.Colour getColour() {
        return colour;
    }

    @Override
    public int getIdCode() {
        return idCode;
    }

    @Override
    public boolean nullDetection() {
        return false;
    }

    @Override
    public boolean isAvailable() {
        return availability;
    }

    @Override
    public void makeAvailable() {
        availability = true;
    }

    @Override
    public void makeUnavailable() {
        availability = false;
    }

    @Override
    public boolean getAdjacency() {
        return adjacency;
    }

    @Override
    public void setAdjacency() {
        adjacency = true;
    }

    @Override
    public void resetAdjacency() {
        adjacency = false;
    }

    public PlayableItemTile(String colour, int id){
        if(colour.equals("GREEN")) {
            Colour helperColour = Colour.GREEN;
            this.colour = helperColour;
            this.idCode = id;
        } else if (colour.equals("WHITE")) {
            Colour helperColour = Colour.WHITE;
            this.colour = helperColour;
            this.idCode = id;
        } else if (colour.equals("YELLOW")) {
            Colour helperColour = Colour.YELLOW;
            this.colour = helperColour;
            this.idCode = id;
        } else if (colour.equals("BLUE")) {
            Colour helperColour = Colour.BLUE;
            this.colour = helperColour;
            this.idCode = id;
        } else if (colour.equals("CYAN")) {
            Colour helperColour = Colour.CYAN;
            this.colour = helperColour;
            this.idCode = id;
        } else {
            Colour helperColour = Colour.PINK;
            this.colour = helperColour;
            this.idCode = id;
        }
    }
}



enum ScoringTokensType {
    Token_2,
    Token_4,
    Token_6,
    Token_8
}


enum CommonGoalType {
    COMMONGOAL01,
    COMMONGOAL02,
    CommonGOAL03,
    COMMONGOAL04,
    COMMONGOAL05,
    COMMONGOAL06,
    COMMONGOAL07,
    COMMONGOAL08,
    COMMONGOAL09,
    COMMONGOAL10,
    COMMONGOAL11,
    COMMONGOAL12,
}

<<<<<<< HEAD


abstract class LivingRoomFactory{
    abstract public ItemTile[][] create();
}

public class TwoLivingRoomFactory extends LivingRoomFactory{
    @Override
    public ItemTile[][] create() {
        ItemTile[][] helperTable = new ItemTile[9][9];
        NullItemTile nullTile = new NullItemTile();

        for(int i=0; i<9; i++){
            //metto null Tiles da [0][0] a [0][8]
            for(int j=0; j<9 && i==0; j++) {
                helperTable[i][j] = nullTile;
            }
            //metto null Tiles da [1][0] a [1][2] e da [1][5] a [1][8]
            for(int j=0; j<9 && i==1; j++) {
                if (j < 3 || j > 4) {
                    helperTable[i][j] = nullTile;
                }
            }

            //metto null Tiles da [7][0] a [7][3] e da [7][6] a [7][8]
            for(int j=0; j<9 && i==7; j++) {
                if (j < 4 || j > 5) {
                    helperTable[i][j] = nullTile;
                }
            }
            //metto null Tiles da [2][0] a [2][2] e da [2][6] a [2][8]
            //metto null Tiles da [6][0] a [6][2] e da [6][6] a [6][8]
            for(int j=0; j<9 && (i==2 || i==6); j++) {
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
            for(int j=0; j<9 && i==8; j++) {
                helperTable[i][j] = nullTile;
            }
        }
        return helperTable;
    }
}

public class ThreeLivingRoomFactory extends LivingRoomFactory{
    @Override
    public ItemTile[][] create() {
        ItemTile[][] helperTable = new ItemTile[9][9];
        NullItemTile nullTile = new NullItemTile();

        for(int i=0; i<9; i++){
            //metto null Tiles da [0][0] a [0][2] e da [0][4] a [0][8]
            for(int j=0; j<9 && i==0; j++) {
                if (j != 3) {
                    helperTable[i][j] = nullTile;
                }
            }
            //metto null Tiles da [1][0] a [1][2] e da [1][5] a [1][8]
            for(int j=0; j<9 && i==1; j++) {
                if (j < 3 || j > 4) {
                    helperTable[i][j] = nullTile;
                }
            }

            //metto null Tiles da [7][0] a [7][3] e da [7][6] a [7][8]
            for(int j=0; j<9 && i==7; j++) {
                if (j < 4 || j > 5) {
                    helperTable[i][j] = nullTile;
                }
            }
            //metto null Tiles da [2][0] a [2][1] e da [2][7] a [2][8]
            //metto null Tiles da [6][0] a [6][1] e da [6][7] a [6][8]
            for(int j=0; j<9 && (i==2 || i==6); j++) {
                if (j < 2 || j > 6) {
                    helperTable[i][j] = nullTile;
                }
            }
            //metto null Tile in [3][0] e [3][1]
            helperTable[3][0] = nullTile;
            helperTable[3][1] = nullTile;

            //metto null Tile in [4][0] e [4][8]
            helperTable[4][0] = nullTile;
            helperTable[4][8] = nullTile;

            //metto null Tiles da [5][8] e [5][7]
            helperTable[5][7] = nullTile;
            helperTable[5][8] = nullTile;


            //metto null Tiles da [8][0] a [8][4] e da [8][6] a [8][8]
            for(int j=0; j<9 && i==8; j++) {
                if (j != 5) {
                    helperTable[i][j] = nullTile;
                }
            }
        }

        return helperTable;
    }
}

public class FourLivingRoomFactory extends LivingRoomFactory{
    @Override
    public ItemTile[][] create() {
        ItemTile[][] helperTable = new ItemTile[9][9];
        NullItemTile nullTile = new NullItemTile();

        for(int i=0; i<9; i++){
            //metto null Tiles da [0][0] a [0][2] e da [0][5] a [0][8]
            for(int j=0; j<9 && i==0; j++) {
                if (j < 3 || j > 4) {
                    helperTable[i][j] = nullTile;
                }
            }
            //metto null Tiles da [1][0] a [1][2] e da [1][6] a [1][8]
            //metto null Tiles da [7][0] a [7][2] e da [7][6] a [7][8]
            for(int j=0; j<9 && (i==1 || i==7); j++) {
                if (j < 3 || j > 5) {
                    helperTable[i][j] = nullTile;
                }
            }
            //metto null Tiles da [2][0] a [2][1] e da [2][7] a [2][8]
            //metto null Tiles da [6][0] a [6][1] e da [6][7] a [6][8]
            for(int j=0; j<9 && (i==2 || i==6); j++) {
                if (j < 2 || j > 6) {
                    helperTable[i][j] = nullTile;
                }
            }
            //metto null Tile in [3][0]
            helperTable[3][0] = nullTile;

            //metto null Tiles da [5][8]
            helperTable[5][8] = nullTile;

            //metto null Tiles da [8][0] a [8][3] e da [8][6] a [8][8]
            for(int j=0; j<9 && i==8; j++) {
                if (j < 4 || j > 5) {
                    helperTable[i][j] = nullTile;
                }
            }
        }

        return helperTable;
    }
}



public class PlayableItemTileFactory(){
    public PlayableItemTile createPlayableItemTile(String colour, int id) throws InvalidPlayableItemTileColourException{
        if(colour.equals("GREEN") || colour.equals("WHITE") || colour.equals("YELLOW") || colour.equals("BLUE") || colour.equals("CYAN") || colour.equals("PINK")){
            PlayableItemTile helperTile = new PlayableItemTile(colour, id);
            return helperTile;
        }else{
            throw new InvalidPlayableItemTileColourException();
        }
    }
}

enum Colour{GREEN, WHITE, YELLOW, BLUE, CYAN, PINK, VOID
}

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}

public class PlayableItemTileInsertionException extends Exception {
}

public class InvalidPlayableItemTileColourException extends Exception {
}

public class MaxTilesPickedException extends Exception {
}

public class UnavailableTileException extends Exception {
}

public class NoAvailableTilesException extends Exception {
}