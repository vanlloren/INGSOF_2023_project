package server.Controller;
import Network.message.*;
import Observer.Observer;
import Observer.ViewObserver;

import javax.swing.text.View;

import java.util.concurrent.ExecutorService;
/*
Il pacchetto Java java.util.concurrent fornisce una serie di strumenti
per la gestione del multithreading e dell'esecuzione parallela dei task
in un programma. Tra questi strumenti,
 l'interfaccia ExecutorService è uno dei più importanti e utilizzati.
 */
import java.util.concurrent.Executors;

/*
è un interprete tra la parte di network ed una generica view: riceve i messaggi,definiti nel pacchetto messaggi, li codifica e li passa al network/server
 */
public class ClientServerController implements ViewObserver, Observer {

    private final View view;

    private clientInterface client;
    private String nickname;

    private final ExecutorService taskQueue;
    /*
    ExecutorService definisce un framework per l'esecuzione di task
     in modo asincrono, attraverso l'utilizzo di un pool di thread.
      Il pool di thread viene creato al momento della creazione dell'oggetto ExecutorService e può essere composto da un numero variabile di thread,
    che vengono riutilizzati per eseguire i vari task
     */

    public ClientServerController(View view) {
        this.view = view;
        taskQueue = Executors.newSingleThreadExecutor();
    }

    @Override
    public void onUpdateNickname(String nickname) {
        this.nickname = nickname;
        client.sendMessage(new LoginRequestMessage(this.nickname));

    }
    public void onUpdatePlayersNumber(int playersNumber) {
        client.sendMessage(new PlayersNumberReplyMessage(playersNumber));
    }

    @Override
    public void update(Message message) {
        switch (message.getMessageEnumeration()) {
            case LOGIN_REPLY:
                LoginReplyMessage loginReplyMessage = (LoginReplyMessage) message;
                taskQueue.execute(()->view.showLoginResult(loginReplyMessage.isNicknameUniqueAccepted(), loginReplyMessage.isConnectionVerified(), this.nickname));
                break;
            case PLAYERNUMBER_REQUEST:
                taskQueue.execute(view::askPlayersNumber);
                break;
            case LOBBY:
                LobbyMessage lobbyMessage = (LobbyMessage) message;
                taskQueue.execute(() -> view.showLobby(lobbyMessage.getNicknameList(), lobbyMessage.getMaxPlayers()));
                break;
            default:
                break;
        }
    }
}
