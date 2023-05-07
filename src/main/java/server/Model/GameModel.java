package server.Model;
import Network.message.LobbyMessage;
import Observer.ServerObservable;


import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

//Rappresenta il turn del gioco sasso carta forbice lizard spock
public class GameModel extends ServerObservable implements Serializable {
    private static final long serialVersionUID = 44051L;


    private Player chosenChairOwner;
    private Integer chosenPlayersNumber;
    public static final String Server_Nick = "Server";//C'è un collegamento al server per ogni giocatore.
    private Player currPlayer;
    //private String matchWinner; possiamo individuarlo attraverso un assaggio logico del giocatore con più punti e poi stabilire
    //Player matchWinner = playerInGame.equals(nickname)
    private GameBoard myShelfie ;
    private ArrayList<Player> playersInGame;
    private boolean endGame;
    //private boolean endGame=false; Implementando l'enum GameState è possibile verificare lo stato della partita e quindi dell'ultimo turno di gioco

    private PlayableItemTile pickTile;

    private PlayableItemTile putTile;

    public GameModel(){
        this.myShelfie = new GameBoard();
        this.playersInGame = new ArrayList<>();
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
        notifyObservers(obs ->{
            try {
                obs.onUpdateModelEndGame(this.endGame);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
        //setChangedAndNotifyObservers(Event.ENDGAME);
    }

    public Player getChairOwner() {
        return this.chosenChairOwner;
    }

    public Player getCurrPlayer(){
        return this.currPlayer;
    }

    public void setMyShelfie(GameBoard gameBoard){
        this.myShelfie = gameBoard;
        //setChangedAndNotifyObservers(Event.GAMEBOARD);
    }

    public int getPlayersNumber() {
        return this.chosenPlayersNumber;
    }

    public void setPlayersNumber(int playersNumber){
        this.chosenPlayersNumber = playersNumber;
        notifyObservers(obs ->{
            try {

                obs.onUpdateModelPlayersNumber(playersNumber);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });

    }

    public void setCurrPlayer(Player currPlayer){
        this.currPlayer = currPlayer;

    }

    public void setChairOwner(Player player) {
      this.chosenChairOwner = player;
        //setChangedAndNotifyObservers(Event.CHAIR_OWNER);
        notifyObservers(obs ->{
            try {
                obs.onUpdateModelChairOwner(player);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public ArrayList<Player> getPlayersInGame() {
        return this.playersInGame;
    }

    public void setPlayersInGame(Player newPlayer) {
        this.playersInGame.add(newPlayer);
        notifyObservers(obs ->{
            try {
                obs.onUpdateModelListPlayers(this.playersInGame);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void setmyShelfie(GameBoard myShelfie) {
        this.myShelfie = myShelfie;
       // setChangedAndNotifyObservers(Event.GAME_BOARD);
        notifyObservers(obs ->{
            try {

                obs.onUpdateGameBoard(myShelfie);
            } catch (RemoteException e) {
                throw new RuntimeException(e);

            }
        });
    }
    public GameBoard getMyShelfie(){
        return myShelfie;
    }



    public ArrayList<String> getPlayersNicknames() {
        ArrayList<String> nicknames = new ArrayList<String>();
        for (Player p : playersInGame) {
            nicknames.add(p.getNickname());
        }
        return nicknames;
    }
    public void reset() {
        chosenChairOwner = null;
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

