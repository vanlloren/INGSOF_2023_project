package client.view;
import Observer.*;
import Network.ClientSide.IOManager;
import server.Model.PlayableItemTile;
import server.Model.Player;
import server.Model.Shelf;
import server.Model.GameModel


import java.io.PrintStream;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.*;


public class TUI extends ViewObservable implements View {  //dovrà diventare observable dal client
    private final PrintStream out;
    private final Scanner scanner = new Scanner(System.in);

    private final IOManager viewManager = new IOManager();

    protected final List<ViewObserver> observers = new ArrayList<>();

    private boolean checker = false;
    public TUI(){
        this.out = System.out;
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

            scanner.nextLine(); // consume the newline character
            controller.setnumberOfPlayers(numOfPlayers);
            gameState.addPlayers;
            while (controller.gameState.equals(addPlayers)) {
                for (int i = 1; i <= numOfPlayers; i++) {
                    out.print("Enter nickname for player " + i + ": ");
                    String nickname = askNickname();
                    controller.addPlayer(nickname);
                }
                out.println("Game starting...");

                while (controller.gameState.equals(GameState.FlowGame)) {
                    out.println(controller.getGameBoard().getLivingRoom().toString());
                    out.println("Next tile: " + controller.getGameBoard().getNextInGameTile());
                    out.print("Enter row to pick tile from: ");
                    int row = scanner.nextInt();
                    System.out.print("Enter column to pick tile from: ");
                    int column = scanner.nextInt();
                    nextLine(); // consume the newline character
                    controller.pickTile(row, column);

                    for (Player player : controller.getPlayers()) {
                        System.out.println(player.getNickname() + "'s tiles:");
                        for (PlayableItemTile tile : player.getTiles()) {
                            out.print(tile.toString() + " ");
                        }
                        System.out.println();
                    }

                    out.print("Enter row to place tile on: ");
                    int placeRow = scanner.nextInt();
                    System.out.print("Enter column to place tile on: ");
                    int placeColumn = scanner.nextInt();
                    scanner.nextLine(); // consume the newline character
                    controller.putTile(placeRow, placeColumn);

                    System.out.println();
                }

                out.println("Game over!");
            }
        }
    }

    @Override
    public String askServerInfo(){
        out.println("Per favore, inserisci alcune informazioni:");
        String serverAddress;
        do {
            out.println("Inserisci l'indirizzo del Server [default = localhost]:");
            // effettuo check di validità su in.nextLine();
            serverAddress = scanner.nextLine();
            if(checkAddressValidity(serverAddress){
                checker = true;
            }else{
                out.println("Indirizzo non valido!");
                checker = false;
            }
        }while(!checker);

        return serverAddress;
        //dovrò fornire a qualcuno serverAddress e serverPort per effettuare il collegamento
    }

    public int askServerPort() {
        do {
            out.println("Inserisci la porta del Server [default = ??]:");
            //effettuo check validità su in.nextLine();
            int portNum = scanner.nextInt();
            if (checkPortValidity(portNum)) {
                checker = true;
            } else {
                out.println("Porta del server non valida!");
                checker = false;
            }
        } while (!checker);

        return portNum;
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

        out.println("Il nickname scelto è: " + nickName);

    }

    @Override
    public void askPlayersNumber() {
        int playersNum;

        out.println("Nickname accepted!");
        out.println("You are the first player of the game! Please, insert the number of total player for the match [min=2, max=4]:");
        playersNum = scanner.nextInt();
        while(playersNum<2 || playersNum>4){
            out.println("The number of player is not valid!\n");
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
    }

    @Override
    public void askStopPicking() {
            out.println("Would you like to keep picking tiles?[Y/N]");
            String picking = scanner.nextLine();
            while(picking != "Y" || picking !="N"){
                out.println("Symbol not recoignized, please try  again...");
                picking= scanner.nextLine();
            }
        String finalPicking = picking;
        notifyObserver(obs-> obs.onUpdateAskKeepPicking(finalPicking));
    }



    @Override
    public void askTileToPut(ArrayList<PlayableItemTile> tilesInPlayerHand) {

    }

    @Override
    public void askPlacingTileInShelfPosition() {

    }

    @Override
    public void showLoginResults(boolean nickAccepted, String chosenNickname) {
        if(nickAccepted){
            out.println("Login successful, nickname accepted!");
        }else{
            out.println("Nickname already chosen, choose another nickname!");
            askNickname();
        }

    }

    @Override
    public void showLobby(List<String> nicknameList, int numPlayers) {

    }

    @Override
    public void showPlayersList(ArrayList<Player> playersList) {
        int i=0;
        String nickName = null;
        String j = String.valueOf(playersList.size());
        out.println("In the current Game we have "+j+"players:");
        while(i<playersList.size())
             nickName = playersList.get(i).getNickname();
            out.println(nickName+", ");

    }

    @Override
    public void showWinMessage(String winnerNickname) {

    }

    @Override
    public void showLivingRoom(ArrayList<PlayableItemTile> livingRoom) {


    }

    @Override
    public void showPlayerShelf(ArrayList<PlayableItemTile> playerShelf) {

    }

    @Override
    public void showPointMessage(int addingpoint) {
        //passo punteggio derivato dal completamento di un obiettivo e lo mostro

    }

    @Override
    public void showPointCounter(int  pointCount) {
    String point = String.valueOf(pointCount);
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

