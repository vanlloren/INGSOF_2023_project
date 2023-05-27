package Network.ClientSide;

import Network.Events.*;
import Network.ServerSide.RemoteServerInterface;
import Network.message.*;
import Observer.*;
import Util.CommonGoalType;
import Util.PersonalGoalType;
import client.view.TurnView;
import client.view.View;
import server.Model.GameBoard;
import server.Model.PlayableItemTile;
import server.Model.Player;
import server.enumerations.PickTileResponse;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;


public  class RemoteClientImplementation extends Client implements RemoteClientInterface, ViewObserver, LivingRoomObserver, ShelfObserver, PlayerObserver, GameModelObserver, PersonalGoalObserver {

    @Serial
    private static final long serialVersionUID = 3324195868427777436L;
    private final PrintStream out = System.out;
    private final Scanner scanner = new Scanner(System.in);
    private RemoteServerInterface server;
    private TurnView turnView;
    private String nickname;
    private boolean gameBegin=false;
    private ArrayList<PlayableItemTile> turnTiles = null;



    public RemoteClientImplementation(String address, int port, View userInterface) throws RemoteException {
        super(address, port, userInterface);
    }

    private void setGameBegin(){
        gameBegin=true;
    }
    @Override
    public void connectionInit() throws Exception {
        Registry registry = LocateRegistry.getRegistry(getServerAddress(), getPortNum());
        server = (RemoteServerInterface) registry.lookup("MyShelfieServer");

    }

    @Override
    public void sendMessage(Message message) throws IOException {

    }

