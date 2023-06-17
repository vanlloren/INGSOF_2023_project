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
                    onUpdateChoiceBegin();



        }
    }

    @Override
    public void onModelModify(TurnView turnView, Event event){
        this.turnView=turnView;
        switch (event.getEventType()){
            case UPDATE_REFILL_LIVINGROOM ->
                    UpdateAllClientOnRefillLivingRoom();
            case UPDATE_PLAYERS_LIST -> {
                UpdatePlayersListEvent newEvent = (UpdatePlayersListEvent) event;
                UpdateAllClientOnModelListPlayers(newEvent.getPlayer());
            }
            case UPDATE_END_GAME -> {
                UpdateEndGameEvent newEvent = (UpdateEndGameEvent) event;
                UpdateAllClientOnModelEndGame(newEvent.getPlayer(),newEvent.isEndGame());
            }
            case UPDATE_PLAYERS_NUMBER -> {
                UpdatePlayersNumberEvent newEvent = (UpdatePlayersNumberEvent) event;
                UpdateAllClientOnPlayersNumber(newEvent.getPlayersNumber());
            }
            case UPDATE_CHAIR_OWNER -> {
                UpdateChairOwnerEvent newEvent = (UpdateChairOwnerEvent) event;
                UpdateAllClientOnChairOwner(newEvent.getPlayer());


            }
            case UPDATE_GAME_HAS_STARTED ->
                    UpdateAllClientOnModelGameHasStarted();

            case UPDATE_CURR_PLAYER -> {
                UpdateCurrPlayerEvent newEvent = (UpdateCurrPlayerEvent) event;
                UpdateAllClientOnModelCurrPlayer(newEvent.getCurrPlayer());
            }
            case UPDATE_MATCH_WINNER -> {
                UpdateMatchWinnerEvent newEvent = (UpdateMatchWinnerEvent) event;
                UpdateAllClientOnModelMatchWinner(newEvent.getMatchWinner());
            }
            case UPDATE_GAME_HAS_ENDED ->
                    UpdateAllClientOnModelGameHasEnd();

            case UPDATE_PICKED_LIVINGROOM_TILE -> {
                UpdatePickedLivingRoomTileEvent newEvent = (UpdatePickedLivingRoomTileEvent) event;
                UpdateAllClientOnPickedTileFromLivingRoom(newEvent.getCurrPlayer(), newEvent.getXPos(), newEvent.getYPos());
            }
            case UPDATE_PERSONAL_GOAL -> {
                UpdatePersonalGoalEvent newEvent = (UpdatePersonalGoalEvent) event;
                UpdateAllClientOnModelPersonalGoal(newEvent.getPlayer(), newEvent.getPersonalGoalType());
            }
            case UPDATE_COMMON_GOAL -> {
                UpdateCommonGoalEvent newEvent = (UpdateCommonGoalEvent) event;
                UpdateAllClientOnModelCommonGoal(newEvent.getCommonGoalType1(), newEvent.getCommonGoalType2());
            }
            case UPDATE_PLAYER_POINTS -> {
                UpdatePlayerPointEvent newEvent = (UpdatePlayerPointEvent) event;
                UpdateAllClientOnModelPlayerPoint(newEvent.getPlayer(), newEvent.getPoints());
            }
            case UPDATE_STATUS_COMMON_GOAL2 -> {
                UpdateStatusCommonGoal2Event newEvent = (UpdateStatusCommonGoal2Event) event;
                UpdateAllClientOnModelStatusCommonGoal2(newEvent.getNickname());
            }
            case UPDATE_STATUS_COMMON_GOAL1 -> {
                UpdateStatusCommonGoal1Event newEvent = (UpdateStatusCommonGoal1Event) event;
                UpdateAllClientOnModelStatusCommonGoal1(newEvent.getNickname());
            }
            case UPDATE_PUT_SHELF_TILE -> {
                UpdatePutShelfTileEvent newEvent = (UpdatePutShelfTileEvent) event;
                UpdateAllClientOnStructureShelf(newEvent.getXPos(), newEvent.getYPos(), newEvent.getTile());
            }
            case UPDATE_CHAT -> {
                UpdateChatEvent newEvent = (UpdateChatEvent) event;
                UpdateAllClientOnNewMessageChat(newEvent.getNickname(),newEvent.getChat(),newEvent.getReceiver());

            }
        }
    }

    private void UpdateAllClientOnRefillLivingRoom() {
        System.out.println("NEW UPDATE: LivingRoom has been refilled!");
        System.out.println("New LivingRoom will now be printed");
        this.userInterface.showLivingRoom(turnView.getLivingRoom());
    }

    @Override
    public void UpdateAllClientOnModelGameHasEnd(){
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
    @Override
    public void UpdateAllClientOnModelMatchWinner(String matchWinner) {
        System.out.println("CONGRATULATION : "+matchWinner+" HAS WON THIS GAME");
    }
    @Override
    public void UpdateAllClientOnModelCurrPlayer(Player currPlayer) {
        this.userInterface.setCurrPlayer(currPlayer.getNickname());
        System.out.println("NEW UPDATE: " + currPlayer.getNickname() + " is now the current player");
        this.userInterface.riprendiEsecuzione();
    }

    @Override
    public void disconnect() throws RemoteException {
    System.out.println("ciao dal server");
    }

    @Override
    public void UpdateAllClientOnModelListPlayers(Player player) {
        System.out.println("...NEW UPDATE: We have a new player: "+player.getNickname()+" has joined this lobby");
    }

    @Override
    public void UpdateAllClientOnModelEndGame(String Nickname,boolean endGame) {
        System.out.println(Nickname+" HAS FILLED ALL HIS SHELF FOR FIRST :" +
                "lAST TURN IN GAME....");
    }
    // gestisci un booleano per quando sei arrivato all'ultimo giocatore del turno finale
    // dal controller si determina il vincitore si comunica e a quel punto lancio

    @Override
    public void UpdateAllClientOnPlayersNumber(int playersNumber) {

        System.out.println("Your request of playerNumber for this game has been accepted\n" +
                "This game will have "+playersNumber+" players");

    }



    @Override
    public void UpdateAllClientOnModelPersonalGoal(String Nickname,PersonalGoalType personalGoalType) {
        System.out.println("....NEW UPDATE:The PersonalGoal assigned to player "+Nickname+" is "+personalGoalType);
    }

    @Override
    public void UpdateAllClientOnModelCommonGoal(CommonGoalType commonGoalType1, CommonGoalType commonGoalType2) {
        System.out.println("....NEW UPDATE:CommonGoal 1 for this game is "+commonGoalType1);
        System.out.println("....NEW UPDATE:CommonGoal 2 for this game is "+commonGoalType2);
    }

    @Override
    public void UpdateAllClientOnModelPlayerPoint(String nickNameCurrentPlayer, Integer points) {
        System.out.println("....NEW UPDATE:" +nickNameCurrentPlayer+" has now"+points+" points");
    }

    @Override
    public void UpdateAllClientOnModelStatusCommonGoal1(String nickNameCurrentPlayer) {
        System.out.println("....NEW UPDATE:"+nickNameCurrentPlayer+"has satisfied CommonGoal1");
    }

    @Override
    public void UpdateAllClientOnModelStatusCommonGoal2(String nickNameCurrentPlayer) {
        System.out.println("....NEW UPDATE:"+nickNameCurrentPlayer+"has satisfied CommonGoal2");
    }

    @Override
    public void UpdateAllClientOnChairOwner(Player player) {
        System.out.println("....NEW UPDATE: The chair owner is "+player.getNickname());
    }

    @Override
    public void UpdateAllClientOnModelGameHasStarted() {
        System.out.println("LOBBY HAS REACHED THE REQUESTED NUMBER OF PLAYER: GAME IS STARTING.....");

    }



    public void onUpdateChoiceBegin(){
        this.userInterface.riprendiEsecuzione();
    }

    @Override
    public void UpdateAllClientOnStructureShelf(int x, int y, PlayableItemTile Tile) {
        System.out.println("Il giocatore " + turnView.getNicknameCurrentPlayer() + " ha posizionato la tessera "
                + Tile.getColour() + " " + Tile.getIdCode() + " nella posizione x=" + x + ", y=" + y);

        System.out.println("A seguire verrà stampata la PlayerShelf aggiornata");
        this.userInterface.showPlayerShelf(turnView.getShelfTable(turnView.getNicknameCurrentPlayer()));

    }



    @Override
    public void UpdateAllClientOnNewMessageChat(String Nickname, String chatMessage,String receiver) {
        if(receiver.equals(this.nickname)||receiver.equals("Everyone")){
            System.out.println(Nickname+": "+chatMessage+".");
        }
    }

    @Override
    public void UpdateAllClientOnPickedTileFromLivingRoom(String currPlayer, int x, int y) {
        System.out.println("Il giocatore " + currPlayer + " ha pescato da LivingRoom " +
                "la tessera in posizione: x=" + x + ", y=" + y);

        System.out.println("A seguire verrà stampata la LivingRoom aggiornata");
        this.userInterface.showLivingRoom(turnView.getLivingRoom());
    }

    //


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

        /*buffer = scanner.nextLine();
        while(buffer.equals("chat")){
            ChatThread thread = new ChatThread(userInterface, this);
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            buffer = scanner.nextLine();
        }*/
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

        //pesca terza tessera
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
            out.println("Tile picked: {[" + turnTile.getColour() + ", " + turnTile.getIdCode() + "]," + " in position : " + i + "}");
            i++;
        }
        switch (turnTiles.size()) {
            case 1 -> out.println("Choose the tile that you want to put in the shelf(Valid insert:[ 0 ])");
            case 2 -> out.println("Choose the tile that you want to put in the shelf(Valid insert:[ 0 , 1 ])");
            case 3 -> out.println("Choose the tile that you want to put in the shelf(Valid insert:[ 0 , 1 , 2 ])");
        }
        while (!scanner.hasNextInt()) {
            buffer = scanner.nextLine();
            if (buffer.equals("chat")) {
                chatThread();
                switch (turnTiles.size()) {
                    case 1 -> out.println("Choose the tile that you want to put in the shelf(Valid insert:[ 0 ])");
                    case 2 -> out.println("Choose the tile that you want to put in the shelf(Valid insert:[ 0 , 1 ])");
                    case 3 -> out.println("Choose the tile that you want to put in the shelf(Valid insert:[ 0 , 1 , 2 ])");
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
            if(index < 0 || index > turnTiles.size()-1) {
                out.println("The index of the tile is not valid!\n");
                switch (turnTiles.size()) {
                    case 1 -> out.println("Choose the tile that you want to put in the shelf(Valid insert:[ 0 ])");
                    case 2 -> out.println("Choose the tile that you want to put in the shelf(Valid insert:[ 0 , 1 ])");
                    case 3 -> out.println("Choose the tile that you want to put in the shelf(Valid insert:[ 0 , 1 , 2 ])");
                }
                while(!scanner.hasNextInt()) {
                    buffer = scanner.nextLine();
                    if(buffer.equals("chat")) {
                        chatThread();
                        switch (turnTiles.size()) {
                            case 1 -> out.println("Choose the tile that you want to put in the shelf(Valid insert:[ 0 ])");
                            case 2 -> out.println("Choose the tile that you want to put in the shelf(Valid insert:[ 0 , 1 ])");
                            case 3 -> out.println("Choose the tile that you want to put in the shelf(Valid insert:[ 0 , 1 , 2 ])");
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

        do {
            out.println("Choose the x_coordinate where you want to put the tile in the shelf(Valid insert:[ 0 , 1 , 2 , 3 , 4 , 5 ])");
            while (!scanner.hasNextInt()) {
                buffer = scanner.nextLine();
                if (buffer.equals("chat")) {
                    chatThread();
                    out.println("Choose the x_coordinate where you want to put the tile in the shelf(Valid insert:[ 0 , 1 , 2 , 3 , 4 , 5 ])");
                } else {
                    if (!buffer.equals("")) {
                        out.println("Input non valido, scrivi 'chat' per usare la chat oppure indica un intero");
                    }
                }
            }
            xPos = scanner.nextInt();
            scanner.nextLine();
            while (xPos < 0 || xPos > 5) {
                out.println("The position x for the the insertion of the tile is not valid!\n");
                out.println("Choose the x_coordinate where you want to put the tile in the shelf(Valid insert:[ 0 , 1 , 2 , 3 , 4 , 5 ])");
                while (!scanner.hasNextInt()) {
                    buffer = scanner.nextLine();
                    if (buffer.equals("chat")) {
                        chatThread();
                        out.println("Choose the x_coordinate where you want to put the tile in the shelf(Valid insert:[ 0 , 1 , 2 , 3 , 4 , 5 ])");
                    } else {
                        if (!buffer.equals("")) {
                            out.println("Input non valido, scrivi 'chat' per usare la chat oppure indica un intero");
                        }
                    }
                }
                xPos = scanner.nextInt();
                scanner.nextLine();
            }
            out.println("Choose the position y where you want to put the tile(Valid insert: [ 0 , 1 , 2 , 3 , 4 ])!\n");
            while (!scanner.hasNextInt()) {
                buffer = scanner.nextLine();
                if (buffer.equals("chat")) {
                    chatThread();
                    out.println("Choose the position y where you want to put the tile(Valid insert: [ 0 , 1 , 2 , 3 , 4 ])!\n");
                } else {
                    if (!buffer.equals("")) {
                        out.println("Input non valido, scrivi 'chat' per usare la chat oppure indica un intero");
                    }
                }
            }
            yPos = scanner.nextInt();
            scanner.nextLine();
            while (yPos < 0 || yPos > 4) {
                out.println("The position y for the the insertion of the tile is not valid!\n");
                out.println("Choose the position y where you want to put the tile(Valid insert: [ 0 , 1 , 2 , 3 , 4 ])!\n");
                while (!scanner.hasNextInt()) {
                    buffer = scanner.nextLine();
                    if (buffer.equals("chat")) {
                        chatThread();
                        out.println("Choose the position y where you want to put the tile(Valid insert: [ 0 , 1 , 2 , 3 , 4 ])!\n");
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
                out.println("Error in the insertion: coordinates not valid");
            }
        } while (!message.isValid());

        turnTiles.remove(index);

        while (turnTiles.size() > 0) {
            i=0;
            for (PlayableItemTile turnTile : turnTiles) {
                out.println("Tile picked: {[" + turnTile.getColour() + ", " + turnTile.getIdCode() + "]," + " in position : " + i + "}");
                i++;
            }
            switch (turnTiles.size()){
                case 1-> out.println("Choose the tile that you want to put in the shelf(Valid insert:[ 0 ])");
                case 2-> out.println("Choose the tile that you want to put in the shelf(Valid insert:[ 0 , 1 ])");
            }
            while (!scanner.hasNextInt()) {
                buffer = scanner.nextLine();
                if (buffer.equals("chat")) {
                    chatThread();
                    switch (turnTiles.size()) {
                        case 1 -> out.println("Choose the tile that you want to put in the shelf(Valid insert:[ 0 ])");
                        case 2 -> out.println("Choose the tile that you want to put in the shelf(Valid insert:[ 0 , 1 ])");
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
                {out.println("The index of the tile is not valid!\n");
                    switch (turnTiles.size()) {
                        case 1 -> out.println("Choose the tile that you want to put in the shelf(Valid insert:[ 0 ])");
                        case 2 -> out.println("Choose the tile that you want to put in the shelf(Valid insert:[ 0 , 1 ])");
                    }
                    while(!scanner.hasNextInt()) {
                        buffer = scanner.nextLine();
                        if(buffer.equals("chat")) {
                            chatThread();
                            switch (turnTiles.size()) {
                                case 1 -> out.println("Choose the tile that you want to put in the shelf(Valid insert:[ 0 ])");
                                case 2 -> out.println("Choose the tile that you want to put in the shelf(Valid insert:[ 0 , 1 ])");
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

            out.println("Choose the x_coordinate in the shelf(Valid insert:[ 0 , 1 , 2 , 3 , 4 , 5 ])");
            while (!scanner.hasNextInt()) {
                buffer = scanner.nextLine();
                if (buffer.equals("chat")) {
                    chatThread();
                    out.println("Choose the x_coordinate where you want to put the tile in the shelf(Valid insert:[ 0 , 1 , 2 , 3 , 4 , 5 ])");
                } else {
                    if (!buffer.equals("")) {
                        out.println("Input non valido, scrivi 'chat' per usare la chat oppure indica un intero");
                    }
                }
            }
            xPos = scanner.nextInt();
            scanner.nextLine();
            while (xPos < 0 || xPos > 5) {
                out.println("The position x for the the insertion of the tile is not valid!");
                out.println("Choose the x_coordinate in the shelf(Valid insert:[ 0 , 1 , 2 , 3 , 4 , 5 ])");
                while (!scanner.hasNextInt()) {
                    buffer = scanner.nextLine();
                    if (buffer.equals("chat")) {
                        chatThread();
                        out.println("Choose the x_coordinate where you want to put the tile in the shelf(Valid insert:[ 0 , 1 , 2 , 3 , 4 , 5 ])");
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
                out.println("Error in the insertion: coordinates not valid");
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
        this.userInterface.showPlayerShelf(turnView.getShelfTable(nickname));
    }

    @Override
    public int onUpdateShowPartialPoint(String nickname) {
        return turnView.getPartialPoint(nickname);
    }

    @Override
    public void onUpdateShowNickCurrPlayer() {
        this.userInterface.showNickCurrentPlayer(turnView.getNicknameCurrentPlayer());
    }




    @Override
    public void onDisconnection() {

    }


    //---------------------------------//
    @Override
    public void onUpdateModelListPlayers(TurnView turnView, Player player) {
        onModelModify(turnView, new UpdatePlayersListEvent(player));
    }

    @Override
    public void onUpdateModelEndGame(TurnView turnView,Player player, boolean endGame)  {
        onModelModify(turnView, new UpdateEndGameEvent(player.getNickname(), endGame));
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
