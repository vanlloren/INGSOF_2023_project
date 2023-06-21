package Network.ClientSide;

import Network.Events.*;
import Network.ServerSide.RemoteServerInterface;
import Network.message.*;
import Observer.*;
import Util.CommonGoalType;
import Util.PersonalGoalType;
import client.view.TurnView;
import client.view.View;
import server.Model.PlayableItemTile;
import server.Model.Player;
import server.enumerations.PickTileResponse;
import java.io.PrintStream;
import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

public  class RemoteClientImplementation extends Client implements RemoteClientInterface, ViewObserver, LivingRoomObserver, ShelfObserver, PlayerObserver, GameModelObserver, PersonalGoalObserver {

    @Serial
    private static final long serialVersionUID = 3324195868427777436L;
    private final PrintStream out = System.out;
    private final Scanner scanner = new Scanner(System.in);
    private RemoteServerInterface server;
    private TurnView turnView;
    private String nickname;
    private ArrayList<PlayableItemTile> turnTiles = null;



    public RemoteClientImplementation(String address, int port, View userInterface) throws RemoteException {
        super(address, port, userInterface);
    }
    @Override
    public void connectionInit() throws Exception {
        Registry registry = LocateRegistry.getRegistry(getServerAddress(), getPortNum());
        server = (RemoteServerInterface) registry.lookup("MyShelfieServer");

    }

    @Override
    public void onMessage(Message message) throws RemoteException {
        switch (message.getMessageEnumeration()){
            case LOGIN_REPLY -> {
                LoginReplyMessage newMessage = (LoginReplyMessage)message;
                if(newMessage.isNicknameUniqueAccepted()){
                    this.nickname = newMessage.getNickname();
                    this.userInterface.setNickname(this.nickname);

                }
                this.userInterface.showLoginResults(newMessage.isNicknameUniqueAccepted(), newMessage.getNickname());
            }
            case PLAYERNUMBER_REQUEST -> {
                this.nickname = message.getNickname();
                this.userInterface.setNickname(this.nickname);
                this.userInterface.askPlayersNumber();
            }
            case FULL_LOBBY ->
                    this.userInterface.fullLobbyTerminateUI();

            case CHOICE_BEGIN ->
                    this.userInterface.riprendiEsecuzione();




        }
    }

