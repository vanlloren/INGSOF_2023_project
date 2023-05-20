package server.Model;


import Observer.GameModelObservable;
import Util.RandChairOwner;
import client.view.TurnView;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;


public class GameModel extends GameModelObservable implements Serializable, SimpleGameModel {
    private static final long serialVersionUID = 44051L;


    private Player chosenChairOwner;
    private Integer chosenPlayersNumber = -1;

    public static final String Server_Nick = "Server";//C'è un collegamento al server per ogni giocatore.
    private Player currPlayer ;
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
        notifyObservers(obs -> {
            try {
                obs.onUpdateModelEndGame(new TurnView(this),this.getCurrPlayer(), this.endGame);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });

    }

    public void setMatchWinner(Player player){
        this.matchWinner = player.getNickname();
        notifyObservers(obs -> {
            try {
                obs.onUpdateModelMatchWinner(new TurnView(this), matchWinner);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
        GameTerminator();
    }

    public void setChat(String Nickname,String chat) {
        this.Chat = chat;
        notifyObservers(obs -> {
            try {
                obs.onUpdateModelChat(new TurnView(this), Nickname,chat);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void GameTerminator() {
        this.GameOn = false;
        notifyObservers(obs -> {
            try {
                obs.onUpdateModelGameHasEnd(new TurnView(this));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });

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
        notifyObservers(obs -> {
            try {
                obs.onUpdateModelPlayersNumber(new TurnView(this), playersNumber);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });

    }

    public void setCurrPlayer(Player currPlayer){
        this.currPlayer = currPlayer;
        notifyObservers(obs -> {
            try {
                obs.onUpdateModelCurrentPlayer(new TurnView(this), currPlayer);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });

    }

    public void setChairOwner(Player player) {
      this.chosenChairOwner = player;
        notifyObservers(obs -> {
            try {
                obs.onUpdateModelChairOwner(new TurnView(this), player);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
            setCurrPlayer(player);
        });

    }



    public ArrayList<Player> getPlayersInGame() {
        return this.playersInGame;
    }

    public void setPlayersInGame(Player newPlayer) {
        if(chosenPlayersNumber.equals(-1)){
            this.playersInGame.add(newPlayer);
            notifyObservers(obs -> {
                try {
                    obs.onUpdateModelListPlayers(new TurnView(this), newPlayer);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            });
                }
        else if (!chosenPlayersNumber.equals(-1)&&playersInGame.size()<chosenPlayersNumber-1){
            this.playersInGame.add(newPlayer);
            notifyObservers(obs -> {
                try {
                    obs.onUpdateModelListPlayers(new TurnView(this), newPlayer);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        else if(!chosenPlayersNumber.equals(-1)&&playersInGame.size()==chosenPlayersNumber-1){
            this.playersInGame.add(newPlayer);
            notifyObservers(obs -> {
                try {
                    obs.onUpdateModelListPlayers(new TurnView(this), newPlayer);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            });
            notifyObservers(obs -> {
                try {
                    obs.OnUpdateModelCommonGoal(new TurnView(this), getMyShelfie().getLivingRoom().getCommonGoal1().getCommonGoalType(), getMyShelfie().getLivingRoom().getCommonGoal2().getCommonGoalType());
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            });
            notifyObservers(obs -> {
                try {
                    obs.onUpdateModelGameHasStarted(new TurnView(this));
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            });
            setChairOwner(playersInGame.get(RandChairOwner.ChooseRand(playersInGame.size())));
        }
        }


    public void setmyShelfie(GameBoard myShelfie) {
        this.myShelfie = myShelfie;
        notifyObservers(obs -> {
            try {
                obs.onUpdateGameBoard(new TurnView(this), myShelfie);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
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



