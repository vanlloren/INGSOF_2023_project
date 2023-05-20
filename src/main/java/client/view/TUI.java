package client.view;
import Observer.*;
import Network.ClientSide.IOManager;
import server.Model.*;


import java.io.PrintStream;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.*;


public class TUI extends ViewObservable implements View {  //dovrà diventare observable dal client
    private final PrintStream out;
    private final Scanner scanner = new Scanner(System.in);

    private boolean needNick=true;

    private final IOManager viewManager = new IOManager();


    private boolean gameOn=true;
    private boolean goOnPicking=true;
    private boolean moveOn;
    private String currPlayer;

    public String nickname;

    private boolean checker = false;
    public TUI(){
        this.out = System.out;
    }
  @Override
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void resetNeedNick(){
        this.needNick = false;
    }

    //Implementando il metodo Runnable ereditiamo tutte le sue classi e oggetti
    //Run è un costruttore basilare costruito direttamente dal metodo Runnable al posto di init
    public void init() {
        out.println("╔════════════════════════════════════════╗");
        out.println("║    --------------------------------    ║");
        out.println("║    |*****WELCOME TO MYSHELFIE*****|    ║");
        out.println("║    --------------------------------    ║");
        out.println("╚════════════════════════════════════════╝");

            String serverAddress = askServerInfo();
            int portNum = askServerPort();
            connectToServerFromTUI(serverAddress, portNum);
            scanner.nextLine();
            while(needNick) {
                askNickname();
            }
            while(gameOn){
            }
        }

    public void resetGameOn() {
        this.gameOn = false;
    }

