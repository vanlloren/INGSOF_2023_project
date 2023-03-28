package org.example;
package org.example;

import java.awt.*;
import java.util.*;
import java.util.List;

public class GameModel {
    private String chairOwner;
    private int playersNumber;
    private String currPlayer;
    private String matchWinner;
    private GameBoard myShelfie;
    private ArrayList<Player> playersInGame = new ArrayList<Player>();
    private Player[] turnOrder;
    private Player[] has;

    // VA SPOSTATO NEL CONTROLLER!!
    public void launchGame_Myshelfie(){
        // va scritta tutta la partita con lo svolgimento//







    }
    public int getPlayersNumber(){
        return this.playersNumber;
    }
    public void setPlayersNumber(int playersNumber){ // inserito dal controller
        this.playersNumber = playersNumber;

    }
//questo metodo verrà chiamato dal controller a cui delego di usare un proprio metodo per decidere random il turno dei giocatori
    public void setChairOwner(Player player ) {
       this.chairOwner = player;
    }

    public ArrayList<Player> getPlayersInGame() {
        return playersInGame;
    }
    public void setPlayersInGame(){ //metodo chiamato dal controller che tramite la view riceverà i giocatori e dovra poi chiamare
        // questo metodo per aggiornare la logica di dati del gioco
        //metodo che inserisce i giocatori quando si collegano va implementato


    }







    public void setGameBoard() {
        myShelfie = new GameBoard();
    }
    public GameBoard getMyShelfie(){
        return myShelfie;
    }

    public void setTurn() { // metodo ch va messo nel controller che va a modificare il chairowner e quindi quale player sta giocando
        //in questo momento

    }
    public boolean haslaunchTerminate() { //verra chiamato dal controller per poi dire che da qui comincia l'ultimo turno di gioco
        points++;
        return true;

    }
    public List<Player> getTurnOrder(){  // metodo chiamato dal controller
        // per ottenere la lista dei giocatori attuale e modificarla chiamando poi il set
     return this.playersInGame;  /// RIGUARDARE ASSIEME QUESTI METODI PER CAPIRE COME IMPOSTARE I TURNI E CHI HA LA SEDIA
    }


}

public class Player {
    private String nickname;
    private int points;
    private Shelf personalShelf = new Shelf();
    private PersonalGoal personalGoal;
    private CommonGoal commonGoal1 = new CommonGoal();
    private CommonGoal commonGoal2 = new CommonGoal();
    private LivingRoom livingRoom;
    private boolean hasCommonGoal1;
    private boolean hasCommonGoal2;
    private boolean hasPersonalGoal;

    // questi tre metodi vengono chiamati dal controller quando il player ha soddisfatto gli obbiettivi carte
    public void setStatusCommonGoal1(){
        this.hasCommonGoal1 = true;
    }

    public void setStatusCommonGoal2(){
        this.hasCommonGoal2 = true;
    }

    public void setStatusPersonalGoal(){
        this.hasPersonalGoal = true;
    }

  //metodo che verra chiamato dal controller il quale preventivamente crea una living room per i giocatori che si sono collegati e assegna a tutti la STESSA LIVING ROOM
    public void setLivingRoom(LivingRoom livingRoom){ // metodo importante che serve ad assegnare ai giocatori la stessa plancia di gioco nel caso ci siano partite multiple da gestire
        this.livingRoom= livingRoom;



    }

    public Shelf getPersonalShelf(){ // metodo che verrà chiamato dal controller per poter accedere alla libreria
        //personale del giocatore e avviare tutti i check che controllano se gli obbiettivi sono stati soddisfatti
        return this.personalShelf;
    }

    public String getNickname(){
        return this.nickname;}

    public void setNickname (String nickname){ //il controller chiama questo metodo per settare nicknames
        this.nickname= nickname;

    }


// va nel controller STRUCTURE VA PASSATO DA CONTROLLER
    public void calculatePoint(ItemTile[][] structure){ // con hascommongoal controllo che il giocatore non abbia gia raggiunto l'obiettivo
        if(!hasCommonGoal1&&commonGoal1.checkGoal(structure)){

            this.points=this.points+commonGoal1.getPoint();
            hasCommonGoal1=true;

        }

        if(!hasCommonGoal2&&commonGoal2.checkGoal(structure)) {
            this.points = this.points + commonGoal2.getPoint();
            hasCommonGoal2 = true;
            }

        if(personalGoal.checkgoal()){
            this.points=this.points+personalGoal.getPoint();


            }
    }

    public void setPersonalGoal(){ // questo viene propriamente inizializzato dal player mentre i commongoal appartengono alla living room
        personalGoal = new PersonalGoal();
        //funzione che assegna personalgoal//
        return personalGoal;
    }

    public void setCommonGoals(){ //chiamato da controller a cascata quando chiamo il generatore di common goal dalla living room
        this.commonGoal1 = livingRoom.getCommonGoal1();
        this.commonGoal2 = livingRoom.getCommonGoal2();
    }


    //controller
    public void insertTile(int x,int y,ItemTile tile) throws NotValidCoordinate,Notavailable{ //parametri passati dal controller;chiamato dal controller quando il player da le istruzioni in cui dice dove mettere l item sulla shelf
       this.personalShelf.putTile(x,y,tile,..) //chiedi alfi come funziona questo metodo
        //metodo che gestisce inserimento in libreria
    }
}


