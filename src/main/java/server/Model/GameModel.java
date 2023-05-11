package server.Model;


import Observer.GameModelObservable;

import java.io.Serializable;
import java.util.ArrayList;


public class GameModel extends GameModelObservable implements Serializable {
    private static final long serialVersionUID = 44051L;


    private Player chosenChairOwner;
    private Integer chosenPlayersNumber;
    public static final String Server_Nick = "Server";//C'è un collegamento al server per ogni giocatore.
    private Player currPlayer;
    //private String matchWinner; possiamo individuarlo attraverso un assaggio logico del giocatore con più punti e poi stabilire
    //Player matchWinner = playerInGame.equals(nickname)
    private GameBoard myShelfie ;
    private ArrayList<Player> playersInGame;
    private boolean endGame=false;


    public GameModel(){
        this.myShelfie = new GameBoard();
        this.playersInGame = new ArrayList<>();
    }


    public boolean getEndGame(){
        return endGame;
    }
    public void setEndGame(){
        this.endGame = true;
        notifyObservers(obs ->{
            obs.onUpdateModelEndGame(this.endGame);
        });

    }

    public Player getChairOwner() {
        return this.chosenChairOwner;
    }

    public Player getCurrPlayer(){
        return this.currPlayer;
    }

    public void setMyShelfie(GameBoard gameBoard){
        this.myShelfie = gameBoard;
    }

    public int getPlayersNumber() {
        return this.chosenPlayersNumber;
    }

    public void setPlayersNumber(int playersNumber){
        this.chosenPlayersNumber = playersNumber;
        notifyObservers(obs ->{
            obs.onUpdateModelPlayersNumber(playersNumber);
        });

    }

    public void setCurrPlayer(Player currPlayer){
        this.currPlayer = currPlayer;

    }

    public void setChairOwner(Player player) {
      this.chosenChairOwner = player;
        notifyObservers(obs ->{
            obs.onUpdateModelChairOwner(player);
        });
    }

    public ArrayList<Player> getPlayersInGame() {
        return this.playersInGame;
    }

    public void setPlayersInGame(Player newPlayer) {
        this.playersInGame.add(newPlayer);
        notifyObservers(obs ->{
                obs.onUpdateModelListPlayers(this.playersInGame);
        });
    }

    public void setmyShelfie(GameBoard myShelfie) {
        this.myShelfie = myShelfie;
       // setChangedAndNotifyObservers(Event.GAME_BOARD);
        notifyObservers(obs ->{
            obs.onUpdateGameBoard(myShelfie);
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