    public void askPlayerNextMove(){
        int picking;
        if(gameOn) {
            do {

                out.println("""
                        Press 1 if you want to PICK A TILE FROM LIVING ROOM\s
                        Press 2 if you want to SEE THE LIVINGROOM\s
                        Press 3 if you want to SEE THE PLAYERS IN YOUR GAMELOBBY\s
                        Press 4 if you want to SEE YOUR PERSONAL SHELF\s
                        Press 5 if you want to SEE YOUR CURRENT SCORING\s
                        Press 6 if you want to SEE THE NICKNAME OF THE PLAYER WHO'S PLAYING\s
                        Press 7 if you want to write in the chat"""
                );

                picking = scanner.nextInt();
                if (picking != 1 && picking != 2 && picking != 3 && picking != 4 && picking != 5 && picking != 6 && picking != 7) {
                    out.println("Symbol not recognized, please try again...\n");
                }

            }
            while (picking != 1 && picking != 2 && picking != 3 && picking != 4 && picking != 5 && picking != 6 && picking != 7);

            switch (picking) {
                case 1:
                    if (currPlayer.equals(this.nickname)) {
                        notifyObserver(obs -> {
                            try {
                                obs.onUpdateStartPicking();
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                        });
                        moveOn=false;
                        while(!moveOn){}
                        goOnPicking = true;
                        while(goOnPicking) {
                            goOnPicking=false;
                            moveOn=false;
                            notifyObserver(obs -> {
                                obs.onUpdateShowAvailableTiles();
                            });

                            while (!moveOn){}
                        }
                    }
                    else {
                        System.out.println("IT IS NOT YOUR TURN YET: PLEASE WAIT ");
                        askPlayerNextMove();
                    }
                case 2:
                    notifyObserver(obs -> {
                        obs.onUpdateShowLivingRoom();
                    });
                    askPlayerNextMove();
                case 3:
                    notifyObserver(obs -> obs.onUpdateShowPlayersList());
                    askPlayerNextMove();
                case 4:
                    notifyObserver(obs -> obs.onUpdateShowPlayerShelf(nickname));
                    askPlayerNextMove();
                case 5:
                    notifyObserver(obs -> obs.onUpdateShowPartialPoint(nickname));
                    askPlayerNextMove();
                case 6:
                    notifyObserver(obs -> obs.onUpdateShowNickCurrPlayer());
                    askPlayerNextMove();
                case 7:
                    WriteInChat();
                    askPlayerNextMove();

            }
        }
    }

    public void setMoveOn(){
        moveOn=true;
    }

    public void askIsGameOn(){
        notifyObserver(obs -> {
            obs.onUpdateIsGameOn();
        });
    }


    @Override
    public void WriteInChat(){
        String chatMessage;
        scanner.nextLine();
        out.println("Write in the following line the content of your message");
        chatMessage = scanner.nextLine();
        notifyObserver(obs -> {
            try {
                obs.onUpdateChat(this.nickname,chatMessage);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public String askServerInfo(){
        out.println("Per favore, inserisci alcune informazioni:");
        String serverAddress;
        do {
            out.println("Inserisci l'indirizzo del Server [default = localhost]:");
            serverAddress = scanner.next();
            if(checkAddressValidity(serverAddress)){
                checker = true;
            }
            else{
                out.println("Indirizzo non valido!");
                checker = false;
            }
        }while(!checker);

        return serverAddress;
        //dovrò fornire a qualcuno serverAddress e serverPort per effettuare il collegamento
    }

    public static boolean checkAddressValidity(String ipAddress) {
        if (ipAddress == null || ipAddress.isEmpty()) {
            return false;
        }else if(ipAddress.equals("localhost")){
            return true;
        }

        String[] partedAddress = ipAddress.split("\\.");
        if (partedAddress.length != 4) {
            return false;
        }

        for (String part : partedAddress) {
            try {
                int value = Integer.parseInt(part);
                if (value < 0 || value > 255) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return true;
    }


    public int askServerPort() {
        int portNum;
        do {
            scanner.nextLine();
            out.println("Inserisci la porta del Server [default = ??]:");
            //effettuo check validità su in.nextLine();
            portNum = scanner.nextInt();
            if (checkPortValidity(portNum)) {
                checker = true;
            } else {
                out.println("Porta del server non valida!");
                checker = false;
            }
        } while (!checker);

        return portNum;
    }

    public boolean checkPortValidity(int portNum) {
        if(portNum<1024){
            return false;
        }

        return true;
    }

    public void fullLobbyTerminateUI(){
        out.println("Spiacente, la lobby è piena, riprova più tardi!");
        resetTUI();
        resetGameOn();
    }

    @Override
    public void askNickname() {

        out.println("Enter nickname please: ");
        String nickName = scanner.nextLine();
        notifyObserver(obs -> {
            try {
                obs.onUpdateNickname(nickName);
            } catch (RemoteException e) {
                out.println("errore");
                throw new RuntimeException(e);
            }
        });


    }

    @Override
    public void setCurrPlayer(String currPlayer) {
        this.currPlayer = currPlayer;
    }

    @Override
    public void askPlayersNumber() {
        int playersNum;

        out.println("Nickname accepted!");
        out.println("Il nickname scelto è: " + this.nickname);
        resetNeedNick();
        out.println("You are the first player of the game! Please, insert the number of total player for the match [min=2, max=4]:");
        playersNum = scanner.nextInt();
        while(playersNum<2 || playersNum>4){
            out.println("The number of player is not valid!");
            out.println("Insert the number of total players [min=2, max=4]:");
            playersNum = scanner.nextInt();
        }
            int finalPlayersNum = playersNum;
            notifyObserver(obs -> {
                try {
                    obs.onUpdatePlayersNumber(finalPlayersNum);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    @Override
    public void askMovingTilePosition(ArrayList<PlayableItemTile> availableTiles){
        for (PlayableItemTile tile: availableTiles
             ) {
            out.println("La tessera in posizione x=" + tile.getPosition().toArray()[0] + " y=" + tile.getPosition().toArray()[1] + " é disponibile!\n");
        }

        out.println("Scegli la posizione x della tessera che vuoi pescare!\n");
        int xPos = scanner.nextInt();
        out.println("Scegli la posizione y della tessera che vuoi pescare!\n");
        int yPos = scanner.nextInt();

        notifyObserver(obs -> {
            try {
                obs.onUpdateToPickTile(xPos, yPos);

            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void askStopPicking() {
        out.println("Would you like to keep picking tiles?[Y/N]\n");
        String picking = scanner.nextLine();
        while(picking != "Y" || picking !="N"){
            out.println("Symbol not recognized, please try  again...\n");
            askStopPicking();
        }
        String finalPicking = picking;

        notifyObserver(obs-> {
            try {
                obs.onUpdateAskKeepPicking(finalPicking);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
        if(finalPicking == "Y"){
            goOnPicking = true;
            setMoveOn();
        }else{
            setMoveOn();
        }
    }


    @Override
    public void askTileToPut(ArrayList<PlayableItemTile> tilesInPlayerHand) {
        int numOfTiles = tilesInPlayerHand.size();
        int xPos; int yPos; int index;
        for (PlayableItemTile tile: tilesInPlayerHand
        ) {
            out.println("Tiles picked: {["+tilesInPlayerHand.get(1)+"]," + "["+tilesInPlayerHand.get(2)+"]," +"["+tilesInPlayerHand.get(3)+"]}");
        }
        {   out.println("Choose the tile that you want to put in the shelf(Valid insert:[ 1 , 2 , 3 ])");
            index = scanner.nextInt();
            while(index<1 || index>3){
                out.println("The index of the tile is not valid!\n");
                out.println("Choose the tile that you want to put in the shelf(Valid insert:[ 1 , 2 , 3 ])");
                index = scanner.nextInt();
            }
            out.println("Choose the tile that you want to put in the shelf(Valid insert:[ 0 , 1 , 2 , 3 , 4 , 5 ])");
            xPos = scanner.nextInt();
            while(xPos<0 || xPos>5){
                out.println("The position x for the the insertion of the tile is not valid!\n");
                out.println("Choose the tile that you want to put in the shelf(Valid insert:[ 0 , 1 , 2 , 3 , 4 , 5 ])");
                xPos = scanner.nextInt();
            }
            out.println("Choose the position y where you want to put the tile(Valid insert: [ 0 , 1 , 2 , 3 , 4 ])!\n");
            yPos = scanner.nextInt();
            while(yPos<0 || yPos>4){
                out.println("The position y for the the insertion of the tile is not valid!\n");
                out.println("Choose the position y where you want to put the tile(Valid insert: [ 0 , 1 , 2 , 3 , 4 ])!\n");
                yPos = scanner.nextInt();
            }
            ArrayList<Integer> columnPosition = new ArrayList<>();
            columnPosition.add(yPos);
            int finalIndex = index;
            int finalYPos = yPos;
            int finalXPos = xPos;
            notifyObserver(obs -> {
                try {
                    obs.onUpdateToPutTile(finalXPos, finalYPos, tilesInPlayerHand.get(finalIndex), columnPosition , numOfTiles , tilesInPlayerHand);

                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            });
        }

    }
    public void askTileToPut2or3tile(ArrayList<PlayableItemTile> tilesInPlayerHand) {
        int numOfTiles = tilesInPlayerHand.size();
        int xPos;
        int index;
        {
            out.println("Choose the tile that you want to put in the shelf(Valid insert:[ 1 , 2 ])");
            index = scanner.nextInt();
            while (index < 1 || index > numOfTiles) {
                out.println("The index of the tile is not valid!\n");
                out.println("Choose the tile that you want to put in the shelf(Valid insert:[ 1 , 2  ])");
                index = scanner.nextInt();
            }
            out.println("Choose the tile that you want to put in the shelf(Valid insert:[ 0 , 1 , 2 , 3 , 4 , 5 ])");
            xPos = scanner.nextInt();
            while (xPos < 0 || xPos > 5) {
                out.println("The position x for the the insertion of the tile is not valid!\n");
                out.println("Choose the tile that you want to put in the shelf(Valid insert:[ 0 , 1 , 2 , 3 , 4 , 5 ])");
                xPos = scanner.nextInt();
            }
            ArrayList<Integer> columnPosition = new ArrayList<>();
            int finalIndex = index;
            int finalXPos = xPos;
            notifyObserver(obs -> {
                try {
                    obs.onUpdateToPut2or3Tile(finalXPos, tilesInPlayerHand.get(finalIndex), tilesInPlayerHand);

                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public void maxTilesPicked(){
        out.println("Numero massimo di tessere raccolte dalla LivingRoom, procedi ora a inserirle nella Shelf");
        setMoveOn();
    }

    @Override
    public void showLoginResults(boolean nickAccepted, String chosenNickname) {
        if(nickAccepted == true){
            out.println("Login successful, nickname accepted!");
            out.println("Il nickname scelto è: " + this.nickname);
            resetNeedNick();
        }else{
            out.println("Nickname already chosen, choose another nickname!");
        }

    }

    public void showNegativePutTileResults (  ArrayList<PlayableItemTile> tilesInPlayerHand){
            out.println("Error in the insertion!\n");
            out.println("Please retry the insertion\n");
            askTileToPut(tilesInPlayerHand);
    }

    public void showNegativePut2Or3TileResults ( ArrayList<PlayableItemTile> tilesInPlayerHand){
        out.println("Error in the insertion!\n");
        out.println("Please retry the insertion\n");
        askTileToPut2or3tile(tilesInPlayerHand);
    }

    @Override
    public void invalidTileHandler() {
        out.println("Tessera scelta non disponibile, scegline un'altra!");
        goOnPicking = true;
        setMoveOn();
    }

    @Override
    public void showPlayersList(ArrayList<Player> playerList) {
        int i=0;
        String nickName = null;

        String j = String.valueOf(playerList.size());
        out.println("In the current Game we have "+j+"players whose Names are:\n");
        while(i<playerList.size()){
             nickName = playerList.get(i).getNickname();
            out.println(nickName+" , ");
            i++;
        }

    }

    @Override
    public void showWinMessage(String stringWinning) {

    }



    @Override
    public void showNickCurrentPlayer(String Nickname){
    out.println("The current player is:  "+Nickname);
    }

    @Override
    public void showLivingRoom(SimpleLivingRoom livingRoom) {
        ItemTile[][] board = livingRoom.getGameTable();

        //ogni volta stampo la legenda
        out.println("Legenda: []=empty, [X]=unavailable, [B]=blue tile, [C]=cyan tile," +
                " [Y]=yellow tile, [P]=pink tile, [W]=white tile, [G]=green tile");

        //stampo livingRoom
        for(int i=0;i<9;i++) {
            out.println();
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == null) {
                    out.print("[]");
                } else {
                    switch (board[i][j].getColour()) {
                        case GREEN -> out.print("[G]");
                        case BLUE -> out.print("[B]");
                        case CYAN -> out.print("[C]");
                        case PINK -> out.print("[P]");
                        case WHITE -> out.print("[W]");
                        case YELLOW -> out.print("[Y]");
                        case VOID -> out.print("[X]");
                    }
                }
            }
        }
        out.println();
    }


    @Override
    public void showPlayerShelf(SimpleShelf shelf) {
        PlayableItemTile[][] personalShelf = shelf.getStructure();

        //ogni volta stampo la legenda
        out.println("Legenda: []=empty, [X]=unavailable, [B]=blue tile, [C]=cyan tile," +
                " [Y]=yellow tile, [P]=pink tile, [W]=white tile, [G]=green tile , the number near the color"+
                "rappresents the unique ID code of the tile");

        //stampo shelf
        for(int i=0;i<6;i++) {
            out.println();
            for (int j = 0; j < 5; j++) {
                if (personalShelf[i][j] == null) {
                    out.print("[]");
                } else {
                    switch (personalShelf[i][j].getColour()) {
                        case GREEN -> out.print("[G , " + personalShelf[i][j].getIdCode()+"]");
                        case BLUE -> out.print("[B, " + personalShelf[i][j].getIdCode()+"]");
                        case CYAN -> out.print("[C, " + personalShelf[i][j].getIdCode()+"]");
                        case PINK -> out.print("[P, " + personalShelf[i][j].getIdCode()+"]");
                        case WHITE -> out.print("[W, " + personalShelf[i][j].getIdCode()+"]");
                        case YELLOW -> out.print("[Y, " + personalShelf[i][j].getIdCode()+"]");
                        case VOID -> out.print("[X, " + personalShelf[i][j].getIdCode()+"]");
                    }
                }
            }
        }
        out.println();
    }



    @Override
    public void showPartialPoint(int point) {
    out.println("Your total point so far is :"+point);
    }

    @Override
    public void showDisconnectionMessage(String disconnectedPlayerNickname, String disconnectionMessage) {

    }

    @Override
    public void showErrorMessage(String errorMessage) {

    }

    @Override
    public void showDefaultMessage(String defaultMessage) {

    }

    @Override
    public void showMatchSituation(List<String> actualPlayers, List<Shelf> actualShelf, String actualPlayerNickname) {

    }

    public void resetTUI(){
        out.println("Cleaning of the textual interface...");
        out.flush();
    }

    public void connectToServerFromTUI(String address, int port){
        //se implementiamo socket si deve anche definire tipo di connessione

        out.println("Per favore, indica il tipo di connessione desiderata [0=RMI, 1=Socket]: ");
        int connectionType = scanner.nextInt();

        if (connectionType == 0) {
            try {
                observers.add(viewManager.connectRMI(address, port, this));
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            out.println("Connessione col Server riuscita!");
        } else {
            //observers.add(viewManager.connectSocket(address, port, this));
        }
    }
}