    @Override
    public void onModelModify(TurnView turnView, Event event){
        this.turnView=turnView;
        switch (event.getEventType()){
            case UPDATE_REFILL_LIVINGROOM -> {
                System.out.println("NEW UPDATE: La LivingRoom è stata riempita di nuovo!");
                System.out.println("A seguire la stampa della nuova LivingRoom");
                this.userInterface.showLivingRoom(turnView.getLivingRoom());
            }
            case UPDATE_PLAYERS_LIST -> {
                UpdatePlayersListEvent newEvent = (UpdatePlayersListEvent) event;
                System.out.println("...NEW UPDATE: : "+newEvent.getPlayer().getNickname()+" si è unito alla partita");
            }
            case UPDATE_END_GAME -> {
                UpdateEndGameEvent newEvent = (UpdateEndGameEvent) event;
                System.out.println(newEvent.getPlayer()+" ha riempito per primo la sua shelf! :" +
                        "ha ora inizio l'ultimo turno di gioco");
            }
            case UPDATE_PLAYERS_NUMBER -> {
                UpdatePlayersNumberEvent newEvent = (UpdatePlayersNumberEvent) event;
                System.out.println("la richiesta di numero di giocatori è stata accettata\n" +
                        "Questa partita avrà "+newEvent.getPlayersNumber()+" giocaotori");
            }
            case UPDATE_CHAIR_OWNER -> {
                UpdateChairOwnerEvent newEvent = (UpdateChairOwnerEvent) event;
                System.out.println("....NEW UPDATE: il chairOwner per questa partita è "+newEvent.getPlayer().getNickname());
            }
            case UPDATE_GAME_HAS_STARTED ->
                 System.out.println("numero giocatori richiesto è stato raggiunto: La partita sta per cominciare.....");

            case UPDATE_CURR_PLAYER -> {
                UpdateCurrPlayerEvent newEvent = (UpdateCurrPlayerEvent) event;
                this.userInterface.setCurrPlayer(newEvent.getCurrPlayer().getNickname());
                System.out.println("NEW UPDATE: " + newEvent.getCurrPlayer().getNickname() + "è il nuovo giocatore corrente");
                this.userInterface.riprendiEsecuzione();
            }
            case UPDATE_MATCH_WINNER -> {
                UpdateMatchWinnerEvent newEvent = (UpdateMatchWinnerEvent) event;
                System.out.println("IL VINCITORE E' : " + newEvent.getMatchWinner());
                if(newEvent.getMatchWinner().equals(this.nickname)) {
                    System.out.println("CONGRATULAZIONI : HAI VINTO LA PARTITA!");
                }
                else {
                    System.out.println("HAI PERSO!..RITENTA SARAI PIU FORTUNATO..");
                }
            }
            case UPDATE_GAME_HAS_ENDED -> {
                this.userInterface.resetGameOn();
                System.out.println("""
                    .......................
                    .......................
                    .......................
                    .......................
                    .......................
                    .......GAME OVER......."""
                );
            }
            case UPDATE_PICKED_LIVINGROOM_TILE -> {
                UpdatePickedLivingRoomTileEvent newEvent = (UpdatePickedLivingRoomTileEvent) event;
                System.out.println("Il giocatore " + newEvent.getCurrPlayer() + " ha pescato da LivingRoom " +
                "la tessera in posizione: x=" + newEvent.getXPos() + ", y=" + newEvent.getYPos());
                System.out.println("A seguire verrà stampata la LivingRoom aggiornata");
                this.userInterface.showLivingRoom(turnView.getLivingRoom());
            }
            case UPDATE_PERSONAL_GOAL -> {
                UpdatePersonalGoalEvent newEvent = (UpdatePersonalGoalEvent) event;
                System.out.println("....NEW UPDATE:La personalGoal assegnata al giocatore "+newEvent.getPlayer()+" è "+newEvent.getPersonalGoalType());
            }
            case UPDATE_COMMON_GOAL -> {
                UpdateCommonGoalEvent newEvent = (UpdateCommonGoalEvent) event;
                System.out.println("....NEW UPDATE: La CommonGoal 1 per questa partita è "+newEvent.getCommonGoalType1());
                System.out.println("....NEW UPDATE: La CommonGoal 2 per questa partita è "+newEvent.getCommonGoalType2());
            }
            case UPDATE_PLAYER_POINTS -> {
                UpdatePlayerPointEvent newEvent = (UpdatePlayerPointEvent) event;
                System.out.println("....NEW UPDATE:" +newEvent.getPlayer()+" ha adesso "+newEvent.getPoints()+" punti");
            }
            case UPDATE_STATUS_COMMON_GOAL2 -> {
                UpdateStatusCommonGoal2Event newEvent = (UpdateStatusCommonGoal2Event) event;
                System.out.println("....NEW UPDATE:"+newEvent.getNickname()+"ha soddisfatto la CommonGoal2");
            }
            case UPDATE_STATUS_COMMON_GOAL1 -> {
                UpdateStatusCommonGoal1Event newEvent = (UpdateStatusCommonGoal1Event) event;
                System.out.println("....NEW UPDATE:"+newEvent.getNickname()+"ha soddisfatto la CommonGoal1");
            }
            case UPDATE_PUT_SHELF_TILE -> {
                UpdatePutShelfTileEvent newEvent = (UpdatePutShelfTileEvent) event;
                System.out.println("Il giocatore " + turnView.getNicknameCurrentPlayer() + " ha posizionato la tessera "
                        + newEvent.getTile().getColour() + " " + newEvent.getTile().getIdCode() + " nella posizione x=" + newEvent.getXPos() + ", y=" + newEvent.getYPos());
                System.out.println("A seguire verrà stampata la PlayerShelf aggiornata");
                this.userInterface.showPlayerShelf(turnView.getShelfTable());
            }
            case UPDATE_CHAT -> {
                UpdateChatEvent newEvent = (UpdateChatEvent) event;
                String receiver = newEvent.getReceiver();
                String Nickname = newEvent.getNickname();
                String chatMessage = newEvent.getChat();
                if(receiver.equals(this.nickname)||receiver.equals("Everyone")){
                    System.out.println(Nickname+": "+chatMessage+".");
                }
            }
            case UPDATE_TILES_AVAILABILITY ->{
            }

        }
    }

