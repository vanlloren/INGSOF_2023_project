package client.view;

import server.Model.ItemTile;
import server.Model.PlayableItemTile;

import java.util.ArrayList;
import java.util.List;

public interface view {

    String askServerInfo(int portNum);
    //richiede al giocatore d'indicare il nickName
    //è il server che poi si occupa di verificare che sia univoco
    String askNickname();

    //se il player è il primo a collegarsi al server, è lui che crea la partita
    //il server dunque gli chiede quanti giocatori far partecipare
    void askPlayersNumber();

    //chiede al player la posizione nella livingRoom della tessera da spostare
    void askMovingTilePosition(ArrayList<PlayableItemTile> availableTiles);

    //chiede al player se desidera smettere di prendere tiles dalla livingRoom volontariamente
    void askStopPicking();

    //chiede al player quale tessera della sua mano collocare nella shelf
    void askTileToPut(ArrayList<PlayableItemTile> tilesInPlayerHand);

    //chiede al player dove collocare la tessera nella shelf
    //TO-DO: capire in che formato fornire l'elenco delle posizioni libere nella shelf
    void askPlacingTileInShelfPosition();

    //mostra il risultato della richiesta di login, con il nome scelto dal player
    void showLoginResults(boolean nickAccepted, boolean connectionOn, String chosenNickname);

    //mostra lista dei player
    void showPlayersList(List<String> playersList);

    //mostra un messaggio che indica la fine della partita e il vincitore
    void showWinMessage(String winnerNickname);

    //rappresentazione aggiornata mossa per mossa della livingRoom
    void showLivingRoom(ArrayList<PlayableItemTile> livingRoom);

    //rappresentazione aggiornata mossa per mossa della shelf relativa allo specifico Player
    void showPlayerShelf(ArrayList<PlayableItemTile> playerShelf);

    //messaggio che indica al player che un'azione gli ha conferito dei punti
    void showPointMessage();

    //mostra al player il suo punteggio (volendo si può mostrare anche quello degli avversari)
    void showPointCounter(int pointCount);

    //messaggio di disconnessione
    void showDisconnectionMessage(String disconnectedPlayerNickname, String disconnectionMessage);

    void showErrorMessage(String errorMessage);
}
