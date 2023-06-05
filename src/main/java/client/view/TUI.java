package client.view;
import Observer.*;
import Network.ClientSide.IOManager;
import server.Model.*;


import java.io.PrintStream;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.*;
import java.util.concurrent.Semaphore;


public class TUI extends ViewObservable implements View {  //dovrà diventare observable dal client
    private final PrintStream out;
    private final Scanner scanner = new Scanner(System.in);

    private boolean needNick=true;

    private final IOManager viewManager = new IOManager();

    private boolean gameOn=true;

    private boolean isLastTurn = false;


    private boolean goOnPicking=true;
    private boolean moveOn;
    private String currPlayer;

    public String nickname;
    private boolean isPaused = true;
    private Semaphore semaphore= new Semaphore(0);

    private boolean checker = false;


    public TUI(){
        this.out = System.out;
    }
  @Override
    public void setNickname(String nickname) {
        this.nickname = nickname;

    }

    @Override
    public void setIsTurn() {
this.isLastTurn = true;
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
        while (needNick) {
            askNickname();
        }

        try {
            semaphore.acquire();
            do{
            askPlayerNextMove();
            }while (!isLastTurn);
        } catch (InterruptedException e) {
            out.println("ci sono stati problemi di connessione, partita cancellata");
            throw new RuntimeException(e);
        }
        catch (RuntimeException e){
            out.println("ci sono stati problemi di connessione, partita cancellata");
        }
        out.println("it was your last turn please wait for final results");
     while (gameOn){
     }
    }

    @Override
    public void riprendiEsecuzione() {
        semaphore.release();
    }

    public void resetGameOn() {
        this.gameOn = false;
    }

    public void setGameOn(){
        this.gameOn=true;
    }

    public void resetPaused(){
        this.isPaused=false;
    }



    @Override
    public void askPlayerNextMove(){
        String choose;
        int picking = -1;
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

                choose = scanner.next();
                try {
                    picking = Integer.parseInt(choose);
                    if (picking != 1 && picking != 2 && picking != 3 && picking != 4 && picking != 5 && picking != 6 && picking != 7) {
                        out.println("Symbol not recognized, please try again...\n");
                        askPlayerNextMove();
                    }
                }
                catch (NumberFormatException ex) {
                   out.println( "Not valid string please write only a number in the string format");
                   askPlayerNextMove();
                }
            }
            while (picking != 1 && picking != 2 && picking != 3 && picking != 4 && picking != 5 && picking != 6 && picking != 7);

        switch (picking) {
            case 1 -> {
                notifyObserver(obs -> obs.onUpdateShowCurrPlayer());
                if (currPlayer.equals(this.nickname)) {
                    askMovingTilePosition();
                    //qua ci va la chiamata a quello che inserisce le tiles nella shelf
                } else {
                    System.out.println("IT IS NOT YOUR TURN YET: PLEASE WAIT ");
                    out.println();

                }
            }
            case 2 -> notifyObserver(obs -> obs.onUpdateShowLivingRoom());
            case 3 -> notifyObserver(obs -> obs.onUpdateShowPlayersList());
            case 4 -> notifyObserver(obs -> obs.onUpdateShowPlayerShelf(nickname));
            case 5 -> {
                final int[] points = new int[1];
                notifyObserver(obs -> points[0] = obs.onUpdateShowPartialPoint(nickname));
                showPartialPoint(points[0]);
            }
            case 6 -> notifyObserver(obs -> obs.onUpdateShowNickCurrPlayer());
            case 7 -> WriteInChat();
        }

    }

    public void setMoveOn(){
        moveOn=true;
    }



    @Override
    public void WriteInChat(){
        String chatMessage;
        out.println("Write in the following line the content of your message");
        chatMessage = scanner.nextLine();
        while(chatMessage.equals("")) {
            chatMessage = scanner.nextLine();
        }

        String finalChatMessage = chatMessage;
        notifyObserver(obs -> {
            try {
                obs.onUpdateChat(this.nickname, finalChatMessage);
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
        return portNum >= 1024;
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
        String num;
        int playersNum = -1;
        boolean isValid = false;
        out.println("Nickname accepted!");
        out.println("Il nickname scelto è: " + this.nickname);
        resetNeedNick();
           out.println("You are the first player of the game! Please, insert the number of total player for the match [min=2, max=4]:");
     do{ out.println("Insert the number of total players [min=2, max=4]:");
         num = scanner.next();

        try {
            playersNum = Integer.parseInt(num);
            if (playersNum<2 || playersNum>4)
                out.println("The number of player is not valid!");
                else isValid = true;
            }
        catch (NumberFormatException ex) {
            out.println( "Not valid string please write only a number in the string format");

        }}
        while(!isValid);

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
    public void askMovingTilePosition(){
        notifyObserver(obs -> obs.onUpdateShowLivingRoom());
        notifyObserver(obs -> obs.onUpdateShowAvailableTiles());



        notifyObserver(obs -> {
            try {
                obs.onUpdateToPickTile();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
            askTileToPut();
        };



    @Override
    public void askTileToPut() {
        notifyObserver(obs -> obs.onUpdateShowPlayerShelf(nickname));
        notifyObserver(obs -> {
            try {
                obs.onUpdateToStartPutting();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
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
                    out.print("[ ]");
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
                " [Y]=yellow tile, [P]=pink tile, [W]=white tile, [G]=green tile , the number near the color "+
                "represents the unique ID code of the tile");

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



    public void resetTUI(){
        out.println("Cleaning of the textual interface...");
        //out.flush();
    }

    public void connectToServerFromTUI(String address, int port){
        //se implementiamo socket si deve anche definire tipo di connessione

        out.println("Per favore, indica il tipo di connessione desiderata [0=RMI, 1=Socket]: ");
        int connectionType = scanner.nextInt();

        if (connectionType == 0) {
            try {
                observers.add(viewManager.connectRMI(address, port, this));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            out.println("Connessione col Server riuscita!");
        } else {
            //observers.add(viewManager.connectSocket(address, port, this));
        }
    }
}