    public void onUpdateToPickTile() throws RemoteException{
        ArrayList<PlayableItemTile> helperList = new ArrayList<>();
        int x;
        int y;
        String buffer;

        System.out.println("In qualsiasi momento dell'esecuzione della sequenza di pesca/inserimento delle tile, scrivere 'chat' permetterà di inviare un messaggio nella chat del gioco!");
        do {
            System.out.println("Scegli la posizione x della prima tessera che vuoi pescare!");
            while(!scanner.hasNextInt()){
                if(scanner.nextLine().equals("chat")) {
                    chatThread();
                    System.out.println("Scegli la posizione x della prima tessera che vuoi pescare!");
                }else{
                    if (!scanner.nextLine().equals("")) {
                        out.println("Input non valido, scrivi 'chat' per usare la chat oppure indica un intero");
                    }
                }
            }
            x = scanner.nextInt();
            scanner.nextLine();
            if(x<0 || x>8){
                out.println("Coordinata non valida, riprova!");
            }
        }while(x<0 || x>8);
        do {
            out.println("Scegli la posizione y della prima tessera che vuoi pescare!");
            while(!scanner.hasNextInt()){
                if(scanner.nextLine().equals("chat")) {
                    chatThread();
                    System.out.println("Scegli la posizione y della prima tessera che vuoi pescare!");
                }else{
                    if (!scanner.nextLine().equals("")) {
                        out.println("Input non valido, scrivi 'chat' per usare la chat oppure indica un intero");
                    }
                }
            }
            y = scanner.nextInt();
            scanner.nextLine();
            if (y < 0 || y > 8) {
                out.println("Coordinata non valida, riprova!");
            }
        }while(y<0 || y>8);

        TileReplyMessage message = server.onTilePickMessage(nickname, x, y);
        while (message.isTileAccepted().equals(PickTileResponse.MAX_TILE_PICKED) || message.isTileAccepted().equals(PickTileResponse.INVALID_TILE)){
            if(message.isTileAccepted().equals(PickTileResponse.MAX_TILE_PICKED)){
                out.println("Massimo numero di tessere scelte. Ora ti verrà chiesto di collocare le tessere nella tua Shelf");
                this.turnTiles=helperList;

                return;
            } else if (message.isTileAccepted().equals(PickTileResponse.INVALID_TILE)) {
                out.println("Tessera scelta non disponibile, scegli un'altra tessera");
                onUpdateShowLivingRoom();
                onUpdateShowAvailableTiles();
                do {
                    System.out.println("Scegli la posizione x della prima tessera che vuoi pescare!");
                    while(!scanner.hasNextInt()){
                        if(scanner.nextLine().equals("chat")) {
                            chatThread();
                            System.out.println("Scegli la posizione x della prima tessera che vuoi pescare!");
                        }else{
                            if (!scanner.nextLine().equals("")) {
                                out.println("Input non valido, scrivi 'chat' per usare la chat oppure indica un intero");
                            }
                        }
                    }
                    x = scanner.nextInt();
                    scanner.nextLine();
                    if(x<0 || x>8){
                        out.println("Coordinata non valida, riprova!");
                    }
                }while(x<0 || x>8);

                do {
                    out.println("Scegli la posizione y della prima tessera che vuoi pescare!");
                    while(!scanner.hasNextInt()){
                        if(scanner.nextLine().equals("chat")) {
                            chatThread();
                            System.out.println("Scegli la posizione y della prima tessera che vuoi pescare!");
                        }else{
                            if (!scanner.nextLine().equals("")) {
                                out.println("Input non valido, scrivi 'chat' per usare la chat oppure indica un intero");
                            }
                        }
                    }
                    y = scanner.nextInt();
                    scanner.nextLine();
                    if (y < 0 || y > 8) {
                        out.println("Coordinata non valida, riprova!");
                    }
                }while(y<0 || y>8);

                message = server.onTilePickMessage(nickname, x, y);
            }
        }
        helperList.add(message.getTile());


        out.println("Una tessera pescata, vuoi pescare altre tessere? [Y/N]");
        String test = scanner.nextLine();
        while(test.equals("chat")){
            chatThread();
            out.println("Una tessera pescata, vuoi pescare altre tessere? [Y/N]");
            test = scanner.nextLine();
        }

        while(!test.equals("Y")){
            if(test.equals("N")){
                this.turnTiles=helperList;
                server.onMessage(new StopPickingMessage(nickname));
                out.println("Ora ti verrà chiesto di collocare le tessere nella tua Shelf");
                return;
            }
            out.println("Simbolo errato!");
            buffer = scanner.nextLine();
            while(buffer.equals("chat")){
                chatThread();
                buffer = scanner.nextLine();
            }
            out.println("Una tessera pescata, vuoi pescare altre tessere? [Y/N]");
            test = buffer;
            while(test.equals("chat")){
                chatThread();
                out.println("Una tessera pescata, vuoi pescare altre tessere? [Y/N]");
                test = scanner.nextLine();
                while(test.equals("")) {
                    test = scanner.nextLine();
                }
            }
        }

        onUpdateShowLivingRoom();
        onUpdateShowAvailableTiles();
        if(turnView.getLivingRoom().getAvailableTiles().size()==0) {
            server.onMessage(new StopPickingMessage(nickname));
            out.println("Non ci sono altre tessere disponibili, mi spiace!");
            out.println("Massimo numero di tessere scelte. Ora ti verrà chiesto di collocare le tessere nella tua Shelf");
            this.turnTiles = helperList;
            return;
        }


        //pesca seconda tessera
        do {
            System.out.println("Scegli la posizione x della seconda tessera che vuoi pescare!");
            while(!scanner.hasNextInt()){
                if(scanner.nextLine().equals("chat")) {
                    chatThread();
                    System.out.println("Scegli la posizione x della seconda tessera che vuoi pescare!");
                }else{
                    if (!scanner.nextLine().equals("")) {
                        out.println("Input non valido, scrivi 'chat' per usare la chat oppure indica un intero");
                    }
                }
            }
            x = scanner.nextInt();
            scanner.nextLine();
            if(x<0 || x>8){
                out.println("Coordinata non valida, riprova!");
            }
        }while(x<0 || x>8);

        do {
            out.println("Scegli la posizione y della seconda tessera che vuoi pescare!");
            while(!scanner.hasNextInt()){
                if(scanner.nextLine().equals("chat")) {
                    chatThread();
                    System.out.println("Scegli la posizione y della seconda tessera che vuoi pescare!");
                }else {
                    if (!scanner.nextLine().equals("")) {
                        out.println("Input non valido, scrivi 'chat' per usare la chat oppure indica un intero");
                    }
                }
            }
            y = scanner.nextInt();
            scanner.nextLine();
            if (y < 0 || y > 8) {
                out.println("Coordinata non valida, riprova!");
            }
        }while(y<0 || y>8);
        message = server.onTilePickMessage(nickname, x, y);
        while (message.isTileAccepted().equals(PickTileResponse.MAX_TILE_PICKED) || message.isTileAccepted().equals(PickTileResponse.INVALID_TILE)){
            if(message.isTileAccepted().equals(PickTileResponse.MAX_TILE_PICKED)){
                out.println("Massimo numero di tessere scelte. Ora ti verrà chiesto di collocare le tessere nella tua Shelf");
                this.turnTiles=helperList;
                return;
            } else if (message.isTileAccepted().equals(PickTileResponse.INVALID_TILE)) {
                out.println("Tessera scelta non disponibile, scegli un'altra tessera");
                onUpdateShowLivingRoom();
                onUpdateShowAvailableTiles();
                do {
                    System.out.println("Scegli la posizione x della seconda tessera che vuoi pescare!");
                    while(!scanner.hasNextInt()){
                        if(scanner.nextLine().equals("chat")) {
                            chatThread();
                            System.out.println("Scegli la posizione x della seconda tessera che vuoi pescare!");
                        }else{
                            if (!scanner.nextLine().equals("")) {
                                out.println("Input non valido, scrivi 'chat' per usare la chat oppure indica un intero");
                            }
                        }
                    }
                    x = scanner.nextInt();
                    scanner.nextLine();
                    if(x<0 || x>8){
                        out.println("Coordinata non valida, riprova!");
                    }
                }while(x<0 || x>8);
                do {
                    out.println("Scegli la posizione y della seconda tessera che vuoi pescare!");
                    while(!scanner.hasNextInt()){
                        if(scanner.nextLine().equals("chat")) {
                            chatThread();
                            System.out.println("Scegli la posizione y della seconda tessera che vuoi pescare!");
                        }else{
                            if (!scanner.nextLine().equals("")) {
                                out.println("Input non valido, scrivi 'chat' per usare la chat oppure indica un intero");
                            }
                        }
                    }
                    y = scanner.nextInt();
                    scanner.nextLine();
                    if (y < 0 || y > 8) {
                        out.println("Coordinata non valida, riprova!");
                    }
                }while(y<0 || y>8);
                message = server.onTilePickMessage(nickname, x, y);
            }
        }
        helperList.add(message.getTile());

        out.println("Due tessere pescate, vuoi pescare altre tessere? [Y/N]");
        test = scanner.nextLine();
        while(test.equals("chat")){
            chatThread();
            out.println("Due tessere pescate, vuoi pescare altre tessere? [Y/N]");
            test = scanner.nextLine();
        }
        while(!test.equals("Y")){
            if(test.equals("N")){
                this.turnTiles=helperList;
                server.onMessage(new StopPickingMessage(nickname));
                out.println("Ora ti verrà chiesto di collocare le tessere nella tua Shelf");
                return;
            }
            out.println("Simbolo errato!");
            buffer = scanner.nextLine();
            while(buffer.equals("chat")){
                chatThread();
                buffer = scanner.nextLine();
            }
            out.println("Due tessere pescate, vuoi pescare altre tessere? [Y/N]");
            test = scanner.nextLine();
            while(test.equals("chat")){
                chatThread();
                out.println("Due tessere pescate, vuoi pescare altre tessere? [Y/N]");
                test = scanner.nextLine();
                while(test.equals("")) {
                    test = scanner.nextLine();
                }
            }
        }

        onUpdateShowLivingRoom();
        onUpdateShowAvailableTiles();
        if(turnView.getLivingRoom().getAvailableTiles().size()==0) {
            server.onMessage(new StopPickingMessage(nickname));
            out.println("Non ci sono altre tessere disponibili, mi spiace!");
            out.println("Massimo numero di tessere scelte. Ora ti verrà chiesto di collocare le tessere nella tua Shelf");
            this.turnTiles = helperList;
            return;
        }


        do {
            System.out.println("Scegli la posizione x della terza tessera che vuoi pescare!");
            while(!scanner.hasNextInt()){
                if(scanner.nextLine().equals("chat")) {
                    chatThread();
                    System.out.println("Scegli la posizione x della terza tessera che vuoi pescare!");
                }else{
                    if (!scanner.nextLine().equals("")) {
                        out.println("Input non valido, scrivi 'chat' per usare la chat oppure indica un intero");
                    }
                }
            }
            x = scanner.nextInt();
            scanner.nextLine();
            if(x<0 || x>8){
                out.println("Coordinata non valida, riprova!");
            }
        }while(x<0 || x>8);

        do {
            out.println("Scegli la posizione y della terza tessera che vuoi pescare!");
            while(!scanner.hasNextInt()){
                if(scanner.nextLine().equals("chat")) {
                    chatThread();
                    System.out.println("Scegli la posizione y della terza tessera che vuoi pescare!");
                }else{
                    if (!scanner.nextLine().equals("")) {
                        out.println("Input non valido, scrivi 'chat' per usare la chat oppure indica un intero");
                    }
                }
            }
            y = scanner.nextInt();
            scanner.nextLine();
            if (y < 0 || y > 8) {
                out.println("Coordinata non valida, riprova!");
            }
        }while(y<0 || y>8);
        message = server.onTilePickMessage(nickname, x, y);
        while (message.isTileAccepted().equals(PickTileResponse.MAX_TILE_PICKED) || message.isTileAccepted().equals(PickTileResponse.INVALID_TILE)){
            if(message.isTileAccepted().equals(PickTileResponse.MAX_TILE_PICKED)){
                out.println("Massimo numero di tessere scelte. Ora ti verrà chiesto di collocare le tessere nella tua Shelf");
                this.turnTiles=helperList;
                return;
            } else if (message.isTileAccepted().equals(PickTileResponse.INVALID_TILE)) {
                out.println("Tessera scelta non disponibile, scegli un'altra tessera");
                onUpdateShowLivingRoom();
                onUpdateShowAvailableTiles();
                do {
                    System.out.println("Scegli la posizione x della terza tessera che vuoi pescare!");
                    while(!scanner.hasNextInt()){
                        if(scanner.nextLine().equals("chat")) {
                            chatThread();
                            System.out.println("Scegli la posizione x della terza tessera che vuoi pescare!");
                        }else{
                            if (!scanner.nextLine().equals("")) {
                                out.println("Input non valido, scrivi 'chat' per usare la chat oppure indica un intero");
                            }
                        }
                    }
                    x = scanner.nextInt();
                    scanner.nextLine();
                    if(x<0 || x>8){
                        out.println("Coordinata non valida, riprova!");
                    }
                }while(x<0 || x>8);
                do {
                    out.println("Scegli la posizione y della terza tessera che vuoi pescare!");
                    while(!scanner.hasNextInt()){
                        if(scanner.nextLine().equals("chat")) {
                            chatThread();
                            System.out.println("Scegli la posizione y della terza tessera che vuoi pescare!");
                        }else{
                            if (!scanner.nextLine().equals("")) {
                                out.println("Input non valido, scrivi 'chat' per usare la chat oppure indica un intero");
                            }
                        }
                    }
                    y = scanner.nextInt();
                    scanner.nextLine();
                    if (y < 0 || y > 8) {
                        out.println("Coordinata non valida, riprova!");
                    }
                }while(y<0 || y>8);
                message = server.onTilePickMessage(nickname, x, y);
            }
        }
        helperList.add(message.getTile());
        server.onMessage(new StopPickingMessage(nickname));
        out.println("Massimo numero di tessere scelte. Ora ti verrà chiesto di collocare le tessere nella tua Shelf");
        this.turnTiles=helperList;
    }

