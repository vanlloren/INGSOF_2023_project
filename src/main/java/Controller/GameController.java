package Controller;

import Model.GameModel;

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
}
