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

    private ArrayList<Player> playerList;


    private String currPlayer;

    public String nickname;
    private final Semaphore semaphore= new Semaphore(0);

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
    @Override
    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }


    //Implementando il metodo Runnable ereditiamo tutte le sue classi e oggetti
    //Run è un costruttore basilare costruito direttamente dal metodo Runnable al posto di init
    public void init() throws InterruptedException {
        boolean isConnected = false;
        out.println("╔════════════════════════════════════════╗");
        out.println("║    --------------------------------    ║");
        out.println("║    |*****WELCOME TO MYSHELFIE*****|    ║");
        out.println("║    --------------------------------    ║");
        out.println("╚════════════════════════════════════════╝");

        while(!isConnected) {
            try {
                String serverAddress = askServerInfo();
                int portNum = askServerPort();
                connectToServerFromTUI(serverAddress, portNum);
                isConnected = true;
            } catch (RuntimeException e) {
                out.println("Errore di connessione: indirizzo ip del server sbagliato...riprova");
            }
        }
        scanner.nextLine();
        new Thread(() -> {
            while(true){
                try{
                    OnVerifyConnection();
                }
                catch (RuntimeException e){
                    out.println("ci sono stati problemi di connessione, partita cancellata");
                    System.exit(0);
                }
            }
        }).start();
        while (needNick) {
                try {
                    askNickname();
                }catch (RuntimeException e){
                    return;
                }
        }
        semaphore.acquire();
        notifyObserver(obs -> obs.onUpdateSetPlayersList());
        do{
        askPlayerNextMove();
        }while (!isLastTurn);


        out.println("it was your last turn please wait for final results");
     while (true){
         if (!gameOn) break;
     }
    }



    private void OnVerifyConnection() {
        notifyObserver(obs -> {
            try {
                obs.onConnectionVerify();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });

    }


    @Override
    public void riprendiEsecuzione() {
        semaphore.release();
    }

    public void resetGameOn() {
        this.gameOn = false;
    }

    /**
     *
     */
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
                } else {
                    System.out.println("IT IS NOT YOUR TURN YET: PLEASE WAIT ");
                    out.println();

                }
            }
            case 2 -> {
                try{notifyObserver(obs -> obs.onUpdateShowLivingRoom());}
                catch (RuntimeException e){
                    System.exit(0);
                }
            }
            case 3 -> showPlayersList(playerList);
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

    @Override
    public void WriteInChat(){
        String chatMessage;
        boolean isValid = false;
        boolean isNameValid = false;
        int chosenOption = -1;
        String receiver = "Everyone";
        out.println("Se vuoi mandare il messaggio in privato premi 1 altrimenti 0");
        do{
            String num = scanner.next();
            try {
                 chosenOption = Integer.parseInt(num);
                if (chosenOption != 1 && chosenOption!= 0)
                    out.println("numero non valido..riprova ");
                else isValid = true;
            }
            catch (NumberFormatException ex) {
                out.println( "Stringa non valida per favore immetti stringa numerica!");
            }}
        while(!isValid);
        if (chosenOption == 1) {
            showPlayersList(playerList);
            do{
                out.println("scrivi il nome del giocatore a cui vuoi scrivere ");
                receiver = scanner.next();
                for(Player player : playerList){
                    if (receiver.equals(player.getNickname())) {

                        isNameValid = true;
                        break;
                    }
                }
                if(!isNameValid){
                    out.println("Non ci sono giocatori con quel nickname..riprova");
                }
            }while(!isNameValid);
        }
        out.println("Scrivi a seguire il contenuto del tuo messaggio ");
        chatMessage = scanner.nextLine();
        while(chatMessage.equals("")) {
            chatMessage = scanner.nextLine();
        }
        String finalChatMessage = chatMessage;
        String finalReceiver = receiver;
        notifyObserver(obs -> {
            try {
                obs.onUpdateChat(this.nickname, finalChatMessage, finalReceiver);
                out.println("Messaggio inviato!");
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
        }



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

    @Override
    public void showLoginResults(boolean nickAccepted, String chosenNickname) {
        if(nickAccepted){
            out.println("Login successful, nickname accepted!");
            out.println("Il nickname scelto è: " + this.nickname);
            resetNeedNick();
        }else{
            out.println("Nickname already chosen, choose another nickname!");
        }

    }


    @Override
    public void showPlayersList(ArrayList<Player> playerList) {
        int i=0;
        String nickName;

        String j = String.valueOf(playerList.size());
        out.println("Nella partita ci sono "+j+" giocatori :");
        while(i<playerList.size()){
             nickName = playerList.get(i).getNickname();
            out.print(nickName+" , ");
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

    public void resetTUI(){
        out.println("Cleaning of the textual interface...");
        //out.flush();
    }

    public void connectToServerFromTUI(String address, int port){
        //se implementiamo socket si deve anche definire tipo di connessione
        boolean isValid = false;
        String connection;
         int connectionType;
        do{
            out.println("Per favore, indica il tipo di connessione desiderata [0=RMI, 1=Socket]: ");
            connection = scanner.next();
            try{
               connectionType = Integer.parseInt(connection);

                if(connectionType==0){
                    isValid = true;
                }
                else {
                    out.println("Numero non valido");
                }
            }
            catch (NumberFormatException e){
                out.println("Errore! per favore inserisci stringa numerica");
            }
        }while(!isValid);

            try {
                observers.add(viewManager.connectRMI(address, port, this));
                out.println("Connessione col Server riuscita!");
            } catch (Exception e) {
                throw new RuntimeException();
            }

    }
}