    public void onUpdateToStartPutting() throws RemoteException {

        int xPos;
        int yPos;
        int index;
        InsertionReplyMessage message;
        String buffer;

        int i = 0;
        for (PlayableItemTile turnTile : turnTiles) {
            out.println("Tile pescate dalla LivingRoom: {[" + turnTile.getColour() + ", " + turnTile.getIdCode() + "]," + " in posizione : " + i + "}");
            i++;
        }
        switch (turnTiles.size()) {
            case 1 -> out.println("Scegli la tile da inserire nella Shelf(Inserimento valido:[ 0 ])");
            case 2 -> out.println("Scegli la tile da inserire nella Shelf(Inserimento valido:[ 0 , 1 ])");
            case 3 -> out.println("Scegli la tile da inserire nella Shelf(Inserimento valido:[ 0 , 1 , 2 ])");
        }
        while (!scanner.hasNextInt()) {
            buffer = scanner.nextLine();
            if (buffer.equals("chat")) {
                chatThread();
                switch (turnTiles.size()) {
                    case 1 -> out.println("Scegli la tile da inserire nella Shelf(Inserimento valido:[ 0 ])");
                    case 2 -> out.println("Scegli la tile da inserire nella Shelf(Inserimento valido:[ 0 , 1 ])");
                    case 3 -> out.println("Scegli la tile da inserire nella Shelf(Inserimento valido:[ 0 , 1 , 2 ])");        }
            } else {
                if (!buffer.equals("")) {
                    out.println("Input non valido, scrivi 'chat' per usare la chat oppure indica un intero");
                }
            }
        }

        index = scanner.nextInt();
        scanner.nextLine();
        do {
            if(index < 0 || index > turnTiles.size()-1) {
                out.println("Indice non valido!");
                switch (turnTiles.size()) {
                    case 1 -> out.println("Scegli la tile da inserire nella Shelf(Inserimento valido:[ 0 ])");
                    case 2 -> out.println("Scegli la tile da inserire nella Shelf(Inserimento valido:[ 0 , 1 ])");
                    case 3 -> out.println("Scegli la tile da inserire nella Shelf(Inserimento valido:[ 0 , 1 , 2 ])");
                }
                while(!scanner.hasNextInt()) {
                    buffer = scanner.nextLine();
                    if(buffer.equals("chat")) {
                        chatThread();
                        switch (turnTiles.size()) {
                            case 1 -> out.println("Scegli la tile da inserire nella Shelf(Inserimento valido:[ 0 ])");
                            case 2 -> out.println("Scegli la tile da inserire nella Shelf(Inserimento valido:[ 0 , 1 ])");
                            case 3 -> out.println("Scegli la tile da inserire nella Shelf(Inserimento valido:[ 0 , 1 , 2 ])");    }
                    }else{
                        if (!buffer.equals("")) {
                            out.println("Input non valido, scrivi 'chat' per usare la chat oppure indica un intero");
                        }
                    }
                }
                index = scanner.nextInt();
                scanner.nextLine();
            }
        } while (index < 0 || index > turnTiles.size()-1);

        do {
            out.println("Scegli la coordinata in X sulla shelf dove mettere la tile(Inserimento Valido:[ 0 , 1 , 2 , 3 , 4 , 5 ])");
            while (!scanner.hasNextInt()) {
                buffer = scanner.nextLine();
                if (buffer.equals("chat")) {
                    chatThread();
                    out.println("Scegli la coordinata in X sulla shelf dove mettere la tile(Inserimento Valido:[ 0 , 1 , 2 , 3 , 4 , 5 ])");
                } else {
                    if (!buffer.equals("")) {
                        out.println("Input non valido, scrivi 'chat' per usare la chat oppure indica un intero");
                    }
                }
            }
            xPos = scanner.nextInt();
            scanner.nextLine();
            while (xPos < 0 || xPos > 5) {
                out.println("Coordinata non valida..riprova");
                out.println("Scegli la coordinata in X sulla shelf dove mettere la tile(Inserimento Valido:[ 0 , 1 , 2 , 3 , 4 , 5 ])");
                while (!scanner.hasNextInt()) {
                    buffer = scanner.nextLine();
                    if (buffer.equals("chat")) {
                        chatThread();
                        out.println("Scegli la coordinata in X sulla shelf dove mettere la tile(Inserimento Valido:[ 0 , 1 , 2 , 3 , 4 , 5 ])");
                    } else {
                        if (!buffer.equals("")) {
                            out.println("Input non valido, scrivi 'chat' per usare la chat oppure indica un intero");
                        }
                    }
                }
                xPos = scanner.nextInt();
                scanner.nextLine();
            }
            out.println("Scegli la coordinata in Y sulla shelf dove mettere la tile (Inserimento Valido: [ 0 , 1 , 2 , 3 , 4 ])!\n");
            while (!scanner.hasNextInt()) {
                buffer = scanner.nextLine();
                if (buffer.equals("chat")) {
                    chatThread();
                    out.println("Scegli la coordinata in Y sulla shelf dove mettere la tile (Inserimento Valido: [ 0 , 1 , 2 , 3 , 4 ])!\n");
                } else {
                    if (!buffer.equals("")) {
                        out.println("Input non valido, scrivi 'chat' per usare la chat oppure indica un intero");
                    }
                }
            }
            yPos = scanner.nextInt();
            scanner.nextLine();
            while (yPos < 0 || yPos > 4) {
                out.println("Coordinata non valida riprova");
                out.println("Scegli la coordinata in Y sulla shelf dove mettere la tile (Inserimento Valido: [ 0 , 1 , 2 , 3 , 4 ])!\n");
                while (!scanner.hasNextInt()) {
                    buffer = scanner.nextLine();
                    if (buffer.equals("chat")) {
                        chatThread();
                        out.println("Scegli la coordinata in Y sulla shelf dove mettere la tile (Inserimento Valido: [ 0 , 1 , 2 , 3 , 4 ])!\n");
                    } else {
                        if (!buffer.equals("")) {
                            out.println("Input non valido, scrivi 'chat' per usare la chat oppure indica un intero");
                        }
                    }
                }
                yPos = scanner.nextInt();
                scanner.nextLine();
            }
            message = server.ToPutTileRequestMessage(xPos, yPos, turnTiles.get(index), turnTiles.size());
            if (!message.isValid()) {
                out.println("Errore riscontrato:non puoi inserire in quella casella");
            }
        } while (!message.isValid());

        turnTiles.remove(index);

        while (turnTiles.size() > 0) {
            i=0;
            for (PlayableItemTile turnTile : turnTiles) {
                out.println("Tile pescate dalla LivingRoom: {[" + turnTile.getColour() + ", " + turnTile.getIdCode() + "]," + " in posizione: " + i + "}");
                i++;
            }
            switch (turnTiles.size()){
                case 1 -> out.println("Scegli la tile da inserire nella Shelf(Inserimento valido:[ 0 ])");
                case 2 -> out.println("Scegli la tile da inserire nella Shelf(Inserimento valido:[ 0 , 1 ])");
            }
            while (!scanner.hasNextInt()) {
                buffer = scanner.nextLine();
                if (buffer.equals("chat")) {
                    chatThread();
                    switch (turnTiles.size()) {
                        case 1 -> out.println("Scegli la tile da inserire nella Shelf(Inserimento valido:[ 0 ])");
                        case 2 -> out.println("Scegli la tile da inserire nella Shelf(Inserimento valido:[ 0 , 1 ])");
                    }
                } else {
                    if (!buffer.equals("")) {
                        out.println("Input non valido, scrivi 'chat' per usare la chat oppure indica un intero");
                    }
                }
            }
            index = scanner.nextInt();
            scanner.nextLine();
            do {
                if(index < 0 || index > turnTiles.size()-1)
                {out.println("Indice non valido..riprova");
                    switch (turnTiles.size()) {
                        case 1 -> out.println("Scegli la tile da inserire nella Shelf(Inserimento valido:[ 0 ])");
                        case 2 -> out.println("Scegli la tile da inserire nella Shelf(Inserimento valido:[ 0 , 1 ])");
                    }
                    while(!scanner.hasNextInt()) {
                        buffer = scanner.nextLine();
                        if(buffer.equals("chat")) {
                            chatThread();
                            switch (turnTiles.size()) {
                                case 1 -> out.println("Scegli la tile da inserire nella Shelf(Inserimento valido:[ 0 ])");
                                case 2 -> out.println("Scegli la tile da inserire nella Shelf(Inserimento valido:[ 0 , 1 ])");
                            }
                        }else{
                            if (!buffer.equals("")) {
                                out.println("Input non valido, scrivi 'chat' per usare la chat oppure indica un intero");
                            }
                        }
                    }
                    index = scanner.nextInt();
                    scanner.nextLine();
                }
            } while (index < 0 || index > turnTiles.size()-1);

            out.println("Scegli la coordinata in X sulla shelf dove mettere la tile (Inserimento valido:[ 0 , 1 , 2 , 3 , 4 , 5 ])");
            while (!scanner.hasNextInt()) {
                buffer = scanner.nextLine();
                if (buffer.equals("chat")) {
                    chatThread();
                    out.println("Scegli la coordinata in X sulla shelf dove mettere la tile (Inserimento valido:[ 0 , 1 , 2 , 3 , 4 , 5 ])");
                } else {
                    if (!buffer.equals("")) {
                        out.println("Input non valido, scrivi 'chat' per usare la chat oppure indica un intero");
                    }
                }
            }
            xPos = scanner.nextInt();
            scanner.nextLine();
            while (xPos < 0 || xPos > 5) {
                out.println("Coordinata non valida..riprova");
                out.println("Scegli la coordinata in X sulla shelf dove mettere la tile (Inserimento valido:[ 0 , 1 , 2 , 3 , 4 , 5 ])");
                while (!scanner.hasNextInt()) {
                    buffer = scanner.nextLine();
                    if (buffer.equals("chat")) {
                        chatThread();
                        out.println("Scegli la coordinata in X sulla shelf dove mettere la tile (Inserimento valido:[ 0 , 1 , 2 , 3 , 4 , 5 ])");
                    } else {
                        if (!buffer.equals("")) {
                            out.println("Input non valido, scrivi 'chat' per usare la chat oppure indica un intero");
                        }
                    }
                }
                xPos = scanner.nextInt();
                scanner.nextLine();
            }

            message = server.ToPutTileRequestMessage(xPos, turnTiles.get(index), turnTiles.size());
            if (!message.isValid())
                out.println("Errore riscontrato:non puoi inserire in quella casella");
            else turnTiles.remove(index);
        }
        if (message.isLastTurn())
            this.userInterface.setIsTurn();
    }

