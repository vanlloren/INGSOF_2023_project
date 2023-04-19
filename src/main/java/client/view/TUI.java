package client.view;

import server.Model.PlayableItemTile;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TUI implements view{
    private final PrintStream out;
    private final Scanner in = new Scanner(System.in);

    private boolean checker = false;
    public TUI(){
        this.out = System.out;
    }
    public void init(){
        out.println("Benvenuto nel gioco MyShelfie!");

        askServerInfo();
    }

    public void askServerInfo(){
        out.println("Perfavore, inserisci alcune informazioni:");

        do {
            out.println("Inserisci l'indirizzo del Server [default = localhost]:");
            // effettuo check di validità su in.nextLine();
            String serverAddress = in.nextLine();
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
            String serverPort = in.nextLine();
            if(checkPortValidity(serverPort)){
                checker = true;
            }else{
                out.println("Porta del server non valida!");
                checker = false;
            }
        }while(!checker);

        //dovrò fornire a qualcuno serverAddress e serverPort per effettuare il collegamento
    }
    @Override
    public void askNickname() {

    }

    @Override
    public void askPlayersNumber() {

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
}
