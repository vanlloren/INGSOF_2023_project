package client.view;

import Network.ClientSide.IOManager;
import server.Model.PlayableItemTile;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TUI implements view{  //dovrà diventare observable dal client
    private final PrintStream out;
    private final Scanner in = new Scanner(System.in);

    private IOManager viewManager = new IOManager();

    private boolean checker = false;
    public TUI(){
        this.out = System.out;
    }
    public void init(){
        out.println("Benvenuto nel gioco MyShelfie!");

        int portNum = 0;
        String serverAddress = askServerInfo(portNum);
        String nickname = askNickname();
        connectToServerFromTUI(serverAddress, portNum, nickname, this);
    }

    @Override
    public String askServerInfo(int portNum){
        out.println("Per favore, inserisci alcune informazioni:");
        String serverAddress;

        do {
            out.println("Inserisci l'indirizzo del Server [default = localhost]:");
            // effettuo check di validità su in.nextLine();
            serverAddress = in.nextLine();
            if(checkAddressValidity(serverAddress){
                checker = true;
            }else{
                out.println("Indirizzo non valido!");
                checker = false;
            }
        }while(!checker);

        do {
            out.println("Inserisci la porta del Server [default = ??]:");
            //effettuo check validità su in.nextLine();
            portNum = in.nextInt();
            if(checkPortValidity(portNum)){
                checker = true;
            }else{
                out.println("Porta del server non valida!");
                checker = false;
            }
        }while(!checker);

        return serverAddress;
        //dovrò fornire a qualcuno serverAddress e serverPort per effettuare il collegamento
    }
    @Override
    public String askNickname() {
        out.println("Inserisci il Nickname che vuoi utilizzare:");
        String nickName = in.nextLine();

        //dovrò fornire nickName al server in qualche modo per il controllo dell'univocità

        out.println("Il nickname scelto è: " + nickName);
        return nickName;
    }

    @Override
    public void askPlayersNumber() {

        out.println("Sei il primo giocatore della partita! Per favore, inserisci il numero di giocatori totali [min=2, max=4]:");

        int playersNum;
        playersNum = in.nextInt();
        while(playersNum<2 || playersNum>4){
            out.println("Numero giocatori non valido!");
            out.println("Inserisci il numero di giocatori totali [min=2, max=4]:");
            playersNum = in.nextInt();
        }

        //dovrò fornire playersNum al server in qualche modo

        out.println("Il numero di giocatori scelto è: " + playersNum);
    }

    @Override
    public void askMovingTilePosition(ArrayList<PlayableItemTile> availableTiles) {

    }

    @Override
    public void askStopPicking() {

    }

    @Override
    public void askTileToPut(ArrayList<PlayableItemTile> tilesInPlayerHand) {

    }

    @Override
    public void askPlacingTileInShelfPosition() {

    }

    @Override
    public void showLoginResults(boolean nickAccepted, boolean connectionOn, String chosenNickname) {

    }

    @Override
    public void showPlayersList(List<String> playersList) {

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
    public void showPointMessage() {

    }

    @Override
    public void showPointCounter(int pointCount) {

    }

    @Override
    public void showDisconnectionMessage(String disconnectedPlayerNickname, String disconnectionMessage) {

    }

    @Override
    public void showErrorMessage(String errorMessage) {

    }

    public void connectToServerFromTUI(String address, int port, String nickname, TUI textualInterface){
        //se implementiamo socket si deve anche definire tipo di connessione

        out.println("Per favore, indica il tipo di connessione desiderata [0=RMI, 1=Socket]: ");
        int connectionType = in.nextInt();
        try {
            if (connectionType == 0) {
                viewManager.connectRMI(address, port, nickname, textualInterface);
            } else {
                viewManager.connectSocket(address, port, nickname, textualInterface);
            }
        }catch (Exception e){

        }
    }
}