    @Override
    public void onConnectionVerify() throws RemoteException {
        server.verifyStillConnected();
    }


    @Override
    public void onUpdateNickname(String nickname) throws RemoteException {
        LoginRequestMessage loginRequestMessage = new LoginRequestMessage(this,nickname);
        server.onMessage(loginRequestMessage);
    }

    @Override
    public void onUpdatePlayersNumber(int playersNumber) throws RemoteException {
        server.onMessage(new PlayersNumberReplyMessage(playersNumber) );
    }

    @Override
    public void onUpdateChat(String Nickname, String chat,String receiver) throws RemoteException {
        server.onMessage(new WriteInChatMessage(Nickname, chat,receiver));
    }





    public void onUpdateShowLivingRoom(){
        this.userInterface.showLivingRoom(turnView.getLivingRoom());
    }

    @Override
    public void onUpdateShowCurrPlayer(){
        this.userInterface.setCurrPlayer(turnView.getNicknameCurrentPlayer());
    }

    @Override
    public void onUpdateShowAvailableTiles(){
        for (PlayableItemTile tile: turnView.getLivingRoom().getAvailableTiles()){
            System.out.println("La tessera in posizione x=" + tile.getPosition().toArray()[0] + " y=" + tile.getPosition().toArray()[1] + " é disponibile!\n");
        }
    }

