package server.Controller;
import server.Model.*;


import java.util.ArrayList;

public class GameController {
    private GameModel game;
    private ArrayList<Player> playersInGame = new ArrayList<Player>();
    private boolean timeOut;



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
        game.getPlayersInGame().add(newPlayer);
    }
    /*metodo che gestische l'evoluzione del turno durante le mosse del giocatore con i rispettivi
     aggiornamenti nei campi del model per quanto riguarda i punteggi e pescaggio e inserimento tiles

     */
    public void initLivingRoom(int numOfPlayers){
        LivingRoom livingRoom = new LivingRoom();
        livingRoom = game.getMyShelfie().getLivingRoom();
        game.getMyShelfie().getLivingRoom().createGameTable(numOfPlayers);

        // lore aggiungi metodo che filla la gametable nella maniera giusta

        CommonGoal commonGoal1 = new CommonGoal();
        CommonGoal commonGoal2 = new CommonGoal();
        //metodo random che sceglie i type delle commongoal
        livingRoom.setCommonGoal1(commonGoal1);
        livingRoom.setCommonGoal2(commonGoal2);

        //creation of the tokenlist above each commongoal according to the number of players
        livingRoom.getCommonGoal1().setTokens(game.getPlayersNumber());
        livingRoom.getCommonGoal2().setTokens(game.getPlayersNumber());




    }
       /*public PlayableItemTile pickTile () {

        //PlayableItemTile tile = new PlayableItemTile();

        LORENZO METTI TU I METODI CHE GESTISCONO ETSRAZIONE DELLA TILE
        tile = metodi estrazione etc etc




         //   return tile;




        }*/

        public void InsertTileShelf(Player player,PlayableItemTile tile,int x, int y,int num){
        Shelf shelf = new Shelf();
        shelf = player.getPersonalShelf();
        //shelf.putTile(); //alf sistema

        }

        public void nextTurn (Player player) {

            ArrayList<Player> listPLayer = game.getPlayersInGame();

            int index = listPLayer.indexOf(game.getCurrPlayer());
            if (index != listPLayer.size())
                game.setCurrPlayer(listPLayer.get(index + 1));
            else {
                if (game.getEndGame() == false) {
                    game.setCurrPlayer(listPLayer.get(0));
                }
                //else //METODO CHE BLOCCA TUTTO E ANNUNCIA VINCITORE
            }
        }


            // metodo che determina l'inizio dell'ultimo turno di gioco
            public void launchEndGame () {
                //if (game.getChairOwner().equals(game.getCurrPlayer()))
                //METODO CHE BLOCCA TUTTO E LACNIA WINNER


            // else game.setEndGame();

            }

        public void calculatePoint (Player player, ItemTile[][]structure, LivingRoom livingRoom)
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
    }

