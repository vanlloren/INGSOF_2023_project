package server.Model;


import Network.ServerSide.ProxyObserver;
import Observer.GameModelObservable;
import Util.RandChairOwner;
import client.view.TurnView;

import java.io.Serializable;
import java.util.ArrayList;


public class GameModel extends GameModelObservable implements Serializable {
    private static final long serialVersionUID = 44051L;


    private Player chosenChairOwner;
    private Integer chosenPlayersNumber;

    private final ProxyObserver proxyObserver;
    public static final String Server_Nick = "Server";//C'è un collegamento al server per ogni giocatore.
    private Player currPlayer;
    private String matchWinner;
    private GameBoard myShelfie ;
    private ArrayList<Player> playersInGame;
    private boolean endGame=false;
    private boolean GameOn = true;


    public GameModel(ProxyObserver proxyObserver){
        this.myShelfie = new GameBoard(proxyObserver);
        this.playersInGame = new ArrayList<>();
        this.proxyObserver = proxyObserver;
    }


    public boolean getEndGame(){
        return endGame;
    }
    public void setEndGame(){
        this.endGame = true;
        notifyObservers(obs -> obs.onUpdateModelEndGame(this.endGame));

    }

    public void setMatchWinner(Player player){
        this.matchWinner = player.getNickname();
        notifyObservers(obs -> obs.onUpdateModelMatchWinner(matchWinner));
        GameTerminator();
    }

    public void GameTerminator() {
        this.GameOn = false;
        notifyObservers(obs -> obs.onUpdateModelGameHasEnd());

    }
    public boolean getIsGameOn(){
        return this.GameOn;
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
        notifyObservers(obs -> obs.onUpdateModelPlayersNumber(playersNumber));

    }

    public void setCurrPlayer(Player currPlayer){
        this.currPlayer = currPlayer;
        notifyObservers(obs -> obs.onUpdateModelCurrentPlayer(currPlayer));

    }

    public void setChairOwner(Player player) {
      this.chosenChairOwner = player;
        notifyObservers(obs -> obs.onUpdateModelChairOwner(player));
        setCurrPlayer(player);
    }

    public ArrayList<Player> getPlayersInGame() {
        return this.playersInGame;
    }

    public void setPlayersInGame(Player newPlayer) {
        if(playersInGame.size()==chosenPlayersNumber-1){
            this.playersInGame.add(newPlayer);
            notifyObservers(obs -> obs.onUpdateModelListPlayers(newPlayer));
            setChairOwner(playersInGame.get(RandChairOwner.ChooseRand(playersInGame.size())));
            notifyObservers(obs -> obs.onUpdateModelGameHasStarted());

        }
        else {
            this.playersInGame.add(newPlayer);
            notifyObservers(obs -> obs.onUpdateModelListPlayers(newPlayer));
        }
    }

    public void setmyShelfie(GameBoard myShelfie) {
        this.myShelfie = myShelfie;
        notifyObservers(obs -> obs.onUpdateGameBoard(myShelfie));
    }
    public GameBoard getMyShelfie(){
        return myShelfie;
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
            if(p.getNickname().equals(nickname)){
                return false;
            }
        }

        return true;
    }


}