    @Override
    public void closeConnection() throws Exception {

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

            case TO_PUT_TILE_REPLY_ERROR-> {
                ToPutTileReplyMessage newMessage = (ToPutTileReplyMessage) message;
                ArrayList<PlayableItemTile> tilesInPlayerHand = newMessage.getPlayableItemTile();
                this.userInterface.showNegativePutTileResults(tilesInPlayerHand);
            }

            case TO_PUT_TILE_2_OR_3_REPLY_ERROR ->{
                ToPutTile2Or3ReplyMessage newMessage = (ToPutTile2Or3ReplyMessage) message;
                ArrayList<PlayableItemTile> tilesInPlayerHand = newMessage.getPlayableItemTile();
                this.userInterface.showNegativePut2Or3TileResults(tilesInPlayerHand);
            }
            case KEEP_PUTTING_REQUEST -> {
                ToKeepPuttingMessage newMessage = (ToKeepPuttingMessage) message;
                ArrayList<PlayableItemTile> tilesInPlayerHand = newMessage.getPlayableItemTiles();
                this.userInterface.askTileToPut2or3tile(tilesInPlayerHand);
            }
            case FULL_LOBBY -> {
                this.userInterface.fullLobbyTerminateUI();
            }
            case CHOICE_BEGIN -> {
                onUpdateChoiceBegin();
            }


        }
    }

    @Override
    public void onModelModify(TurnView turnView, Event event){
        this.turnView=turnView;
        switch (event.getEventType()){
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
            case UPDATE_GAME_BOARD -> {
                UpdateGameBoardEvent newEvent = (UpdateGameBoardEvent) event;
                UpdateAllClientOnModelGameBoard(newEvent.getGameBoard());
            }
            case UPDATE_GAME_HAS_STARTED -> {
                UpdateAllClientOnModelGameHasStarted();
            }
            case UPDATE_CURR_PLAYER -> {
                UpdateCurrPlayerEvent newEvent = (UpdateCurrPlayerEvent) event;
                UpdateAllClientOnModelCurrPlayer(newEvent.getCurrPlayer());
            }
            case UPDATE_MATCH_WINNER -> {
                UpdateMatchWinnerEvent newEvent = (UpdateMatchWinnerEvent) event;
                UpdateAllClientOnModelMatchWinner(newEvent.getMatchWinner());
            }
            case UPDATE_GAME_HAS_ENDED -> {
                UpdateAllClientOnModelGameHasEnd();
            }
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
                UpdateAllClientOnNewMessageChat(newEvent.getNickname(),newEvent.getChat());

            }
        }
    }
    @Override
    public void UpdateAllClientOnModelGameHasEnd(){
        this.userInterface.resetGameOn();
        System.out.println(".......................\n" +
                           ".......................\n" +
                           ".......................\n" +
                           ".......................\n" +
                           ".......................\n" +
                           ".......GAME OVER......."
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
        System.out.println("....NEW UPDATE:"+nickNameCurrentPlayer+"has now"+points+"points");
    }

    @Override
    public void UpdateAllClientOnModelStatusCommonGoal1(String nickNameCurrentPlayer) {
        System.out.println("...NEW UPDATE:"+nickNameCurrentPlayer+"has satisfied CommonGoal1");
    }

    @Override
    public void UpdateAllClientOnModelStatusCommonGoal2(String nickNameCurrentPlayer) {
        System.out.println("...NEW UPDATE:"+nickNameCurrentPlayer+"has satisfied CommonGoal2");
    }

    @Override
    public void UpdateAllClientOnChairOwner(Player player) {
        System.out.println("...NEW UPDATE: The chair owner is "+player.getNickname());
    }

    @Override
    public void UpdateAllClientOnModelGameHasStarted() {
        System.out.println("LOBBY HAS REACHED THE REQUESTED NUMBER OF PLAYER: GAME IS STARTING.....");

    }

    @Override
    public void onUpdateAllClientOnCurrentPlayer(Player currPlayer) {
        System.out.println("...NEW UPDATE: Now it's"+currPlayer.getNickname()+" turn");
        //this.userInterface.setCurrPlayer(currPlayer.getNickname());

        //this.userInterface.askPlayerNextMove();
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
    public void UpdateAllClientOnModelGameBoard(GameBoard gameBoard) {

    }

    @Override
    public void UpdateAllClientOnNewMessageChat(String Nickname, String chatMessage) {
       System.out.println(Nickname+": "+chatMessage+".");
    }

    @Override
    public void UpdateAllClientOnPickedTileFromLivingRoom(String currPlayer, int x, int y) {
        System.out.println("Il giocatore " + currPlayer + " ha pescato da LivingRoom " +
                "la tessera in posizione: x=" + x + ", y=" + y);

        System.out.println("A seguire verrà stampata la LivingRoom aggiornata");
        this.userInterface.showLivingRoom(turnView.getLivingRoom());
    }

    //
    @Override
    public void onUpdateServerInfo(Map<String, String> serverInfo) {

    }

    public void onUpdateToPickTile() throws RemoteException{
        ArrayList<PlayableItemTile> helperList = new ArrayList<>();

        //pesca prima tessera
        System.out.println("Scegli la posizione x della prima tessera che vuoi pescare!");
        int x = scanner.nextInt();
        out.println("Scegli la posizione y della prima tessera che vuoi pescare!");
        int y = scanner.nextInt();
        TileReplyMessage message = server.onTilePickMessage(nickname, x, y);
        while (message.isTileAccepted().equals(PickTileResponse.MAX_TILE_PICKED) || message.isTileAccepted().equals(PickTileResponse.INVALID_TILE)){
            if(message.isTileAccepted().equals(PickTileResponse.MAX_TILE_PICKED)){
                out.println("Massimo numero di tessere scelte. Ora ti verrà chiesto di collocare le tessere nella tua Shelf");
                this.turnTiles=helperList;
                this.userInterface.setTurnTiles(turnTiles);
                return;
            } else if (message.isTileAccepted().equals(PickTileResponse.INVALID_TILE)) {
                out.println("Tessera scelta non disponibile, scegli un'altra tessera");
                onUpdateShowLivingRoom();
                onUpdateShowAvailableTiles();
                do {
                    System.out.println("Scegli la posizione x della prima tessera che vuoi pescare!");
                    x = scanner.nextInt();
                    if(x<0 || x>8){
                        out.println("Coordinata non valida, riprova!");
                    }
                }while(x<0 || x>8);

                do {
                    out.println("Scegli la posizione y della prima tessera che vuoi pescare!");
                    y = scanner.nextInt();
                    if (y < 0 || y > 8) {
                        out.println("Coordinata non valida, riprova!");
                    }
                }while(y<0 || y>8);

                message = server.onTilePickMessage(nickname, x, y);
            }
        }
        helperList.add(message.getTile());

        scanner.nextLine();
        out.println("Una tessera pescata, vuoi pescare altre tessere? [Y/N]");
        String test = scanner.nextLine();
        while(!test.equals("Y")){
            if(test.equals("N")){
                this.turnTiles=helperList;
                server.onMessage(new StopPickingMessage(nickname));
                out.println("Ora ti verrà chiesto di collocare le tessere nella tua Shelf");
                return;
            }
            out.println("Simbolo errato!");
            scanner.nextLine();
            out.println("Una tessera pescata, vuoi pescare altre tessere? [Y/N]");
            test = scanner.nextLine();
        }

        onUpdateShowLivingRoom();
        onUpdateShowAvailableTiles();
        if(turnView.getLivingRoom().getAvailableTiles().size()==0) {
            out.println("Non ci sono altre tessere disponibili, mi spiace!");
            out.println("Massimo numero di tessere scelte. Ora ti verrà chiesto di collocare le tessere nella tua Shelf");
            this.turnTiles = helperList;
            return;
        }


        //pesca seconda tessera
        do {
            System.out.println("Scegli la posizione x della seconda tessera che vuoi pescare!");
            x = scanner.nextInt();
            if(x<0 || x>8){
                out.println("Coordinata non valida, riprova!");
            }
        }while(x<0 || x>8);

        do {
            out.println("Scegli la posizione y della seconda tessera che vuoi pescare!");
            y = scanner.nextInt();
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
                System.out.println("Scegli la posizione x della seconda tessera che vuoi pescare!");
                x = scanner.nextInt();
                out.println("Scegli la posizione y della seconda tessera che vuoi pescare!");
                y = scanner.nextInt();
                message = server.onTilePickMessage(nickname, x, y);
            }
        }
        helperList.add(message.getTile());

        scanner.nextLine();
        out.println("Due tessere pescate, vuoi pescare altre tessere? [Y/N]");
        test = scanner.nextLine();
        while(!test.equals("Y")){
            if(test.equals("N")){
                this.turnTiles=helperList;
                server.onMessage(new StopPickingMessage(nickname));
                out.println("Ora ti verrà chiesto di collocare le tessere nella tua Shelf");
                return;
            }
            out.println("Simbolo errato!");
            scanner.nextLine();
            out.println("Due tessere pescate, vuoi pescare altre tessere? [Y/N]");
            test = scanner.nextLine();
        }

        onUpdateShowLivingRoom();
        onUpdateShowAvailableTiles();
        if(turnView.getLivingRoom().getAvailableTiles().size()==0) {
            out.println("Non ci sono altre tessere disponibili, mi spiace!");
            out.println("Massimo numero di tessere scelte. Ora ti verrà chiesto di collocare le tessere nella tua Shelf");
            this.turnTiles = helperList;
            return;
        }

        //pesca terza tessera
        do {
            System.out.println("Scegli la posizione x della terza tessera che vuoi pescare!");
            x = scanner.nextInt();
            if(x<0 || x>8){
                out.println("Coordinata non valida, riprova!");
            }
        }while(x<0 || x>8);

        do {
            out.println("Scegli la posizione y della terza tessera che vuoi pescare!");
            y = scanner.nextInt();
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
                System.out.println("Scegli la posizione x della terza tessera che vuoi pescare!");
                x = scanner.nextInt();
                out.println("Scegli la posizione y della terza tessera che vuoi pescare!");
                y = scanner.nextInt();
                message = server.onTilePickMessage(nickname, x, y);
            }
        }
        helperList.add(message.getTile());

        out.println("Massimo numero di tessere scelte. Ora ti verrà chiesto di collocare le tessere nella tua Shelf");
        this.turnTiles=helperList;
    }

    public void onUpdateToStartPutting() throws RemoteException {
        int numOfTiles = turnTiles.size();
        int xPos;
        int yPos;
        int index;
        InsertionReplyMessage message;
        do {
            for (int i = 0; i < turnTiles.size(); i++) {
                out.println("Tile picked: {[" + turnTiles.get(i) + "],");
            }

            out.println("Choose the tile that you want to put in the shelf(Valid insert:[ 0 , 1 , 2 ])");
            index = scanner.nextInt();
            do {
                out.println("The index of the tile is not valid!\n");
                out.println("Choose the tile that you want to put in the shelf(Valid insert:[ 0 , 1 , 2 ])");
                index = scanner.nextInt();
            } while (index < 0 || index > turnTiles.size());
            out.println("Choose the x_coordinate where you want to put the tile in the shelf(Valid insert:[ 0 , 1 , 2 , 3 , 4 , 5 ])");
            xPos = scanner.nextInt();
            while (xPos < 0 || xPos > 5) {
                out.println("The position x for the the insertion of the tile is not valid!\n");
                out.println("Choose the x_coordinate where you want to put the tile in the shelf(Valid insert:[ 0 , 1 , 2 , 3 , 4 , 5 ])");
                xPos = scanner.nextInt();
            }
            out.println("Choose the position y where you want to put the tile(Valid insert: [ 0 , 1 , 2 , 3 , 4 ])!\n");
            yPos = scanner.nextInt();
            while (yPos < 0 || yPos > 4) {
                out.println("The position y for the the insertion of the tile is not valid!\n");
                out.println("Choose the position y where you want to put the tile(Valid insert: [ 0 , 1 , 2 , 3 , 4 ])!\n");
                yPos = scanner.nextInt();
            }

            message = server.ToPutTileRequestMessage(xPos, yPos, turnTiles.get(index), numOfTiles, turnTiles);
            if (!message.isValid())
                out.println("Error in the insertion: coordinates not valid");


        } while (!message.isValid());


        turnTiles.remove(index - 1);


        while (turnTiles.size() > 0) {
            out.println("Choose the tile that you want to put in the shelf(Valid insert:[ 1 , 2 ])");
            index = scanner.nextInt();
            while (index < 1 || index > numOfTiles) {
                out.println("The index of the tile is not valid!\n");
                out.println("Choose the tile that you want to put in the shelf(Valid insert:[ 1 , 2  ])");
                index = scanner.nextInt();
            }
            out.println("Choose the x_coordinate in the shelf(Valid insert:[ 0 , 1 , 2 , 3 , 4 , 5 ])");
            xPos = scanner.nextInt();
            while (xPos < 0 || xPos > 5) {
                out.println("The position x for the the insertion of the tile is not valid!\n");
                out.println("Choose the x_coordinate in the shelf(Valid insert:[ 0 , 1 , 2 , 3 , 4 , 5 ])");
                xPos = scanner.nextInt();
            }
            int finalIndex = index - 1;
            message = server.ToPutTileRequestMessage(xPos, turnTiles.get(finalIndex), numOfTiles, turnTiles);
        }
        if (message.isLastTurn())
            this.userInterface.setIsTurn();

    }








    public void onUpdateToPut2or3Tile(int finalXPos,PlayableItemTile tile , ArrayList<PlayableItemTile> playableItemTiles) throws RemoteException {
        server.onMessage(new ToPut2Or3TileRequestMessage(finalXPos , tile , playableItemTiles));
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
    public void onUpdateChat(String Nickname, String chat) throws RemoteException {
        server.onMessage(new WriteInChatMessage(Nickname, chat));
    }

    @Override
    public void onUpdateFirstPlayer(String nickname) throws RemoteException {

    }

    public void onUpdateIsGameOn(){
        if(turnView.getIsGameOn()){
            this.userInterface.resetGameOn();
        }
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
    public void onUpdateShowPlayersList() {
        this.userInterface.showPlayersList(turnView.getGameModel().getPlayersInGame());
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
    public void onUpdateModelChairOwner(TurnView turnView, Player player) {
        onModelModify(turnView, new UpdateChairOwnerEvent(player));
    }

    @Override
    public void onUpdateGameBoard(TurnView turnView, GameBoard gameBoard) {
        onModelModify(turnView, new UpdateGameBoardEvent(gameBoard));
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
    public void onUpdateModelChat(TurnView turnView, String nickname, String chat) {
        onModelModify(turnView, new UpdateChatEvent(nickname,chat));
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



    //-------------------------Qua scrivo per le shelf---------------------------------//
    @Override
    public void onUpdatePuttedTileIntoShelf(TurnView turnView, int x, int y, PlayableItemTile tile){
        onModelModify(turnView, new UpdatePutShelfTileEvent(x, y, tile));
    }
}