//-------------------------------------------------

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
                if (structure[i][Y] == null) {
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
        if (availability==true) {
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
                System.out.println("You enough space to put tiles in the shelf")
                return false;
            }
        }
        System.out.println("you have completed your shelf"); // i due print vanno visti meglio perchè devono finire nella view non dovrebbero stare nel model
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

    //MODEL
    public void setItemBag(){ //genera la ItemBag a ogni inizio partita ; chiamato dal controller
        ItemBag helperBag = new ItemBag();
        helperBag.putTiles();
        this.bag = helperBag;
    }

    //MODEL
    public void setLivingRoom(int playerNum){ // il parametro viene passato dal controller
        LivingRoom helperLivingRoom = new LivingRoom();
        helperLivingRoom.createGameTable(playerNum);
        this.livingRoom = helperLivingRoom;
    }

    //MODEL
   // metodo che va messo nel controller perchè è un metodo che agisce
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
//va messo nel controller
    public void getNextInGameTile(){
        this.nextInGameTile = bag.randPickTile();
    }

    //CONTROLLER
    public boolean pickedTilesNum() {//tiene conto del numero d' ItemTiles pescate ogni turno
        return toPlayerTiles.size() < 3;
    }

    //Check sulle tessere adiacenti già fatto, getToPlayerTile deve controllare al massimo
    //per TRE volte che la tessera che l'utente vuole sia disponibile.
    //getToPlayerTile riceverà dal GameBoard (e in principio quindi dal Player) le coordinate
    //della tessera da raccogliere dalla LivingRoom

    //MODEL
    public void getToPlayerFirstTile(int x, int y) throws UnavailableTileException{ //prende tessera 1 dalla LivingRoom

        toPlayerTiles = new ArrayList<PlayableItemTile>();
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

    //MODEL
    public void getToPlayerAnotherTile(int x, int y) throws UnavailableTileException{ //prende tessera 2/3 dalla LivingRoom
        toPlayerTiles = new ArrayList<PlayableItemTile>();
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


    public void setCommonGoal1(CommonGoal commonGoal1){ // delego al controller come scegliere random i commongoal e poi uso qusti due metodi per settare alla living room quali sono i commongoal
     this.commonGoal1 = commonGoal1;
    }
    public void setCommonGoal2(CommonGoal commonGoal2){
        this.commonGoal2 = commonGoal2;
    }

    public CommonGoal getCommonGoal1() { //
        return commonGoal1;
    }

    public CommonGoal getCommonGoal2() {
        return commonGoal2;
    }

    //MODEL
    public PlayableItemTile pickTile(int x, int y) {//questo metodo permette alla LivingRoom di passare una sua tessera alla GameBoard
        PlayableItemTile helperTile;
        helperTile = (PlayableItemTile) gameTable[x][y];

        gameTable[x][y] = null;

        return helperTile;
    }

    //MODEL
    public boolean searchVoid(int x, int y){
        if(gameTable[x][y] != null){
            return false;
        }
        return true;
    }

    //MODEL
    public void fillVoid(int x, int y, PlayableItemTile tile){
        gameTable[x][y] = tile;
    }

    //CONTROLLER
    public boolean isTileAvailable(int x, int y) {//verifica se la singola Tile nella LivingRoom alle
        // coordinate x e y è disponibile per essere presa dal player

        return gameTable[x][y].isAvailable();
    }

    //CONTROLLER
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

    //CONTROLLER
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
public class rulePersonalGoal() {
    public boolean checkFourCard(int[][] MyShelf) {
    }

    public boolean checkFiveCard(int[][] MyShelf) {
    }

    public boolean checkSixCard(int[][] MyShelf) {
    }

    public boolean checkThreeCard(int[][] MyShelf) {
        int count = 0;
        int i, j, x, y;
        int item;

        for (i = 0; i <= MyShelf.length; i++) {
            for (j = 0; j <= MyShelf.length; j++) {
                item = MyShelf[i][j];
                if (i == 0 && j == 0) {
                    for (x = i + 1; x <= i + 2; x++) {
                        for (y = j + 1; y <= j + 2; j++) {
                            if (MyShelf[i][y] == item && MyShelf[i][y + 1] == item) {
                                return true;
                            }
                            if (MyShelf[i][y] == item && MyShelf[x][y] == item) {
                                return true;
                            }
                            if (MyShelf[i][y] == item && MyShelf[x][j] == item) {
                                return true;
                            }
                            if (MyShelf[x][j] == item && MyShelf[x][y] == item) {
                                return true;
                            }
                            if (MyShelf[x][j] == item && MyShelf[x + 1][j] == item) {
                                return true;
                            }
                        }
                    }
                }
                if (i == 0 && j =! 0) {
                    for (x = i + 1; x <= i + 2; x++) {
                        for (y = j - 2; y <= j + 2; y++) {
                            if ((y == j - 2 || y == j + 1) && MyShelf[i][y] == item && MyShelf[i][y + 1] == item) {
                                return true;
                            }
                            if (y == j - 1 && MyShelf[i][y] == item && MyShelf[i][y + 2] == item) {
                                return true;
                            }
                            if ((y == j - 1 && x == i + 1) || (y == j + 1 && x == i + 1) && MyShelf[i][y] == item && MyShelf[x][y] == item) {
                                return true;
                            }
                            if (x == i + 1 && MyShelf[x][j] == item && MyShelf[x + 1][j] == item) {
                                return true;
                            }
                            if ((x == i + 1 && y == j - 1) || (x == i + 1 && y == j + 1) && MyShelf[x][y] == item && MyShelf[x][j] == item) {
                                return true;
                            }

                        }
                    }
                }
                if (i != 0 && j == 0) {
                    for (x = i - 2; x <= i + 2; x++) {
                        for (y = j + 1; y <= j + 2; y++) {
                            if ((x == i - 2 || x == i + 1) && MyShelf[x][j] == item && MyShelf[x + 1][j] == item) {
                                return true;
                            }
                            if (x == i - 1 && MyShelf[x][j] == item && MyShelf[x + 2][j] == item) {
                                return true;
                            }
                            if (y == j + 1 && MyShelf[i][y] == item && MyShelf[i][y + 1] == item) {
                                return true;
                            }
                            if ((y == j + 1 && x == i + 1) && (MyShelf[i][y] == item && MyShelf[x][y] == item) || (MyShelf[x][j] == item && MyShelf[x][y] == item)) {
                                return true;
                            }
                            if ((x == i - 1 && y == j + 1) && (MyShelf[x][y] == item && MyShelf[x][j] == item) || (MyShelf[x][y] == item && MyShelf[i][y] == item)) {
                                return true;
                            }
                        }
                    }
                }
                if (i != 0 && j != 0) {
                    for (x = i - 2; x <= i + 2; x++) {
                        for (y = j - 2; y <= j + 2; y++) {

                        }
                    }
                }
            }
        }
    }
}

public class PersonalGoal() {
    public int Personal_goal_1(int[][] MyShelf) {
        int i, j;
        int count = 0;
        if (i == 0 && j == 0) {
            if ( int[i][j]==Colour.PINK){

                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 2 && j == 0) {
            if ( int[i][j]==Colour.BLUE){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 4 && j == 1) {
            if ( int[i][j]==Colour.GREEN){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 3 && j == 2) {
            if ( int[i][j]==Colour.WHITE){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 1 && j == 3) {
            if ( int[i][j]==Colour.YELLOW){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 2 && j == 5) {
            if ( int[i][j]==Colour.CYAN){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        return count;
    }

    public int Personal_goal_2(int[][] MyShelf) {
        int count = 0;
        int i, j;
        if (i == 1 && j == 1) {
            if ( int[i][j]==Colour.PINK){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 0 && j == 2) {
            if ( int[i][j]==Colour.GREEN){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 2 && j == 2) {
            if ( int[i][j]==Colour.YELLOW){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 4 && j == 3) {
            if ( int[i][j]==Colour.WHITE){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 3 && j == 4) {
            if ( int[i][j]==Colour.CYAN){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 4 && j == 5) {
            if ( int[i][j]==Colour.BLUE){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        return count;
    }

    public int Personal_goal_3(int[][] MyShelf) {
        int count = 0;
        int i, j;
        if (i == 0 && j == 1) {
            if ( int[i][j]==Colour.BLUE){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 3 && j == 1) {
            if ( int[i][j]==Colour.YELLOW){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }

        if (i == 2 && j == 2) {
            if ( int[i][j]==Colour.PINK){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 1 && j == 3) {
            if ( int[i][j]==Colour.GREEN){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }

        if (i == 4 && j == 3) {
            if ( int[i][j]==Colour.CYAN){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 0 && j == 5) {
            if ( int[i][j]==Colour.WHITE){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        return count;
    }

    public int Personal_goal_4(int[][] MyShelf) {
        int count = 0;
        int i, j;
        if (i == 4 && j == 0) {
            if ( int[i][j]==Colour.YELLOW){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 0 && j == 2) {
            if ( int[i][j]==Colour.CYAN){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 2 && j == 2) {
            if ( int[i][j]==Colour.BLUE){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 3 && j == 3) {
            if ( int[i][j]==Colour.PINK){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 1 && j == 4) {
            if ( int[i][j]==Colour.WHITE){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 2 && j == 4) {
            if ( int[i][j]==Colour.GREEN){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        return count;
    }

    public int Personal_goal_5(int[][] MyShelf) {
        int count = 0;
        int i, j;
        if (i == 1 && j == 1) {
            if ( int[i][j]==Colour.CYAN){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 1 && j == 3) {
            if ( int[i][j]==Colour.BLUE){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 2 && j == 3) {
            if ( int[i][j]==Colour.WHITE){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 4 && j == 4) {
            if ( int[i][j]==Colour.PINK){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 0 && j == 5) {
            if ( int[i][j]==Colour.YELLOW){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 3 && j == 5) {
            if ( int[i][j]==Colour.GREEN){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        return count;
    }

    public int Personal_goal_6(int[][] MyShelf) {
        int count = 0;
        int i, j;
        if (i == 2 && j == 0) {
            if ( int[i][j]==Colour.CYAN){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 4 && j == 0) {
            if ( int[i][j]==Colour.GREEN){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 3 && j == 2) {
            if ( int[i][j]==Colour.WHITE){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 2 && j == 4) {
            if ( int[i][j]==Colour.YELLOW){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 3 && j == 4) {
            if ( int[i][j]==Colour.BLUE){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 0 && j == 5) {
            if ( int[i][j]==Colour.PINK){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        return count;
    }

    public int Persoal_goal_7(int[][] MyShelf) {
        int count = 0;
        int i, j;
        if (i == 0 && j == 0) {
            if ( int[i][j]==Color.GREEN){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 3 && j == 1) {
            if ( int[i][j]==Colour.BLUE){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 1 && j == 2) {
            if ( int[i][j]==Colour.PINK){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 0 && j == 3) {
            if ( int[i][j]==Colour.CYAN){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 4 && j == 4) {
            if ( int[i][j]==Colour.YELLOW){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 2 && j == 5) {
            if ( int[i][j]==Colour.WHITE){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        return count;
    }


    public int Personal_goal_8(int[][] MyShelf) {
        int count = 0;
        int i, j;
        if (i == 4 && j == 0) {
            if ( int[i][j]==Colour.BLUE){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 1 && j == 1) {
            if ( int[i][j]==Colour.GREEN){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 2 && j == 2) {
            if ( int[i][j]==Colour.CYAN){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 0 && j == 3) {
            if ( int[i][j]==Colour.PINK){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 3 && j == 4) {
            if ( int[i][j]==Colour.WHITE){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 3 && j == 5) {
            if ( int[i][j]==Colour.YELLOW){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        return count;
    }

    public int Personal_goal_9(int[][] MyShelf) {
        int count = 0;
        int i, j;
        if (i == 2 && j == 0) {
            if ( int[i][j]==Colour.YELLOW){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 2 && j == 2) {
            if ( int[i][j]==Colour.GREEN){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 4 && j == 3) {
            if ( int[i][j]==Colour.WHITE){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 2 && j == 4) {
            if ( int[i][j]==Colour.CYAN){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 4 && j == 4) {
            if ( int[i][j]==Colour.PINK){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 0 && j == 5) {
            if ( int[i][j]==Colour.BLUE){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        return count;
    }

    public int Personal_goal_10(int[][] MyShelf) {
        int count = 0;
        int i, j;
        if (i == 4 && j == 0) {
            if ( int[i][j]==Colour.CYAN){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 1 && j == 1) {
            if ( int[i][j]==Colour.YELLOW){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 0 && j == 2) {
            if ( int[i][j]==Colour.WHITE){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 3 && j == 3) {
            if ( int[i][j]==Colour.GREEN){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 1 && j == 4) {
            if ( int[i][j]==Colour.BLUE){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 3 && j == 5) {
            int([i][j] == Colour.PINK){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        return count;
    }

    public int Personal_goal_11(int[][] MyShelf) {
        int count = 0;
        int i, j;
        if (i == 2 && j == 0) {
            if ( int[i][j]==Colour.PINK){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 1 && j == 1) {
            if ( int[i][j]==Colour.WHITE){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 0 && j == 2) {
            if ( int[i][j]==Colour.YELLOW){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 2 && j == 3) {
            if ( int[i][j]==Colour.BLUE){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 4 && j == 4) {
            if ( int[i][j]==Color.GREEN){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 3 && j == 5) {
            if ( int[i][j]==Colour.CYAN){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        return count;
    }

    public int Personal_goal_12(int[][] MyShelf) {
        int i, j;
        int count = 0;
        if (i == 2 && j == 0) {
            if ( int[i][j]==Colour.WHITE){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 1 && j == 1) {
            if ( int[i][j]==Colour.PINK){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 2 && j == 2) {
            if ( int[i][j]==Colour.BLUE){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 3 && j == 3) {
            if ( int[i][j]==Colour.CYAN){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 4 && j == 4) {
            if ( int[i][j]==Colour.YELLOW){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        if (i == 0 && j == 5) {
            if ( int[i][j]==Colour.GREEN){
                if (count > 2 && count <= 4) {
                    count += 2;
                }
                if (count < 2) {
                    count++;
                }
                if (count > 3 && count <= 5) {
                    count += 3;
                }
            }
        }
        return count;
    }

    public void addPoint(int num, int add) {
        num = num + add;
    }


    enum personalGoalType {PERSONALGOAL1, PERSONALGOAL2, PERSONALGOAL3, PERSONALGOAL4}

    public int checkPersonalGoal() {
        int n = 0;
        int a;
        if (personalGoalType.PERSONALGOAL1) {
            if (rulePersonalGoal.checkThreeCard()) {
                addPoint(n, a = 2);
            }
        }
        if (personalGoalType.PERSONALGOAL2) {
            if (rulePersonalGoal.checkFourCard()) {
                addPoint(n, a = 1);
            }
        }
        if (personalGoalType.PERSONALGOAL3) {
            if (rulePersonalGoal.checkFiveCard()) {
                addPoint(n, a = 2);
            }
        }
        if (personalGoalType.PERSONALGOAL4) {
            if (rulePersonalGoal.checkSixCard()) {
                addPoint(n, a = 3);
            }
        }
       return n;
    }
}



public class CommonGoal {
            CommonGoalType commonGoalType;
            private ArrayList<Integer> token_list = new ArrayList<Integer>();


            public void setTokens(int playersNumber) { //parametro dato dal controller
                if (playersNumber == 2) {
                    token_list.add(4);
                    token_list.add(8);
                }
                if (playersNumber == 3) {
                    token_list.add(4);
                    token_list.add(6);
                    token_list.add(8);
                }
                if (playersNumber == 4) {
                    token_list.add(2);
                    token_list.add(4);
                    token_list.add(6);
                    token_list.add(8);
                }
            }

            //questo va messo nel controller
            public boolean checkGoal(ItemTile[][] structure) { //il parametro è gia passato quando chiamo calculatepoints
                boolean checker = false;
                switch (commonGoalType) {
                    case COMMONGOAL02:
                        checker = Algorithm.checkCorner(structure);
                        break;
                    case COMMONGOAL01:
                        checker = Algorithm.checkSixCouples(structure);
                        break;
                    case CommonGOAL03:
                        checker = Algorithm.checkFourGroups(structure);
                        break;
                    case COMMONGOAL04:
                        checker = Algorithm.checkSquare(structure);
                        break;
                    case COMMONGOAL05:
                        checker = Algorithm.CheckColumn1(structure);
                        break;
                    case COMMONGOAL06:
                        checker = Algorithm.checkEight(structure);
                        break;
                    case COMMONGOAL07:
                        checker = Algorithm.checkDiagonal(structure);
                        break;
                    case COMMONGOAL08:
                        checker = Algorithm.CheckLine1(structure);
                        break;
                    case COMMONGOAL09:
                        checker = Algorithm.CheckColumn2(structure);
                        break;
                    case COMMONGOAL10:
                        checker = Algorithm.CheckLine2(structure);
                        break;
                    case COMMONGOAL11:
                        checker = Algorithm.checkCrux(structure);
                        break;
                    case COMMONGOAL12:
                        checker = Algorithm.checkStair(structure);
                        break;
                }
                return checker;
            }


            //anche questo va nel controller
            public int getPoint() {
                int i = 0; // se ho finite le carte punteggio si assegnano zero punti
                if (0 < token_list.size()) {
                    i = token_list.get(token_list.size());
                    token_list.remove(token_list.size() - 1);
                }
                return i;
            }
        }

//questo va inserito nel controller ma devo valutare se usare classe o direttamnete un metodo con tutti gli algoritmi devo capie meglio
//lista di tutti gli algoritmi che implementano le regole delle common goal avendo a disposizione la matrice della libreria del rispettivo giocatore

// classe di supporto che ha metodi statici quindi non va instanziata per funzionare
        public class Algorithm {
            public static boolean checkCorner(ItemTile[][] structure) {
                boolean checker = false;
                if (structure[0][0] != null && structure[0][0].getColour() == structure[0][4].getColour()
                        && structure[0][0].getColour() == structure[5][0].getColour() && structure[0][0].getColour() == structure[5][4].getColour())
                    checker = true;
                return checker;

            }

            public static boolean checkSixCouples(ItemTile[][] structure) {
                Colour[][] matrix = new Colour[][];
                int count = 0;

                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (structure[i][j] != null) {
                            matrix[i][j] = structure[i][j].getColour();
                        }
                    }
                }
                //inizio a controllare quante coppie ci sono e appena ne trovo una la rendo null per evitare sovrapposizione(caso particolare forma ad L di 4 tessere uguali devo valutare la disposizione della coppia
                //in maniera tale da contare 2 coppie e non una coppia sbagliata con due caselle non adiacenti che se avessi scelto meglio avrei ottenuto due coppie
                for (int i = 5; i > 0; i--) {
                    for (int j = 0; j < 4; j++) {
                        if (matrix[i][j] != null && matrix[i][j + 1] != null &&
                                matrix[i][j + 1] == matrix[i][j]) {
                            count++;
                            matrix[i][j] = null;
                            matrix[i][j + 1] = null;
                        }
                        if (i < 5) {
                            if (matrix[i][j] != null && matrix[i + 1][j] != null &&
                                    matrix[i][j] == matrix[i + 1][j]) {
                                count++;
                                matrix[i][j] = null;
                                matrix[i + 1][j] = null;
                            }
                        }
                    }
                }
                if (count >= 6)
                    return true;
                else return false;
            }


            public static boolean checkFourGroups(ItemTile[][] structure) {
                Colour[][] matrix = new Colour[][];
                int count = 0;
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (structure[i][j] != null) {
                            matrix[i][j] = structure[i][j].getColour();
                        }
                    }
                }
                for (int i = 5; i > 0; i--) {
                    for (int j = 0; j < 2; j++) {
                        if (matrix[i][j] != null && matrix[i][j + 1] != null &&
                                matrix[i][j + 2] != null && matrix[i][j + 3] != null && matrix[i][j + 1] == matrix[i][j]
                                && matrix[i][j + 2] == matrix[i][j] && matrix[i][j + 3] == matrix[i][j]) {
                            count++;
                            matrix[i][j] = null;
                            matrix[i][j + 1] = null;
                            matrix[i][j + 2] = null;
                            matrix[i][j + 3] = null;
                        }
                        if (i < 2) {
                            if (matrix[i][j] != null && matrix[i + 1][j] != null &&
                                    matrix[i + 2][j] != null && matrix[i + 3][j] != null &&
                                    matrix[i][j] == matrix[i + 1][j] && matrix[i][j] == matrix[i + 2][j] && matrix[i][j] == matrix[i + 3][j]) {
                                count++;
                                matrix[i][j] = null;
                                matrix[i + 1][j] = null;
                                matrix[i + 2][j] = null;
                                matrix[i + 3][j] = null;
                            }
                        }
                    }
                }
                if (count >= 4)
                    return true;
                else return false;
            }

            public static boolean checkSquare(ItemTile[][] structure) {                          //per scrivere codice che controlla le 6 coppie sfrutta questo algoritmo modificando alcuni parametri
                boolean checker = false;
                for (int i = 0; i <= 4; i++) {
                    for (int j = 0; j <= 3; j++) {
                        if (structure[i][j] != null) {
                            Colour value = structure[i][j].getColour();
                            if (structure[i][j + 1] != null && structure[i + 1][j] != null && structure[i + 1][j + 1] != null && structure[i][j + 1].getColour() == value &&
                                    structure[i + 1][j].getColour() == value && structure[i +][j + 1].getColour() == value) {
                                int k = i;
                                while (k < 2) {
                                    int l = j + 2;
                                    while (l < 4) {
                                        //scrivi controlla quadrato//
                                        if () {
                                            checker = true;
                                        }

                                    }
                                }
                                k = i + 2;
                                while (k < 5) {
                                    int l = j;
                                    while (l < 5) {
                                        //uguale a commento sopra//

                                        if () {
                                            checker = true;
                                        }
                                    }

                                }

                            }

                        }
                    }
                } return checker;
            }

            public static boolean checkDiagonal(ItemTile[][] structure) {
                int x = 1;
                for (int i = 0; i <= 3; i++) {
                    if (structure[i][i] != null && structure[i + 1][i + 1] != null &&
                            !(structure[i][i].getColour().equals(structure[i + 1][i + 1].getColour()))) {
                        x = 0;
                        break;
                    }
                }
                for (int i = 1; i <= 4; i++) {
                    if (structure[i][i] != null && structure[i + 1][i + 1] != null &&
                            !(structure[i][i].getColour().equals(structure[i + 1][i + 1].getColour()))) {
                        x = 0;
                        break;
                    }
                }
                if (x == 1) {
                    return true;
                } else return false;
            }

            public static boolean checkCrux(ItemTile[][] structure) {
                for (int i = 1; i <= 4; i++) {
                    for (int j = 1; j <= 3; j++) {
                        if (structure[i][j] != null && structure[i - 1][j - 1] != null &&
                                structure[i + 1][j + 1] != null && structure[i + 1][j - 1] != null && structure[i - 1][j + 1] != null &&
                                structure[i][j].getColour().equals(structure[i - 1][j - 1].getColour()) &&
                                structure[i][j].getColour().equals(structure[i + 1][j + 1].getColour()) &&
                                structure[i][j].getColour().equals(structure[i + 1][j - 1].getColour()) &&
                                structure[i][j].getColour().equals(structure[i - 1][j + 1].getColour())
                        )
                            return true;
                    }
                }
                return false;
            }

            public static boolean checkStair(ItemTile[][] structure) {
                boolean checker = true;
                int k = 4;
                for (int i = 5; i >= 0; i--) {
                    for (int j = k; j >= 0; j--) {
                        if (structure[i][j] == null) {
                            checker = false;
                        }
                    }
                    int z = 4;
                    while (z > k) {
                        if (structure[i][z] != null)
                            checker = false;
                        z--;
                    }
                    k--;
                }
                int k = 0;
                for (int i = 5; i >= 0; i--) {
                    for (int j = k; j <= 4; j++) {
                        if (structure[i][j] == null) {
                            checker = false;
                        }
                        int z = 0;
                        while (z < k) {
                            if (structure[i][z] != null)
                                checker = false;
                            z++;
                        }
                        k++;
                    }
                }

                return checker;
            }


            public static boolean CheckColumn1(ItemTile[][] structure) {//scorro colonne e uso algoritmo che controlla un max di 3 tipi diversi tramite un counter
                int columnCount = 0;
                boolean checker = false;

                for (int j = 0; j < 5; j++) {
                    if (structure[0][j] != null) {
                        ArrayList<Colour> list = new ArrayList<Colour>();
                        for (int i = 0; i < 6; i++) {
                            list.add(structure[i][j].getColour());
                        }
                        int distinctCount = 0;
                        for (int l = 0; l < list.size(); l++) {
                            boolean isDistinct = true;
                            for (int k = 0; k < l; k++) {
                                if (list.get(l).equals(list.get(k))) {
                                    isDistinct = false;
                                    break;
                                }
                            }
                            if (isDistinct) {
                                distinctCount++;
                            }
                        }
                        if (distinctCount <= 3) {
                            columnCount++;
                        }
                    }

                }
                if (columnCount >= 3)
                    checker = true;
                return checker;
            }

            public static boolean CheckColumn2(ItemTile[][] structure) {//due colonne di 6 tipi diversi una cazzata basta fare controllo delle colonne con due per considerarer le 6 righe e scorro avanti di colonne quindi usa un while con dentro un for e usa un counter
                int columnCount = 0;
                boolean checker = false;

                for (int j = 0; j < 5; j++) {
                    if (structure[0][j] != null) {
                        ArrayList<Colour> list = new ArrayList<Colour>();
                        for (int i = 0; i < 6; i++) {
                            list.add(structure[i][j].getColour());
                        }
                        int distinctCount = 0;
                        for (int l = 0; l < list.size(); l++) {
                            boolean isDistinct = true;
                            for (int k = 0; k < l; k++) {
                                if (list.get(l).equals(list.get(k))) {
                                    isDistinct = false;
                                    break;
                                }
                            }
                            if (isDistinct) {
                                distinctCount++;
                            }
                        }
                        if (distinctCount == 6) {
                            columnCount++;
                        }
                    }

                }
                if (columnCount >= 2)
                    checker = true;
                return checker;

            }

            public static boolean CheckLine1(ItemTile[][] structure) { //uguale a column 1 ma sviluppato per righe
                int lineCount = 0;
                boolean checker = false;
                for (int i = 5; i >= 0; i--) {
                    int val = 1;
                    for (int j = 0; j < 5; j++) {
                        if (structure[i][j] == null) {
                            val = 0;
                            break;
                        }
                    }
                    if (val == 1) {
                        ArrayList<Colour> list = new ArrayList<Colour>();
                        for (int k = 0; k < 5; k++) {
                            list.add(structure[i][k].getColour());
                        }
                        int distinctCount = 0;
                        for (int l = 0; l < list.size(); l++) {
                            boolean isDistinct = true;
                            for (int z = 0; z < l; z++) {
                                if (list.get(l).equals(list.get(z))) {
                                    isDistinct = false;
                                    break;
                                }
                            }
                            if (isDistinct) {
                                distinctCount++;
                            }
                        }
                        if (distinctCount <= 3) {
                            lineCount++;
                        }
                    } else
                        break; //se la riga inferiore rispetto a quella controllata non è completa non ha senso continuare il for perchè l'obiettivo non è raggiungibile per come è composto l'inserimento delle carte dal basso verso l'alto
                }
                if (lineCount >= 4)
                    checker = true;
                return checker;

            }

            public static boolean CheckLine2(ItemTile[][] structure) { //uguale a column 2 ma sviluppato per le righe
                int lineCount = 0;
                boolean checker = false;
                for (int i = 5; i >= 0; i--) {
                    int val = 1;
                    for (int j = 0; j < 5; j++) {
                        if (structure[i][j] == null) {
                            val = 0;
                            break;
                        }
                    }
                    if (val == 1) {
                        ArrayList<Colour> list = new ArrayList<Colour>();
                        for (int k = 0; k < 5; k++) {
                            list.add(structure[i][k].getColour());
                        }
                        int distinctCount = 0;
                        for (int l = 0; l < list.size(); l++) {
                            boolean isDistinct = true;
                            for (int z = 0; z < l; z++) {
                                if (list.get(l).equals(list.get(z))) {
                                    isDistinct = false;
                                    break;
                                }
                            }
                            if (isDistinct) {
                                distinctCount++;
                            }
                        }
                        if (distinctCount == 5) {
                            lineCount++;
                        }
                    } else
                        break; //se la riga inferiore rispetto a quella controllata non è completa non ha senso continuare il for perchè l'obiettivo non è raggiungibile per come è composto l'inserimento delle carte dal basso verso l'alto
                }
                if (lineCount >= 2)
                    checker = true;
                return checker;


            }


            public static boolean checkEight(ItemTile[][] structure) {
                for (int i = 0; i < structure.length; i++) {
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
                                    }
                                }
                            }
                        }
                    }
                }
                return false;
            }
        }









<<<<<<<HEAD


        public class ItemBag() {

            private List<PlayableItemTile> bag;


            public void putTiles() {
                bag = new ArrayList<PlayableItemTile>();
                PlayableItemTileFactory factory = new PlayableItemTileFactory();

                for (int i = 0; i < 132; i++) {
                    if (i < 22) {
                        try {
                            bag.add(factory.createPlayableItemTile("GREEN", i + 1));
                        } catch (InvalidPlayableItemTileColourException exc) {

                        }
                    } else if (i < 44) {
                        try {
                            bag.add(factory.createPlayableItemTile("WHITE", i + 1));
                        } catch (InvalidPlayableItemTileColourException exc) {

                        }
                    } else if (i < 66) {
                        try {
                            bag.add(factory.createPlayableItemTile("YELLOW", i + 1));
                        } catch (InvalidPlayableItemTileColourException exc) {

                        }
                    } else if (i < 88) {
                        try {
                            bag.add(factory.createPlayableItemTile("BLUE", i + 1));
                        } catch (InvalidPlayableItemTileColourException exc) {

                        }
                    } else if (i < 110) {
                        try {
                            bag.add(factory.createPlayableItemTile("CYAN", i + 1));
                        } catch (InvalidPlayableItemTileColourException exc) {

                        }
                    } else if (i < 132) {
                        try {
                            bag.add(factory.createPlayableItemTile("PINK", i + 1));
                        } catch (InvalidPlayableItemTileColourException exc) {

                        }
                    }
                }
            }

            //dovrebbe effettuare pick random di ItemTile da ItemBag
            public PlayableItemTile randPickTile() {
                final Random RAND = new Random();
                int index = RAND.nextInt(bag.size());
                PlayableItemTile helperTile = bag.get(index);
                bag.remove(index);
                return helperTile;
            }
        }

        public abstract class ItemTile {
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

        public class NullItemTile extends ItemTile {
            private Colour colour;
            private int idCode;
            private boolean availability;
            private boolean adjacency;

            public NullItemTile() {
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

            public void makeAvailable() {
            }

            public void makeUnavailable() {
            }

            @java.lang.Override
            public boolean isAvailable() {
                return false;
            }

            @Override
            public boolean nullDetection() {
                return true;
            }

            public void setAdjacency() {
            }

            ;

            public boolean getAdjacency() {
                return adjacency;
            }

            ;

            @Override
            public void resetAdjacency() {
                adjacency = false;
            }
        }
        public class PlayableItemTile extends ItemTile {
            private Colour colour;
            private int idCode;
            private boolean availability;
            private boolean adjacency = false;

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

            public PlayableItemTile(String colour, int id) {
                if (colour.equals("GREEN")) {
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

<<<<<<<HEAD


        abstract class LivingRoomFactory {
            abstract public ItemTile[][] create();
        }

        public class TwoLivingRoomFactory extends LivingRoomFactory {
            @Override
            public ItemTile[][] create() {
                ItemTile[][] helperTable = new ItemTile[9][9];
                NullItemTile nullTile = new NullItemTile();

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

        public class ThreeLivingRoomFactory extends LivingRoomFactory {
            @Override
            public ItemTile[][] create() {
                ItemTile[][] helperTable = new ItemTile[9][9];
                NullItemTile nullTile = new NullItemTile();

                for (int i = 0; i < 9; i++) {
                    //metto null Tiles da [0][0] a [0][2] e da [0][4] a [0][8]
                    for (int j = 0; j < 9 && i == 0; j++) {
                        if (j != 3) {
                            helperTable[i][j] = nullTile;
                        }
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
                    //metto null Tiles da [2][0] a [2][1] e da [2][7] a [2][8]
                    //metto null Tiles da [6][0] a [6][1] e da [6][7] a [6][8]
                    for (int j = 0; j < 9 && (i == 2 || i == 6); j++) {
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
                    for (int j = 0; j < 9 && i == 8; j++) {
                        if (j != 5) {
                            helperTable[i][j] = nullTile;
                        }
                    }
                }

                return helperTable;
            }
        }

        public class FourLivingRoomFactory extends LivingRoomFactory {
            @Override
            public ItemTile[][] create() {
                ItemTile[][] helperTable = new ItemTile[9][9];
                NullItemTile nullTile = new NullItemTile();

                for (int i = 0; i < 9; i++) {
                    //metto null Tiles da [0][0] a [0][2] e da [0][5] a [0][8]
                    for (int j = 0; j < 9 && i == 0; j++) {
                        if (j < 3 || j > 4) {
                            helperTable[i][j] = nullTile;
                        }
                    }
                    //metto null Tiles da [1][0] a [1][2] e da [1][6] a [1][8]
                    //metto null Tiles da [7][0] a [7][2] e da [7][6] a [7][8]
                    for (int j = 0; j < 9 && (i == 1 || i == 7); j++) {
                        if (j < 3 || j > 5) {
                            helperTable[i][j] = nullTile;
                        }
                    }
                    //metto null Tiles da [2][0] a [2][1] e da [2][7] a [2][8]
                    //metto null Tiles da [6][0] a [6][1] e da [6][7] a [6][8]
                    for (int j = 0; j < 9 && (i == 2 || i == 6); j++) {
                        if (j < 2 || j > 6) {
                            helperTable[i][j] = nullTile;
                        }
                    }
                    //metto null Tile in [3][0]
                    helperTable[3][0] = nullTile;

                    //metto null Tiles da [5][8]
                    helperTable[5][8] = nullTile;

                    //metto null Tiles da [8][0] a [8][3] e da [8][6] a [8][8]
                    for (int j = 0; j < 9 && i == 8; j++) {
                        if (j < 4 || j > 5) {
                            helperTable[i][j] = nullTile;
                        }
                    }
                }

                return helperTable;
            }
        }


        public class PlayableItemTileFactory() {
            public PlayableItemTile createPlayableItemTile(String colour, int id) throws InvalidPlayableItemTileColourException {
                if (colour.equals("GREEN") || colour.equals("WHITE") || colour.equals("YELLOW") || colour.equals("BLUE") || colour.equals("CYAN") || colour.equals("PINK")) {
                    PlayableItemTile helperTile = new PlayableItemTile(colour, id);
                    return helperTile;
                } else {
                    throw new InvalidPlayableItemTileColourException();
                }
            }
        }

        enum Colour {GREEN, WHITE, YELLOW, BLUE, CYAN, PINK, VOID}


        public class App {
            public static void main(String[] args) {
                System.out.println("Hello World!");
            }

            // codice diletta
            public void setPersonalGoalType(CommonGoalType commonGoalType) { // chiamato e passato dal controller quando decide random che tipo di carta viene estratta
                this.commonGoalType = commonGoalType;
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
    }
