package server.Controller;
import Util.RandCommonGoal;
import server.Model.*;
import Network.ClientSide.*;


import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

//All'interno di questa classe vi deve essere contenuta tutta la logica che sta dietro al gioco effettivo
//escluso il settaggio della lobby e la costruzione del gioco quindi solo il pescaggio, l'inserimento ed il conteggio sono le azioni da seguire e tenere sott'occhio
public class GameController implements Observer {

    enum GameState {
        AddingPlayers,
        GameFlow,
        LastTurn,
        WinnerRevealed
    }
    private final GameModel game;
    private final Client client;
  //  private boolean timeOut;
    private final GameBoardController gameBoardController = new GameBoardController(); // gameBoardController è il tramite tra GameController e le classi GameBoard, LivingRoom e ItemBag

    public GameController(GameModel game,Client client) {
        this.game = game;
        this.client = client;

        game.addObserver(this);
    }
/*
    public GameModel getGame() {
        return this.game;
    }
*/
    //metodo per avviare sessione gioco
    public void initGame() {

    }

    //metodo per preparare l'inizio della partita: aggiunta giocatori e inizializzazione shelf personali etc..
    public void setUp() {
        /*Set del giocatore e del nickname*/
        Player newPlayer = new Player(client.getNickname());
        game.getPlayersInGame().add(newPlayer);
        game.setPlayersNumber(game.getPlayersNumber());
    }

    public void initGameBoard(){
        RandCommonGoal randCommonGoal = new RandCommonGoal();
        gameBoardController.setPlayerNum(game.getPlayersNumber());
        gameBoardController.gameBoardInit();  //inizializza itemBag e livingRoom riempiendola di tessere
        game.setMyShelfie(gameBoardController.getControlledGameBoard());
        randCommonGoal.setType(game.getMyShelfie().getLivingRoom().getCommonGoal1(), game.getMyShelfie().getLivingRoom().getCommonGoal2());
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
            // DILETTA AGGIUNGI METODO CHE AGGIUNGE PUNTI PER LE TUE CARTE  if(!player.)
        }


        public Integer addPoint (CommonGoal commonGoal){
            ArrayList<Integer> token_list = commonGoal.getToken_list();
            Integer i = 0;
            if (0 < token_list.size()) {
                i = token_list.get(token_list.size());
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

