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

    private final IOManager viewManager = new IOManager();

    protected final List<ViewObserver> observers = new ArrayList<>();

    private boolean gameOn=true;

    private final TurnView turnView;

    public String Nickname;

    private boolean checker = false;
    public TUI(TurnView turnView){
        this.out = System.out;
        this.turnView = turnView;
    }

    public void setNickname(String nickname) {
        this.Nickname = nickname;
    }

    //Implementando il metodo Runnable ereditiamo tutte le sue classi e oggetti
    //Run è un costruttore basilare costruito direttamente dal metodo Runnable al posto di init
    public void init() {
        while (true) {
            out.println("Welcome to the game MyShelfie!");
            String serverAddress = askServerInfo();
            int portNum = askServerPort();
            connectToServerFromTUI(serverAddress, portNum);
            askNickname();


        }
    }

    public void resetGameOn() {
        this.gameOn = false;
    }

    public void matchReadyTUI(){
        while(gameOn){
            //qui è dove la TUI deve lavorare durante la partita.
            //quando gameOn diventa false, la TUI si chiude perché è finita la partita
        }
    }
    @Override
    public TurnView getGameModelView() {
        return turnView;
    }


    public void askPlayerNextMove(){
        String picking;
        scanner.nextLine();
            do {
                scanner.nextLine();
                out.println("Press 1 if you want to PICK A TILE FROM LIVING ROOM " +
                        "Press 2 if you want to SEE THE LIVINGROOM " +
                        "Press 3 if you want to SEE THE PLAYERS IN YOUR GAMELOBBY " +
                        "Press 4 if you want to SEE YOUR PERSONAL SHELF" +
                        "Press 5 if you want to SEE YOUR CURRENT SCORING" +
                        "Press 6 if you want to SEE THE NICKNAME OF THE PLAYER WHO'S PLAYING\n"
                );

                picking = scanner.next();
                if (!picking.equals("1") || !picking.equals("2") || !picking.equals("3") || !picking.equals("4") || !picking.equals("5") ||
                        !picking.equals("6") || !picking.equals("7") || !picking.equals("8") || !picking.equals("9")) {
                    out.println("Symbol not recognized, please try  again...\n");
                }
            }
            while (!picking.equals("1") || !picking.equals("2") || !picking.equals("3") || !picking.equals("4") || !picking.equals("5") ||
                    !picking.equals("6") || !picking.equals("7") || !picking.equals("8") || !picking.equals("9"));

            switch (picking) {
                case "1":
                    if (turnView.getNickNameCurrentPlayer().equals(this.Nickname))
                        askMovingTilePosition(turnView.getGameBoard().getLivingRoom().getAvailableTiles());
                    else {
                        System.out.println("IT IS NOT YOUR TURN YET: PLEASE WAIT\n ");
                        askPlayerNextMove();
                    }
                case "2":
                    showLivingRoom();
                    askPlayerNextMove();
                case "3":
                    showPlayersList();
                    askPlayerNextMove();
                case "4":
                    showPlayerShelf();
                    askPlayerNextMove();
                case "5":
                    showPointMessage();
                    askPlayerNextMove();
                case "6":
                    showNickCurrentPlayer();
                    askPlayerNextMove();
            }
    }


    @Override
    public String askServerInfo(){
        out.println("Per favore, inserisci alcune informazioni:\n");
        String serverAddress;
        do {
            scanner.nextLine();
            out.println("Inserisci l'indirizzo del Server [default = localhost]:\n");
            serverAddress = scanner.next();
            if(checkAddressValidity(serverAddress)){
                checker = true;
            }
            else{
                out.println("Indirizzo non valido!\n");
                checker = false;
            }
        }while(!checker);

        return serverAddress;
        //dovrò fornire a qualcuno serverAddress e serverPort per effettuare il collegamento
    }

    private boolean checkAddressValidity(String serverAddress) {
    }

    public int askServerPort() {
        int portNum;
        do {
            scanner.nextLine();
            out.println("Inserisci la porta del Server [default = ??]:\n");
            //effettuo check validità su in.nextLine();
            portNum = scanner.nextInt();
            if (checkPortValidity(portNum)) {
                checker = true;
            } else {
                out.println("Porta del server non valida!\n");
                checker = false;
            }
        } while (!checker);

        return portNum;
    }

    public boolean checkPortValidity(int portNum) {
    }

    public void fullLobbyTerminateUI(){
        out.println("Spiacente, la lobby è piena, riprova più tardi!");
        resetTUI();
        resetGameOn();
    }

    @Override
    public void askNickname() {
        out.println("Enter nickname please: \n");
        String nickName = scanner.nextLine();
        notifyObserver(obs -> {
            try {
                obs.onUpdateNickname(nickName);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });

        out.println("Il nickname scelto è: " + nickName+"\n");
    }

    @Override
    public void askPlayersNumber() {
        int playersNum;

        out.println("Nickname accepted!\n");
        out.println("You are the first player of the game! Please, insert the number of total player for the match [min=2, max=4]:\n");
        playersNum = scanner.nextInt();
        while(playersNum<2 || playersNum>4){
            out.println("The number of player is not valid!\n");
            out.println("Insert the number of total players [min=2, max=4]:\n");
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
                out.println("Symbol not recoignized, please try  again...\n");
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
    }



    @Override
    public void askTileToPut(ArrayList<PlayableItemTile> tilesInPlayerHand) {
        //PRIMA COSA STAMPA LA TILES IN PLAYER HAND COSI PLAYER VEDE CHE CARTE HA IN MANO
        //POI STAMPA TUTTA LA SHELF COSI IL PLAYER VEDE LA SHELF IN TEMPO REALE
        //POI GESTISCI INSERIMENTO COORDINATE E INVIO MESSAGGIO CON NOTIFICA AGLI OBSERVER


    }

    @Override
    public void askPlacingTileInShelfPosition() {

    }

    @Override
    public void showLoginResults(boolean nickAccepted, String chosenNickname) {
        if(nickAccepted){
            out.println("Login successful, nickname accepted!\n");
        }else{
            out.println("Nickname already chosen, choose another nickname!\n");
            askNickname();
        }

    }



    @Override
    public void showPlayersList() {
        int i=0;
        String nickName = null;
        ArrayList<Player> playersList = turnView.getPlayerInGame();
        String j = String.valueOf(playersList.size());
        out.println("In the current Game we have "+j+"players whose Names are:\n");
        while(i<playersList.size()){
             nickName = playersList.get(i).getNickname();
            out.println(nickName+" , ");
            i++;
        }

    }

    @Override
    public void showWinMessage(String stringWinning) {

    }

    @Override
    public void showNickCurrentPlayer(){

    }

    @Override
    public void showLivingRoom(LivingRoom livingRoom) {
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
    public void showNumberOfPlayers(){

    }

    @Override
    public void showPlayerShelf() {
    PlayableItemTile[][] shelfTable = turnView.getShelfTable();
      // STAMPA TUTTA LA SHELF
    }

    @Override
    public void showPointMessage() {


    }

    @Override
    public void showPartialPoint() {
    String point = String.valueOf(turnView.getPartialPoint());
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
        try {
            if (connectionType == 0) {
                observers.add(viewManager.connectRMI(address, port, this));
                out.println("Connessione col Server riuscita!");
            } else {
                //observers.add(viewManager.connectSocket(address, port, this));
            }
        }catch (Exception e){

        }
    }



}

