package server.Controller;
import server.Model.*;


import java.util.ArrayList;

public class GameController {
    GameModel game;

    public  GameController(GameModel game){
        this.game = game;
    }


    public GameModel getGame(){
        return this.game;
    }

    //metodo per avviare sessione gioco
    public void initGame(){

    }
    //metodo per preparare l'inizio della partita: aggiunta giocatori e inizializzazione shelf personali etc..
    public void setUp(String nickName){


    }
    /*metodo che gestische l'evoluzione del turno durante le mosse del giocatore con i rispettivi
     aggiornamenti nei campi del model per quanto riguarda i punteggi e pescaggio e inserimento tiles

     */
    public void GameManager(){

    }

    public void nextTurn(){

    }
    // metodo che determina l'inizio dell'ultimo turno di gioco
    public void launchTerminator(){

    }
    public void calculatePoint(Player player,ItemTile[][] structure,LivingRoom livingRoom) { // con hascommongoal controllo che il giocatore non abbia gia raggiunto l'obiettivo
        if (!player.getHasCommonGoal1() && CheckCommonGoal.checkGoal(player.getPersonalShelf(), livingRoom.getCommonGoal1().getCommonGoalType())){
            Integer i;
            i = player.getPoints();
            i = i + addPoint(livingRoom.getCommonGoal1());
            player.setStatusCommonGoal1();
        }

        if(!player.getHasCommonGoal2()&& CheckCommonGoal.checkGoal(player.getPersonalShelf(),livingRoom.getCommonGoal2().getCommonGoalType())) {
            Integer i;
            i = player.getPoints();
            i = i + addPoint(livingRoom.getCommonGoal2());
            player.setStatusCommonGoal2();
        }


    }


    public int addPoint(CommonGoal commonGoal){
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
