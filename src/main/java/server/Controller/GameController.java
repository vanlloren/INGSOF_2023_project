package server.Controller;
import server.Model.*;


import java.util.ArrayList;

public class GameController {
    private GameModel game;
    private ArrayList<Player> playersInGame = new ArrayList<Player>();
    private boolean timeOut;

    private GameBoardController gameBoardController = new GameBoardController(); // gameBoardController è il tramite tra GameController e le classi GameBoard, LivingRoom e ItemBag



    public GameController(GameModel game) {
        this.game = game;
    }


    public GameModel getGame() {
        return this.game;
    }

    //metodo per avviare sessione gioco
    public void initGame() {

    }

    //metodo per preparare l'inizio della partita: aggiunta giocatori e inizializzazione shelf personali etc..
    public void setUp(String nickName) {
        Player newPlayer = new Player(nickName);
        playersInGame.add(newPlayer);
    }
    /*metodo che gestische l'evoluzione del turno durante le mosse del giocatore con i rispettivi
     aggiornamenti nei campi del model per quanto riguarda i punteggi e pescaggio e inserimento tiles

     */
    public void initGameBoard(int numOfPlayers){
        gameBoardController.setPlayerNum(numOfPlayers);
        gameBoardController.gameBoardInit();  //inizializza itemBag e livingRoom riempiendola di tessere
        game.setMyShelfie(gameBoardController.getControlledGameBoard());


        livingRoom.setCommonGoal1(); //queste due chiamate vanno modificate in modo da passare tramite GameBoardController
        livingRoom.setCommonGoal2();
        /*
        ricordati metodo random commongoal
         */


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
        shelf.putTile() //alf sistema

        }

        public void nextTurn (Player player) {

        ArrayList<Player> listPLayer = game.getPlayersInGame();

        int index= listPLayer.indexOf(game.getCurrPlayer());
        if(index !=listPLayer.size())
            game.setCurrPlayer(listPLayer.get(index+1));
        else {
            if(getEndGame()==false ) {
                game.setCurrPlayer(listPLayer.get(0));
            }
            else //METODO CHE BLOCCA TUTTO E ANNUNCIA VINCITORE
        }


        // metodo che determina l'inizio dell'ultimo turno di gioco
        public void launchEndGame (Player player) {
            if(game.getChairOwner().equals(game.getCurrPlayer()))
                //METODO CHE BLOCCA TUTTO E LACNIA WINNER
            }
            else game.setEndGame();

        }
        /*public void calculatePoint (Player player, ItemTile[][]structure, LivingRoom livingRoom)
        { // con hascommongoal controllo che il giocatore non abbia gia raggiunto l'obiettivo
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


        }


        public int addPoint (CommonGoal commonGoal){
            ArrayList<Integer> token_list = new ArrayList<Integer>();
            token_list = commonGoal.getToken_list();
            int i = 0; // se ho finite le carte punteggio si assegnano zero punti
            if (0 < token_list.size()) {
                i = token_list.get(token_list.size());
                token_list.remove(token_list.size() - 1);
            }
            return i;
        }
    }*/
}
