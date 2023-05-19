package server.Model;


import Observer.GameModelObservable;
import Util.RandChairOwner;
import client.view.TurnView;

import java.io.Serializable;
import java.util.ArrayList;


public class GameModel extends GameModelObservable implements Serializable, SimpleGameModel {
    private static final long serialVersionUID = 44051L;


    private Player chosenChairOwner;
    private Integer chosenPlayersNumber;

    public static final String Server_Nick = "Server";//C'è un collegamento al server per ogni giocatore.
    private Player currPlayer;
    private String matchWinner;
    private GameBoard myShelfie ;
    private ArrayList<Player> playersInGame;
    private boolean endGame=false;
    private boolean GameOn = true;
    private String Chat;

    public GameModel(){
        this.myShelfie = new GameBoard(this);
        this.playersInGame = new ArrayList<>();
    }



    public boolean getEndGame(){
        return endGame;
    }
    public void setEndGame(){
        this.endGame = true;
        notifyObservers(obs -> obs.onUpdateModelEndGame(new TurnView(this),this.getCurrPlayer(), this.endGame));

    }

    public void setMatchWinner(Player player){
        this.matchWinner = player.getNickname();
        notifyObservers(obs -> obs.onUpdateModelMatchWinner(new TurnView(this), matchWinner));
        GameTerminator();
    }

    public void setChat(String Nickname,String chat) {
        this.Chat = chat;
        notifyObservers(obs -> obs.onUpdateModelChat(new TurnView(this), Nickname,chat));
    }

    public void GameTerminator() {
        this.GameOn = false;
        notifyObservers(obs -> obs.onUpdateModelGameHasEnd(new TurnView(this)));

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
        notifyObservers(obs -> obs.onUpdateModelPlayersNumber(new TurnView(this), playersNumber));

    }

    public void setCurrPlayer(Player currPlayer){
        this.currPlayer = currPlayer;
        notifyObservers(obs -> obs.onUpdateModelCurrentPlayer(new TurnView(this), currPlayer));

    }

    public void setChairOwner(Player player) {
      this.chosenChairOwner = player;
        notifyObservers(obs -> obs.onUpdateModelChairOwner(new TurnView(this), player));
        setCurrPlayer(player);
    }



    public ArrayList<Player> getPlayersInGame() {
        return this.playersInGame;
    }

    public void setPlayersInGame(Player newPlayer) {
        if(playersInGame.size()==chosenPlayersNumber-1){
            this.playersInGame.add(newPlayer);
            notifyObservers(obs -> obs.onUpdateModelListPlayers(new TurnView(this), newPlayer));
            notifyObservers(obs -> obs.onUpdateModelGameHasStarted(new TurnView(this)));
            setChairOwner(playersInGame.get(RandChairOwner.ChooseRand(playersInGame.size())));

        }
        else {
            this.playersInGame.add(newPlayer);
            notifyObservers(obs -> obs.onUpdateModelListPlayers(new TurnView(this), newPlayer));
        }
    }

    public void setmyShelfie(GameBoard myShelfie) {
        this.myShelfie = myShelfie;
        notifyObservers(obs -> obs.onUpdateGameBoard(new TurnView(this), myShelfie));
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



