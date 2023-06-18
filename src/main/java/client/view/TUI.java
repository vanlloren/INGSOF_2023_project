package client.view;
import Observer.*;
import Network.ClientSide.IOManager;
import server.Model.*;
import java.io.PrintStream;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.*;
import java.util.concurrent.Semaphore;


/**
 * This class manages the initial outputs and inputs demands when the {@link client.ClientApp ClientApp} is launched from terminal
 * Its functionalities include the methods to interact with the user so that he can enter his turn or see
 * the model data of th game
 */
public class TUI extends ViewObservable implements View {
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


    /**
     * This method creates an instance of {@link TUI TUI}.
     * It binds the attribute {@link PrintStream out} to the {@code System.out}
     */
    public TUI(){
        this.out = System.out;
    }

    /**
     * Sets the actual {@link String nickname} of the user that has joined the game only when its nickname has been
     * previously accepted.
     * @param nickname the instance of {@link String nickname} of the current TUI
     */
  @Override
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
t    *this method sets the attribute  {@code isLastTurn} true only when the last turn played
     *from this user was their last one
     */
    @Override
    public void setIsTurn() {
    this.isLastTurn = true;
    }

    /**
     *this method is called only when the {@code nickname} has been accepted from the model and
     * as a consequence sets the attribute {@code needNick} to false, which indicates that no more requests
     * of a valid {@code nickname} are needed
     */
    public void resetNeedNick(){
        this.needNick = false;
    }

