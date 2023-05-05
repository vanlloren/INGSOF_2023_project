package server.Model;
import Network.message.LobbyMessage;
import Observer.Observable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Rappresenta il turn del gioco sasso carta forbice lizard spock
public class GameModel extends Observable implements Serializable {
    private static final long serialVersionUID = 44051L;

    private static GameModel instance;
    private Player chosenChairOwner;
    private Integer chosenPlayersNumber;
    public static final String Server_Nick = "Server";//C'è un collegamento al server per ogni giocatore.
    public static final int maximumNumberPlayers = 4;
    private Player currPlayer;
    //private String matchWinner; possiamo individuarlo attraverso un assaggio logico del giocatore con più punti e poi stabilire
    //Player matchWinner = playerInGame.equals(nickname)
    private final GameBoard myShelfie ;
    private List<Player> playersInGame;
    private boolean endGame;
    //private boolean endGame=false; Implementando l'enum GameState è possibile verificare lo stato della partita e quindi dell'ultimo turno di gioco

    private PlayableItemTile pickTile;

    private PlayableItemTile putTile;

    public GameModel(){
        this.myShelfie = new GameBoard();
        this.playersInGame = new ArrayList<>();
    }
    public static  GameModel getInstance(){
        if(instance == null)
            instance = new GameModel();
        return instance;
    }

    public PlayableItemTile getPickTile() {
        return pickTile;
    }

    public PlayableItemTile getPutTile() {
        return putTile;
    }

    public boolean getEndGame(){
        return endGame;
    }
    public void setEndGame(){
        this.endGame = true;
        setChangedAndNotifyObservers(Event.ENDGAME);
    }

    public Player getChairOwner() {
        return this.chosenChairOwner;
    }

    public Player getCurrPlayer(){
        return this.currPlayer;
    }

    public void setMyShelfie(GameBoard gameBoard){
        this.myShelfie = gameBoard;
        setChangedAndNotifyObservers(Event.GAMEBOARD);
    }

    public int getPlayersNumber() {
        return this.chosenPlayersNumber;
    }

    public void setPlayersNumber(int playersNumber){
        if (playersNumber >0 && playersNumber <=maximumNumberPlayers)
        this.chosenPlayersNumber = playersNumber;
        notifyObservers(new LobbyMessage((getPlayersNicknames()), this.chosenPlayersNumber));
    }

    public void setCurrPlayer(Player currPlayer){
        this.currPlayer = currPlayer;

    }

    public void setChairOwner(Player player) {
      this.chosenChairOwner = player;
        setChangedAndNotifyObservers(Event.CHAIR_OWNER);
    }

    public ArrayList<Player> getPlayersInGame() {
        return this.playersInGame;
    }

    public void setPlayersInGame(Player newPlayer) {
        this.playersInGame.add(newPlayer);
        setChangedAndNotifyObservers(Event.PLAYERS_IN_GAME);
    }

    public void setmyShelfie(GameBoard myShelfie) {
        this.myShelfie = myShelfie;
        setChangedAndNotifyObservers(Event.GAME_BOARD);
    }
    public GameBoard getMyShelfie(){
        return myShelfie;
    }

    @Override
    public String askServerInfo(int portNum) {
        return null;
    }

    @Override
    public void askPlayersNumber() {

    }


    public ArrayList<String> getPlayersNicknames() {
        ArrayList<String> nicknames = new ArrayList<String>();
        for (Player p : playersInGame) {
            nicknames.add(p.getNickname());
        }
        return nicknames;
    }
    public void reset() {
        chairOwner = null;
        currPlayer= null;
        playersInGame = null;
        myShelfie = null;
        endGame= false;
    }

    public boolean isNicknameAvailable(String nickname) {
        for (Player p: playersInGame
             ) {
            if(p.getNickname() == nickname){
                return false;
            }
        }

        return true;
    }


}

