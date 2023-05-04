package server.Controller;
import Util.RandCommonGoal;
import Util.RandPersonalGoal;
import server.Model.*;
import Network.ClientSide.*;
import server.enumerations.GameState;
import Network.message.*;


import java.util.*;

//All'interno di questa classe vi deve essere contenuta tutta la logica che sta dietro al gioco effettivo
//escluso il settaggio della lobby e la costruzione del gioco quindi solo il pescaggio, l'inserimento ed il conteggio sono le azioni da seguire e tenere sott'occhio
public  class GameController implements Observer {

    private  GameModel game;
    private final Client client;
  //  private boolean timeOut;
    private final GameBoardController gameBoardController = new GameBoardController(); // gameBoardController è il tramite tra GameController e le classi GameBoard, LivingRoom e ItemBag
    private transient Map<String ,VirtualView> virtualViewMap;
    /*
    Il comando dichiara un campo della classe come transient,
    il che significa che il campo non sarà incluso nella serializzazione
    dell'oggetto.
    In questo caso, il campo virtualViewMap è una mappa che associa un nickname a una vista virtuale (VirtualView)
    e viene utilizzato dal controller per comunicare con le viste dei singoli giocatori.
    Poiché le viste devono essere ricreate ad
    ogni avvio del gioco e non devono essere incluse nella serializzazione,
     il campo viene dichiarato come transient.
     */
    private GameState gameState;
    private TurnController turnController;

    public GameController(GameModel game,Client client) {
        this.game = game;
        this.client = client;
    }
/*
    public GameModel getGame() {
        return this.game;
    }
*/
    //metodo per avviare sessione gioco
    public void initGameController() {
        this.game = GameModel.getInstance();
        setGameState(GameState.LOGIN);
    }

    public void onMessageReceived(Message receivedMessage) {

        VirtualView virtualView = virtualViewMap.get(receivedMessage.getNickname());
        switch (gameState) {
            case LOGIN:
                loginState(receivedMessage);
                break;
            case INIT:
                if (inputController.checkUser(receivedMessage)) {
                    initState(receivedMessage, virtualView);
                }
                break;
            case IN_GAME:
                if (inputController.checkUser(receivedMessage)) {
                    inGameState(receivedMessage);
                }
                break;
            default: // Should never reach this condition
                Server.LOGGER.warning(STR_INVALID_STATE);
                break;
        }
    }
    private void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
    //metodo per preparare l'inizio della partita: aggiunta giocatori e inizializzazione shelf personali etc..
    public void setUp() {
        /*Set del giocatore e del nickname*/

        Player newPlayer = new Player(client.getNickname());
        RandPersonalGoal.setType(newPlayer, newPlayer.getPersonalGoal(),game.getPlayersInGame());    //Give unique PersonalGoal to player
        game.getPlayersInGame().add(newPlayer);
        game.setPlayersNumber(game.getPlayersNumber());


    }


    /*
    the following method is called when the number of player has reached the requested number
    and as a consequence a game board with the right livingRoom (based on numOfPlayers) is created and the commonGoals are being set and prepared for the game
     */
    public void initGameBoard(){
        gameBoardController.setPlayerNum(game.getPlayersNumber());
        gameBoardController.gameBoardInit();  //inizializza itemBag e livingRoom
        game.setMyShelfie(gameBoardController.getControlledGameBoard());
        RandCommonGoal.setType(game.getMyShelfie().getLivingRoom().getCommonGoal1(), game.getMyShelfie().getLivingRoom().getCommonGoal2());
        game.getMyShelfie().getLivingRoom().getCommonGoal1().setTokens(game.getPlayersNumber());
        game.getMyShelfie().getLivingRoom().getCommonGoal2().setTokens(game.getPlayersNumber());

    }


    public ArrayList<PlayableItemTile> pickTilesArray () {  //restituisce le 1/2/3 tiles prese dalla livingRoom dal player nel suo turno
            boolean finish=false;
            ArrayList<PlayableItemTile> tileArray = new ArrayList<PlayableItemTile>();

            while(!finish) {
                if (gameBoardController.checkPickedTilesNum()) {
                    tileArray=gameBoardController.PickManager(x, y, finish);
                } else {
                    //messaggio che informi che sono già state prese tre tessere
                    finish = true;
                    tileArray=gameBoardController.giveTileToPlayer();
                }
            }

            gameBoardController.toPlayerTilesResetter();
            return tileArray;
    }

        public void InsertTileShelf(Player player,PlayableItemTile tile,int x, int y,int num){
        Shelf shelf = new Shelf();
        shelf = player.getPersonalShelf();
        shelf.putTile(x,y,num); //alf sistema

        calculatePoint(player,player.getPersonalShelf().getStructure(),game.getMyShelfie().getLivingRoom());
        }

        public void nextTurn (Player player) {

        ArrayList<Player> listPLayer = game.getPlayersInGame();

        int index= listPLayer.indexOf(game.getCurrPlayer());
        if(index !=listPLayer.size())
            game.setCurrPlayer(listPLayer.get(index+1));
        else {
            if(!game.getEndGame()) {
                game.setCurrPlayer(listPLayer.get(0));
            }
             //else ->METODO CHE BLOCCA TUTTO E ANNUNCIA VINCITORE
        }
    }


        // metodo che determina l'inizio dell'ultimo turno di gioco
        public void launchEndGame (Player player) {
            if (game.getChairOwner().equals(game.getCurrPlayer())) {
                //METODO CHE BLOCCA TUTTO E ANNUNCIA WINNER
            }
            else{
                game.setEndGame();
            }
        }

        public void calculatePoint (Player player, ItemTile[][]structure, LivingRoom livingRoom) {
            if (!player.getHasCommonGoal1() && CheckCommonGoal.checkGoal(player.getPersonalShelf(), livingRoom.getCommonGoal1().getCommonGoalType())) {
                Integer i;
                i = player.getPoints();
                i = i + addPoint(livingRoom.getCommonGoal1());
                player.setStatusCommonGoal1();
            }

            if (!player.getHasCommonGoal2() && CheckCommonGoal.checkGoal(player.getPersonalShelf(), livingRoom.getCommonGoal2().getCommonGoalType())) {
                Integer i;
                i = player.getPoints();
                i = i + addPoint(livingRoom.getCommonGoal2());
                player.setStatusCommonGoal2();
            }
            if(player.getPersonalGoal().getPoint()<CheckPersonalGoal.calculatePoints(player.getPersonalGoal(), player.getPersonalShelf().getStructure())){
                Integer i;
                Integer x;
                Integer y;
                i = player.getPoints();
                x = CheckPersonalGoal.calculatePoints(player.getPersonalGoal(), player.getPersonalShelf().getStructure());
                y = player.getPersonalGoal().getPoint();
                player.getPersonalGoal().setPoint(x);
                i = i + x - y;

            }
        }


        public Integer addPoint (CommonGoal commonGoal){
            ArrayList<Integer> token_list = commonGoal.getToken_list();
            Integer i = 0;
            if (0 <= token_list.size()) {
                i = token_list.get(token_list.size()-1);
                token_list.remove(token_list.size() - 1);
            }
            return i;
        }

    public void update(Client o, GameModel.Event arg) {
        if (!o.equals(client)) {
            System.err.println("Discarding notification from " + o);
            return;
        }
        arg.equals(GameModel.Event.PLAYERS_IN_GAME);
        game.setPlayersInGame(arg)
        initGame();
    }
//Sono interessato a ricevere notifiche solo dalla TextualUI/GraphicalUI
}