    /**
     *this method is called when the number of Player has reached the requested size,
     *Sets the actual {@link ArrayList<Player> playerList} of the current match
     *
     * @param playerList the instance of {@link ArrayList<Player> playerList} of the current match
     */
    @Override
    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }

    /**
     *it manages the correct sequence of connection to the {@link Network.ServerSide.RMIServer server}
     * with also verifying a valid nickname following the rules of the game. It runs a thread that pings in every instance the server
     * to handle the disconnection of {@link Network.ServerSide.RMIServer server}.
     * This method stays alive until the game has reached its end
     *
     */
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


        out.println("E' stato il tuo ultimo turno...attendi risultati finali");
     while (true){
         if (!gameOn) break;
     }
    }


    /**
     * This is the method that is actually responsible for the disconnection handling.
     * It calls internally a method of the {@link Network.ServerSide.RemoteServerImplementation server} and
     * use a try-catch to see if there is a {@code RemoteException}
     */
    private void OnVerifyConnection() {
        notifyObserver(obs -> {
            try {
                obs.onConnectionVerify();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     *It just calls the release of the attribute {@code semaphore} so that the
     * execution of the init can keep on after LORE CONTROLLA NON LO SO
     */
    @Override
    public void riprendiEsecuzione() {
        semaphore.release();
    }

    /**
     *it sets the attribute {@code gameOn} to {@code false}
     *when the user has played his last turn
     */
    public void resetGameOn() {
        this.gameOn = false;
    }

    /**
     *it allows the user to choose between different options on the basis of
     *the given input on the command line:
     *When the local variable {@code choose} equals 1 it makes the players start their turn
     *The others are for visualise the data game
     */
    @Override
    public void askPlayerNextMove(){

        String choose;
        int picking = -1;
            do {
                out.println("""
                        Premi 1 se vuoi entrare nel tuo turno di gioco\s
                        Premi 2 se vuoi vedere la LivingRoom\s
                        Premi 3 per vedere i giocatori in partita\s
                        Premi 4 per vedere la tua PersonalShelf\s
                        Premi 5 per vedere il tuo punteggio\s
                        Premi 6 per vedere il giocatore di turno\s
                        Premi 7 per scrivere in chat"""
                );

                choose = scanner.next();
                try {
                    picking = Integer.parseInt(choose);
                    if (picking != 1 && picking != 2 && picking != 3 && picking != 4 && picking != 5 && picking != 6 && picking != 7) {
                        out.println("Simbolo non riconosciuto...riprova");
                        askPlayerNextMove();
                    }

                }
                catch (NumberFormatException ex) {
                   out.println("Stringa non valida per favore inserisci valore numerico per la scelta tra le opzioni");
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
                    System.out.println("Non è ancora il tuo turno aspetta!");
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

    /**
     *It is the method that allows the user to write a message to be sent in private or
     * to all {@link Player players} in the {@link ArrayList<Player> playerList} of the game
     */
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

    /**

     */
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

    /**
     * This method check if the given input is a valid ipAddress according
     * to the well known partition
     * @param ipAddress is a String that should contain an ipAddress
     * @return {@code true} if the given address is valid, {@code false} otherwise
     */
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

    /**
     *the user through this method is asked to write the server port to connect
     * while checking its validity
     */
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

    /**
     * @param portNum represents the port number chosen by the user
     * @return {@code true} if {@code portNum} is >=1024, {@code false} otherwise
     */
    public boolean checkPortValidity(int portNum) {
        return portNum >= 1024;
    }

    /**
     *This method is called when this current user is trying to connect to the {@link Network.ServerSide.RMIServer server}
     * that has already reached its full size
     */
    public void fullLobbyTerminateUI(){
        out.println("Spiacente, la lobby è piena, riprova più tardi!");
        resetTUI();
        resetGameOn();
    }

    /**
     *Through this method the user is asked to write the {@code nickname} it will use ,
     * and the method through to a {@code notifyObserver} sends the request to the {@link Network.ServerSide.RMIServer server }
     */
    @Override
    public void askNickname() {

        out.println("Scrivi il tuo Nickname: ");
        String nickName = scanner.nextLine();
        notifyObserver(obs -> {
            try {
                obs.onUpdateNickname(nickName);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * @param currPlayer represents the new current {@link Player player} that is set to the
     *  attribute {@code currPlayer} of this {@code TUI} instance
     */
    @Override
    public void setCurrPlayer(String currPlayer) {
        this.currPlayer = currPlayer;

    }

    /**
     *This method asks only the first user connected to the {@link Network.ServerSide.RMIServer}
     * to enter the number of {@link Player player} for the current game
     */
    @Override
    public void askPlayersNumber() {
        String num;
        int playersNum = -1;
        boolean isValid = false;
        out.println("Nickname accettato!");
        out.println("Il nickname scelto è: " + this.nickname);
        resetNeedNick();
           out.println("Sei il primo giocatore entrato sul server!");
     do{ out.println("Inserisci il numero di giocatori per questa partita [min=2, max=4]:");
         num = scanner.next();

        try {
            playersNum = Integer.parseInt(num);
            if (playersNum<2 || playersNum>4)
                out.println("Numero di giocatori non valido...!");
                else isValid = true;
            }
        catch (NumberFormatException ex) {
            out.println("Stringa non valida per favore inserisci valore numerico per il numero di giocatori");

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

    /**
     *This method handles the request of this user to start picking the {@link PlayableItemTile Tile}
     * from the {@link LivingRoom livingRoom} while showing the current disposition of the latter and calls
     * afterward the method {@code askTileToPut} that manages the insertion in the {@link Shelf personalShelf}
     */
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

     /**
     *This method handles the request of this user to start inserting the {@link PlayableItemTile Tile}
     * from the {@link LivingRoom livingRoom} into the {@link Shelf personalShelf} while showing the current disposition of the latter
     */
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

    /**
     *This method prints the {@code chosenNickname} only if the param {@code nickAccepted}
     * is {@code true}, otherwise it prints a negative comment
     * @param nickAccepted contains the result from the {@link Network.ServerSide.RMIServer server}
     * of the {@code nickname} written previously.
     * @param chosenNickname is the same {@code nickname} written when requested
     */
    @Override
    public void showLoginResults(boolean nickAccepted, String chosenNickname) {
        if(nickAccepted){
            out.println("Login avvenuto con successo!");
            out.println("Il nickname scelto è: " + this.nickname);
            resetNeedNick();
        }else{
            out.println("Nickname already chosen, choose another nickname!");
        }

    }

    /**
     *This method prints all the {@code nicknames} of all the {@link Player players} in the attribute
     * {@link ArrayList<Player> playerList} of this instance
     */
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

    /**
     *This method prints the {@code nickname}
     * of the {@link Player currentPlayer} in turn
     */
    @Override
    public void showNickCurrentPlayer(String Nickname){
    out.println("The current player is:  "+Nickname);
    }

    /**
     *This method prints all the {@link PlayableItemTile tile} in the {@link LivingRoom}
     * simplified in a {@link SimpleLivingRoom simpleLivingRoom}
     */
    @Override
    public void showLivingRoom(SimpleLivingRoom livingRoom) {
        ItemTile[][] board = livingRoom.getGameTable();
        out.println("Legenda: []=empty, [X]=unavailable, [B]=blue tile, [C]=cyan tile," +
                " [Y]=yellow tile, [P]=pink tile, [W]=white tile, [G]=green tile");
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

     /**
     *This method prints all the {@link PlayableItemTile tile} in the {@link Shelf personalShelf} simplified
      * in a {@link SimpleShelf simpleShelf}
      * of this user
     */
    @Override
    public void showPlayerShelf(SimpleShelf shelf) {
        PlayableItemTile[][] personalShelf = shelf.getStructure();
        out.println("Legenda: []=empty, [X]=unavailable, [B]=blue tile, [C]=cyan tile," +
                " [Y]=yellow tile, [P]=pink tile, [W]=white tile, [G]=green tile , the number near the color "+
                "represents the unique ID code of the tile");
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

     /**
     *This method prints the current {@code points} of this user taking
     */
    @Override
    public void showPartialPoint(int point) {
    out.println("Il tuo punteggio corrente :"+point);
    }

    /**
     * Just shows a message
     */
    public void resetTUI(){
        out.println("Cleaning of the textual interface...");
    }

    /**
     *Establish a connection to the {@link Network.ServerSide.RMIServer} handling
     * the case of wrong inputs
     * @param address corresponds to the ipAddress of the {@link Network.ServerSide.RMIServer}
     * @param port corresponds to its port
     */
    public void connectToServerFromTUI(String address, int port){
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

