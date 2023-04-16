package server.Controller;

import server.Model.GameModel;


public class ControllerIntermediate {
    GameController game = new GameController();
    private boolean timeOut = false;
    /* classe che gestisce la ricezione di messaggi e gestisce le chiamate verso i metodi di controller
    per la gestione del turno del giocatore e di tutte gli eventi che accadono in un turno con gli aggiornamenti
    dei dati nel modello
     */
    ControllerIntermediate (GameController gameController//devi passare anche la view)
    public void loginHandler(){
       /* -ricevuto messaggio da client di primo login avvia timer e inizializza gamemodel e game e inizia ad aggiungere giocatori tramite metodo setup purchè nel limite
        di numero e di tempo timeout e inizializza valori come la shelf personale.. -> se no giocatori sufficiente entro timeout avvia endgame che restarta lobby, se giocatori sufficienti entro i limiti di numero sia che timeout finito o no avvia initgame che determina l'inizio della vera e propria partita

*/

    }
public void initGame(){
    /*

    QUESTA PARTE VIENE CHIAMATA SE NON HO CHIAMATO ENDGAME DURANTE I LOGIN
    -se startgame è stato lanciato allora inizializzo la livingRoom e collego tutti i giocatori a questa cosi vedono la stessa gameboard e stessi commongoal usando i metodi setter etc etc..
    -al primo giocatore comparirà messaggio da parte del controller metodo picktile che  estrae oggetto dalla board e verifica le eventuali eccezzione, se non ci sono dentro picktile abbiamo i metodi che aggiornano i valori di livingRoom
    - mando messaggio al client se vuole ancora estrarre carte..
    - ad ogni estrazione vaòutata correttamente chiamo l'inserimento nella shelf che chiama se ci sono le eccezioni, se non ci sono inserisco e valuto il conseguimento degli obiettivi common che personal che altro,
    -quando il giocatore dice che non vuole piu estrarre carte tramite il turnController cambio turno al succ
    -ripeti questo fino a quando un giocatore non ha riempito tutta la sua shelf e quindi avvia metodo launchterminator che determina ultimo turno e finito questo turno finale chiama endgame che calcola chi è il vincitore e gli manda il messaggio di vittoria





     */
}













    public void setGameController(GameModel game){
    this.game = game;

    }

}