    @Override
    public void onUpdateSetPlayersList() {
        this.userInterface.setPlayerList(turnView.getGameModel().getPlayersInGame());
    }

    @Override
    public void onUpdateShowPlayerShelf(String nickname) {
        this.userInterface.showPlayerShelf(turnView.getShelfTable());
    }

    @Override
    public int onUpdateShowPartialPoint(String nickname) {
        return turnView.getPartialPoint(nickname);
    }

    @Override
    public void onUpdateShowNickCurrPlayer() {
        this.userInterface.showNickCurrentPlayer(turnView.getNicknameCurrentPlayer());
    }

    //---------------------------------//
    @Override
    public void onUpdateModelListPlayers(TurnView turnView, Player player) {
        onModelModify(turnView, new UpdatePlayersListEvent(player));
    }

    @Override
    public void onUpdateModelEndGame(TurnView turnView,Player player, boolean endGame)  {
        onModelModify(turnView, new UpdateEndGameEvent(player.getNickname()));
    }

    @Override
    public void onUpdateModelPlayersNumber(TurnView turnView, int playersNumber)  {
        onModelModify(turnView, new UpdatePlayersNumberEvent(playersNumber));
    }

    public void onUpdateTilesAvailability(TurnView turnView){
        onModelModify(turnView, new UpdateTileAvailabilityEvent());
    }

