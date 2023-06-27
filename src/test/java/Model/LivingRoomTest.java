package Model;

import Network.ClientSide.RemoteClientImplementation;
import Util.Colour;
import client.view.TUI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.Model.*;

import java.rmi.RemoteException;

public class LivingRoomTest {
    private LivingRoom livingRoom;

    @BeforeEach
    public void setup() {
        GameModel gameModel = new GameModel();
        gameModel.getMyShelfie().setItemBag();
        gameModel.getMyShelfie().setLivingRoom(2);
        livingRoom = gameModel.getMyShelfie().getLivingRoom();
        try {
            RemoteClientImplementation client = new RemoteClientImplementation("localhost", 1099, new TUI());
            livingRoom.addObserver(client);
            gameModel.setCurrPlayer(new Player("lorenzo", client, gameModel));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testCreateGameTable(){
        livingRoom.createGameTable(2);

        //elenco di verifiche per 2 player
        for (int i = 0; i < 9; i++) {
            //metto null Tiles da [0][0] a [0][8]
            for (int j = 0; j < 9 && i == 0; j++) {
                Assertions.assertTrue(livingRoom.nullTileDetection(i,j));
            }
            //metto null Tiles da [1][0] a [1][2] e da [1][5] a [1][8]
            for (int j = 0; j < 9 && i == 1; j++) {
                if (j < 3 || j > 4) {
                    Assertions.assertTrue(livingRoom.nullTileDetection(i,j));
                }else{
                    Assertions.assertTrue(livingRoom.nullDetection(i,j));
                }
            }

            //metto null Tiles da [7][0] a [7][3] e da [7][6] a [7][8]
            for (int j = 0; j < 9 && i == 7; j++) {
                if (j < 4 || j > 5) {
                    Assertions.assertTrue(livingRoom.nullTileDetection(i,j));
                }else{
                    Assertions.assertTrue(livingRoom.nullDetection(i,j));
                }
            }
            //metto null Tiles da [2][0] a [2][2] e da [2][6] a [2][8]
            //metto null Tiles da [6][0] a [6][2] e da [6][6] a [6][8]
            for (int j = 0; j < 9 && (i == 2 || i == 6); j++) {
                if (j < 3 || j > 5) {
                    Assertions.assertTrue(livingRoom.nullTileDetection(i,j));
                }else{
                    Assertions.assertTrue(livingRoom.nullDetection(i,j));
                }
            }
            //metto null Tile in [3][0] e [3][1] e [3][8]
            if(i==3) {
                for(int j=0; j<9; j++){
                    if(j==0 || j==1 || j==8){
                        Assertions.assertTrue(livingRoom.nullTileDetection(i,j));
                    }else{
                        Assertions.assertTrue(livingRoom.nullDetection(i,j));
                    }
                }
            }

            //metto null Tile in [4][0] e [4][8]
            if(i==4) {
                for(int j=0; j<9; j++){
                    if(j==0 || j==8){
                        Assertions.assertTrue(livingRoom.nullTileDetection(i,j));
                    }else{
                        Assertions.assertTrue(livingRoom.nullDetection(i,j));
                    }
                }
            }
            //metto null Tiles in [5][] e [5][8] e [5][7]
            if(i==5) {
                for(int j=0; j<9; j++){
                    if(j==7 || j==0 || j==8){
                        Assertions.assertTrue(livingRoom.nullTileDetection(i,j));
                    }else{
                        Assertions.assertTrue(livingRoom.nullDetection(i,j));
                    }
                }
            }

            //metto null Tiles da [8][0] a [8][8]
            for (int j = 0; j < 9 && i == 8; j++) {
                Assertions.assertTrue(livingRoom.nullTileDetection(i,j));
            }
        }


        livingRoom.createGameTable(3);

        //elenco di verifiche per 3 player
        for (int i = 0; i < 9; i++) {
            //metto null Tiles da [0][0] a [0][2] e da [0][4] a [0][8]
            for (int j = 0; j < 9 && i == 0; j++) {
                if (j != 3) {
                    Assertions.assertTrue(livingRoom.nullTileDetection(i,j));
                }else{
                    Assertions.assertTrue(livingRoom.nullDetection(i,j));
                }
            }
            //metto null Tiles da [1][0] a [1][2] e da [1][5] a [1][8]
            for (int j = 0; j < 9 && i == 1; j++) {
                if (j < 3 || j > 4) {
                    Assertions.assertTrue(livingRoom.nullTileDetection(i,j));
                }else{
                    Assertions.assertTrue(livingRoom.nullDetection(i,j));
                }
            }

            //metto null Tiles da [7][0] a [7][3] e da [7][6] a [7][8]
            for (int j = 0; j < 9 && i == 7; j++) {
                if (j < 4 || j > 5) {
                    Assertions.assertTrue(livingRoom.nullTileDetection(i,j));
                }else{
                    Assertions.assertTrue(livingRoom.nullDetection(i,j));
                }
            }
            //metto null Tiles da [2][0] a [2][1] e da [2][7] a [2][8]
            //metto null Tiles da [6][0] a [6][1] e da [6][7] a [6][8]
            for (int j = 0; j < 9 && (i == 2 || i == 6); j++) {
                if (j < 2 || j > 6) {
                    Assertions.assertTrue(livingRoom.nullTileDetection(i,j));
                }else{
                    Assertions.assertTrue(livingRoom.nullDetection(i,j));
                }
            }
            //metto null Tile in [3][0] e [3][1]
            if(i==3) {
                for(int j=0; j<9; j++){
                    if(j==0 || j==1){
                        Assertions.assertTrue(livingRoom.nullTileDetection(i,j));
                    }else{
                        Assertions.assertTrue(livingRoom.nullDetection(i,j));
                    }
                }
            }

            //metto null Tile in [4][0] e [4][8]
            if(i==4) {
                for(int j=0; j<9; j++){
                    if(j==0 || j==8){
                        Assertions.assertTrue(livingRoom.nullTileDetection(i,j));
                    }else{
                        Assertions.assertTrue(livingRoom.nullDetection(i,j));
                    }
                }
            }
            //metto null Tiles da [5][8] e [5][7]
            if(i==5) {
                for(int j=0; j<9; j++){
                    if(j==7 || j==8){
                        Assertions.assertTrue(livingRoom.nullTileDetection(i,j));
                    }else{
                        Assertions.assertTrue(livingRoom.nullDetection(i,j));
                    }
                }
            }


            //metto null Tiles da [8][0] a [8][4] e da [8][6] a [8][8]
            for (int j = 0; j < 9 && i == 8; j++) {
                if (j != 5) {
                    Assertions.assertTrue(livingRoom.nullTileDetection(i,j));
                }else{
                    Assertions.assertTrue(livingRoom.nullDetection(i,j));
                }
            }
        }


        livingRoom.createGameTable(4);

        //elenco di verifiche per 4 player
        for (int i = 0; i < 9; i++) {
            //metto null Tiles da [0][0] a [0][2] e da [0][5] a [0][8]
            for (int j = 0; j < 9 && i == 0; j++) {
                if (j < 3 || j > 4) {
                    Assertions.assertTrue(livingRoom.nullTileDetection(i,j));
                }else{
                    Assertions.assertTrue(livingRoom.nullDetection(i,j));
                }
            }
            //metto null Tiles da [1][0] a [1][2] e da [1][6] a [1][8]
            //metto null Tiles da [7][0] a [7][2] e da [7][6] a [7][8]
            for (int j = 0; j < 9 && (i == 1 || i == 7); j++) {
                if (j < 3 || j > 5) {
                    Assertions.assertTrue(livingRoom.nullTileDetection(i,j));
                }else{
                    Assertions.assertTrue(livingRoom.nullDetection(i,j));
                }
            }
            //metto null Tiles da [2][0] a [2][1] e da [2][7] a [2][8]
            //metto null Tiles da [6][0] a [6][1] e da [6][7] a [6][8]
            for (int j = 0; j < 9 && (i == 2 || i == 6); j++) {
                if (j < 2 || j > 6) {
                    Assertions.assertTrue(livingRoom.nullTileDetection(i,j));
                }else{
                    Assertions.assertTrue(livingRoom.nullDetection(i,j));
                }
            }
            //metto null Tile in [3][0]
            if(i==3) {
                for(int j=0; j<9; j++){
                    if(j==0){
                        Assertions.assertTrue(livingRoom.nullTileDetection(i,j));
                    }else{
                        Assertions.assertTrue(livingRoom.nullDetection(i,j));
                    }
                }
            }


            if(i==4) {
                for(int j=0; j<9; j++){
                    Assertions.assertTrue(livingRoom.nullDetection(i,j));
                }
            }
            //metto null Tiles da [5][8]
            if(i==5) {
                for(int j=0; j<9; j++){
                    if(j==8){
                        Assertions.assertTrue(livingRoom.nullTileDetection(i,j));
                    }else{
                        Assertions.assertTrue(livingRoom.nullDetection(i,j));
                    }
                }
            }

            //metto null Tiles da [8][0] a [8][3] e da [8][6] a [8][8]
            for (int j = 0; j < 9 && i == 8; j++) {
                if (j < 4 || j > 5) {
                    Assertions.assertTrue(livingRoom.nullTileDetection(i,j));
                }else{
                    Assertions.assertTrue(livingRoom.nullDetection(i,j));
                }
            }
        }
    }

    @Test
    public void testSearchVoid(){
        ItemTile[][] gameTable;
        livingRoom.createGameTable(2);
        gameTable = livingRoom.getGameTable();

        ItemBag bag = new ItemBag();
        bag.putTiles();
        gameTable[4][3] = bag.randPickTile();
        livingRoom.setGameTable(gameTable);



        Assertions.assertFalse(livingRoom.searchVoid(4,3));
        Assertions.assertTrue(livingRoom.searchVoid(3,3));
    }

    @Test
    public void testFillVoid(){
        ItemBag bag = new ItemBag();
        bag.putTiles();
        PlayableItemTile helperTile = bag.randPickTile();
        livingRoom.createGameTable(2);
        livingRoom.fillVoid(4,3,helperTile);

        Assertions.assertFalse(livingRoom.nullDetection(4,3));
        Assertions.assertFalse(livingRoom.searchVoid(4,3));
    }

    @Test
    public void testNullDetection() {
        livingRoom.createGameTable(2);
        ItemBag bag = new ItemBag();
        bag.putTiles();
        PlayableItemTile helperTile = bag.randPickTile();
        livingRoom.fillVoid(4,3, helperTile);

        Assertions.assertTrue(livingRoom.nullDetection(3,3));
        Assertions.assertFalse(livingRoom.nullDetection(4,3));

    }

    @Test
    public void testPickTile() {
        ItemBag bag = new ItemBag();
        bag.putTiles();
        PlayableItemTile testTile = bag.randPickTile();
        livingRoom.fillVoid(4, 3, testTile);

        PlayableItemTile helperTile = livingRoom.pickTile(4, 3);
        Assertions.assertEquals(testTile, helperTile);
        Assertions.assertTrue(livingRoom.nullDetection(4, 3));

    }

    @Test
    public void testUpdateAvailability(){
        ItemTile[][] gameTable;
        livingRoom.createGameTable(2);
        gameTable = livingRoom.getGameTable();

        ItemBag bag = new ItemBag();
        bag.putTiles();
        gameTable[4][3] = bag.randPickTile();
        gameTable[4][2] = bag.randPickTile();
        gameTable[4][4] = bag.randPickTile();
        gameTable[5][3] = bag.randPickTile();
        gameTable[3][3] = bag.randPickTile();
        gameTable[5][4] = bag.randPickTile();
        gameTable[4][5] = bag.randPickTile();
        gameTable[5][2] = bag.randPickTile();
        gameTable[6][3] = bag.randPickTile();


        livingRoom.setGameTable(gameTable);

        livingRoom.updateAvailability();




        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!livingRoom.nullTileDetection(i,j) && !livingRoom.nullDetection(i,j)) {
                    //metto available tutti i corner cases
                    //1° tessere [0][3] e [0][4]
                    if (i == 0) {
                        if (j == 3) {
                            Assertions.assertTrue(livingRoom.getTileAvailability(i,j));
                        }else if (j == 4) {
                            Assertions.assertTrue(livingRoom.getTileAvailability(i,j));
                        }
                    }
                    //2° tessere  [4][0] e [5][0]
                    else if (j == 0) {
                        if (i == 4) {
                            Assertions.assertTrue(livingRoom.getTileAvailability(i,j));
                        }
                        if (i == 5) {
                            Assertions.assertTrue(livingRoom.getTileAvailability(i,j));
                        }
                    }
                    //3° tessere [8][4] e [8][5]
                    else if (i == 8) {
                        if (j == 4) {
                            Assertions.assertTrue(livingRoom.getTileAvailability(i,j));
                        }
                        if (j == 5) {
                            Assertions.assertTrue(livingRoom.getTileAvailability(i,j));
                        }
                    }
                    //4° tessere [3][8] e [4][8]
                    else if (j == 8) {
                        if (i == 3) {
                            Assertions.assertTrue(livingRoom.getTileAvailability(i,j));
                        }
                        if (i == 4) {
                            Assertions.assertTrue(livingRoom.getTileAvailability(i,j));
                        }
                    }
                    //guardo il caso generale
                    Assertions.assertFalse(livingRoom.getTileAvailability(4,3));
                    Assertions.assertTrue(livingRoom.getTileAvailability(3, 3));
                    Assertions.assertTrue(livingRoom.getTileAvailability(4,2));
                    Assertions.assertTrue(livingRoom.getTileAvailability(4,4));
                    Assertions.assertFalse(livingRoom.getTileAvailability(5,3));

                }
            }
        }
    }

    @Test
    public void testUpdateAdjacentAvailabilityV1(){
        ItemTile[][] gameTable;
        livingRoom.createGameTable(2);
        gameTable = livingRoom.getGameTable();

        ItemBag bag = new ItemBag();
        bag.putTiles();
        gameTable[4][3] = bag.randPickTile();
        gameTable[4][2] = bag.randPickTile();
        gameTable[4][4] = bag.randPickTile();
        gameTable[5][3] = bag.randPickTile();
        gameTable[3][3] = bag.randPickTile();
        gameTable[4][6] = bag.randPickTile();
        livingRoom.setGameTable(gameTable);

        livingRoom.updateAdjacentAvailabilityV1(4,3);

        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if((i==5 && j==3) || (i==3 && j==3) || (i==4 && j==4) || (i==4 && j==2)){
                    Assertions.assertTrue(livingRoom.getTileAdjacency(i,j));
                }else {
                    Assertions.assertFalse(livingRoom.getTileAdjacency(i, j));
                }
            }
        }

    }

    @Test
    public void testResetAdjacentAvailability(){
        ItemTile[][] gameTable;
        livingRoom.createGameTable(2);
        gameTable = livingRoom.getGameTable();

        ItemBag bag = new ItemBag();
        bag.putTiles();
        gameTable[4][3] = bag.randPickTile();
        gameTable[4][2] = bag.randPickTile();
        gameTable[4][4] = bag.randPickTile();
        gameTable[5][3] = bag.randPickTile();
        gameTable[3][3] = bag.randPickTile();
        livingRoom.setGameTable(gameTable);

        livingRoom.updateAdjacentAvailabilityV1(4,3);
        livingRoom.resetAdjacentAvailability();

        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if (!livingRoom.nullTileDetection(i,j) && !livingRoom.nullDetection(i,j)) {
                    Assertions.assertFalse(livingRoom.getTileAdjacency(i, j));
                }
            }
        }

    }

    @Test
    public void testUpdateAdjacentAvailabilityV2(){
        //vado sopra
        ItemTile[][] gameTable;
        livingRoom.createGameTable(2);
        gameTable = livingRoom.getGameTable();

        ItemBag bag = new ItemBag();
        bag.putTiles();
        gameTable[4][3] = bag.randPickTile();
        gameTable[4][2] = bag.randPickTile();
        gameTable[4][4] = bag.randPickTile();
        gameTable[5][3] = bag.randPickTile();
        gameTable[3][3] = bag.randPickTile();
        livingRoom.setGameTable(gameTable);

        livingRoom.updateAdjacentAvailabilityV1(4,3);
        gameTable = livingRoom.getGameTable();
        gameTable[4][3] = null;
        gameTable[3][4] = bag.randPickTile();
        gameTable[3][2] = bag.randPickTile();
        gameTable[2][3] = bag.randPickTile();
        gameTable[2][5] = bag.randPickTile();



        livingRoom.setGameTable(gameTable);

        livingRoom.updateAdjacentAvailabilityV2(3,3,4,3);

        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if((i==5 && j==3) || (i==2 && j==3) || (i==3 && j==3)){
                    Assertions.assertTrue(livingRoom.getTileAdjacency(i,j));
                }else {
                    Assertions.assertFalse(livingRoom.getTileAdjacency(i, j));
                }
            }
        }
        //vado sotto
        livingRoom.createGameTable(2);
        gameTable = livingRoom.getGameTable();

        bag = new ItemBag();
        bag.putTiles();
        gameTable[4][3] = bag.randPickTile();
        gameTable[4][2] = bag.randPickTile();
        gameTable[4][4] = bag.randPickTile();
        gameTable[5][3] = bag.randPickTile();
        gameTable[3][3] = bag.randPickTile();
        livingRoom.setGameTable(gameTable);

        livingRoom.updateAdjacentAvailabilityV1(4,3);
        gameTable = livingRoom.getGameTable();
        gameTable[4][3] = null;
        gameTable[5][4] = bag.randPickTile();
        gameTable[6][3] = bag.randPickTile();
        gameTable[2][5] = bag.randPickTile();
        gameTable[5][2] = bag.randPickTile();



        livingRoom.setGameTable(gameTable);

        livingRoom.updateAdjacentAvailabilityV2(5,3,4,3);


        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if((i==6 && j==3) || (i==3 && j==3) || (i==5 && j==3)){
                    Assertions.assertTrue(livingRoom.getTileAdjacency(i,j));
                }else {
                    Assertions.assertFalse(livingRoom.getTileAdjacency(i, j));
                }
            }
        }

        //vado a sx
        livingRoom.createGameTable(2);
        gameTable = livingRoom.getGameTable();

        bag = new ItemBag();
        bag.putTiles();
        gameTable[4][3] = bag.randPickTile();
        gameTable[4][2] = bag.randPickTile();
        gameTable[4][4] = bag.randPickTile();
        gameTable[5][3] = bag.randPickTile();
        gameTable[3][3] = bag.randPickTile();
        livingRoom.setGameTable(gameTable);

        livingRoom.updateAdjacentAvailabilityV1(4,3);
        gameTable = livingRoom.getGameTable();
        gameTable[4][3] = null;
        gameTable[5][2] = bag.randPickTile();
        gameTable[4][1] = bag.randPickTile();
        gameTable[2][5] = bag.randPickTile();
        gameTable[3][2] = bag.randPickTile();



        livingRoom.setGameTable(gameTable);

        livingRoom.updateAdjacentAvailabilityV2(4,2,4,3);

        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if((i==4 && j==1) || (i==4 && j==4) || (i==4 && j==2)){
                    Assertions.assertTrue(livingRoom.getTileAdjacency(i,j));
                }else {
                    Assertions.assertFalse(livingRoom.getTileAdjacency(i, j));
                }
            }
        }

        //vado a dx
        livingRoom.createGameTable(2);
        gameTable = livingRoom.getGameTable();

        bag = new ItemBag();
        bag.putTiles();
        gameTable[4][3] = bag.randPickTile();
        gameTable[4][2] = bag.randPickTile();
        gameTable[4][4] = bag.randPickTile();
        gameTable[5][3] = bag.randPickTile();
        gameTable[3][3] = bag.randPickTile();
        livingRoom.setGameTable(gameTable);

        livingRoom.updateAdjacentAvailabilityV1(4,3);
        gameTable = livingRoom.getGameTable();
        gameTable[4][3] = null;
        gameTable[5][4] = bag.randPickTile();
        gameTable[4][5] = bag.randPickTile();
        gameTable[2][5] = bag.randPickTile();
        gameTable[3][4] = bag.randPickTile();



        livingRoom.setGameTable(gameTable);

        livingRoom.updateAdjacentAvailabilityV2(4,4,4,3);

        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if((i==4 && j==2) || (i==4 && j==5) || (i==4 && j==4)){
                    Assertions.assertTrue(livingRoom.getTileAdjacency(i,j));
                }else {
                    Assertions.assertFalse(livingRoom.getTileAdjacency(i, j));
                }
            }
        }


    }

    @Test
    public void testGetTileAvailabilityNullTile(){

        ItemTile[][] gameTable;
        livingRoom.createGameTable(2);
        gameTable = livingRoom.getGameTable();

        gameTable[3][3] = null;

        livingRoom.setGameTable(gameTable);


        Assertions.assertFalse(livingRoom.getTileAvailability(3,3));
    }


    @Test
    public void testUpdateAvailability3And4(){
        livingRoom.createGameTable(3);
        ItemBag bag = new ItemBag();
        bag.putTiles();
        boolean isVoid;
        PlayableItemTile helperTile;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                isVoid = livingRoom.searchVoid(i, j);
                if (isVoid) {
                    helperTile = bag.randPickTile();
                    if (helperTile.getColour() != Colour.VOID) {
                        livingRoom.fillVoid(i, j, helperTile);
                    }
                }
            }
        }

        livingRoom.updateAvailability();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!livingRoom.nullTileDetection(i,j) && !livingRoom.nullDetection(i,j)) {
                    //metto available tutti i corner cases
                    //1° tessere [0][3] e [0][4]
                    if (i == 0) {
                        if (j == 3) {
                            Assertions.assertTrue(livingRoom.getTileAvailability(i,j));
                        }else if (j == 4) {
                            Assertions.assertFalse(livingRoom.getTileAvailability(i,j));
                        }
                    }
                    //2° tessere  [4][0] e [5][0]
                    else if (j == 0) {
                        if (i == 4) {
                            Assertions.assertFalse(livingRoom.getTileAvailability(i,j));
                        }
                        if (i == 5) {
                            Assertions.assertTrue(livingRoom.getTileAvailability(i,j));
                        }
                    }
                    //3° tessere [8][4] e [8][5]
                    else if (i == 8) {
                        if (j == 4) {
                            Assertions.assertFalse(livingRoom.getTileAvailability(i,j));
                        }
                        if (j == 5) {
                            Assertions.assertTrue(livingRoom.getTileAvailability(i,j));
                        }
                    }
                    //4° tessere [3][8] e [4][8]
                    else if (j == 8) {
                        if (i == 3) {
                            Assertions.assertTrue(livingRoom.getTileAvailability(i,j));
                        }
                        if (i == 4) {
                            Assertions.assertFalse(livingRoom.getTileAvailability(i,j));
                        }
                    }
                }
            }
        }

        livingRoom.createGameTable(4);
        bag = new ItemBag();
        bag.putTiles();
        boolean isVoid2;
        PlayableItemTile helperTile2;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                isVoid2 = livingRoom.searchVoid(i, j);
                if (isVoid2) {
                    helperTile2 = bag.randPickTile();
                    if (helperTile2.getColour() != Colour.VOID) {
                        livingRoom.fillVoid(i, j, helperTile2);
                    }
                }
            }
        }

        livingRoom.updateAvailability();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!livingRoom.nullTileDetection(i,j) && !livingRoom.nullDetection(i,j)) {
                    //metto available tutti i corner cases
                    //1° tessere [0][3] e [0][4]
                    if (i == 0) {
                        if (j == 3) {
                            Assertions.assertTrue(livingRoom.getTileAvailability(i,j));
                        }else if (j == 4) {
                            Assertions.assertTrue(livingRoom.getTileAvailability(i,j));
                        }
                    }
                    //2° tessere  [4][0] e [5][0]
                    else if (j == 0) {
                        if (i == 4) {
                            Assertions.assertTrue(livingRoom.getTileAvailability(i,j));
                        }
                        if (i == 5) {
                            Assertions.assertTrue(livingRoom.getTileAvailability(i,j));
                        }
                    }
                    //3° tessere [8][4] e [8][5]
                    else if (i == 8) {
                        if (j == 4) {
                            Assertions.assertTrue(livingRoom.getTileAvailability(i,j));
                        }
                        if (j == 5) {
                            Assertions.assertTrue(livingRoom.getTileAvailability(i,j));
                        }
                    }
                    //4° tessere [3][8] e [4][8]
                    else if (j == 8) {
                        if (i == 3) {
                            Assertions.assertTrue(livingRoom.getTileAvailability(i,j));
                        }
                        if (i == 4) {
                            Assertions.assertTrue(livingRoom.getTileAvailability(i,j));
                        }
                    }

                }
            }
        }
    }

    @Test
    public void testOnError(){
        LivingRoom livingRoom = new LivingRoom(new GameModel());
        livingRoom.createGameTable(2);

        ItemTile[][] gameTable = livingRoom.getGameTable();
        gameTable[2][3] = new PlayableItemTile("BLUE", 85);
        gameTable[2][5] = new PlayableItemTile("YELLOW", 60);
        gameTable[3][3] = new PlayableItemTile("CYAN", 100);
        gameTable[3][2] = new PlayableItemTile("CYAN", 101);

        livingRoom.setGameTable(gameTable);

        livingRoom.updateAvailability();
        Assertions.assertEquals(4, livingRoom.getAvailableTiles().size());
        Assertions.assertTrue(livingRoom.getAvailableTiles().contains(gameTable[2][3]));
        Assertions.assertTrue(livingRoom.getAvailableTiles().contains(gameTable[2][5]));
        Assertions.assertTrue(livingRoom.getAvailableTiles().contains(gameTable[3][3]));
        Assertions.assertTrue(livingRoom.getAvailableTiles().contains(gameTable[3][2]));

    }


}