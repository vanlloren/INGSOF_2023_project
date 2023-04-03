package server.Controller;

import org.example.ItemTile;
import server.Model.CommonGoal;
import server.Model.GameModel;

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
    public void calculatePoint(ItemTile[][] structure){ // con hascommongoal controllo che il giocatore non abbia gia raggiunto l'obiettivo
        if(!hasCommonGoal1&&commonGoal1.checkGoal(structure)){

            this.points=this.points+commonGoal1.getPoint();
            hasCommonGoal1=true;

        }

        if(!hasCommonGoal2&&commonGoal2.checkGoal(structure)) {
            this.points = this.points + commonGoal2.getPoint();
            hasCommonGoal2 = true;
        }

        if(personalGoal.checkgoal()){
            this.points=this.points+personalGoal.getPoint();


        }
    }
    public int getPoint(CommonGoal commonGoal){
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
