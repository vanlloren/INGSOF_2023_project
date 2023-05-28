package client.view;

import server.Model.*;

import java.util.ArrayList;


public interface View {

    String askServerInfo();

    int askServerPort();
    //richiede al giocatore d'indicare il nickName
    //è il server che poi si occupa di verificare che sia univoco
    void askNickname();

    void setMoveOn();

    void setCurrPlayer(String currPlayer);

    //se il player è il primo a collegarsi al server, è lui che crea la partita
    //il server dunque gli chiede quanti giocatori far partecipare
    void askPlayersNumber();

    //chiede al player la posizione nella livingRoom della tessera da spostare
    void askMovingTilePosition();

    //chiede al player se desidera smettere di prendere tiles dalla livingRoom volontariamente

    //chiede al player quale tessera della sua mano collocare nella shelf
    void askTileToPut();

    void showLoginResults(boolean nicknameAccepted, String chosenNickname);

    void showPlayersList(ArrayList<Player> playerList);




    void fullLobbyTerminateUI();


    void showLivingRoom(SimpleLivingRoom livingRoom);

    //rappresentazione aggiornata mossa per mossa della shelf relativa allo specifico Player
    void showPlayerShelf(SimpleShelf shelf);





    void showNickCurrentPlayer(String Nickname);



    //mostra al player il suo punteggio (volendo si può mostrare anche quello degli avversari)
    void showPartialPoint(int point);

    //messaggio di disconnessione
    void showDisconnectionMessage(String disconnectedPlayerNickname, String disconnectionMessage);

    void showErrorMessage(String errorMessage);

    void showDefaultMessage( String defaultMessage);

    void setNickname(String nickname);




    void setIsTurn();




    void riprendiEsecuzione();

    void resetGameOn();

    void askPlayerNextMove();

    void maxTilesPicked();


    void invalidTileHandler();

    void WriteInChat();
}