    @Override
    public void onUpdateRefillLivingRoom(TurnView turnView) throws RemoteException {
        onModelModify(turnView, new UpdateRefillLivingRoomEvent());
    }

    @Override
    public void onUpdateModelChairOwner(TurnView turnView, Player player) {
        onModelModify(turnView, new UpdateChairOwnerEvent(player));
    }

    @Override
    public void onUpdateModelGameHasStarted(TurnView turnView) {
        onModelModify(turnView, new UpdateGameHasStartedEvent());
    }

    @Override
    public void onUpdateModelCurrentPlayer(TurnView turnView, Player currPlayer) {
        onModelModify(turnView, new UpdateCurrPlayerEvent(currPlayer));
    }

    @Override
    public void onUpdateModelMatchWinner(TurnView turnView, String player) {
        onModelModify(turnView, new UpdateMatchWinnerEvent(player));
    }

    @Override
    public void onUpdateModelGameHasEnd(TurnView turnView) {
        onModelModify(turnView, new UpdateGameHasEndEvent());
    }

    @Override
    public void onUpdateModelChat(TurnView turnView, String nickname, String chat,String receiver) {
        onModelModify(turnView, new UpdateChatEvent(nickname,chat,receiver));
    }

    @Override
    public void onUpdatePickedTileFromLivingRoom(TurnView turnView, int x, int y) {
        onModelModify(turnView, new UpdatePickedLivingRoomTileEvent(turnView.getNicknameCurrentPlayer() ,x, y));
    }

    @Override
    public void OnUpdateModelPersonalGoal(TurnView turnView, PersonalGoalType personalGoalType, String nickname) {
        onModelModify(turnView, new UpdatePersonalGoalEvent(nickname, personalGoalType));
    }

    @Override
    public void OnUpdateModelCommonGoal(TurnView turnView, CommonGoalType commonGoalType1, CommonGoalType commonGoalType2) {
        onModelModify(turnView, new UpdateCommonGoalEvent(commonGoalType1, commonGoalType2));
    }

    @Override
    public void OnUpdateModelPlayerPoint(TurnView turnView, Integer points) {
        onModelModify(turnView, new UpdatePlayerPointEvent(turnView.getNicknameCurrentPlayer(), points));
    }

    @Override
    public void OnUpdateModelStatusCommonGoal2(TurnView turnView) {
        onModelModify(turnView, new UpdateStatusCommonGoal2Event(turnView.getNicknameCurrentPlayer()));
    }

    @Override
    public void OnUpdateModelStatusCommonGoal1(TurnView turnView) {
        onModelModify(turnView, new UpdateStatusCommonGoal1Event(turnView.getNicknameCurrentPlayer()));
    }

    public void chatThread(){
        ChatThread thread = new ChatThread(userInterface);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //-------------------------Qua scrivo per le shelf---------------------------------//
    @Override
    public void onUpdatePuttedTileIntoShelf(TurnView turnView, int x, int y, PlayableItemTile tile){
        onModelModify(turnView, new UpdatePutShelfTileEvent(x, y, tile));
    }
    @Override
    public void pingReply () throws RemoteException{
    }
}
